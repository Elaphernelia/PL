package tiny1;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.Reader;

import alex.AnalizadorLexicoTinyUno;
import asint.TinyASint.Prog;
import ast.asc.ClaseLexica;
import maquinaP.MaquinaP;
import procesamientos.*;
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
	
	public static void astPrintAsc(String fname) throws Exception {
		AnalizadorLexicoTinyUno alex = new AnalizadorLexicoTinyUno(
				new InputStreamReader(new FileInputStream(fname)));
		ast.asc.ConstructorASTTinyUno asint = new ast.asc.ConstructorASTTinyUno(alex);
		
		Prog prog = (Prog) asint.parse().value;
		prog.procesa(new Impresion());
	}
	
	public static void astPrintDesc(String fname) throws Exception {
		Reader input = new InputStreamReader(new FileInputStream(fname));
		ast.desc.AnalizadorSintacticoTinyUno asint = new ast.desc.AnalizadorSintacticoTinyUno(input);
		
		Prog prog = asint.Sp();
		prog.procesa(new Impresion());
	}
	
	private static boolean compilaCommon(Prog prog) {
		// Si el procesador ha construido satisfactoriamente el AST, aplicará
		// sobre el mismo el procesamiento de vinculación.
		Vinculacion v = new Vinculacion();
		prog.procesa(v);
		if (v.isDirty()) {
			System.err.println("Hubo errores en la vinculación. Parando compilación.");
			return false;
		} else {
			System.out.println("Vinculación terminada sin errores");
		}
		
		ComprobacionTipos t = new ComprobacionTipos();
		prog.procesa(t);
		if (t.isDirty()) {
			System.err.println("Hubo errores en la comprobación de tipos. Parando compilación");
			return false;
		} else {
			System.out.println("No se han detectado errores de tipos");
		}
		
		AsignaEspacio a = new AsignaEspacio();
		prog.procesa(a);
		System.out.println("Procesado asignación de espacio");
		
		Etiquetado e = new Etiquetado();
		prog.procesa(e);
		System.out.println("Procesado etiquetado");
		
		// TODO: Obtener tamaños de la asignacion de espacio
		MaquinaP p = new MaquinaP(0,0,0,0);
		
		GeneraCodigo c = new GeneraCodigo(p);
		prog.procesa(c);
		System.out.println("Generado código de máquina-p:");
		p.muestraCodigo();
		
		System.out.println("-------------------------");
		System.out.println("Ejecutando en máquina-p");
		p.ejecuta();
		System.out.println("Estado final:");
		p.muestraEstado();
		
		return true;
	}
	
	public static void compilaAsc(String fname) throws Exception {
		AnalizadorLexicoTinyUno alex = new AnalizadorLexicoTinyUno(
				new InputStreamReader(new FileInputStream(fname)));
		ast.asc.ConstructorASTTinyUno asint = new ast.asc.ConstructorASTTinyUno(alex);
		
		compilaCommon((Prog) asint.parse().value);
	}
	
	public static void compilaDesc(String fname) throws Exception {
		Reader input = new InputStreamReader(new FileInputStream(fname));
		ast.desc.AnalizadorSintacticoTinyUno asint = new ast.desc.AnalizadorSintacticoTinyUno(input);
		
		compilaCommon(asint.Sp());
			
	}

	public static void main(String[] args) throws Exception {
		if (args.length != 2) {
			System.err.println("Usage: ./asint filename <desc/asc>");
			System.exit(1);
		}

		if (args[1].equalsIgnoreCase("desc")) {
			compilaAsc(args[0]);
		} else if (args[1].equalsIgnoreCase("asc")) {
			compilaDesc(args[0]);
		} else if (args[1].equalsIgnoreCase("printasc")) {
			astPrintAsc(args[0]);
		} else if (args[1].equalsIgnoreCase("printdesc")) {
			astPrintDesc(args[0]);
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