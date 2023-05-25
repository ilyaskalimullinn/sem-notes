package org.example.ilyaskalimullinn.notes.data.repository;

import org.example.ilyaskalimullinn.notes.data.entity.User;
import org.example.ilyaskalimullinn.notes.data.entity.note.Category;
import org.example.ilyaskalimullinn.notes.data.entity.note.Note;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;

@Repository
public class NoteRepositoryJpa {
    @PersistenceContext
    private EntityManager em;

    public List<Note> findByAuthorOrderByUpdatedAtDesc(User author, Pageable pageable) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Note> criteriaQuery = criteriaBuilder.createQuery(Note.class);
        Root<Note> noteRoot = criteriaQuery.from(Note.class);

        noteRoot.join("categories", JoinType.LEFT);
        noteRoot.fetch("categories", JoinType.LEFT);

        criteriaQuery.select(noteRoot)
                .where(criteriaBuilder.equal(noteRoot.get("author"), author))
                .orderBy(criteriaBuilder.desc(noteRoot.get("updatedAt")));

        return em.createQuery(criteriaQuery)
                .setFirstResult(pageable.getPageNumber() * pageable.getPageSize())
                .setMaxResults(pageable.getPageSize())
                .getResultList();

    }
}
