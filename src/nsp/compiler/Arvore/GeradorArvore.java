package nsp.compiler.Arvore;

import java.util.List;

import nsp.compiler.AnLex.Token;

public class GeradorArvore{
    private static String arvore = "";
    

    //lst (dig (9:DIG) (-:MENOS) lst (dig (5:DIG) (+:MAIS) lst (dig (2:DIG) )
    // [14 | 0-4 | <MAIN> | main] 

    public static void grArvBlock(){
        arvore += " block ("; 
    }
    public static void grArvFBlock(){
        arvore += ") "; 
    }

    public static void grArvReturn(){
        arvore += " returnStatement ("; 
    }
    public static void grArvFReturn(){
        arvore += ") "; 
    }

    public static void grArvIfStat(){
        arvore += " ifStatement ("; 
    }
    public static void grArvFIfStat(){
        arvore += ") "; 
    }

    public static void grArvVariableDeclaration(){
        arvore += " VariableDeclaration ("; 
    }
    public static void grArvFVariableDeclaration(){
        arvore += ") "; 
    }

    public static void grArvVariableAtribution(){
        arvore += " VariableAtribution ("; 
    }
    public static void grArvFVariableAtribution(){
        arvore += ") "; 
    }

    public static void grArvFunction(){
        arvore += " Function ("; 
    }
    public static void grArvFFunction(){
        arvore += ") "; 
    }

    public static void grArvFunctionMain(){
        arvore += " Main ("; 
    }
    public static void grArvFFunctionMain(){
        arvore += ") "; 
    }

    public static void grArvIsParams(){
        arvore += " IsParams ("; 
    }
    public static void grArvFIsParams(){
        arvore += ") "; 
    }

    public static void grArvShow(){
        arvore += " show ("; 
    }
    public static void grArvFShow(){
        arvore += ") "; 
    }

    public static void grArvExp(){
        arvore += " exp ("; 
    }
    public static void grArvFExp(){
        arvore += ") "; 
    }

    public static void grArvFator(){
        arvore += " fator ("; 
    }
    public static void grArvFFator(){
        arvore += ") "; 
    }

    public static void grArvTermo(){
        arvore += " termo ("; 
    }
    public static void grArvFTermo(){
        arvore += ") "; 
    }

    /* TERMINAIS */
    public static void grArvLex(List<Token> tokenList,int pos){
        arvore +=  " " + tokenList.get(pos).tipo.toString().toLowerCase() + "(";
        arvore += "" + "[" + tokenList.get(pos).lexeme + "]" + ":" + tokenList.get(pos).tipo;
        arvore +=") ";
    }

    public static void grArvValue(List<Token> tokenList,int pos){
        arvore +=  " " + tokenList.get(pos).tipo.toString().toLowerCase() + "(";
        arvore += "" + "[" + tokenList.get(pos).lexeme + "]" + ":" + tokenList.get(pos).tipo;
        arvore +=") ";
    }
    


    public static void printArv(){
        System.out.println(arvore);
    }
}

