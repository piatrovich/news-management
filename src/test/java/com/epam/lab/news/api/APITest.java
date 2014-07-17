package com.epam.lab.news.api;

import com.epam.lab.news.config.MongoDbConfig;
import com.epam.lab.news.config.WebAppConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {WebAppConfig.class, MongoDbConfig.class},
        loader = AnnotationConfigContextLoader.class)
public class APITest {

    @Test
    public void testGET() {

    }

}
