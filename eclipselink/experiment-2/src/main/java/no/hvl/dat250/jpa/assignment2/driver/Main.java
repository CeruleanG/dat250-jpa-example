package no.hvl.dat250.jpa.assignment2.driver;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import no.hvl.dat250.jpa.assignment2.Address;
import no.hvl.dat250.jpa.assignment2.Bank;
import no.hvl.dat250.jpa.assignment2.CreditCard;
import no.hvl.dat250.jpa.assignment2.Person;
import no.hvl.dat250.jpa.assignment2.Pincode;

public class Main {
    public static final String PERSISTENCE_UNIT_NAME = "experiment2";

    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();

        em.getTransaction().begin();
        // Creation of the different variables
        Person person = new Person();
        person.setName("Max Mustermann");
        
        Address address = new Address();
        address.setStreet("Inndalsveien");
        address.setNumber(28);
        
        
        CreditCard creditCard1 = new CreditCard();
        creditCard1.setNumber(12345);
        creditCard1.setBalance(-5000);
        creditCard1.setLimit(-10000);
        
        Pincode pincode = new Pincode();
        pincode.setPincode("123");
        pincode.setCount(1);
        
        CreditCard creditCard2 = new CreditCard();
        creditCard2.setNumber(123);
        creditCard2.setBalance(1);
        creditCard2.setLimit(2000);
        
        Bank bank = new Bank();
        bank.setName("Pengebank");
        
        // Associations of the variables
        List<Address> adresses = new ArrayList<>();
        adresses.add(address);
        person.setAddresses(adresses);
        
        List<Person> persons = new ArrayList<>();
        persons.add(person);
        address.setOwners(persons);
               
        
        creditCard1.setOwningBank(bank);
        creditCard1.setPincode(pincode);

        
        creditCard2.setOwningBank(bank);
        creditCard2.setPincode(pincode);
        
        List<CreditCard> creditCards = new ArrayList<>();
        creditCards.add(creditCard1);
        creditCards.add(creditCard2);
        
        bank.setOwnedCards(creditCards);
        
        person.setCreditCards(creditCards);
        
        
        
        em.persist(person);
        em.persist(creditCard1);
        em.persist(creditCard2);
        em.persist(address);
        em.persist(pincode);
        em.persist(bank);
        em.getTransaction().commit();

        //em.close();
        System.out.println("Exit");
    }
}
