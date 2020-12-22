package com.spring.backend.app;

import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Api(tags = "UserFiles Controller")
@RestController
@RequestMapping("/api/files")
public class UserFilesControllers {

    @Autowired
    UserFilesRepository userFilesRepo;

    public UserFilesControllers(){}

    @ApiOperation(value = "Get user files for specific user in system." , response = UserFiles.class)
    @GetMapping("/{username}")
    public Optional<UserFiles> getUserFiles(@PathVariable String username) {
        return userFilesRepo.findById(username);
    }
 /*
 {
 Example JSON to pass in as request body:
    "username": "tyler",
    "projects": "{\"name\": \"myPythonProject\", \"startDate\": \"Sep 14, 2020 4:50:01 PM\"}",
    "skills": "{\"groupTag\": \"languages\", \"skillTag\": \"python\", \"resources\": [{\"link\": \"codingbat.com\", \"name\": \"codepython\"}]}",
    "habits": "{\"name\": \"die twice a day\"}",
    "settings": "{\"theme\": \"darktheme\"}"
}
  */
    @ApiOperation(value = "Update user files for specific user in system.", response = UserFiles.class)
    @PostMapping("/newfiles")
    public UserFiles addFiles(@RequestBody UserFiles files) {
        userFilesRepo.save(files);
        return files;
    }

//    @PostMapping("/newskill")
//    public UserFiles addUserAndFiles() {
//        Skills.LearningResources[] lr = {new Skills.LearningResources("codepython", "codingbat.com")};
//        Projects project = new Projects("myPythonProject", new Date());
//        Skills skill = new Skills("python", "languages", lr);
//        Habits habit = new Habits("die twice a day");
//        Settings setting = new Settings("darktheme");
//        UserFiles files = new UserFiles("marita", project, skill, setting, habit);
//        userFilesRepo.save(files);
//        return files;
//    }
}
