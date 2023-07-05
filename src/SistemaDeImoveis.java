
import java.util.ArrayList;

public class SistemaDeImoveis {
    private ArrayList<Proprietario> proprietarios;
    private ArrayList<Imovel> imoveis;

    public SistemaDeImoveis() {
        this.proprietarios = new ArrayList<>();
        this.imoveis = new ArrayList<>();
    };

    public void cadastrarProprietario(String cpf, String identidade, String nome, String estado, String cidade, String rua, String cep, String numero) throws UsuarioExistenteException {
        for (Proprietario proprietario : this.proprietarios) {
            if (proprietario.getCpf().equals(cpf)) {
                throw new UsuarioExistenteException("Proprietário já existe");
            }
        }

        proprietarios.add(new Proprietario(cpf, identidade, nome, estado, cidade, rua, cep, numero));
    }

    public void cadastrarImovel(String cpfProprietario, Imovel imovel) throws NaoExistemException, NaoCadastradoException{

        if (this.proprietarios.isEmpty()) {
            throw new NaoExistemException("Não existem proprietários cadastrados");
        }

        for (Proprietario proprietario : this.proprietarios) {
            if(proprietario.getCpf().equals(cpfProprietario)) {
                proprietario.adicionaImovel(imovel);
                this.imoveis.add(imovel);
                return;
            }
        }
        throw new NaoCadastradoException("Proprietário não cadastrado.");
    }

    public void cadastrarImovel(Imovel imovel) {
        this.imoveis.add(imovel);
    }

    public void bloquearImovel( String numeroIPTU, String data) throws  NaoExistemException, NaoCadastradoException {
        if (this.imoveis.isEmpty()) {
            throw new NaoExistemException("Não existem imóveis cadastrados");
        }

        for (Imovel imovel : this.imoveis) {
            if (imovel.getNumeroIPTU().equals(numeroIPTU)) {
               imovel.adicionarDataBloqueadaAgenda(data);
               return;
            }
        }
        throw new NaoCadastradoException("Imóvel não cadastrado");
    }

    public boolean verificarDisponibilidade(String numeroIPTU, String datainicio, String datafim) throws Exception {
        Imovel imovel = this.obtemImovel(numeroIPTU);
        return imovel.consultarDisponibilidade(datainicio, datafim);
    }

    public double consultarAluguel(String numeroIPTU, String datainicio, String datafim) throws Exception {
        Imovel imovel = this.obtemImovel(numeroIPTU);
        return imovel.calcularPrecoAluguel(datainicio, datafim);

    }

    public double consultarAluguel(String numeroIPTU, String dataUnica) throws Exception {
        Imovel imovel = this.obtemImovel(numeroIPTU);
        return imovel.calcularPrecoAluguel(dataUnica);

    }

    public double valorDeReferencia(String numeroIPTU) throws Exception {
        Imovel imovel = this.obtemImovel(numeroIPTU);
        return imovel.calcularValorReferencia();
    }

    public double valorDeReferencia(String numeroIPTU, int sazonalidade) throws Exception {

        Imovel imovel = this.obtemImovel(numeroIPTU);

        double valorReferencia = imovel.calcularValorReferencia();
        if(imovel instanceof UnidadeCompartilhada) {
            UnidadeCompartilhada uc = (UnidadeCompartilhada) imovel;
            if (uc.getQtdItensLazer() < 1) { valorReferencia = valorReferencia * 0.9;}
        }

        double indiceSazonalidade = Sazonalidade.obterValorSazonalidade(sazonalidade);
        return valorReferencia + (valorReferencia*(indiceSazonalidade*0.01));

    }

    private Imovel obtemImovel(String numeroIPTU) throws NaoExistemException, NaoCadastradoException {
        if (this.imoveis.isEmpty()) {
            throw new NaoExistemException("Não existem imóveis cadastrados.");
        }
        for (Imovel imovel : this.imoveis) {
            if (imovel.getNumeroIPTU().equals(numeroIPTU)) {
                return imovel;
            }
        }
        throw new NaoCadastradoException("Imóvel não cadastrado");
    }
}
