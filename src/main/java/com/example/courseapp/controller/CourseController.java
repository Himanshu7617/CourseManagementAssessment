package com.example.courseapp.controller;

import com.example.courseapp.model.Course;
import com.example.courseapp.service.CourseService;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CourseController {

    private final CourseService service;

    public CourseController(CourseService service) {
        this.service = service;
    }



 
    @GetMapping("/course/{id}")
    public String getCourse(@PathVariable Long id, Model model) {
        model.addAttribute("course", service.getCourseById(id).orElse(null));
        return "update-course";
    }

    @GetMapping("/courses/instructor/{name}")
    public String getByInstructor(@PathVariable String name, Model model) {
        model.addAttribute("courses", service.getByInstructor(name));
        return "courses";
    }

    @GetMapping("/course/new")
    public String showAddForm(Model model) {
        model.addAttribute("course", new Course());
        return "add-course";
    }

    @PostMapping("/course")
    public String addCourse(@ModelAttribute Course course) {
        service.saveCourse(course);
        return "redirect:/courses";
    }

    @PostMapping("/course/{id}")
    public String updateCourse(@PathVariable Long id, @ModelAttribute Course course) {
        course.setId(id);
        service.saveCourse(course);
        return "redirect:/courses";
    }

    @GetMapping("/courses")
    public String viewCourses(@RequestParam(required = false) String instructor, Model model) {
        List<Course> list;
        if (instructor != null && !instructor.isEmpty()) {
            list = service.getCoursesByInstructor(instructor);
        } else {
            list = service.getAllCourses();  // or repo.findAll()
        }
        model.addAttribute("courses", list);
        model.addAttribute("instructorFilter", instructor);
        return "courses";
    }
    @GetMapping("/course/delete/{id}")
    public String deleteCourse(@PathVariable Long id) {
        service.deleteCourse(id);
        return "redirect:/courses";
    }
}
