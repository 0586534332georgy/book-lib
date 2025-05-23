package book.lib.dto;

import java.time.LocalDate;

import book.lib.api.BookGenreEnum;

public interface BookReservedDto {
	
	String getTitle();

    String getAuthorSurname();

    String getAuthorName();

    BookGenreEnum getBookGenre();
    
    LocalDate getReservedDate();

}
