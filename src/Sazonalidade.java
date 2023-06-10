public enum Sazonalidade {
    REVEILLON(1, 20),
    CARNAVAL(2, 15),
    FERIADO_ALTA_ESTACAO(3, 10),
    FERIADO_BAIXA_ESTACAO(4, 5),
    COMUM(0, 0);

    private final int numero;
    private final int valor;

    Sazonalidade(int numero, int valor) {
        this.numero = numero;
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }

    public static int obterValorSazonalidade(int numero) {
        for (Sazonalidade sazonalidade : Sazonalidade.values()) {
            if (sazonalidade.numero == numero) {
                return sazonalidade.valor;
            }
        }
        throw new IllegalArgumentException("Número inválido: " + numero);
    }
}
