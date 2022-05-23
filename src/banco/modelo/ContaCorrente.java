package banco.modelo;

import java.text.DecimalFormat;

public class ContaCorrente extends Conta{

    private double limite;
    private double juros;

    public ContaCorrente(Cliente cliente) {
        super(cliente);
        limite = 1000;
        juros = 5.9;
    }

    public ContaCorrente(Cliente cliente, double limite, double juros){
        super(cliente);
        this.limite = limite;
        this.juros = juros;
    }

    @Override
    public String getSaldoFormatado() {
        return DecimalFormat.getCurrencyInstance().format(saldo + limite);
    }

    @Override
    public String listarDados() {
        String nome;
        if (cliente instanceof PessoaFisica){
            nome = ((PessoaFisica)cliente).getNome();
        }else{
            nome = ((PessoaJuridica)cliente).getRazaoSocial();
        }
        return "NÚMERO: "+ numero + "\n"+
                "CORRENTISTA: "+ nome + "\n"+
                "SALDO: "+ getSaldoFormatado();
    }

    @Override
    public boolean sacar(double valor) {
        if (saldo+limite >= valor){
            saldo -=valor;
            return true;
        }else{
            return false;
        }
    }

    public void aplicarJuros(){
        if(saldo < 0)
            saldo = saldo -(saldo*juros/100);
    }
}
