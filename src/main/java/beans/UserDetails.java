package beans;

public class UserDetails 
{
	private int AssociateID;
	private String UserRole;
	private int UserValue;
	
	public int getAssociateID() {
		return AssociateID;
	}
	public void setAssociateID(int associateID) {
		AssociateID = associateID;
	}
	public String getUserRole() {
		return UserRole;
	}
	public void setUserRole(String userRole) {
		UserRole = userRole;
	}
	public int getUserValue() {
		return UserValue;
	}
	public void setUserValue(int userValue) {
		UserValue = userValue;
	}
	
	public UserDetails(int associateID, String userRole, int userValue) {
		super();
		AssociateID = associateID;
		UserRole = userRole;
		UserValue = userValue;
	}
	
	public UserDetails() {
		super();
	}
	
	
}
