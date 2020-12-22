package com.demo1.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/names")
public class NamesControllers {
    public NamesControllers(){}

    @GetMapping("/{id}")
    public String getUserName(@PathVariable String id) {
        String[] names = {"Name0", "Name1", "Name2"};
        return names[Integer.parseInt(id)];
    }
}
