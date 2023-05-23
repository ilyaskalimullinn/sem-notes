package org.example.ilyaskalimullinn.notes.data.repository;

import org.example.ilyaskalimullinn.notes.data.entity.User;
import org.example.ilyaskalimullinn.notes.data.entity.note.Note;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface NoteRepository extends CrudRepository<Note, Long> {
    Note findByIdAndAuthor(Long id, User author);

    @Transactional
    long deleteByIdAndAuthor(Long id, User author);

}
