import java.util.ArrayList;

public class Proprietario {
    private String cpf;
    private String identidade;
    private String nome;
    private Endereco endereco;
    private ArrayList<Imovel> imoveisParaLocacao;

    public Proprietario(String cpf, String identidade, String nome, String estado,
                        String cidade, String rua, String cep, String numero) {
        this.cpf = cpf;
        this.identidade = identidade;
        this.nome = nome;
        this.endereco = new Endereco(estado, cidade, rua, cep, numero);
        this.imoveisParaLocacao = new ArrayList<>();
    }

    public void atualizaEndereco(String estado, String cidade, String rua, String cep,
                                 String numero) {
        this.endereco.atualizaEndereco(estado, cidade, rua, cep, numero);
    }

    public void atualizaEndereco(String rua, String cep, String numero) {

        this.endereco.atualizaEndereco(rua, cep, numero);
    }

    public void adicionaImovel(Imovel imovel) {
        this.imoveisParaLocacao.add(imovel);
    }

    public void getImoveisPorTipo(String tipo) {
        for (Imovel imovel : this.imoveisParaLocacao) {
            if (imovel.getTipo().equals(tipo)) {
                System.out.println(imovel.toString() + "\n");
            }
        }
    }

    public String getCpf() {
        return this.cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getIdentidade() {
        return this.identidade;
    }

    public void setIdentidade(String identidade) {
        this.identidade = identidade;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEstado() {
        return this.endereco.getEstado();
    }

    public void setEstado(String estado) {
        this.endereco.setEstado(estado);
    }

    public String getCidade() {
        return this.endereco.getCidade();
    }

    public void setCidade(String cidade) {
        this.endereco.setCidade(cidade);
    }

    public String getRua() {
        return this.endereco.getRua();
    }

    public void setRua(String rua) {
        this.endereco.setRua(rua);
    }

    public String getCep() {
        return this.endereco.getCep();
    }

    public void setCep(String cep) {

        this.endereco.setCep(cep);
    }

    public String getNumero() {
        return this.endereco.getNumero();
    }

    public void setNumero(String numero) {
        this.endereco.setNumero(numero);
    }
}
