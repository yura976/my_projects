package ru.hibernate;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "LinkedPurchaseList")
@Setter
@Getter
public class LinkedPurchaseList {

    @EmbeddedId
    private LinkedPurchaseKey id;
}
