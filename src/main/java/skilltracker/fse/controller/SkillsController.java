package skilltracker.fse.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import skilltracker.fse.dto.EngineerSkillProfile;
import skilltracker.fse.dto.Result;
import skilltracker.fse.entity.SkillProfile;
import skilltracker.fse.service.SkillsService;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/engineer")
public class SkillsController {

	private SkillsService skillsService;

	public SkillsService getSkillsService() {
		return skillsService;
	}

	@Autowired
	public void setSkillsService(SkillsService skillsService) {
		this.skillsService = skillsService;
	}

	@RequestMapping(value = "/fetchProfile/{id}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public Result fetchProfile(@PathVariable String id) {
		List<SkillProfile> result = this.skillsService.fetchProfile(id);
		return result.isEmpty() ? new Result("-1", "No results found for " + id , "") : new Result(result.toArray());
	}
	
	@GetMapping("/fetchAll")
	public Result fetchAllProfiles() {
		List<SkillProfile> allProfileRecords = this.skillsService.fetchAllProfiles();
		return new Result(allProfileRecords.toArray());
	}

	@RequestMapping(value = "/addProfile", method = RequestMethod.POST, headers = {
			"content-type=application/json" }, consumes = {
					MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public Result addProfile(@RequestBody @Valid EngineerSkillProfile newProfile) {
		this.skillsService.addProfile(newProfile);
		return new Result("0", "", "");
	}

	@ResponseBody
	@RequestMapping(value = "/updateProfile", method = RequestMethod.POST, headers = {
			"content-type=application/json" }, consumes = {
					MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public Result updateProfile(@RequestBody @Valid EngineerSkillProfile updatedProfile) {
		this.skillsService.updateProfile(updatedProfile.getAssociateId(), updatedProfile);
		return new Result("0", "", "");
	}

}
