package com.userauthentication.demo.entities;

import javax.persistence.*;

/**
 * Created by mkaroune on 28/10/18.
 */
@Entity
public class Role
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "role_id")
    private Integer id;
    private String role;

    public Role()
    {
    }

    public Role(String role)
    {
        this.role = role;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getRole()
    {
        return role;
    }

    public void setRole(String role)
    {
        this.role = role;
    }

    @Override
    public String toString()
    {
        return "Role{" +
                "id=" + id +
                ", role='" + role + '\'' +
                '}';
    }
}
