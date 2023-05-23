package org.example.ilyaskalimullinn.notes.data.repository;

import org.example.ilyaskalimullinn.notes.data.entity.User;
import org.example.ilyaskalimullinn.notes.data.entity.note.Note;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface NoteRepository extends CrudRepository<Note, Long> {
    List<Note> findByAuthorOrderByUpdatedAtAsc(User author, Pageable pageable);
    Note findByIdAndAuthor(Long id, User author);

    @Transactional
    long deleteByIdAndAuthor(Long id, User author);

}
