package skilltracker.fse.dto;

import java.io.Serializable;
import java.util.List;

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
	private List<SkillsExpertise> skillsList;

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

	public List<SkillsExpertise> getSkillsList() {
		return skillsList;
	}

	public void setSkillsList(List<SkillsExpertise> skillsList) {
		this.skillsList = skillsList;
	}
	
	public String toString() {
		return this.associateId;
	}

}
