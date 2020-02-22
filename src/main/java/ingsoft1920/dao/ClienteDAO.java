package ingsoft1920.dao;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.springframework.beans.factory.annotation.Autowired;

import ingsoft1920.cm.bean.Cliente;
import ingsoft1920.conector.conectorBBDD;

public class ClienteDAO {
		@Autowired
		private QueryRunner runner;
		
		private static conectorBBDD conector = new conectorBBDD("localhost:3306", "root", "Arturo26118", "bd-reservas-cm");

		public int anadirCliente(String nombre, String dni, String email, String password) {

			String insertar = "INSERT INTO cliente (nombre,dni,email,password) VALUES (?,?,?,?)";
			String obtenerId = "SELECT id FROM cliente WHERE name= "+ nombre;
			Integer idCliente = null;
			
			PreparedStatement statement = null;
			PreparedStatement statement2 = null;
	        ResultSet resultset = null;
	        ResultSet resultset2 = null;

	        try {
	            statement = conector.getConn().prepareStatement(insertar);
	            statement.setString(1, nombre);
	            statement.setString(2, dni);
	            statement.setString(3, email);
	            statement.setString(4, password);
	            resultset = statement.executeQuery();
	            statement2 = conector.getConn().prepareStatement(obtenerId);
	            resultset2 = statement2.executeQuery();
	            idCliente = Integer.parseInt(resultset2.getString("id"));

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
	        		c.setId(Integer.parseInt(resultset.getString("id")));
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
