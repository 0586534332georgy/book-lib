package book.lib.repo;

import java.util.List;

import book.lib.dto.BookCredentialsDto;
import book.lib.dto.BookDto;
import book.lib.dto.BookReservedDto;

public interface BookRepositoryJdbc {
    List<BookDto> findAllBooks();
    List<BookCredentialsDto> findByBookGenre(String bookGenre);
    List<BookCredentialsDto> findByBookGenreAndPagesAmountBetween(String genre, int min, int max);
    List<BookCredentialsDto> findByBookGenreAndPagesAmountBetweenOrderByPagesAmountAsc(String genre, int min, int max);
    List<BookDto> getFreeBooks();
    List<BookReservedDto> getReservedBooks();
    int setBookFree(String title);
    int setBookReserved(String title);
}
