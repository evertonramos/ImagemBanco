package principal;

import java.io.File;
import java.io.FileInputStream;

public class Util {

    public boolean salvarImagem(PostgreSQL conexao, long id, String caminhoArquivo) {

        try {
            FileInputStream fis = new FileInputStream(new File(caminhoArquivo));
            
            
            
        } catch (Exception e) {
            System.out.println("ERRO: " + e.getMessage());
        }

        return false;
    }

}
