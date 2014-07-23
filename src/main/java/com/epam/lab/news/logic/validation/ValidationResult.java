package com.epam.lab.news.logic.validation;

import java.util.Map;

public class ValidationResult {
    private boolean status;
    private Map<String, String> result;

    public ValidationResult(boolean status){
        this.status = status;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Map<String, String> getResult() {
        return result;
    }

    public void setResult(Map<String, String> result) {
        this.result = result;
    }

}
