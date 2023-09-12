package org.sp.news.domain;

import lombok.Data;

@Data
public class UserHobby {
	private int user_hobby_idx;
	private Member member;
	private Hobby hobby;
}
