package beans;


public class ClarityDetails 
{
private int AssociateID;
private String HCSCID;
private String AssociateName;
private String ProjectName;
private String Location;
private String ClarityAccess;
private String HCSCEmailID;
private String PhoneNumber;
private String HCSCJoiningDate;
private String LastClarityUpdateDate;
private int Rate;


public int getAssociateID() {
	return AssociateID;
}
public void setAssociateID(int associateID) {
	AssociateID = associateID;
}
public String getHCSCID() {
	return HCSCID;
}
public void setHCSCID(String hCSCID) {
	HCSCID = hCSCID;
}
public String getAssociateName() {
	return AssociateName;
}
public void setAssociateName(String associateName) {
	AssociateName = associateName;
}
public String getProjectName() {
	return ProjectName;
}
public void setProjectName(String projectName) {
	ProjectName = projectName;
}
public String getLocation() {
	return Location;
}
public void setLocation(String location) {
	Location = location;
}
public String getClarityAccess() {
	return ClarityAccess;
}
public void setClarityAccess(String clarityAccess) {
	ClarityAccess = clarityAccess;
}
public String getHCSCEmailID() {
	return HCSCEmailID;
}
public void setHCSCEmailID(String hCSCEmailID) {
	HCSCEmailID = hCSCEmailID;
}
public String getPhoneNumber() {
	return PhoneNumber;
}
public void setPhoneNumber(String phoneNumber) {
	PhoneNumber = phoneNumber;
}
public String getHCSCJoiningDate() {
	return HCSCJoiningDate;
}
public void setHCSCJoiningDate(String hCSCJoiningDate) {
	HCSCJoiningDate = hCSCJoiningDate;
}
public String getLastClarityUpdateDate() {
	return LastClarityUpdateDate;
}
public void setLastClarityUpdateDate(String lastClarityUpdateDate) {
	LastClarityUpdateDate = lastClarityUpdateDate;
}
public int getRate() {
	return Rate;
}
public void setRate(int rate) {
	Rate = rate;
}
public ClarityDetails(int associateID, String hCSCID, String associateName, String projectName, String location,
		String clarityAccess, String hCSCEmailID, String phoneNumber, String hCSCJoiningDate,
		String lastClarityUpdateDate, int rate) {
	super();
	AssociateID = associateID;
	HCSCID = hCSCID;
	AssociateName = associateName;
	ProjectName = projectName;
	Location = location;
	ClarityAccess = clarityAccess;
	HCSCEmailID = hCSCEmailID;
	PhoneNumber = phoneNumber;
	HCSCJoiningDate = hCSCJoiningDate;
	LastClarityUpdateDate = lastClarityUpdateDate;
	Rate = rate;
}
public ClarityDetails() {
	super();
}





}
