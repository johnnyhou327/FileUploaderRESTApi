package com.johnny.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import com.johnny.entity.FileMetaData;

@Repository
public class MetaDataDAOImpl implements MetaDataDAO{
	
	@PersistenceContext
	EntityManager em;

	@Override
	public void save(FileMetaData fileMetaData) {
		System.out.println(fileMetaData);
		em.persist(fileMetaData);
	}

	@Override
	public List<FileMetaData> loadAllMetaData() {
		String queryString = "SELECT Object (fmd) FROM FileMetaData fmd";
		Query query = em.createQuery(queryString);
		return query.getResultList();
	}

	@Override
	public FileMetaData loadMetaDataById(Integer fileId) {		
		return em.find(FileMetaData.class, fileId);
	}
	
	
}
