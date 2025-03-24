package com.arvind.jobms;

import com.arvind.jobms.dto.JobDTO;
import com.arvind.jobms.impl.JobServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {
    @Autowired
    private JobServiceImpl jobServiceImpl;
    @GetMapping
    private ResponseEntity<List<JobDTO>> getJobs(){
        List<JobDTO> jobs = jobServiceImpl.findAll();
        return ResponseEntity.ok(jobs);
    }
    @PostMapping
    private ResponseEntity<String> createJob(@RequestBody Job job){
        jobServiceImpl.createJob(job);
        return ResponseEntity.status(HttpStatus.CREATED).body("Job created!!");
    }
    @GetMapping("/{id}")
    private ResponseEntity<JobDTO> getJob(@PathVariable Long id){
        JobDTO jobDTO = jobServiceImpl.getJob(id);
        if(jobDTO!=null)
            return new ResponseEntity<>(jobDTO,HttpStatus.OK);
        return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
    }
    @PutMapping("/{id}")
    private ResponseEntity<String> updateJob(@PathVariable Long id, @RequestBody Job job){
        boolean isUpdated = jobServiceImpl.updateJob(id,job);
        if(isUpdated)
            return ResponseEntity.ok("Job updated successfully!!");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Job not found!!!");
    }
    @DeleteMapping("/{id}")
    private ResponseEntity<String> deleteJob(@PathVariable Long id){
        boolean isDeleted = jobServiceImpl.deleteJob(id);
        if(isDeleted)
            return new ResponseEntity<>("Job deleted successfully!!!",HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
