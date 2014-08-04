package com.epam.lab.news.util;

import org.junit.runners.model.InitializationError;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

public class TestClassRunner extends SpringJUnit4ClassRunner {

    private TestClassListener classListener;

    public TestClassRunner(Class<?> clazz) throws InitializationError {
        super(clazz);
    }

    @Override
    protected Object createTest() throws Exception {
        Object test =  super.createTest();
        if(test instanceof TestClassListener && classListener == null){
            classListener = (TestClassListener)test;
            classListener.beforeClass();
        }
        return test;
    }

}
