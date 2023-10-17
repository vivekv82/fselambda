package skilltracker.fse.service;

import java.util.List;

import skilltracker.fse.dto.EngineerSkillProfile;
import skilltracker.fse.dto.SearchSkillProfile;
import skilltracker.fse.entity.SkillProfile;

public interface SkillsService {

	public List<SkillProfile> fetchProfile(SearchSkillProfile searchProfileCriteria);

	public List<SkillProfile> fetchProfileWithId(SearchSkillProfile searchProfileCriteria);

	public List<SkillProfile> fetchAllProfiles();

	public void addProfile(EngineerSkillProfile newProfile);

	public SkillProfile fetchLoginProfile(String id);

	public void updateProfile(String associateId, EngineerSkillProfile updatedProfile);

}
