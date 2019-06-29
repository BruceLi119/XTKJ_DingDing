package com.xtkj.controller;

import com.xtkj.bean.User;
import com.xtkj.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/account")
public class TestController {

    @Autowired
    private TestService testService;

    @RequestMapping(value = "/addAccount", method = RequestMethod.GET)
    @ResponseBody
    public User addAccount(@RequestParam("userId") String userId) {
        return testService.addAccount(Integer.parseInt(userId));
    }

}
