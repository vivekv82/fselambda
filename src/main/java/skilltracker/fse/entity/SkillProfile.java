package skilltracker.fse.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import skilltracker.fse.dto.SkillsExpertise;

@Document(collection = "FSESkillProfiles")
public class SkillProfile implements Serializable {

	private static final long serialVersionUID = 6220741624813720241L;

	private String firstName;

	private String lastName;

	@Id
	private String associateId;

	private String mobile;

	private String email;

    private List<Expertise> technicalSkillsList;
	
	private List<Expertise> softSkillsList;
	
	private Date createdDate;
	
	private Date updatedDate;
	
	public SkillProfile(String firstName, String lastName, String associateId, String email, String mobile, List<SkillsExpertise> technicalSkillsList, List<SkillsExpertise> softSkillsList) {
		this.associateId = associateId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.mobile = mobile;
		this.technicalSkillsList = getExpertiseList(technicalSkillsList);
		this.softSkillsList = getExpertiseList(softSkillsList);
		this.createdDate = new Date();
	}
	
	public SkillProfile(String firstName, String lastName, String associateId, String email, String mobile, List<SkillsExpertise> technicalSkillsList, List<SkillsExpertise> softSkillsList, Date createdDate, Date updatedDate) {
		this.associateId = associateId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.mobile = mobile;
		this.technicalSkillsList = getExpertiseList(technicalSkillsList);
		this.softSkillsList = getExpertiseList(softSkillsList);
		this.createdDate = new Date();
	}
	
	public SkillProfile() {
	}

	private List<Expertise> getExpertiseList(List<SkillsExpertise> list) {
		List<Expertise> expertiseList = new ArrayList<Expertise>();
		list.forEach(skillsExpertise -> {
			expertiseList.add(getExpertise(skillsExpertise));
		});
		return expertiseList;
	}
	
	private Expertise getExpertise(SkillsExpertise skillsExpertise) {
		Expertise expertise = new Expertise(skillsExpertise.getSkillName(), skillsExpertise.getSkillExpertiseLevel());
		return expertise;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getAssociateId() {
		return associateId;
	}

	public void setAssociateId(String associateId) {
		this.associateId = associateId;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public List<Expertise> getTechnicalSkillsList() {
		return technicalSkillsList;
	}

	public void setTechnicalSkillsList(List<Expertise> technicalSkillsList) {
		this.technicalSkillsList = technicalSkillsList;
	}

	public List<Expertise> getSoftSkillsList() {
		return softSkillsList;
	}

	public void setSoftSkillsList(List<Expertise> softSkillsList) {
		this.softSkillsList = softSkillsList;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

}
