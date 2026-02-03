package clients;

import io.qameta.allure.Step;
import models.Author;
import specs.SpecFactory;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class AuthorClient {
    private static final String AUTHORS_PATH = "/api/v1/Authors";

    @Step("Fetching the list of all authors")
    public Response getAllAuthors() {
        return given().spec(SpecFactory.getRequestSpec()).get(AUTHORS_PATH);
    }

    @Step("Retrieving details for author with ID: {id}")
    public Response getAuthorById(int id) {
        return given()
                .spec(SpecFactory.getRequestSpec())
                .pathParam("id", id)
                .get(AUTHORS_PATH + "/{id}");
    }

    @Step("Creating a new author: {author.firstName} {author.lastName}")
    public Response createAuthor(Author author) {
        return given()
                .spec(SpecFactory.getRequestSpec())
                .body(author)
                .post(AUTHORS_PATH);
    }

    @Step("Updating author with ID: {id}")
    public Response updateAuthor(int id, Author author) {
        return given()
                .spec(SpecFactory.getRequestSpec())
                .pathParam("id", id)
                .body(author)
                .put(AUTHORS_PATH + "/{id}");
    }

    @Step("Deleting author with ID: {id}")
    public Response deleteAuthor(int id) {
        return given()
                .spec(SpecFactory.getRequestSpec())
                .pathParam("id", id)
                .delete(AUTHORS_PATH + "/{id}");
    }
}