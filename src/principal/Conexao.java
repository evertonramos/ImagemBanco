package principal;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Conexao {
    private final PostgreSQL c = new PostgreSQL();
    
    public Conexao(String application_name) throws Exception {
        String host = "localhost";
        
        int port = 5432;

        String database = "postgres";

        String username = "postgres";

        String password = "";

        System.out.println("--- " + (new SimpleDateFormat("dd/MM/yyyy HH:mm:ss")).format(new Date()) + " - application: " + application_name + " - host: " + host + ":" + port + " - database: " + database + " - username: " + username);
        
        // conectar
        c.connect(host, port, database, username, password, application_name);
    }
    
    public PostgreSQL getConexao() {
        return c;
    }
}
