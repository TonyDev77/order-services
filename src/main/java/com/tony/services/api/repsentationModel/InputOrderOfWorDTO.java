package com.tony.services.api.repsentationModel;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class InputOrderOfWorDTO {

    @NotBlank
    private String description;
    @NotNull
    private BigDecimal price;

    @Valid
    @NotNull
    private ClientIdInput client;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public ClientIdInput getClient() {
        return client;
    }

    public void setClient(ClientIdInput client) {
        this.client = client;
    }
}
