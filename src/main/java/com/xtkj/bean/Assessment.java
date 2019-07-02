package com.xtkj.bean;

public class Assessment {
    private Long departmentId;
    private String departmentName;
    private String assessmentTitle;
    private String assessmentContent;
    private String creatDeptGrop;
    private String autoAddUser;
    private Long partentId;

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getAssessmentTitle() {
        return assessmentTitle;
    }

    public void setAssessmentTitle(String assessmentTitle) {
        this.assessmentTitle = assessmentTitle;
    }

    public String getAssessmentContent() {
        return assessmentContent;
    }

    public void setAssessmentContent(String assessmentContent) {
        this.assessmentContent = assessmentContent;
    }

    public String getCreatDeptGrop() {
        return creatDeptGrop;
    }

    public void setCreatDeptGrop(String creatDeptGrop) {
        this.creatDeptGrop = creatDeptGrop;
    }

    public String getAutoAddUser() {
        return autoAddUser;
    }

    public void setAutoAddUser(String autoAddUser) {
        this.autoAddUser = autoAddUser;
    }

    public Long getPartentId() {
        return partentId;
    }

    public void setPartentId(Long partentId) {
        this.partentId = partentId;
    }

    @Override
    public String toString() {
        return "Assessment{" +
                "departmentId=" + departmentId +
                ", departmentName='" + departmentName + '\'' +
                ", assessmentTitle='" + assessmentTitle + '\'' +
                ", assessmentContent='" + assessmentContent + '\'' +
                ", creatDeptGrop='" + creatDeptGrop + '\'' +
                ", autoAddUser='" + autoAddUser + '\'' +
                ", partentId=" + partentId +
                '}';
    }
}
