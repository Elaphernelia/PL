package alex;

import ast.desc.Token;

public class StringLocalizado {
	private String _s;
	private int _fila;
	private int _col;
	
	public StringLocalizado(String s, int fila, int col) {
		_s = s;
		_fila = fila;
		_col = col;
	}
	
	public StringLocalizado(Token id) {
		this(id.toString(), id.beginLine, id.beginColumn);
	}

	public int fila() {
		return _fila;
	}
	
	public int col() {
		return _col;
	}
	
	@Override
	public String toString() {
		return _s;
	}
	
	@Override
	public boolean equals(Object o) {
		return (o == this) || (
			   (o instanceof StringLocalizado) &&
			   (((StringLocalizado) o)._s.equals(_s)));
	}
	
	@Override
	public int hashCode() {
		return _s.hashCode();
	}
}