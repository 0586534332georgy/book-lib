package book.lib.dto;

import book.lib.api.BookGenreEnum;

public interface BookFreeDto {
	
	String getTitle();

    String getAuthorSurname();

    String getAuthorName();    

    BookGenreEnum getBookGenre();

}
