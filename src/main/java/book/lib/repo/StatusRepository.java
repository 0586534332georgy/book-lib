package book.lib.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import book.lib.dto.BookFreeDto;
import book.lib.dto.BookReservedDto;
import book.lib.entity.BookStatus;

public interface StatusRepository extends JpaRepository<BookStatus, Integer> {
	
	@Query(value = """
			SELECT
			b.bookname AS title, 
			b.author_surname AS authorSurname, 
            b.author_name AS authorName,
            c.book_genre AS bookGenre			
			FROM book_library b
			LEFT JOIN book_credential c ON b.id_book = c.id_book
			LEFT JOIN book_status s ON b.id_book = s.id_book
			WHERE s.reserved_status = false			
			""", nativeQuery = true)
	List<BookFreeDto> getFreeBooks();
	
	@Query(value = """
			SELECT
			b.bookname AS title, 
			b.author_surname AS authorSurname, 
            b.author_name AS authorName,
            c.book_genre AS bookGenre,
            s.reserved_date AS reservedDate			
			FROM book_library b
			LEFT JOIN book_credential c ON b.id_book = c.id_book
			LEFT JOIN book_status s ON b.id_book = s.id_book
			WHERE s.reserved_status = true			
			""", nativeQuery = true)
	List<BookReservedDto> getReservedBooks();

}
