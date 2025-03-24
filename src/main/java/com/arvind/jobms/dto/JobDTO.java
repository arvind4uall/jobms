package com.arvind.jobms.dto;

import com.arvind.jobms.external.Company;
import lombok.Data;

@Data
public class JobDTO {
    private Long id;
    private String title;
    private String description;
    private String minSalary;
    private String maxSalary;
    private String location;
    private Company company;
}
