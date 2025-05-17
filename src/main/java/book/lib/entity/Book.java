package book.lib.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "book_library")
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_book")
	private Integer id;	

    private String author_surname;

    private String author_name;

    @Column(name = "bookname")
    private String title;

}
