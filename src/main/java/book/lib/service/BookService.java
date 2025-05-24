package book.lib.service;

import java.util.List;

import book.lib.api.BookGenreEnum;
import book.lib.dto.BookCredentialsDto;
import book.lib.dto.BookDto;
import book.lib.dto.BookReservedDto;
import book.lib.entity.Book;


public interface BookService {

	public List<Book> getAll();

	public List<BookDto> getAll2();

	public List<BookCredentialsDto> getBooksByGenre(BookGenreEnum bookGenre);

	public List<BookCredentialsDto> getBooksByGenreAndPages(BookGenreEnum genre, int min, int max);

	public List<BookCredentialsDto> getBooksByGenreAndPagesOrdered(BookGenreEnum genre, int min, int max);

	public List<BookDto> getFreeBooks();

	public List<BookReservedDto> getReservedBooks();

	public int setBookFree(String title);

	public int setBookReserved(String title);

}
