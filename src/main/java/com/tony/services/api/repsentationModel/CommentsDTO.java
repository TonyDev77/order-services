package com.tony.services.api.repsentationModel;

import java.time.OffsetDateTime;

public class CommentsDTO {

    private Long id;
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
}
