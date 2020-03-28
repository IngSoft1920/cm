package ingsoft1920.cm.dao;

import java.math.BigInteger;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.springframework.beans.factory.annotation.Autowired;

import ingsoft1920.cm.bean.Categoria;
import ingsoft1920.cm.conector.ConectorBBDD;

public class CategoriaDAO {

	@Autowired
	private QueryRunner runner = new QueryRunner();
	
	@Autowired
	private ConectorBBDD conector = new ConectorBBDD();

	public int anadir(Categoria categoria) {
		BigInteger idGenerado = null;
		ScalarHandler<BigInteger> handler = new ScalarHandler<>();
		String query = "INSERT INTO Categoria (nombre) VALUES (?)";

		try (Connection conn = conector.getConn()) {
			
			idGenerado = runner.insert(conn, query, handler, categoria.getNombre());
			
		} catch (Exception e) { e.printStackTrace(); }

		return idGenerado != null ? idGenerado.intValue() : -1;
	}

	public Categoria getByID(int id) {
		Categoria res = null;
		BeanHandler<Categoria> handler = new BeanHandler<>(Categoria.class);
		String query = "SELECT * FROM Categoria WHERE id = ?";

		try (Connection conn = conector.getConn()) 
		{
			res = runner.query(conn, query, handler, id);

		} catch (Exception e) { e.printStackTrace(); }

		return res;
	}

	public List<Categoria> categorias() {
		List<Categoria> res = new ArrayList<>();
		BeanListHandler<Categoria> handler = new BeanListHandler<>(Categoria.class);
		String query = "SELECT * FROM Categoria";

		try (Connection conn = conector.getConn())
		{
			res = runner.query(conn, query, handler);

		} catch (Exception e) { e.printStackTrace(); }

		return res;
	}
}
