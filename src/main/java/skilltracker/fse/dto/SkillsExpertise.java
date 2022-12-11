package skilltracker.fse.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Builder;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SkillsExpertise implements Serializable {

	private static final long serialVersionUID = -1264756693651108391L;
	
	//@NotNull
	//@NotBlank
	private String skillName;
	
	//@NotNull
	//@Min(value = 0)
	//@Max(value = 20)
	private Integer skillExpertiseLevel;
	
	public String getSkillName() {
		return skillName;
	}
	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}
	public Integer getSkillExpertiseLevel() {
		return skillExpertiseLevel;
	}
	public void setSkillExpertiseLevel(Integer skillExpertiseLevel) {
		this.skillExpertiseLevel = skillExpertiseLevel;
	}
	
}
