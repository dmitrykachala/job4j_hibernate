package ru.job4j.hql;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String publishingHouse;

    public static Book of(String name, String publishingHouse) {
        Book b = new Book();
        b.name = name;
        b.publishingHouse = publishingHouse;
        return b;
    }

    public String getPublishingHouse() {
        return publishingHouse;
    }

    public void setPublishingHouse(String publishingHouse) {
        this.publishingHouse = publishingHouse;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Book book = (Book) o;
        return id == book.id && Objects.equals(name, book.name)
                && Objects.equals(publishingHouse, book.publishingHouse);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, publishingHouse);
    }

    @Override
    public String toString() {
        return "Book{" + "id=" + id + ", name='" + name + '\'' + ", publishingHouse='"
                + publishingHouse + '\'' + '}';
    }
}
