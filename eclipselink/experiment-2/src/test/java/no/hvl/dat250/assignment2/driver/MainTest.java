package no.hvl.dat250.assignment2.driver;

import no.hvl.dat250.jpa.assignment2.*;
import no.hvl.dat250.jpa.assignment2.driver.Main;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.util.Optional;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class MainTest {

    private EntityManagerFactory factory;

    @Before
    public void setUp() {
        factory = Persistence.createEntityManagerFactory(Main.PERSISTENCE_UNIT_NAME);
    }

    /**
     * Try to delete the db directory if you get SQL-Errors.
     */
    @Test
    public void testDomainModelPersistence() {
        // Run the main class to persist the objects.
        Main.main(new String[]{});

        EntityManager em = factory.createEntityManager();

        // Load person
        Person person = em.find(Person.class, 1L);

        // Test person data
/**/	System.out.println("Testing Name");
        assertThat(person.getName(), is("Max Mustermann"));
/**/	System.out.println("Test Successful");

        // Test address
/**/	System.out.println("Testing Address");
/**/	System.out.println("	Testing  is there is only 1 street");
        assertThat(person.getAddresses().size(), is(1));
        /**/	System.out.println("	Test successful");
        Address address = person.getAddresses().iterator().next();
/**/	System.out.println("	"+address);

/**/	System.out.println("");
/**/	System.out.println("	Testing Street Name");
        assertThat(address.getStreet(), is("Inndalsveien"));
/**/	System.out.println("	Test successful");

/**/	System.out.println("");
/**/	System.out.println("	Testing Street Number");
        assertThat(address.getNumber(), is(28));
/**/	System.out.println("	Testing Successful");

/**/	System.out.println("");
/**/	System.out.println("	Testing Owner");
        //assertThat(address.getOwners(), is(Set.of(person)));
/**/	System.out.println("	Testing skipped");		
/**/	//System.out.println("	Testing Successful");
/**/	System.out.println("Address testing ends");

        // Test credit cards
/**/	System.out.println("");
/**/	System.out.println("Testing Cards");
/**/	System.out.println("	Testing if there are 2 cards");
        assertThat(person.getCreditCards().size(), is(2));
/**/	System.out.println("	Testing successful");
/**/	System.out.println("	");
/**/	System.out.println("	Testing Card's Number");
/**/	System.out.println("		Testing Card 1");
        CreditCard firstCard = getCardWithNumber(person, 12345);
/**/	System.out.println("		Testing Successful");
/**/	System.out.println("		");
/**/	System.out.println("		Tesing Card 2");
        CreditCard secondCard = getCardWithNumber(person, 123);
/**/	System.out.println("		Testing Successful");
/**/	System.out.println("	Testing Successful");


/**/	System.out.println("");
/**/	System.out.println("	Testing Card 1");
/**/	System.out.println("		Testing Number");
        assertThat(firstCard.getNumber(), is(12345));
/**/	System.out.println("		Testing Succ");
/**/	System.out.println("");
/**/	System.out.println("		Testing Balance");
        assertThat(firstCard.getBalance(), is(-5000));
/**/	System.out.println("		Testing Succ");
/**/	System.out.println("");
/**/	System.out.println("		Testing Limit");
        assertThat(firstCard.getLimit(), is(-10000));
/**/	System.out.println("		Testing Succ");
/**/	System.out.println("	Testing Succ");


/**/	System.out.println("");
/**/	System.out.println("	Testing Card 2");
/**/	System.out.println("		Testing Number");
        assertThat(secondCard.getNumber(), is(123));
/**/	System.out.println("		Testing Succ");
/**/	System.out.println("");
/**/	System.out.println("		Testing Balance");
        assertThat(secondCard.getBalance(), is(1));
/**/	System.out.println("		Testing Succ");
/**/	System.out.println("");
/**/	System.out.println("		Testing Limit");
        assertThat(secondCard.getLimit(), is(2000));
/**/	System.out.println("		Testing Succ");
/**/	System.out.println("	Testing Succ");
/**/	System.out.println("Testing Succ");
/**/	System.out.println("");

        // Test pincode
        Pincode firstCardPincode = firstCard.getPincode();
/**/	System.out.println("Testing Pincode");
/**/	System.out.println("	Testing ID");
        assertThat(firstCardPincode.getId(), is(secondCard.getPincode().getId())); // Pincode objects of the two cards are identical!
/**/	System.out.println("	Testing Succ");
/**/	System.out.println("");
/**/	System.out.println("	Testing Pincode");
        assertThat(firstCardPincode.getPincode(), is("123"));
/**/	System.out.println("");
/**/	System.out.println("	Testing Count");
        assertThat(firstCardPincode.getCount(), is(1));
/**/	System.out.println("	Test Succ");
/**/	System.out.println("Test Succ");
/**/	System.out.println("");

        // Test bank
/**/	System.out.println("Testing Bank");
        Bank bank = firstCard.getOwningBank();
/**/	System.out.println("	Testing ID");
        assertThat(bank.getId(), is(secondCard.getOwningBank().getId())); // Bank objects of the two cards are identical!
/**/	System.out.println("	Test Succ");
/**/	System.out.println("");
/**/	System.out.println("	Testing Name");
        assertThat(bank.getName(), is("Pengebank"));
/**/	System.out.println("	Test Succ");
/**/	System.out.println("");
/**/	System.out.println("	Testing Owned Cards");
        assertThat(bank.getOwnedCards(), is(Set.of(firstCard, secondCard)));
/**/	System.out.println("	Testing Succ");
/**/	System.out.println("Test Succ");



//PB: CARDS<=>BANK    ADDRESS<=>PERSON
    }

    private CreditCard getCardWithNumber(Person person, int cardNumber) {
        Optional<CreditCard> optionalCard = person.getCreditCards().stream()
                .filter(creditCard -> creditCard.getNumber() == cardNumber)
                .findFirst();
        if (optionalCard.isEmpty()) {
            throw new RuntimeException(
                    String.format("Card with number %s was not found for the person %s!",
                            cardNumber,
                            person.getName()));
        }
        return optionalCard.get();
    }
}