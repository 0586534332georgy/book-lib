package book.lib.service;

import java.util.List;

import org.springframework.stereotype.Service;

import book.lib.api.BookGenreEnum;
import book.lib.dto.BookCredentialsDto;
import book.lib.dto.BookDto;
import book.lib.dto.BookReservedDto;
import book.lib.entity.Book;
import book.lib.repo.BookRepository;
import book.lib.repo.StatusRepository;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

	final private BookRepository bookRepo;

	final private StatusRepository statusRepo;

	public List<Book> getAll() {
		return bookRepo.findAll();
	}

	public List<BookDto> getAll2() {
		return bookRepo.findAllBooks();
	}

	public List<BookCredentialsDto> getBooksByGenre(BookGenreEnum bookGenre) {
//		String dbGenre = bookGenre.name();
		return bookRepo.findByBookGenre(bookGenre);
	}

	public List<BookCredentialsDto> getBooksByGenreAndPages(BookGenreEnum genre, int min, int max) {
		String dbGenre = genre.name();
		return bookRepo.findByBookGenreAndPagesAmountBetween(dbGenre, min, max);
	}

	public List<BookCredentialsDto> getBooksByGenreAndPagesOrdered(BookGenreEnum genre, int min, int max) {
		String dbGenre = genre.name();
		return bookRepo.findByBookGenreAndPagesAmountBetweenOrderByPagesAmountAsc(dbGenre, min, max);
	}

	public List<BookDto> getFreeBooks() {
		return statusRepo.getFreeBooks();
	}

	public List<BookReservedDto> getReservedBooks() {
		return statusRepo.getReservedBooks();
	}

	public int setBookFree(String title) {
		return statusRepo.setBookFree(title);
	}

	public int setBookReserved(String title) {
		return statusRepo.setBookReserved(title);
	}

}
