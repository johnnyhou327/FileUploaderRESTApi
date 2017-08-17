package com.johnny.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.johnny.dao.MetaDataRepository;
import com.johnny.entity.FileMetaData;
import com.johnny.service.StoreFileServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FileUploaderServiceDAOIntegrationTest {
	
	@Autowired
	private MetaDataRepository metaDataRepository;
	@Autowired
	private StoreFileServiceImpl mockStoreFileService;
	

	@Test
	public void shouldLoadAllFileInfo() throws Exception{
		metaDataRepository.save(new FileMetaData());
		metaDataRepository.save(new FileMetaData());
		assertEquals(2, mockStoreFileService.loadAllFileInfo().size());		
		
	}
	
}
