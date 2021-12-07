import java.io.Serializable;

public class Cliente implements Serializable {
      /**
       * Nome do cliente
       */
      private String nome;
      /**
       * Morada do cliente
       */
      private Morada morada;
      /**
       * Email do cliente
       */
      private String email;
      /**
       * Telefone do cliente
       */
      private int telefone;
      /**
       * Data de nascimento do cliente
       */
      private Data dataNascimento;

      /**
       * Construtor da superclasse Cliente
       * 
       * @param nome Nome do cliente
       * @param morada Morada do cliente
       * @param email Email do cliente
       * @param telefone Telefone do cliente
       * @param dataNascimento Data de nascimento do cliente
       */
      public Cliente(String nome, Morada morada, String email, int telefone, Data dataNascimento) {
            this.nome = nome;
            this.morada = morada;
            this.email = email;
            if (telefone < 100000000 || telefone > 999999999) {
                  System.out.println("Telefone inválido");
                  System.exit(1);
            }
            this.telefone = telefone;
            this.dataNascimento = dataNascimento;
      }

      @Override
      public String toString() {
            return "\nNome: " + this.nome
                        + "\nMorada: " + this.morada.toString()
                        + "\nEmail: " + this.email
                        + "\nTelefone: " + this.telefone
                        + "\nData de Nascimento: " + this.dataNascimento.toString();
      }

      public String getNome() {
            return this.nome;
      }

      public void setNome(String nome) {
            this.nome = nome;
      }

      public Morada getMorada() {
            return this.morada;
      }

      public void setMorada(Morada morada) {
            this.morada = morada;
      }

      public String getEmail() {
            return this.email;
      }

      public void setEmail(String email) {
            this.email = email;
      }

      public int getTelefone() {
            return this.telefone;
      }

      public void setTelefone(int telefone) {
            this.telefone = telefone;
      }

      public Data getDataNascimento() {
            return this.dataNascimento;
      }

      public void setDataNascimento(Data dataNascimento) {
            this.dataNascimento = dataNascimento;
      }

      /**
       * Método que retorna o custo dos portes
       * 
       * @param custoAtual custo atual da compra asssociada ao cliente
       * @return Custo dos portes 20
       */
      public double custoPortes(double custoAtual) {
            return 20;
      }

}
