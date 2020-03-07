package ingsoft1920.cm.dao;

import java.math.BigInteger;
import java.sql.*;

import org.apache.commons.dbutils.QueryRunner;
import org.springframework.beans.factory.annotation.Autowired;

import ingsoft1920.cm.bean.Cliente;
import ingsoft1920.cm.conector.ConectorBBDD;

public class ClienteDAO {

		@Autowired
		private QueryRunner runner;
		
		private static ConectorBBDD conector = new ConectorBBDD();


		public int anadirCliente(String nombre, String dni, String email, String password) {

			String insertar = "INSERT INTO cliente (nombre,dni,email,password) VALUES (?,?,?,?)";
			int idCliente = 0;
			
			PreparedStatement statement = null;

	        ResultSet resultset = null;

	        try {
	            statement = conector.getConn().prepareStatement(insertar, Statement.RETURN_GENERATED_KEYS);
	            statement.setString(1, nombre);
	            statement.setString(2, dni);
	            statement.setString(3, email);
	            statement.setString(4, password);

	            statement.execute();
	            resultset = statement.getGeneratedKeys();

                if (resultset.next()) {
                    idCliente = resultset.getInt(1);
                }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return idCliente;
		}
		
		public Cliente login (String email, String password) {
			Cliente c = new Cliente();
			
			String clienteRegistrado = "SELECT * FROM Cliente where email= "+email+" and password = "+password;
			PreparedStatement statement = null;
	        ResultSet resultset = null;
	        
	        try {
	        	statement = conector.getConn().prepareStatement(clienteRegistrado);
	        	resultset = statement.executeQuery();
	        	
	        	if (resultset.next()){
	        		c.setId(resultset.getInt("id"));
	                c.setNombre(resultset.getString("nombre"));
	                c.setDNI(resultset.getString("dni"));
	                c.setEmail(resultset.getString("email"));
	                c.setPassword(resultset.getString("password"));
	            }

	        }catch (SQLException e) {
	            e.printStackTrace();
	        }
			return c;
		}
}
