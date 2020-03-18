package com.agenda.springcloudmysql.model;

import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Entity
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private static Long id;


    @Column(name = "name")
    private static String name;
    @Column(name = "email")
    private static String email;
    @Column(name = "phone")
    private static String phone;



    public static Long getId() {
        return id;
    }

    public void setId(Long id) { this.id = id; }

    public static String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static String getPhone() { return phone; }

    public void setPhone(String phone) { this.phone = phone; }



}
