package com.xtkj.serviceImpl;

import com.xtkj.bean.Assessment;
import com.xtkj.mapper.AssessmentMapper;
import com.xtkj.service.AssessmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssessmentServiceImpl implements AssessmentService {

    @Autowired
    AssessmentMapper assessmentMapper;

    @Override
    public void updateAssessmentCriteria(long departmentId, String assessmentContent) {
       assessmentMapper.updateAssessmentCriteria(departmentId,assessmentContent);

    }

    @Override
    public List<Assessment> searchAllAssessmentsCriteria() {
        return assessmentMapper.searchAllAssessmentsCriteria();
    }

    @Override
    public int getUserPermissionLevel(Integer userId) {
        return assessmentMapper.getUserPermissionLevel(userId);
    }

    @Override
    public void deleteAssessment(Integer departmentId) {
        assessmentMapper.deleteAssessment(departmentId);
    }

    @Override
    public List<Assessment> searchAssessmentCriteria(Integer departmentId) {
        return assessmentMapper.searchAssessmentCriteria(departmentId);
    }
}
