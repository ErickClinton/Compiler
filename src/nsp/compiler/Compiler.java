package nsp.compiler;

import java.util.List;
import nsp.compiler.AnLex.Token;
import nsp.compiler.Arvore.GeradorArvore;

public class Compiler {
    
    private String[] filePath;
    private String codFont;

    private Compile compile;

    public Compiler(String[] param){
        this.filePath = param;
        this.compile = new Compile();
    }

    public void run(){
        System.out.println("Compiling NSP:");
        try {
            //Leitura do Arquivo
            if (filePath.length == 0){
                System.out.println("\tError: Arquivo não foi passado como parametro");
                System.exit(1);
            }else if(filePath.length > 1){
                System.out.println("\tError: Too many arguments");
                System.exit(1);
            }
            // System.out.println("\tCompiling: "+filePath[0]);
            File file = new File(filePath[0]);
            this.codFont = file.getCodFont();

            //Lexico
            List<Token> tokens = this.compile.lexico(this.codFont);
            System.out.println("Done");
            // System.out.println(tokens);

            //Sintatico
            this.compile.sintatico(tokens);
            System.out.println("Done");
            // GeradorArvore.printArv();
        } catch (RuntimeException e) {
            System.out.println("Error");
            System.out.println(e);
            System.exit(1);
        }
    }
}
