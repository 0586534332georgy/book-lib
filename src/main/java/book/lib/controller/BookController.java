package book.lib.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import book.lib.api.BookGenreEnum;
import book.lib.dto.BookCredentialsDto;
import book.lib.dto.BookDto;
import book.lib.dto.BookReservedDto;
import book.lib.entity.Book;
import book.lib.repo.BookRepository;
import book.lib.repo.StatusRepository;

@RestController
@RequestMapping("/api")
public class BookController {

	@Autowired
	private BookRepository bookRepo;
	
	@Autowired
	private StatusRepository statusRepo;

	@GetMapping("/books")
	public List<Book> getAll() {
		return bookRepo.findAll();
	}
	
	@GetMapping("/books-dto")
	public List<BookDto> getAll2() {
		return bookRepo.findAllBooks();
	}
	

//	http://localhost:8080/api/books-by-genre?bookGenre=Fantasy
    @GetMapping("/books-by-genre")
    public List<BookCredentialsDto> getBooksByGenre(@RequestParam BookGenreEnum bookGenre) {
    	String dbGenre = bookGenre.name();
        return bookRepo.findByBookGenre(dbGenre);
    }

    
//  http://localhost:8080/api/books-by-genre-and-pages?genre=Horror&min=200&max=400
    @GetMapping("/books-by-genre-and-pages")
    public List<BookCredentialsDto> getBooksByGenreAndPages (
    		@RequestParam BookGenreEnum genre,
    		@RequestParam int min,
    		@RequestParam int max
    		) 
    	{
    	String dbGenre = genre.name();
    	return bookRepo.findByBookGenreAndPagesAmountBetween(dbGenre, min, max);
    }
    
//  http://localhost:8080/api/books-by-genre-and-pages-ordered?genre=Horror&min=200&max=400    
    @GetMapping("/books-by-genre-and-pages-ordered")
    public List<BookCredentialsDto> getBooksByGenreAndPagesOrdered (
    		@RequestParam BookGenreEnum genre,
    		@RequestParam int min,
    		@RequestParam int max
    		) 
    	{
    	String dbGenre = genre.name();
    	return bookRepo.findByBookGenreAndPagesAmountBetweenOrderByPagesAmountAsc(dbGenre, min, max);
    }
    
    @GetMapping("/books-free")
    public List<BookDto> getFreeBooks() {
    	return statusRepo.getFreeBooks();
    }
    
    @GetMapping("/books-reserved")
    public List<BookReservedDto> getReservedBooks() {
    	return statusRepo.getReservedBooks();
    }
    
    
}
