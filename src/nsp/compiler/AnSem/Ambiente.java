package nsp.compiler.AnSem;

import java.util.Stack;

import nsp.compiler.AnLex.Token;
import nsp.compiler.AnSin.Regras.Error;

public class Ambiente {
    
    public static Stack<TabelaSimbolos> pilhaHash = new Stack<>();
    public static Stack<TabelaSimbolos> pilhaSuporte = new Stack<>();

    public static void iniciarEscopo(){
        TabelaSimbolos tabelaHash = new TabelaSimbolos();
        pilhaHash.push(tabelaHash);
    }

    public static void fecharEscopo(){
        pilhaHash.pop();
    }

    public static void inserir(String lex, Token token){
        pilhaHash.peek().inserir(lex, token);
    }

    public static Token achar(String lex){
        Token tk = pilhaHash.peek().achar(lex);
        if (tk != null){
            return tk;
        }else{
            tk = acharNoAnterior(lex);
            
            if(tk == null){
                Error.errorVariable();
            }
            return tk;
        }
    }

    public static Token acharNoAnterior(String lex){
        Token tk = pilhaHash.peek().achar(lex);
        while (tk == null && !pilhaHash.isEmpty()) {
            acessarAnterior();
            if(!pilhaHash.isEmpty()){
                tk = pilhaHash.peek().achar(lex);
            }
        }
        colocarDeVolta();
        return tk;
    }

    public static void acessarAnterior(){
        if(!pilhaHash.isEmpty()){
            TabelaSimbolos tabelaHash = pilhaHash.pop();
            pilhaSuporte.push(tabelaHash);
        }
    }
    public static void colocarDeVolta(){
        while(!pilhaSuporte.isEmpty()){
            TabelaSimbolos tabelaHash = pilhaSuporte.pop();
            pilhaHash.push(tabelaHash);
        }
    }

    public static void printPilha(){
        System.out.print("Pilha Hash: ");
        pilhaHash.peek().printHash();
    }
}
