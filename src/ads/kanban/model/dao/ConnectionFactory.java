package ads.kanban.model.dao;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public static Connection getConnection() throws IOException {
		try {
//			return DriverManager.getConnection("jdbc:mysql://52.205.131.244:3306/kanbanitsm?"
//					+ "useTimezone=true&serverTimezone=UTC&user=Alunos&password=alunosvsead");
			return DriverManager.getConnection("jdbc:mysql://localhost:3306/kanbanitsm?"
					+ "useTimezone=true&serverTimezone=UTC&user=Alunos&password=alunos");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
		}
	}

}
