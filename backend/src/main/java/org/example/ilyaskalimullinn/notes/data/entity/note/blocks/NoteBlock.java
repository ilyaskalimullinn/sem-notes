package org.example.ilyaskalimullinn.notes.data.entity.note.blocks;

import lombok.*;
import org.example.ilyaskalimullinn.notes.data.entity.note.Note;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "note_block")
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name="type")
@Getter
@Setter
@ToString
public abstract class NoteBlock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(referencedColumnName = "id", nullable = false)
    protected Note note;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        NoteBlock noteBlock = (NoteBlock) o;
        return getId() != null && Objects.equals(getId(), noteBlock.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
