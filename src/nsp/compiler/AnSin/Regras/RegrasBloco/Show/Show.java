package nsp.compiler.AnSin.Regras.RegrasBloco.Show;

import nsp.compiler.AnLex.Token;
import nsp.compiler.AnLex.Tokens_List;
import nsp.compiler.AnSin.Utils.Utils;
import nsp.compiler.Arvore.GeradorArvore;

import java.util.List;

public class Show{
    public List<Token> tokens;
    public int pos;

    public Show(List<Token> tokens){
        this.tokens = tokens;
    }

    public int run(int pos){
        this.pos = pos;

        this.pos = Utils.match(tokens, Tokens_List.SHOW, this.pos);
        GeradorArvore.grArvLex(tokens, this.pos);
        this.pos = Utils.match(tokens, Tokens_List.A_PARENTESES, this.pos);
        GeradorArvore.grArvLex(tokens, this.pos);
        this.pos = Utils.match(tokens, Tokens_List.ID, this.pos);
        GeradorArvore.grArvLex(tokens, this.pos);
        this.pos = Utils.match(tokens, Tokens_List.F_PARENTESES, this.pos);
        GeradorArvore.grArvLex(tokens, this.pos);
        this.pos = Utils.match(tokens, Tokens_List.EOI, this.pos);
        GeradorArvore.grArvLex(tokens, this.pos);

        return this.pos+1;
    }
}