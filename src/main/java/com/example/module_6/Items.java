package com.example;

import javax.persistence.*;

// This class represents a Hibernate entity mapped to the "Items" table in the SQLite database
@Entity
@Table(name = "Items") // Maps this class to the database table named "Items" (case-sensitive)
public class Items {

// Primary key field (mapped to the "id" column in the database)
    @Id
    private String id;

// Field mapped to a column with the same name ("name")
    private String name;

// Field explicitly mapped to the "short_description" column
    @Column(name = "short_description")
    private String shortDescription;

// Field explicitly mapped to the "task_status" column
    @Column(name = "task_status")
    private int taskStatus;

// Parameterized constructor (used when creating new Items objects in code)
    public Items(String id,String name, String shortDescription, int taskStatus){
        this.id = id;
        this.name = name;
        this.shortDescription = shortDescription;
        this.taskStatus = taskStatus;
    }

// Default no-arg constructor (required by Hibernate for entity initialization)
    public Items() {

    }

    // Getters and setters (or public fields if you're keeping it simple)
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getShortDescription() { return shortDescription; }
    public void setShortDescription(String shortDescription) { this.shortDescription = shortDescription; }

    public int getTaskStatus() { return taskStatus; }
    public void setTaskStatus(int taskStatus) { this.taskStatus = taskStatus; }
}
