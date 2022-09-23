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

        //requester
        Staff requester = new Staff();
        requester.setName("Nguyễn Khánh");
        requester.setEmail("nkhanh@cmcglobal.vn");
        requester.setCompany("CMCGLOBAL");
        requester.setLocation("CGLOBAL.HN");
        requester.setJobLevel("Professional");
        requester.setRole("SE04");
        requester.setDepartment("SA");
        requester.setDivision("GLB.TECH");
        requester.setPhoneNumber("0334967729");
        requester.setGender("Male");
        requester.setId("1");

        DepartmentName departmentNameRequester = new DepartmentName();
        departmentNameRequester.setNameEn("TDX DEPT");
        departmentNameRequester.setNameVi("Phòng Công nghệ và Chuyển đổi số");
        requester.setDepartmentName(departmentNameRequester);

        // approver
        Staff approver = new Staff();
        approver.setName("Bùi Ngọc Châu");
        approver.setEmail("bnchau@cmcglobal.vn");
        approver.setCompany("CMCGLOBAL");
        approver.setLocation("CGLOBAL.HN");
        approver.setJobLevel("Professional");
        approver.setRole("SE05");
        approver.setDepartment("SA");
        approver.setDivision("GLB.TECH");
        approver.setPhoneNumber("0334967729");
        approver.setGender("Male");
        approver.setId("2");

        DepartmentName departmentNameApprover = new DepartmentName();
        departmentNameApprover.setNameEn("TDX DEPT");
        departmentNameApprover.setNameVi("Phòng Công nghệ và Chuyển đổi số");
        approver.setDepartmentName(departmentNameApprover);

        Log log = new Log();
        log.setCurrentStatus("DEFAULT");
        log.setOldStatus(null);
        log.setCurrentReason("Xác nhận");
        log.setOldReason(null);

        setId(RandomStringUtils.randomAlphabetic(10));
        setRequesterInfo(requester);
        setApproverInfo(approver);
        setRequest("Work Outside request");
        setDescription("A Châu Lead TDX đã approve và user báo HR rep user gửi Admin xử lý");
        setRequestTypeCode("WORK_OUTSIDE");
        setRequestTypeName("Work Outside request");
        setRequestTypeNameVi("Đơn xin làm việc ngoài công ty");
        setReasonTypeCode("WFH");
        setReasonTypeName("Working From Home");
        setReasonTypeNameVi("Làm việc tại nhà");
        setSalary("Yes");
        setFromDate("2021-09-27 08:30:00");
        setToDate("2021-10-01 17:30:00");
        setRequestStatus(RequestStatus.DEFAULT);
        setCreatedDate("2021-10-21 06:34:26");
        setUpdatedDate("2021-10-21 06:34:26");
        setDuration(1);
        setLog(log);
    }
}
