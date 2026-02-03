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


        Assert.assertTrue(response.getContentType().contains("application/json"),
                "Expected JSON but received: " + response.getContentType());


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

        Book newBook = new Book();
        newBook.setId(101);
        newBook.setTitle("Automation Mastery");
        newBook.setDescription("Advanced API Testing");
        newBook.setPageCount(300);
        newBook.setExcerpt("Clean code practices");
        newBook.setPublishDate("2026-02-02T10:00:00Z");

        Response response = bookClient.createBook(newBook);

        Assert.assertEquals(response.getStatusCode(), 200, "POST should return 200 in this API");
        Assert.assertEquals(response.jsonPath().getString("title"), "Automation Mastery");
    }

    @Test(priority = 4, description = "Happy Path: Verify updating an existing book with full payload")
    public void testUpdateBook() {
        int bookId = 5;

        Book updatedBook = new Book();
        updatedBook.setId(bookId);
        updatedBook.setTitle("Updated Title by Ata");
        updatedBook.setDescription("Comprehensive API Testing Guide");
        updatedBook.setPageCount(450);
        updatedBook.setExcerpt("Lombok and RestAssured are powerful tools.");
        updatedBook.setPublishDate("2026-02-02T19:00:00Z");

        Response response = bookClient.updateBook(bookId, updatedBook);

        Assert.assertEquals(response.getStatusCode(), 200,
                "Expected 200 but received: " + response.getStatusCode());
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


    @Test(priority = 8, description = "Edge Case: Verify that ID 0 returns 404")
    public void testGetBookByZeroId() {
        Response response = bookClient.getBookById(0);
        Assert.assertEquals(response.getStatusCode(), 404);
    }

    @Test(priority = 9, description = "Edge Case: Verify creating a book with empty title")
    public void testCreateBookWithEmptyTitle() {
        Book emptyTitleBook = new Book();
        emptyTitleBook.setTitle("");
        emptyTitleBook.setId(999);

        Response response = bookClient.createBook(emptyTitleBook);
        Assert.assertTrue(response.getStatusCode() == 200 || response.getStatusCode() == 400);
    }

    @Test(priority = 10, description = "Performance: Verify that getting all books responds within 2 seconds")
    public void testGetAllBooksPerformance() {
        bookClient.getAllBooks()
                .then()
                .time(org.hamcrest.Matchers.lessThan(2000L));
    }

    @Test(priority = 11, description = "Negative: Verify that DELETE is not allowed on base URL")
    public void testDeleteAllBooksNotAllowed() {
        Response response = io.restassured.RestAssured.given()
                .spec(specs.SpecFactory.getRequestSpec())
                .delete("/api/v1/Books");

        Assert.assertTrue(response.getStatusCode() >= 400, "Should not allow mass deletion");
    }
}