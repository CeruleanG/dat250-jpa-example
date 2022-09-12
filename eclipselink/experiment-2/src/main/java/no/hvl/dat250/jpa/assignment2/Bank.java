package no.hvl.dat250.jpa.assignment2;

import javax.persistence.*;

import lombok.Data;


import java.util.List;

@Entity
@Data

public class Bank {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    
    @OneToMany(mappedBy = "owningBank")
    private List<CreditCard> ownedCards;

}
