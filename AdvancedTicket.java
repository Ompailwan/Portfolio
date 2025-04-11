package PA03;

public class AdvancedTicket extends Ticket{

	/* Instance variable declaration*/
	protected int noOfDaysInAdvance;

	/* Default constructor*/
	AdvancedTicket()
	{
		/*Fill in to instantiate only the purchase date variable
			Note that this class does not have date variable nor
			date package*/
		super();
	}

	/*parameterized constructor*/
	public AdvancedTicket(String number, int days)
	{
		/*Fill in to instantiate the 1. purchase date variable
		2. ticket number
		3. days
		4. ticketprice using ticket calculation */
		this.ticketNumber = number;
		this.noOfDaysInAdvance = days;
		calculateTicket();
	}

	/*getter method*/
	public int getNoOfDaysInAdvance()
	{
		return noOfDaysInAdvance;
	}

	/*ticket calculation*/
	public void calculateTicket()
	{
		//fill in to calculate ticket cost based on number of days
		if (noOfDaysInAdvance >= 10) {
			this.ticketCost = 30.0;
		} else if (noOfDaysInAdvance < 10) {
			this.ticketCost = 40.0;
		}
	}

	@Override //overrided method to print the object details
	public String toString()
	{	
		return super.toString()+"\t"+noOfDaysInAdvance;
	}
}
