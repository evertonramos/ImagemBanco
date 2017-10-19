package principal;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class PostgreSQL {

    private Connection c;
    private PreparedStatement ps;

    public void connect(String hostname, int port, String database, String username, String password, String application_name) throws Exception {
        Class.forName("org.postgresql.Driver");

        c = DriverManager.getConnection("jdbc:postgresql://" + hostname + ":" + port + "/" + database + "?ApplicationName=" + application_name, username, password);
    }

    public void disconnect() throws Exception {
        c.close();
    }

    public void setAutoCommit(boolean value) throws Exception {
        c.setAutoCommit(value);
    }

    public void commit() throws Exception {
        c.commit();
    }

    public void rollback() throws Exception {
        c.rollback();
    }

    public boolean execute(String sql, Object[] valores) throws Exception {
        ps = c.prepareStatement(sql);

        for (int i = 0; i < valores.length; i++) {
            switch (valores[i].getClass().getSimpleName()) {
                case "String":
                    ps.setString(i + 1, valores[i].toString().trim());
                    break;
                case "Character":
                    ps.setString(i + 1, valores[i].toString().trim());
                    break;
                case "Integer":
                    ps.setInt(i + 1, (Integer) valores[i]);
                    break;
                case "Long":
                    ps.setLong(i + 1, (Long) valores[i]);
                    break;
                case "Double":
                    ps.setDouble(i + 1, (Double) valores[i]);
                    break;
                case "Float":
                    ps.setFloat(i + 1, (Float) valores[i]);
                    break;
                case "FileInputStream":
                    ps.setBinaryStream(i + 1, (FileInputStream) valores[i]);
                    break;
                default:
                    throw new Exception("Execute/Tipo não encontrado: " + valores[i].getClass().getSimpleName());
            }
        }

        return (ps.executeUpdate() > 0);
    }

    public long insert(String sql, Object[] valores) throws Exception {
        ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        for (int i = 0; i < valores.length; i++) {

            switch (valores[i].getClass().getSimpleName()) {
                case "String":
                    ps.setString(i + 1, valores[i].toString().trim());
                    break;
                case "Character":
                    ps.setString(i + 1, valores[i].toString().trim());
                    break;
                case "Integer":
                    ps.setInt(i + 1, (Integer) valores[i]);
                    break;
                case "Long":
                    ps.setLong(i + 1, (Long) valores[i]);
                    break;
                case "Double":
                    ps.setDouble(i + 1, (Double) valores[i]);
                    break;
                case "Float":
                    ps.setFloat(i + 1, (Float) valores[i]);
                    break;
                case "FileInputStream":
                    ps.setBinaryStream(i + 1, (FileInputStream) valores[i]);
                    break;
                default:
                    throw new Exception("Insert/Tipo não encontrado: " + valores[i].getClass().getSimpleName());
            }
        }

        ps.executeUpdate();

        ResultSet rsID = ps.getGeneratedKeys();

        while (rsID.next()) {
            return rsID.getInt(1);
        }

        return 0;
    }

    public ResultSet query(String sql, Object[] valores) throws Exception {
        ps = c.prepareStatement(sql);

        for (int i = 0; i < valores.length; i++) {
            switch (valores[i].getClass().getSimpleName()) {
                case "String":
                    ps.setString(i + 1, valores[i].toString().trim());
                    break;
                case "Character":
                    ps.setString(i + 1, valores[i].toString().trim());
                    break;
                case "Integer":
                    ps.setInt(i + 1, (Integer) valores[i]);
                    break;
                case "Long":
                    ps.setLong(i + 1, (Long) valores[i]);
                    break;
                case "Double":
                    ps.setDouble(i + 1, (Double) valores[i]);
                    break;
                case "Float":
                    ps.setFloat(i + 1, (Float) valores[i]);
                    break;
                case "FileInputStream":
                    ps.setBinaryStream(i + 1, (FileInputStream) valores[i]);
                    break;
                default:
                    throw new Exception("Query/Tipo não encontrado: " + valores[i].getClass().getSimpleName());
            }
        }

        return ps.executeQuery();
    }
}
