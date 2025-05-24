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
            b.title AS title,
            b.authorSurname AS authorSurname,
            b.authorName AS authorName,
            c.bookGenre AS bookGenre
        FROM Book b
        LEFT JOIN BookCredential c ON b.id = c.book.id
        LEFT JOIN BookStatus s ON b.id = s.id
        WHERE s.reservedStatus = false		
			""")
	List<BookDto> getFreeBooks();
	
	@Query(value = """
        SELECT 
            b.title AS title,
            b.authorSurname AS authorSurname,
            b.authorName AS authorName,
            c.bookGenre AS bookGenre,
            s.reservedDate AS reservedDate
        FROM Book b
        LEFT JOIN BookCredential c ON b.id = c.book.id
        LEFT JOIN BookStatus s ON b.id = s.id
        WHERE s.reservedStatus = true			
			""")
	List<BookReservedDto> getReservedBooks();
	
	@Modifying
	@Transactional
	@Query(value="""
        UPDATE BookStatus s
        SET s.reservedStatus = false,
            s.reservedDate = null
        WHERE s.reservedStatus = true
        AND s.id = (
            SELECT b.id FROM Book b
            WHERE b.title = :title
        )
				""")	
	int setBookFree(@Param("title") String title);
	
	
	@Modifying
	@Transactional
	@Query(value="""
        UPDATE BookStatus s
        SET s.reservedStatus = true,
            s.reservedDate = CURRENT_DATE
        WHERE s.reservedStatus = false
        AND s.id = (
            SELECT b.id FROM Book b
            WHERE b.title = :title
        )
				""")	
	int setBookReserved(@Param("title") String title);

}
