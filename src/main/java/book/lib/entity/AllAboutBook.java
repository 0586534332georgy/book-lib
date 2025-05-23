package book.lib.entity;

import book.lib.api.BookGenreEnum;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "all_about_book")

public class AllAboutBook {
	@Id
    @Column(name = "id_book")
    private Integer id;
    
    @Column(name = "author_surname")
    private String authorSurname;

    @Column(name = "author_name")
    private String authorName;
    
    @Column(name = "bookname")
    private String title;

    @Enumerated(EnumType.STRING)
    @Column(name = "book_genre")
    private BookGenreEnum bookGenre;

    @Column(name = "pages_amount")
    private Integer pagesAmount;

}
