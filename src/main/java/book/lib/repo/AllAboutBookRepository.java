package book.lib.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import book.lib.api.BookGenreEnum;
import book.lib.entity.AllAboutBook;

public interface AllAboutBookRepository extends JpaRepository<AllAboutBook, Integer> {
	
    List<AllAboutBook> findByBookGenre(BookGenreEnum genre);

    List<AllAboutBook> findByPagesAmountBetween(int min, int max);
    
    List<AllAboutBook> findByBookGenreAndPagesAmountBetween(BookGenreEnum genre, int min, int max);

    List<AllAboutBook> findByBookGenreAndPagesAmountBetweenOrderByPagesAmountAsc(BookGenreEnum genre, int min, int max);


}
