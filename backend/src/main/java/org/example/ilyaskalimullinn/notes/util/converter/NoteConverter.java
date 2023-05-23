package org.example.ilyaskalimullinn.notes.util.converter;

import org.example.ilyaskalimullinn.notes.data.entity.note.Note;
import org.example.ilyaskalimullinn.notes.data.entity.note.blocks.*;
import org.example.ilyaskalimullinn.notes.data.entity.note.blocks.subblocks.NoteChecklistBlockItem;
import org.example.ilyaskalimullinn.notes.data.entity.note.blocks.subblocks.NoteListBlockItem;
import org.example.ilyaskalimullinn.notes.data.serializer.note.NoteContentSerializer;
import org.example.ilyaskalimullinn.notes.data.serializer.note.NoteSerializer;
import org.example.ilyaskalimullinn.notes.data.serializer.note.block.*;
import org.example.ilyaskalimullinn.notes.data.serializer.note.block.data.*;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.GenericConverter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class NoteConverter implements GenericConverter {
    protected Set<ConvertiblePair> convertiblePairs;

    protected final String CONVERTER_METHOD_FORMAT = "convert%sTo%s";

    public NoteConverter() {
        this.convertiblePairs = new HashSet<>();
        this.convertiblePairs.add(new ConvertiblePair(NoteSerializer.class, Note.class));
        this.convertiblePairs.add(new ConvertiblePair(Note.class, NoteSerializer.class));
    }

    @Override
    public Set<ConvertiblePair> getConvertibleTypes() {
        return this.convertiblePairs;
    }

    @Override
    @NonNull
    public Object convert(Object source,
                          @NonNull TypeDescriptor sourceType,
                          @NonNull TypeDescriptor targetType) {

        ConvertiblePair pair = this.convertiblePairs.stream()
                .filter(convertiblePair -> convertiblePair.getSourceType().equals(sourceType.getType())
                        && convertiblePair.getTargetType().equals(targetType.getType())
                ).findFirst().orElseThrow(() -> new ConversionNotSupportedException(source, null, null));

        try {
            String methodName = getConverterMethodName(pair);

            Method method = this.getClass().getDeclaredMethod(methodName, sourceType.getType());
            return method.invoke(this, sourceType.getType().cast(source));
        } catch (Exception e) {
            throw new ConversionFailedException(sourceType, targetType, source, e);
        }
    }

    protected String getConverterMethodName(ConvertiblePair convertiblePair) {
        return getConverterMethodName(convertiblePair.getSourceType().getSimpleName(),
                convertiblePair.getTargetType().getSimpleName());
    }

    protected String getConverterMethodName(String from, String to) {
        return CONVERTER_METHOD_FORMAT.formatted(from, to);
    }

    protected String getConverterMethodName(Class<?> from, Class<?> to) {
        return CONVERTER_METHOD_FORMAT.formatted(from.getSimpleName(), to.getSimpleName());
    }

    protected Note convertNoteSerializerToNote(NoteSerializer noteSerializer) throws NoSuchMethodException,
            InvocationTargetException, IllegalAccessException {
        Note note = Note.builder()
                .title(noteSerializer.getTitle())
                .editorVersion(noteSerializer.getContent().getVersion())
                .id(noteSerializer.getId())
                .build();

        List<NoteBlockSerializer> blockSerializers = noteSerializer.getContent().getBlocks();

        List<NoteBlock> blocks = new ArrayList<>();

        for (NoteBlockSerializer blockSerializer : blockSerializers) {
            String methodName = this.getConverterMethodName(blockSerializer.getClass(), NoteBlock.class);

            Method method = this.getClass().getDeclaredMethod(methodName, blockSerializer.getClass());
            NoteBlock block = (NoteBlock) method.invoke(this, blockSerializer);
            block.setNote(note);
            blocks.add(block);
        }

        note.setBlocks(blocks);

        return note;
    }

    protected NoteParagraphBlock convertNoteParagraphBlockSerializerToNoteBlock
            (NoteParagraphBlockSerializer noteParagraphBlockSerializer) {
        return NoteParagraphBlock.builder()
                .text(noteParagraphBlockSerializer.getData().getText())
                .build();
    }

    protected NoteHeaderBlock convertNoteHeaderBlockSerializerToNoteBlock
            (NoteHeaderBlockSerializer noteHeaderBlockSerializer) {
        return NoteHeaderBlock.builder()
                .text(noteHeaderBlockSerializer.getData().getText())
                .level(noteHeaderBlockSerializer.getData().getLevel())
                .build();
    }

    protected NoteQuoteBlock convertNoteQuoteBlockSerializerToNoteBlock
            (NoteQuoteBlockSerializer noteQuoteBlockSerializer) {
        return NoteQuoteBlock.builder()
                .alignment(noteQuoteBlockSerializer.getData().getAlignment())
                .text(noteQuoteBlockSerializer.getData().getText())
                .caption(noteQuoteBlockSerializer.getData().getCaption())
                .build();
    }

    protected NoteCodeBlock convertNoteCodeBlockSerializerToNoteBlock
            (NoteCodeBlockSerializer noteCodeBlockSerializer) {
        return NoteCodeBlock.builder()
                .code(noteCodeBlockSerializer.getData().getCode())
                .build();
    }

    protected NoteListBlock convertNoteListBlockSerializerToNoteBlock
            (NoteListBlockSerializer noteListBlockSerializer) {

        NoteListBlock listBlock = NoteListBlock.builder()
                .style(noteListBlockSerializer.getData().getStyle())
                .build();

        List<NoteListBlockItem> items = noteListBlockSerializer.getData()
                .getItems()
                .stream()
                .map(this::convertStringToNoteListBlockItem)
                .collect(Collectors.toList());
        ;

        items.forEach(item -> item.setList(listBlock));
        listBlock.setItems(items);
        return listBlock;
    }

    protected NoteListBlockItem convertStringToNoteListBlockItem(String string) {
        return NoteListBlockItem.builder()
                .text(string)
                .build();
    }

    protected NoteChecklistBlock convertNoteChecklistBlockSerializerToNoteBlock
            (NoteChecklistBlockSerializer noteChecklistBlockSerializer) {

        NoteChecklistBlock checklistBlock = NoteChecklistBlock.builder()
                .build();

        List<NoteChecklistBlockItem> items = noteChecklistBlockSerializer.getData()
                .getItems()
                .stream()
                .map(this::convertNoteChecklistBlockItemSerializerToNoteChecklistBlockItem)
                .collect(Collectors.toList());
        ;

        items.forEach(item -> item.setList(checklistBlock));
        checklistBlock.setItems(items);
        return checklistBlock;
    }

    protected NoteChecklistBlockItem convertNoteChecklistBlockItemSerializerToNoteChecklistBlockItem
            (NoteChecklistBlockItemSerializer noteChecklistBlockItemSerializer) {
        return NoteChecklistBlockItem.builder()
                .checked(noteChecklistBlockItemSerializer.getChecked())
                .text(noteChecklistBlockItemSerializer.getText())
                .build();
    }

    protected NoteSerializer convertNoteToNoteSerializer(Note note) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        List<NoteBlockSerializer> blockSerializers = new ArrayList<>();

        for (NoteBlock block : note.getBlocks()) {
            String methodName = this.getConverterMethodName(block.getClass(), NoteBlockSerializer.class);
            Method converterMethod = this.getClass().getDeclaredMethod(methodName, block.getClass());

            NoteBlockSerializer blockSerializer = (NoteBlockSerializer) converterMethod.invoke(this, block);

            blockSerializers.add(blockSerializer);
        }

        NoteContentSerializer contentSerializer = NoteContentSerializer.builder()
                .version(note.getEditorVersion())
                .blocks(blockSerializers)
                .build();

        return NoteSerializer.builder()
                .title(note.getTitle())
                .content(contentSerializer)
                .id(note.getId())
                .build();
    }

    protected NoteParagraphBlockSerializer convertNoteParagraphBlockToNoteBlockSerializer(
            NoteParagraphBlock noteParagraphBlock
    ) {
        NoteParagraphBlockDataSerializer data = NoteParagraphBlockDataSerializer.builder()
                .text(noteParagraphBlock.getText())
                .build();

        return NoteParagraphBlockSerializer.builder()
                .data(data)
                .build();
    }

    protected NoteHeaderBlockSerializer convertNoteHeaderBlockToNoteBlockSerializer(
            NoteHeaderBlock noteHeaderBlock
    ) {
        NoteHeaderBlockDataSerializer data = NoteHeaderBlockDataSerializer.builder()
                .level(noteHeaderBlock.getLevel())
                .text(noteHeaderBlock.getText())
                .build();

        return NoteHeaderBlockSerializer.builder()
                .data(data)
                .build();
    }

    protected NoteQuoteBlockSerializer convertNoteQuoteBlockToNoteBlockSerializer(
        NoteQuoteBlock noteQuoteBlock
    ) {
        NoteQuoteBlockDataSerializer data = NoteQuoteBlockDataSerializer.builder()
                .alignment(noteQuoteBlock.getAlignment())
                .caption(noteQuoteBlock.getCaption())
                .text(noteQuoteBlock.getText())
                .build();

        return NoteQuoteBlockSerializer.builder()
                .data(data)
                .build();
    }

    protected NoteCodeBlockSerializer convertNoteCodeBlockToNoteBlockSerializer(
            NoteCodeBlock noteCodeBlock
    ) {
        NoteCodeBlockDataSerializer data = NoteCodeBlockDataSerializer.builder()
                .code(noteCodeBlock.getCode())
                .build();

        return NoteCodeBlockSerializer.builder()
                .data(data)
                .build();
    }

    protected NoteListBlockSerializer convertNoteListBlockToNoteBlockSerializer(
            NoteListBlock noteListBlock
    ) {
        List<String> items = noteListBlock.getItems().stream()
                .map(this::convertNoteListBlockItemToString)
                .toList();

        NoteListBlockDataSerializer data = NoteListBlockDataSerializer.builder()
                .style(noteListBlock.getStyle())
                .items(items)
                .build();

        return NoteListBlockSerializer.builder()
                .data(data)
                .build();
    }

    protected String convertNoteListBlockItemToString(NoteListBlockItem noteListBlockItem) {
        return noteListBlockItem.getText();
    }

    protected NoteChecklistBlockSerializer convertNoteChecklistBlockToNoteBlockSerializer(
            NoteChecklistBlock noteChecklistBlock
    ) {
        List<NoteChecklistBlockItemSerializer> items = noteChecklistBlock.getItems()
                .stream()
                .map(this::convertNoteChecklistBlockItemToNoteChecklistItemSerializer)
                .toList();

        NoteChecklistBlockDataSerializer data = NoteChecklistBlockDataSerializer.builder()
                .items(items)
                .build();

        return NoteChecklistBlockSerializer.builder()
                .data(data)
                .build();
    }

    protected NoteChecklistBlockItemSerializer convertNoteChecklistBlockItemToNoteChecklistItemSerializer(
            NoteChecklistBlockItem noteChecklistBlockItem
    ) {
        return NoteChecklistBlockItemSerializer.builder()
                .checked(noteChecklistBlockItem.getChecked())
                .text(noteChecklistBlockItem.getText())
                .build();
    }
}
