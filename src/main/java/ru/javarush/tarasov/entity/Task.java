package ru.javarush.tarasov.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.Type;
import org.hibernate.type.SqlTypes;

import java.sql.SQLType;

@Entity
@Table(schema = "todo", name="task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name="description")
    private String description;
    @JdbcTypeCode(SqlTypes.INTEGER)
    @Column(name="status")
    @Enumerated(EnumType.ORDINAL)
    private Status status;

    public Task(String description, Status status) {
        this.description = description;
        this.status = status;
    }

    public Task() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
