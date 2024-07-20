package ru.hibernate;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.lang.annotation.Native;
import java.util.Date;

@Entity
@Table(name = "PurchaseList")
@Setter
@Getter
@ToString
public class PurchaseList {
    @Native
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "student_name")
    private String studentName;

    @Column(name = "course_name")
    private String courseName;

    private Long price;

    @Column(name = "subscription_date")
    private Date subscriptionDate;
}
