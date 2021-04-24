package alex;

public class UnidadLexicaMultivaluada extends UnidadLexica {
	public UnidadLexicaMultivaluada(int fila, int columna, ClaseLexica clase, String lexema) {
		super(fila, columna, clase, lexema);
	}

	public String toString() {
		return "[clase:" + clase() + ",fila:" + fila() + ",col:" + columna() + ",lexema:" + lexema() + "]";
	}
}
