package Repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Models.ConstructorResult;

public class ConstructorRepository {
    String jdbcURL = "jdbc:postgresql://localhost:5432/f1";
    String username = "postgres";
    String password = "rets99";
    
    public List<ConstructorResult> getResultByYear(int year) {
        
        List<ConstructorResult> results = new ArrayList<>();
        
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        
        try {
            // Establecer la conexion
            conn = DriverManager.getConnection(jdbcURL, username, password);
            
            // Ejecutar la consulta
            String sql = "SELECT\n"
                    + "    r.year,\n"
                    + "    co.name,\n"
                    + "    COUNT(CASE WHEN res.position = 1 THEN 1 END) AS wins,\n"
                    + "    SUM(res.points) AS total_points,\n"
                    + "    RANK() OVER (PARTITION BY r.year ORDER BY SUM(res.points) DESC) AS season_rank\n"
                    + "FROM\n"
                    + "    results res\n"
                    + "JOIN\n"
                    + "    races r ON res.race_id = r.race_id\n"
                    + "JOIN\n"
                    + "    constructors co ON res.constructor_id = co.constructor_id\n"
                    + "\n"
                    + "WHERE r.year = ?\n"
                    + "GROUP BY\n"
                    + "    r.year, co.constructor_id, co.name\n"
                    + "ORDER BY\n"
                    + "    r.year, season_rank;";
            
            // Crear una sentencia
            statement = conn.prepareStatement(sql);
            statement.setInt(1, year);
            
            rs = statement.executeQuery();
            
            // Procesar los resultados
            while(rs.next()) {
                String name = rs.getString("name");
                int wins = rs.getInt("wins");
                int total_points = rs.getInt("total_points");
                int season_rank = rs.getInt("season_rank");
                
                ConstructorResult result = new ConstructorResult(name, wins, total_points, season_rank);
                results.add(result);
            }
            
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (statement != null) statement.close();
                if (conn != null) conn.close();
            } catch(Exception ex) {
                ex.printStackTrace();
            }
        }
        
        return results;
    }
}
