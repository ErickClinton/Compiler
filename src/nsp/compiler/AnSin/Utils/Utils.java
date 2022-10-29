package nsp.compiler.AnSin.Utils;

import java.util.List;

import nsp.compiler.AnLex.Token;
import nsp.compiler.AnLex.Tokens_List;
import nsp.compiler.AnSin.Regras.Error;

public class Utils{
    public static int match(List<Token> tokens,Tokens_List esperado, int pos){
        pos = lookAhead(pos);
        if (tokens.get(pos).tipo == esperado){
        }else{
            Error.errorToken(tokens.get(pos).tipo, esperado);
        }
        return pos;
    }

    public static int lookAhead(int pos){
        pos++;
        return pos;
    }
}