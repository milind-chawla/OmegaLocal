package com.omega.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.omega.actor.transport.BookTransport.BookCreated;
import com.omega.actor.transport.BookTransport.BookUpdated;
import com.omega.domain.Book;
import com.omega.service.ActorService;

public class BookDaoJpaImpl implements BookDao {
	
	private ActorService actorService = null;

	public BookDaoJpaImpl(ActorService actorService) {
		this.actorService = actorService;
	}

	@PersistenceContext(unitName = "OmegaUnit1")
    private EntityManager entityManager = null;

	@Override
	public Book findById(long bookId) {
		Book book = entityManager.find(Book.class, bookId);
		
		return book;
	}

	@Override
	public List<Book> findByName(String name) {
		List<Book> books = entityManager.createQuery("SELECT b FROM Book b WHERE b.name = :name ORDER BY b.id ASC", Book.class)
				.setParameter("name", name)
				.getResultList();
		
		return books;
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
		book.setImage("[image]");
		
		entityManager.persist(book);
		entityManager.flush();
		
		actorService.bookAction(new BookCreated(book.getId(), book.getName()));
		
		return book;
	}

	@Override
	public Book update(Book book) {
	    Book book2 = entityManager.find(Book.class, book.getId());
	    book2.setName(book.getName());
	    
	    entityManager.persist(book2);
	    entityManager.flush();
	    
	    actorService.bookAction(new BookUpdated(book.getId(), book.getName()));
        
		return book2;
	}
}
