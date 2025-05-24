package book.lib.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import book.lib.dto.BookCredentialsDto;
import book.lib.dto.BookDto;
import book.lib.entity.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {

    @Query("""
        SELECT            
            b.authorSurname AS authorSurname,
            b.authorName AS authorName,
            b.title AS title,
            c.bookGenre AS bookGenre
        FROM Book b
        LEFT JOIN BookCredential c ON b.id = c.id
        """)
    List<BookDto> findAllBooks();

    @Query("SELECT b FROM Book b WHERE b.id = :id")
    Book findBookById(@Param("id") int id);

    @Query("""
        SELECT
            c.id AS id,
            b.authorSurname AS authorSurname,
            b.authorName AS authorName,
            b.title AS title,
            c.bookGenre AS bookGenre,
            c.pagesAmount AS pagesAmount
        FROM Book b
        JOIN BookCredential c ON b.id = c.id
        WHERE c.bookGenre = :genre
        """)
    List<BookCredentialsDto> findByBookGenre(@Param("genre") String genre);

    @Query("""
        SELECT 
            c.id AS id,
            b.authorSurname AS authorSurname,
            b.authorName AS authorName,
            b.title AS title,
            c.bookGenre AS bookGenre,
            c.pagesAmount AS pagesAmount
        FROM Book b
        JOIN BookCredential c ON b.id = c.id
        WHERE c.bookGenre = :genre
          AND c.pagesAmount BETWEEN :min AND :max
        """)
    List<BookCredentialsDto> findByBookGenreAndPagesAmountBetween(@Param("genre") String genre,
                                                                  @Param("min") int min,
                                                                  @Param("max") int max);

    @Query("""
        SELECT
            c.id AS id,
            b.authorSurname AS authorSurname,
            b.authorName AS authorName,
            b.title AS title,
            c.bookGenre AS bookGenre,
            c.pagesAmount AS pagesAmount
        FROM Book b
        JOIN BookCredential c ON b.id = c.id
        WHERE c.bookGenre = :genre
          AND c.pagesAmount BETWEEN :min AND :max
        ORDER BY c.pagesAmount ASC
        """)
    List<BookCredentialsDto> findByBookGenreAndPagesAmountBetweenOrderByPagesAmountAsc(@Param("genre") String genre,
                                                                                       @Param("min") int min,
                                                                                       @Param("max") int max);
}
