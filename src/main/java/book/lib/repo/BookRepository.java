package book.lib.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import book.lib.dto.BookCredentialsDto;
import book.lib.dto.BookDto;
import book.lib.entity.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {
	
    @Query(value = "SELECT * FROM book_library", nativeQuery = true)
    List<BookDto> findAllBooks();

    @Query(value = "SELECT * FROM book_library WHERE id_book = :id", nativeQuery = true)
    Book findBookById(@Param("id") int id);
    
    @Query(value = """    		
    		SELECT 
            c.id_book AS id, 
            b.author_surname AS authorSurname, 
            b.author_name AS authorName, 
            b.bookname AS title, 
            c.book_genre AS bookGenre, 
            c.pages_amount AS pagesAmount 
            FROM book_library b 
            JOIN book_credential c ON b.id_book = c.id_book 
            WHERE c.book_genre:: text = :genre
            """,
            nativeQuery = true)
    List<BookCredentialsDto> findByBookGenre(@Param("genre") String genre);

    @Query(value = """
    		SELECT
            c.id_book AS id,
            b.author_surname AS authorSurname,
            b.author_name AS authorName,
            b.bookname AS title,
            c.book_genre AS bookGenre,
            c.pages_amount AS pagesAmount
            FROM book_library b
            JOIN book_credential c ON b.id_book = c.id_book
            WHERE c.book_genre:: text = :genre
            AND c.pages_amount BETWEEN :min AND :max
            """,
            nativeQuery = true)
    List<BookCredentialsDto> findByBookGenreAndPagesAmountBetween(@Param("genre") String genre,
                                                                  @Param("min") int min,
                                                                  @Param("max") int max);

    @Query(value = """    		
    		SELECT 
            c.id_book AS id, 
            b.author_surname AS authorSurname, 
            b.author_name AS authorName, 
            b.bookname AS title, 
            c.book_genre AS bookGenre, 
            c.pages_amount AS pagesAmount 
            FROM book_library b 
            JOIN book_credential c ON b.id_book = c.id_book 
            WHERE c.book_genre:: text = :genre 
            AND c.pages_amount BETWEEN :min AND :max 
            ORDER BY c.pages_amount ASC            
    		""", nativeQuery = true)
    List<BookCredentialsDto> findByBookGenreAndPagesAmountBetweenOrderByPagesAmountAsc(@Param("genre") String genre,
                                                                                       @Param("min") int min,
                                                                                       @Param("max") int max);

}
