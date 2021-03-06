package com.example.demo.model;


import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;



// the CardData class is only created to organize the getCards endpoint results
public class CardData {

	private long daysSinceCreated;
	private String slaStatus;
	private int patientid;
	private String patientname;
	private int healthInsuranceid;
	private String healthInsuranceName;
	private int visitId;
	private int billid;
	private String billtype;
	private double totalAmount;
	private int numberOfPendencies;
	private int numberOfOpenPendencies;
	private int numberOfDocuments;
	private int numberOfNotReceivedDocuments;
	private int numberOfChecklistItem;
	private int numberOfDoneChecklistItem;
	
	//constructor
	public CardData(Card card) {
		Activity activity = card.getActivity();
		Patient patient = card.getPatient();
		HealthInsurance healthInsurance = card.getHealthinsurance();
		Bill bill = card.getBill();
		HashMap<String,Boolean> pendencies = card.getPendencies();
		HashMap<String,Boolean> documents = card.getDocuments();
		HashMap<String,Boolean> checklist = card.getChecklist();
		
		LocalDate currentDate = LocalDate.now();
		LocalDate cardDate = card.getCreatedIn();
		this.daysSinceCreated = ChronoUnit.DAYS.between(cardDate, currentDate);
		
		double slastatus = (double)daysSinceCreated/(double)activity.getSla();
		
		if(slastatus < 0.75) //check sla status
		{
			this.slaStatus = "OK";
		}else {
			if(slastatus <= 1) {
				this.slaStatus = "WARNING";
			}
			else {
				this.slaStatus = "DELAYED";
			}
		}
		
		this.patientid = patient.getPatientId();
		this.patientname = patient.getName();
		
		this.healthInsuranceid = healthInsurance.getHealthInsuranceId();
		this.healthInsuranceName = healthInsurance.getName();
		
		this.visitId = card.getVisitId();
		
		this.billid = bill.getBillid();
		this.billtype = bill.getBilltype();
		
		this.totalAmount = bill.getTotalamount();
		
		this.numberOfPendencies = pendencies.size();
		
		
		HashMap<String,Boolean> openPendencies = checkPendencesDocumentsChecklist(pendencies);
		this.numberOfOpenPendencies = openPendencies.size();
		
		this.numberOfDocuments = documents.size();
		
		HashMap<String,Boolean> notReceivedDocuments = checkPendencesDocumentsChecklist(documents);
		this.numberOfNotReceivedDocuments = notReceivedDocuments.size();
		
		this.numberOfChecklistItem = checklist.size();
		
		HashMap<String,Boolean> notDoneChecklistItens = checkPendencesDocumentsChecklist(checklist);
		this.numberOfDoneChecklistItem = checklist.size() - notDoneChecklistItens.size();
		

	}

	
	//this method returns a submap with only the pairs who have the value = false
	private HashMap<String, Boolean> checkPendencesDocumentsChecklist(HashMap<String, Boolean> pendencies) {
		HashMap<String,Boolean> notDone = new HashMap<String,Boolean>();
		pendencies.forEach((key,value)->{
			if(value == false) notDone.put(key, value);
		});
		return notDone;
		
	}

	

	//getters and setters
	public long getDaysSinceCreated() {
		return daysSinceCreated;
	}

	public void setDaysSinceCreated(long daysSinceCreated) {
		this.daysSinceCreated = daysSinceCreated;
	}

	public String getSlaStatus() {
		return slaStatus;
	}

	public void setSlaStatus(String slaStatus) {
		this.slaStatus = slaStatus;
	}

	public int getPatientid() {
		return patientid;
	}

	public void setPatientid(int patientid) {
		this.patientid = patientid;
	}

	public String getPatientname() {
		return patientname;
	}

	public void setPatientname(String patientname) {
		this.patientname = patientname;
	}

	public int getHealthInsuranceid() {
		return healthInsuranceid;
	}

	public void setHealthInsuranceid(int healthInsuranceid) {
		this.healthInsuranceid = healthInsuranceid;
	}

	public String getHealthInsuranceName() {
		return healthInsuranceName;
	}

	public void setHealthInsuranceName(String healthInsuranceName) {
		this.healthInsuranceName = healthInsuranceName;
	}

	public int getVisitId() {
		return visitId;
	}

	public void setVisitId(int visitId) {
		this.visitId = visitId;
	}

	public int getBillid() {
		return billid;
	}

	public void setBillid(int billid) {
		this.billid = billid;
	}

	public String getBilltype() {
		return billtype;
	}

	public void setBilltype(String billtype) {
		this.billtype = billtype;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public int getNumberOfPendencies() {
		return numberOfPendencies;
	}

	public void setNumberOfPendencies(int numberOfPendencies) {
		this.numberOfPendencies = numberOfPendencies;
	}

	public int getNumberOfOpenPendencies() {
		return numberOfOpenPendencies;
	}

	public void setNumberOfOpenPendencies(int numberOfOpenPendencies) {
		this.numberOfOpenPendencies = numberOfOpenPendencies;
	}

	public int getNumberOfDocuments() {
		return numberOfDocuments;
	}

	public void setNumberOfDocuments(int numberOfDocuments) {
		this.numberOfDocuments = numberOfDocuments;
	}

	public int getNumberOfNotReceivedDocuments() {
		return numberOfNotReceivedDocuments;
	}

	public void setNumberOfNotReceivedDocuments(int numberOfNotReceivedDocuments) {
		this.numberOfNotReceivedDocuments = numberOfNotReceivedDocuments;
	}

	public int getNumberOfChecklistItem() {
		return numberOfChecklistItem;
	}

	public void setNumberOfChecklistItem(int numberOfChecklistItem) {
		this.numberOfChecklistItem = numberOfChecklistItem;
	}

	public int getNumberOfDoneChecklistItem() {
		return numberOfDoneChecklistItem;
	}

	public void setNumberOfDoneChecklistItem(int numberOfDoneChecklistItem) {
		this.numberOfDoneChecklistItem = numberOfDoneChecklistItem;
	}


	
}
