package com.practice.library_dependent;

import com.practice.library_dependent.clients.BookServiceClient;
import com.practice.library_dependent.clients.UserServiceClient;
import com.practice.library_dependent.dao.LibraryDao;
import com.practice.library_dependent.entity.Book;
import com.practice.library_dependent.entity.User;
import com.practice.library_dependent.service.LibraryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LibraryDependentApplicationTests {

	@Mock
	LibraryDao libraryDao;

	@Mock
	private BookServiceClient bookServiceClient;

	@Mock
	private UserServiceClient userServiceClient;
	@InjectMocks
	private LibraryService libraryService;

	@Test
	public void test_BookById() {
		// Arrange
		Book book = new Book(10, "Clean Code", "Pearson", "Robert C. Martin");
		ResponseEntity<Book> mockResponse = ResponseEntity.ok(book);
		when(bookServiceClient.getBookById(10)).thenReturn(mockResponse);

		ResponseEntity<Book> response = libraryService.getBookById(10);

		// Assert
		assertEquals(200 , response.getStatusCode().value());
		assertEquals("Clean Code", response.getBody().getName());
	}

	@Test
	public void test_allBooks(){
		List<Book> bookList = List.of(
				new Book(1, "Clean Code", "Pearson", "Robert C. Martin"),
				new Book(2, "Effective Java", "Addison-Wesley", "Joshua Bloch")
		);
		ResponseEntity<List<Book>> listResponseEntity = ResponseEntity.ok(bookList);
		when(bookServiceClient.getAllBooks()).thenReturn(listResponseEntity);
		ResponseEntity<List<Book>> response = libraryService.getAllBooks();
		assertEquals(200, response.getStatusCode().value());
	}

	@Test
	public void test_userById(){
		User user =  new User("Phalgun Amancha","phalgunamancha@gmail.com");
		ResponseEntity<User> userResponseEntity = ResponseEntity.ok(user);
		when(userServiceClient.getUserByUsername("Phalgun Amancha")).thenReturn(userResponseEntity);
		ResponseEntity<User> response = libraryService.getUserByUsername("Phalgun Amancha");
		assertEquals(200,response.getStatusCode().value());
	}
	@Test
	public void test_AllUsers(){
		List<User> userList = List.of(new User("Manohar Reddy","manoharreddy@gmail.com"),
				new User("Poorna Sai","poornasai@gmail.com"));
		ResponseEntity<List<User>> userResponseEntity = ResponseEntity.ok(userList);
		when(userServiceClient.getAllUsers()).thenReturn(userResponseEntity);
		ResponseEntity<List<User>> response = libraryService.getAllUsers();
		assertEquals(200,response.getStatusCode().value());
	}
}
