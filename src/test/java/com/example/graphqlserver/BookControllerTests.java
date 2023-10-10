package com.example.graphqlserver;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.GraphQlTest;
import org.springframework.graphql.test.tester.GraphQlTester;

@GraphQlTest(BookController.class)
class BookControllerTests {

    @Autowired
    private GraphQlTester graphQlTester;

    @Test
    void shouldGetFirstBook() {
        this.graphQlTester
				.documentName("bookDetails")
        .operationName("bookDetails") // Acesta este numele operației din GraphQL
        .variable("id", "book-1")
                .execute()
                .path("bookById")
                .matchesJson("""
                    {
                        "id": "book-1",
                        "name": "Effective Java",
                        "pageCount": 416,
                        "author": {
                          "firstName": "Joshua",
                          "lastName": "Bloch"
                        }
                    }
                """);
    }

//  @Test
//  void shouldGetByNameBook() {
//    this.graphQlTester
//        .documentName("bookDetails")
//        .operationName("bookName") // Acesta este numele operației din GraphQL
//        .variable("name", "Effective Java")
//        .execute()
//        .path("bookByName")
//        .matchesJson("""
//                     {
//                        "id": "book-1",
//                        "name": "Effective Java",
//                        "pageCount": 416,
//                        "author": {
//                          "firstName": "Joshua",
//                          "lastName": "Bloch"
//                        }
//                    }
//                """);
//  }
}
