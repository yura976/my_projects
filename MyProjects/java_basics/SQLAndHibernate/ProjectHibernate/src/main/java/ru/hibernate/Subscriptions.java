package ru.hibernate;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "Subscriptions")
@Setter
@Getter
public class Subscriptions {

    @EmbeddedId
    private SubscriptionKey id;

    @Column(name = "student_id", insertable = false, updatable = false)
    private Long studentId;

    @Column(name = "course_id", insertable = false, updatable = false)
    private Long courseId;

    @Column(name = "subscription_date")
    private Date subscriptionDate;

    @EqualsAndHashCode
    @ToString
    @Embeddable
    public class SubscriptionKey implements Serializable {
        @Column(name = "student_id")
        private Long studentId;
        @Column(name = "course_id")
        private Long courseId;
    }
}
