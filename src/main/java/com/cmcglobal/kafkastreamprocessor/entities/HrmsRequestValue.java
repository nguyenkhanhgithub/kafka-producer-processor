package com.cmcglobal.kafkastreamprocessor.entities;

import com.cmcglobal.kafkastreamprocessor.entities.enums.RequestStatus;
import lombok.Data;
import org.apache.commons.lang3.RandomStringUtils;

@Data
public class HrmsRequestValue {
    private String id;
    private String request;
    private Staff requesterInfo;
    private Staff approverInfo;
    private String description;
    private String requestTypeCode;
    private String requestTypeName;
    private String requestTypeNameVi;
    private String reasonTypeName;
    private String reasonTypeNameVi;
    private String reasonTypeCode;
    private String salary;
    private String fromDate;
    private String toDate;
    private RequestStatus requestStatus;
    private String createdDate;
    private String updatedDate;
    private Integer duration;
    private Log log;

    @Data
    public static class Log {
        private String currentStatus;
        private String oldStatus;
        private String currentReason;
        private String oldReason;
    }

    @Data
    public static class Staff {
        private String name;
        private String email;
        private String phoneNumber;
        private String company;
        private String jobLevel;
        private String role;
        private String location;
        private String department;
        private String division;
        private String gender;
        private String id;
        private DepartmentName departmentName;
    }

    @Data
    public static class DepartmentName {
        private String nameVi;
        private String nameEn;
    }

    public HrmsRequestValue() {

    }
}
