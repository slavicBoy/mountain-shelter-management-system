package com.example.demo.model.message;

import com.example.demo.model.user.User;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "date")
    private LocalDate localDate;
    @Column(name = "time")
    private LocalTime localTime;
    @Column(name = "content")
    private String content;
    @Column(name = "likes")
    private int likes;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Message() {

    }

    public Message(LocalDate localDate, String content) {
        this.localDate = localDate;
        this.content = content;
        this.likes = 0;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public LocalTime getLocalTime() {
        return localTime;
    }

    public void setLocalTime(LocalTime localTime) {
        this.localTime = localTime;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", localTime=" + localDate +
                ", content='" + content + '\'' +
                ", likes=" + likes +
                ", user=" + user +
                '}';
    }
}
