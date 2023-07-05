import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class UnidadeAutonoma extends Imovel{
    private double areaUtil;
    private double areaConstruida;

    public UnidadeAutonoma(String numeroIPTU, String estado, String cidade, String rua,
                           String cep, String tipo, String utilizacao, String numero,
                           double areaUtil, double areaConstruida, double valorIPTU) {
        super(numeroIPTU, estado, cidade, rua, cep, tipo, utilizacao, numero, valorIPTU);
        this.areaUtil = areaUtil;
        this.areaConstruida = areaConstruida;
    }

    public UnidadeAutonoma(String numeroIPTU, String rua, String cep, String tipo,
                           String utilizacao, String numero, double areaUtil, double areaConstruida, double valorIPTU) {
        this(numeroIPTU, "BA", "Salvador", rua, cep, tipo, utilizacao, numero, areaUtil, areaConstruida,  valorIPTU);
    }

    public double getAreaUtil() {
        return areaUtil;
    }

    public void setAreaUtil(double areaUtil) {
        this.areaUtil = areaUtil;
    }

    public double getAreaConstruida() {
        return this.areaConstruida;
    }

    public void setAreaConstruida(double areaConstruida) {
        this.areaConstruida = areaConstruida;
    }

    private static Calendar stringParaCalendar(String dataString) throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateFormat.parse(dataString));
        return calendar;
    }

    @Override
    public String toString() {
        return super.toString() +
                "\nÁrea útil: " + this.areaUtil +
                "\nÁrea construída: " + this.areaConstruida;
    }

    @Override
    public double calcularValorReferencia() {
        return this.areaConstruida * 15;
    };

}
