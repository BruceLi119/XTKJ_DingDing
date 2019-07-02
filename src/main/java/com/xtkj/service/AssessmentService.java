package com.xtkj.service;


import com.xtkj.bean.Assessment;
import com.xtkj.bean.PartOfAssessment;

import java.util.List;

public interface AssessmentService {

    //    1.查询所选科室考核标准
    Assessment searchAssessmentCriteria(Long departmentId);

    //    2.查询所有科室考核标准
    List<Assessment> searchAllAssessmentsCriteria();

    //   2.1 查询所有台账标准的部门id和部门名称
    List<PartOfAssessment> searchPartOfAllAssessmentsCriteria();

    //    3.修改所选考核标准
    int updateAssessmentCriteria(long departmentId, String assessmentContent);


    //   4.删除所选考核标准
    void deleteAssessment(Long departmentId);

    //   4.1 获取用户的权限级别
    int getUserPermissionLevel(Long userId);

    // 5.同步钉钉数据
    // 5.1同步钉钉部门信息数据
    // 5.1.1 更新操作
    int SynchronizeDingDingData(Long departmentId, String departmentName, String creatDeptGroup, String autoAddUser, Long parentId);
    // 5.1.2 新增操作
    int addDingDingData(Long departmentId, String departmentName, String creatDeptGroup, String autoAddUser, Long parentId);

    List<Assessment>  getAssessmentCriteria(Long departmentId);

    // 5.2同步钉钉成员信息


}
