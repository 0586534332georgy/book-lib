package book.lib.repo;

import book.lib.api.BookGenreEnum;
import book.lib.dto.BookCredentialsDto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;


public interface BookCredentialsDtoRepo extends JpaRepository<BookCredentialsDto, Integer> {
    @Query(value = "SELECT " +
            "c.id_book AS id, " +
            "b.author_surname AS authorSurname, " +
            "b.author_name AS authorName, " +
            "b.bookname AS title, " +
            "c.book_genre AS bookGenre, " +
            "c.pages_amount AS pagesAmount " +
            "FROM book_library b " +
            "JOIN book_credential c ON b.id_book = c.id_book " +
            "WHERE c.book_genre = CAST(:genre AS book_genre_enum)",
            nativeQuery = true)
    List<BookCredentialsDto> findByBookGenre(@Param("genre") BookGenreEnum genre);

    @Query(value = "SELECT " +
            "c.id_book AS id, " +
            "b.author_surname AS authorSurname, " +
            "b.author_name AS authorName, " +
            "b.bookname AS title, " +
            "c.book_genre AS bookGenre, " +
            "c.pages_amount AS pagesAmount " +
            "FROM book_library b " +
            "JOIN book_credential c ON b.id_book = c.id_book " +
            "WHERE c.book_genre = CAST(:genre AS book_genre_enum) " +
            "AND c.pages_amount BETWEEN :min AND :max",
            nativeQuery = true)
    List<BookCredentialsDto> findByBookGenreAndPagesAmountBetween(@Param("genre") BookGenreEnum genre,
                                                                  @Param("min") int min,
                                                                  @Param("max") int max);

    @Query(value = "SELECT " +
            "c.id_book AS id, " +
            "b.author_surname AS authorSurname, " +
            "b.author_name AS authorName, " +
            "b.bookname AS title, " +
            "c.book_genre AS bookGenre, " +
            "c.pages_amount AS pagesAmount " +
            "FROM book_library b " +
            "JOIN book_credential c ON b.id_book = c.id_book " +
            "WHERE c.book_genre = CAST(:genre AS book_genre_enum) " +
            "AND c.pages_amount BETWEEN :min AND :max " +
            "ORDER BY c.pages_amount ASC",
            nativeQuery = true)
    List<BookCredentialsDto> findByBookGenreAndPagesAmountBetweenOrderByPagesAmountAsc(@Param("genre") BookGenreEnum genre,
                                                                                       @Param("min") int min,
                                                                                       @Param("max") int max);
}
