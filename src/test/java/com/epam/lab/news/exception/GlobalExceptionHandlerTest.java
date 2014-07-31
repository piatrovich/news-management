package com.epam.lab.news.exception;

import com.epam.lab.news.configuration.ApplicationConfig;
import com.epam.lab.news.controller.APIController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * Testing exception handling
 *
 * @author Dzmitry Piatrovich
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {ApplicationConfig.class})
public class GlobalExceptionHandlerTest {
    /** Application context */
    @Autowired
    WebApplicationContext context;

    /** Wiring controller */
    @Autowired
    protected APIController apiController;

    /** Entry point */
    MockMvc mockMvc;

    /**
     * Setup
     */
    @Before
    public void init() {
        this.mockMvc = webAppContextSetup(this.context).build();
    }

    /**
     * Checks exception handler for non existing news
     *
     * @throws Exception
     */
    @Test
    public void testGetArticleForView() throws Exception{
        mockMvc.perform(get("/api/get/0"))
                .andExpect(status().isNotFound());
    }

}
