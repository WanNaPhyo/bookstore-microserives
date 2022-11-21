package com.example.transportaionapp.ds;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Getter
@Setter
@Entity
public class TransportInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String orderId;
    @OneToMany(mappedBy = "transportInfo",cascade = CascadeType.PERSIST)
    private List<Book> books=new ArrayList<>();
    private double total;

    public TransportInfo(){}

    public TransportInfo(String name, String orderId, double total) {
        this.name = name;
        this.orderId = orderId;
        this.total = total;
    }

    public void addBook(Book book){
        book.setTransportInfo(this);
        books.add(book);
    }

}
