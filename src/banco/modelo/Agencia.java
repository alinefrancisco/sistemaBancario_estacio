package banco.modelo;

import java.util.ArrayList;
import java.util.List;

public class Agencia {

    private int numero;
    private String nome;
    private List<Conta> contas;

    public Agencia(int numero, String nome) {
        this.numero = numero;
        this.nome = nome;
        contas = new ArrayList<>();
    }

    public int getNumero() {
        return numero;
    }

    public String getNome() {
        return nome;
    }

    public List<Conta> getContas() {
        return contas;
    }

    public void incluirConta(Conta conta){
        contas.add(conta);
    }

    public void excluirConta(Conta conta){
        contas.remove(conta);
    }
}
