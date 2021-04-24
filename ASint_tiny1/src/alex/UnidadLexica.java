package alex;

import java_cup.runtime.Symbol;

public abstract class UnidadLexica extends Symbol {
	private ClaseLexica clase;
	private int fila;
	private int columna;
	private String lexema;
	
	public UnidadLexica(int fila, int columna, ClaseLexica clase, String lexema) {
		super(clase.ordinal(), lexema);
		this.fila = fila;
		this.columna = columna;
		this.clase = clase;
		this.lexema = lexema;
	}

	public UnidadLexica(int fila, int columna, ClaseLexica clase) {
		this(fila, columna, clase, clase.toString());
	}

	public ClaseLexica clase() {
		return clase;
	}

	public String lexema() {
		return lexema;
	}

	public int fila() {
		return fila;
	}

	public int columna() {
		return columna;
	}
}
