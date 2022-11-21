package com.example.transportaionapp.dao;

import com.example.transportaionapp.ds.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookDao extends JpaRepository<Book,Integer> {
}
