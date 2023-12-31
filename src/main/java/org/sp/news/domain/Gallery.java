package org.sp.news.domain;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class Gallery {

	private int gallery_idx;
	private String title;
	private String writer;
	private String regdate;
	private int hit;
	private String content;
	
	//복수개의 업로드 파일을 받는 업로드 컴포넌트 객체 
	MultipartFile[] file;

	//하나의 겔러리 게시물은 여러 이미지들을 보유할 수 있다. 
	List<GalleryImg> galleryImgList;
}





