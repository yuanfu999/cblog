package com.cyf.cblog;

import com.cyf.cblog.mapper.BlogCategoryMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CblogApplicationTests {

    @Autowired
    private BlogCategoryMapper blogCategoryMapper;

    @Test
    void contextLoads() {

    }

}
