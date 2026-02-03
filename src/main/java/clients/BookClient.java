package clients;

import io.qameta.allure.Step;
import models.Book;
import specs.SpecFactory;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class BookClient {
    private static final String BOOKS_PATH = "/api/v1/Books";

    @Step("Fetching the list of all books from the store")
    public Response getAllBooks() {
        return given().spec(SpecFactory.getRequestSpec()).get(BOOKS_PATH);
    }

    @Step("Retrieving book details for ID: {id}")
    public Response getBookById(int id) {
        return given()
                .spec(SpecFactory.getRequestSpec())
                .pathParam("id", id)
                .get(BOOKS_PATH + "/{id}");
    }

    @Step("Adding a new book titled: {book.title}")
    public Response createBook(Book book) {
        return given()
                .spec(SpecFactory.getRequestSpec())
                .body(book)
                .post(BOOKS_PATH);
    }

    @Step("Updating book information for ID: {id}")
    public Response updateBook(int id, Book book) {
        return given()
                .spec(SpecFactory.getRequestSpec())
                .pathParam("id", id)
                .body(book)
                .put(BOOKS_PATH + "/{id}");
    }

    @Step("Removing book from the system with ID: {id}")
    public Response deleteBook(int id) {
        return given()
                .spec(SpecFactory.getRequestSpec())
                .pathParam("id", id)
                .delete(BOOKS_PATH + "/{id}");
    }
}