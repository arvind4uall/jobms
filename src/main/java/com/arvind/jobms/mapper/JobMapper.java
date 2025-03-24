package com.arvind.jobms.mapper;

import com.arvind.jobms.Job;
import com.arvind.jobms.dto.JobDTO;
import com.arvind.jobms.external.Company;
import com.arvind.jobms.external.Review;

import java.util.List;

public class JobMapper {
    public static JobDTO mapJobWithCompanyAndReviewDTO(Job job, Company company, List<Review> reviews){
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
}
