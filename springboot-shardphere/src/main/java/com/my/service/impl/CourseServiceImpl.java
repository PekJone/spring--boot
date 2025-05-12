package com.my.service.impl;

import com.my.entity.Course;
import com.my.mapper.CourseMapper;
import com.my.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 王朋飞
 * @version 1.0
 * @date 2025-05-12  16:24
 */
@Service
public class CourseServiceImpl implements CourseService {
    private CourseMapper courseMapper;
    @Autowired
    public void setCourseMapper(CourseMapper courseMapper) {
        this.courseMapper = courseMapper;
    }

    @Override
    public void addCourse(Course course) {
          courseMapper.insert(course);
    }
}
