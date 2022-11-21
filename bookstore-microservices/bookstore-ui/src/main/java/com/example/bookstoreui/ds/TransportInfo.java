package com.example.bookstoreui.ds;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
@AllArgsConstructor
@Getter
@Setter
public class TransportInfo {

    private String name;
    private String orderId;
    private Set<Book> books;
    private double total;

    public TransportInfo(){}

}
