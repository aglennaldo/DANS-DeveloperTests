package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.Entity.JobList;
import com.example.demo.Service.JobListService;

@Controller
public class JobListController {

    private final JobListService jobListService;

    @Autowired
    public JobListController(JobListService jobListService) {
        this.jobListService = jobListService;
    }

    @GetMapping("/joblists")
    public String getJobListData( Model model) {
        String apiUrl = "http://dev3.dansmultipro.co.id/api/recruitment/positions.json";
        List<JobList> jobList = jobListService.getJobListFromAPI(apiUrl);
        model.addAttribute("jobList", jobList);
        return "display";
    }

    
    @GetMapping("/jobdetail/{id}")
    public String getJobDetailData(@PathVariable String id,Model model) {
        String apiUrl = "http://dev3.dansmultipro.co.id/api/recruitment/positions/" +id;
        JobList jobDetail = jobListService.getJobFromAPI(apiUrl);
        model.addAttribute("jobDetail", jobDetail);
        return "displayDetail";
    }
}

