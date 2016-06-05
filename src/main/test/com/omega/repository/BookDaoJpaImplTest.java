package com.omega.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

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
public class BookDaoJpaImplTest {

	@PersistenceContext(unitName = "OmegaUnit1")
	private EntityManager entityManager = null;
	
	@Autowired
	private BookDao bookDao = null;
	
	private Book book;
	private long bookId;
	
	@Before
	public void setupData() {
		bookId = 24L;
		book = new Book(bookId, "Book 1.1", "[image]");
	}
	
	@Test
	public void testFindById() {
		Book book2 = bookDao.findById(bookId);
		
		assertEquals(book.getId(), book2.getId());
		assertEquals(book.getName(), book2.getName());
		assertEquals(book.getImage(), book2.getImage());
	}
	
	@Test
	public void testFindByName() {
		List<Book> books = bookDao.findByName(book.getName());
		
		for(Book book2: books) {
			assertEquals(book.getName(), book2.getName());
		}
	}
	
	@Test
	public void testSave() {
		Book book = new Book("[name]", "[image]");
		
		assertNotNull(book.getName());
		assertNotNull(book.getImage());
		
		bookDao.save(book);
		
		Book book2 = bookDao.findById(book.getId());
		
		assertEquals(book.getId(), book2.getId());
		assertEquals(book.getName(), book2.getName());
		assertEquals(book.getImage(), book2.getImage());
	}
	
	@Test
	public void testUpdate() {
		Book book2 = bookDao.findById(book.getId());
	    book2.setName(book.getName());
	    
	    assertNotNull(book2.getName());
		assertNotNull(book2.getImage());
	    
	    bookDao.update(book2);
	    
	    Book book3 = bookDao.findById(book.getId());
	    
	    assertEquals(book2.getId(), book3.getId());
		assertEquals(book2.getName(), book3.getName());
		assertEquals(book2.getImage(), book3.getImage());
	}
}
