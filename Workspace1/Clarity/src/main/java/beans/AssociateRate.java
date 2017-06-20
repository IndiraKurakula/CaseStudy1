package beans;

public class AssociateRate {

	private int AssociateID;
	private float Rate;

	public int getAssociateID() {
		return AssociateID;
	}

	public void setAssociateID(int associateID) {
		AssociateID = associateID;
	}

	public float getRate() {
		return Rate;
	}

	public void setRate(float rate) {
		Rate = rate;
	}

	public AssociateRate(int associateID, float rate) {
		super();
		AssociateID = associateID;
		Rate = rate;
	}

	public AssociateRate() {
		super();
	}
}
