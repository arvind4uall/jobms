package com.arvind.jobms;

import com.arvind.jobms.dto.JobDTO;

import java.util.List;

public interface JobService {
    List<JobDTO> findAll();
    void createJob(Job job);
    JobDTO getJob(Long id);
    boolean updateJob(Long id,Job job);
    boolean deleteJob(Long id);
}
