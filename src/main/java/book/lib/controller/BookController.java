package book.lib.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import book.lib.api.BookGenreEnum;
import book.lib.entity.Book;
import book.lib.entity.BookCredential;
import book.lib.repo.BookCredentialRepository;
import book.lib.repo.BookRepository;

@RestController
@RequestMapping("/api")
public class BookController {

	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
    private BookCredentialRepository bookCredentialRepository;

	@GetMapping("/books")
	public List<Book> getAll() {
		return bookRepository.findAll();
	}
	
	
//	Derived Query Method
//	http://localhost:8080/api/books-by-genre?bookGenre=Fantasy
    @GetMapping("/books-by-genre")
    public List<BookCredential> getBooksByGenre(@RequestParam BookGenreEnum bookGenre) {
        return bookCredentialRepository.findByBookGenre(bookGenre);
    }
    
    
//  http://localhost:8080/api/books2-by-genre-and-pages?genre=Horror&min=200&max=400
    @GetMapping("/books2-by-genre-and-pages")
    public List<BookCredential> getBooksByGenreAndPages (
    		@RequestParam BookGenreEnum genre,
    		@RequestParam int min,
    		@RequestParam int max
    		) 
    	{
    	return bookCredentialRepository.findByBookGenreAndPagesAmountBetween(genre, min, max);
    }
}
