package skilltracker.fse.dto;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Builder;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EngineerSkillProfile implements Serializable {

	private static final long serialVersionUID = -1653671025419004600L;
	@NotNull
	@Size(min = 5, max = 30)
	private String firstName;

	@NotNull
	@Size(min = 5, max = 30)
	private String lastName;

	@NotNull
	@Size(min = 5, max = 30)
	@Pattern(regexp = "^CTS[0-9]*")
	@Id
	private String associateId;

	@NotNull
	@Size(min = 10, max = 10)
	private String mobile;

	@NotNull
	@Size(max = 40)
	@Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}", flags = Pattern.Flag.CASE_INSENSITIVE)
	private String email;

	@NotNull
	@Valid
	private List<SkillsExpertise> technicalSkillsList;

	@NotNull
	@Valid
	private List<SkillsExpertise> softSkillsList;

	private Date createdDate;

	private Date updatedDate;

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

	public List<SkillsExpertise> getTechnicalSkillsList() {
		return technicalSkillsList;
	}

	public void setTechnicalSkillsList(List<SkillsExpertise> technicalSkillsList) {
		this.technicalSkillsList = technicalSkillsList;
	}

	public List<SkillsExpertise> getSoftSkillsList() {
		return softSkillsList;
	}

	public void setSoftSkillsList(List<SkillsExpertise> softSkillsList) {
		this.softSkillsList = softSkillsList;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String toString() {
		return this.associateId;
	}

	public void sortSkillsExpertise() {
		Comparator<SkillsExpertise> comparator = Comparator.comparing(SkillsExpertise::getSkillExpertiseLevel);
		this.technicalSkillsList = this.technicalSkillsList.stream().sorted(comparator).collect(Collectors.toList());
		this.softSkillsList = this.softSkillsList.stream().sorted(comparator).collect(Collectors.toList());
	}

}
