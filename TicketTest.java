package PA03;

import java.io.FileNotFoundException;
import java.io.*;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class TicketTest {

	/*Array of Audience object*/
	static Audience[] audienceList;
	
	//VAriable to store and display output
	public static String output ="";

	public static void main(String[] args) throws FileNotFoundException {

		/*Input variable declaration and initialization*/
		String name = "";
		int noOfAdvancedays = 0;
		int numberOfAudience = 0;
		int status = 0;
		String studentID="";
		String ticketNumber = "";

		/*Get number of audience and create the Audience array*/
		while (true) {
			try {
				numberOfAudience = Integer.parseInt(JOptionPane.showInputDialog("Enter Number of Audience:"));
				if (numberOfAudience <= 0) {
					throw new Exception();
				}
				break;
			} catch (Exception e) {
				int option = JOptionPane.showConfirmDialog(null, "Error: Invalid Number of Audience.\n" + "Would you like to try again?");
				if (option != JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}
		}
		
		//creating audience array with accepted value from user
		audienceList = new Audience[numberOfAudience];

		/*Loop over no of audience to create appropriate ticket objects*/
		for (int i = 0; i < numberOfAudience; i++) {

			//Fill in the logic to get the input data
			
			//Generating ticket number with help of math.random() function
			ticketNumber ="Tkt"+ String.valueOf(((int)Math.ceil(Math.random()* 1000)));
			
			//accepting entries for customer name
				while (true) {
				try {
					name = JOptionPane.showInputDialog("Enter Audience Name:");
					if (!name.matches("[a-zA-Z]+\\s+[a-zA-Z]+")) {
						throw new Exception();
					} 
						break;
				} catch (Exception ex) {
					int option = JOptionPane.showConfirmDialog(null, "Error: Invalid Audience Name.\n" + "Would you like to try again?");
					if (option != JOptionPane.YES_OPTION) {
						System.exit(0);
					}

				}
			}
				
				//Accepting values for days remaining for the event
				while (true) {
					try {
						noOfAdvancedays = Integer.parseInt(JOptionPane.showInputDialog("Enter Number of Days before the event"));
						if (noOfAdvancedays < 0) {
							throw new Exception();
						} else
							break;
					} catch (Exception ex) {
						int option = JOptionPane.showConfirmDialog(null, "Error: Invalid Number of Days before the event entered.\n" + "Would you like to try again?");
						if (option != JOptionPane.YES_OPTION) {
							System.exit(0);
						}

					}
				}
				
				//Checking if the event is today or not to so walkup tickets can be given if days remaining is 0 
				if (noOfAdvancedays != 0) {
					//accepting choice for Is customer a student
					while (true) {
						try {
							status = Integer.parseInt(JOptionPane.showInputDialog("Are you a student?: 1: Yes 0: No"));
							if ((status < 0) || (status > 1)) {
								throw new Exception();
							} else 
								break;
						} catch (Exception ex) {
							int option = JOptionPane.showConfirmDialog(null, "Error: Invalid Entry.\n" + "Would you like to try again?");
							if (option != JOptionPane.YES_OPTION) {
								System.exit(0);
							}

						}
					}
					
					//Checking on if student is customer 
					if (status == 0) {
						addAdvancedTicket(name, ticketNumber, noOfAdvancedays);
					} else {
						
						while (true) {
							try {
								studentID = JOptionPane.showInputDialog("Enter Student ID:");
								if (studentID.equals("")) {
									throw new Exception();
								} else
									break;
							} catch (Exception ex) {
								int option = JOptionPane.showConfirmDialog(null, "Invalid: Entry.\n" + "Would you like to try again?");
								if (option != JOptionPane.YES_OPTION) {
									System.exit(0);
								}
							}
						}
						
						//Checking on if student is Oncampus or offcampus
						while (true) {
							try {
								status = Integer.valueOf(JOptionPane.showInputDialog("Are you an on-campus student?:1: Yes 0: No"));
								if ((status < 0) || (status > 1)) {
									throw new Exception();
								} else
									break;
							} catch (Exception ex) {
								int option = JOptionPane.showConfirmDialog(null, "Error: Invalid Entry.\n" + "Would you like to try again?");
								if (option != JOptionPane.YES_OPTION) {
									System.exit(0);
								}

							}
						}
						
						if (status == 0) {
							addOffcampusAdvanceTicket(name, ticketNumber, noOfAdvancedays, studentID);
						} else
							addOncampusAdvanceTicket(name, ticketNumber, noOfAdvancedays, studentID);
					}
				} 
				//Calling walkup class and its method if days remaing for event is 0
				else {
					addWalkupTicket(name, ticketNumber);
				}
				
		}

		display("Before Sorting"); // display the unsorted list of audience details
		selectionSort(); // sort the list based on ticketPrice
		display("After Sorting"); //display the sorted list
		writeToFile(); //write the sorted list to output file
	}

	/*Method to create Advanced Ticket object and add to audience array*/
	private static void addAdvancedTicket(String name, String ticketNumber, int days) {
		audienceList[Audience.getNoOfAudience()]  = new Audience(name, new AdvancedTicket(ticketNumber, days));
	}

	/*Method to create Off campus Advanced Ticket object and add to audience array*/
	private static void addOffcampusAdvanceTicket(String name, String ticketNumber, int days, String ID) {
		audienceList[Audience.getNoOfAudience()]  = new Audience(name, new OffcampusStudentAdvanceTicket(ticketNumber, days, ID));
	}

	/*Method to create On campus Advanced Ticket object and add to audience array*/
	private static void addOncampusAdvanceTicket(String name, String ticketNumber, int days, String ID) {
		audienceList[Audience.getNoOfAudience()]  = new Audience(name, new OncampusStudentAdvanceTicket(ticketNumber, days, ID));
	}

	/*Method to create Walkup Ticket object and add to audience array*/
	public static void addWalkupTicket(String name, String ticketNumber){
		audienceList[Audience.getNoOfAudience()] = new Audience(name, new WalkupTicket(ticketNumber));
	}

	/*Method to display audience object details*/
	public static void display(String s ){
		//creating variable for heading of output
		String heading=s;
		output = "Name\t" + "Ticket Number\t" + "Ticket Price\t" + "Date Purchased\t" + "Days advance\t" + "Student ID\t" + "Is On-campus\t";
		
		for (int i = 0; i < audienceList.length; i++)
			output += "\n" + audienceList[i].toString();
		//Displaying Output
		JOptionPane.showMessageDialog(null, new JTextArea(output), heading, JOptionPane.INFORMATION_MESSAGE);

	}

	/*Method to sort audience object based on ticketCost using compareTo() method*/
	public static void selectionSort() {
		for (int i = 0; i < Audience.getNoOfAudience() - 1; i++) {
			//initializing values to start with selection sort
			Audience currentMin = audienceList[i];
			int currentMinIndex = i;
			
			//sorting the values with respect to amount
			for (int j = i + 1; j < Audience.getNoOfAudience(); j++) {
				if (currentMin.compareTo(audienceList[j]) > 0) {
					currentMin = audienceList[j];
					currentMinIndex = j;
				}
			}
			if (currentMinIndex != i) {
				audienceList[currentMinIndex] = audienceList[i];
				audienceList[i] = currentMin;
			}
		}
		
		//Displaying message once completed with sorting of array
		JOptionPane.showMessageDialog(null, "Done Sorting Array.");

	}//end selection sort

	/*Method to write audience object details into an output file*/
	public static void writeToFile() throws FileNotFoundException {
		
		//Creating a file Audience.txt
		File file = new File("Audience.txt");
		
		PrintWriter output = new PrintWriter(file);
		
		for (int i = 0; i < Audience.getNoOfAudience(); i++)
			output.println(audienceList[i].toString());

		//Closing the file Audience.txt
		output.close();
		
		//Displaying message once file is done writing
		JOptionPane.showMessageDialog(null, "Done Writing in file");


	}//end write to file

}//end TicketTest Class
