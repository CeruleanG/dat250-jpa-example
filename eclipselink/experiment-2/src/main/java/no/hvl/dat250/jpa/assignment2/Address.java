package no.hvl.dat250.jpa.assignment2;

import java.util.List;

import javax.persistence.*;

import lombok.Data;

@Entity
@Data
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String street;
    private int number;
    
    @ManyToMany(mappedBy = "addresses")
    //@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    //@JoinTable
    private List<Person> owners;


}
