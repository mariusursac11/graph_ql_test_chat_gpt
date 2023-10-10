package com.example.graphqlserver;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

@Controller
public class BookController {

  @QueryMapping
  public Book bookById(@Argument String id) {
    return Book.getById(id);
  }

  @SchemaMapping(typeName = "Query", field = "booksByIds")
  public List<Book> booksByIds(@Argument List<String> ids) {
    return Book.getByIds(ids);
  }

  @QueryMapping
  public Book bookByName(@Argument String name) {
    return Book.getByName(name);
  }

  @SchemaMapping(typeName = "Query", field = "booksOrderByPageCount")
  public List<Book> booksOrderByPageCount(@Argument(name = "orderBy") OrderByInput orderBy) {

    if (orderBy != null && "pageCount".equals(orderBy.getField()) && "ASC".equals(orderBy.getDirection())) {
      return Book.books.stream()
                       .sorted(Comparator.comparingInt(Book::pageCount))
                       .collect(Collectors.toList());
    } else {
      return Book.books;
    }
  }

  @SchemaMapping
  public Author author(Book book) {
    return Author.getById(book.authorId());
  }

  @SchemaMapping(typeName = "Query", field = "booksByAuthorId")
  public List<Book> booksByAuthorId(@Argument(name = "authorId") String authorId) {
    List<Book> booksByAuthor = new ArrayList<>();

    for (Book book : Book.books) {
      if (authorId.equals(book.authorId())) {
        booksByAuthor.add(book);
      }
    }
    return booksByAuthor;
  }

  @SchemaMapping(typeName = "Query", field = "getBooks")
  public PaginatedBook getBooks(@Argument(name = "page") Integer page,
                                @Argument(name = "size") Integer size) {
    PaginatedBook paginatedBook = new PaginatedBook();
    int totalItems = Book.books.size();
    int startIndex = (page - 1) * size;
    int totalPages = (int) Math.ceil((double) totalItems / size);
    int endIndex = Math.min(startIndex + size, totalItems);
    List<Book> paginatedBooks = Book.books.subList(startIndex, endIndex);
    paginatedBook.setContent(paginatedBooks);
    paginatedBook.setPageInfo(new PageInfo(page, totalPages, totalItems));
    return paginatedBook;
  }
}
