package com.omega.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.omega.domain.Book;

public class BookDaoJpaImpl implements BookDao {
	
	@PersistenceContext(unitName = "OmegaUnit1")
    private EntityManager entityManager = null;

	@Override
	public Book findById(long id) {
		return entityManager.find(Book.class, id);
	}

	@Override
	public Book findByName(String name) {
		return entityManager.find(Book.class, name);
	}

	@Override
	public List<Book> getBooks() {
		return entityManager.createQuery("SELECT b FROM Book b ORDER BY b.id ASC", Book.class).getResultList();
	}

	@Override
	public List<Book> getBooks(int page) {
		int pageSize = 10;
		
		// TypedQuery<Integer> countQuery = entityManager.createQuery("SELECT count(b.id) FROM Book b", Integer.class);
        // int countResult = countQuery.getSingleResult();
        // int lastPageNumber = (countResult / pageSize) + 1;
        
        List<Book> list = entityManager
            .createQuery("SELECT b FROM Book b ORDER BY b.id ASC", Book.class)
            .setFirstResult((page - 1)* pageSize)
            .setMaxResults(pageSize)
            .getResultList();
        
		return list;
	}

	@Override
	public Book save(Book book) {
		entityManager.persist(book);
        // actorService.bookAction(BookCreated(book.id, book.name))
        
		return book;
	}

	@Override
	public Book update(Book book) {
		long id = book.getId();
		        
        Book book2 = entityManager.find(Book.class, id);
        book2.setName(book.getName());
        entityManager.persist(book2);
        // actorService.bookAction(BookUpdated(book2.id, book2.name))
        
		return book2;
	}
}
