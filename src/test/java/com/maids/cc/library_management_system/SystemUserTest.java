package com.maids.cc.library_management_system;

import com.maids.cc.library_management_system.domain.SystemUser;
import com.maids.cc.library_management_system.service.SystemUserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.ObjectUtils;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class SystemUserTest {
    @Autowired
    private SystemUserService systemUserService;

    @Test
    public void findByUsername(){
        SystemUser systemUser = systemUserService.findByEmail("domebingonye@gmail.com");
        Assert.assertTrue(!ObjectUtils.isEmpty(systemUser));
    }
}
