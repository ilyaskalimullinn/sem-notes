package org.example.ilyaskalimullinn.notes.data.entity.note.blocks.subblocks;

import lombok.*;
import org.example.ilyaskalimullinn.notes.data.entity.note.blocks.NoteListBlock;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "note_list_block_item")
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NoteListBlockItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // todo check if eager is necessary
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(referencedColumnName = "id", nullable = false)
    private NoteListBlock list;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String text;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        NoteListBlockItem that = (NoteListBlockItem) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
