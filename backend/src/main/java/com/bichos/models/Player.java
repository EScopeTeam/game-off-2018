package com.bichos.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Player {
	
	private String id;

	private String username;
	
	private String password;
	
	private String salt;
	
}
