package com.project.tour.travels.TourTravels.dto;

public class DocumentResponseDTO {

	private static final long serialVersionUID = -8668235651933128162L;

	private Long id;
	private byte[] content;
	private String fileName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public DocumentResponseDTO(Long id) {
		super();
		this.id = id;
	}

	public DocumentResponseDTO() {

	}

	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}