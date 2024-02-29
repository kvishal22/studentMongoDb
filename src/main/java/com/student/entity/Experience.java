package com.student.entity;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class Experience {

    private String userDesignation;
    private String userOrganizationName;
    private String userOrganizationLocation;
    private LocalDate userOrganizationFromDate;
    private LocalDate userOrganizationToDate;
    private boolean isPresent;

}
