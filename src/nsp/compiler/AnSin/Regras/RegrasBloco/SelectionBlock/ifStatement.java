package nsp.compiler.AnSin.Regras.RegrasBloco.SelectionBlock;

import java.util.List;

import nsp.compiler.AnLex.Token;
import nsp.compiler.AnLex.Tokens_List;
import nsp.compiler.AnSem.Ambiente;
import nsp.compiler.AnSin.Regras.Error;
import nsp.compiler.AnSin.Utils.Utils;
import nsp.compiler.Arvore.GeradorArvore;

//inst_if -> if ( exp ) { lst_opc } else { lst_opc }
//lst_opc -> lst_inst | Vazio
// exp -> id op id
// op -> op_lgc | op_rel



public class ifStatement {

    private List<Token> tokens;
    private int pos;
    private lstOpc opc;

    public ifStatement(List<Token> tokens){
        this.tokens = tokens;
        this.opc = new lstOpc(this.tokens);
    }

    public int run(int pos){
        this.pos = pos;
        GeradorArvore.grArvLex(this.tokens, this.pos);
        this.pos = Utils.match(this.tokens, Tokens_List.A_PARENTESES, this.pos);
        GeradorArvore.grArvLex(this.tokens, this.pos);
        isExp();
        this.pos = Utils.match(this.tokens, Tokens_List.F_PARENTESES, this.pos);
        GeradorArvore.grArvLex(this.tokens, this.pos);
        this.pos = Utils.match(this.tokens, Tokens_List.A_CHAVES, this.pos);

        Ambiente.iniciarEscopo();

        GeradorArvore.grArvLex(this.tokens, this.pos);
        GeradorArvore.grArvBlock();

        this.pos = this.opc.run(this.pos+1);

        GeradorArvore.grArvFBlock();
        GeradorArvore.grArvLex(this.tokens, this.pos);

        Ambiente.fecharEscopo();

        this.pos = Utils.match(this.tokens, Tokens_List.ELSE, this.pos);
        GeradorArvore.grArvLex(this.tokens, this.pos);
        this.pos = Utils.match(this.tokens, Tokens_List.A_CHAVES, this.pos);

        Ambiente.iniciarEscopo();

        GeradorArvore.grArvLex(this.tokens, this.pos);
        GeradorArvore.grArvBlock();

        this.pos = this.opc.run(this.pos+1);

        GeradorArvore.grArvFBlock();
        GeradorArvore.grArvLex(this.tokens, this.pos);

        Ambiente.fecharEscopo();
        return this.pos+1;
    }

    public void isExp(){
        if (match(Tokens_List.ID)) {
            Ambiente.achar(this.tokens.get(this.pos).lexeme);
            this.pos ++;
            if(isOperador()){
                match(Tokens_List.ID);
                Ambiente.achar(this.tokens.get(this.pos).lexeme);
            }else{
                Error.errorTipo(this.tokens.get(this.pos).tipo);
            }
        }else if(match(Tokens_List.NOT)){
            this.pos ++;

        }else{
            Error.errorTipo(this.tokens.get(this.pos).tipo);
        }
    }

    private boolean isOperador(){
        switch (this.tokens.get(this.pos).tipo) {
            case MAIOR:
            case MENOR:
            case MAIOR_IGUAL:
            case MENOR_IGUAL:
            case AND:
            case OR:
                return true;
            default:
                return false;
        }
    }

    private boolean match(Tokens_List esperado){
        this.pos ++;
        if (tokens.get(this.pos).tipo == esperado){
        }else{
            Error.errorToken(this.tokens.get(this.pos).tipo, esperado);
        }
        return true;
    }
}
