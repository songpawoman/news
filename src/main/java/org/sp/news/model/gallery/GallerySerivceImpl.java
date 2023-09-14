package org.sp.news.model.gallery;

import java.util.List;

import org.sp.news.domain.Gallery;
import org.sp.news.domain.GalleryImg;
import org.sp.news.exception.FileException;
import org.sp.news.exception.GalleryException;
import org.sp.news.exception.GalleryImgException;
import org.sp.news.model.util.FileManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GallerySerivceImpl implements GalleryService{
	@Autowired
	private GalleryDAO galleryDAO;
	
	@Autowired
	private GalleryImgDAO galleryImgDAO;
	
	@Autowired
	private FileManager fileManager;
	
	@Override
	public List selectAll() {
		return galleryDAO.selectAll();
	}
	
	@Override
	public Gallery select(int gallery_idx) {
		return galleryDAO.select(gallery_idx);
	}
	
	//게시물 등록
	@Transactional(propagation = Propagation.REQUIRED)
	public void regist(Gallery gallery, String realpath) throws FileException, GalleryException, GalleryImgException{
		
		//파일저장 
		fileManager.saveFile(gallery, realpath);
		//saveFile 메서드 실행 후엔, galleryImgList 도 모두 채워져 있슴

		
		//insert 수행 전 gallery_idx 0 
		galleryDAO.insert(gallery);
		//insert 수행 후 gallery_idx   pk 채워짐

		for(GalleryImg galleryImg : gallery.getGalleryImgList()) {
			galleryImg.setGallery(gallery); //gallery_idx가 채워진 Gallery DTO 전달
			galleryImgDAO.insert(galleryImg);
		}				

	}

	@Override
	public void update(Gallery gallery) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Gallery gallery) {
		// TODO Auto-generated method stub
		
	}
	
}
