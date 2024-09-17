package com.hei.absencemanager.Controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class endpoint {
    @GetMapping("/students")
    public void getAllStudents() {
    }
}
