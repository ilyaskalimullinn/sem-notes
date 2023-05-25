package org.example.ilyaskalimullinn.notes.data.repository;

import org.example.ilyaskalimullinn.notes.data.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.validation.constraints.NotNull;

public interface UserRepository extends CrudRepository<User, Long> {
    @Query("SELECT user FROM User user left join fetch user.authorities WHERE user.username = :username")
    User findByUsername(String username);

    boolean existsByUsername(String username);
}
