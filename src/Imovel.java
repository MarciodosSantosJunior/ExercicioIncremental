import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class Imovel {
    private String numeroIPTU;
    private double valorIPTU;
    private String tipo;
    private String utilizacao;
    private Endereco endereco;
    private Agenda agenda;

    public Imovel(String numeroIPTU, String estado, String cidade, String rua,
                  String cep, String tipo, String utilizacao, String numero, double valorIPTU) {
        this.numeroIPTU = numeroIPTU;
        this.valorIPTU = valorIPTU;
        this.tipo = tipo;
        this.utilizacao = utilizacao;
        this.endereco = new Endereco(estado, cidade, rua, cep, numero);
        this.agenda = new Agenda();
    }

    public Imovel(String numeroIPTU, String rua, String cep, String tipo,
                  String utilizacao, String numero, double valorIPTU) {
        this.numeroIPTU = numeroIPTU;
        this.valorIPTU = valorIPTU;
        this.tipo = tipo;
        this.utilizacao = utilizacao;
        this.endereco = new Endereco("BA", "Salvador", rua, cep, numero);
        this.agenda = new Agenda();
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

    public double getValorIPTU() {
        return this.valorIPTU;
    }

    public void setValorIPTU(double valorIPTU) {
        this.valorIPTU = valorIPTU;
    }

    public void adicionarDataAlugadaAgenda(String data) {
        this.agenda.adicionarDataAlugada(data);
    }

    public void adicionarDataBloqueadaAgenda(String data) {
        this.agenda.adicionarDataBloqueada(data);
    }

    public void adicionarDataDisponivelAgenda(String data) {
        this.agenda.adicionarDataDisponivel(data);
    }

    public boolean removerDataAlugadaAgenda(String data) {
        return this.agenda.removerDataAlugada(data);
    }

    public boolean removerDataBloqueadaAgenda(String data) {
        return this.agenda.removerDataBloqueada(data);
    }

    public boolean removerDataDisponivelAgenda(String data) {
        return this.agenda.removerDataDisponivel(data);
    }

    public boolean dataEstaAlugada(String data) {
        return this.agenda.dataEstaAlugada(data);
    }

    public boolean dataEstaBloqueada(String data) {
        return this.agenda.dataEstaBloqueada(data);
    }

    public boolean dataEstaDisponivel(String data) {
        return this.agenda.dataEstaDisponivel(data);
    }

    public void exibeDatasAlugadas() {
        this.agenda.exibeDatasAlugadas();
    }

    public void exibeDatasBloqueadas() {
        this.agenda.exibeDatasBloqueadas();
    }

    public void exibeDatasDisponiveis() {
        this.agenda.exibeDatasDisponiveis();
    }

    public abstract double calcularValorReferencia();

    @Override
    public String toString() {
        return "Número IPTU: " + numeroIPTU +
                "\nTipo: " + tipo +
                "\nUtilização: " + utilizacao +
                "\nEndereço: " + endereco.toString();
    }
}
