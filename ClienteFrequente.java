public class ClienteFrequente extends Cliente {

      public ClienteFrequente(String nome, Morada morada, String email, int telefone, Data dataNascimento) {
            super(nome, morada, email, telefone, dataNascimento);
      }

      public double  custoPortes(double custoAtual){
            if(custoAtual>40){
                  return 0;
            }else{
                  return 15;
            }
      }

}
