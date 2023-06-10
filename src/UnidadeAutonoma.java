public class UnidadeAutonoma extends Imovel {
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
        super(numeroIPTU, rua, cep, tipo, utilizacao, numero, valorIPTU);
        this.areaUtil = areaUtil;
        this.areaConstruida = areaConstruida;
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
