package com.mgt.DixitFurnitures.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.NamedQuery;

import java.io.Serializable;

@NamedQuery(name = "Users.findByEmailId", query = "select u from Users u where u.email=:email")



@Data
@DynamicUpdate
@DynamicInsert
@Entity
@Table(name="User")
public class Users implements Serializable {
    private static final long serialVerionUID =1L;

    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    private String contact;

    private String email;

    private String password;

    private String status;

    private String role;





}
