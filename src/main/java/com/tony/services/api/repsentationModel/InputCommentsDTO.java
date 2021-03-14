package com.tony.services.api.repsentationModel;

import javax.validation.constraints.NotBlank;

public class InputCommentsDTO {

    @NotBlank
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
