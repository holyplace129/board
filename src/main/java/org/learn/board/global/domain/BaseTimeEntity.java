package org.learn.board.global.domain;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AutoCloseable.class)
public abstract class BaseTimeEntity {

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updateAt;
}
