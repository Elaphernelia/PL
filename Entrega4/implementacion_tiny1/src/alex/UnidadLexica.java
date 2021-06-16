package alex;

import java_cup.runtime.Symbol;

public abstract class UnidadLexica extends Symbol {
	private int fila;
	private int columna;

	public UnidadLexica(int fila, int columna, int clase, String lexema) {
		super(clase, new StringLocalizado(lexema, fila, columna));
		this.fila = fila;
		this.columna = columna;
	}

	public int clase() {
		return sym;
	}

	public StringLocalizado lexema() {
		return (StringLocalizado) value;
	}

	public int fila() {
		return fila;
	}

	public int columna() {
		return columna;
	}
}
