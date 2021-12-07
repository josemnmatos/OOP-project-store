public class ClienteFrequente extends Cliente {

      /**
       * Construtor da subclasse de Cliente, ClienteFrequente
       * 
       * @param nome Nome do cliente
       * @param morada Morada do cliente
       * @param email Email do cliente
       * @param telefone Telefone do cliente
       * @param dataNascimento Data de nascimento do cliente
       */
      public ClienteFrequente(String nome, Morada morada, String email, int telefone, Data dataNascimento) {
            super(nome, morada, email, telefone, dataNascimento);
      }

      /**
       * Método que retorna o custo dos portes da compra efetuada por um cliente frequente
       * 
       * @param custoAtual Custo atual da compra associada ao cliente
       * @return Custo dos portes (0 se a compra tiver um valor maior que 40, e 15 se for menor)
       */
      public double  custoPortes(double custoAtual){
            if(custoAtual>40){
                  return 0;
            }else{
                  return 15;
            }
      }

}
