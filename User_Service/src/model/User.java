package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class User {
	
	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/paf?useTimezone=true&serverTimezone=UTC","root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public String addUserDetails(String Ruser_name, String Ruser_address, String Ruser_gender, String Ruser_age, String Ruser_notes) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement  
			String query = " insert into reg_user(`Ruser_ID`,`Ruser_name`,`Ruser_address`,`Ruser_gender`,`Ruser_age`,`Ruser_notes`)"
					+ " values (?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, Ruser_name);
			preparedStmt.setString(3, Ruser_address);
			preparedStmt.setString(4, (Ruser_gender));
			preparedStmt.setString(5, Ruser_age);
			preparedStmt.setString(6, Ruser_notes);
		
			// execute the statement
			preparedStmt.execute();
			con.close();
			//output = "Inserted successfully";
			String newUsers = readUsers();
			 output = "{\"status\":\"success\", \"data\": \"" +newUsers + "\"}";
			
		} catch (Exception e) {
			//output = "Error while inserting the user.";
			output = "{\"status\":\"error\", \"data\":\"Error while inserting the user.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String readUsers() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border=\'1\'><tr><th>User Name</th><th>User Address</th><th>Gender</th><th>Age</th><th>User Notes</th></tr>";
			String query = "select * from reg_user";
			Statement stmt = (Statement) con.createStatement();
			ResultSet rs = ((java.sql.Statement) stmt).executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String Ruser_ID = Integer.toString(rs.getInt("Ruser_ID"));
				String Ruser_name = rs.getString("Ruser_name");
				String Ruser_address = rs.getString("Ruser_address");
				String Ruser_gender = rs.getString(rs.getInt("Ruser_gender"));
				String Ruser_age = rs.getString("Ruser_age");
				String Ruser_notes = rs.getString("Ruser_notes");
		
				// Add into the html table
				output += "<tr><td><input id='hidRuser_IDUpdate' name='hidRuser_IDUpdate' type='hidden' value='" + Ruser_ID + "'>" + Ruser_name + "</td>";
				
				/*output += "<tr><td><input id=\"hidUserIDUpdate\"name=\"hidUserIDUpdate\"type=\"hidden\" value=\""
						+ userID + "\">" + userName + "</td>";*/
				
				output += "<td>" + Ruser_address + "</td>";
				output += "<td>" + Ruser_gender + "</td>";
				output += "<td>" + Ruser_age + "</td>";
				output += "<td>" + Ruser_notes + "</td>";
			
				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td><td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-userid='"
						 + Ruser_ID + "'>" + "</td></tr>";
				
				/*output += "<td><input name=\"btnUpdate\"type=\"button\" value=\"Update\"class=\" btnUpdate btn btn-secondary\"></td><td><form method=\"post\" action=\"User.jsp\"><input name=\"btnRemove\" type=\"submit\"value=\"Remove\" class=\"btn btn-danger\"><input name=\"hidUserIDDelete\" type=\"hidden\"value=\""
						+ userID + "\">" + "</form></td></tr>";*/
				
				/*
				 * output +=
				 * "<td><input name=\"btnUpdate\" type=\"button\"value=\"Update\" class=\"btn btn-secondary\"></td>"
				 * + "<td><form method=\"post\" action=\"items.jsp\">" +
				 * "<input name=\"btnRemove\" type=\"submit\" value=\"Remove\"class=\"btn btn-danger\">"
				 * + "<input name=\"userID\" type=\"hidden\" value=\"" + userID + "\">" +
				 * "</form></td></tr>";
				 */
			}
			con.close();
			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the users.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String updateUserDetails(String Ruser_ID, String Ruser_name, String Ruser_address, String Ruser_gender, String Ruser_age, String Ruser_notes) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
			// create a prepared statement
			String query = "UPDATE reg_user SET Ruser_name=?,Ruser_address=?,Ruser_gender=?,Ruser_age=?,Ruser_notes=?WHERE Ruser_ID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setString(1, Ruser_name);
			preparedStmt.setString(2, Ruser_address);
			preparedStmt.setString(3, (Ruser_gender));
			preparedStmt.setString(4, Ruser_age);
			preparedStmt.setString(5, Ruser_notes);
			preparedStmt.setInt(6, Integer.parseInt(Ruser_ID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			//output = "Updated successfully";
			
			String newUsers = readUsers();
			 output = "{\"status\":\"success\", \"data\": \"" +newUsers + "\"}";
			
		} catch (Exception e) {
			//output = "Error while updating the user.";
			output = "{\"status\":\"error\", \"data\":\"Error while updating the user.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String deleteUsers(String Ruser_ID) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
			// create a prepared statement
			String query = "delete from reg_user where Ruser_ID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(Ruser_ID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			//output = "Deleted successfully";
			String newUsers = readUsers();
			 output = "{\"status\":\"success\", \"data\": \"" +newUsers + "\"}";
			
		} catch (Exception e) {
			//output = "Error while deleting the user.";
			output = "{\"status\":\"error\", \"data\":\"Error while deleting the user.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}

}
	
	
	
	
	
	
	

