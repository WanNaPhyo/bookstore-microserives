package com.example.transportaionapp.dao;

import com.example.transportaionapp.ds.TransportInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransportInfoDao extends JpaRepository<TransportInfo,Integer> {
}
