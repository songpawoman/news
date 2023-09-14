package org.sp.news.model.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.sp.news.domain.Gallery;
import org.sp.news.domain.GalleryImg;
import org.sp.news.exception.FileException;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileManager {
	
	//파일저장 
	public void saveFile(Gallery gallery, String realpath) throws FileException{
		
		//Gallery DTO가 보유할 List 생성
		List galleryImgList = new ArrayList<GalleryImg>();
		
		for(MultipartFile photo : gallery.getFile()) {
			String filename=photo.getOriginalFilename(); //원래 파일명
			
			filename=createFilename(filename); //개발자가 지정한 파일명.. 473284327.jpg
			
			GalleryImg galleryImg = new GalleryImg();
			galleryImg.setFilename(filename);
			galleryImgList.add(galleryImg);
			
			File f = new File(realpath+filename);
			
			try {
				photo.transferTo(f);
			} catch (IllegalStateException e) {
				e.printStackTrace();
				throw new FileException("파일저장 실패", e);
			} catch (IOException e) {
				e.printStackTrace();
				throw new FileException("파일저장 실패", e);
			}
		}
		gallery.setGalleryImgList(galleryImgList); //Gallery에 대입
	}
	
	//확장자 구하기   dkdk.dd.d.d.jpg 
	public String getExt(String path) {
		return path.substring(path.lastIndexOf(".")+1, path.length());
	}
	
	//파일명 생성하기 
	public String createFilename(String path) {
		long time = System.currentTimeMillis();
		String ext = getExt(path);
		return time+"."+ext;
	}
	
	//파일삭제 
	public void removeFile(Gallery gallery, String realpath) throws FileException{

		for(GalleryImg img : gallery.getGalleryImgList()) {
			File file = new File(realpath, img.getFilename());
			if(file.delete()==false) {
				throw new FileException("파일 삭제실패");
			} 
		}
	}
	
}








