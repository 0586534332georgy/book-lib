package book.lib.service;

import java.util.List;

import org.springframework.stereotype.Service;

import book.lib.api.BookGenreEnum;
import book.lib.dto.BookCredentialsDto;
import book.lib.dto.BookDto;
import book.lib.dto.BookReservedDto;
import book.lib.entity.Book;
import book.lib.repo.BookRepositoryJdbc;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
	
	final private BookRepositoryJdbc bookRepoJdbc;

	@Override
	public List<Book> getAll() {
//		return bookRepoJdbc.findAll();
		throw new UnsupportedOperationException("This method is not supported in JdbcTemplate version.");
	}

	@Override
	public List<BookDto> getAll2() {
		return bookRepoJdbc.findAllBooks();
	}

	@Override
	public List<BookCredentialsDto> getBooksByGenre(BookGenreEnum bookGenre) {
		String dbGenre = bookGenre.name();
		return bookRepoJdbc.findByBookGenre(dbGenre);
	}

	@Override
	public List<BookCredentialsDto> getBooksByGenreAndPages(BookGenreEnum genre, int min, int max) {
		String dbGenre = genre.name();
		return bookRepoJdbc.findByBookGenreAndPagesAmountBetween(dbGenre, min, max);
	}

	@Override
	public List<BookCredentialsDto> getBooksByGenreAndPagesOrdered(BookGenreEnum genre, int min, int max) {
		String dbGenre = genre.name();
		return bookRepoJdbc.findByBookGenreAndPagesAmountBetweenOrderByPagesAmountAsc(dbGenre, min, max);
	}

	@Override
	public List<BookDto> getFreeBooks() {
		return bookRepoJdbc.getFreeBooks();
	}

	@Override
	public List<BookReservedDto> getReservedBooks() {
		return bookRepoJdbc.getReservedBooks();
	}

	@Override
	public int setBookFree(String title) {
		return bookRepoJdbc.setBookFree(title);
	}
		
	@Override
	public int setBookReserved(String title) {
		return bookRepoJdbc.setBookReserved(title);
	}

}
