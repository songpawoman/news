package org.sp.news.model.gallery;

import java.util.List;

import org.sp.news.domain.Gallery;
import org.sp.news.domain.GalleryImg;
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
		// TODO Auto-generated method stub
		return null;
	}

	
	//게시물 등록
	@Transactional(propagation = Propagation.REQUIRED)
	public void regist(Gallery gallery) throws GalleryException, GalleryImgException{
		
		//insert 수행 전 gallery_idx 0 
		galleryDAO.insert(gallery);
		//insert 수행 후 gallery_idx   pk 채워짐
		
		List<GalleryImg> galleryImgList = gallery.getGalleryImgList();
		
		for(GalleryImg galleryImg : galleryImgList) {
			galleryImg.setGallery(gallery); //gallery_idx가 채워진 Gallery DTO 전달
			galleryImgDAO.insert(galleryImg);
		}
		
		//파일도 저장 
		//fileManager.saveFile();		
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
