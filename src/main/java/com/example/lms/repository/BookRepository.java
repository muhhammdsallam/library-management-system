package com.example.lms.repository;

import com.example.lms.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByIsbn(String isbn);

    @Query("SELECT CASE WHEN b.quantity > 0 THEN true ELSE false END FROM Book b WHERE b.id = :bookId")
    boolean isBookAvailable(Long bookId);
}