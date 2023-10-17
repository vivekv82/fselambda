package skilltracker.fse.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import skilltracker.fse.dto.Result;
import skilltracker.fse.dto.SearchSkillProfile;
import skilltracker.fse.entity.SkillProfile;
import skilltracker.fse.service.SkillsService;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {

	private SkillsService skillsService;

	public SkillsService getSkillsService() {
		return skillsService;
	}

	@Autowired
	public void setSkillsService(SkillsService skillsService) {
		this.skillsService = skillsService;
	}

	@RequestMapping(value = "/fetchProfile", method = RequestMethod.POST, headers = {
			"content-type=application/json" }, consumes = {
					MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public Result fetchProfile(@RequestBody SearchSkillProfile searchProfile) {
		List<SkillProfile> result = searchProfile.getAssociateId().isEmpty()
				? this.skillsService.fetchProfile(searchProfile)
				: this.skillsService.fetchProfileWithId(searchProfile);
		System.out.println("result" + result);
		if (result == null) {
			result = this.skillsService.fetchProfile(searchProfile);
		}
		return result.isEmpty() ? new Result(-1, "No results found for ", "") : new Result(result.toArray());
	}

	@GetMapping("/fetchAll")
	public Result fetchAllProfiles() {
		List<SkillProfile> allProfileRecords = this.skillsService.fetchAllProfiles();
		return new Result(allProfileRecords.toArray());
	}

	@RequestMapping(value = "/fetchLoginProfile", method = RequestMethod.POST, headers = {
			"content-type=application/json" }, consumes = {
					MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public Result fetchLoginProfile(@RequestBody SearchSkillProfile searchProfile) {
		SkillProfile profile = this.skillsService.fetchLoginProfile(searchProfile.getAssociateId());
		if (profile != null) {
			List<SkillProfile> skillprofile = new ArrayList<SkillProfile>();
			skillprofile.add(profile);
			return new Result(skillprofile.toArray());
		} else {
			return new Result(-1, "No results found for ", "");
		}

	}

}
