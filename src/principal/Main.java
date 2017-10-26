package principal;

import java.sql.ResultSet;

public class Main {

    public static void main(String[] args) throws Exception {
        // faz a conexão ao banco
        System.out.println("faz a conexão ao banco");
        PostgreSQL conexao = (new Conexao("nome do sistema")).getConexao();

        // crio uma tabela TEMPORÁRIA
        System.out.println("criando tabela TEMPORÁRIA");
        conexao.execute("create temporary table teste (id_teste serial, nome text, valor numeric)");

        // gravo alguns dados
        System.out.println("gravando ALGUNS dados");
        conexao.insert("insert into teste (nome, valor) values (?, ?)", new Object[]{
            "nome1",
            123
        });

        conexao.insert("insert into teste (nome, valor) values (?, ?)", new Object[]{
            "nome2",
            321
        });

        conexao.insert("insert into teste (nome, valor) values (?, ?)", new Object[]{
            "nome3",
            987.12
        });

        // listo TODOS os dados
        System.out.println("listando TODOS os dados");
        ResultSet rs = conexao.query("select id_teste, nome, valor from teste");

        while (rs.next()) {
            System.out.println(rs.getLong("id_teste") + "\t" + rs.getString("nome") + "\t" + rs.getDouble("valor"));
        }

        // apago o id_teste 2
        System.out.println("apago o id_teste 2");
        conexao.execute("delete from teste where id_teste = ?", new Object[]{
            2
        });

        // altero o nome do id_teste 3
        System.out.println("altero o nome do id_teste 3");
        conexao.execute("update teste set nome = ? where id_teste = ?", new Object[]{
            "nome alterado",
            3
        });
        
        // gravo mais alguns dados
        System.out.println("gravo mais alguns dados");

        for (int x = 1; x <= 10; x++) {
            Object[] valores = new Object[2];

            valores[0] = "nome pelo loop x=" + x;
            valores[1] = Math.pow(x, x);

            conexao.insert("insert into teste (nome, valor) values (?, ?)", valores);
        }
        
        // listo TODOS os dados (novamente)
        System.out.println("listando TODOS os dados (novamente)");
        ResultSet rs2 = conexao.query("select id_teste, nome, valor from teste");

        while (rs2.next()) {
            System.out.println(rs2.getLong("id_teste") + "\t" + rs2.getString("nome") + "\t" + rs2.getDouble("valor"));
        }
        
        // listo APENAS os dados com id_teste <= 5
        System.out.println("listo APENAS os dados com id_teste <= 5");

        ResultSet rs3 = conexao.query("select id_teste, nome, valor from teste where id_teste <= ?", new Object[]{5});

        while (rs3.next()) {
            System.out.println(rs3.getLong("id_teste") + "\t" + rs3.getString("nome") + "\t" + rs3.getDouble("valor"));
        }
        
        // finaliza a conexão
        System.out.println("finaliza a conexão");
        conexao.disconnect();

    }
}
