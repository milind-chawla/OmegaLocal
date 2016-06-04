package com.omega.repository;

import static org.junit.Assert.assertNotNull;

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
		
		/*for(Book book2: books) {
			assertEquals(name, book2.getName());
		}*/
		
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
		
		assertNotNull(book.getName());
		assertNotNull(book.getImage());
		
		entityManager.persist(book);
		entityManager.flush();
		
		/*Book book2 = entityManager.find(Book.class, book.getId());
		
		assertEquals(book.getId(), book2.getId());
		assertEquals(book.getName(), book2.getName());
		assertEquals(book.getImage(), book2.getImage());*/
		
		actorService.bookAction(new BookCreated(book.getId(), book.getName()));
		
		return book;
	}

	@Override
	public Book update(Book book) {
		long id = book.getId();
        
	    Book book2 = entityManager.find(Book.class, id);
	    
	    book2.setName(book.getName());
	    
	    assertNotNull(book2.getName());
		assertNotNull(book2.getImage());
	    
	    entityManager.persist(book2);
	    entityManager.flush();
	    
	    actorService.bookAction(new BookUpdated(book.getId(), book.getName()));
	    
	    /*Book book3 = entityManager.find(Book.class, id);
	    
	    assertEquals(book2.getId(), book3.getId());
		assertEquals(book2.getName(), book3.getName());
		assertEquals(book2.getImage(), book3.getImage());*/
        
		return book2;
	}
}
