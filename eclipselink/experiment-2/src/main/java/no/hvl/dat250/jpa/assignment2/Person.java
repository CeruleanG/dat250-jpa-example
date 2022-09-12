package no.hvl.dat250.jpa.assignment2;

import javax.persistence.*;

import lombok.Data;

import java.util.List;

@Entity
@Data
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    
    private List<CreditCard> creditCards;
    
    @ManyToMany//(mappedBy = "owners", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinTable(
    		  name = "person_address", 
    		  joinColumns = @JoinColumn(name = "person_id"), 
    		  inverseJoinColumns = @JoinColumn(name = "address_id"))
    private List<Address> addresses;
    

   
}
