package com.spring.backend.app;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Api(value = "StatControllers",tags = "StatControllers")
@RestController
@RequestMapping("/api/stats")
public class StatControllers {

    @Autowired
    StatRepository StatRepo;

    public StatControllers() {};

    @ApiOperation(value = "Stat information for the user in the System ", response = String.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 401, message = "Not authorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not found") })
    @GetMapping("/{username}")
    public Optional<StatInfo> getStat(@PathVariable String username) {
        return StatRepo.findById(username);
    }

    @ApiOperation(value = "Put new statistic for the user", response = String.class)
    @PostMapping("/newStat")
    public StatInfo addFiles(@RequestBody StatInfo files) {
        StatRepo.save(files);
        return files;
    }
}
