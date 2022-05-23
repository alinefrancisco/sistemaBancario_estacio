package banco.modelo;

public class PessoaFisica extends Cliente{

    private String nome;
    private String cpf;

    public PessoaFisica(){
        super();
        nome = "INDEFINIDO";
        cpf = "";
    }

    public PessoaFisica(String cidade, String estado, String nome, String cpf){
        super(cidade,estado);
        this.nome = nome;
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String listarDados(){
        return "NOME: " + nome + "\n" + "CPF: " + cpf + "\n" + super.listarDados();
    }
}
