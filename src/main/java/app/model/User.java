package app.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users")
    public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    @NotEmpty(message = "Empty values not allowed")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 character")
    private String name;

    @Column
    @NotEmpty(message = "Empty values not allowed")
    private String surName;
    @Column
    @NotEmpty(message = "Empty values not allowed")
    @Email(message = "Not correct email entered")
    private String email;
    @Column
    @Min(value = 0, message = "Age must be greater than 0")
    private int age;

    public User() {
    }

    public User(int id, String name, String surName, String email, int age) {
        this.id = id;
        this.name = name;
        this.surName = surName;
        this.email = email;
        this.age = age;
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

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


}
