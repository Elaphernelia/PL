package alex;

public class UnidadLexicaUnivaluada extends UnidadLexica {
	public UnidadLexicaUnivaluada(int fila, int columna, int clase, String lex) {
		super(fila, columna, clase, lex);
	}

	@Override
	public String toString() {
		return "[clase:" + clase() + ",fila:" + fila() + ",col:" + columna() + ",clase:" + lexema() + "]";
	}
}
