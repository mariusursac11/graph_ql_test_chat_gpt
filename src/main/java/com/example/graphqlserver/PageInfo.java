package com.example.graphqlserver;

public class PageInfo {
  private int currentPage;
  private int totalPages;
  private int totalItems;

  public PageInfo(int currentPage, int totalPages, int totalItems) {
    this.currentPage = currentPage;
    this.totalPages = totalPages;
    this.totalItems = totalItems;
  }

  public int getCurrentPage() {
    return currentPage;
  }

  public void setCurrentPage(int currentPage) {
    this.currentPage = currentPage;
  }

  public int getTotalPages() {
    return totalPages;
  }

  public void setTotalPages(int totalPages) {
    this.totalPages = totalPages;
  }

  public int getTotalItems() {
    return totalItems;
  }

  public void setTotalItems(int totalItems) {
    this.totalItems = totalItems;
  }
}
