import java.util.ArrayList;

public class UnidadeCompartilhada extends Imovel {
    private String identificacao;
    private Condominio condominio;

    public UnidadeCompartilhada(String numeroIPTU, String estado, String cidade, String rua,
                                String cep, String tipo, String utilizacao, String numero,
                                String identificacao, double valorIPTU, ArrayList<String> itensLazer) {
        super(numeroIPTU, estado, cidade, rua, cep, tipo, utilizacao, numero, valorIPTU);
        this.identificacao = identificacao;
        this.condominio = new Condominio(estado, cidade, rua, cep, numero, itensLazer);
    }

    public UnidadeCompartilhada(String numeroIPTU, String rua, String cep, String tipo,
                                String utilizacao, String numero, String identificacao,
                                Condominio condominio, double valorIPTU, ArrayList<String> itensLazer) {
        super(numeroIPTU, rua, cep, tipo, utilizacao, numero, valorIPTU);
        this.identificacao = identificacao;
        this.condominio = condominio;
        this.condominio = new Condominio("BA", "Salvador", rua, cep, numero, itensLazer);
    }


    public String getIdentificacao() {
        return identificacao;
    }

    public void setIdentificacao(String identificacao) {
        this.identificacao = identificacao;
    }

    public Condominio getCondominio() {
        return condominio;
    }

    public void setCondominio(Condominio condominio) {
        this.condominio = condominio;
    }

    public ArrayList<String> getItensLazer() {
        return  this.condominio.getItensLazer();
    }

    public int getQtdItensLazer() {
        return this.condominio.getItensLazer().size();
    }

    @Override
    public double calcularValorReferencia() {
        double valorReferencia = getValorIPTU() * condominio.getItensLazer().size();

        if (condominio.getItensLazer().isEmpty()) {
            valorReferencia *= 0.9; // Redução de 10% para unidade compartilhada sem itens de lazer
        }

        return valorReferencia;
    }

    @Override
    public String toString() {
        return super.toString() +
                "\nÁrea útil: " + this.identificacao;
    }
}
