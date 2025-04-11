package PA03;

import java.util.Date;

public abstract class Ticket implements BaseTicket{

	/* Instance variable declaration*/
	protected String ticketNumber;
	protected double ticketCost;
	protected Date purchaseDate;

	/* Default constructor*/
	Ticket()
	{
		/*Fill in to instantiate only the purchase date variable
			Note that this is the only class that has date variable and
			date package*/
		this.purchaseDate = new Date();
	}

	/*getter methods*/
	public String getTicketNumer()
	{
		return ticketNumber;
		
	}

	public Date getPurchaseDate()
	{
		return purchaseDate;
		//return
	}

	@Override //method to print ticket number and ticket cost
	public String toString()
	{
		return ticketNumber+"\t"+ticketCost+"\t"+purchaseDate;
		//return
	}
	
	//retained abstract method
	public abstract void calculateTicket();
}
