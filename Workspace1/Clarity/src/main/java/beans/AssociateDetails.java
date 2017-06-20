package beans;

public class AssociateDetails {
	private int AssociateID;
	private String HCSCID;
	private String AssociateName;
	private String ProjectName;
	private String Location;
	private String OnsiteOffshore;
	private String ClarityAccess;
	private String HCSCEmailID;
	private String PhoneNumber;
	private String HCSCJoiningDate;
	private String LastClarityUpdateDate;
	private int Rate;
	private String Revenue;
	private String Date;
	private String Month;
	private int Hours;
	private int TotalHours;
	private String BacklogMonth;
	private int BacklogHours;
	private String BacklogAmount;

	public String getBacklogMonth() {
		return BacklogMonth;
	}

	public void setBacklogMonth(String backlogMonth) {
		BacklogMonth = backlogMonth;
	}

	public int getBacklogHours() {
		return BacklogHours;
	}

	public void setBacklogHours(int backlogHours) {
		BacklogHours = backlogHours;
	}

	public String getBacklogAmount() {
		return BacklogAmount;
	}

	public void setBacklogAmount(String backlogAmount) {
		BacklogAmount = backlogAmount;
	}

	public String getRevenue() {
		return Revenue;
	}

	

	public AssociateDetails(int associateID, String hCSCID, String associateName, String projectName, String location,
			String onsiteOffshore, String clarityAccess, String hCSCEmailID, String phoneNumber, String hCSCJoiningDate,
			String lastClarityUpdateDate, int rate, String revenue, String date, String month, int hours,
			int totalHours, String backlogMonth, int backlogHours, String backlogAmount) {
		super();
		AssociateID = associateID;
		HCSCID = hCSCID;
		AssociateName = associateName;
		ProjectName = projectName;
		Location = location;
		OnsiteOffshore = onsiteOffshore;
		ClarityAccess = clarityAccess;
		HCSCEmailID = hCSCEmailID;
		PhoneNumber = phoneNumber;
		HCSCJoiningDate = hCSCJoiningDate;
		LastClarityUpdateDate = lastClarityUpdateDate;
		Rate = rate;
		Revenue = revenue;
		Date = date;
		Month = month;
		Hours = hours;
		TotalHours = totalHours;
		BacklogMonth = backlogMonth;
		BacklogHours = backlogHours;
		BacklogAmount = backlogAmount;
	}

	public void setRevenue(String revenue) {
		Revenue = revenue;
	}

	public String getDate() {
		return Date;
	}

	public void setDate(String date) {
		Date = date;
	}

	public String getMonth() {
		return Month;
	}

	public void setMonth(String month) {
		Month = month;
	}

	public int getHours() {
		return Hours;
	}

	public void setHours(int hours) {
		Hours = hours;
	}

	public int getTotalHours() {
		return TotalHours;
	}

	public void setTotalHours(int totalHours) {
		TotalHours = totalHours;
	}

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

	public String getOnsiteOffshore() {
		return OnsiteOffshore;
	}

	public void setOnsiteOffshore(String onsiteOffshore) {
		OnsiteOffshore = onsiteOffshore;
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

	

	public AssociateDetails() {
		super();
	}

}
