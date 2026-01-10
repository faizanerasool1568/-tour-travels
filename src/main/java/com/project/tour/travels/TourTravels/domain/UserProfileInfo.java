package com.project.tour.travels.TourTravels.domain;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.project.tour.travels.TourTravels.dto.CodeAndDescriptionDTO;

@Entity
@Table(name = "CUSTOMER_PROFILE_INFO")
@SequenceGenerator(initialValue = 1, allocationSize = 1, name = "idgen", sequenceName = "CUSTOMER_PROFILE_INFO_SEQ")
//@Cache(region = "baseDomainCache", usage = CacheConcurrencyStrategy.READ_ONLY)
public class UserProfileInfo extends AbstractEntity {

	@Column(name = "MOBILE_NUMBER", nullable = false)
	private String mobileNumber;

	@Column(name = "EMAIL_ADDRESS", nullable = false)
	private String email;

	@OneToOne
	@JoinColumn(name = "AADHAR_ID")
	private DocumentDetail aadharDocumentId;

	@OneToOne
	@JoinColumn(name = "PAN_ID")
	private DocumentDetail panDocumentId;

	@OneToOne
	@JoinColumn(name = "DL_ID")
	private DocumentDetail dlDocumentId;

	@OneToOne
	@JoinColumn(name = "BILL_ID")
	private DocumentDetail billDocumentId;

	@OneToOne
	@JoinColumn(name = "OTHER_FILE_ID")
	private DocumentDetail otherDocumentId;

	@OneToOne
	@JoinColumn(name = "GHUMASTA_ID")
	private DocumentDetail ghumastaDocumentId;

	@OneToOne
	@JoinColumn(name = "ADVANCE_AMT_FILE_ID")
	private DocumentDetail advPayRecpDocumentId;

	@OneToOne
	@JoinColumn(name = "FULL_AMT_FILE_ID")
	private DocumentDetail fullPayRecpDocumentId;

	@Column(name = "WHATSAPP_NUMBER")
	private String whatsAppNumber;

	@OneToOne
	@JoinColumn(name = "GENDER_ID")
	private ConfigurationValue genderId;

	@Column(name = "ROOM_NUMBER")
	private String roomNumber;

	@Column(name = "NAGAR")
	private String nagar;

	@Column(name = "PIN_CODE")
	private String pinCode;

	@Column(name = "STREET")
	private String street;

	@Column(name = "NEAR_BY")
	private String nearBy;

	@Column(name = "DATE_OF_JOINING")
	private LocalDate dateOfJoining;

	@Column(name = "PERM_ROOM_NUMBER")
	private String permanentRoomNumber;

	@Column(name = "PERM_NAGAR")
	private String permanentNagar;

	@Column(name = "PERM_PIN_CODE")
	private String permanentPinCode;

	@Column(name = "PERM_STREET")
	private String permanentStreet;

	@Column(name = "PERM_NEAR_BY")
	private String permanentNearBy;

	@OneToOne
	@JoinColumn(name = "EXPERIENCE_ID")
	private ConfigurationValue experinceId;

	@OneToOne
	@JoinColumn(name = "TOURIST_LICENSE_ID")
	private ConfigurationValue touristLicenseId;

	@Column(name = "COMPANY_NAME")
	private String companyName;

	@Column(name = "MANAGER_NAME")
	private String managerName;

	@Column(name = "MANAGER_MOBILE_NUMBER")
	private String managerMobileNumber;

	@Column(name = "MANAGER_EMAIL")
	private String managerEmail;

	@Column(name = "COMPANY_SHOP_NUMBER")
	private String shopNumber;

	@Column(name = "COMPANY_NAGAR")
	private String companyNagar;

	@Column(name = "COMPANY_PIN_CODE")
	private String companyPinCode;

	@Column(name = "COMPANY_STREET")
	private String companyStreet;

	@Column(name = "COMPANY_NEAR_BY")
	private String companyNearBy;

	@Column(name = "CURRENT_SALARY")
	private String currentSalary;

	@Column(name = "EXPECTED_SALARY")
	private String expectedSalary;

	@Column(name = "APPROVED_SALARY")
	private String approvedSalary;

	@Column(name = "AGREEMENT_ID")
	private String agreementId;

	@Column(name = "AGREEMENT_AMOUNT")
	private String agreementAmount;

