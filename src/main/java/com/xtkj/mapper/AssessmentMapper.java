package com.xtkj.mapper;

import com.xtkj.bean.Assessment;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssessmentMapper {

    List<Assessment> searchAssessmentCriteria(Integer departmentId);

    List<Assessment> searchAllAssessmentsCriteria();

    void updateAssessmentCriteria(@Param("departmentId") long departmentId, @Param("assessmentContent") String assessmentContent);

    int getUserPermissionLevel(Integer userId);

    void deleteAssessment(Integer departmentId);

}