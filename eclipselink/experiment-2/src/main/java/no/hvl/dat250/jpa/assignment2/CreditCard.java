package no.hvl.dat250.jpa.assignment2;


import javax.persistence.*;

import lombok.Data;

@Entity
@Data
public class CreditCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int number;
    private int limit;
    private int balance;
    private Pincode pincode;
    
    @ManyToOne
    private Bank owningBank;
  
}
