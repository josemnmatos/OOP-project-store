public class Cliente {
      private String nome;
      private Morada morada;
      private String email;
      private int telefone;
      private Data dataNascimento;
      private boolean frequente;

      public Cliente(){
            
      }


      public Cliente(String nome, Morada morada, String email, int telefone, Data dataNascimento, boolean frequente) {
            this.nome = nome;
            this.morada = morada;
            this.email = email;
            if (telefone < 100000000 || telefone > 999999999) {
                  System.out.println("Telefone inválido");
                  System.exit(1);
            }
            this.telefone = telefone;
            this.dataNascimento = dataNascimento;
            this.frequente = frequente;
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

      public void setFrequente(boolean frequente) {
            this.frequente = frequente;
      }

      public boolean isFrequente() {
            return this.frequente;
      }

}
