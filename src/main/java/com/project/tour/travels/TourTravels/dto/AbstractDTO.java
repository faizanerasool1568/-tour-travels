package com.project.tour.travels.TourTravels.dto;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class AbstractDTO implements Serializable {
	private static final long serialVersionUID = 5422588710329994661L;
	private Map<String, String> extras = new HashMap<String, String>();
	private Long id;

	private String aadharFileName;
	private MultipartFile aadharUploadFile;
	private Long aadharDocumentId;

	private String panFileName;
	private MultipartFile panUploadFile;
	private Long panDocumentId;

	private String dlFileName;
	private MultipartFile dlUploadFile;
	private Long dlDocumentId;

	private String billFileName;
	private MultipartFile billUploadFile;
	private Long billDocumentId;

	private String otherFileName;
	private MultipartFile otherUploadFile;
	private Long otherDocumentId;

	private String ghumastaFileName;
	private MultipartFile ghumastaUploadFile;
	private Long ghumastaDocumentId;

	private String advPayRecpFileName;
	private MultipartFile advPayRecpUploadFile;
	private Long advPayRecpDocumentId;

	private String fullPayRecpFileName;
	private MultipartFile fullPayRecpUploadFile;
	private Long fullPayRecpDocumentId;

	public Map<String, String> getExtras() {
		return extras;
	}

	public void setExtras(Map<String, String> extras) {
		this.extras = extras;
	}

	public String getAadharFileName() {
		return aadharFileName;
	}

	public void setAadharFileName(String aadharFileName) {
		this.aadharFileName = aadharFileName;
	}

	public MultipartFile getAadharUploadFile() {
		return aadharUploadFile;
	}

	public void setAadharUploadFile(MultipartFile aadharUploadFile) {
		this.aadharUploadFile = aadharUploadFile;
	}

	public Long getAadharDocumentId() {
		return aadharDocumentId;
	}

	public void setAadharDocumentId(Long aadharDocumentId) {
		this.aadharDocumentId = aadharDocumentId;
	}

	public String getPanFileName() {
		return panFileName;
	}

	public void setPanFileName(String panFileName) {
		this.panFileName = panFileName;
	}

	public MultipartFile getPanUploadFile() {
		return panUploadFile;
	}

	public void setPanUploadFile(MultipartFile panUploadFile) {
		this.panUploadFile = panUploadFile;
	}

	public Long getPanDocumentId() {
		return panDocumentId;
	}

	public void setPanDocumentId(Long panDocumentId) {
		this.panDocumentId = panDocumentId;
	}

	public String getDlFileName() {
		return dlFileName;
	}

	public void setDlFileName(String dlFileName) {
		this.dlFileName = dlFileName;
	}

	public MultipartFile getDlUploadFile() {
		return dlUploadFile;
	}

	public void setDlUploadFile(MultipartFile dlUploadFile) {
		this.dlUploadFile = dlUploadFile;
	}

	public Long getDlDocumentId() {
		return dlDocumentId;
	}

	public void setDlDocumentId(Long dlDocumentId) {
		this.dlDocumentId = dlDocumentId;
	}

	public String getBillFileName() {
		return billFileName;
	}

	public void setBillFileName(String billFileName) {
		this.billFileName = billFileName;
	}

	public MultipartFile getBillUploadFile() {
		return billUploadFile;
	}

	public void setBillUploadFile(MultipartFile billUploadFile) {
		this.billUploadFile = billUploadFile;
	}

	public Long getBillDocumentId() {
		return billDocumentId;
	}

	public void setBillDocumentId(Long billDocumentId) {
		this.billDocumentId = billDocumentId;
	}

	public String getOtherFileName() {
		return otherFileName;
	}

	public void setOtherFileName(String otherFileName) {
		this.otherFileName = otherFileName;
	}

	public MultipartFile getOtherUploadFile() {
		return otherUploadFile;
	}

	public void setOtherUploadFile(MultipartFile otherUploadFile) {
		this.otherUploadFile = otherUploadFile;
	}

	public Long getOtherDocumentId() {
		return otherDocumentId;
	}

	public void setOtherDocumentId(Long otherDocumentId) {
		this.otherDocumentId = otherDocumentId;
	}

	public String getGhumastaFileName() {
		return ghumastaFileName;
	}

	public void setGhumastaFileName(String ghumastaFileName) {
		this.ghumastaFileName = ghumastaFileName;
	}

	public MultipartFile getGhumastaUploadFile() {
		return ghumastaUploadFile;
	}

	public void setGhumastaUploadFile(MultipartFile ghumastaUploadFile) {
		this.ghumastaUploadFile = ghumastaUploadFile;
	}

	public Long getGhumastaDocumentId() {
		return ghumastaDocumentId;
	}

	public void setGhumastaDocumentId(Long ghumastaDocumentId) {
		this.ghumastaDocumentId = ghumastaDocumentId;
	}

	public String getAdvPayRecpFileName() {
		return advPayRecpFileName;
	}

	public void setAdvPayRecpFileName(String advPayRecpFileName) {
		this.advPayRecpFileName = advPayRecpFileName;
	}

	public MultipartFile getAdvPayRecpUploadFile() {
		return advPayRecpUploadFile;
	}

	public void setAdvPayRecpUploadFile(MultipartFile advPayRecpUploadFile) {
		this.advPayRecpUploadFile = advPayRecpUploadFile;
	}

	public Long getAdvPayRecpDocumentId() {
		return advPayRecpDocumentId;
	}

	public void setAdvPayRecpDocumentId(Long advPayRecpDocumentId) {
		this.advPayRecpDocumentId = advPayRecpDocumentId;
	}

	public String getFullPayRecpFileName() {
		return fullPayRecpFileName;
	}

	public void setFullPayRecpFileName(String fullPayRecpFileName) {
		this.fullPayRecpFileName = fullPayRecpFileName;
	}

	public MultipartFile getFullPayRecpUploadFile() {
		return fullPayRecpUploadFile;
	}

	public void setFullPayRecpUploadFile(MultipartFile fullPayRecpUploadFile) {
		this.fullPayRecpUploadFile = fullPayRecpUploadFile;
	}

	public Long getFullPayRecpDocumentId() {
		return fullPayRecpDocumentId;
	}

	public void setFullPayRecpDocumentId(Long fullPayRecpDocumentId) {
		this.fullPayRecpDocumentId = fullPayRecpDocumentId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}