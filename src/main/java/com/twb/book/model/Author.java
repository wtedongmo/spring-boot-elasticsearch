package com.twb.book.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "authors")
public class Author {

    @Id
    private String id;

    private String name;

    private String town;


    public Author() {
    }

    public Author(String id, String name, String town) {
        this.id = id;
        this.name = name;
        this.town = town;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", town='" + town + '\'' +
                '}';
    }
}
