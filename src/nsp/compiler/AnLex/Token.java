package nsp.compiler.AnLex;

public class Token {
    
    public final Tokens_List tipo;
    public final int line;
    public final int col_Start;
    public final int col_End;
    public final String lexeme;

    public Token(Tokens_List tipo,int line, int col_Start, int col_End){
        this.tipo = tipo;
        this.line = line;
        this.col_Start = col_Start;
        this.col_End = col_End;
        this.lexeme = "";
    }

    public Token(Tokens_List tipo, int value, int line, int col_Start, int col_End){
        this.tipo = tipo;
        this.line = line;
        this.lexeme = "" + value;
        this.col_Start = col_Start;
        this.col_End = col_End;
    }
    
    public Token(Tokens_List tipo, Float value, int line, int col_Start, int col_End){
        this.tipo = tipo;
        this.line = line;
        this.lexeme = "" + value;
        this.col_Start = col_Start;
        this.col_End = col_End;
    }

    public Token(Tokens_List tipo, String value, int line, int col_Start, int col_End){
        this.tipo = tipo;
        this.line = line;
        this.lexeme = value;
        this.col_Start = col_Start;
        this.col_End = col_End;
    }

    public Token(String value, int line, int col_Start, int col_End){
        this.line = line;
        this.lexeme = "" + value;
        this.col_Start = col_Start;
        this.col_End = col_End;
        switch(value){
            case "if":
                this.tipo = Tokens_List.IF;
                break;
            case "int":
                this.tipo = Tokens_List.INT;
                break;
            case "char":
                this.tipo = Tokens_List.CHAR;
                break;
            case "show":
                this.tipo = Tokens_List.SHOW;
                break;
            case "string":
                this.tipo = Tokens_List.STRING;
                break;
            case "func":
                this.tipo = Tokens_List.FUNCTION;
                break;
            case "float":
                this.tipo = Tokens_List.FLOAT;
                break;
            case "for":
                this.tipo = Tokens_List.FOR;
                break;
            case "else":
                this.tipo = Tokens_List.ELSE;
                break;
            case "void":
                this.tipo = Tokens_List.VOID;
                break;
            case "while":
                this.tipo = Tokens_List.WHILE;
                break;
            case "boolean":
                this.tipo = Tokens_List.BOOLEAN;
                break;
            case "and":
                this.tipo = Tokens_List.AND;
                break;
            case "or":
                this.tipo = Tokens_List.OR;
                break;
            case "main":
                this.tipo = Tokens_List.MAIN;
                break;
            default:
                this.tipo = Tokens_List.ID;
                break;
        }
    }

    public String toString(){
        return String.format("[%d | %d-%d | <%s> | %s]", this.line, this.col_Start, this.col_End, this.tipo, this.lexeme);
    }
}
