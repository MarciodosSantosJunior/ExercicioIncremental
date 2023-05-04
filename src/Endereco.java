public class Endereco {
    private enum SiglasEstados {AC, AL, AM, AP, BA, CE, DF, ES, GO, MA, MG, MS, MT, PA, PB, PE, PI, PR, RJ, RN, RO, RR,
        RS, SC, SE, SP, TO};

    private String estado;
    private String cidade;
    private String rua;
    private String cep;
    private String numero;

    public Endereco(String estado, String cidade, String rua, String cep, String numero) {

        // Verifica se a sigla estatal é válida
        this.estadoInvalido(estado);

        this.estado = estado.toUpperCase();
        this.cidade = cidade;
        this.rua = rua;
        this.cep = cep;
        this.numero = numero;
    }

    public void atualizaEndereco(String estado, String cidade, String rua, String cep,
                                 String numero) {

        // Verifica se a sigla estatal é válida
        this.estadoInvalido(estado);

        this.estado = estado;
        this.cidade = cidade;
        this.rua = rua;
        this.cep = cep;
        this.numero = numero;
    }

    public void atualizaEndereco(String rua, String cep, String numero) {

        this.estado = "BA";
        this.cidade = "Salvador";
        this.rua = rua;
        this.cep = cep;
        this.numero = numero;
    }

    private void estadoInvalido(String estado) {
        try {
            SiglasEstados.valueOf(estado.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("A sigla estatal '" + estado.toUpperCase() + "' não é válida");
        }
    }

    public String getEstado() {
        return this.estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCidade() {
        return this.cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getRua() {
        return this.rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getCep() {
        return this.cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getNumero() {
        return this.numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    @Override
    public String toString() {
        return "Estado: " + estado +
                ", Cidade: " + cidade +
                ", Rua: " + rua +
                ", CEP: " + cep +
                ", Número: " + numero;
    }
}
