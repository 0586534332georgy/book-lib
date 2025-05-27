package book.lib.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import book.lib.api.BookGenreEnum;
import book.lib.dto.BookCredentialsDto;
import book.lib.dto.BookDto;
import book.lib.dto.BookReservedDto;
import book.lib.entity.Book;
import book.lib.entity.BookCredential;
import book.lib.entity.BookStatus;
import book.lib.repo.BookRepository;
import book.lib.repo.CredentialRepository;
import book.lib.repo.StatusRepository;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

	final private BookRepository bookRepo;
	
	final private CredentialRepository credentialRepo;

	final private StatusRepository statusRepo;
	
	@Override
	public List<Book> getAll() {
		return bookRepo.findAll();
	}

    @Override
    public List<BookDto> getAll2() {
    	List<BookDto> booksDto = new ArrayList<>();
    	List<Book> books = bookRepo.findAll();
    	for(Book b: books) {
    		BookDto bookDto = BookDto.build(b);
    		booksDto.add(bookDto);
    	}    	
    	
    	return booksDto;
        
    }

    @Override
    public List<BookCredentialsDto> getBooksByGenre(BookGenreEnum bookGenre) {
    	List<BookCredentialsDto> credentialsDto = new ArrayList<>();
        List<Book> books = bookRepo.findByCredential_BookGenre(bookGenre);
        for (Book b : books) {
        	credentialsDto.add(BookCredentialsDto.build(b));
        }        
        return credentialsDto;
    }

    @Override
    public List<BookCredentialsDto> getBooksByGenreAndPages(BookGenreEnum bookGenre, int min, int max) {
        List<BookCredentialsDto> result = new ArrayList<>();
        List<Book> books = bookRepo.findByCredential_BookGenreAndCredential_PagesAmountBetween(bookGenre, min, max);
        for (Book b : books) {
            result.add(BookCredentialsDto.build(b));
        }
        return result;
    }

    @Override
    public List<BookCredentialsDto> getBooksByGenreAndPagesOrdered(BookGenreEnum bookGenre, int min, int max) {
        List<BookCredentialsDto> result = new ArrayList<>();
        List<Book> books = bookRepo.findByCredential_BookGenreAndCredential_PagesAmountBetweenOrderByCredential_PagesAmountAsc(bookGenre, min, max);
        for (Book b : books) {
            result.add(BookCredentialsDto.build(b));
        }
        return result;
    }

    @Override
    public List<BookReservedDto> getReservedBooks() {
        List<BookReservedDto> result = new ArrayList<>();
        List<BookStatus> statuses = statusRepo.findByReservedStatusTrue();
        for (BookStatus s : statuses) {
            result.add(BookReservedDto.build(s.getBook()));
        }
        return result;
    }

    @Override
    public List<BookDto> getFreeBooks() {
        List<BookDto> result = new ArrayList<>();
        List<BookStatus> statuses = statusRepo.findByReservedStatusFalse();
        for (BookStatus s : statuses) {
            result.add(BookDto.build(s.getBook()));
        }
        return result;
    }

    @Override
    public int setBookReserved(String title) {
    	int res = 0;
        Optional<Book> bookOpt = bookRepo.findByTitle(title);
        if (bookOpt.isPresent()) {
            BookStatus status = bookOpt.get().getStatus();
            if (!status.getReservedStatus()) {
                status.setReservedStatus(true);
                status.setReservedDate(java.time.LocalDate.now());
                statusRepo.save(status);
                res = 1;
            }
        }
        return res;
    }

    @Override
    public int setBookFree(String title) {
    	int res = 0;
        Optional<Book> bookOpt = bookRepo.findByTitle(title);
        if (bookOpt.isPresent()) {
            BookStatus status = bookOpt.get().getStatus();
            if (status.getReservedStatus()) {
                status.setReservedStatus(false);
                status.setReservedDate(null);
                statusRepo.save(status);
                res = 1;
            }
        }
        return res;
    }


}