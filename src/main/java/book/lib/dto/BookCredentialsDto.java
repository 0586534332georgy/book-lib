package book.lib.dto;

import book.lib.api.BookGenreEnum;

public interface BookCredentialsDto {
	
    Integer getId();

    String getAuthorSurname();

    String getAuthorName();

    String getTitle();

    BookGenreEnum getBookGenre();

    Integer getPagesAmount();

}
