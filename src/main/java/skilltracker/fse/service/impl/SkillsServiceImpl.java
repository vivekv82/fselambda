package skilltracker.fse.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import skilltracker.fse.dao.SkillsRepository;
import skilltracker.fse.dto.EngineerSkillProfile;
import skilltracker.fse.dto.SearchSkillProfile;
import skilltracker.fse.entity.SkillProfile;
import skilltracker.fse.service.SkillsService;

@Service
@CacheConfig(cacheNames = "skills-cache")
public class SkillsServiceImpl implements SkillsService {

	@Autowired
	private SkillsRepository skillsRepository;

	@Autowired
	private JmsTemplate jmsTemplate;

	@Value("${skill.queue}")
	private String skillQueue;

	public List<SkillProfile> fetchProfile(SearchSkillProfile searchProfileCriteria) {
		System.out.println("firstname " + searchProfileCriteria.getFirstName());
		System.out.println("lastname " + searchProfileCriteria.getLastName());
		System.out.println("associateid " + searchProfileCriteria.getAssociateId());
		System.out.println("techskillname " + searchProfileCriteria.getTechnicalSkillName());
		System.out.println("softskillname " + searchProfileCriteria.getSoftSkillName());

		System.out.println("Calling fetchProfile");
		return this.skillsRepository.fetchProfile(searchProfileCriteria.getFirstName(),
				searchProfileCriteria.getLastName(), searchProfileCriteria.getTechnicalSkillName(),
				searchProfileCriteria.getSoftSkillName());

	}

	@Cacheable
	public List<SkillProfile> fetchProfileWithId(SearchSkillProfile searchProfileCriteria) {
		System.out.println("firstname " + searchProfileCriteria.getFirstName());
		System.out.println("lastname " + searchProfileCriteria.getLastName());
		System.out.println("associateid " + searchProfileCriteria.getAssociateId());
		System.out.println("techskillname " + searchProfileCriteria.getTechnicalSkillName());
		System.out.println("softskillname " + searchProfileCriteria.getSoftSkillName());
		System.out.println("Calling fetchProfileWithId");
		return this.skillsRepository.fetchProfileWithId(searchProfileCriteria.getFirstName(),
				searchProfileCriteria.getLastName(), searchProfileCriteria.getAssociateId(),
				searchProfileCriteria.getTechnicalSkillName(), searchProfileCriteria.getSoftSkillName());
	}

	public List<SkillProfile> fetchAllProfiles() {
		return this.skillsRepository.fetchAllProfiles();
	}

	public void addProfile(EngineerSkillProfile newProfile) {
		System.out.println("userQueue and newProfile = " + skillQueue + newProfile.toString());
		newProfile.sortSkillsExpertise();
		jmsTemplate.convertAndSend(skillQueue, newProfile);
	}

	@CachePut(key = "#associateId")
	public void updateProfile(String associateId, EngineerSkillProfile updatedProfile) {
		updatedProfile.sortSkillsExpertise();
		this.skillsRepository.updateProfile(updatedProfile);
	}

	private List<SkillProfile> processOutput(SkillProfile skillProfile) {
		List<SkillProfile> result = new ArrayList<SkillProfile>();
		if (skillProfile != null)
			result.add(skillProfile);
		return result;
	}

	@Override
	public SkillProfile fetchLoginProfile(String id) {
		Optional<SkillProfile> skillProfile = this.skillsRepository.findById(id);
		return skillProfile.isPresent() ? skillProfile.get() : null;
	}

}
