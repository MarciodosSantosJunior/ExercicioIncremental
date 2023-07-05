import java.util.concurrent.TimeUnit;
import java.util.ArrayList;
import java.util.Scanner;

public class Aplicacao {
    private Scanner in = new Scanner(System.in);
    private SistemaDeImoveis si;

    public Aplicacao(SistemaDeImoveis si) {
        this.si = si;
    }

    public static void main(String[] args) throws InterruptedException{

        SistemaDeImoveis si = new SistemaDeImoveis();
        Aplicacao app = new Aplicacao(si);
        app.menuPrincipal();
    }

    public void menuPrincipal() throws InterruptedException{
        boolean aplicacaoAtiva = true;
        while (aplicacaoAtiva) {
            int escolha = 0;
            while (escolha < 1 || escolha > 7) {
                TimeUnit.SECONDS.sleep(1);
                System.out.println("\n=========================================");
                System.out.println("|           MENU DE OPÇÕES              |");
                System.out.println("=========================================");
                System.out.println("|      [1] Cadastrar Imóvel             |");
                System.out.println("|      [2] Cadastrar Proprietário       |");
                System.out.println("|      [3] Agendar Bloqueio             |");
                System.out.println("|      [4] Obter Valor de Referência    |");
                System.out.println("|      [5] Disponibilidade de Imóvel    |");
                System.out.println("|      [6] Consultar Aluguel            |");
                System.out.println("|      [7] Sair da aplicação            |");
                System.out.println("=========================================\n");
                System.out.print("\n [ ] ESCOLHA: ");
                if (in.hasNextInt()) {
                    escolha = in.nextInt();
                    if (escolha < 1 || escolha > 7) {
                        System.out.println("Opção inválida. Tente novamente.");
                    }
                } else {
                    in.next();
                    System.out.println("Opção inválida. Tente novamente.");
                }
            }

            switch (escolha) {
                case 1:
                    this.cadastrarImovel();
                    break;
                case 2:
                    this.cadastrarProprietario();
                    break;
                case 3:
                    this.agendarBloqueio();
                    break;
                case 4:
                    this.exibeValorDeReferencia();
                    break;
                case 5:
                    this.verificarDisponibilidadeImovel();
                    break;
                case 6:
                    this.consultarAluguel();
                    break;
                default:
                    aplicacaoAtiva = false;
            }

        }
    }

    public void cadastrarImovel() {
        String cpfProprietario = "", respostaCadastrar = "", respostaProprietario = "", respostaSemProprietario = "";
        in.nextLine();
        do {
            System.out.print(" [*] O proprietário do imóvel já está cadastrado? [s/n] ");
            respostaProprietario = in.nextLine();
        } while (!respostaProprietario.equalsIgnoreCase("s") && !respostaProprietario.equalsIgnoreCase("n"));

        if (respostaProprietario.equalsIgnoreCase("n")) {

            do {
                System.out.print(" [*] Deseja cadastrar um imóvel sem proprietário? [s/n] ");
                respostaSemProprietario = in.nextLine();
            } while (!respostaProprietario.equalsIgnoreCase("s") && !respostaProprietario.equalsIgnoreCase("n"));

            if (respostaSemProprietario.equalsIgnoreCase("n")) {
                System.out.print("\n [ PRESSIONE ENTER ]");
                cpfProprietario = this.cadastrarProprietario();
                if (cpfProprietario.equalsIgnoreCase("0")) {
                    return;
                }
            }
        } else {
            System.out.print(" [-] Digite o CPF do proprietário: ");
            cpfProprietario = in.nextLine();
        }

        System.out.println("\n   ============= CADASTRO DE IMÓVEIS =============   ");
        String respostaAutonomo = null;
        do {
            System.out.print(" [*] O imóvel é autônomo ou compartilhado? [autonomo/compartilhado]: ");
            respostaAutonomo = in.nextLine();
        } while (!respostaAutonomo.equalsIgnoreCase("autonomo") && !respostaAutonomo.equalsIgnoreCase("compartilhado"));


        if(respostaAutonomo.equals("autonomo")) {
            this.cadastrarImovelAutonamo(cpfProprietario);
        } else {
            this.cadastrarImovelCompartilhado(cpfProprietario);
        }
    }

