package skilltracker.fse.service;

import java.util.List;

import skilltracker.fse.dto.EngineerSkillProfile;
import skilltracker.fse.entity.SkillProfile;

public interface SkillsService {

	
	public List<SkillProfile> fetchProfile(String associateId);
	
	public List<SkillProfile> fetchAllProfiles();

	public void addProfile(EngineerSkillProfile newProfile);
	
	public void updateProfile(String associateId, EngineerSkillProfile updatedProfile);
	
}
