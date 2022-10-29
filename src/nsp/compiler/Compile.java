package nsp.compiler;

import java.util.List;

import nsp.compiler.AnLex.Lexico;
import nsp.compiler.AnLex.Token;

import nsp.compiler.AnSin.Sintatico;;

public class Compile {
    public List<Token>  lexico(String codFont){
        System.out.print("\tAnalise Lexica... ");
        Lexico anLexico;
        (anLexico = new Lexico()).codFont = codFont;
        return anLexico.setTokens();
    }

    public void sintatico(List<Token> tokens){
        // System.out.print("\tAnalise Sintatica... ");
        Sintatico anSintatico;
        (anSintatico = new Sintatico()).tokenList = tokens;
        anSintatico.run();
    }
}
