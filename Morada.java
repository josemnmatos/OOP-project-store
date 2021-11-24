public class Morada {
      private String rua;
      private int numero;
      private int codigoPostal;

      public Morada(String rua, int numero, int codigoPostal) {
            this.rua = rua;
            if (numero < 0 || codigoPostal < 0 || codigoPostal > 9999999) {
                  System.out.println("Morada inválida");
                  System.exit(1);
            } else {
                  this.numero = numero;
                  this.codigoPostal = codigoPostal;
            }
      }

      public String getRua() {
            return this.rua;
      }

      public void setRua(String rua) {
            this.rua = rua;
      }

      public int getNumero() {
            return this.numero;
      }

      public void setNumero(int numero) {
            this.numero = numero;
      }

      public int getCodigoPostal() {
            return this.codigoPostal;
      }

      public void setCodigoPostal(int codigoPostal) {
            this.codigoPostal = codigoPostal;
      }

      @Override
      public String toString() {
            return "{" + " rua='" + getRua() + "'" + ", numero='" + getNumero() + "'" + ", codigoPostal='"
                        + getCodigoPostal() + "'" + "}";
      }

}
