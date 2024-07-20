package com.skillbox.fibonacci;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.SQLInsert;


@Entity
@Table(name = "fibonacci_number", uniqueConstraints = { @UniqueConstraint(columnNames={"index"}) })
@SQLInsert(sql = "INSERT INTO fibonacci_number(index, value) VALUES (?, ?) ON CONFLICT(index) DO UPDATE SET value = EXCLUDED.value RETURNING id" )
public class FibonacciNumber {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "serial")
    @JsonIgnore
    private Integer id;

    private int index;

    private int value;

    public FibonacciNumber() {

    }

    public FibonacciNumber(int index, int value) {
        this();
        this.index = index;
        this.value = value;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
