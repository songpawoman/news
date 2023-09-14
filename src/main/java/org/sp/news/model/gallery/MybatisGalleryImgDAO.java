package org.sp.news.model.gallery;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.sp.news.domain.GalleryImg;
import org.sp.news.exception.GalleryImgException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MybatisGalleryImgDAO implements GalleryImgDAO{
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	
	@Override
	public void insert(GalleryImg galleryImg) throws GalleryImgException{
		int result = sqlSessionTemplate.insert("GalleryImg.insert", galleryImg);
		if(result <1) {
			throw new GalleryImgException("이미지 등록 실패");
		}
	}

	@Override
	public List selectByGalleryIdx(int gallery_idx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteByGalleryIdx(int gallery_idx) {
		// TODO Auto-generated method stub
		
	}
	
}
