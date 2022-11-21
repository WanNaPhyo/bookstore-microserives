package com.example.transportaionapp.controller;

import com.example.transportaionapp.dao.BookDao;
import com.example.transportaionapp.dao.TransportInfoDao;
import com.example.transportaionapp.ds.Book;
import com.example.transportaionapp.ds.BookDto;
import com.example.transportaionapp.ds.TransportInfo;
import com.example.transportaionapp.ds.TransportInfoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transport")
public class TransportController {
    @Autowired
    private TransportInfoDto transportInfoDto;
    @Autowired
    private TransportInfoDao transportInfoDao;
    @Autowired
    private BookDao bookDao;

    @Transactional
    @PostMapping(value = "/transport-create",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createTransportInfo(@RequestBody TransportInfoDto transportInfoDto){
       TransportInfo transportInfo=new TransportInfo(transportInfoDto.getName(),
               transportInfoDto.getOrderId(),
               transportInfoDto.getTotal()
       );
       for (BookDto bookDto: transportInfoDto.getBooks()){
           transportInfo.addBook(bookDao.save(toEntity(bookDto)));
       }
        transportInfoDao.save(transportInfo);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    public Book toEntity(BookDto bookDto){
        return new Book(
                bookDto.getTitle(),
                bookDto.getAuthorName(),
                bookDto.getPrice(),
                bookDto.getPublisher(),
                bookDto.getYearPublished(),
                bookDto.getGenre(),
                bookDto.getImageUrl()

        );
    }

}
