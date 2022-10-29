package nsp.compiler.AnSin.Regras.RegrasBloco.Retorno;

import nsp.compiler.AnLex.Token;
import nsp.compiler.AnLex.Tokens_List;
import nsp.compiler.AnSin.Utils.Utils;
import nsp.compiler.Arvore.GeradorArvore;

import java.util.List;

public class Return{
    public List<Token> tokens;
    public int pos;

    public Return(List<Token> tokens){
        this.tokens = tokens;
    }

    public int run(int pos){
        this.pos = pos;
        this.pos = Utils.match(tokens, Tokens_List.RETURN, this.pos);
        GeradorArvore.grArvLex(this.tokens, this.pos);
        this.pos = Utils.match(tokens, Tokens_List.ID, this.pos);
        GeradorArvore.grArvLex(this.tokens, this.pos);
        this.pos = Utils.match(tokens, Tokens_List.EOI, this.pos);
        GeradorArvore.grArvLex(this.tokens, this.pos);
        return this.pos+1;
    }
}