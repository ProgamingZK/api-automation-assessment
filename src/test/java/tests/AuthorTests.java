package tests;

import clients.AuthorClient;
import models.Author;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AuthorTests {
    private AuthorClient authorClient;

    @BeforeClass
    public void setup() {
        authorClient = new AuthorClient();
    }

    @Test(priority = 1, description = "Happy Path: Get all authors")
    public void testGetAllAuthors() {
        Response response = authorClient.getAllAuthors();
        Assert.assertEquals(response.getStatusCode(), 200);
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
        Author newAuthor = Author.builder()
                .id(55)
                .idBook(10)
                .firstName("Ata")
                .lastName("SDET")
                .build();

        Response response = authorClient.createAuthor(newAuthor);
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.jsonPath().getString("firstName"), "Ata");
    }

    @Test(priority = 4, description = "Happy Path: Update an existing author with full payload")
    public void testUpdateAuthor() {
        int authorId = 5;
        Author updatedAuthor = Author.builder()
                .id(authorId)
                .idBook(2)
                .firstName("Ata - Updated")
                .lastName("Senior Engineer")
                .build();

        Response response = authorClient.updateAuthor(authorId, updatedAuthor);
        Assert.assertEquals(response.getStatusCode(), 200, "Update failed with status: " + response.getStatusCode());
        Assert.assertEquals(response.jsonPath().getString("firstName"), "Ata - Updated");
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
        Assert.assertEquals(response.getStatusCode(), 404, "Should return 404 for missing author");
    }
}