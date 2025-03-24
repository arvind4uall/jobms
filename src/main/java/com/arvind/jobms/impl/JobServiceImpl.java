package com.arvind.jobms.impl;

import com.arvind.jobms.AppConfig;
import com.arvind.jobms.Job;
import com.arvind.jobms.JobRepository;
import com.arvind.jobms.JobService;
import com.arvind.jobms.dto.JobDTO;
import com.arvind.jobms.external.Company;
import com.arvind.jobms.external.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JobServiceImpl implements JobService {
    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private AppConfig appConfig;
    @Override
    public List<JobDTO> findAll() {
        List<Job> jobs = jobRepository.findAll();
        return jobs.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public void createJob(Job job) {
        jobRepository.save(job);
    }

    private JobDTO convertToDto(Job job){
        RestTemplate restTemplate = appConfig.restTemplate();
        Company company = restTemplate.getForObject("http://companyms/companies/"+job.getCompanyId(),Company.class);
        ResponseEntity<List<Review>> reviewResponse = restTemplate.exchange("http://reviewms/reviews?companyId=" + job.getCompanyId(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Review>>() {
                });
        List<Review> reviews = reviewResponse.getBody();
        JobDTO jobDTO = new JobDTO();
        jobDTO.setId(job.getId());
        jobDTO.setTitle(job.getTitle());
        jobDTO.setDescription(job.getDescription());
        jobDTO.setMinSalary(job.getMinSalary());
        jobDTO.setMaxSalary(job.getMaxSalary());
        jobDTO.setLocation(job.getLocation());
        jobDTO.setCompany(company);
        jobDTO.setReviews(reviews);
        return jobDTO;
    }

    @Override
    public JobDTO getJob(Long id) {
        Job job = jobRepository.findById(id).orElse(null);
        if(job!=null){
            return convertToDto(job);
        }
        return null;
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
