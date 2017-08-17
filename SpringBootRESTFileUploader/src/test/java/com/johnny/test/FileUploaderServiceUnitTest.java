package com.johnny.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import com.johnny.dao.MetaDataRepository;
import com.johnny.entity.FileMetaData;
import com.johnny.exception.FileUploadException;
import com.johnny.service.StoreFileServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FileUploaderServiceUnitTest {
	
	@Mock
	private MetaDataRepository metaDataRepository;
	
	@InjectMocks
	private StoreFileServiceImpl mockStoreFileService;
	
	public FileMetaData mockFmd = new FileMetaData();
	private final Integer fileId = 21;
	private final String fileName = "test.txt";
	private final String fileDescription = "ThisIsMetaData";
	private final String fileLocation = "ThisIsFileLocation";
	private final Long fileSize = (long) 12;
	
	
	@Before
	public void initTest() {
		MockitoAnnotations.initMocks(this);
		mockFmd.setFileDescription(fileDescription);
		mockFmd.setFileId(fileId);
		mockFmd.setFileName(fileName);
		mockFmd.setFileLocation(fileLocation);
		mockFmd.setFileSize(fileSize);
	}
	
	
	@Test(expected=FileUploadException.class)
	public void testFileIsEmptyException() throws Exception{
		MultipartFile mpFile =new MockMultipartFile("hello", new byte[0]) ;
		mockStoreFileService.store(mpFile, fileDescription);
	}
	
	@Test
	public void testLoadAllFileInfo() throws Exception{
		
		List<FileMetaData> resList = new ArrayList<FileMetaData>();
		resList.add(mockFmd);
		resList.add(new FileMetaData());		
		when(metaDataRepository.findAll()).thenReturn(resList);
		assertEquals(2, mockStoreFileService.loadAllFileInfo().size());		
		
	}
	
	@Test
	public void testLoadFileInfoById() throws Exception{
		
		when(metaDataRepository.findOne(fileId)).thenReturn(mockFmd);
		assertEquals(mockFmd.getFileId(),mockStoreFileService.loadFileInfoById(fileId).getFileId());
	}
}
