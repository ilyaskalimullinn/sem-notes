package org.example.ilyaskalimullinn.notes.data.entity.note.blocks;

import lombok.*;
import org.example.ilyaskalimullinn.notes.data.entity.note.blocks.subblocks.NoteChecklistBlockItem;
import org.example.ilyaskalimullinn.notes.data.entity.note.blocks.subblocks.NoteListBlockItem;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@ToString(callSuper = true)
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@DiscriminatorValue("checklist")
@Table(name="note_checklist_block")
public class NoteChecklistBlock extends NoteBlock {
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "list", cascade = CascadeType.ALL)
    @ToString.Exclude
    @OrderColumn(name = "order_num")
    private List<NoteChecklistBlockItem> items = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        NoteParagraphBlock that = (NoteParagraphBlock) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }
}
