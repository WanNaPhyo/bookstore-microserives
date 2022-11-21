package com.example.bookstoreui.ds;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
public class Book {

    private Integer id;
    private String title;
    private String authorName;
    private double price;
    private String publisher;
    private LocalDate yearPublished;
    private String genre;
    private String imageUrl;

    private List<Integer> quantity=new LinkedList<>();

    private Integer itemCount;

    public Book(){}

    public Book(String title, String authorName, double price, String publisher, LocalDate yearPublished, String genre, String imageUrl) {
        this.title = title;
        this.authorName = authorName;
        this.price = price;
        this.publisher = publisher;
        this.yearPublished = yearPublished;
        this.genre = genre;
        this.imageUrl = imageUrl;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return id.equals(book.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
