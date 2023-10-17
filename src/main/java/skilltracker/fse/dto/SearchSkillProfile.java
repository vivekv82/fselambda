package skilltracker.fse.dto;

import java.io.Serializable;

import javax.validation.Valid;
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
public class SearchSkillProfile implements Serializable {

	private static final long serialVersionUID = 1125182670838615797L;

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
	@Valid
	private String technicalSkillName;

	@NotNull
	@Valid
	private String softSkillName;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAssociateId() {
		return associateId;
	}

	public void setAssociateId(String associateId) {
		this.associateId = associateId;
	}

	public String getTechnicalSkillName() {
		return technicalSkillName;
	}

	public void setTechnicalSkillName(String technicalSkillName) {
		this.technicalSkillName = technicalSkillName;
	}

	public String getSoftSkillName() {
		return softSkillName;
	}

	public void setSoftSkillName(String softSkillName) {
		this.softSkillName = softSkillName;
	}

	public String toString() {
		return this.associateId;
	}

}
