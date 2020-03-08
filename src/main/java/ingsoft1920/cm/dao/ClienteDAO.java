package ingsoft1920.cm.dao;

import java.math.BigInteger;
import java.sql.Connection;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ingsoft1920.cm.bean.Cliente;
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
					  +"VALUES (?,?,?,?,?,?,?)";
		
		try ( Connection conn = conector.getConn() )
		{
			res = runner.insert(conn,query,handler,
								 c.getNombre(),
								 c.getApellidos(),
								 c.getDNI(),
								 c.getEmail(),
								 c.getPassword(),
								 c.getNacionalidad(),
								 c.getTelefono()
								 );
			
		} catch(Exception e) { e.printStackTrace(); }
		
		return ( res != null ? res.intValue() : -1 );
	}

}
