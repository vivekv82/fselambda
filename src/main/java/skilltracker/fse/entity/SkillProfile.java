package skilltracker.fse.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import skilltracker.fse.dto.SkillsExpertise;

@Document(collection = "Skillprofile")
public class SkillProfile implements Serializable {

	private static final long serialVersionUID = 6220741624813720241L;

	private String firstName;

	private String lastName;

	@Id
	private String associateId;

	private String mobile;

	private String email;

	private List<Expertise> skillsList;
	
	public SkillProfile(String firstName, String lastName, String associateId, String email, String mobile, List<SkillsExpertise> list) {
		this.associateId = associateId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.mobile = mobile;
		this.skillsList = getExpertiseList(list);
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

	public List<Expertise> getSkillsList() {
		return skillsList;
	}

	public void setSkillsList(List<Expertise> skillsList) {
		this.skillsList = skillsList;
	}

}
