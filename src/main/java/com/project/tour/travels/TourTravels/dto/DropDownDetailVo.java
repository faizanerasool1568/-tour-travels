package com.project.tour.travels.TourTravels.dto;

public class DropDownDetailVo {

	private Long id;
	private String code;
	private String description;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public DropDownDetailVo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DropDownDetailVo(Long id, String code, String description) {
		super();
		this.id = id;
		this.code = code;
		this.description = description;
	}

}
