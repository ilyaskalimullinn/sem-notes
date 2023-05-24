package org.example.ilyaskalimullinn.notes.data.entity.note;

import lombok.*;
import org.example.ilyaskalimullinn.notes.data.entity.User;
import org.example.ilyaskalimullinn.notes.data.entity.note.blocks.NoteBlock;
import org.hibernate.Hibernate;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;


@Entity
@Table(name = "note")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name="author_id", referencedColumnName="id")
    private User author;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String editorVersion;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "note", cascade = CascadeType.ALL)
    @OrderColumn(name = "order_num")
    @ToString.Exclude
    private List<NoteBlock> blocks = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "note_category",
        joinColumns = @JoinColumn(name = "note", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "category", referencedColumnName = "id")
    )
    @ToString.Exclude
    private List<Category> categories = new ArrayList<>();

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
    private Date createdAt;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Note note = (Note) o;
        return getId() != null && Objects.equals(getId(), note.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
