package com.agenda.springcloudmysql.model;


import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Entity
public class Groups {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private static Long id;

    @Column(name = "description")
    private String description;
    @Column(name = "id_contact")
    private static Long id_contact;

    public static Long getId_contact() {
        return id_contact;
    }

    public void setId_contact(String id_contact) {
        Groups.id_contact = Contact.getId();
    }


    public static Long getGroup_id() {
        return id;
    }

    public void setGroup_id(Long group_id) {
        this.id = group_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }





}
