package com.my.controller;

import com.my.entity.Course;
import com.my.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 王朋飞
 * @version 1.0
 * @date 2025-05-12  16:27
 */
@RestController
@RequestMapping("/course")
public class CourseController {

    private CourseService courseService;
    @Autowired
    public void setCourseService(CourseService courseService) {
        this.courseService = courseService;
    }
    @GetMapping("/add")
    public void addCourse(){
        for(int i=0;i<10;i++){
            Course course = new Course();
            course.setCstatus("1");
            course.setCname("java");
            course.setUserId(100L);
            courseService.addCourse(course);
        }
    }
}
