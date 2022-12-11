package skilltracker.fse.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import skilltracker.fse.dto.EngineerSkillProfile;
import skilltracker.fse.entity.SkillProfile;

public interface SkillsRepository extends MongoRepository<SkillProfile, String> {

	public default SkillProfile fetchProfile(String associateID) {
		Optional<SkillProfile> skillProfile = findById(associateID);
		System.out.println("Find : " + skillProfile.isPresent());
		return skillProfile.isPresent() ? skillProfile.get(): null;
	}
	
	public default SkillProfile fetchProfile(SkillProfile skillProfile) {
		String associateId = skillProfile.getAssociateId();
		findById(associateId).get();
		return findById(skillProfile.getAssociateId()).get();
	}
	
	public default List<SkillProfile> fetchAllProfiles() {
		return findAll();
	}

	public default void addProfile(EngineerSkillProfile newProfile) {
		boolean isSkillRecordPresent = isSkillRecordPresent(newProfile.getAssociateId());
		if (!isSkillRecordPresent) {
			insert(getEntityFor(newProfile));
		}
	}
	
	public default boolean isSkillRecordPresent(String associateID) {
		return existsById(associateID);
	}

	public default void updateProfile(EngineerSkillProfile updatedProfile) {
		boolean isSkillRecordPresent = isSkillRecordPresent(updatedProfile.getAssociateId());
		if (isSkillRecordPresent) {
			save(getEntityFor(updatedProfile));
		}
	}

	static SkillProfile getEntityFor(EngineerSkillProfile profile) {
		SkillProfile skillProfile = new SkillProfile(profile.getFirstName(), profile.getLastName(),
				profile.getAssociateId(), profile.getEmail(), profile.getMobile(), profile.getSkillsList());
		return skillProfile;
	}

}
