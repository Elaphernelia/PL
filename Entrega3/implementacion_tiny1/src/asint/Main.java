package asint;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import alex.AnalizadorLexicoTinyUno;
import asint.TinyASint.Prog;
import asint.asc.ClaseLexica;
import procesamientos.Impresion;
import alex.UnidadLexica;

public class Main {
	public static void alexMain(String fname) throws Exception {
		Reader input = new InputStreamReader(new FileInputStream(fname));
		AnalizadorLexicoTinyUno al = new AnalizadorLexicoTinyUno(input);
		UnidadLexica unidad;
		do {
			unidad = al.next_token();
			System.out.println(unidad);
		} while (unidad.clase() != ClaseLexica.EOF);
	}
	
	public static void alexAsintAsc(String fname) throws Exception {
		AnalizadorLexicoTinyUno alex = new AnalizadorLexicoTinyUno(
				new InputStreamReader(new FileInputStream(fname)));
		asint.asc.AnalizadorSintacticoTinyUno asint = new asint.asc.AnalizadorSintacticoTinyUno(alex);
		// asint.parse();
		asint.debug_parse();
		System.err.println("OK");
	}
	
	public static void alexAsintDesc(String fname) throws Exception {
		asint.desc.AnalizadorSintacticoTinyUno asint = new asint.desc.AnalizadorSintacticoTinyUno(
				new FileReader(fname));
		asint.Sp();
		System.out.println("OK");
	}
	
	public static void astAsc(String fname) throws Exception {
		AnalizadorLexicoTinyUno alex = new AnalizadorLexicoTinyUno(
				new InputStreamReader(new FileInputStream(fname)));
		asint.asc.AnalizadorSintacticoTinyUno asint = new asint.asc.AnalizadorSintacticoTinyUno(alex);
		
		Prog prog = (Prog) asint.parse().value;
		prog.procesa(new Impresion());
	}

	public static void main(String[] args) throws Exception {
		if (args.length != 2) {
			System.err.println("Usage: ./asint filename <desc/asc>");
			System.exit(1);
		}

		if (args[1].equalsIgnoreCase("desc")) {
			throw new UnsupportedOperationException("AST desc not supported yet");
		} else if (args[1].equalsIgnoreCase("asc")) {
			astAsc(args[0]);
		} else if (args[1].equalsIgnoreCase("asintdesc")) {
			alexAsintDesc(args[0]);
		} else if (args[1].equalsIgnoreCase("asintasc")) {
			alexAsintAsc(args[0]);
		} else if (args[1].equalsIgnoreCase("alex")) {
			alexMain(args[0]);
		} else {
			System.err.println("Invalid op. Op should be desc/asc");
			System.exit(1);
		}
	}
}