package ru.job4j.hql.candidate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "vacancyBase")
public class VacancyBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String username;

    private boolean active;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Vacancy> vacancies = new ArrayList<>();

    public void addBook(Vacancy vacancy) {
        this.vacancies.add(vacancy);
    }

    public static VacancyBase of(String username) {
        VacancyBase vb = new VacancyBase();
        vb.username = username;
        vb.active = true;
        return vb;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<Vacancy> getVacancies() {
        return vacancies;
    }

    public void setVacancies(List<Vacancy> vacancies) {
        this.vacancies = vacancies;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        VacancyBase that = (VacancyBase) o;
        return id == that.id && active == that.active && Objects.equals(username, that.username)
                && Objects.equals(vacancies, that.vacancies);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, active, vacancies);
    }

    @Override
    public String toString() {
        return "VacancyBase{" + "id=" + id + ", username='" + username + '\'' + ", active="
                + active + ", vacancies=" + vacancies + '}';
    }
}
