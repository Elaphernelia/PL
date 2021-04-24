package alex;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

public class Main {
	public static void main(String[] args) throws IOException, FileNotFoundException {
		if (args.length != 1) {
			System.err.println("Usage: altiny [filename]");
			System.exit(1);
		}
		
		Reader input = new InputStreamReader(new FileInputStream(args[0]));
		AnalizadorLexicoTiny al = new AnalizadorLexicoTiny(input);
		
		UnidadLexica unidad;
		do {
			unidad = al.sigToken();
			System.out.println(unidad);
		} while(unidad.clase() != ClaseLexica.EOF);
		
		System.exit(0);
	}
}
