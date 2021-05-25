package tiny;

import c_ast_descendente_manual.AnalizadorLexicoTiny;
import c_ast_descendente_manual.ConstructorAST;
import asint.TinyASint.Prog;
import c_ast_descendente_manual.ClaseLexica;
import c_ast_descendente_manual.GestionErrores;
import c_ast_descendente_manual.UnidadLexica;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import procesamientos.Impresion;

public class Main {
	public static void main(String[] args) throws Exception {
     Prog prog = null;
     prog = ejecuta_descendente_manual(args[0]);
     prog.procesa(new Impresion());         
    }

   private static Prog ejecuta_descendente_manual(String in) throws Exception {
     Reader input = new InputStreamReader(new FileInputStream(in));
     ConstructorAST constructorast = new ConstructorAST(input);
     return constructorast.Sp();
   }
}