    public void cadastrarImovelAutonamo(String cpfProprietario) {

        System.out.print(" [-] Digite o número do IPTU: ");
        String numeroIPTU = in.nextLine();

        System.out.print(" [-] Digite o valor do IPTU: ");
        double valorIPTU = in.nextDouble();
        in.nextLine();

        System.out.print(" [-] Digite o estado: ");
        String estado = in.nextLine();

        System.out.print(" [-] Digite a cidade: ");
        String cidade = in.nextLine();

        System.out.print(" [-] Digite a rua: ");
        String rua = in.nextLine();

        System.out.print(" [-] Digite o CEP: ");
        String cep = in.nextLine();

        System.out.print(" [-] Digite o tipo do imóvel: ");
        String tipo = in.nextLine();

        System.out.print(" [-] Digite a utilização do imóvel: ");
        String utilizacao = in.nextLine();

        System.out.print(" [-] Digite o número do imóvel: ");
        String numero = in.nextLine();

        System.out.print(" [-] Digite a área util do imóvel em m²: ");
        double areaUtil = in.nextDouble();
        in.nextLine();

        System.out.print(" [-] Digite a área contruida do imóvel em m²: ");
        double areaConstruida = in.nextDouble();
        in.nextLine();

        UnidadeAutonoma unidadeAutonoma = new UnidadeAutonoma(numeroIPTU, estado, cidade, rua, cep, tipo, utilizacao, numero,
                areaUtil, areaConstruida, valorIPTU);

        if (cpfProprietario.isEmpty()) {
            this.si.cadastrarImovel(unidadeAutonoma);
        } else {
            try {
                this.si.cadastrarImovel(cpfProprietario, unidadeAutonoma);
            } catch (NaoExistemException e) {
                System.out.println("\n========================================================");
                System.out.println("  Não existem proprietários cadastrados no momento.");
                System.out.println("========================================================");
                return;
            } catch (NaoCadastradoException e) {
                System.out.println("\n=====================================================");
                System.out.println("  O proprietário especificado não está cadastrado.");
                System.out.println("=====================================================");
                return;
            }
        }

        System.out.println("\n=======================================");
        System.out.println("      IMOVEL AUTÔNOMO ADICIONADO");
        System.out.println("---------------------------------------");

    }

    public void cadastrarImovelCompartilhado(String cpfProprietario) {
        String respostaItensDeLazer = null;
        String item = null;
        ArrayList<String> itensLazer = new ArrayList<>();

        System.out.print(" [-] Digite o número do IPTU: ");
        String numeroIPTU = in.nextLine();

        System.out.print(" [-] Digite o valor do IPTU: ");
        double valorIPTU = in.nextDouble();
        in.nextLine();

        System.out.print(" [-] Digite o estado: ");
        String estado = in.nextLine();

        System.out.print(" [-] Digite a cidade: ");
        String cidade = in.nextLine();

        System.out.print(" [-] Digite a rua: ");
        String rua = in.nextLine();

        System.out.print(" [-] Digite o CEP: ");
        String cep = in.nextLine();

        System.out.print(" [-] Digite o tipo do imóvel: ");
        String tipo = in.nextLine();

        System.out.print(" [-] Digite a utilização do imóvel: ");
        String utilizacao = in.nextLine();

        System.out.print(" [-] Digite o número do imóvel: ");
        String numero = in.nextLine();

        System.out.print(" [-] Como pode-se identificar o seu imóvel no condomínio: ");
        String identificacao = in.nextLine();

        do {
            System.out.print(" [*] O condomínio possui itens de lazer [s/n] ");
            respostaItensDeLazer = in.nextLine();
        } while (!respostaItensDeLazer.equalsIgnoreCase("s") && !respostaItensDeLazer.equalsIgnoreCase("n"));

        if (respostaItensDeLazer.equalsIgnoreCase("s")) {
            do {
                System.out.print(" [*] Digite um item de lazer ['q' para sair]:  ");
                item = in.nextLine();

                if (item.equalsIgnoreCase("q")) {break;}
                itensLazer.add(item);
            } while (true);
        }

        UnidadeCompartilhada unidadeCompartilhada = new UnidadeCompartilhada(numeroIPTU, estado, cidade, rua, cep, tipo, utilizacao,
                numero, identificacao, valorIPTU, itensLazer);

        if (cpfProprietario.isEmpty()) {
            this.si.cadastrarImovel(unidadeCompartilhada);
        } else {
            try {
                this.si.cadastrarImovel(cpfProprietario, unidadeCompartilhada);
            } catch (NaoExistemException e) {
                System.out.println("\n========================================================");
                System.out.println("  Não existem proprietários cadastrados no momento.");
                System.out.println("========================================================");
                return;
            } catch (NaoCadastradoException e) {
                System.out.println("\n=====================================================");
                System.out.println("  O proprietário especificado não está cadastrado.");
                System.out.println("=====================================================");
                return;
            }
        }

        System.out.println("\n=======================================");
        System.out.println("    IMOVEL COMPARTILHADO ADICIONADO");
        System.out.println("---------------------------------------");
    }

