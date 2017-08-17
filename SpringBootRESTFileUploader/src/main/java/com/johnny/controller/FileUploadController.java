package com.johnny.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.johnny.entity.FileMetaData;
import com.johnny.service.StoreFileService;

@Controller
public class FileUploadController {
	
	private final StoreFileService storeFileService;
	
	// constructor DI
	@Autowired 
	public FileUploadController(StoreFileService storeFileService) {
		this.storeFileService = storeFileService;
	}
	
	@GetMapping(value="/")
	public String home() {
		return "upload";
	}
	
	@GetMapping(value="/files")
	public List<FileMetaData> listUploadedFiles() throws IOException {
		storeFileService.loadAllFileInfo();
		return null;
	}
	
	@GetMapping(value="/files/{fileId}")
	@ResponseBody
	public ResponseEntity<Resource> serveFile(@PathVariable Integer fileId) {
		return null;
	}
	
	@PostMapping(value="/")
	public String uploadFile(@RequestParam("file") MultipartFile file) {
		storeFileService.store(file);
		return "upload";
	}

}
