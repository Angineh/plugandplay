package com.tech.plugandplay.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="business")
public class Business implements Serializable {
	
	@Id
	@Column(name="ID")
	private int id;
	@Column(name="COMPANY_NAME")
	private String companyname;
	@Column(name="BLURB")
	private String blurb;
	@Column(name="WEBSITE")
	private String website;
	@Column(name="CONTACT")
	private String contact;
	@Column(name="CONTACT_TITLE")
	private String contactTitle;
	@Column(name="EMAIL")
	private String email;
	@Column(name="PHONE_NUMBER")
	private String phoneNumber;
	@Column(name="TOTAL_MONEY_RAISED")
	private String totalMoneyRaised;
	@Column(name="STAGE")
	private String stage;
	@Column(name="INDUSTRIES")
	private String industries;
	@Column(name="EMPLOYEE_COUNT")
	private String employeeCount;
	@Column(name="HQ_LOCATION")
	private String hqLocation;
	@Column(name="HQ_ADDRESS_L1")
	private String hqAddressL1;
	@Column(name="HQ_ADDRESS_L2")
	private String hqAddressL2;
	@Column(name="HQ_CITY")
	private String hqCity;
	@Column(name="HQ_STATE")
	private String hqState;
	@Column(name="HQ_ZIP")
	private String hqZip;
	@Column(name="ADVANTAGE")
	private String advantage;
	@Column(name="BACKGROUND")
	private String background;
	@Column(name="FOUNDED")
	private String founded;
	@Column(name="APPLICATION")
	private String application;
	@Column(name="CASE_STUDY")
	private String caseStudy;
	@Column(name="TAGS")
	private String tags;
	@Column(name="PROGRAM")
	private String program;
	@Column(name="BATCH")
	private String batch;
	@Column(name="NOTES")
	private String notes;
	@Column(name="INVESTORS")
	private String investors;
	@Column(name="NUMBER_OF_INVESTORS")
	private String numberOfInvestors;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCompanyname() {
		return companyname;
	}
	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}
	public String getBlurb() {
		return blurb;
	}
	public void setBlurb(String blurb) {
		this.blurb = blurb;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getContactTitle() {
		return contactTitle;
	}
	public void setContactTitle(String contactTitle) {
		this.contactTitle = contactTitle;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getTotalMoneyRaised() {
		return totalMoneyRaised;
	}
	public void setTotalMoneyRaised(String totalMoneyRaised) {
		this.totalMoneyRaised = totalMoneyRaised;
	}
	public String getStage() {
		return stage;
	}
	public void setStage(String stage) {
		this.stage = stage;
	}
	public String getIndustries() {
		return industries;
	}
	public void setIndustries(String industries) {
		this.industries = industries;
	}
	public String getEmployeeCount() {
		return employeeCount;
	}
	public void setEmployeeCount(String employeeCount) {
		this.employeeCount = employeeCount;
	}
	public String getHqLocation() {
		return hqLocation;
	}
	public void setHqLocation(String hqLocation) {
		this.hqLocation = hqLocation;
	}
	public String getHqAddressL1() {
		return hqAddressL1;
	}
	public void setHqAddressL1(String hqAddressL1) {
		this.hqAddressL1 = hqAddressL1;
	}
	public String getHqAddressL2() {
		return hqAddressL2;
	}
	public void setHqAddressL2(String hqAddressL2) {
		this.hqAddressL2 = hqAddressL2;
	}
	public String getHqCity() {
		return hqCity;
	}
	public void setHqCity(String hqCity) {
		this.hqCity = hqCity;
	}
	public String getHqState() {
		return hqState;
	}
	public void setHqState(String hqState) {
		this.hqState = hqState;
	}
	public String getHqZip() {
		return hqZip;
	}
	public void setHqZip(String hqZip) {
		this.hqZip = hqZip;
	}
	public String getAdvantage() {
		return advantage;
	}
	public void setAdvantage(String advantage) {
		this.advantage = advantage;
	}
	public String getBackground() {
		return background;
	}
	public void setBackground(String background) {
		this.background = background;
	}
	public String getFounded() {
		return founded;
	}
	public void setFounded(String founded) {
		this.founded = founded;
	}
	
	public String getApplication() {
		return application;
	}
	public void setApplication(String application) {
		this.application = application;
	}
	public String getCaseStudy() {
		return caseStudy;
	}
	public void setCaseStudy(String caseStudy) {
		this.caseStudy = caseStudy;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	public String getProgram() {
		return program;
	}
	public void setProgram(String program) {
		this.program = program;
	}
	public String getBatch() {
		return batch;
	}
	public void setBatch(String batch) {
		this.batch = batch;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public String getInvestors() {
		return investors;
	}
	public void setInvestors(String investors) {
		this.investors = investors;
	}
	public String getNumberOfInvestors() {
		return numberOfInvestors;
	}
	public void setNumberOfInvestors(String numberOfInvestors) {
		this.numberOfInvestors = numberOfInvestors;
	}
	
}
