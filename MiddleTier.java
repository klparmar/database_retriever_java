import java.sql.*;

public class A02MiddleTier {
	//This class will contain your code for interacting with Database, acquire the query result and display it in the GUI text area.
	
	//various queries we will use. q1 is for conferences, q2 for journals, q3 for books
	public String q1 = "select name, evdate from event, eventconference where  id = EventID";
	public String q2 = "SELECT name, ActivityDate FROM event, eventjournal, activityhappens where  id = eventjournal.EventID and eventjournal.EventID = activityhappens.eventid and activityhappens.activitydate=(select max(activitydate) from activityhappens where activityhappens.EventID = eventjournal.EventID)";
	public String q3 = "SELECT name, ActivityDate FROM event, eventbook, activityhappens where  id = eventbook.EventID and eventbook.EventID = activityhappens.eventid and activityhappens.activitydate=(select max(activitydate) from activityhappens where activityhappens.EventID = eventbook.EventID)";
	
	String url = "jdbc:mysql://localhost:3306/a02schema";
	   String username = "root";
	   String password = "";
	   
	 
	 public String printResult(String query) {
			try (Connection connection = DriverManager.getConnection(url, username, password)) {

				Statement s = connection.createStatement();
				ResultSet r = s.executeQuery(query);

				String output = "";

				while (r.next()) {
					output += r.getString(1) + " "+r.getString(2) + "\n";

				}
				return output;

			} catch (Exception e) {	System.out.println(e.toString());return "AN ERROR OCCURRED.";}
		}
}