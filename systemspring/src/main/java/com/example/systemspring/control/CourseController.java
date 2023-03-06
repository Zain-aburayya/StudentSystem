package com.example.systemspring.control;

import com.example.systemspring.model.Course;
import com.example.systemspring.service.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
@RequestMapping(path = "student")
public class CourseController {
    DatabaseService databaseService;

    @Autowired
    public CourseController(DatabaseService databaseService){
        this.databaseService = databaseService;
    }

    @GetMapping("{id}/marks")
    public String getStudentMarks(@PathVariable("id") Integer id, Model model) {
        ArrayList<Course> courses = databaseService.getAllStudentMarks(id);
        model.addAttribute("courses", courses);
        return "student-marks";
    }

    @GetMapping("{id}/mark")
    public String getSingleMarkForm(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("id", id);
        model.addAttribute("route", "mark");
        model.addAttribute("mark", new Course());
        return "courses-form";
    }

    @PostMapping("{id}/mark")
    public String getSingleMarkResult(@ModelAttribute Course mark, @PathVariable("id") Integer id, Model model) {
        Course courseMark = databaseService.getStudentMark(id, mark.getName());
        model.addAttribute("Mark" , courseMark);
        model.addAttribute("text" , "");
        return "result-form";
    }
    @PostMapping("{id}/average")
    public String getAvgResult(@ModelAttribute Course mark, @PathVariable int id,  Model model) {
        Course courseMark = databaseService.getAvg(mark.getName());
        System.out.println(courseMark);
        model.addAttribute("Mark" , courseMark);
        model.addAttribute("text" , "Average");
        return "result-form";
    }
    @GetMapping("{id}/average")
    public String getAvgForm(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("id", id);
        model.addAttribute("route", "average");
        model.addAttribute("mark", new Course());
        return "courses-form";
    }
    @GetMapping("{id}/max")
    public String getMaxForm(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("id", id);
        model.addAttribute("route", "max");
        model.addAttribute("mark", new Course());
        return "courses-form";
    }

    @PostMapping("{id}/max")
    public String getMaxResult(@ModelAttribute Course mark, @PathVariable int id,  Model model) {
        Course courseMark = databaseService.getMax(mark.getName());
        model.addAttribute("Mark" , courseMark);
        model.addAttribute("text" , "Maximum");
        return "result-form";
    }

    @GetMapping("{id}/min")
    public String getMinForm(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("id", id);
        model.addAttribute("route", "min");
        model.addAttribute("mark", new Course());
        return "courses-form";
    }

    @PostMapping("{id}/min")
    public String getMinResult(@ModelAttribute Course mark, @PathVariable int id,  Model model) {
        Course courseMark = databaseService.getMin(mark.getName());
        model.addAttribute("Mark" , courseMark);
        model.addAttribute("text" , "Minimum");
        return "result-form";
    }

    @GetMapping("{id}/median")
    public String getMedianForm(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("id", id);
        model.addAttribute("route", "median");
        model.addAttribute("mark", new Course());
        return "courses-form";
    }

    @PostMapping("{id}/median")
    public String getMedianResult(@ModelAttribute Course mark, @PathVariable int id,  Model model) {
        Course courseMark = databaseService.getMedian(mark.getName());
        model.addAttribute("Mark" , courseMark);
        model.addAttribute("text" , "Median");
        return "result-form";
    }
}
