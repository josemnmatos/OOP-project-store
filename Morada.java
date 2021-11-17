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
}
