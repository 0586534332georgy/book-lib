package book.lib.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import book.lib.api.BookGenreEnum;
import book.lib.entity.BookCredential;

public interface BookCredentialRepository extends JpaRepository<BookCredential, Integer> {
	List<BookCredential> findByBookGenre(BookGenreEnum bookGenre);
	
	List<BookCredential> findByBookGenreAndPagesAmountBetween(BookGenreEnum genre, int min, int max);

}
