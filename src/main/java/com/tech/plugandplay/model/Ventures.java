package com.tech.plugandplay.model;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.hibernate.annotations.ForeignKey;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.search.annotations.Store;
import org.ocpsoft.pretty.time.PrettyTime;

@SuppressWarnings("serial")
@Entity
@Indexed
@Table(name="ventures")
public class Ventures implements Serializable {
	
	@Id
	@GeneratedValue
	@Column(name="ID")
	private int id;
	@Column(name="TIME_STAMP")
	private String timestamp;
	@Field(index=Index.YES, analyze=Analyze.YES, store=Store.NO)
	@Column(name="COMPANY_NAME")
	private String companyName;
	@Lob
    @Column(name="PICTURE",columnDefinition="MEDIUMBLOB")
    private String thumbnail; /*50 pixels*/
	@Field(index=Index.YES, analyze=Analyze.YES, store=Store.NO)
	@Column(name="BLURB")
	private String blurb;
	@Field(index=Index.YES, analyze=Analyze.YES, store=Store.NO)
	@Column(name="VERTICALS")
	private String verticals;
	@Field(index=Index.YES, analyze=Analyze.YES, store=Store.NO)
	@Column(name="WEBSITE")
	private String website;
	@Field(index=Index.YES, analyze=Analyze.YES, store=Store.NO)
	@Column(name="PNP_CONTACT")
	private String pnpContact;
	@Field(index=Index.YES, analyze=Analyze.YES, store=Store.NO)
	@Column(name="CONTACT_NAME")
	private String contactName;
	@Field(index=Index.YES, analyze=Analyze.YES, store=Store.NO)
	@Column(name="EMAIL")
	private String email;
	@Field(index=Index.YES, analyze=Analyze.YES, store=Store.NO)
	@Column(name="PHONE_NUMBER")
	private String phoneNumber;
	@Field(index=Index.YES, analyze=Analyze.YES, store=Store.NO)
	@Column(name="TOTAL_MONEY_RAISED")
	private String totalMoneyRaised;
	@Field(index=Index.YES, analyze=Analyze.YES, store=Store.NO)
	@Column(name="STAGE")
	private String stage;
	@Field(index=Index.YES, analyze=Analyze.YES, store=Store.NO)
	@Column(name="B2B_B2C")
	private String b2bb2c;
	@Field(index=Index.YES, analyze=Analyze.YES, store=Store.NO)
	@Column(name="EMPLOYEES")
	private String employees;
	@Field(index=Index.YES, analyze=Analyze.YES, store=Store.NO)
	@Column(name="LOCATION")
	private String location;
	@Field(index=Index.YES, analyze=Analyze.YES, store=Store.NO)
	@Column(name="CITY")
	private String city;
	@Field(index=Index.YES, analyze=Analyze.YES, store=Store.NO)
	@Column(name="COMPETITION")
	private String competition;
	@Field(index=Index.YES, analyze=Analyze.YES, store=Store.NO)
	@Column(name="ADVANTAGE")
	private String advantage;
	@Field(index=Index.YES, analyze=Analyze.YES, store=Store.NO)
	@Column(name="BACKGROUND")
	private String background;
	@Field(index=Index.YES, analyze=Analyze.YES, store=Store.NO)
	@Column(name="FOUNDED")
	private String founded;
	@Field(index=Index.YES, analyze=Analyze.YES, store=Store.NO)
	@Column(name="PARTNER_INTERESTS")
	private String partnerInterests;
	@Field(index=Index.YES, analyze=Analyze.YES, store=Store.NO)
	@Column(name="CASE_STUDY")
	private String caseStudy;
	@Field(index=Index.YES, analyze=Analyze.YES, store=Store.NO)
	@Column(name="COMMENTS")
	private String comments;
	@Field(index=Index.YES, analyze=Analyze.YES, store=Store.NO)
	@Column(name="TAGS")
	private String tags;
	@Column(name="MATERIALS")
	private String materials;
	@Column(name="DATE_OF_INVESTMENT")
	private String dateOfInvestment;
	@Column(name="PORTFOLIO")
	private boolean portfolio;
	@Column(name="UPDATED")
	@Temporal(TemporalType.TIMESTAMP) /* Tells Hibernate its of time / date format */
	private Date updated;
	@IndexedEmbedded
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(cascade=CascadeType.ALL, orphanRemoval=true)
    @JoinColumn(name="listName")
    @ForeignKey(name = "top100")
	private List<Top100> top100;
	@IndexedEmbedded
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(cascade=CascadeType.ALL, orphanRemoval=true)
    @JoinColumn(name="listNameTop20")
    @ForeignKey(name = "top20")
	private List<Top20> top20;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getBlurb() {
		return blurb;
	}
	public void setBlurb(String blurb) {
		this.blurb = blurb;
	}
	public String getVerticals() {
		return verticals;
	}
	public void setVerticals(String verticals) {
		this.verticals = verticals;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getPnpContact() {
		return pnpContact;
	}
	public void setPnpContact(String pnpContact) {
		this.pnpContact = pnpContact;
	}
	public String getContactName() {
		return contactName;
	}
	public void setContactName(String contactName) {
		this.contactName = contactName;
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
	public String getB2bb2c() {
		return b2bb2c;
	}
	public void setB2bb2c(String b2bb2c) {
		this.b2bb2c = b2bb2c;
	}
	public String getEmployees() {
		return employees;
	}
	public void setEmployees(String employees) {
		this.employees = employees;
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
	public String getPartnerInterests() {
		return partnerInterests;
	}
	public void setPartnerInterests(String partnerInterests) {
		this.partnerInterests = partnerInterests;
	}
	public String getCaseStudy() {
		return caseStudy;
	}
	public void setCaseStudy(String caseStudy) {
		this.caseStudy = caseStudy;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
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
	public String getDateOfInvestment() {
		return dateOfInvestment;
	}
	public void setDateOfInvestment(String dateOfInvestment) {
		this.dateOfInvestment = dateOfInvestment;
	}
	public boolean isPortfolio() {
		return portfolio;
	}
	public void setPortfolio(boolean portfolio) {
		this.portfolio = portfolio;
	}
	public String getUpdated(){
        PrettyTime t = new PrettyTime();
        String timestamp = t.format(updated);
        return timestamp;
    }
	public void setUpdated(Date updated){
        this.updated = updated;
    }
	public List<Top100> getTop100() {
		return top100;
	}
	public void setTop100(List<Top100> top100) {
		this.top100 = top100;
	}
	public void addTop100(Top100 addme){
		top100.add(addme);
	}
	public void removeTop100(Top100 removeme){
		for(Top100 list: top100){
			if(list.getListName().equalsIgnoreCase(removeme.getListName())){
				top100.remove(list);
				return;
			}
		}
	}
	
	public List<Top20> getTop20() {
		return top20;
	}
	public void setTop20(List<Top20> top20) {
		this.top20 = top20;
	}
	public void addTop20(Top20 addme){
		top20.add(addme);
	}
	public void removeTop20(Top20 removeme){
		for(Top20 list: top20){
			if(list.getListName().equalsIgnoreCase(removeme.getListName())){
				top20.remove(list);
				return;
			}
		}
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(BufferedImage thumbnail, String format) throws IOException {
	        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	        ImageIO.write(thumbnail, format, outputStream);
	        InputStream stream = new ByteArrayInputStream(outputStream.toByteArray());
	        byte[] bytes = IOUtils.toByteArray(stream);
            this.thumbnail = Base64.encodeBase64String(bytes);
	}
	
}