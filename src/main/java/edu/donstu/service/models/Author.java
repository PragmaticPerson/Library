package edu.donstu.service.models;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;

import edu.donstu.validation.annotations.AuthorConstraint;

@Entity
@Table(name = "author")
@AuthorConstraint
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "firstname")
    @NotBlank(message = "Firstname not blank")
    private String firstName;

    @Column(name = "lastname")
    @NotBlank(message = "Lastname not blank")
    private String lastName;

    @Column(name = "birthday")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;

    @Column(name = "date_of_death")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfDeath;

    public Author() {
    }

    public Author(String firstName, String lastName, LocalDate birthday, LocalDate dateOfDeath) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.dateOfDeath = dateOfDeath;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public LocalDate getDateOfDeath() {
        return dateOfDeath;
    }

    public void setDateOfDeath(LocalDate dateOfDeath) {
        this.dateOfDeath = dateOfDeath;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((birthday == null) ? 0 : birthday.hashCode());
        result = prime * result + ((dateOfDeath == null) ? 0 : dateOfDeath.hashCode());
        result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
        result = prime * result + id;
        result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Author other = (Author) obj;
        if (birthday == null) {
            if (other.birthday != null)
                return false;
        } else if (!birthday.equals(other.birthday))
            return false;
        if (dateOfDeath == null) {
            if (other.dateOfDeath != null)
                return false;
        } else if (!dateOfDeath.equals(other.dateOfDeath))
            return false;
        if (firstName == null) {
            if (other.firstName != null)
                return false;
        } else if (!firstName.equals(other.firstName))
            return false;
        if (id != other.id)
            return false;
        if (lastName == null) {
            if (other.lastName != null)
                return false;
        } else if (!lastName.equals(other.lastName))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Author [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", birthday=" + birthday
                + ", dateOfDeath=" + dateOfDeath + "]";
    }

}
