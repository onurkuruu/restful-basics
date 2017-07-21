package com.oonurkuru.restful.domains;

import java.io.Serializable;

/**
 * Created by Onur Kuru on 21.7.2017.
 */
public class Employee implements Serializable {

    private Integer id;
    private String name;
    private Integer age;
    private Integer salary;
    private String departmentName;

    public Employee() {
    }

    public Employee(Integer id, String name, Integer age, Integer salary, String departmentName) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.departmentName = departmentName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
}
