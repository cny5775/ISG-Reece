import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

/**
 * 
 */

/**
 * @author gmarte
 *
 */
public class AddressBookManagerImpl implements AddressBookManager {
	
	public AddressBook defaultAddressBook;
	public volatile List<AddressBook> addressBookRegistry;
	
	//private int uniqueContactCounts;
	private HashSet<Contact> contactSet;
	
	AddressBookManagerImpl(){
		this.defaultAddressBook = new AddressBook("Chatswood");
		this.addressBookRegistry =  new ArrayList<AddressBook>();
		this.contactSet = new HashSet<Contact>();
		addAddressBook(defaultAddressBook);
	}

	/* (non-Javadoc)
	 * @see AddressBookManager#addContact(Contact)
	 */
	
	@Override
	public void addContact(Contact contact) {
		// TODO Auto-generated method stub
		
		if(null==this.defaultAddressBook.getContacts().get(String.valueOf(contact.getId()))){
			this.defaultAddressBook.getContacts().put(String.valueOf(contact.getId()),contact);
		}

	}

	/* (non-Javadoc)
	 * @see AddressBookManager#removeContact(Contact)
	 */
	@Override
	public void removeContact(int contactId) {		
		this.defaultAddressBook.getContacts().remove(String.valueOf(contactId));		
	}

	/* (non-Javadoc)
	 * @see AddressBookManager#printContacts()
	 */
	@Override
	public void printDefaultAddressBookContacts() {
		// TODO Auto-generated method stub
		System.out.println("****************** AddressBook's Contact List *********************\n");
		List<Contact> contacts = new ArrayList<Contact>(defaultAddressBook.getContacts().values());
		Collections.sort(contacts, new Comparator<Contact>() {
			@Override
			public int compare(Contact contact1, Contact contact2) {
				// TODO Auto-generated method stub
				return contact1.getName().compareTo(contact2.getName());
			}
		});
		for(Contact contact:contacts){
			System.out.println("ID: " + contact.getId()  + ", Name:" + contact.getName() + ", phone:" + contact.getPhone() );
		}

	}

	public AddressBook getAddressBook() {
		return defaultAddressBook;
	}

	public void setAddressBook(AddressBook addressBook) {
		this.defaultAddressBook = addressBook;
	}

	
	@Override
	public Collection<Contact> getContacts() {
		// TODO Auto-generated method stub
		return defaultAddressBook.getContacts().values();
	}

	@Override
	public void addAddressBook(AddressBook addressBook) {
		this.addressBookRegistry.add(addressBook);
		if(null == contactSet){
			contactSet = new HashSet<Contact>();
		}		
		for(Contact contact : addressBook.getContacts().values()){
				contactSet.add(contact);
		}	
		
	}

	@Override
	public void printUniqueContacts() {
		System.out.println("****************** Registry Contact List *********************\n");
		
		List<Contact> uniqueContacts = new ArrayList<Contact>(contactSet);
		Collections.sort(uniqueContacts, new Comparator<Contact>() {
			@Override
			public int compare(Contact contact1, Contact contact2) {
				// TODO Auto-generated method stub
				return contact1.getName().compareTo(contact2.getName());
			}
		});
		
		for(Contact contact:uniqueContacts){
			System.out.println("ID: " + contact.getId()  + ", Name:" + contact.getName() + ", phone:" + contact.getPhone() );
		}
		
	}

	public HashSet<Contact> getContactSet() {
		return contactSet;
	}

}
