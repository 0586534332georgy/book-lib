package book.lib.entity;

import java.time.LocalDate;

import book.lib.api.BookStatusEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "book_status")
public class BookStatus {
	@Id
	@Column(name = "id_book")
	private Integer id;	
	
	@Column(name = "reserved_status")
	private Boolean reservedStatus;
	
	@Column(name = "reserved_date")	
	private LocalDate reservedDate;
	
	@Column(name = "condition_status")
	private BookStatusEnum conditionStatus;
	
	

}
