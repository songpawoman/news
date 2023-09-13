package org.sp.news.model.gallery;

import java.util.List;

import org.sp.news.domain.GalleryImg;

public interface GalleryImgDAO {
	
	public void insert(GalleryImg galleryImg);
	public List selectByGalleryIdx(int gallery_idx);
	public void deleteByGalleryIdx(int gallery_idx);
}
