package no.hvl.dat250.jpa.assignment2;

import javax.persistence.*;

import lombok.Data;

import java.util.Set;

@Entity
@Data
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    
    private Set<CreditCard> creditCards;
    
    @ManyToMany
    /*@JoinTable(
    		  name = "person_address", 
    		  joinColumns = @JoinColumn(name = "person_id"), 
    		  inverseJoinColumns = @JoinColumn(name = "address_id"))*/
    private Set<Address> addresses;
    

}
