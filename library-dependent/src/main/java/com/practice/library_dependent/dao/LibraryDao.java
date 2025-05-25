package com.practice.library_dependent.dao;

import com.practice.library_dependent.entity.Library;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LibraryDao extends JpaRepository<Library, Integer> {

    Optional<Library> findByUsernameAndBookId(String username, Integer bookId);

    int countByUsername(String username);
}
