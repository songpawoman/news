package org.sp.news.model.gallery;

import java.util.List;

import org.sp.news.domain.Gallery;

public interface GalleryService {

	//목록, 등록, 수정, 삭제 
	public List selectAll();
	public Gallery select(int gallery_idx);
	public void regist(Gallery gallery, String realpath);
	public void update(Gallery gallery, String realpath);
	public void delete(Gallery gallery);
	
}









