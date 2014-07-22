package com.epam.lab.news.logic.validation;

import com.epam.lab.news.bean.Article;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Minimal validator implementation
 *
 * @author Dzmitry Piatrovich
 */
@Component
public class ArticleValidator implements Validator {

    /**
     * This method checks can this validator validate
     *
     * @param aClass Class name
     * @return True if support
     */
    @Override
    public boolean supports(Class<?> aClass) {
        return Article.class.isAssignableFrom(aClass);
    }

    /**
     * Checks only "must have" field values
     *
     * @param object Target object
     * @param errors Resulting errors
     */
    @Override
    public void validate(Object object, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "title.empty", "Empty title");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "description.empty", "Empty description");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "text", "text.empty", "Empty text");
    }

}
