CREATE TABLE comments
(
    id BIGINT NOT NULL AUTO_INCREMENT,
    order_of_work_id BIGINT NOT NULL,
    description TEXT NOT NULL,
    post_date DATETIME NOT NULL,

    PRIMARY KEY (id)
);

ALTER TABLE comments ADD CONSTRAINT fk_comments_order_of_work
    FOREIGN KEY (order_of_work_id) REFERENCES order_of_work (id);