import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class AddressBookManagerImplTest {

	
	AddressBookManagerImpl impl = new AddressBookManagerImpl();
	int forRemoval;
		
	@Before
	public void setUp() throws Exception {
		
		Contact kevin = new Contact("Kevin Rudd","3333-4444");
		impl.addContact(kevin);		
		
		Contact tony = new Contact("Tony Abbot","2222-5555");
		impl.addContact(tony);
		
		this.forRemoval = kevin.getId();
		
	}

	@Test
	public void testAddContact() {		
		
		Contact julia = new Contact("Julia Roberts","1111-7777");
		impl.addContact(julia);
		assertEquals("contacts size must be 3", 3, impl.getAddressBook().getContacts().size());
		assertEquals("contacts has entry for Julia Roberts", julia.getName(), 
				impl.getAddressBook().getContacts().get(String.valueOf(julia.getId())).getName());		
					
	}

	@Test
	public void testRemoveContact() {	
		
		impl.removeContact(this.forRemoval);		
		assertEquals("Contact size must be 1", 1, impl.getAddressBook().getContacts().size());
		
	}

	@Test
	public void testPrintDefaultAddressBookContacts() {
		
		Contact malcom = new Contact("Malcolm Turnbull","3344-2211");
		impl.addContact(malcom);
		
		Contact donald = new Contact("Donald Trump","1234-5678");
		impl.addContact(donald);
		
		impl.printDefaultAddressBookContacts();
		//fail("Not yet implemented");
	}
	
	
	/*
	 * PRINT Unique Contacts by NAME
	 */
	@Test
	public void testPrintUniqueContacts() {
		
		//create new address book for othe branch
		AddressBook burwood = new AddressBook("BURWOOD");
		
		//this is duplicate contact from default branch @Setup
		Contact kevin = new Contact("Kevin Rudd","3333-4444");
		burwood.getContacts().put(kevin.getName(), kevin);			
		
		//this is duplicate contact from default branch @Setup
		Contact tony = new Contact("Tony Abbot","2222-5555");
		burwood.getContacts().put(tony.getName(), tony);
		
		//unique
		Contact malcom = new Contact("Malcolm Turnbull","3344-2211");
		burwood.getContacts().put(malcom.getName(), malcom);
		
		//unique
		Contact donald = new Contact("Donald Trump","1234-5678");
		burwood.getContacts().put(donald.getName(), donald);
		
		//add BURWOOD addressbook to registry
		impl.addAddressBook(burwood);
		
		assertEquals("Contact size must be 4", 4, impl.getContactSet().size());
		
		impl.printUniqueContacts();
	}

	

}
