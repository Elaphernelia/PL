package asint;
import java.io.FileReader;
public class Main{
   public static void main(String[] args) throws Exception {
      AnalizadorSintacticoTinyUno asint = new AnalizadorSintacticoTinyUno(new FileReader(args[0]));
	  asint.Sp();
   }
}