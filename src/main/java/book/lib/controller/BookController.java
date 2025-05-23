package book.lib.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import book.lib.api.BookGenreEnum;
import book.lib.entity.AllAboutBook;
import book.lib.entity.Book;
import book.lib.entity.BookCredential;
import book.lib.repo.AllAboutBookRepository;
import book.lib.repo.BookRepository;

@RestController
@RequestMapping("/api")
public class BookController {

	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
    private AllAboutBookRepository allAboutBookRepo;

	@GetMapping("/books")
	public List<Book> getAll() {
		return bookRepository.findAll();
	}
	
	
//	Derived Query Method
//	http://localhost:8080/api/books-by-genre?bookGenre=Fantasy
    @GetMapping("/books-by-genre")
    public List<AllAboutBook> getBooksByGenre(@RequestParam BookGenreEnum bookGenre) {
        return allAboutBookRepo.findByBookGenre(bookGenre);
    }
    
    
//  http://localhost:8080/api/books-by-genre-and-pages?genre=Horror&min=200&max=400
    @GetMapping("/books-by-genre-and-pages")
    public List<AllAboutBook> getBooksByGenreAndPages (
    		@RequestParam BookGenreEnum genre,
    		@RequestParam int min,
    		@RequestParam int max
    		) 
    	{
    	return allAboutBookRepo.findByBookGenreAndPagesAmountBetween(genre, min, max);
    }
    
//  http://localhost:8080/api/books-by-genre-and-pages-ordered?genre=Horror&min=200&max=400    
    @GetMapping("/books-by-genre-and-pages-ordered")
    public List<AllAboutBook> getBooksByGenreAndPagesOrdered (
    		@RequestParam BookGenreEnum genre,
    		@RequestParam int min,
    		@RequestParam int max
    		) 
    	{
    	return allAboutBookRepo.findByBookGenreAndPagesAmountBetweenOrderByPagesAmountAsc(genre, min, max);
    }
    
    
}
