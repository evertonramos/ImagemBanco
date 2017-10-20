package principal;

import java.sql.ResultSet;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("teste...");
        
        // faz a conexão ao banco
        PostgreSQL conexao = (new Conexao("nome do sistema")).getConexao();
        
        /*
        try {
            // inicio a transação
            conexao.setAutoCommit(false);
            
            conexao.execute("delete from imagem where id_imagem = ?", new Object[]{10});
            
            // finalizo a transação
            conexao.setAutoCommit(true);            
        } catch (Exception e) {
            // deu merda... faço o rollback e desativo a transação
            conexao.rollback();
            
            conexao.setAutoCommit(true);
            
            System.out.println(e.getMessage());
        }
        */
        
        
        ResultSet rs = conexao.query("select current_timestamp", new Object[0]);
        
        if(rs.next()) {
            System.out.println(rs.getString(1));
        }
        
        // finaliza a conexão
        conexao.disconnect();
        
    }
}
