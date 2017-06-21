package beans;

public class ClarityMonth {
	private int clarityMonthId;
	private int AssociateID;
	private String HCSCID;
	private String Month;
	private int TotalHours;
	private float Rate;
	private float Revenue;

	public int getClarityMonthId() {
		return clarityMonthId;
	}

	public void setClarityMonthId(int clarityMonthId) {
		this.clarityMonthId = clarityMonthId;
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

	public String getMonth() {
		return Month;
	}

	public void setMonth(String month) {
		Month = month;
	}

	public int getTotalHours() {
		return TotalHours;
	}

	public void setTotalHours(int totalHours) {
		TotalHours = totalHours;
	}

	public float getRate() {
		return Rate;
	}

	public void setRate(float rate) {
		Rate = rate;
	}

	public float getRevenue() {
		return Revenue;
	}

	public void setRevenue(float revenue) {
		Revenue = revenue;
	}

	public ClarityMonth(int clarityMonthId, int associateID, String hCSCID, String month, int totalHours, float rate,
			float revenue) {
		super();
		this.clarityMonthId = clarityMonthId;
		AssociateID = associateID;
		HCSCID = hCSCID;
		Month = month;
		TotalHours = totalHours;
		Rate = rate;
		Revenue = revenue;
	}

	public ClarityMonth() {
		super();
	}

}
