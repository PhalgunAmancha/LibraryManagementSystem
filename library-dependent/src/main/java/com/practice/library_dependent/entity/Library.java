package com.practice.library_dependent.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "`library`")
public class Library {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String username;
    private Integer bookId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    @Override
    public String toString() {
        return "Library{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", bookId=" + bookId +
                '}';
    }
}
