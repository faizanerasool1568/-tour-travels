package com.project.tour.travels.TourTravels.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "FND_DOCUMENT_DETAIL")
@SequenceGenerator(initialValue = 1, allocationSize = 1, name = "idgen", sequenceName = "FND_DOCUMENT_DETAIL_SEQ")
public class DocumentDetail extends AbstractEntity {

	@Column(name = "DOCUMENT_EXT", nullable = false)
	private String documentExt;

	@Column(name = "PATH", nullable = true, length = 2000)
	private String path;

	@Lob
	@Column(name = "DOCUMENT", nullable = false)
	private byte[] file;

	@Column(name = "CREATED_DATE")
	private LocalDateTime createdDate = LocalDateTime.now();

	@Column(name = "CREATED_BY")
	private Long createdBy;

	@Column(name = "FILE_NAME")
	private String fileName;

	public byte[] getFile() {
		return file;
	}

	public void setFile(byte[] file) {
		this.file = file;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getDocumentExt() {
		return documentExt;
	}

	public void setDocumentExt(String documentExt) {
		this.documentExt = documentExt;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}
