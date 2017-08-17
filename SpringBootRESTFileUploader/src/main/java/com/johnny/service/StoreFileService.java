package com.johnny.service;

import java.nio.file.Path;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

import com.johnny.entity.FileMetaData;

public interface StoreFileService {
	
	public void store(MultipartFile file);
	
	List<FileMetaData> loadAllFileInfo();
	
	Path load(String fileName);
	
}
