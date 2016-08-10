import java.util.concurrent.atomic.AtomicInteger;


public class Contact {
	
	
	//just a counter for ID since there is no DB involve--
	private static AtomicInteger uniqueId=new AtomicInteger();
		
	private int instanceId;
	
	
	String name;
	String phone;
	
	Contact(){
		this.instanceId = uniqueId.incrementAndGet();
	}
	Contact(String name, String phone){
		this.name = name;
		this.phone = phone;
		this.instanceId = uniqueId.incrementAndGet();
	}
	
	//this is not part of the Test, this is just part of the Swing GUI
	Contact(int instanceId, String name, String phone){
		this.name = name;
		this.phone = phone;
		this.instanceId = instanceId;
	}
		
	public int getId() {
		return instanceId;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
	@Override
	public boolean equals(Object obj) {
		boolean isEqual = false;
		if (!(obj instanceof Contact))
            return false;
        if (obj == this)
            return true;

        Contact con = (Contact) obj;
        if (con.getName() == null) {
        	return true;
        }else{
        	if (con.getName().equals(name)){
           	 isEqual =  true;
            }
        }
             
       return isEqual;    
        
	}
	
	@Override
	public int hashCode() {
		  final int prime = 31;
		  int result = 17;
		  result = prime * result + ((name == null) ? 0 : name.hashCode());
		  
		  return result;
		
	}
		

}
