package book.lib.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import book.lib.api.BookGenreEnum;
import book.lib.dto.BookCredentialsDto;
import book.lib.entity.BookCredential;

public interface BookCredentialRepository extends JpaRepository<BookCredential, Integer> {
//	List<BookCredential> findByBookGenre(BookGenreEnum bookGenre);
//	
//	List<BookCredential> findByBookGenreAndPagesAmountBetween(BookGenreEnum genre, int min, int max);
//	
//	List<BookCredential> findByBookGenreAndPagesAmountBetweenOrderByPagesAmountAsc(
//		    BookGenreEnum genre,
//		    int min,
//		    int max
//		);
    @Query(value = "SELECT * FROM book_library b left join book_credential c WHERE c.book_genre = :genre and b.id_book=c.id_book" , nativeQuery = true)
    List<BookCredentialsDto> findByBookGenre(@Param("genre") String genre);

//    @Query(value = "SELECT * FROM book_credential WHERE book_genre = :genre AND pages_amount BETWEEN :min AND :max ORDER BY pages_amount ASC", nativeQuery = true)
//    List<BookCredential> findByGenreAndPagesRangeOrdered(@Param("genre") String genre, @Param("min") int min, @Param("max") int max);


}
