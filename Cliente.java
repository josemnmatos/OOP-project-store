public abstract class Cliente {
      protected String nome;
      protected Morada morada;
      protected String email;
      protected int telefone;
      protected Data dataNascimento;

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

}
