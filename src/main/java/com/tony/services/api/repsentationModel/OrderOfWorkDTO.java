package com.tony.services.api.repsentationModel;

import com.tony.services.domain.model.OrderOfWorkStatus;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Objects;

public class OrderOfWorkDTO {

    private Long id;
    private ClientSummaryDTO client;
    private String description;
    private BigDecimal price;
    private OrderOfWorkStatus status;
    private OffsetDateTime openingDate;
    private OffsetDateTime closingDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public OrderOfWorkStatus getStatus() {
        return status;
    }

    public void setStatus(OrderOfWorkStatus status) {
        this.status = status;
    }

    public OffsetDateTime getOpeningDate() {
        return openingDate;
    }

    public void setOpeningDate(OffsetDateTime openingDate) {
        this.openingDate = openingDate;
    }

    public OffsetDateTime getClosingDate() {
        return closingDate;
    }

    public void setClosingDate(OffsetDateTime closingDate) {
        this.closingDate = closingDate;
    }

    public ClientSummaryDTO getClient() {
        return client;
    }

    public void setClient(ClientSummaryDTO client) {
        this.client = client;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderOfWorkDTO)) return false;
        OrderOfWorkDTO that = (OrderOfWorkDTO) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
