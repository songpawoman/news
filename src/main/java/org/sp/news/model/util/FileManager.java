package org.sp.news.model.util;

import org.sp.news.exception.FileException;
import org.springframework.stereotype.Component;

@Component
public class FileManager {
	
	//파일저장 
	public void saveFile() throws FileException{
		
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
	
}




