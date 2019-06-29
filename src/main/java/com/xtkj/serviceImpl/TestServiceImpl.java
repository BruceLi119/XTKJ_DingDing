package com.xtkj.serviceImpl;

import com.xtkj.bean.User;
import com.xtkj.mapper.TestMapper;
import com.xtkj.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

    @Service
    public class TestServiceImpl implements TestService {
        @Autowired
        private TestMapper testMapper;

        public User addAccount(int userId) {
            return testMapper.selectByPrimaryKey(userId);
        }

    }
