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
    
    private void setValue(int pos, Object object) throws Exception {
        switch (object.getClass().getSimpleName()) {
            case "String":
            case "Character":
                ps.setString(pos, object.toString().trim());
                break;
            case "Integer":
                ps.setInt(pos, (Integer) object);
                break;
            case "Long":
                ps.setLong(pos, (Long) object);
                break;
            case "Double":
                ps.setDouble(pos, (Double) object);
                break;
            case "Float":
                ps.setFloat(pos, (Float) object);
                break;
            case "FileInputStream":
                ps.setBinaryStream(pos, (FileInputStream) object);
                break;
            default:
                throw new Exception("Object class name not found: " + object.getClass().getSimpleName());
        }
    }

    public boolean execute(String sql, Object[] valores) throws Exception {
        ps = c.prepareStatement(sql);

        for (int i = 0; i < valores.length; i++) {
            setValue(i + 1, valores[i]);
        }

        return (ps.executeUpdate() > 0);
    }

    public boolean execute(String sql) throws Exception {
        return execute(sql, new Object[0]);
    }

    public long insert(String sql, Object[] valores) throws Exception {
        ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        for (int i = 0; i < valores.length; i++) {
            setValue(i + 1, valores[i]);
        }

        ps.executeUpdate();

        ResultSet rsID = ps.getGeneratedKeys();

        if (rsID.next()) {
            return rsID.getLong(1);
        }

        return 0;
    }

    public long insert(String sql) throws Exception {
        return insert(sql, new Object[0]);
    }

    public ResultSet query(String sql, Object[] valores) throws Exception {
        ps = c.prepareStatement(sql);

        for (int i = 0; i < valores.length; i++) {
            setValue(i + 1, valores[i]);
        }

        return ps.executeQuery();
    }

    public ResultSet query(String sql) throws Exception {
        return query(sql, new Object[0]);
    }
}