	@OneToOne
	@JoinColumn(name = "LOCKING_PERIOD_ID")
	private ConfigurationValue lockingPeriodId;

	@OneToOne
	@JoinColumn(name = "COMPANY_NOTICE_PERIOD_ID")
	private ConfigurationValue companyNoticePeriodId;

	@OneToOne
	@JoinColumn(name = "AGREEMENT_REVIEWER_ID")
	private ConfigurationValue agreementReviewerId;

	@OneToOne
	@JoinColumn(name = "BREAKING_PENALTY_ID")
	private ConfigurationValue breakingPenaltyId;

	@OneToOne
	@JoinColumn(name = "PARTNER_NOTICE_PERIOD_ID")
	private ConfigurationValue partnerNoticePeriodId;

	@Column(name = "DATE_OF_AGREEMENT")
	private LocalDate dateOfAgreement;

	@Column(name = "GST_NUMBER")
	private String gstNumber;

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public DocumentDetail getAadharDocumentId() {
		return aadharDocumentId;
	}

	public void setAadharDocumentId(DocumentDetail aadharDocumentId) {
		this.aadharDocumentId = aadharDocumentId;
	}

	public DocumentDetail getPanDocumentId() {
		return panDocumentId;
	}

	public void setPanDocumentId(DocumentDetail panDocumentId) {
		this.panDocumentId = panDocumentId;
	}

	public DocumentDetail getDlDocumentId() {
		return dlDocumentId;
	}

	public void setDlDocumentId(DocumentDetail dlDocumentId) {
		this.dlDocumentId = dlDocumentId;
	}

	public DocumentDetail getBillDocumentId() {
		return billDocumentId;
	}

	public void setBillDocumentId(DocumentDetail billDocumentId) {
		this.billDocumentId = billDocumentId;
	}

	public DocumentDetail getOtherDocumentId() {
		return otherDocumentId;
	}

	public void setOtherDocumentId(DocumentDetail otherDocumentId) {
		this.otherDocumentId = otherDocumentId;
	}

	public DocumentDetail getGhumastaDocumentId() {
		return ghumastaDocumentId;
	}

	public void setGhumastaDocumentId(DocumentDetail ghumastaDocumentId) {
		this.ghumastaDocumentId = ghumastaDocumentId;
	}

	public DocumentDetail getAdvPayRecpDocumentId() {
		return advPayRecpDocumentId;
	}

	public void setAdvPayRecpDocumentId(DocumentDetail advPayRecpDocumentId) {
		this.advPayRecpDocumentId = advPayRecpDocumentId;
	}

	public DocumentDetail getFullPayRecpDocumentId() {
		return fullPayRecpDocumentId;
	}

	public void setFullPayRecpDocumentId(DocumentDetail fullPayRecpDocumentId) {
		this.fullPayRecpDocumentId = fullPayRecpDocumentId;
	}

	public String getWhatsAppNumber() {
		return whatsAppNumber;
	}

	public void setWhatsAppNumber(String whatsAppNumber) {
		this.whatsAppNumber = whatsAppNumber;
	}

	public ConfigurationValue getGenderId() {
		return genderId;
	}

	public void setGenderId(ConfigurationValue genderId) {
		this.genderId = genderId;
	}

