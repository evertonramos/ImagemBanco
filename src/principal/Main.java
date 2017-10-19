package principal;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("teste...");
        
        // faz a conexão ao banco
        PostgreSQL conexao = (new Conexao("nome do sistema")).getConexao();
        
        
        
        
        // finaliza a conexão
        conexao.disconnect();
        
    }
}
