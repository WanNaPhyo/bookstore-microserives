package com.example.transportaionapp.ds;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Component
public class TransportInfoDto {

    private String name;
    private String orderId;

    private double total;

    private List<BookDto> books=new ArrayList<>();

    public TransportInfoDto(){}
}
