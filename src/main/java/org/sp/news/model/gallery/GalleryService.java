package org.sp.news.model.gallery;

import java.util.List;

import org.sp.news.domain.Gallery;

public interface GalleryService {

	//목록, 등록, 수정, 삭제 
	public List selectAll();
	public void regist(Gallery gallery);
	public void update(Gallery gallery);
	public void delete(Gallery gallery);
	
}









