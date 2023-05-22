package org.example.ilyaskalimullinn.notes.data.entity.note.blocks;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@ToString(callSuper = true)
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@DiscriminatorValue("code")
@Table(name="note_code_block")
public class NoteCodeBlock extends NoteBlock {
    @Column(nullable = false, columnDefinition = "TEXT")
    private String code;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        NoteParagraphBlock that = (NoteParagraphBlock) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }
}
