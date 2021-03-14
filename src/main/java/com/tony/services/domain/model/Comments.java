package com.tony.services.domain.model;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.Objects;
@Entity
public class Comments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private OrderOfWork orderOfWork;

    private String description;
    private OffsetDateTime postDate;

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

    public OffsetDateTime getPostDate() {
        return postDate;
    }

    public void setPostDate(OffsetDateTime postDate) {
        this.postDate = postDate;
    }

    public OrderOfWork getOrderOfWork() {
        return orderOfWork;
    }

    public void setOrderOfWork(OrderOfWork orderOfWork) {
        this.orderOfWork = orderOfWork;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Comments)) return false;
        Comments comments = (Comments) o;
        return Objects.equals(id, comments.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
