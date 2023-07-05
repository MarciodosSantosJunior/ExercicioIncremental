import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class UnidadeCompartilhada extends Imovel{
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
        this(numeroIPTU, "BA", "Salvador",  rua, cep, tipo, utilizacao, numero, identificacao, valorIPTU, itensLazer);
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
        double valorReferencia = this.getValorIPTU() * condominio.getItensLazer().size();

        if (condominio.getItensLazer().isEmpty()) {
            return this.getValorIPTU();
        }

        return valorReferencia;
    }

    @Override
    public String toString() {
        return super.toString() +
                "\nÁrea útil: " + this.identificacao;
    }
}
