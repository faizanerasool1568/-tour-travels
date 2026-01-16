package com.project.tour.travels.TourTravels.dto;

public class CodeAndDescriptionDTO {

	private Long id;

	private String code;

	private String description;

	public CodeAndDescriptionDTO(Long id, String code, String description) {
		super();
		this.id = id;
		this.code = code;
		this.description = description;
	}

	public CodeAndDescriptionDTO(String description) {
		super();
		this.description = description;
	}

	public CodeAndDescriptionDTO(Long id, String code) {
		super();
		this.id = id;
		this.code = code;
	}

	public CodeAndDescriptionDTO(Long id) {
		super();
		this.id = id;
	}

	public CodeAndDescriptionDTO() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
