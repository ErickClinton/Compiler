package nsp.compiler.AnSin.Regras;

import java.util.List;

import nsp.compiler.AnLex.Token;
import nsp.compiler.AnLex.Tokens_List;
import nsp.compiler.AnSem.Ambiente;
import nsp.compiler.AnSin.Utils.Utils;
import nsp.compiler.Arvore.GeradorArvore;

public class FunctionMain {
    public List<Token> tokens;
    public int pos;
    private Bloco bloco;
    
    public FunctionMain(List<Token> tokenList, int pos){
        this.tokens = tokenList;
        this.pos = pos;
        this.bloco = new Bloco(this.tokens);
    }

    public int run(){
        this.pos = Utils.match(this.tokens,Tokens_List.A_PARENTESES,this.pos);
        GeradorArvore.grArvLex(this.tokens, this.pos);
        GeradorArvore.grArvIsParams();
        isParams();
        GeradorArvore.grArvFIsParams();
        this.pos = Utils.match(this.tokens,Tokens_List.F_PARENTESES,this.pos);
        GeradorArvore.grArvLex(this.tokens, this.pos);
        this.pos = Utils.match(this.tokens,Tokens_List.A_CHAVES,this.pos);
        
        Ambiente.iniciarEscopo();
        
        GeradorArvore.grArvLex(this.tokens, this.pos);
        this.pos = Utils.lookAhead(this.pos);
        GeradorArvore.grArvBlock();
        this.pos = this.bloco.run(this.pos);
        Ambiente.printPilha();
        Ambiente.iniciarEscopo();

        GeradorArvore.grArvFBlock();
        GeradorArvore.grArvLex(this.tokens, this.pos-1);
        return this.pos;
    }

    private void isTipo(){
        this.pos = Utils.lookAhead(this.pos);
        switch (this.tokens.get(this.pos).tipo) {
            case INT:
            case FLOAT:
            case BOOLEAN:
            case CHAR:
            case STRING:
            case VOID:
                break;
            default:
                Error.errorTipo(this.tokens.get(this.pos).tipo);
                break;
        }
    }

    private void isParams(){
        if(this.tokens.get(this.pos+1).tipo == Tokens_List.F_PARENTESES){
            return;
        }
        this.pos = Utils.match(this.tokens,Tokens_List.ID,this.pos);
        this.pos = Utils.match(this.tokens,Tokens_List.DOISPONTOS,this.pos);
        isTipo();
        if(this.tokens.get(this.pos+1).tipo == Tokens_List.VIRGULA){
            this.pos = Utils.lookAhead(this.pos);
            isParams();
        }
    }
}
