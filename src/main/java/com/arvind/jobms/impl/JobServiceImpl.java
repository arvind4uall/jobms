package com.arvind.jobms.impl;

import com.arvind.jobms.Job;
import com.arvind.jobms.JobRepository;
import com.arvind.jobms.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService {
    @Autowired
    private JobRepository jobRepository;
    @Override
    public List<Job> findAll() {
        return jobRepository.findAll();
    }

    @Override
    public void createJob(Job job) {
        jobRepository.save(job);
    }

    @Override
    public Job getJob(Long id) {
        return jobRepository.findById(id).orElse(null);
    }

    @Override
    public boolean updateJob(Long id, Job jobToUpdate) {
        Optional<Job> jobOptional = jobRepository.findById(id);
        if(jobOptional.isPresent()){
            Job job = jobOptional.get();
            jobToUpdate.setId(id);
            jobRepository.save(jobToUpdate);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteJob(Long id) {
        Optional<Job> jobOptional = jobRepository.findById(id);
        if(jobOptional.isPresent()){
            jobRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
