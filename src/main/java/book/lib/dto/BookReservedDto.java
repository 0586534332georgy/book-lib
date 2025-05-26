package book.lib.dto;

import java.time.LocalDate;

import book.lib.api.BookGenreEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BookReservedDto {
	
	String title;

    String authorSurname;

    String authorName;

    BookGenreEnum bookGenre;
    
    LocalDate reservedDate;

}
