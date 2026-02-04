package tests;

import clients.AuthorClient;
import models.Author;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.lessThan;

public class AuthorTests {
    private AuthorClient authorClient;

    @BeforeClass
    public void setup() {
        authorClient = new AuthorClient();
    }

    @Test(priority = 1, description = "Happy Path: Get all authors and verify format")
    public void testGetAllAuthors() {
        Response response = authorClient.getAllAuthors();

        Assert.assertEquals(response.getStatusCode(), 200);
        // Senior Touch: Validation of Content-Type
        Assert.assertTrue(response.getContentType().contains("application/json"),
                "Expected JSON but got: " + response.getContentType());
        Assert.assertNotNull(response.jsonPath().getList("$"), "Authors list should not be null");
    }

    @Test(priority = 2, description = "Happy Path: Get author by valid ID")
    public void testGetAuthorById() {
        int targetId = 1;
        Response response = authorClient.getAuthorById(targetId);
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.jsonPath().getInt("id"), targetId);
    }

    @Test(priority = 3, description = "Happy Path: Create a new author")
    public void testCreateAuthor() {
        Author newAuthor = new Author();
        newAuthor.setId(55);
        newAuthor.setIdBook(10);
        newAuthor.setFirstName("Ata");
        newAuthor.setLastName("SDET");

        Response response = authorClient.createAuthor(newAuthor);
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.jsonPath().getString("firstName"), "Ata");
    }

    @Test(priority = 4, description = "Happy Path: Update an existing author")
    public void testUpdateAuthor() {
        int authorId = 5;
        Author updatedAuthor = new Author();
        updatedAuthor.setId(authorId);
        updatedAuthor.setIdBook(2);
        updatedAuthor.setFirstName("Ata - Updated");
        updatedAuthor.setLastName("Senior Engineer");

        Response response = authorClient.updateAuthor(authorId, updatedAuthor);
        Assert.assertEquals(response.getStatusCode(), 200, "Update failed");
        Assert.assertEquals(response.jsonPath().getString("firstName"), "Ata - Updated");
        Assert.assertEquals(response.jsonPath().getString("lastName"), "Senior Engineer");
    }

    @Test(priority = 5, description = "Happy Path: Delete author by ID")
    public void testDeleteAuthor() {
        int authorId = 3;
        Response response = authorClient.deleteAuthor(authorId);
        Assert.assertEquals(response.getStatusCode(), 200, "Deletion failed");
    }

    @Test(priority = 6, description = "Edge Case: Get author with non-existing ID")
    public void testGetInvalidAuthor() {
        Response response = authorClient.getAuthorById(9999);
        Assert.assertEquals(response.getStatusCode(), 404);
    }

    @Test(priority = 7, description = "Edge Case: Verify that ID 0 returns 404")
    public void testGetAuthorByZeroId() {
        Response response = authorClient.getAuthorById(0);
        Assert.assertEquals(response.getStatusCode(), 404);
    }

    @Test(priority = 8, description = "Performance: Verify author API response time")
    public void testAuthorsPerformance() {
        authorClient.getAllAuthors()
                .then()
                .time(lessThan(2000L));
    }

    @Test(priority = 9, description = "Negative: Verify mass deletion is not allowed")
    public void testDeleteAllAuthorsNotAllowed() {
        Response response = io.restassured.RestAssured.given()
                .spec(specs.SpecFactory.getRequestSpec())
                .delete("/api/v1/Authors");

        Assert.assertTrue(response.getStatusCode() >= 400);
    }
}