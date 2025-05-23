package book.lib.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import book.lib.entity.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {
	
    @Query(value = "SELECT * FROM book_library", nativeQuery = true)
    List<Book> findAllBooks();

    @Query(value = "SELECT * FROM book_library WHERE id_book = :id", nativeQuery = true)
    Book findBookById(@Param("id") int id);

}
