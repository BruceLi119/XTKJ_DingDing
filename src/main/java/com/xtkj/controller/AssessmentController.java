package com.xtkj.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xtkj.bean.Assessment;
import com.xtkj.bean.PartOfAssessment;
import com.xtkj.service.AssessmentService;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@RequestMapping("/Assessment")
public class AssessmentController {

    @Autowired
    AssessmentService assessmentService;

    //  1. 查询所选科室考核标准
    @ResponseBody
    @RequestMapping(value = "/searchAssessment", method = RequestMethod.GET)
    public Assessment SelectAssessment(@RequestParam Long departmentId) {


        //  1.1 查询所选科室的考核标准

//        Long departmentId = assessment.getDepartmentId();
        Assessment resultOfSearch = assessmentService.searchAssessmentCriteria(departmentId);
        return resultOfSearch;
    }

    //    2. 查询所有科室考核标准
    @ResponseBody
    @RequestMapping(value = "/searchAllAssessmentsCriteria", method = RequestMethod.GET)
    public List<Assessment> searchAllAssessmentsCriteria(HttpServletResponse response) {
        response.setContentType("text/json");
        response.setCharacterEncoding("UTF-8");//设置响应类型，防止中文乱码

        //  2.1 查询所有科室的考核标准
        List<Assessment> list = assessmentService.searchAllAssessmentsCriteria();
        return list;
    }

    //    3. 查询所有科室考核标准(只要科室ID和标题)
    @ResponseBody
    @RequestMapping(value = "/searchPartOfAllAssessmentsCriteria", method = RequestMethod.GET)
    public List<PartOfAssessment> searchPartOfAllAssessmentsCriteria(HttpServletResponse response) {
        response.setContentType("text/json");
        response.setCharacterEncoding("UTF-8");//设置响应类型，防止中文乱码

        //  3.1 查询所有科室的考核标准(只要科室ID和标题)
        List<PartOfAssessment> list = assessmentService.searchPartOfAllAssessmentsCriteria();
        return list;
    }

    //  4.修改所选考核标准
    @ResponseBody
    @RequestMapping(value = "/updateAssessmentCriteria", method = RequestMethod.POST)
    public String updateAssessmentCriteria(@RequestBody Assessment assessment) {

        Long departmentId = assessment.getDepartmentId();
        String assessmentContent = assessment.getAssessmentContent();
        int resultOfUpdate = assessmentService.updateAssessmentCriteria(departmentId, assessmentContent);
        String result;
        if (resultOfUpdate == 1) {
            System.out.println("更新成功！");
            result = "success";
        } else {
            System.out.println("更新失败！");
            result = "fail";
        }
        return result;
    }

    //    5. 删除所选考核标准
    @ResponseBody
    @RequestMapping(value = "/deleteAssessment", method = RequestMethod.DELETE)
    public String deleteAssessment(@RequestParam Long departmentId,
                                   @RequestParam Long userId
    ) {


//        4.1 根据userId获取相应的权限级别
        int level = assessmentService.getUserPermissionLevel(userId);
//        4.2  根据权限进行判定是否具有删除的权利
        String result;
        if (level == 3) {
            assessmentService.deleteAssessment(departmentId);
            result = "success";
        } else {
            result = "fail";
        }

        return result;
    }

    //      6.8 同步钉钉部门数据
/*    @ResponseBody
//    @RequestMapping(value = "/SynchronizeDingDingData", method = RequestMethod.POST)

    public String SynchronizeDingDingData(HttpServletRequest request, HttpServletResponse response,
                                          Model model,
                                          @RequestParam("departmentId") Long departmentId,
                                          @RequestParam("departmentName") String departmentName,
                                          @RequestParam("creatDeptGroup") String creatDeptGroup,
                                          @RequestParam("autoAddUser") String autoAddUser,
                                          @RequestParam("parentId") Long parentId
    ) {
//      调用SelectAssessment方法进行判断departmentId对应的部门信息是否存在，若不存在则进行insert，存在进行update操作；
        AssessmentController assessment = new AssessmentController();
        Assessment resultOfSelect = assessment.SelectAssessment(departmentId);
        if (resultOfSelect != null) {
//       6.8.1 update操作
            int resultOfSynchronize = assessmentService.SynchronizeDingDingData(departmentId, departmentName, creatDeptGroup, autoAddUser, parentId);
            //        声明一个变量model，前台可通过其获取修改结果。
            model.addAttribute("result", "");
            if (resultOfSynchronize == 1) {
                return "synchronizeSuccess!";
            } else {
                return "synchronizeFail!";
            }
        } else {
//       6.8.2 insert操作
            int resultOfAdd = assessmentService.addDingDingData(departmentId, departmentName, creatDeptGroup, autoAddUser, parentId);
            if (resultOfAdd == 1) {
                return "synchronizeSuccess!";
            } else {
                return "synchronizeFail!";
            }
        }
    }*/

