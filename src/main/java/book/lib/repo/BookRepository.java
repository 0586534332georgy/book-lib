package book.lib.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import book.lib.entity.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {

}
