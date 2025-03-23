package com.arvind.jobms;

import java.util.List;

public interface JobService {
    List<Job> findAll();
    void createJob(Job job);
    Job getJob(Long id);
    boolean updateJob(Long id,Job job);
    boolean deleteJob(Long id);
}
