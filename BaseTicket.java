package PA03;

/*Interface for Ticket class*/
public interface BaseTicket {

/*Make the following 2 varibales final*/
double onCampusBaseCharge  = 1.25;
double offCampusBaseCharge = 2.34;

/*Fill in to declare the 2 abstract methods as mentioned in the requirement*/

public abstract void calculateTicket();

@Override
String toString();



}
