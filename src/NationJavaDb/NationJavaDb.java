package NationJavaDb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class NationJavaDb {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		String url = "jdbc:mysql://localhost:3306/db-nation";
		String user = "root";
		String password ="root";
		
		
		Scanner scan = new Scanner(System.in);

		Connection con = null;
		try {
		    con = DriverManager.getConnection(url, user, password);
		    System.out.print("Inserisci il nome di una città: ");
		    
		    String city = scan.nextLine();
		    
		    System.out.println("");
		    
		    String sql = "SELECT countries.country_id as id_paese, countries.name as nome_paese, regions.name as nome_regione, continents.name as nome_continente\n"
		            + "FROM countries\n"
		            + "Inner join regions\n"
		            + "on countries.region_id = regions.region_id\n"
		            + "Inner join continents\n"
		            + "on regions.continent_id = continents.continent_id\n"
		            + "Where countries.name = ?\n"
		            + "Order by countries.name";
		    
		    try (PreparedStatement ps = con.prepareStatement(sql)) {
		        ps.setString(1, city);
		        
		        try (ResultSet rs = ps.executeQuery()) {
		            System.out.println("Id nazione\t\tnazione\t\t\t\t\t\t\tregione\t\t\t\t\t\t\t\t\t\t\t\tContinente");
		            if (rs.next()) {
		                System.out.println(
		                        rs.getString(1) + "\t\t\t" +
		                        rs.getString(2) + "\t\t\t\t\t\t\t" +
		                        rs.getString(3) + "\t\t\t\t\t\t\t\t\t\t\t" +
		                        rs.getString(4));
		            } else {
		                System.out.println("Nessuna città trovata con il nome " + city);
		            }
		        }
		    }
		} catch (SQLException ex) {
		    ex.printStackTrace();
		} finally {
		    if (con != null) {
		        con.close();
		    }
		}
		
		scan.close();

	}

}
