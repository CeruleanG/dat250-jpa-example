package no.hvl.dat250.jpa.assignment2;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

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
    private Set<Person> owners = new HashSet<>();

    public String toString() 
    {
    	return id+" "+street;
    }

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Address other = (Address) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

}