    //        6. 同步数据
    @ResponseBody
    @RequestMapping(value = "/SynchronizeData")
    public String SynchronizeData(HttpServletRequest request, HttpServletResponse response, Model model) {
//        6.0 用来接收同步结果
        String result = null;
//        6.1 调用接口获取json串后进行解析，获取departmentId
        String urlDepartmentList = "http://218.29.74.138:9010/dingding/DingDingManage/WorkLedger/GetDingDepartment";//获取部门列表的接口
        String urlMemberList = "http://218.29.74.138:9010/dingding/DingDingManage/WorkLedger/GetDingUserByDeptId?value=118812353";//获取钉钉成员列表

//        6.2 构建get请求
        HttpGet httpGet1 = new HttpGet(urlDepartmentList);
        HttpGet httpGet2 = new HttpGet(urlMemberList);
//        6.3 根据CloseableHttpClient创建好我们的httpClient
        CloseableHttpClient httpClient = HttpClients.createDefault();
//        6.4 执行http请求
        CloseableHttpResponse response1 = null;
        CloseableHttpResponse response2 = null;
        String content1 = "";
        String content2 = "";
        try {
            response1 = httpClient.execute(httpGet1);
            response2 = httpClient.execute(httpGet2);
            HttpEntity entity1 = response1.getEntity();
            HttpEntity entity2 = response2.getEntity();
//         6.5 将数据转换为String
            content1 = EntityUtils.toString(entity1, Charset.forName("utf-8"));
            content2 = EntityUtils.toString(entity2, Charset.forName("utf-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(content1);
        System.out.println(content2);

//        6.6 将content字符串转为JSON对象
        JSONObject jsonObject1 = JSON.parseObject(content1);
//        6.7 根据resultData键获取对应的值
        JSONArray resultDataArray1 = jsonObject1.getJSONArray("department");
        for (int i = 0; i < resultDataArray1.size(); i++) {
            JSONObject resultData = (JSONObject) resultDataArray1.get(i);
            String depId = resultData.getString("id");
            Long departmentId = Long.parseLong(depId);
            String departmentName = resultData.getString("name");
            String createDeptGroup = resultData.getString("createDeptGroup");
            String autoAddUser = resultData.getString("autoAddUser");
            String parId = resultData.getString("parentid");
            Long parentId = null;
            if (parId != null) {
                parentId = Long.parseLong(parId);
            }
            System.out.println("departmentId:" + departmentId + "departmentName:" + departmentName + "createDeptGroup:" + createDeptGroup + "autoAddUser:" + autoAddUser + "parentId:" + parentId);
//        6.8 调用SynchronizeDingDingData方法进行数据同步（即更新）；
            //----------------------------------

            Assessment resultOfSearch = assessmentService.searchAssessmentCriteria(departmentId);
            if (resultOfSearch != null) {
//       6.8.1 update操作
                int resultOfSynchronize = assessmentService.SynchronizeDingDingData(departmentId, departmentName, createDeptGroup, autoAddUser, parentId);
                if (resultOfSynchronize == 1) {
                    result = "synchronizeSuccess!";
                    System.out.println("synchronize: "+i+"th " + "Success!");
                } else {
                    result = "synchronizeFail!";
                    System.out.println("synchronize: "+i+"th " + "Success!");
                }
            } else {
//       6.8.2 insert操作
                int resultOfAdd = assessmentService.addDingDingData(departmentId, departmentName, createDeptGroup, autoAddUser, parentId);
                if (resultOfAdd == 1) {
                    result = "synchronizeSuccess!";
                    System.out.println("synchronize: "+i+"th " + "Success!");
                } else {
                    result = "synchronizeFail!";
                    System.out.println("synchronize: "+i+"th " + "Success!");
                }
            }
        }
        return result;
//        6.9 根据departmentId获取钉钉成员列表
    }
}
