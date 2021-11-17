public class Cliente {
      protected String nome;
      protected String morada;
      protected String email;
      protected int telefone;
      protected Data dataNascimento;

      public Cliente(String nome, String morada, String email, int telefone, Data dataNascimento) {
            this.nome = nome;
            this.morada = morada;
            this.email = email;
            this.telefone = telefone;
            this.dataNascimento = dataNascimento;
      }

}
