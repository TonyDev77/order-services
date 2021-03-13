package com.tony.services.api.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Problem {

    private Integer status;
    private OffsetDateTime dateHour;
    private String title;

    private List<FieldException> fieldExceptions;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public OffsetDateTime getDateHour() {
        return dateHour;
    }

    public void setDateHour(OffsetDateTime dateHour) {
        this.dateHour = dateHour;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<FieldException> getFieldExceptions() {
        return fieldExceptions;
    }

    public void setFieldExceptions(List<FieldException> fieldExceptions) {
        this.fieldExceptions = fieldExceptions;
    }

    // Classe para tratar os campos da exceção
    public static class FieldException {
        private String name;
        private String message;

        public FieldException(String name, String message) {
            this.name = name;
            this.message = message;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
