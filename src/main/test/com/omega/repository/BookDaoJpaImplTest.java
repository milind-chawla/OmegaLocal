package com.omega.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.omega.config.OmegaCoreConfig;
import com.omega.domain.Book;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { OmegaCoreConfig.class })
@Transactional
public class BookDaoJpaImplTest {

	@PersistenceContext(unitName = "OmegaUnit1")
	private EntityManager entityManager;
	
	private Book book;
	private long bookId;
	
	@Before
	public void setupData() {
		bookId = 24L;
		book = new Book(bookId, "Book 1.1", "[image]");
	}
	
	@Test
	public void testFindById() {
		Book book2 = entityManager.find(Book.class, bookId);
		
		assertEquals(book.getId(), book2.getId());
		assertEquals(book.getName(), book2.getName());
		assertEquals(book.getImage(), book2.getImage());
	}
	
	@Test
	public void testFindByName() {
		String name = book.getName();
		
		List<Book> books = entityManager.createQuery("SELECT b FROM Book b WHERE b.name = :name ORDER BY b.id ASC", Book.class)
				.setParameter("name", name)
				.getResultList();
		
		for(Book book2: books) {
			assertEquals(name, book2.getName());
		}
	}
	
	@Test
	public void testSave() {
		Book book = new Book("[name]", "[image]");
		
		assertNotNull(book.getName());
		assertNotNull(book.getImage());
		
		entityManager.persist(book);
		entityManager.flush();
		
		Book book2 = entityManager.find(Book.class, book.getId());
		
		assertEquals(book.getId(), book2.getId());
		assertEquals(book.getName(), book2.getName());
		assertEquals(book.getImage(), book2.getImage());
		
		// actorService.bookAction(new BookCreated(book.getId(), book.getName()));
	}
	
	@Test
	public void testUpdate() {
		long id = book.getId();
        
	    Book book2 = entityManager.find(Book.class, id);
	    
	    book2.setName(book.getName());
	    
	    assertNotNull(book2.getName());
		assertNotNull(book2.getImage());
	    
	    entityManager.persist(book2);
	    entityManager.flush();
	    
	    Book book3 = entityManager.find(Book.class, id);
	    
	    assertEquals(book2.getId(), book3.getId());
		assertEquals(book2.getName(), book3.getName());
		assertEquals(book2.getImage(), book3.getImage());
		
		// actorService.bookAction(new BookUpdated(book.getId(), book.getName()));
	}
}
