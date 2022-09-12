package no.hvl.dat250.jpa.assignment2;

import javax.persistence.*;

import lombok.Data;

@Entity
@Data
public class Pincode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String pincode;
    private int count;

   
}
