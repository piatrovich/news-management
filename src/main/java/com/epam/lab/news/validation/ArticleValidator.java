package com.epam.lab.news.validation;

import com.epam.lab.news.bean.Article;
import org.springframework.stereotype.Component;
import org.springframework.validation.*;

import java.util.HashMap;
import java.util.Map;

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

    public ValidationResult validate(Article article){
        DataBinder binder = new DataBinder(article);
        binder.setValidator(this);
        binder.validate();
        if(binder.getBindingResult().hasErrors()) {
            ValidationResult result = new ValidationResult(false);
            Map<String, String> values = new HashMap<String, String>();
            for(FieldError field : binder.getBindingResult().getFieldErrors()) {
                values.put(field.getCode(),field.getDefaultMessage());
            }
            result.setResult(values);
            return result;
        }
        return new ValidationResult(true);
    }

}
