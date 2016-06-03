package com.omega.repository;

import static org.junit.Assert.assertEquals;

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
	
	@Before
	public void setupData() {
		book = new Book(1L, "Book 1", "[image]");
	}
	
	@Test
	public void testFindById() {
		Book book2 = entityManager.find(Book.class, 1L);
		
		assertEquals(book.getId(), book2.getId());
		assertEquals(book.getName(), book2.getName());
		assertEquals(book.getImage(), book2.getImage());
	}
	
	@Test
	public void testBookSave() {
		Book book = new Book("[name]", "[image]");
		
		entityManager.persist(book);
		entityManager.flush();
		
		Book book2 = entityManager.find(Book.class, book.getId());
		
		assertEquals(book.getId(), book2.getId());
		assertEquals(book.getName(), book2.getName());
		assertEquals(book.getImage(), book2.getImage());
	}
	
	@Test
	public void testBookUpdate() {
		long id = book.getId();
        
	    Book book2 = entityManager.find(Book.class, id);
	    
	    book2.setName(book.getName() + "--newname--");
	    entityManager.persist(book2);
	    entityManager.flush();
	    
	    Book book3 = entityManager.find(Book.class, id);
	    
	    assertEquals(book2.getId(), book3.getId());
		assertEquals(book2.getName(), book3.getName());
		assertEquals(book2.getImage(), book3.getImage());
	}
}
