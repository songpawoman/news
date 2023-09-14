package org.sp.news.model.gallery;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.sp.news.domain.Gallery;
import org.sp.news.exception.GalleryException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MybatisGalleryDAO implements GalleryDAO{
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	
	@Override
	public List selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Gallery select(int gallery_idx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(Gallery gallery) throws GalleryException{
		int result = sqlSessionTemplate.insert("Gallery.insert", gallery);
		if(result <1) {
			throw new GalleryException("겔러리 등록 실패");
		}
	}

	@Override
	public void update(Gallery gallery) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int gallery_idx) {
		// TODO Auto-generated method stub
		
	}
	
}
