package book.lib.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import book.lib.entity.BookCredential;

public interface BookCredentialRepository extends JpaRepository<BookCredential, Integer> {

	
}
