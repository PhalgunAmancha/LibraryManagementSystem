package com.practice.library_dependent.entity;

public class Book {
    private Integer id;
    private String name;
    private String publisher;
    private String author;

    public Book() {
    }

    public Book(Integer id, String name, String publisher, String author) {
        this.id = id;
        this.name = name;
        this.publisher = publisher;
        this.author = author;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }


    @Override
    public String toString() {
        return "BookDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", publisher='" + publisher + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
