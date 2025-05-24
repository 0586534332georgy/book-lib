package book.lib.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import book.lib.dto.BookDto;
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
	List<BookDto> getFreeBooks();
	
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
	
	@Modifying
	@Transactional
	@Query(value="""
			UPDATE book_status
			SET reserved_status = false,
			    reserved_date = NULL
			WHERE
			 	reserved_status = true
			 	AND id_book = (
					SELECT b.id_book 
					FROM book_library b
					where b.bookname = :title
					LIMIT 1
				)
				""", nativeQuery = true)	
	int setBookFree(@Param("title") String title);
	
	
	@Modifying
	@Transactional
	@Query(value="""
			UPDATE book_status
			SET reserved_status = true,
			    reserved_date = CURRENT_DATE
			WHERE 
				reserved_status = false    
				AND id_book = (
					SELECT b.id_book 
					FROM book_library b
					where b.bookname = :title				
					LIMIT 1
				)
				""", nativeQuery = true)	
	int setBookReserved(@Param("title") String title);

}
