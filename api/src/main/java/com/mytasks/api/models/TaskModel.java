package com.mytasks.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "TASKS")
public class TaskModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String description;

    @Column
    private Boolean completed;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserModel user;

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreatedDate
    private LocalDateTime createdAt;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
