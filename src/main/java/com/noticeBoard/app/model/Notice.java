package com.noticeBoard.app.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "notices")
public class Notice extends AbstractEntity {

    @Size(min = 5, max = 100)
    private String title;

    @Size(min = 5, max = 500)
    private String content;

    private LocalDateTime created;

    @NotNull
    @Column(name = "end_date")
    private LocalDate endDate;

    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private byte[] photo;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Size(min = 1)
    @ManyToMany
    @JoinTable(name = "notices_categories",
            joinColumns = @JoinColumn(name = "notice_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<Category> categories = new ArrayList<>();

    @OneToMany(mappedBy = "notice")
    private List<Comment> comments = new ArrayList<>();

    @PrePersist
    public void prePersist() {
        setCreated(LocalDateTime.now());
    }

    public Notice() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
