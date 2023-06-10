import java.util.ArrayList;

public class Condominio {
    private Endereco endereco;
    private ArrayList<String> itensLazer;

    public Condominio(String estado, String cidade, String rua, String cep, String numero, ArrayList<String> itensLazer) {
        this.endereco = new Endereco(estado, cidade, rua, cep, numero);
        this.itensLazer = itensLazer;
    }

    public Condominio(String estado, String cidade, String rua, String cep, String numero) {
        this.endereco = new Endereco(estado, cidade, rua, cep, numero);
        this.itensLazer = new ArrayList<>();
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public ArrayList<String> getItensLazer() {
        return itensLazer;
    }

    public void setItensLazer(ArrayList<String> itensLazer) {
        this.itensLazer = itensLazer;
    }

    @Override
    public String toString() {
        return "Endereço do Condomínio: " + endereco.toString() +
                "\nItens de Lazer: " + itensLazer;
    }
}
