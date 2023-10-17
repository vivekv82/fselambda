package skilltracker.fse.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import skilltracker.fse.dto.EngineerSkillProfile;
import skilltracker.fse.dto.Result;
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

	@RequestMapping(value = "/addProfile", method = RequestMethod.POST, headers = {
			"content-type=application/json" }, consumes = {
					MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public Result addProfile(@RequestBody @Valid EngineerSkillProfile newProfile) {
		this.skillsService.addProfile(newProfile);
		return new Result(0, "", "");
	}

	@ResponseBody
	@RequestMapping(value = "/updateProfile", method = RequestMethod.POST, headers = {
			"content-type=application/json" }, consumes = {
					MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public Result updateProfile(@RequestBody @Valid EngineerSkillProfile updatedProfile) {
		this.skillsService.updateProfile(updatedProfile.getAssociateId(), updatedProfile);
		return new Result(0, "", "");
	}

}
