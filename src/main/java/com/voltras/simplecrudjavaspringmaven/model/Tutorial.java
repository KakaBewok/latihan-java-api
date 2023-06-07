package com.voltras.simplecrudjavaspringmaven.model;

import java.util.UUID;

import org.springframework.data.mongodb.core.mapping.Document;

// for Spring Boot 3
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "tutorial")
public class Tutorial {

	@Id
	private String id;

	private String title;

	private String description;

	private boolean published;

	public Tutorial(String title, String description, boolean published) {
		super();
		this.setId(UUID.randomUUID().toString());
		this.title = title;
		this.description = description;
		this.published = published;
	}

	
}