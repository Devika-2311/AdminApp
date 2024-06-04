package com.example.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.servlet.ModelAndView;

import com.example.model.account;
import com.example.repo.accountRepo;
import com.example.service.accountService;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(accountController.class)
public class AccountControllerTest {

    @MockBean
    private accountRepo ar;
    
    @MockBean
    private accountService as;

    @InjectMocks
    private accountController accountController;
//@BeforeEach
//    public void setUp() {
//        accountController = new accountController(ar);
////        MockitoAnnotations.initMocks(this);
//    	
//    }
    

    @Test
    public void testRead() {
        // Create a list of mock accounts
        List<account> mockAccounts = new ArrayList<>();
        mockAccounts.add(new account("user1", "John Doe", "1234", 30,"789440", "bangalore", "john@gmal.com", "888775", 1000));
        mockAccounts.add(new account("user2", "Jane Smith","1235", 30,"789440", "bangalore", "jane@gmal.com", "889875", 3000));

        // Mock the findAll method of accountRepo
        when(ar.findAll()).thenReturn(mockAccounts);

        // Call the read method
        List<account> result = accountController.read();

        // Verify that the findAll method was called
        verify(ar).findAll();

        // TODO: Add assertions to verify the correctness of the result list
    }
    


        @Test
        public void testCreateAccount() {
            // Create mock input parameters
            String name = "John Doe";
            String username = "johndoe";
            String password = "password";
            int age = 30;
            String ssn = "123-45-6789";
            String address = "123 Main St";
            String email = "john.doe@example.com";
            String phone = "555-123-4567";
            float balance = 1000;

            // Create a mock account
            account mockAccount = new account(name, username, password, age, ssn, address, email, phone, balance);

            // Mock the save method of accountRepo
            when(ar.save(any(account.class))).thenReturn(mockAccount);

            // Call the createAccount method
            String result = accountController.createAccount(name, username, password, age, ssn, address, email, phone, balance);

            // Verify that the save method was called
            verify(ar).save(any(account.class));

            // TODO: Add assertions to verify the correctness of the result string
        }
//        @Test
//        public void testDelete() {
//            // Mock input parameter
//            String username = "johndoe";
//
//            // Call the delete method
//            String result = accountController.delete(username);
//
//            // Verify that the delete method was called with the correct username
//            verify(as).delete(username);
//
//            // TODO: Add assertions to verify the correctness of the result string
//        }
        @Test
        public void testLoginMethod() {
            // Arrange
            accountController controller = new accountController();

            // Act
            ModelAndView modelAndView = controller.login();

            // Assert
            assertEquals("login", modelAndView.getViewName());
        }
    

//@Test
//public void testLoginaccSuccessful() {
//    // Arrange
//    accountService mockAccountService = mock(accountService.class);
//    when(mockAccountService.login("testUser", "testPassword")).thenReturn(true);
//
//    accountController controller = new accountController(mockAccountService);
//
//    // Act
//    ModelAndView modelAndView = controller.loginacc("testUser", "testPassword", null);
//
//    // Assert
//    assertEquals("redirect:/admin/indexPage", modelAndView.getViewName());
//}
@Test
public void testLoginaccFailed() {
    // Arrange
    accountService mockAccountService = mock(accountService.class);
    when(mockAccountService.login("invalidUser", "invalidPassword")).thenReturn(false);

    accountController controller = new accountController(mockAccountService);
//  controller.setAs(mockAccountService); // Set the mocked service

    // Act
    ModelAndView modelAndView = controller.loginacc("invalidUser", "invalidPassword", null);

    // Assert
    assertEquals("login", modelAndView.getViewName());
    assertTrue(modelAndView.getModel().containsKey("loginFailed"));
}
@Test
public void testIndexPage() {
    // Arrange
    accountController controller = new accountController();

    // Act
    ModelAndView modelAndView = controller.indexPage();

    // Assert
    assertEquals("indexPage", modelAndView.getViewName());
}
@Test
public void testDeletePage() {
    // Arrange
    accountController controller = new accountController();

    // Act
    ModelAndView modelAndView = controller.deletePage();

    // Assert
    assertEquals("deletePage", modelAndView.getViewName());
}
@Test
public void testAddPage() {
    // Arrange
    accountController controller = new accountController();

    // Act
    ModelAndView modelAndView = controller.addPage();

    // Assert
    assertEquals("addPage", modelAndView.getViewName());
}
}
