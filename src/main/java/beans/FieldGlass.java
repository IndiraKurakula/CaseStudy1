package beans;

public class FieldGlass {
	
	public int TimeSheetID;
	public int StartDate;
	public int EndDate;
	public String Worker;
	public String Site;
	public String CostCenter;
	public int BillRateSTHr;
	public int BillRateOTHr;
	public int BillRateDTHr;
	public int TimeSheetBillableHoursSTHr;
	public int TimeSheetBillableHoursOTHr;
	public int TimeSheetBillableHoursDTHr;
	public int TimeSheetBillableHours;
	public int TimeSheetNonbillableHours;
	public String Status;
	public int TimeSheetAmount;
	public int CreditDebitMemoAmount;
	public int PerDiemAmount;
	public int NetAmount;
	
	
	public int getTimeSheetID() {
		return TimeSheetID;
	}
	public void setTimeSheetID(int timeSheetID) {
		TimeSheetID = timeSheetID;
	}
	public int getStartDate() {
		return StartDate;
	}
	public void setStartDate(int startDate) {
		StartDate = startDate;
	}
	public int getEndDate() {
		return EndDate;
	}
	public void setEndDate(int endDate) {
		EndDate = endDate;
	}
	public String getWorker() {
		return Worker;
	}
	public void setWorker(String worker) {
		Worker = worker;
	}
	public String getSite() {
		return Site;
	}
	public void setSite(String site) {
		Site = site;
	}
	public String getCostCenter() {
		return CostCenter;
	}
	public void setCostCenter(String costCenter) {
		CostCenter = costCenter;
	}
	public int getBillRateSTHr() {
		return BillRateSTHr;
	}
	public void setBillRateSTHr(int billRateSTHr) {
		BillRateSTHr = billRateSTHr;
	}
	public int getBillRateOTHr() {
		return BillRateOTHr;
	}
	public void setBillRateOTHr(int billRateOTHr) {
		BillRateOTHr = billRateOTHr;
	}
	public int getBillRateDTHr() {
		return BillRateDTHr;
	}
	public void setBillRateDTHr(int billRateDTHr) {
		BillRateDTHr = billRateDTHr;
	}
	public int getTimeSheetBillableHoursSTHr() {
		return TimeSheetBillableHoursSTHr;
	}
	public void setTimeSheetBillableHoursSTHr(int timeSheetBillableHoursSTHr) {
		TimeSheetBillableHoursSTHr = timeSheetBillableHoursSTHr;
	}
	public int getTimeSheetBillableHoursOTHr() {
		return TimeSheetBillableHoursOTHr;
	}
	public void setTimeSheetBillableHoursOTHr(int timeSheetBillableHoursOTHr) {
		TimeSheetBillableHoursOTHr = timeSheetBillableHoursOTHr;
	}
	public int getTimeSheetBillableHoursDTHr() {
		return TimeSheetBillableHoursDTHr;
	}
	public void setTimeSheetBillableHoursDTHr(int timeSheetBillableHoursDTHr) {
		TimeSheetBillableHoursDTHr = timeSheetBillableHoursDTHr;
	}
	public int getTimeSheetBillableHours() {
		return TimeSheetBillableHours;
	}
	public void setTimeSheetBillableHours(int timeSheetBillableHours) {
		TimeSheetBillableHours = timeSheetBillableHours;
	}
	public int getTimeSheetNonbillableHours() {
		return TimeSheetNonbillableHours;
	}
	public void setTimeSheetNonbillableHours(int timeSheetNonbillableHours) {
		TimeSheetNonbillableHours = timeSheetNonbillableHours;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	public int getTimeSheetAmount() {
		return TimeSheetAmount;
	}
	public void setTimeSheetAmount(int timeSheetAmount) {
		TimeSheetAmount = timeSheetAmount;
	}
	public int getCreditDebitMemoAmount() {
		return CreditDebitMemoAmount;
	}
	public void setCreditDebitMemoAmount(int creditDebitMemoAmount) {
		CreditDebitMemoAmount = creditDebitMemoAmount;
	}
	public int getPerDiemAmount() {
		return PerDiemAmount;
	}
	public void setPerDiemAmount(int perDiemAmount) {
		PerDiemAmount = perDiemAmount;
	}
	public int getNetAmount() {
		return NetAmount;
	}
	public void setNetAmount(int netAmount) {
		NetAmount = netAmount;
	}
	public FieldGlass() {
		super();
		TimeSheetID = getTimeSheetID();
		StartDate = getStartDate();
		EndDate = getEndDate();
		Worker = getWorker();
		Site = getSite();
		CostCenter = getCostCenter();
		BillRateSTHr = getBillRateSTHr();
		BillRateOTHr = getBillRateOTHr();
		BillRateDTHr = getBillRateDTHr();
		TimeSheetBillableHoursSTHr = getTimeSheetBillableHoursSTHr();
		TimeSheetBillableHoursOTHr = getTimeSheetBillableHoursOTHr();
		TimeSheetBillableHoursDTHr = getTimeSheetBillableHoursDTHr();
		TimeSheetBillableHours = getTimeSheetBillableHours();
		TimeSheetNonbillableHours = getTimeSheetNonbillableHours();
		Status = getStatus();
		TimeSheetAmount = getNetAmount();
		CreditDebitMemoAmount = getCreditDebitMemoAmount();
		PerDiemAmount = getPerDiemAmount();
		NetAmount = getNetAmount();
	}
	
	

}
