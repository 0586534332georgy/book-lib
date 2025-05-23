package book.lib.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import book.lib.api.BookGenreEnum;
import book.lib.dto.BookCredentialsDto;
import book.lib.entity.Book;
import book.lib.repo.BookRepository;

@RestController
@RequestMapping("/api")
public class BookController {

	@Autowired
	private BookRepository bookRepo;
	
//	@Autowired
//    private BookCredentialsDtoRepo bookDtoRepo;

	@GetMapping("/books")
	public List<Book> getAll() {
		return bookRepo.findAll();
	}
	
	
//	Derived Query Method
//	http://localhost:8080/api/books-by-genre?bookGenre=Fantasy
    @GetMapping("/books-by-genre")
    public List<BookCredentialsDto> getBooksByGenre(@RequestParam BookGenreEnum bookGenre) {
        return bookRepo.findByBookGenre(bookGenre);
    }
    
    
//  http://localhost:8080/api/books-by-genre-and-pages?genre=Horror&min=200&max=400
    @GetMapping("/books-by-genre-and-pages")
    public List<BookCredentialsDto> getBooksByGenreAndPages (
    		@RequestParam BookGenreEnum genre,
    		@RequestParam int min,
    		@RequestParam int max
    		) 
    	{
    	return bookRepo.findByBookGenreAndPagesAmountBetween(genre, min, max);
    }
    
//  http://localhost:8080/api/books-by-genre-and-pages-ordered?genre=Horror&min=200&max=400    
    @GetMapping("/books-by-genre-and-pages-ordered")
    public List<BookCredentialsDto> getBooksByGenreAndPagesOrdered (
    		@RequestParam BookGenreEnum genre,
    		@RequestParam int min,
    		@RequestParam int max
    		) 
    	{
    	return bookRepo.findByBookGenreAndPagesAmountBetweenOrderByPagesAmountAsc(genre, min, max);
    }
    
    
}
