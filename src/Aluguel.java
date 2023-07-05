public interface Aluguel {
    boolean consultarDisponibilidade(String dataInicio, String dataFim);
    double calcularPrecoAluguel(String data);
    double calcularPrecoAluguel(String dataInicio, String dataFim);
}
