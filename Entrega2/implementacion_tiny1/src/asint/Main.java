package asint;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.Reader;

import alex.AnalizadorLexicoTinyUno;
import asint.asc.ClaseLexica;
import alex.UnidadLexica;

public class Main{
   public static void main(String[] args) throws Exception {
	   if (args.length != 2) {
		   System.err.println("Usage: ./asint filename <desc/asc>");
		   System.exit(1);
	   }
	   
	   if (args[1].equalsIgnoreCase("desc")) {
		   asint.desc.AnalizadorSintacticoTinyUno asint = new asint.desc.AnalizadorSintacticoTinyUno(new FileReader(args[0]));
		   asint.Sp();
		   System.out.println("OK");
	   } else if (args[1].equalsIgnoreCase("asc")) {
		   AnalizadorLexicoTinyUno alex = new AnalizadorLexicoTinyUno(new InputStreamReader(new FileInputStream(args[0])));
		   asint.asc.AnalizadorSintacticoTinyUno asint = new asint.asc.AnalizadorSintacticoTinyUno(alex);
		   // asint.parse();
		   asint.debug_parse();
		   System.err.println("OK");
	   } else if (args[1].equalsIgnoreCase("alex")) {
		   Reader input = new InputStreamReader(new FileInputStream(args[0]));
		   AnalizadorLexicoTinyUno al = new AnalizadorLexicoTinyUno(input);
		   UnidadLexica unidad;
		   do {
			   unidad = al.next_token();
		       System.out.println(unidad);
		   }
		   while (unidad.clase() != ClaseLexica.EOF);        
	   } else {
		   System.err.println("Invalid op. Op should be desc/asc");
		   System.exit(1);
	   }
   }}