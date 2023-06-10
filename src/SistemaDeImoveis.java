
import java.util.ArrayList;

public class SistemaDeImoveis {
    private ArrayList<Proprietario> proprietarios;

    public SistemaDeImoveis() {
        this.proprietarios = new ArrayList<>();
    };

    public void cadastrarProprietario(String cpf, String identidade, String nome, String estado,
                                 String cidade, String rua, String cep, String numero) {
        proprietarios.add(new Proprietario(cpf, identidade, nome, estado, cidade, rua, cep, numero));
    }

    public void cadastrarImovel(String cpfProprietario, Imovel imovel) {

        if (this.proprietarios.isEmpty()) {
            System.out.println("Não existem proprietários.");
            return;
        }

        for (Proprietario proprietario : this.proprietarios) {
            if(proprietario.getCpf().equals(cpfProprietario)) {
                proprietario.adicionaImovel(imovel);
                System.out.println("\nImovel cadastrado.");
                return;
            }
        }
        System.out.println("Proprietário não encontrado.");
    }

    public void bloquearImovel(String cpf, String numeroIPTU, String data) {
        if (this.proprietarios.isEmpty()) {
            System.out.println("Não existem proprietários cadastrados para o Imóvel");
            return;
        }

        for (Proprietario proprietario : this.proprietarios) {
            if (proprietario.getCpf().equals(cpf)) {
                proprietario.adicionarDataBloqueadaImovel(numeroIPTU, data);
                System.out.println("DATA: " + data + " do Imovel com IPTU " + numeroIPTU + " foi bloqueada" );
                return;
            }
        }
        System.out.println("Erro ao bloquear Imovel.");
    }

    public double valorDeReferencia(String cpf, String numeroIPTU) {
        if (this.proprietarios.isEmpty()) {
            System.out.println("Não existem proprietários cadastrados para o Imóvel");
            return -1;
        }

        for (Proprietario proprietario : this.proprietarios) {
            if (proprietario.getCpf().equals(cpf)) {
                for (Imovel imovel : proprietario.getImoveisParaLocacao()) {
                    if (imovel.getNumeroIPTU().equals(numeroIPTU)) {
                        if (imovel instanceof UnidadeAutonoma) {
                            UnidadeAutonoma unidadeAutonoma = (UnidadeAutonoma) imovel;
                            return unidadeAutonoma.calcularValorReferencia();
                        } else if (imovel instanceof UnidadeCompartilhada) {
                            UnidadeCompartilhada unidadeCompartilhada = (UnidadeCompartilhada) imovel;
                            return unidadeCompartilhada.calcularValorReferencia();
                        }
                    }
                }
                System.out.println("Imóvel não encontrado para o número de IPTU fornecido");
                return -1;
            }
        }
        System.out.println("Proprietário não encontrado para o CPF fornecido");
        return -1;
    }

    public double valorDeReferencia(String cpf, String numeroIPTU, int sazonalidade) {
        int indice = Sazonalidade.obterValorSazonalidade(sazonalidade);
        double valorReferencia = this.valorDeReferencia(cpf, numeroIPTU);
        return  valorReferencia + (valorReferencia*(indice*0.01));
    }
}
