package book.lib.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import book.lib.entity.Book;
import book.lib.repo.BookRepository;

@RestController
@RequestMapping("/api")
public class BookController {

	@Autowired
	private BookRepository bookRepository;

	@GetMapping("/books")
	public List<Book> getAll() {
		return bookRepository.findAll();
	}
}
