package PA03;

public class OncampusStudentAdvanceTicket extends AdvancedTicket {

	/* Instance variable declaration*/
	private String studentID;
	private boolean status;

	/*parameterized constructor*/
	public OncampusStudentAdvanceTicket(String number, int days, String ID)
	{
		/*Fill in to instantiate the 1. purchase date variable
		2. ticket number
		3. days
		4. student ID
		5. student status
		6. ticketprice using ticket calculation
		*/
		super();
		this.ticketNumber = number;
		this.noOfDaysInAdvance = days;
		this.studentID = ID;
		this.status = true;
		calculateTicket();
	}

	/*getter methods*/
	public String getStudentID()
	{
		return studentID;
	}

	public boolean getStatus()
	{
		return status;
	}

	/*ticket calculation*/
	public void calculateTicket()
	{
		/*fill in to calculate ticket cost based on number of days
		Make sure to include the base charges*/
		if (noOfDaysInAdvance >= 10) {
			this.ticketCost = 15.0 + onCampusBaseCharge;
		} else if (noOfDaysInAdvance < 10) {
			this.ticketCost = 20.0 + onCampusBaseCharge;
		}
	}

	@Override //overrided method to print the object details
	public String toString()
	{
		return super.toString() +"\t"+ this.studentID +"\t"+ this.status;
	}
}
