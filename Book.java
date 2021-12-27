package lv.sda.books;

import java.time.LocalDate;

class Book {

    private String isbn;
    private String title;
    private String description;
    private String author;
    private String publisher;
    private int pages;
    private LocalDate publishingYear;

    //Constructor
    public Book(String isbn, String title, String description, String author, String publisher, int pages, LocalDate publishingYear) {
        this.isbn = isbn;
        this.title = title;
        this.description = description;
        this.author = author;
        this.publisher = publisher;
        this.pages = pages;
        this.publishingYear = publishingYear;
    }

    public Book(){}

    //Getters

    public String getIsbn() {
        return isbn;
    }
    public String getTitle() {
        return title;
    }

    //Setters

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
    public void setPages(int pages) {
        this.pages = pages;
    }
    public void setPublishingYear(LocalDate publishingYear) {
        this.publishingYear = publishingYear;
    }


    @Override
    public String toString() {
        return  isbn + ", " +
                title + ", " +
                description + ", " +
                author + ", " +
                publisher + ", " +
                pages +", " +
                publishingYear.getYear();
    }

    public String toCSV() {
        return  isbn + ";" +
                title + ";" +
                description + ";" +
                author + ";" +
                publisher + ";" +
                pages + ";" +
                publishingYear.getYear() + "\n";
    }

}