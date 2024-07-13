package Repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import Models.Season;

public class SeasonRepository {
	
	String jdbcURL = "jdbc:postgresql://localhost:5432/f1";
    String username = "postgres";
    String password = "rets99";
	
	
	
public List<Season> getSeasons(){
		
		List<Season> seasons = new ArrayList<Season>();
		
		try {
			// Establecer la conexion
			Connection conn = DriverManager.getConnection(jdbcURL, username, password);
			
			// Crear una sentencia
			Statement statement = conn.createStatement();
			
			// Ejecutar la consulta
			String sql = "SELECT * FROM seasons\r\n"
					+ "ORDER BY year;";
			ResultSet rs = statement.executeQuery(sql);
			
			// Procesar los resultados
			while(rs.next()) {
				int year = rs.getInt("year");
				
				Season season = new Season(year);
				seasons.add(season);
			}
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
		return seasons;
		
	}

}
