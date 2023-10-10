package com.example.graphqlserver;

import java.util.ArrayList;
import java.util.List;

public class PaginatedBook {

  private PageInfo pageInfo;
  private List<Book> content = new ArrayList<>();

  public PageInfo getPageInfo() {
    return pageInfo;
  }

  public void setPageInfo(PageInfo pageInfo) {
    this.pageInfo = pageInfo;
  }

  public List<Book> getContent() {
    return content;
  }

  public void setContent(List<Book> books) {
    this.content = books;
  }
}
