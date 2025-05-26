package book.lib.dto;

import book.lib.api.BookGenreEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BookDto {
	
	String title;

    String authorSurname;

    String authorName;    

    BookGenreEnum bookGenre;

}
