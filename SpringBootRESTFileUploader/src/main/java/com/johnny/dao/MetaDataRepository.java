package com.johnny.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.johnny.entity.FileMetaData;

public interface MetaDataRepository extends JpaRepository<FileMetaData, Integer>{

}
