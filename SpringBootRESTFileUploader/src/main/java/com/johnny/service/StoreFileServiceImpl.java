package com.johnny.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.johnny.dao.MetaDataDAO;
import com.johnny.entity.FileMetaData;
import com.johnny.exception.FileUploadException;

@Service
public class StoreFileServiceImpl implements StoreFileService{
	
	// hard coded the location for the uploaded files, but can be refined using DI if needed
	private final Path rootLocation = Paths.get("uploaded_files");
	
	private MetaDataDAO mdDAO;
	
	@Autowired
	public void setMdDAO(MetaDataDAO mdDAO) {
		this.mdDAO = mdDAO;
	};

	@Override
	@Transactional
	public void store(MultipartFile file) {
		
		Long currentTime = System.currentTimeMillis();
		String originalFileName = StringUtils.cleanPath(file.getOriginalFilename());
		String fileName = currentTime + "_" + originalFileName;
		String fileLocation = this.rootLocation.toString() + originalFileName;
		FileMetaData fmd = new FileMetaData();
		fmd.setFileName(fileName);
		fmd.setFileSize(file.getSize());
		fmd.setFileLocation(fileLocation);
		
		try {
			if (file.isEmpty()) {
				throw new FileUploadException("File to upload is empty. File name: " + fileName);
			}						
			Files.copy(file.getInputStream(), this.rootLocation.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
			mdDAO.save(fmd);
		} 
		catch (IOException e) {
			throw new FileUploadException("Uploading file failed. File name or target folder doesn't exist. File name: " + fileName);
		}
	}

	@Override
	public List<FileMetaData> loadAllFileInfo() {
		return mdDAO.loadAllMetaData();
	}

	@Override
	public Path load(String fileName) {
		return null;
	}

}
