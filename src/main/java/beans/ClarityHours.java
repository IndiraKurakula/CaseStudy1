package beans;

public class ClarityHours 
{
	private int clarityhoursid;
	private int AssociateID;
	private String HCSCID;
	private String Date;
	private int Year;
	private int Month;
	private int Hours;
	private String BacklogMonth;
	private int BacklogHours;
	private String BacklogAmount;
	private String Revenue;
	public int getClarityhoursid() {
		return clarityhoursid;
	}
	public void setClarityhoursid(int clarityhoursid) {
		this.clarityhoursid = clarityhoursid;
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
	public String getDate() {
		return Date;
	}
	public void setDate(String date) {
		Date = date;
	}
	public int getYear() {
		return Year;
	}
	public void setYear(int year) {
		Year = year;
	}
	public int getMonth() {
		return Month;
	}
	public void setMonth(int month) {
		Month = month;
	}
	public int getHours() {
		return Hours;
	}
	public void setHours(int hours) {
		Hours = hours;
	}
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
	public void setRevenue(String revenue) {
		Revenue = revenue;
	}
	public ClarityHours(int clarityhoursid, int associateID, String hCSCID, String date, int year, int month, int hours,
			String backlogMonth, int backlogHours, String backlogAmount, String revenue) {
		super();
		this.clarityhoursid = clarityhoursid;
		AssociateID = associateID;
		HCSCID = hCSCID;
		Date = date;
		Year = year;
		Month = month;
		Hours = hours;
		BacklogMonth = backlogMonth;
		BacklogHours = backlogHours;
		BacklogAmount = backlogAmount;
		Revenue = revenue;
	}
	public ClarityHours() {
		super();
	}
	
	
	
}
