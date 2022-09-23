package com.cmcglobal.kafkastreamprocessor.entities;

import lombok.Data;

@Data
public class JiraTicketInfo {

    private Project project;
    private String issueType; //PC-LAPTOP,
    private Request request;
    private String ticketId; //ITS-7709
    private Staff reporter;
    private WorkFlow currentWorkFlow;
    private WorkFlow oldWorkFlow;
    private String location;
    private String summary;
    private String description;
    private String priority; //LOWEST, LOW, MEDIUM, HIGH, HIGHEST
    private String dueDate; //yyyy-mm-dd hh:mm:ss
    private String createdDate; //yyyy-mm-dd hh:mm:ss
    private String updatedDate; //yyyy-mm-dd hh:mm:ss
    private String ticketUrl; //https://pms.cmcglobal.com.vn/browse/ITS-10921


    @Data
    public static class Staff {
        private String email;
        private String name;
        private String id;
        private String phoneNumber;
        private String location; //CGLOBAL.HN
        private String role; //SE04
        private DepartmentName departmentName;
    }

    @Data
    public static class DepartmentName {
        private String nameVi; //TDX DEPT
        private String nameEn; //Phòng Công nghệ và Chuyển đổi số
    }

    @Data
    public static class Log {
        private String oldStatus; //TODO
        private String currentStatus; //INPROGRESS
    }

    @Data
    public static class Request {
        private String code; // id: 11719,
        private String name; //name: Install Operation System, Format PC
    }

    @Data
    public static class Project {
        private String code; //code: ITS, ADS,...;
        private String name; // name: Admin Service
    }

    @Data
    public static class WorkFlow {
        private Staff staff;
        private String status; //CREATE
        private String step; //CREATED_ADVANCED_REQUEST
    }

    public JiraTicketInfo () {
        Project project = new Project();
        project.setCode("ITS");
        project.setName("IT Service");
        setProject(project);
        setIssueType("PC-LAPTOP");

        Request request = new Request();
        request.setCode("11719");
        request.setName("Install Operation System, Format PC");
        setRequest(request);
        setTicketId("ITS-7709");

        Staff reporter = new Staff();
        DepartmentName departmentNameReporter = new DepartmentName();
        departmentNameReporter.setNameEn("TDX DEPT");
        departmentNameReporter.setNameVi("Phòng Công nghệ và Chuyển đổi số");

        reporter.setEmail("nkhanh@cmcglobal.vn");
        reporter.setName("Nguyễn Khánh");
        reporter.setId("123");
        reporter.setPhoneNumber("0334967729");
        reporter.setDepartmentName(departmentNameReporter);

        setReporter(reporter);

        WorkFlow currentWorkFlow = new WorkFlow();
        DepartmentName departmentNameStaff = new DepartmentName();
        departmentNameStaff.setNameEn("TDX DEPT");
        departmentNameStaff.setNameVi("Phòng Công nghệ và Chuyển đổi số");

        Staff staff = new Staff();
        staff.setEmail("nkhanh@cmcglobal.vn");
        staff.setName("Nguyễn Khánh");
        staff.setId("123");
        staff.setPhoneNumber("0334967728");
        staff.setDepartmentName(departmentNameStaff);

        currentWorkFlow.setStatus("CREATE");
        currentWorkFlow.setStep("CREATED_ADVANCED_REQUEST");
        currentWorkFlow.setStaff(staff);

        setCurrentWorkFlow(currentWorkFlow);
        setOldWorkFlow(null);
        setLocation("Tầng 7");
        setSummary("Request nâng cấu hình server");
        setDescription("Request nâng cấu hình server");
        setPriority("HIGHEST");
        setDueDate("2022-03-03 15:30:00");
        setCreatedDate("2022-03-02 15:30:00");
        setUpdatedDate("2022-03-02 15:30:00");
        setTicketUrl("https://pms.cmcglobal.com.vn/browse/ITS-10921");
    }
}
