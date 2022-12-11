package skilltracker.fse.service;

import skilltracker.fse.dto.EngineerSkillProfile;

public interface SkillMQConsumer {

	public void addProfile(EngineerSkillProfile newProfile);
}
