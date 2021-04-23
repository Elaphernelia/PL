package asint;

import java.io.FileReader;

import asint.desc.AnalizadorSintacticoTinyUno;

public class Main{
   public static void main(String[] args) throws Exception {
	   if (args.length != 2) {
		   System.err.println("Usage: ./asint filename <desc/asc>");
		   System.exit(1);
	   }
	   
	   if (args[1].equalsIgnoreCase("desc")) {
		   AnalizadorSintacticoTinyUno asint = new AnalizadorSintacticoTinyUno(new FileReader(args[0]));
		   asint.Sp();
	   } else if (args[1].equalsIgnoreCase("asc")) {
		   throw new UnsupportedOperationException("Not supported yet");
	   } else {
		   System.err.println("Invalid op. Op should be desc/asc");
		   System.exit(1);
	   }
   }
}