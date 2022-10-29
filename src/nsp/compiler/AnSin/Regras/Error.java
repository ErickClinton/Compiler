package nsp.compiler.AnSin.Regras;

import nsp.compiler.AnLex.Tokens_List;

public class Error {
    
    public static void errorToken(Tokens_List recebido, Tokens_List esperado){
        System.out.printf("\tError sintatico:\n\tToken Recebido: %s\n\tToken Esperado: %s", recebido, esperado);
        System.exit(1);
    }

    public static void errorTipo(Tokens_List recebido){
        System.out.printf("\tError sintatico:\n\tToken Recebido: %s - Não Corresponde a um tipo [INT, FLOAT, BOOLEAN, CAHR, STRING, VOID]", recebido);
        System.exit(1);
    }

    public static void errorVariable(){
        System.out.printf("\tError Sintatico:\n\t\tVariavel inexistente");
        System.exit(1);
    }
}
