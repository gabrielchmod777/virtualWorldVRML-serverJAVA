package ro.utcluj.learning3d.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class StaticDB {

	public static Connection c = null;

	public StaticDB() {
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection(ServerGlobals.DB_PARAMS);
			System.out.println("OK ... DB SQLITE");

			Statement stmt = c.createStatement();
			String sql = "CREATE TABLE if not exists l3d " +
					"(NAME           TEXT , " + 
					" FIELD1         TEXT , " + 
					" FIELD2         TEXT , " + 
					" FIELD3         TEXT , " + 
					" FIELD4         TEXT , " + 
					" FIELD5         TEXT , " + 
					" FIELD6         TEXT , " + 
					" FIELD7         TEXT , " + 
					" FIELD8         TEXT , " + 
					" FIELD9         TEXT , " + 
					" FIELD10         TEXT )"; 
			stmt.executeUpdate(sql);
			stmt.close();


		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		}
	}

	public static void update(String sqlUpdate) {
		Statement stmt = null;
		try {
			c.setAutoCommit(false);
			stmt = c.createStatement();

			System.out.println(sqlUpdate);

			stmt.executeUpdate(sqlUpdate);

			stmt.close();
			c.commit();

		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		}
	}

	public static String query(String sqlQueryStatement) {
		String result = "<error>SQL</error><cause>query="+sqlQueryStatement+"</cause>";

		try {
			c.setAutoCommit(false);
			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery( sqlQueryStatement );
			while ( rs.next() ) {

				String  name = rs.getString("NAME");
				String  f1 = rs.getString("FIELD1");
				String  f2 = rs.getString("FIELD2");
				String  f3 = rs.getString("FIELD3");
				String  f4 = rs.getString("FIELD4");
				String  f5 = rs.getString("FIELD5");
				String  f6 = rs.getString("FIELD6");
				String  f7 = rs.getString("FIELD7");
				String  f8 = rs.getString("FIELD8");
				String  f9 = rs.getString("FIELD9");
				String  f10 = rs.getString("FIELD10");
				

				result = 
						"<query_name>"+name+"</query_name>"+
								"<query_field1>"+f1+"</query_field1>"+
								"<query_field2>"+f2+"</query_field2>"+
								"<query_field3>"+f3+"</query_field3>"+
								"<query_field4>"+f4+"</query_field4>"+
								"<query_field5>"+f5+"</query_field5>"+
								"<query_field6>"+f6+"</query_field6>"+
								"<query_field7>"+f7+"</query_field7>"+
								"<query_field8>"+f8+"</query_field8>"+
								"<query_field9>"+f9+"</query_field9>"+
								"<query_field10>"+f10+"</query_field10>";

			}
			rs.close();
			stmt.close();
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );

		}

		return result;
	}
}