	public String getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}

	public String getNagar() {
		return nagar;
	}

	public void setNagar(String nagar) {
		this.nagar = nagar;
	}

	public String getPinCode() {
		return pinCode;
	}

	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getNearBy() {
		return nearBy;
	}

	public void setNearBy(String nearBy) {
		this.nearBy = nearBy;
	}

	public LocalDate getDateOfJoining() {
		return dateOfJoining;
	}

	public void setDateOfJoining(LocalDate dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}

	public ConfigurationValue getExperinceId() {
		return experinceId;
	}

	public void setExperinceId(ConfigurationValue experinceId) {
		this.experinceId = experinceId;
	}

	public ConfigurationValue getTouristLicenseId() {
		return touristLicenseId;
	}

	public void setTouristLicenseId(ConfigurationValue touristLicenseId) {
		this.touristLicenseId = touristLicenseId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public String getManagerMobileNumber() {
		return managerMobileNumber;
	}

	public void setManagerMobileNumber(String managerMobileNumber) {
		this.managerMobileNumber = managerMobileNumber;
	}

	public String getManagerEmail() {
		return managerEmail;
	}

	public void setManagerEmail(String managerEmail) {
		this.managerEmail = managerEmail;
	}

	public String getCompanyNagar() {
		return companyNagar;
	}

	public void setCompanyNagar(String companyNagar) {
		this.companyNagar = companyNagar;
	}

	public String getCompanyPinCode() {
		return companyPinCode;
	}

	public void setCompanyPinCode(String companyPinCode) {
		this.companyPinCode = companyPinCode;
	}

	public String getCompanyStreet() {
		return companyStreet;
	}

	public void setCompanyStreet(String companyStreet) {
		this.companyStreet = companyStreet;
	}

	public String getCompanyNearBy() {
		return companyNearBy;
	}

	public void setCompanyNearBy(String companyNearBy) {
		this.companyNearBy = companyNearBy;
	}

	public String getCurrentSalary() {
		return currentSalary;
	}

	public void setCurrentSalary(String currentSalary) {
		this.currentSalary = currentSalary;
	}

	public String getExpectedSalary() {
		return expectedSalary;
	}

	public void setExpectedSalary(String expectedSalary) {
		this.expectedSalary = expectedSalary;
	}

	public String getApprovedSalary() {
		return approvedSalary;
	}

	public void setApprovedSalary(String approvedSalary) {
		this.approvedSalary = approvedSalary;
	}

	public String getPermanentRoomNumber() {
		return permanentRoomNumber;
	}

	public void setPermanentRoomNumber(String permanentRoomNumber) {
		this.permanentRoomNumber = permanentRoomNumber;
	}

	public String getPermanentNagar() {
		return permanentNagar;
	}

	public void setPermanentNagar(String permanentNagar) {
		this.permanentNagar = permanentNagar;
	}

	public String getPermanentPinCode() {
		return permanentPinCode;
	}

	public void setPermanentPinCode(String permanentPinCode) {
		this.permanentPinCode = permanentPinCode;
	}

	public String getPermanentStreet() {
		return permanentStreet;
	}

	public void setPermanentStreet(String permanentStreet) {
		this.permanentStreet = permanentStreet;
	}

	public String getPermanentNearBy() {
		return permanentNearBy;
	}

	public void setPermanentNearBy(String permanentNearBy) {
		this.permanentNearBy = permanentNearBy;
	}

	public String getShopNumber() {
		return shopNumber;
	}

	public void setShopNumber(String shopNumber) {
		this.shopNumber = shopNumber;
	}

	public String getAgreementId() {
		return agreementId;
	}

	public void setAgreementId(String agreementId) {
		this.agreementId = agreementId;
	}

	public String getAgreementAmount() {
		return agreementAmount;
	}

	public void setAgreementAmount(String agreementAmount) {
		this.agreementAmount = agreementAmount;
	}

	public ConfigurationValue getLockingPeriodId() {
		return lockingPeriodId;
	}

	public void setLockingPeriodId(ConfigurationValue lockingPeriodId) {
		this.lockingPeriodId = lockingPeriodId;
	}

	public ConfigurationValue getCompanyNoticePeriodId() {
		return companyNoticePeriodId;
	}

	public void setCompanyNoticePeriodId(ConfigurationValue companyNoticePeriodId) {
		this.companyNoticePeriodId = companyNoticePeriodId;
	}

	public ConfigurationValue getAgreementReviewerId() {
		return agreementReviewerId;
	}

	public void setAgreementReviewerId(ConfigurationValue agreementReviewerId) {
		this.agreementReviewerId = agreementReviewerId;
	}

	public ConfigurationValue getBreakingPenaltyId() {
		return breakingPenaltyId;
	}

	public void setBreakingPenaltyId(ConfigurationValue breakingPenaltyId) {
		this.breakingPenaltyId = breakingPenaltyId;
	}

	public ConfigurationValue getPartnerNoticePeriodId() {
		return partnerNoticePeriodId;
	}

	public void setPartnerNoticePeriodId(ConfigurationValue partnerNoticePeriodId) {
		this.partnerNoticePeriodId = partnerNoticePeriodId;
	}

	public LocalDate getDateOfAgreement() {
		return dateOfAgreement;
	}

	public void setDateOfAgreement(LocalDate dateOfAgreement) {
		this.dateOfAgreement = dateOfAgreement;
	}

	public String getGstNumber() {
		return gstNumber;
	}

	public void setGstNumber(String gstNumber) {
		this.gstNumber = gstNumber;
	}

}