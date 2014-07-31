package com.epam.lab.news.controller;

import com.epam.lab.news.configuration.ApplicationConfig;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * Test for ViewsController
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {ApplicationConfig.class})
public class ViewsControllerTest {

    @Autowired
    WebApplicationContext context;

    MockMvc mockMvc;

    /**
     * Initializing mock
     */
    @Before
    public void init() {
        this.mockMvc = webAppContextSetup(this.context).build();
    }

    /**
     * Testing index page
     *
     * @throws Exception
     */
    @Test
    public void testIndex() throws Exception{
        this.mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("/WEB-INF/pages/index.jsp"));
    }

    /**
     * Testing view news page
     *
     * @throws Exception
     */
    @Test
    public void testShowView() throws Exception{
        this.mockMvc.perform(get("/view/2345"))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("/WEB-INF/pages/view.jsp"));
    }

    /**
     * Testing add news page
     *
     * @throws Exception
     */
    @Test
     public void testShowAdd() throws Exception{
        this.mockMvc.perform(get("/add"))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("/WEB-INF/pages/add.jsp"));
    }

    /**
     * Testing edit news page
     *
     * @throws Exception
     */
    @Test
     public void testShowEdit() throws Exception{
        this.mockMvc.perform(get("/edit"))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("/WEB-INF/pages/edit.jsp"));
    }

    /**
     * Testing not existing page
     *
     * @throws Exception
     */
    @Test
    public void testNotFond() throws Exception{
        this.mockMvc.perform(get("/imaginary_page"))
                .andExpect(status().isNotFound());
    }

}
