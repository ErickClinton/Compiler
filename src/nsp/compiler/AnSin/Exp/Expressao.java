package nsp.compiler.AnSin.Exp;

import java.util.Stack;

public class Expressao {
    public Stack<Float> pilha = null;

    public void grAvalInicio() {
        pilha = new Stack<Float>();
    }

    public void grAvalValor(String valor){
        float digito = Float.parseFloat("" + valor);
        pilha.push(digito);
    }

    public void grAvalMais() {
        float exp2 = pilha.pop();
        float exp1 = pilha.pop();
        pilha.push(exp1 + exp2);
    }

    public void grAvalMenos() {
        float exp2 = pilha.pop();
        float exp1 = pilha.pop();
        pilha.push(exp1 - exp2);
    }

    public void grAvalMulti() {
        float exp2 = pilha.pop();
        float exp1 = pilha.pop();
        pilha.push(exp1*exp2);
    }

    public void grAvalDiv() {
        float exp2 = pilha.pop();
        float exp1 = pilha.pop();
        pilha.push(exp1/exp2);
    }

    public Stack<Float> pilha() {
        return pilha;
    }
}