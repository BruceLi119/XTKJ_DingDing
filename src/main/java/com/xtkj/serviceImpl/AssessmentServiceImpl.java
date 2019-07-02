package com.xtkj.serviceImpl;

import com.xtkj.bean.Assessment;
import com.xtkj.bean.PartOfAssessment;
import com.xtkj.mapper.AssessmentMapper;
import com.xtkj.service.AssessmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssessmentServiceImpl implements AssessmentService {

    @Autowired
    AssessmentMapper assessmentMapper;


    //    1.查询所选科室考核标准
    @Override
    public Assessment searchAssessmentCriteria(Long departmentId) {
        return assessmentMapper.searchAssessmentCriteria(departmentId);
    }

    //    2.查询所有科室考核标准
    @Override
    public List<Assessment> searchAllAssessmentsCriteria() {
        return assessmentMapper.searchAllAssessmentsCriteria();
    }

    //   2.1 查询所有台账标准的部门id和部门名称
    @Override
    public List<PartOfAssessment> searchPartOfAllAssessmentsCriteria() {
        return assessmentMapper.searchPartOfAllAssessmentsCriteria();
    }

    //    3.修改所选考核标准
    @Override
    public int updateAssessmentCriteria(long departmentId, String assessmentContent) {
        return assessmentMapper.updateAssessmentCriteria(departmentId, assessmentContent);
    }

    //   4.删除所选考核标准
    @Override
    public void deleteAssessment(Long departmentId) {
        assessmentMapper.deleteAssessment(departmentId);
    }

    //   4.1 获取用户的权限级别
    @Override
    public int getUserPermissionLevel(Long userId) {
        return assessmentMapper.getUserPermissionLevel(userId);
    }

    //    5.同步钉钉数据
    @Override
    public int SynchronizeDingDingData(Long departmentId, String departmentName, String creatDeptGroup, String autoAddUser, Long parentId) {
        return assessmentMapper.SynchronizeDingDingData(departmentId, departmentName, creatDeptGroup, autoAddUser, parentId);
    }

    //    5.1同步钉钉部门信息数据insert
    @Override
    public int addDingDingData(Long departmentId, String departmentName, String creatDeptGroup, String autoAddUser, Long parentId) {
        return assessmentMapper.addDingDingData(departmentId, departmentName, creatDeptGroup, autoAddUser, parentId);
    }

    @Override
    public List<Assessment> getAssessmentCriteria(Long departmentId) {
        return assessmentMapper.getAssessmentCriteria(departmentId);
    }
    //    5.2同步钉钉部门信息数据update
}