package com.epam.lab.news.validation;

import com.epam.lab.news.bean.Article;
import com.epam.lab.news.logic.validation.ArticleValidator;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

public class ArticleValidatorSupportTest {

    @Autowired
    private ArticleValidator validator;

    @Before
    public void init() {

    }

    @Test
    public void test() {
        Article article = new Article();
        //validator.supports()
    }

}
