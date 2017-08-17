package com.johnny.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.johnny.entity.FileMetaData;
import com.johnny.exception.FileUploadException;
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
		return storeFileService.loadAllFileInfo();
	}
	
	@GetMapping(value="/files/{fileId}")
	@ResponseBody
	public FileMetaData findUploadedFileInfoById(@PathVariable Integer fileId) {
		return storeFileService.loadFileInfoById(fileId);
	}
	
	@PostMapping(value="/")
	public String uploadFile(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
		storeFileService.store(file);
		redirectAttributes.addFlashAttribute("message", file.getOriginalFilename() + " is uploaded successfully!");
		return "upload";
	}
	
	@ExceptionHandler(FileUploadException.class)
	public String fileUploadException(Exception e) {
		return "File upload error: " + e.getLocalizedMessage();
	}

}
