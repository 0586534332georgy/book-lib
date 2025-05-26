package book.lib.dto;

import book.lib.api.BookGenreEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BookCredentialsDto {
	
    Integer id;

    String authorSurname;

    String authorName;

    String title;

    BookGenreEnum bookGenre;

    Integer pagesAmount;

}
