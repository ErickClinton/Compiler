package nsp.compiler.AnLex;

import java.util.ArrayList;
import java.util.List;

public class Lexico {
    public String codFont;
    public List<Token> tokens;
    public int pos;
    public int lin;
    public int col;

    public void setCodFont(String condFont){
        this.codFont = condFont;
    }

    public List<Token> setTokens(){
        this.tokens = new ArrayList<>();

        this.pos = 0;
        this.lin = 1;
        this.col = 1;

        int i = this.codFont.length();

        while(this.pos < i){
            if(this.codFont.charAt(this.pos) == '{'){
                this.tokens.add(new Token(Tokens_List.A_CHAVES, "{",this.lin, this.col, this.col));
                lookAhead();
                continue;
            }
            if(this.codFont.charAt(this.pos) == '}'){
                this.tokens.add(new Token(Tokens_List.F_CHAVES, "}",this.lin, this.col, this.col));
                lookAhead();
                continue;
            }
            if(this.codFont.charAt(this.pos) == '('){
                this.tokens.add(new Token(Tokens_List.A_PARENTESES, "(",this.lin, this.col, this.col));
                lookAhead();
                continue;
            }
            if(this.codFont.charAt(this.pos) == ')'){
                this.tokens.add(new Token(Tokens_List.F_PARENTESES, ")",this.lin, this.col, this.col));
                lookAhead();
                continue;
            }
            if(this.codFont.charAt(this.pos) == ','){
                this.tokens.add(new Token(Tokens_List.VIRGULA, ",",this.lin, this.col, this.col));
                lookAhead();
                continue;
            }
            if(this.codFont.charAt(this.pos) == ';'){
                this.tokens.add(new Token(Tokens_List.EOI, ";",this.lin, this.col, this.col));
                lookAhead();
                continue;
            }
            if(this.codFont.charAt(this.pos) == '\n'){
                this.col = 0;
                this.lin ++;
                this.pos ++;
                continue;
            }
            if(this.codFont.charAt(this.pos) == '<'){
                lookAhead();
                if(this.codFont.charAt(this.pos) == '='){
                    this.tokens.add(new Token(Tokens_List.MENOR_IGUAL, "<=",this.lin, this.col-1, this.col));
                    lookAhead();
                    continue;
                }else{
                    this.tokens.add(new Token(Tokens_List.MENOR, "<",this.lin, this.col-1, this.col-1));
                    continue;
                }
            }
            if(this.codFont.charAt(this.pos) == '>'){
                lookAhead();
                if(this.codFont.charAt(this.pos) == '='){
                    this.tokens.add(new Token(Tokens_List.MAIOR_IGUAL, ">=",this.lin, this.col-1, this.col));
                    lookAhead();
                    continue;
                }else{
                    this.tokens.add(new Token(Tokens_List.MAIOR, ">",this.lin, this.col-1, this.col-1));
                    continue;
                }
            }
            if(this.codFont.charAt(this.pos) == '='){
                this.tokens.add(new Token(Tokens_List.IGUALDADE, "=",this.lin, this.col, this.col));
                lookAhead();
                continue;
            }
            if(this.codFont.charAt(this.pos) == '!'){
                lookAhead();
                if(this.codFont.charAt(this.pos) == '='){
                    this.tokens.add(new Token(Tokens_List.DESIGUALDADE, "!=",this.lin, this.col-1, this.col));
                    lookAhead();
                    continue;
                }
                this.tokens.add(new Token(Tokens_List.NOT, this.lin, this.col-1, this.col-1));
                continue;
            }
            if(this.codFont.charAt(this.pos) == ':'){
                lookAhead();
                if(this.codFont.charAt(this.pos) == '='){
                    this.tokens.add(new Token(Tokens_List.ATRIBUICAO, ":=",this.lin, this.col - 1, this.col));
                    lookAhead();
                    continue;
                }else{
                    this.tokens.add(new Token(Tokens_List.DOISPONTOS, ":",this.lin, this.col-1, this.col-1));
                    continue;
                }
            }
            if(this.codFont.charAt(this.pos) == '-'){
                lookAhead();
                if(this.codFont.charAt(this.pos) == '>'){
                    this.tokens.add(new Token(Tokens_List.RETURN, "->",this.lin, this.col - 1, this.col));
                    lookAhead();
                    continue;
                }else{
                    this.tokens.add(new Token(Tokens_List.SUBTRACAO, "-",this.lin, this.col, this.col));
                    continue;
                }
            }
            if(this.codFont.charAt(this.pos) == '+'){
                this.tokens.add(new Token(Tokens_List.ADICAO, "+", this.lin, this.col, this.col));
                lookAhead();
                continue;
            }
            if(this.codFont.charAt(this.pos) == '*'){
                this.tokens.add(new Token(Tokens_List.MULTIPLICACAO, "*",this.lin, this.col, this.col));
                lookAhead();
                continue;
            }
            if(this.codFont.charAt(this.pos) == '/'){
                this.tokens.add(new Token(Tokens_List.DIVISAO, "*",this.lin, this.col, this.col));
                lookAhead();
                continue;
            }
            if(this.codFont.charAt(this.pos) == '\"'){
                int start = this.col;
                String string = "" + this.codFont.charAt(this.pos);
                lookAhead();
                while(this.codFont.charAt(this.pos) != '\"'){
                    string += this.codFont.charAt(this.pos);
                    lookAhead();
                }
                string += this.codFont.charAt(this.pos);
                this.tokens.add(new Token(Tokens_List.STRING_VALUE, string,this.lin,start, this.col));
                lookAhead();
                continue;
            }
            if(this.codFont.charAt(this.pos) == '\''){
                int start = this.col;
                String letra = "" + this.codFont.charAt(this.pos);
                lookAhead();
                letra += "" + this.codFont.charAt(this.pos);
                lookAhead();
                if(this.codFont.charAt(this.pos) != '\''){
                    lookAhead();
                    continue;
                }else{
                    letra += "" + this.codFont.charAt(this.pos);
                    this.tokens.add(new Token(Tokens_List.CHAR_VALUE, letra,this.lin, start, this.col));
                    lookAhead();
                    continue;
                }
            }
            if(isDig(this.codFont.charAt(this.pos))){
                int start = this.col;
                String number = "" + this.codFont.charAt(this.pos);
                lookAhead();
                while(isDig(this.codFont.charAt(this.pos))){
                    number += this.codFont.charAt(this.pos);
                    lookAhead();
                }
                if(this.codFont.charAt(this.pos) == '.'){
                    lookAhead();
                    number += ".";
                    while(isDig(this.codFont.charAt(this.pos))){
                        number += this.codFont.charAt(this.pos);
                        lookAhead();
                    }
                    this.tokens.add(new Token(Tokens_List.FLOAT_VALUE, Float.parseFloat(number),this.lin, start, this.col));
                    continue;
                }else{
                    this.tokens.add(new Token(Tokens_List.INT_VALUE, Integer.parseInt(number),this.lin, start, this.col));   
                    continue;  
                }
            }
            if(isBooleanValue(this.codFont.charAt(this.pos))){
                lookAhead();
                if(isEndId(this.codFont.charAt(this.pos))){
                    this.tokens.add(new Token(Tokens_List.BOOLEAN_VALUE, String.valueOf(this.codFont.charAt(this.pos-1)), this.lin, this.col-1, this.col-1));
                    continue;
                }
                lookBack();
            }
            if(isLetter(this.codFont.charAt(this.pos))){
                int start = this.col;
                String id = "" + this.codFont.charAt(this.pos);
                lookAhead();
                while(!isEndId(this.codFont.charAt(this.pos))){
                    id += "" + this.codFont.charAt(this.pos);
                    lookAhead();
                }
                this.tokens.add(new Token(id, this.lin, start, this.col));
                continue;
            } 
            if (isSpace(this.codFont.charAt(this.pos))){
                lookAhead();
                continue;
            }
        }
        this.tokens.add(new Token(Tokens_List.EOF, this.lin, this.col, this.col));
        return this.tokens;
    }

    public boolean isDig(char value){
        return ('0' <= value && value <= '9');
    }

    public boolean isLetter(char value){
        return (('A' <= value && value <= 'Z') || ('a' <= value && value <= 'z'));
    }

    public void lookAhead(){
        this.pos ++;
        this.col ++;
    }
    public void lookAhead(int jump){
        this.pos += jump;
        this.col += jump;
    }

    public void lookBack(){
        this.pos --;
        this.col --;
    }
    public void lookBack(int jump){
        this.pos -= jump;
        this.col -= jump;
    }

    public boolean isEndId(char value){
        return (value == ' ' || value == '\t' || value == '\r' || value == '\n' || value == '(' || value == ')'|| value == '{' || value == '}'|| value == '[' || value == ']' || value == ';' || value == ':' || value == ',');
    }

    public boolean isSpace(char value){
        return (value == ' ' || value == ',');
    }

    public boolean isBooleanValue(char value){
        return (value == 'T' || value == 'F');
    }
}
