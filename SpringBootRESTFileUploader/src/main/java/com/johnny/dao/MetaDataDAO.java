package com.johnny.dao;

import java.util.List;

import com.johnny.entity.FileMetaData;

public interface MetaDataDAO {
	
	void save(FileMetaData fileMetaData);
	List<FileMetaData> loadAllMetaData();
	FileMetaData loadMetaDataById(Integer fileId);
	
}
