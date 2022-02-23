package ru.job4j.hql.candidate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "vacancies")
public class Vacancy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String employer;

    public static Vacancy of(String name, String employer) {
        Vacancy v = new Vacancy();
        v.name = name;
        v.employer = employer;
        return v;
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

    public String getEmployer() {
        return employer;
    }

    public void setEmployer(String employer) {
        this.employer = employer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Vacancy vacancy = (Vacancy) o;
        return id == vacancy.id && Objects.equals(name, vacancy.name)
                && Objects.equals(employer, vacancy.employer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, employer);
    }

    @Override
    public String toString() {
        return "Vacancy{" + "id=" + id + ", name='" + name + '\'' + ", employer='"
                + employer + '\'' + '}';
    }
}
