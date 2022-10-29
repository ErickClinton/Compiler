package nsp.compiler.AnSin.Regras.RegrasBloco.Variable.exp;

import java.util.List;

import nsp.compiler.AnLex.Token;
import nsp.compiler.AnLex.Tokens_List;
import nsp.compiler.AnSin.Exp.Expressao;
import nsp.compiler.AnSin.Utils.Utils;
import nsp.compiler.Arvore.GeradorArvore;


//exp -> termo + exp | termo - exp | termo
//termo -> fator * termo | fator / termo | fator
//fator -> dig | (exp)
// dig -> float_value | int_value

public class exp {
    
    private List<Token> tokens;
    private Expressao expr;

    public exp(List<Token> tokens){
        this.tokens = tokens;
        this.expr = new Expressao();
        this.expr.grAvalInicio();
    }   

    //exp -> termo + exp | termo - exp | termo
    public int runExp(int pos){
        int posExp = pos;
        GeradorArvore.grArvTermo();
        posExp = runTermo(posExp);
        GeradorArvore.grArvFTermo();
        if(this.tokens.get(posExp).tipo == Tokens_List.EOI){
            return posExp-1;
        }else{
            if(this.tokens.get(posExp).tipo == Tokens_List.ADICAO){
                GeradorArvore.grArvLex(this.tokens, posExp);
                posExp ++;
                GeradorArvore.grArvExp();
                posExp = runExp(posExp);
                GeradorArvore.grArvFExp();
                this.expr.grAvalMais();
            }else if(this.tokens.get(posExp).tipo == Tokens_List.SUBTRACAO){
                GeradorArvore.grArvLex(this.tokens, posExp);
                posExp ++;
                GeradorArvore.grArvExp();
                posExp = runExp(posExp);
                GeradorArvore.grArvFExp();
                this.expr.grAvalMenos();
            }else{
                // Else
            }
        }

       return posExp;
    }

    //termo -> fator * termo | fator / termo | fator
    public int runTermo(int pos){
        int posTermo = pos;
        GeradorArvore.grArvFator();
        posTermo = runFator(posTermo);
        GeradorArvore.grArvFFator();
        
        if(this.tokens.get(posTermo).tipo == Tokens_List.MULTIPLICACAO){
            GeradorArvore.grArvLex(this.tokens, posTermo);
            posTermo ++;
            GeradorArvore.grArvTermo();
            posTermo = runTermo(posTermo);
            GeradorArvore.grArvFTermo();
            this.expr.grAvalMulti();
            return posTermo;
        }else if(this.tokens.get(posTermo).tipo == Tokens_List.DIVISAO){
            GeradorArvore.grArvLex(this.tokens, posTermo);
            posTermo ++;
            GeradorArvore.grArvTermo();
            posTermo = runTermo(posTermo);
            GeradorArvore.grArvFTermo();
            this.expr.grAvalDiv();
        }else{
            return posTermo;
        }
        

        return posTermo;
     }

     //fator -> dig | (exp)
     public int runFator(int pos){
        int posFator = pos;
        if(isValor(posFator)){
            expr.grAvalValor(this.tokens.get(posFator).lexeme);
            GeradorArvore.grArvLex(this.tokens, posFator);
            return posFator + 1;
        }else{
            posFator --;
            posFator = Utils.match(this.tokens, Tokens_List.A_PARENTESES, posFator);
            GeradorArvore.grArvLex(this.tokens, posFator);
            posFator = runExp(posFator);
            posFator = Utils.match(this.tokens, Tokens_List.A_PARENTESES, posFator);
            GeradorArvore.grArvLex(this.tokens, posFator);
            return posFator;
        }
     }

    private boolean isValor(int pos){
        switch (this.tokens.get(pos).tipo) {
            case INT_VALUE:
            case FLOAT_VALUE:
                return true;            
            default:
                return false;
        }
    }
}
