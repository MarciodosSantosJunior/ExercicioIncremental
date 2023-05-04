public class Imovel {
    private String numeroIPTU;
    private String tipo;
    private String utilizacao;
    private Endereco endereco;

    public Imovel(String numeroIPTU, String estado, String cidade, String rua,
                  String cep, String tipo, String utilizacao, String numero) {
        this.numeroIPTU = numeroIPTU;
        this.tipo = tipo;
        this.utilizacao = utilizacao;
        this.endereco = new Endereco(estado, cidade, rua, cep, numero);
    }

    public Imovel(String numeroIPTU, String rua, String cep, String tipo,
                  String utilizacao, String numero) {
        this.numeroIPTU = numeroIPTU;
        this.tipo = tipo;
        this.utilizacao = utilizacao;
        this.endereco = new Endereco("BA", "Salvador", rua, cep, numero);
    }

    public String getNumeroIPTU() {
        return this.numeroIPTU;
    }

    public void setNumeroIPTU(String numeroIPTU) {
        this.numeroIPTU = numeroIPTU;
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

    public String getTipo() {
        return this.tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getUtilizacao() {
        return this.utilizacao;
    }

    public void setUtilizacao(String utilizacao) {
        this.utilizacao = utilizacao;
    }

    public String getNumero() {
        return endereco.getNumero();
    }

    public void setNumero(String numero) {
        this.endereco.setNumero(numero);
    }

    @Override
    public String toString() {
        return "Número IPTU: " + numeroIPTU +
                "\nTipo: " + tipo +
                "\nUtilização: " + utilizacao +
                "\nEndereço: " + endereco.toString();
    }
}
