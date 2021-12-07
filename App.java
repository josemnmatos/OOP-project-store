import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class App {
    // ATRIBUTOS ------------------------------------

    /**
     * Cliente ativo na aplicação
     */
    private Cliente clienteAtivo;

    /**
     * Data corrente da aplicação
     */
    private Data dataAtual = new Data(21, 11, 2021);

    /**
     * Lista de clientes existentes
     */
    private ArrayList<Cliente> clientes = new ArrayList<>();

    /**
     * Lista de produtos disponíveis para compra
     */
    private ArrayList<Produto> produtos = new ArrayList<>();

    /**
     * Lista de promoções (ativas ou não) existentes
     */
    private ArrayList<Promocao> promocoes = new ArrayList<>();

    /**
     * Lista de compras efetuadas até ao momento
     */
    private ArrayList<Compra> compras = new ArrayList<>();

    Scanner scanner = new Scanner(System.in);

    // FUNCIONAMENTO ------------------------------------

    /**
     * Método que lê um inteiro introduzido, o guarda numa varíavel e o retorna
     * 
     * @return Inteiro introduzido
     */
    public int scanInt() {
        int input = scanner.nextInt();
        scanner.nextLine();
        return input;
    }

    /**
     * Método que lê uma String introduzida(Até ao '/n'), a guarda numa varíavel e a
     * retorna
     * 
     * @return String introduzida
     */
    public String scanString() {
        String input = scanner.nextLine();
        return input;
    }

    /**
     * Método para facilitar interpretação da consola
     */
    public void divisoria() {
        System.out.println("-------------------------");
    }

    // DATA ATUAL ------------------------------------
    /**
     * Método que retorna a data atual
     * 
     * @return Objeto da classe Data
     */
    public Data getDataAtual() {
        return dataAtual;
    }

    /**
     * Método que permite mudar a data atual da aplicação
     */
    public void mudaDataAtual() {
        int dia, mes, ano;
        do {
            System.out.print("Dia: ");
            dia = scanInt();
            System.out.print("Mês: ");
            mes = scanInt();
            System.out.print("Ano: ");
            ano = scanInt();
        } while (dia < 1 || dia >= 32 || mes < 1 || mes >= 13);
        dataAtual.setDia(dia);
        dataAtual.setMes(mes);
        dataAtual.setAno(ano);
        gerePromocoes();
    }

    // CLIENTES ------------------------------------
    /**
     * Método que retorna o cliente ativo naquele momento
     *
     * @return Objeto da classe Cliente
     */
    public Cliente getClienteAtivo() {
        return clienteAtivo;
    }

    /**
     * Método que permite ativar um cliente
     * 
     * @param c Cliente a ativar
     */
    public void setClienteAtivo(Cliente c) {
        clienteAtivo = c;
    }

    /**
     * Método que lê e trata os dados do ficheiro de texto relativo aos clientes da
     * primeira vez que o programa funciona, no caso de já existir um ficheiro de
     * objetos relativo a clientes, dá vez à leitura de esse ficheiro
     */
    private void parseClientes() {
        File obj = new File("clientes.obj");
        // verifica a existencia de ficheiro de objetos
        if (obj.exists() && obj.isFile()) {
            try {
                FileInputStream fis = new FileInputStream(obj);
                ObjectInputStream ois = new ObjectInputStream(fis);
                try {
                    clientes = (ArrayList<Cliente>) ois.readObject();
                } catch (EOFException e) {
                    ois.close();
                }
            } catch (FileNotFoundException ex) {
                System.err.println("Erro a abrir ficheiro.");
            } catch (IOException ex) {
                System.err.println("Erro a ler ficheiro.");

            } catch (ClassNotFoundException ex) {
                System.err.println("Erro a converter objeto.");
            }
        } else {// nao existe ficheiro de objetos
            File f = new File("clientes.txt");
            if (f.exists() && f.isFile()) {
                try {
                    FileReader fr = new FileReader(f);
                    BufferedReader br = new BufferedReader(fr);
                    String line;
                    while ((line = br.readLine()) != null) {
                        String[] detalhesCliente = line.split(",");
                        String[] morada = detalhesCliente[2].split("/");
                        Morada m = new Morada(morada[0], Integer.parseInt(morada[1]), Integer.parseInt(morada[2]));
                        String[] data = detalhesCliente[5].split("/");
                        Data d = new Data(Integer.parseInt(data[0]), Integer.parseInt(data[1]),
                                Integer.parseInt(data[2]));
                        if (detalhesCliente[0].equals("f")) {
                            Cliente cFrequente = new ClienteFrequente(detalhesCliente[1], m, detalhesCliente[3],
                                    Integer.parseInt(detalhesCliente[4]), d);
                            clientes.add(cFrequente);
                        } else {
                            Cliente cRegular = new Cliente(detalhesCliente[1], m, detalhesCliente[3],
                                    Integer.parseInt(detalhesCliente[4]), d);
                            clientes.add(cRegular);
                        }

                    }
                    br.close();
                } catch (FileNotFoundException ex) {
                    System.err.println("Erro a abrir ficheiro de texto.");
                } catch (IOException ex) {
                    System.err.println("Erro a ler ficheiro de texto.");
                }

            }
            // cria ficheiro de objetos
            createObjClientes();
        }
    }

    /**
     * Método que guarda dados relativos a clientes no respetivo ficheiro de
     * objetos. Cria o ficheiro caso não exista
     */
    private void createObjClientes() {
        File f = new File("clientes.obj");
        try {
            FileOutputStream fos = new FileOutputStream(f, true);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(clientes);
            oos.close();
        } catch (FileNotFoundException ex) {
            System.err.println("Erro a criar ficheiro.");
        } catch (IOException ex) {
            System.err.println("Erro a escrever para o ficheiro.");
        }
    }

    // PRODUTOS ------------------------------------

    /**
     * Método que lê e trata os dados do ficheiro de texto relativo aos produtos da
     * primeira vez que o programa funciona, no caso de já existir um ficheiro de
     * objetos relativo a produtos, dá vez à leitura de esse ficheiro
     */
    private void parseProdutos() {
        File obj = new File("produtos.obj");

        // verifica a existencia de ficheiro de objetos
        if (obj.exists() && obj.isFile()) {
            try {
                // le mobiliario
                FileInputStream fis = new FileInputStream(obj);
                ObjectInputStream ois = new ObjectInputStream(fis);
                try {
                    produtos = (ArrayList<Produto>) ois.readObject();
                } catch (EOFException e) {
                    ois.close();
                }

            } catch (FileNotFoundException ex) {
                System.err.println("Erro a abrir ficheiro.");
            } catch (IOException ex) {
                System.err.println("Erro a ler ficheiro.");
            } catch (ClassNotFoundException ex) {
                System.err.println("Erro a converter objeto.");
            }
        } else {
            File f = new File("produtos.txt");
            if (f.exists() && f.isFile()) {
                try {
                    FileReader fr = new FileReader(f);
                    BufferedReader br = new BufferedReader(fr);
                    String line;
                    while ((line = br.readLine()) != null) {
                        String[] detalhesProduto = line.split(",");

                        if (detalhesProduto[0].equals("mobiliario")) {
                            double peso = Double.parseDouble(detalhesProduto[5]);
                            String[] dimensoes = detalhesProduto[6].split("/");
                            Dimensao d = new Dimensao(Double.parseDouble(dimensoes[0]),
                                    Double.parseDouble(dimensoes[1]), Double.parseDouble(dimensoes[2]));
                            // id,nome,preco,stock,peso,dimensao
                            Mobiliario p = new Mobiliario(Integer.parseInt(detalhesProduto[1]), detalhesProduto[2],
                                    Double.parseDouble(detalhesProduto[3]), Integer.parseInt(detalhesProduto[4]), peso,
                                    d);
                            produtos.add(p);

                        } else if (detalhesProduto[0].equals("limpeza")) {
                            int grauToxicidade = Integer.parseInt(detalhesProduto[5]);
                            // id,nome,preco,stock,grau de toxicidade
                            Limpeza p = new Limpeza(Integer.parseInt(detalhesProduto[1]), detalhesProduto[2],
                                    Double.parseDouble(detalhesProduto[3]), Integer.parseInt(detalhesProduto[4]),
                                    grauToxicidade);
                            produtos.add(p);

                        } else if (detalhesProduto[0].equals("alimentar")) {
                            double kcal = Double.parseDouble(detalhesProduto[5]);
                            double percentagemGordura = Double.parseDouble(detalhesProduto[6]);
                            // id,nome,preco,stock,kcal,percent de gordura
                            Alimentar p = new Alimentar(Integer.parseInt(detalhesProduto[1]), detalhesProduto[2],
                                    Double.parseDouble(detalhesProduto[3]), Integer.parseInt(detalhesProduto[4]), kcal,
                                    percentagemGordura);
                            produtos.add(p);

                        } else {
                            System.out.println("Erro: Produto inválido.");
                        }

                    }
                    br.close();
                } catch (FileNotFoundException ex) {
                    System.err.println("Erro a abrir ficheiro de texto.");
                } catch (IOException ex) {
                    System.err.println("Erro a ler ficheiro de texto.");
                }
            }
            createObjProdutos();
        }

    }

    /**
     * Método que guarda dados relativos a produtos no respetivo ficheiro de
     * objetos. Cria o ficheiro caso não exista
     */
    private void createObjProdutos() {
        File f = new File("produtos.obj");
        try {
            FileOutputStream fos = new FileOutputStream(f, true);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(produtos);
            oos.close();
        } catch (FileNotFoundException ex) {
            System.err.println("Erro a criar ficheiro.");
        } catch (IOException ex) {
            System.err.println("Erro a escrever para o ficheiro.");
        }

    }

    /**
     * Método que imprime os produtos disponíveis
     */
    public void listaProdutos() {
        System.out.format("%-3s %-20s %-5s %-5s\n\n", "ID", "Produto", "Preço", "Stock");
        for (Produto p : produtos) {
            System.out.format("%-3d %-20s %4.2f %-5d\n\n", p.getId(), p.getNome(), p.getPrecoUnitario(), p.getStock());
        }
        System.out.println("\n");
    }

    // PROMOCOES ------------------------------------

    /**
     * Método que lê e trata os dados do ficheiro de texto relativo às promoções da
     * primeira vez que o programa funciona, no caso de já existir um ficheiro de
     * objetos relativo a promoções, dá vez à leitura de esse ficheiro
     */
    private void parsePromocoes() {
        File obj = new File("promocoes.obj");
        // verifica a existencia de ficheiro de objetos
        if (obj.exists() && obj.isFile()) {
            try {
                // le mobiliario
                FileInputStream fis = new FileInputStream(obj);
                ObjectInputStream ois = new ObjectInputStream(fis);
                try {
                    promocoes = (ArrayList<Promocao>) ois.readObject();
                } catch (EOFException e) {
                    ois.close();
                }
            } catch (FileNotFoundException ex) {
                System.err.println("Erro a abrir ficheiro.");
            } catch (IOException ex) {
                System.err.println("Erro a ler ficheiro.");
            } catch (ClassNotFoundException ex) {
                System.err.println("Erro a converter objeto.");
            }
        } else {
            File f = new File("promocoes.txt");
            if (f.exists() && f.isFile()) {
                try {
                    FileReader fr = new FileReader(f);
                    BufferedReader br = new BufferedReader(fr);
                    String line;
                    while ((line = br.readLine()) != null) {
                        Produto produtoAssociado = null;
                        String[] detalhesPromocao = line.split(",");
                        int idProduto = Integer.parseInt(detalhesPromocao[1]);
                        for (Produto p : produtos) {
                            if (p.getId() == idProduto) {
                                produtoAssociado = p;
                                break;
                            }
                        }
                        String[] detalhesData = detalhesPromocao[2].split("/");
                        Data dataInicio = new Data(Integer.parseInt(detalhesData[0]), Integer.parseInt(detalhesData[1]),
                                Integer.parseInt(detalhesData[2]));
                        detalhesData = detalhesPromocao[3].split("/");
                        Data dataFim = new Data(Integer.parseInt(detalhesData[0]), Integer.parseInt(detalhesData[1]),
                                Integer.parseInt(detalhesData[2]));
                        Data[] periodoPromocao = { dataInicio, dataFim };
                        // leve-3-pague-4
                        if (detalhesPromocao[0].equals("l3p4")) {
                            Promocao p1 = new Pague3Leve4(produtoAssociado, periodoPromocao);
                            promocoes.add(p1);
                        } else if (detalhesPromocao[0].equals("pm")) {
                            Promocao p2 = new PagueMenos(produtoAssociado, periodoPromocao);
                            promocoes.add(p2);
                        }

                    }
                    br.close();
                } catch (FileNotFoundException ex) {
                    System.err.println("Erro a abrir ficheiro de texto.");
                } catch (IOException ex) {
                    System.err.println("Erro a ler ficheiro de texto.");
                }
            }
            createObjPromocoes();
        }

    }

    /**
     * Método que guarda dados relativos a promoções no respetivo ficheiro de
     * objetos. Cria o ficheiro caso não exista
     */
    private void createObjPromocoes() {
        File f = new File("promocoes.obj");
        try {
            FileOutputStream fos = new FileOutputStream(f, true);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(promocoes);
            oos.close();
        } catch (FileNotFoundException ex) {
            System.err.println("Erro a criar ficheiro.");
        } catch (IOException ex) {
            System.err.println("Erro a escrever para o ficheiro.");
        }
    }

    /**
     * Método que imprime as promoções disponíveis
     */
    public void listaPromocoes() {
        System.out.format("%-3s %-20s %-10s %-10s\n\n", "ID", "Produto", "DataInicio", "DataFim");
        for (Promocao p : promocoes) {
            System.out.format("%-3s %-20s %-10s %-10s\n\n", p.getProdutoAssociado().getId(),
                    p.getProdutoAssociado().getNome(), p.getPeriodoPromocao()[0], p.getPeriodoPromocao()[1]);
        }
        System.out.println("\n");
    }

    // COMPRA ------------------------------------

    /**
     * Método que lê e trata os dados do ficheiro de objetos relativo às compras
     * efetuadas, caso este exista
     */
    private void parseCompras() {
        File obj = new File("compras.obj");
        if (obj.exists() && obj.isFile()) {
            try {
                FileInputStream fis = new FileInputStream(obj);
                ObjectInputStream ois = new ObjectInputStream(fis);
                try {
                    compras = (ArrayList<Compra>) ois.readObject();
                } catch (EOFException e) {
                    ois.close();
                }

            } catch (FileNotFoundException ex) {
                System.err.println("Erro a abrir ficheiro.");
            } catch (IOException ex) {
                System.err.println("Erro a ler ficheiro.");

            } catch (ClassNotFoundException ex) {
                System.err.println("Erro a converter objeto.");
            }
        }
    }

    /**
     * Método que permite a um cliente realizar uma compra
     * 
     * @param cliente Cliente a realizar a compra
     */
    public void realizarCompra(Cliente cliente) {
        ArrayList<ItemCompra> produtosCompra = new ArrayList<>();
        Compra c = new Compra(cliente, produtosCompra,
                new Data(dataAtual.getDia(), dataAtual.getMes(), dataAtual.getAno()));
        while (true) {
            System.out.println(
                    "1) Adicionar produto\n2) Remover produto\n3) Carrinho de compras\n4) Produtos e Promoções\n5) Checkout\n6) Menu\n\nCusto atual-> "
                            + c.custoAtual() + "euros");
            System.out.print("Opção-> ");
            int option = scanInt();
            switch (option) {

                case 1:
                    // adicionar produto
                    Produto produtoAtivo = null;
                    do {
                        System.out.print("Indique ID do produto a adicionar: ");
                        int idProduto = scanInt();
                        for (Produto p : produtos) {
                            if (p.id == idProduto) {
                                produtoAtivo = p;
                            }
                        }
                    } while (produtoAtivo == null);
                    // quantidade
                    int quantidade;
                    do {
                        System.out.print("Quantidade a adicionar: ");
                        quantidade = scanInt();
                    } while (quantidade > produtoAtivo.stock);
                    // remove stock
                    produtoAtivo.stock = produtoAtivo.stock - quantidade;
                    // esgotado
                    if (produtoAtivo.stock == 0) {
                        produtos.remove(produtoAtivo);
                    }
                    c.adicionarProduto(produtoAtivo, quantidade);
                    System.out.println("Produto adicionado ao carrinho.");
                    break;

                case 2:
                    // remover produto
                    ItemCompra item = null;

                    do {
                        System.out.print("Indique ID do produto a remover: ");
                        int idProduto = scanInt();
                        for (ItemCompra i : c.getLista()) {
                            if (i.getProduto().getId() == idProduto) {
                                item = i;
                            }
                        }
                    } while (item == null);
                    // quantidade
                    do {
                        System.out.print("Quantidade a remover: ");
                        quantidade = scanInt();
                    } while (quantidade > item.getQuantidade());
                    c.removerProduto(item.getProduto(), quantidade);

                    // adicionar stock

                    System.out.println("Produto removido do carrinho.");
                    break;

                case 3:
                    // mostrar carrinho
                    System.out.println("Carrinho:\n");
                    for (ItemCompra i : c.getLista()) {
                        System.out.format("%-3d %-20s %-5f\n\n", i.getQuantidade(), i.getProduto().getNome(),
                                i.getProduto().getPrecoUnitario());
                    }
                    break;
                case 4:
                    divisoria();
                    System.out.println("Produtos:\n");
                    listaProdutos();
                    System.out.println("Promoções:\n");
                    listaPromocoes();
                    divisoria();
                    break;
                case 5:
                    if (c.getLista().isEmpty()) {
                        System.out.println("Carrinho vazio.");
                        break;
                    }
                    // checkout
                    // realiza nova compra com cliente e data atuais
                    divisoria();
                    System.out.println("Custo       : " + c.custoAtual());
                    System.out.println("Portes      : " + (c.custoFinal() - c.custoAtual()));
                    System.out.println("Custo Final : " + c.custoFinal());
                    System.out.println("Pretende confirmar a compra?\n1) Sim\n2) Não");
                    while (true) {
                        System.out.println("Opção-> ");
                        option = scanInt();
                        if (option == 1) {
                            divisoria();
                            compras.add(c);
                            updateCompraObj();
                            return;
                        } else if (option == 2) {
                            divisoria();
                            break;
                        }
                    }

                case 6:
                    return;

                default:
                    System.out.println("Operação inválida.");

            }
        }
    }

    /**
     * Método que guarda dados relativos a compras no respetivo ficheiro de
     * objetos. Cria o ficheiro caso não exista
     */
    private void updateCompraObj() {
        try {
            new FileOutputStream("compras.obj").close();
        } catch (FileNotFoundException ex) {
            System.err.println("Erro a criar ficheiro.");
        } catch (IOException ex) {
            System.err.println("Erro a escrever para o ficheiro.");
        }
        File f = new File("compras.obj");
        try {
            FileOutputStream fos = new FileOutputStream(f, true);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(compras);
            oos.close();
        } catch (FileNotFoundException ex) {
            System.err.println("Erro a criar ficheiro.");
        } catch (IOException ex) {
            System.err.println("Erro a escrever para o ficheiro.");
        }

    }

    // INICIALIZACAO ------------------------------------
    /**
     * Método que ativa/desativa as promoções tendo em conta a data atual da
     * aplicação
     */
    private void gerePromocoes() {
        for (Produto produto : produtos) {
            for (Promocao promocao : promocoes) {
                if (produto.getId() == promocao.getProdutoAssociado().getId()) {
                    if (promocao.promocaoAtiva(dataAtual)) {
                        produto.setPromocaoAssociada(promocao);
                    } else {
                        produto.setPromocaoAssociada(null);
                    }
                }
            }
        }
    }

    /**
     * Método que inicializa todos os atributos necessários para o funcionamento da
     * aplicação
     */
    public void inicializar() {
        parseClientes();
        parseProdutos();
        parsePromocoes();
        parseCompras();
        gerePromocoes();
    }

    // MAIN ------------------------------------
    public static void main(String[] args) {

        App gestor = new App();

        gestor.inicializar();

        boolean loggedIn = false;
        while (true) {

            Cliente clienteAtivo = null;
            while (loggedIn == false) {
                gestor.divisoria();
                System.out.println(gestor.getDataAtual());
                System.out.println("1) Fazer log-in\n2) Terminar programa\n");
                gestor.divisoria();
                System.out.print("Opção-> ");
                int option = gestor.scanInt();

                switch (option) {

                    case 1:
                        gestor.divisoria();
                        System.out.print("Email-> ");

                        String email = gestor.scanString();
                        for (Cliente c : gestor.clientes) {
                            if (email.equals(c.getEmail())) {
                                clienteAtivo = c;
                                loggedIn = true;
                                break;
                            }
                        }
                        if (loggedIn == true) {
                            System.out.println("Log-in efetuado.\n");
                            gestor.divisoria();
                            break;
                        }
                        System.out.println("Email inválido.");
                        break;

                    case 2:
                        gestor.scanner.close();
                        System.exit(1);

                    default:
                        System.out.println("Operação inválida.");

                }

            }

            while (loggedIn == true) {
                gestor.divisoria();
                System.out.println("Bem-vindo, " + clienteAtivo.getNome() + ".");
                // easter egg
                if (clienteAtivo.getDataNascimento().getDia() == gestor.getDataAtual().getDia()
                        && clienteAtivo.getDataNascimento().getMes() == gestor.getDataAtual().getMes()
                        && clienteAtivo.getDataNascimento().getAno() < gestor.getDataAtual().getAno()) {
                    System.out.println("FELIZ ANIVERSÁRIO! "
                            + (gestor.getDataAtual().getAno() - clienteAtivo.getDataNascimento().getAno()) + "!\n");
                }
                gestor.divisoria();
                System.out.println(
                        "1) Realizar compra\n2) Compras realizadas\n3) Mudar data atual\n4) Log-out\n5) Terminar programa\n");
                gestor.divisoria();
                System.out.print("Opção-> ");
                int option = gestor.scanInt();

                switch (option) {

                    case 1:// Realizar compra
                        gestor.realizarCompra(clienteAtivo);
                        break;

                    case 2:// Compras realizadas
                        if (gestor.compras.isEmpty()) {
                            System.out.println("Não possui compras efetuadas.");
                            break;
                        }
                        int contador = 0;
                        for (Compra c : gestor.compras) {
                            if (c.getCliente().getNome().equals(clienteAtivo.getNome())) {
                                gestor.divisoria();
                                c.mostraCompra();
                                gestor.divisoria();
                                contador += 1;
                            }
                        }
                        if (contador == 0) {
                            System.out.println("Não possui compras efetuadas.");
                        }
                        break;

                    case 3:// Mudar data atual
                        gestor.mudaDataAtual();
                        System.out.println(gestor.getDataAtual());
                        break;

                    case 4:// Log-out
                        System.out.println("Log-out efetuado.\n");
                        gestor.divisoria();
                        loggedIn = false;
                        break;

                    case 5:// Terminar
                        gestor.scanner.close();
                        System.exit(1);

                    default:
                        System.out.println("Operação inválida.");

                }
            }

        }
    }
}