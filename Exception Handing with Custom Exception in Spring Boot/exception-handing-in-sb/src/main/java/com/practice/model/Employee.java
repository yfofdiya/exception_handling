package com.practice.model;


import javax.persistence.*;

@Entity
@Table(name="emp_details")
public class Employee {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="emp_id")
    private int id;
    @Column(name="emp_name")
    private String name;
    @Column(name="emp_address")
    private String address;

    public Employee() {
    }

    public Employee(String name, String address) {
        this.name = name;
        this.address = address;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
