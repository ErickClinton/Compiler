package nsp.compiler.AnSin.Regras.RegrasBloco.Variable;

import java.util.List;

import nsp.compiler.AnLex.Token;
import nsp.compiler.AnLex.Tokens_List;
import nsp.compiler.AnSem.Ambiente;
import nsp.compiler.AnSin.Regras.Error;
import nsp.compiler.AnSin.Regras.RegrasBloco.Variable.exp.exp;
import nsp.compiler.Arvore.GeradorArvore;

public class variableAttribution {
    private List<Token> tokens;
    private int pos;
    private exp _exp;

    public variableAttribution(List<Token> tokens){
        this.tokens = tokens;
        this._exp = new exp(this.tokens);
    }

    public int run(int pos){
        this.pos = pos;
        GeradorArvore.grArvLex(this.tokens, this.pos);
        match(Tokens_List.ATRIBUICAO);
        GeradorArvore.grArvLex(this.tokens, this.pos);
        if(isValor()){
            if(isOp()){
                GeradorArvore.grArvExp();
                this.pos = this._exp.runExp(this.pos);
                GeradorArvore.grArvFExp();
            }else{
                GeradorArvore.grArvLex(this.tokens, this.pos);
            }
        }else{
            match(Tokens_List.ID);
            Ambiente.achar(this.tokens.get(this.pos).lexeme);
            GeradorArvore.grArvLex(this.tokens, this.pos);
        }
        match(Tokens_List.EOI);
        GeradorArvore.grArvLex(this.tokens, this.pos);
        return this.pos+1;
    }

    public void match(Tokens_List esperado){
        lookAhead();
        if (this.tokens.get(this.pos).tipo == esperado){
            //Não Faz Nada
            // System.out.printf("\t%s - %s\n",this.tokens.get(this.pos).tipo, esperado);
        }else{
            Error.errorToken(this.tokens.get(this.pos).tipo, esperado);
        }
    }

    private void lookAhead(){
        this.pos ++;
    }

    public boolean isValor(){
        lookAhead();
        switch (this.tokens.get(this.pos).tipo) {
            case INT_VALUE:
            case FLOAT_VALUE:
            case CHAR_VALUE:
            case BOOLEAN_VALUE:
            case STRING_VALUE:
                return true;
        
            default:
                this.pos --;
                return false;
        }
    }

    public boolean isOp(){
        switch(this.tokens.get(this.pos+1).tipo){
            case ADICAO:
            case SUBTRACAO:
            case MULTIPLICACAO:
            case DIVISAO:
                return true;
            default:
                return false;
        }
    }
}
