package org.sp.news.model.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HobbyServiceImpl implements HobbyService{
	
	@Autowired
	private HobbyDAO hobbyDAO;
	
	public List selectAll() {
		return hobbyDAO.selectAll();
	}
}