    public String cadastrarProprietario() {
        in.nextLine();
        System.out.println("\n===== CADASTRO DE PROPRIETÁRIOS =====");

        System.out.print(" [-] Digite o CPF do proprietário: ");
        String cpf = in.nextLine();

        System.out.print(" [-] Digite a identidade do proprietário: ");
        String identidade = in.nextLine();

        System.out.print(" [-] Digite o nome do proprietário: ");
        String nome = in.nextLine();

        System.out.print(" [-] Digite o estado do proprietário: ");
        String estado = in.nextLine();

        System.out.print(" [-] Digite a cidade do proprietário: ");
        String cidade = in.nextLine();

        System.out.print(" [-] Digite a rua do proprietário: ");
        String rua = in.nextLine();

        System.out.print(" [-] Digite o CEP do proprietário: ");
        String cep = in.nextLine();

        System.out.print(" [-] Digite o número do proprietário: ");
        String numero = in.nextLine();

        try {
            this.si.cadastrarProprietario(cpf, identidade, nome, estado, cidade, rua, cep, numero);
            System.out.println("\n=======================================");
            System.out.println("      NOVO PROPRIETÁRIO ADICIONADO");
            System.out.println("---------------------------------------");
            return cpf;
        } catch (UsuarioExistenteException e) {
            System.out.println("\nxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
            System.out.println("   Erro no cadastro do proprietário: " + e.getMessage());
            System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
        }

        return "0";
    }

    public void agendarBloqueio() {
        System.out.println("\n===== BLOQUEAR IMÓVEL =====");
        in.nextLine();

        System.out.print(" [-] Digite o número de IPTU do imóvel: ");
        String numeroIPTU = in.nextLine();

        System.out.print(" [-] Digite a data de bloqueio (no formato dd/mm/yyyy): ");
        String dataBloqueio = in.nextLine();

        try {
            this.si.bloquearImovel(numeroIPTU, dataBloqueio);
        } catch (NaoExistemException e) {
            System.out.println("\n========================================================");
            System.out.println("  Não existem imóveis cadastrados no momento.");
            System.out.println("========================================================");
            return;
        } catch (NaoCadastradoException e) {
            System.out.println("\n=====================================================");
            System.out.println("  O imóvel especificado não está cadastrado.");
            System.out.println("=====================================================");
            return;
        }

        System.out.println("\n==================================================");
        System.out.println("    IMÓVEL ("+ numeroIPTU +") BLOQUEADO NA DATA: " + dataBloqueio);
        System.out.println("--------------------------------------------------");
    }

    public void verificarDisponibilidadeImovel() {
        System.out.println("\n===== VERIFICAR DISPONIBILIDADE =====");
        in.nextLine();

        System.out.print(" [-] Digite o número de IPTU do imóvel: ");
        String numeroIPTU = in.nextLine();

        System.out.print(" [-] Digite a Data Inicial (no formato dd/mm/yyyy): ");
        String datainicio = in.nextLine();

        System.out.print(" [-] Digite a Data Final (no formato dd/mm/yyyy): ");
        String datafim = in.nextLine();

        try {
            boolean disponivel = this.si.verificarDisponibilidade( numeroIPTU, datainicio, datafim);

            if (disponivel) {
                System.out.println("\n=======================================");
                System.out.println("    Imóvel Disponível para Aluguel       ");
                System.out.println("---------------------------------------");
            } else {
                System.out.println("\n=======================================");
                System.out.println("   Imóvel NÃO Disponivel para Aluguel    ");
                System.out.println("---------------------------------------");
            }
        } catch (Exception e) {
            System.out.println("Ocorreu um problema durante a operação");
        }

    }

    public void consultarAluguel() {
        String repostaReferencia = null;
        double valor = 0;

        System.out.println("\n===== CONSULTAR ALUGUEL =====");
        in.nextLine();

        System.out.print(" [-] Digite o número de IPTU do imóvel: ");
        String numeroIPTU = in.nextLine();

        do {
            System.out.print(" [*] Consulta de DIA ou PERÍODO [d/p] ");
            repostaReferencia = in.nextLine();
        } while (!repostaReferencia.equalsIgnoreCase("d") && !repostaReferencia.equalsIgnoreCase("p"));

        if (repostaReferencia.equalsIgnoreCase("d")) {
            System.out.print(" [-] Digite a Data (no formato dd/mm/yyyy): ");
            String dataUnica = in.nextLine();

            try {
               valor = this.si.consultarAluguel( numeroIPTU, dataUnica);
            } catch (Exception e) {
                System.out.println("Ocorreu um problema durante a operação");
            }
        } else {
            System.out.print(" [-] Digite a Data Inicial (no formato dd/mm/yyyy): ");
            String dataInicial = in.nextLine();

            System.out.print(" [-] Digite a Data Final (no formato dd/mm/yyyy): ");
            String dataFinal = in.nextLine();

            try {
                valor = this.si.consultarAluguel( numeroIPTU, dataInicial, dataFinal);
            } catch (Exception e ){
                System.out.println("Ocorreu um problema durante a operação");
            }
        }

        System.out.println("\n=======================================");
        System.out.println("   Valor de Aluguel do Imóvel");
        System.out.println("---------------------------------------");
        System.out.println("Número de IPTU: " + numeroIPTU);
        System.out.println("Valor: R$ " + valor);
        System.out.println("=======================================");
    }

    public void exibeValorDeReferencia(){
        String repostaReferencia = null;

        System.out.println("\n===== OBTER VALOR DE REFERÊNCIA =====");
        in.nextLine();

        System.out.print(" [-] Digite o número de IPTU do imóvel: ");
        String numeroIPTU = in.nextLine();

        do {
            System.out.print(" [*] Gostaria de adicionar algum índice de sazonalidade [s/n] ");
            repostaReferencia = in.nextLine();
        } while (!repostaReferencia.equalsIgnoreCase("s") && !repostaReferencia.equalsIgnoreCase("n"));

        if (repostaReferencia.equalsIgnoreCase("s")) {
            this.exibeValorDeReferenciaSazonal(numeroIPTU);
        } else {
            this.exibeValorDeReferenciaComum(numeroIPTU);
        }
    }

    public void exibeValorDeReferenciaSazonal(String numeroIPTU){
        int sazonalidade = 0;
        while (sazonalidade < 1 || sazonalidade > 5) {
            System.out.println("\n=========================================");
            System.out.println("|            SAZONALIDADE                |");
            System.out.println("=========================================");
            System.out.println("|      [1] Reveillon                     |");
            System.out.println("|      [2] Carnaval                      |");
            System.out.println("|      [3] Feriado Alta Estação          |");
            System.out.println("|      [4] Feriado Baixa Estação         |");
            System.out.println("=========================================\n");
            System.out.print("\n [ ] ESCOLHA: ");

            if (in.hasNextInt()) {
                sazonalidade = in.nextInt();
                if (sazonalidade < 1 || sazonalidade > 4) {
                    System.out.println("Opção inválida. Tente novamente.");
                }
            } else {
                in.next();
                System.out.println("Opção inválida. Tente novamente.");
            }
        }
        try {
            double valor = this.si.valorDeReferencia(numeroIPTU, sazonalidade);
            System.out.println("\n=======================================");
            System.out.println("  Valor de Referência do Imóvel");
            System.out.println("---------------------------------------");
            System.out.println("Número de IPTU: " + numeroIPTU);
            System.out.println("Valor: R$ " + valor);
            System.out.println("=======================================");
        } catch (Exception e ){
            System.out.println("Ocorreu um problema durante a operação");
        }

    }

    public void exibeValorDeReferenciaComum(String numeroIPTU) {
        try {
            double valor = this.si.valorDeReferencia(numeroIPTU);
            System.out.println("\n=======================================");
            System.out.println("  Valor de Referência do Imóvel");
            System.out.println("---------------------------------------");
            System.out.println("Número de IPTU: " + numeroIPTU);
            System.out.println("Valor: R$ " + valor);
            System.out.println("=======================================");
        } catch (Exception e) {
            System.out.println("Ocorreu um problema durante a operação");
        }
    }
}

