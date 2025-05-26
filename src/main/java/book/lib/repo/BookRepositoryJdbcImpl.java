package book.lib.repo;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import book.lib.dto.BookCredentialsDto;
import book.lib.dto.BookDto;
import book.lib.dto.BookReservedDto;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class BookRepositoryJdbcImpl implements BookRepositoryJdbc {

    private final JdbcTemplate jdbc;
    
    @Override
    public List<BookDto> findAllBooks() {
        return jdbc.query("""        		
        	SELECT 
	            b.bookname AS title,
	            b.author_surname AS authorSurname,
	            b.author_name AS authorName,
	            c.book_genre AS bookGenre
		    FROM book_library b
		    LEFT JOIN book_credential c ON b.id_book = c.id_book        		
        """, BeanPropertyRowMapper.newInstance(BookDto.class));
    }

    @Override
    public List<BookCredentialsDto> findByBookGenre(String genre) {
        return jdbc.query("""
            SELECT 
                c.id_book AS id, 
                b.author_surname AS authorSurname, 
                b.author_name AS authorName, 
                b.bookname AS title, 
                c.book_genre AS bookGenre, 
                c.pages_amount AS pagesAmount 
            FROM book_library b 
            JOIN book_credential c ON b.id_book = c.id_book 
            WHERE c.book_genre::text = ?
        """, BeanPropertyRowMapper.newInstance(BookCredentialsDto.class), genre);
    }

    @Override
    public List<BookCredentialsDto> findByBookGenreAndPagesAmountBetween(String genre, int min, int max) {
        return jdbc.query("""
            SELECT
                c.id_book AS id,
                b.author_surname AS authorSurname,
                b.author_name AS authorName,
                b.bookname AS title,
                c.book_genre AS bookGenre,
                c.pages_amount AS pagesAmount
            FROM book_library b
            JOIN book_credential c ON b.id_book = c.id_book
            WHERE c.book_genre::text = ?
              AND c.pages_amount BETWEEN ? AND ?
        """, BeanPropertyRowMapper.newInstance(BookCredentialsDto.class), genre, min, max);
    }
    
    @Override
    public List<BookCredentialsDto> findByBookGenreAndPagesAmountBetweenOrderByPagesAmountAsc(String genre, int min, int max) {
        return jdbc.query("""
            SELECT
                c.id_book AS id,
                b.author_surname AS authorSurname,
                b.author_name AS authorName,
                b.bookname AS title,
                c.book_genre AS bookGenre,
                c.pages_amount AS pagesAmount
            FROM book_library b
            JOIN book_credential c ON b.id_book = c.id_book
            WHERE c.book_genre::text = ?
              AND c.pages_amount BETWEEN ? AND ?
            ORDER BY c.pages_amount ASC
        """, BeanPropertyRowMapper.newInstance(BookCredentialsDto.class), genre, min, max);
    }

    @Override
    public List<BookDto> getFreeBooks() {
        return jdbc.query("""
            SELECT
                b.bookname AS title, 
                b.author_surname AS authorSurname, 
                b.author_name AS authorName,
                c.book_genre AS bookGenre
            FROM book_library b
            LEFT JOIN book_credential c ON b.id_book = c.id_book
            LEFT JOIN book_status s ON b.id_book = s.id_book
            WHERE s.reserved_status = false
        """, BeanPropertyRowMapper.newInstance(BookDto.class));
    }

    @Override
    public List<BookReservedDto> getReservedBooks() {
        return jdbc.query("""
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
        """, BeanPropertyRowMapper.newInstance(BookReservedDto.class));
    }

    @Override
    public int setBookFree(String title) {
        return jdbc.update("""
            UPDATE book_status
            SET reserved_status = false,
                reserved_date = NULL
            WHERE reserved_status = true
              AND id_book = (
                  SELECT b.id_book
                  FROM book_library b
                  WHERE b.bookname = ?
                  LIMIT 1
              )
        """, title);
    }

    @Override
    public int setBookReserved(String title) {
        return jdbc.update("""
            UPDATE book_status
            SET reserved_status = true,
                reserved_date = CURRENT_DATE
            WHERE reserved_status = false
              AND id_book = (
                  SELECT b.id_book
                  FROM book_library b
                  WHERE b.bookname = ?
                  LIMIT 1
              )
        """, title);
    }
}
