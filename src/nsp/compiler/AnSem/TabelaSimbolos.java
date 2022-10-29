package nsp.compiler.AnSem;

import java.util.HashMap;
import java.util.Map;

import nsp.compiler.AnLex.Token;

//Inserir
//Iniciar escopo
//Finalizar escopo
//Achar
public class TabelaSimbolos {
    
    private Map<String,Token> hashTable;

    public TabelaSimbolos(){
        this.hashTable = new HashMap<String,Token>();
    }

    public void inserir(String lex, Token token){
        hashTable.put(lex, token);
    }

    public Token achar(String lex){
        return hashTable.get(lex);
    }

    public void printHash(){
        System.out.println(this.hashTable);
    }
}
