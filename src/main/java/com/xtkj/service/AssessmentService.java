package com.xtkj.service;


import com.xtkj.bean.Assessment;

import java.util.List;

public interface AssessmentService {

    void updateAssessmentCriteria(long departmentId, String assessmentContent);

    List<Assessment> searchAllAssessmentsCriteria();

    int getUserPermissionLevel(Integer userId);

    void deleteAssessment(Integer departmentId);

    List<Assessment> searchAssessmentCriteria(Integer departmentId);
}
