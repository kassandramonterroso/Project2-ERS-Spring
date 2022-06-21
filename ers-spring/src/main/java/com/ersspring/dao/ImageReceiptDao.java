package com.ersspring.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ersspring.entity.ImageReceiptEntity;


@Repository
public interface ImageReceiptDao extends JpaRepository<ImageReceiptEntity, Integer> {

}
