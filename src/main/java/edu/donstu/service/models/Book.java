package edu.donstu.service.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import edu.donstu.validation.annotations.BookConstraint;

@Entity
@Table(name = "book")
@BookConstraint
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    @NotBlank
    private String name;

    @Column(name = "description")
    @NotBlank
    private String description;

    @Column(name = "release_date")
    private short releaseDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    private Author author;

    @ManyToOne
    @JoinColumn(name = "ganre_id", referencedColumnName = "id")
    private Ganre ganre;

    public Book() {
    }

    public Book(int id) {
        super();
        this.id = id;
    }

    public Book(int id, @NotBlank String name, @NotBlank String description, short releaseDate, Author author,
            Ganre ganre) {
        super();
        this.id = id;
        this.name = name;
        this.description = description;
        this.releaseDate = releaseDate;
        this.author = author;
        this.ganre = ganre;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public short getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(short releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Ganre getGanre() {
        return ganre;
    }

    public void setGanre(Ganre ganre) {
        this.ganre = ganre;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((author == null) ? 0 : author.hashCode());
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((ganre == null) ? 0 : ganre.hashCode());
        result = prime * result + id;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + releaseDate;
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
        Book other = (Book) obj;
        if (author == null) {
            if (other.author != null)
                return false;
        } else if (!author.equals(other.author))
            return false;
        if (description == null) {
            if (other.description != null)
                return false;
        } else if (!description.equals(other.description))
            return false;
        if (ganre == null) {
            if (other.ganre != null)
                return false;
        } else if (!ganre.equals(other.ganre))
            return false;
        if (id != other.id)
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (releaseDate != other.releaseDate)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Book [id=" + id + ", name=" + name + ", description=" + description + ", releaseDate=" + releaseDate
                + ", author=" + author + ", ganre=" + ganre + "]";
    }

}
