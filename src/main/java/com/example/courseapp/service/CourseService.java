package com.example.courseapp.service;

import com.example.courseapp.model.Course;
import com.example.courseapp.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    private final CourseRepository repo;

    public CourseService(CourseRepository repo) {
        this.repo = repo;
    }

    public List<Course> getAllCourses() {
        return repo.findAll();
    }

    public Optional<Course> getCourseById(Long id) {
        return repo.findById(id);
    }

    public List<Course> getByInstructor(String name) {
        return repo.findByInstructor(name);
    }

    public void saveCourse(Course course) {
        repo.save(course);
    }

    public void deleteCourse(Long id) {
        repo.deleteById(id);
    }
}
