package ingsoft1920.cm.dao;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ingsoft1920.cm.bean.Ausencia;
import ingsoft1920.cm.bean.Cliente;
import ingsoft1920.cm.bean.Proveedor;
import ingsoft1920.cm.bean.Ausencia.Estado;
import ingsoft1920.cm.conector.ConectorBBDD;

@Component
public class ClienteDAO {

	@Autowired
	private QueryRunner runner = new QueryRunner();

	@Autowired
	private ConectorBBDD conector = new ConectorBBDD();

	public int anadir(Cliente c) {
		BigInteger res = null;
		ScalarHandler<BigInteger> handler = new ScalarHandler<>();
		String query = "INSERT INTO Cliente "
					  +"(nombre,apellidos,DNI,email,password,nacionalidad,telefono) "
					  +"VALUES (?,?,?,?,?,?,?);";

		try (Connection conn = conector.getConn())
		{
			res = runner.insert(conn, query, handler,
								c.getNombre(),
								c.getApellidos(),
								c.getDNI(),
								c.getEmail(),
								c.getPassword(),
								c.getNacionalidad(),
								c.getTelefono()
							   );

		} catch (Exception e) { e.printStackTrace(); }

		return (res != null ? res.intValue() : -1);
	}

	// Devuelve null si no se ha podido hacer login
	public Cliente login(String email, String password) {
		Cliente res = null;
		BeanHandler<Cliente> handler = new BeanHandler<>(Cliente.class);
		String query = "SELECT * FROM Cliente WHERE email=? AND password=?;";

		try (Connection conn = conector.getConn()) 
		{
			res = runner.query(conn, query, handler, email, password);

		} catch (Exception e) { e.printStackTrace(); }

		return res;
	}
	
	public Cliente getByEmail(String email) {
		Cliente res = null;
		BeanHandler<Cliente> handler = new BeanHandler<>(Cliente.class);
		String query = "SELECT * FROM Cliente WHERE email=?;";

		try (Connection conn = conector.getConn()) {
			res = runner.query(conn, query, handler, email);

		} catch (Exception e) { e.printStackTrace(); }
		
		return res;
	}
	
	// Si email ya está en la base de datos devuelve el clienteID correspondiente
	// y si no está registra el email y devuelve el id generado
	public int getClienteIdAnonimo(String email) {
		Cliente c = getByEmail(email);
		if( c != null ) {
			return c.getId();
		} else {
			c = new Cliente();
			c.setEmail(email);
			
			return anadir(c);
		}		
	}
	
//	public List<Integer> getClientsId() {
//		List<Integer> res = new ArrayList<>();
//		
//		BeanListHandler<Integer> handler = new BeanListHandler<>(Integer.class);
//		String query = "SELECT Cliente.id FROM Cliente;";
//
//		try (Connection conn = conector.getConn()) 
//		{
//			res = runner.query(conn, query, handler);
//
//		} catch (Exception e) { e.printStackTrace(); }
//		
//		return res;
//		
//	}
	
	public List<Cliente> clientes() {

		List<Cliente> res = new ArrayList<>();

		BeanListHandler<Cliente> handler = new BeanListHandler<>(Cliente.class);

		String query = "SELECT * FROM Cliente";

		try (Connection conn = conector.getConn()) {
			res = runner.query(conn, query, handler);

		} catch (Exception e) { e.printStackTrace(); }
		
		return res;

	}
	
//	public static void main(String[] args) {
//		
//		ClienteDAO dao = new ClienteDAO();
//		System.out.println(dao.clientes());
//	}
	

}
