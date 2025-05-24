package book.lib.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import book.lib.api.BookGenreEnum;
import book.lib.dto.BookCredentialsDto;
import book.lib.dto.BookDto;
import book.lib.dto.BookReservedDto;
import book.lib.entity.Book;
import book.lib.service.BookService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class BookController {

	private final BookService bookService;

	@GetMapping("/books")
	public List<Book> getAll() {
		return bookService.getAll();
	}

	@GetMapping("/books-dto")
	public List<BookDto> getAll2() {
		return bookService.getAll2();
	}

//	http://localhost:8080/api/books-by-genre?bookGenre=Fantasy
	@GetMapping("/books-by-genre")
	public List<BookCredentialsDto> getBooksByGenre(@RequestParam BookGenreEnum bookGenre) {
		return bookService.getBooksByGenre(bookGenre);
	}

//  http://localhost:8080/api/books-by-genre-and-pages?genre=Horror&min=200&max=400
	@GetMapping("/books-by-genre-and-pages")
	public List<BookCredentialsDto> getBooksByGenreAndPages(@RequestParam BookGenreEnum genre, @RequestParam int min,
			@RequestParam int max) {
		return bookService.getBooksByGenreAndPages(genre, min, max);
	}

//  http://localhost:8080/api/books-by-genre-and-pages-ordered?genre=Horror&min=200&max=400    
	@GetMapping("/books-by-genre-and-pages-ordered")
	public List<BookCredentialsDto> getBooksByGenreAndPagesOrdered(@RequestParam BookGenreEnum genre,
			@RequestParam int min, @RequestParam int max) {
		return bookService.getBooksByGenreAndPagesOrdered(genre, min, max);
	}

	@GetMapping("/books-free")
	public List<BookDto> getFreeBooks() {
		return bookService.getFreeBooks();
	}

	@GetMapping("/books-reserved")
	public List<BookReservedDto> getReservedBooks() {
		return bookService.getReservedBooks();
	}

	@PutMapping("/free-book")
	public int setBookFree(@RequestParam String title) {
		return bookService.setBookFree(title);
	}

	@PutMapping("/reserve-book")
	public int setBookReserved(@RequestParam String title) {
		return bookService.setBookReserved(title);
	}

}
