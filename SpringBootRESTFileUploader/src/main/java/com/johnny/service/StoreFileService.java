package com.johnny.service;

import java.util.List;
import org.springframework.web.multipart.MultipartFile;

import com.johnny.entity.FileMetaData;

public interface StoreFileService {
	
	public void store(MultipartFile file, String fileDescription);	
	List<FileMetaData> loadAllFileInfo();	
	FileMetaData loadFileInfoById(Integer fileId);
	
}
