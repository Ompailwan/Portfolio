package PA03;

/*Audience class to perform manipulations on Ticket class objects*/
public class Audience implements Comparable<Audience> {

	/* Instance variable declaration*/
	private String name;
	private Ticket ticket;
	private static int noOfAudience;

	/*default constructor*/
	public Audience() {
		//Fill in
		noOfAudience++;
	}

	/*parameterized constructor*/
	public Audience(String name, Ticket ticket) {
		//Fill in
		this.name = name;
		this.ticket = ticket;
		noOfAudience++;
	}

	/*Getter and setter methods*/
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Ticket getticket() {
		return ticket;
	}

	public void setticket(Ticket ticket) {
		this.ticket = ticket;
	}

	public static int getNoOfAudience() {
		return noOfAudience;
	}

	@Override //method to name and ticket object details
	public String toString() {
		return name+"\t"+ticket.toString();
	}

	@Override //method to compare the ticket cost of 2 objects
	public int compareTo(Audience aud1) {
		return Double.compare(this.ticket.ticketCost, aud1.ticket.ticketCost);
		
	}
}
