package org.sp.news.domain;

import lombok.Data;

@Data
public class GalleryImg {
	private int gallery_img_idx;
	private Gallery gallery;
	private String filename;
	
}
