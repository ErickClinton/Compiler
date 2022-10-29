package nsp.compiler;

import java.io.IOException;


public class Aplication {
    public static void main(String ...params) throws IOException{
        Compiler compiler = new Compiler(params);
        compiler.run();
    }
}
