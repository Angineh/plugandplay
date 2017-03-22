package com.tech.plugandplay.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="ventures")
public class VenturesOld implements Serializable {
	
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
	@Column(name="EMAIL")
	private String email;
	@Column(name="PHONE_NUMBER")
	private String phoneNumber;
	@Column(name="TOTAL_MONEY_RAISED")
	private String totalMoneyRaised;
	@Column(name="STAGE")
	private String stage;
	@Column(name="BUSINESS_MODEL")
	private String businessModel;
	@Column(name="EMPLOYEE_COUNT")
	private String employeeCount;
	@Column(name="LOCATION")
	private String location;
	@Column(name="CITY")
	private String city;
	@Column(name="COMPETITION")
	private String competition;
	@Column(name="ADVANTAGE")
	private String advantage;
	@Column(name="BACKGROUND")
	private String background;
	@Column(name="FOUNDED")
	private String founded;
	@Column(name="INDUSTRY")
	private String industry;
	@Column(name="CASE_STUDY")
	private String caseStudy;
	@Column(name="NOTES")
	private String notes;
	@Column(name="TAGS")
	private String tags;
	@Column(name="MATERIALS")
	private String materials;
	@Column(name="PROGRAM")
	private String program;
	@Column(name="BATCH")
	private String batch;
	@Column(name="INVEST")
	private String invest;
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
	public String getBusinessModel() {
		return businessModel;
	}
	public void setBusinessModel(String businessModel) {
		this.businessModel = businessModel;
	}
	public String getEmployeeCount() {
		return employeeCount;
	}
	public void setEmployeeCount(String employeeCount) {
		this.employeeCount = employeeCount;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCompetition() {
		return competition;
	}
	public void setCompetition(String competition) {
		this.competition = competition;
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
	public String getIndustry() {
		return industry;
	}
	public void setIndustry(String industry) {
		this.industry = industry;
	}
	public String getCaseStudy() {
		return caseStudy;
	}
	public void setCaseStudy(String caseStudy) {
		this.caseStudy = caseStudy;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	public String getMaterials() {
		return materials;
	}
	public void setMaterials(String materials) {
		this.materials = materials;
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
	public String getInvest() {
		return invest;
	}
	public void setInvest(String invest) {
		this.invest = invest;
	}
	
	
	

}
