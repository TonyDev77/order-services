package com.tony.services.domain.model;

import com.tony.services.domain.exception.DomainException;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class OrderOfWork {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Client client;

    private String description;
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    private OrderOfWorkStatus status;

    private OffsetDateTime openingDate;
    private OffsetDateTime closingDate;

    @OneToMany(mappedBy = "orderOfWork")
    private List<Comments> comments = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
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

    public List<Comments> getComments() {
        return comments;
    }

    public void setComments(List<Comments> comments) {
        this.comments = comments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderOfWork)) return false;
        OrderOfWork that = (OrderOfWork) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


    public boolean canEnded() {
        return OrderOfWorkStatus.OPENED.equals(getStatus());
    }

    public boolean notCanEnded() {
        return !canEnded();
    }

    public void endedWork() {

        if (notCanEnded()) {
            throw new DomainException("Ordem de serviço não pode ser finalizada");
        }
        setStatus(OrderOfWorkStatus.ENDED);
        setClosingDate(OffsetDateTime.now());
    }
}
