import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;


public class AddressBook {
	
	//just a counter for ID since there is no DB involve--
	private static AtomicInteger uniqueId=new AtomicInteger();
	
	private int instanceId;
	
	
	String branchName;
	
	Map<String,Contact> contacts;
	
	//this should have been instantiated via injection if we use Spring--	
	
	AddressBook(String branchName){
		this.branchName = branchName;
		this.contacts = new ConcurrentHashMap<String,Contact>();
		this.instanceId = uniqueId.incrementAndGet();
	}

	public int getId() {
		return instanceId;
	}

	
	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public Map<String,Contact> getContacts() {
		return this.contacts;
	}

	public void setContacts(Map<String,Contact> contacts) {
		this.contacts = contacts;
	}

	
	
}
