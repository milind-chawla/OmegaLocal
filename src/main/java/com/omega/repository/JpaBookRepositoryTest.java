package com.omega.repository;

import static org.junit.Assert.assertEquals;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.omega.config.OmegaCoreConfig;
import com.omega.domain.Book;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { OmegaCoreConfig.class })
@Transactional
public class JpaBookRepositoryTest {

	@PersistenceContext(unitName = "OmegaUnit1")
	private EntityManager entityManager;
	
	@Autowired
	private BookDao bookDao;
	
	private Book book;
	
	@Before
	public void setupData() {
		book = new Book(1, "Book 1", "[image]");
	}
	
	@Test
	public void testFindById() {
		Book book = bookDao.findById(this.book.getId());
		
		assertEquals(this.book.getId(), book.getId());
		assertEquals(this.book.getName(), book.getName());
		assertEquals(this.book.getImage(), book.getImage());
	}
	
	@Test
	public void testStoreBookWithParams() {
		Book book = new Book("[name]", "[image]");
		bookDao.save(book);
		
		entityManager.flush();
	}
	
	@Test
	public void testStoreBookWithoutParams() {
		Book book = new Book();
		bookDao.save(book);
		
		entityManager.flush();
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
