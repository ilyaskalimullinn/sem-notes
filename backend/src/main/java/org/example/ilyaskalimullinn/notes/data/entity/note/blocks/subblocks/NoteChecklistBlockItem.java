package org.example.ilyaskalimullinn.notes.data.entity.note.blocks.subblocks;

import lombok.*;
import org.example.ilyaskalimullinn.notes.data.entity.note.blocks.NoteChecklistBlock;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "note_checklist_block_item")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NoteChecklistBlockItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // todo check if eager is necessary
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(referencedColumnName = "id", nullable = false)
    private NoteChecklistBlock list;

    @Column(nullable = false)
    private String text;

    @Column(nullable = false)
    private Boolean checked = false;

    @Column(nullable = false, name = "order_num")
    private Integer order;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        NoteChecklistBlockItem that = (NoteChecklistBlockItem) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
