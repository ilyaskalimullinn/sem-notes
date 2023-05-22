## Entities:

Note {
    id
    author_id (author),
    created_at,
    updated_at,
    blocks: List<NoteBlock>
}

NoteBlockType {
    id
    name: String
}

### For regular blocks

NoteBlock {
    type_id
    note_id
    parent: nullable, fk to self
    text: nullable,
    text_attribute: nullable,
    data: text, json
}

### For Items:

NoteChecklistItemBlock

NoteListItemBlock


## Serializers:

### Blocks:

Note{Block_Type}Serializer {
    type: String
    data: Note{Block_Type}Data,
}

### ListItems:
NoteChecklistItemSerializer {
    text: String,
    checked: boolean
}

### Note{Block_Type}Data:

NoteParagraphData {
    text: String
}

NoteHeaderData {
    text: String
    level: int
}

NoteQuoteData {
    text: String
    caption: String
    alignment : String
}

NoteImageData {
    url: String
    caption: String
    withBorder: boolean
    withBackground: boolean
    stretched: boolean
}

NoteChecklistData {
    items: List<NoteChecklistItemSerializer>
}

NoteCodeData {
    code: String
}

NoteListData {
    type: String (ordered or unordered)
    items: List<String>
}
