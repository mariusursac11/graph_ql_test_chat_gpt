package com.example.graphqlserver;

import java.util.Arrays;
import java.util.List;

public record Book(String id, String name, int pageCount, String authorId) {

  public static List<Book> books = Arrays.asList(
      new Book("book-1", "Effective Java", 416, "author-1"),
      new Book("book-2", "Hitchhiker's Guide to the Galaxy", 208, "author-2"),
      new Book("book-3", "Down Under", 436, "author-3"),
      new Book("book-4", "book 2", 250, "author-1"),
      new Book("book-5", "book 3", 400, "author-2"),
      new Book("book-6", "book 4", 350, "author-2"),
      new Book("book-7", "book 5", 280, "author-3"),
      new Book("book-8", "book 6", 320, "author-3")
                                                );

  public static Book getById(String id) {
    return books.stream()
                .filter(book -> book.id().equals(id))
                .findFirst()
                .orElse(null);
  }

  public static List<Book> getByIds(List<String> ids) {

    return books.stream()
                .filter(book -> ids.contains(book.id())).toList();
  }

  public static Book getByName(String name) {
    return books.stream()
                .filter(book -> book.name().equals(name))
                .findFirst()
                .orElse(null);
  }
}
