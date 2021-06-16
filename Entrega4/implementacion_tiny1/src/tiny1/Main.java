package tiny1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import alex.AnalizadorLexicoTinyUno;
import asint.TinyASint;
import asint.TinyASint.Prog;
import ast.asc.ClaseLexica;
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
		ast.asc.ConstructorASTTinyUno asint = new ast.asc.ConstructorASTTinyUno(alex);
		// asint.parse();
		asint.debug_parse();
		System.err.println("OK");
	}
	
	public static void alexAsintDesc(String fname) throws Exception {
		ast.desc.AnalizadorSintacticoTinyUno asint = new ast.desc.AnalizadorSintacticoTinyUno(
				new FileReader(fname));
		asint.Sp();
		System.out.println("OK");
	}
	
	public static void astAsc(String fname) throws Exception {
		AnalizadorLexicoTinyUno alex = new AnalizadorLexicoTinyUno(
				new InputStreamReader(new FileInputStream(fname)));
		ast.asc.ConstructorASTTinyUno asint = new ast.asc.ConstructorASTTinyUno(alex);
		
		Prog prog = (Prog) asint.parse().value;
		prog.procesa(new Impresion());
	}
	
	public static void astDesc(String fname) throws Exception {
		Reader input = new InputStreamReader(new FileInputStream(fname));
		ast.desc.AnalizadorSintacticoTinyUno asint = new ast.desc.AnalizadorSintacticoTinyUno(input);
		
		Prog prog = asint.Sp();
		prog.procesa(new Impresion());
	}

	public static void main(String[] args) throws Exception {
		if (args.length != 2) {
			System.err.println("Usage: ./asint filename <desc/asc>");
			System.exit(1);
		}

		if (args[1].equalsIgnoreCase("desc")) {
			astDesc(args[0]);
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