package banco.tela;

import banco.modelo.*;

import javax.swing.*;

public class Principal {

    public static void main(String[] args) {

        Agencia agencia = new Agencia(1, "São Paulo-Centro");
        String opcaoPrincipal = "";

        do {
            opcaoPrincipal = showInputDialog("Cadastro de Contas para a agência"
                    + agencia.getNumero() + "-" + agencia.getNome() + "\n" + "\nOPÇÕES:\n"
                    + "1-Incluir cliente e conta\n"
                    + "2-Listar contas cadastradas\n"
                    + "3-Sair do sistema");
            if (opcaoPrincipal.equals("1")) {
                Cliente cliente = new Cliente();
                String tipoCliente = showInputDialog("Escolha o tipo de cliente:\n" + "F-Pessoa Física\n J-Pessoa Jurídica");
                if (tipoCliente.equalsIgnoreCase("F")) {
                    cliente = new PessoaFisica();
                    ((PessoaFisica) cliente).setNome(showInputDialog("Nome do Cliente: "));
                    ((PessoaFisica) cliente).setCpf(showInputDialog("Cpf do Cliente: "));
                } else if (tipoCliente.equalsIgnoreCase("J")) {
                    cliente = new PessoaJuridica();
                    ((PessoaJuridica) cliente).setRazaoSocial(showInputDialog("Razão Social:"));
                    ((PessoaJuridica) cliente).setCnpj(showInputDialog("Cnpj do Cliente: "));
                } else {
                    showMessageDialog("OPÇÃO INVÁLIDA! Encerrando o programa...");
                    return;
                }

                showMessageDialog("DADOS DO CLIENTE\n\n" + cliente.listarDados());

                //JOptionPane.showMessageDialog(null, "Possuímos " + Cliente.qtdClientes() + " cliente(s) cadastrados." );
                //Conta conta = new Conta(cliente);
                Conta conta;
                String tipoConta = showInputDialog("Tipo de conta a ser criada:\n" + "C-Conta Corrente\nP-Conta Poupança");
                if (tipoConta.equalsIgnoreCase("P")) {
                    conta = new ContaPoupanca(cliente);
                } else {
                    conta = new ContaCorrente(cliente);
                }

                agencia.incluirConta(conta);
                showMessageDialog("DADOS DA CONTA\n\n" + conta.listarDados());

                //MENU DE MOVIMENTAÇÃO DA CONTA
                int opcao = 0;
                String ret;
                do {
                    String mensagem = "SALDO EM CONTA:" + conta.getSaldoFormatado() + "\n\n" + "OPÇÕES:\n1-Depositar valor\n2-Sacar valor\n3-Finalizar";
                    try {
                        opcao = Integer.parseInt(showInputDialog(mensagem));
                        switch (opcao) {
                            case 1:
                                ret = showInputDialog("Valor do depósito:");
                                conta.depositar(Double.parseDouble(ret));
                                showMessageDialog("Depósito realizado!");
                                break;
                            case 2:
                                ret = showInputDialog("Valor do saque:");
                                if (conta.sacar(Double.parseDouble(ret))) {
                                    showMessageDialog("Saque realizado!");
                                } else {
                                    showMessageDialog("FALHA NO SAQUE!");
                                }
                        }
                    } catch (NumberFormatException ex) {
                        showMessageDialog("VALOR INVÁLIDO!");
                    }
                } while ((opcao == 1) || (opcao == 2));
            } else if (opcaoPrincipal.equals("2")) {
                if (agencia.getContas().size()==0){
                    showMessageDialog("NÃO HÁ CONTAS CADASTRADAS NO MOMENTO");
                }else{
                    for (Conta contas: agencia.getContas()){
                        showMessageDialog(contas.listarDados());
                    }
                }

            }
        }while ((opcaoPrincipal.equals("1")) || (opcaoPrincipal.equals("2")));
    }

    static void showMessageDialog(String message){
        JOptionPane.showMessageDialog(null, message);
    }

    static String showInputDialog(String message){
        return JOptionPane.showInputDialog(null, message);
    }

}
