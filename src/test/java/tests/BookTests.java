package tests;

import clients.BookClient;
import models.Book;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class BookTests {
    private BookClient bookClient;

    @BeforeClass
    public void setup() {
        // Initializing the client to handle API interactions
        bookClient = new BookClient();
    }

    @Test(priority = 1, description = "Happy Path: Verify that all books can be retrieved")
    public void testGetAllBooks() {
        Response response = bookClient.getAllBooks();

        Assert.assertEquals(response.getStatusCode(), 200, "Expected status code 200");
        Assert.assertNotNull(response.jsonPath().getList("$"), "Response body should not be null");
        Assert.assertTrue(response.jsonPath().getList("$").size() > 0, "Book list should not be empty");
    }

    @Test(priority = 2, description = "Happy Path: Verify retrieving a specific book by valid ID")
    public void testGetBookById() {
        int validId = 1;
        Response response = bookClient.getBookById(validId);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.jsonPath().getInt("id"), validId, "Returned ID mismatch");
    }

    @Test(priority = 3, description = "Happy Path: Verify successful creation of a new book")
    public void testCreateBook() {
        // Using Builder pattern for clean data setup
        Book newBook = Book.builder()
                .id(101)
                .title("Automation Mastery")
                .description("Advanced API Testing")
                .pageCount(300)
                .excerpt("Clean code practices")
                .publishDate("2026-02-02T10:00:00Z")
                .build();

        Response response = bookClient.createBook(newBook);

        Assert.assertEquals(response.getStatusCode(), 200, "POST should return 200 in this API");
        Assert.assertEquals(response.jsonPath().getString("title"), "Automation Mastery");
    }

    @Test(priority = 4, description = "Happy Path: Verify updating an existing book with full payload")
    public void testUpdateBook() {
        int bookId = 5;
        // Ensuring all fields are populated to avoid 400 Bad Request
        Book updatedBook = Book.builder()
                .id(bookId)
                .title("Updated Title by Ata")
                .description("Comprehensive API Testing Guide")
                .pageCount(450)
                .excerpt("Lombok and RestAssured are powerful tools.")
                .publishDate("2026-02-02T19:00:00Z") // Proper ISO 8601 format
                .build();

        Response response = bookClient.updateBook(bookId, updatedBook);

        // Assertion with a clear error message for the report
        Assert.assertEquals(response.getStatusCode(), 200,
                "Expected 200 but received: " + response.getStatusCode() + " - " + response.getBody().asString());

        Assert.assertEquals(response.jsonPath().getString("title"), "Updated Title by Ata");
    }

    @Test(priority = 5, description = "Happy Path: Verify deleting a book by ID")
    public void testDeleteBook() {
        int bookId = 10;
        Response response = bookClient.deleteBook(bookId);

        Assert.assertEquals(response.getStatusCode(), 200, "Delete operation failed");
    }

    @Test(priority = 6, description = "Edge Case: Verify 404 for a non-existing book ID")
    public void testGetInvalidBook() {

        Response response = bookClient.getBookById(0);
        Assert.assertEquals(response.getStatusCode(), 404, "Should return 404 for invalid ID");
    }

    @Test(priority = 7, description = "Edge Case: Verify deleting a non-existing book")
    public void testDeleteInvalidBook() {
        Response response = bookClient.deleteBook(99999);
        Assert.assertTrue(response.getStatusCode() == 200 || response.getStatusCode() == 404);
    }
}