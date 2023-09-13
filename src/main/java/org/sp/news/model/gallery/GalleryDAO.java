package org.sp.news.model.gallery;

import java.util.List;

import org.sp.news.domain.Gallery;

public interface GalleryDAO {
	
	public List selectAll();//모든 게시물
	public Gallery select(int gallery_idx);//게시물 한건
	public void insert(Gallery gallery); //글등록 
	public void update(Gallery gallery);//글수정 
	public void delete(int gallery_idx); //글삭제
}
