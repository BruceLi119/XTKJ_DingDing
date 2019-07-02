package com.xtkj.mapper;

import com.xtkj.bean.Assessment;
import com.xtkj.bean.PartOfAssessment;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssessmentMapper {
    // 1. 查询所选科室考核标准
    Assessment searchAssessmentCriteria(Long departmentId);

    // 2. 查询所有科室考核标准
    List<Assessment> searchAllAssessmentsCriteria();

    // 2.1 查询所有台账标准的部门id和部门名称
    List<PartOfAssessment> searchPartOfAllAssessmentsCriteria();

    // 3. 修改所选考核标准
    int updateAssessmentCriteria(@Param("departmentId") long departmentId, @Param("assessmentContent") String assessmentContent);
    // 3.1 更新完 重新查询该台账标准
    List<Assessment>  getAssessmentCriteria(Long departmentId);

    // 4. 删除所选考核标准
    void deleteAssessment(Long departmentId);

    // 4.1 获取用户权限级别
    int getUserPermissionLevel(Long userId);

    //  5. 同步顶顶数据
    // 5.1 更新操作
    int SynchronizeDingDingData(@Param("departmentId") Long departmentId, @Param("departmentName") String departmentName, @Param("creatDeptGroup") String creatDeptGroup, @Param("autoAddUser") String autoAddUser, @Param("parentId") Long parentId);

    // 5.2 新增操作
    int addDingDingData(Long departmentId, String departmentName, String creatDeptGroup, String autoAddUser, Long parentId);

}