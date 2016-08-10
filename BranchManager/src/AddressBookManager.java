import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author gmarte
 *
 */
public interface AddressBookManager {
			 
	void addContact(Contact contact);
	
	void removeContact(int contactId);
	
	void printDefaultAddressBookContacts();
	
	void printUniqueContacts();
       
	void addAddressBook(AddressBook addressBook);
	
	Collection<Contact> getContacts();

}
