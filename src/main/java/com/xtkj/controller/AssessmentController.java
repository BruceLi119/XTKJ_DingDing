package com.xtkj.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xtkj.bean.Assessment;
import com.xtkj.service.AssessmentService;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@RequestMapping("/Assessment")
public class AssessmentController {

    @Autowired
    AssessmentService assessmentService;

    //  1. 查询所选科室考核标准
    @ResponseBody
    @RequestMapping(value = "/searchAssessment", method = RequestMethod.GET)
    public List<Assessment> SelectAssessment(HttpServletRequest request, HttpServletResponse response,
                                             @RequestParam("departmentId") Integer departmentId) {
        response.setContentType("text/json");
        response.setCharacterEncoding("UTF-8");//设置响应类型，防止中文乱码

        //  1.1 查询所选科室的考核标准
        List<Assessment> list = assessmentService.searchAssessmentCriteria(departmentId);
        return list;
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

    //  3.修改所选考核标准
    @ResponseBody
    @RequestMapping(value = "/updateAssessmentCriteria", method = RequestMethod.POST)
    public void updateAssessmentCriteria(HttpServletRequest request, HttpServletResponse response,
                                         @RequestParam("departmentId") Long departmentId,
                                         @RequestParam("assessmentContent") String assessmentContent
    ) {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");//设置响应类型，防止中文乱码

        assessmentService.updateAssessmentCriteria(departmentId, assessmentContent);

    }

    //    4. 删除所选考核标准
    @ResponseBody
    @RequestMapping(value = "/deleteAssessment", method = RequestMethod.DELETE)
    public String deleteAssessment(HttpServletRequest request, HttpServletResponse response,
                                   @RequestParam Integer departmentId,
                                   @RequestParam Integer userId
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

    //    5. 同步数据
    @ResponseBody
    @RequestMapping(value = "/SynchronizeData")
    public void SynchronizeData() {
//        5.1 调用接口获取json串后进行解析，获取departmentId
        String urlDepartmentList = "http://218.29.74.138:9010/dingding/DingDingManage/WorkLedger/GetDingDepartment";//获取部门列表的接口
        String urlMemberList = "http://218.29.74.138:9010/dingding/DingDingManage/WorkLedger/GetDingUserByDeptId?value=118812353";//根据部门编号获取钉钉成员列表

//        5.2 构建get请求
        HttpGet httpGet1 = new HttpGet(urlDepartmentList);
        HttpGet httpGet2 = new HttpGet(urlMemberList);
//        5.3 根据CloseableHttpClient创建好我们的httpClient
        CloseableHttpClient httpClient = HttpClients.createDefault();
//        5.4 执行http请求
        CloseableHttpResponse response1 = null;
        CloseableHttpResponse response2 = null;
        String content1 = "";
        String content2 = "";
        try {
            response1 = httpClient.execute(httpGet1);
            response2 = httpClient.execute(httpGet2);
            HttpEntity entity1 = response1.getEntity();
            HttpEntity entity2 = response2.getEntity();
//         5.5 将数据转换为String
            content1 = EntityUtils.toString(entity1, Charset.forName("utf-8"));
            content2 = EntityUtils.toString(entity2, Charset.forName("utf-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(content1);
        System.out.println(content2);

//        5.6 将content字符串转为JSON对象
        JSONObject jsonObject1 = JSON.parseObject(content1);
        JSONObject jsonObject2 = JSON.parseObject(content2);
//        5.7 根据resultData键获取对应的值
        JSONArray resultDataArray1 = jsonObject1.getJSONArray("department");
        JSONArray resultDataArray2 = jsonObject2.getJSONArray("department");
        Map map = new HashMap();

        for (int i = 0; i < resultDataArray1.size(); i++) {
            JSONObject resultData = (JSONObject) resultDataArray1.get(i);
            String id = resultData.getString("id");
            String name = resultData.getString("name");
            map.put(id,name);
        }
//        5.8 将departmentId进行同步（即将现有的departmentId替换之前的departmentId）

//        5.9 根据departmentId获取钉钉成员列表
    }
}
