package principal;

import java.sql.ResultSet;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("teste...");
        
        // faz a conexão ao banco
        PostgreSQL conexao = (new Conexao("nome do sistema")).getConexao();
        
        ResultSet rs = conexao.query("select current_timestamp", new Object[0]);
        
        if(rs.next()) {
            System.out.println(rs.getString(1));
        }
        
        // finaliza a conexão
        conexao.disconnect();
        
    }
}
