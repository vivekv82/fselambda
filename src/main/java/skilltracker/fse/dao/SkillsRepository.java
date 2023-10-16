package skilltracker.fse.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import skilltracker.fse.dto.EngineerSkillProfile;
import skilltracker.fse.entity.SkillProfile;

public interface SkillsRepository extends MongoRepository<SkillProfile, String> {

	@Query("{ 'firstName': /?0/i, 'lastName': /?1/i, $or: [  { 'technicalSkillsList': { $elemMatch: { 'skillName': ?2, 'skillExpertiseLevel' : { $gte: 10 } } } }, { 'softSkillsList': { $elemMatch: { 'skillName': ?3, 'skillExpertiseLevel' : { $gte: 10 } } } } ] }")
	public List<SkillProfile> fetchProfile(String firstName, String lastName, String technicalSkillName,
			String softSkillName);

	@Query("{ 'firstName': {$regex : /?0/i}, 'lastName': {$regex : /?1/i}, '_id': ?2, $or: [ { 'technicalSkillsList': { $elemMatch: { 'skillName': ?3, 'skillExpertiseLevel' : { $gte: 10 } } } }, { 'softSkillsList': { $elemMatch: { 'skillName': ?4, 'skillExpertiseLevel' : { $gte: 10 } } } } ] }")
	public List<SkillProfile> fetchProfileWithId(String firstName, String lastName, String associateId,
			String technicalSkillName, String softSkillName);

	public default SkillProfile fetchProfile(SkillProfile skillProfile) {
		return findById(skillProfile.getAssociateId()).get();
	}

	public default List<SkillProfile> fetchAllProfiles() {
		return findAll();
	}

	public default void addProfile(EngineerSkillProfile newProfile) {
		boolean isSkillRecordPresent = isSkillRecordPresent(newProfile.getAssociateId());
		if (!isSkillRecordPresent) {
			insert(getEntityFor(newProfile, true));
		}
	}

	public default boolean isSkillRecordPresent(String associateID) {
		return existsById(associateID);
	}

	public default void updateProfile(EngineerSkillProfile updatedProfile) {
		boolean isSkillRecordPresent = isSkillRecordPresent(updatedProfile.getAssociateId());
		if (isSkillRecordPresent) {
			save(getEntityFor(updatedProfile, false));
		}
	}

	static SkillProfile getEntityFor(EngineerSkillProfile profile, boolean isNew) {
		return isNew
				? new SkillProfile(profile.getFirstName(), profile.getLastName(), profile.getAssociateId(),
						profile.getEmail(), profile.getMobile(), profile.getTechnicalSkillsList(),
						profile.getSoftSkillsList(), new Date(), new Date())
				: new SkillProfile(profile.getFirstName(), profile.getLastName(), profile.getAssociateId(),
						profile.getEmail(), profile.getMobile(), profile.getTechnicalSkillsList(),
						profile.getSoftSkillsList(), profile.getCreatedDate(), new Date());
	}

}
