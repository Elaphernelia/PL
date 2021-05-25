package c_ast_descendente_manual;

import java.io.FileInputStream;
import java.io.Reader;
import java.io.InputStreamReader;
import java.io.IOException;


public class AnalizadorLexicoTiny {
	
	private GestionErrores errores;
	private Reader input;
	private StringBuffer lex;
	private int sigCar;
	private int filaInicio;
	private int columnaInicio;
	private int filaActual;
	private int columnaActual;
	private static String NL = System.getProperty("line.separator");

	private static enum Estado {
		INICIO, REC_POR, REC_DIV, REC_PAP, REC_PCIERR, REC_COMA, REC_IGUALDAD,
		REC_MAS, REC_MENOS, REC_ID, REC_ENT, REC_DECPOS, REC_COM,
		REC_EOF, REC_MENOR, REC_MENIG, REC_MAYOR, REC_MAYIG, REC_IGUAL, REC_DESIGUAL,
		REC_PRESEP, REC_PUNTOCOMA, REC_SEP, REC_PUNTODEC, REC_CERO, REC_E_EXP,
		REC_EXP0, REC_SIGEXP, REC_EXP, REC_PUNTO0, REC_DEC0, REC_EXCL
	}

	private Estado estado;

	public AnalizadorLexicoTiny(Reader input) throws IOException {
		this.input = input;
		lex = new StringBuffer();
		sigCar = input.read();
		filaActual = 1;
		columnaActual = 1;
	}
	
	public void fijaGestionErrores (GestionErrores err) {
		this.errores = err;
	}
	
	public UnidadLexica sigToken() throws IOException {
		estado = Estado.INICIO;
		filaInicio = filaActual;
		columnaInicio = columnaActual;
		lex.delete(0, lex.length());
		while (true) {
			switch (estado) {
			case INICIO:
				if (hayLetra())
					transita(Estado.REC_ID);
				else if (hayDigitoPos())
					transita(Estado.REC_ENT);
				else if (hayCero())
					transita(Estado.REC_CERO);
				else if (haySuma())
					transita(Estado.REC_MAS);
				else if (hayResta())
					transita(Estado.REC_MENOS);
				else if (hayMul())
					transita(Estado.REC_POR);
				else if (hayDiv())
					transita(Estado.REC_DIV);
				else if (hayPAp())
					transita(Estado.REC_PAP);
				else if (hayPCierre())
					transita(Estado.REC_PCIERR);
				else if (hayIgual())
					transita(Estado.REC_IGUALDAD);
				else if (hayComa())
					transita(Estado.REC_COMA);
				else if (hayPuntoComa())
					transita(Estado.REC_PUNTOCOMA);
				else if (hayAlmohadilla())
					transitaIgnorando(Estado.REC_COM);
				else if (haySep())
					transitaIgnorando(Estado.INICIO);
				else if (hayEOF())
					transita(Estado.REC_EOF);
				else if (hayMenor())
					transita(Estado.REC_MENOR);
				else if (hayMayor())
					transita(Estado.REC_MAYOR);
				else if (hayAnd())
					transita(Estado.REC_PRESEP);
				else if (haySep() || hayNL())
					transita(Estado.INICIO);
				else if (hayExcl())
					transita(Estado.REC_EXCL);
				else
					error();
				break;
			case REC_ID:
				if (hayLetra() || hayDigito() || hayBarrabaja())
					transita(Estado.REC_ID);
				else
					return unidadId();
				break;
			case REC_CERO:
				if (hayPunto())
					transita(Estado.REC_PUNTODEC);
				else if (hayExp())
					transita(Estado.REC_E_EXP);
				else
					return unidadEnt();
				break;
			case REC_ENT:
				if (hayDigito())
					transita(Estado.REC_ENT);
				else if (hayPunto())
					transita(Estado.REC_PUNTODEC);
				else if (hayExp())
					transita(Estado.REC_E_EXP);
				else
					return unidadEnt();
				break;
			case REC_PUNTODEC:
				if (hayCero())
					transita(Estado.REC_PUNTO0);
				else if (hayDigitoPos())
					transita(Estado.REC_DECPOS);
				else
					error("Debe haber un dígito");
				break;
			case REC_PUNTO0:
				if (hayExp())
					transita(Estado.REC_E_EXP);
				else if (hayDigitoPos())
					transita(Estado.REC_DECPOS);
				else if (hayCero())
					transita(Estado.REC_DEC0);
				else
					return unidadReal();
				break;
			case REC_DECPOS:
				if (hayCero())
					transita(Estado.REC_DEC0);
				else if (hayDigitoPos())
					transita(Estado.REC_DECPOS);
				else if (hayExp())
					transita(Estado.REC_E_EXP);
				else
					return unidadReal();
				break;
			case REC_DEC0:
				if (hayCero())
					transita(Estado.REC_DEC0);
				else if (hayDigitoPos())
					transita(Estado.REC_DECPOS);
				else
					error("No puede haber ceros no significativos a la derecha");
				break;
			case REC_E_EXP:
				if (hayCero())
					transita(Estado.REC_EXP0);
				else if (hayDigitoPos())
					transita(Estado.REC_EXP);
				else if (haySuma() || hayResta())
					transita(Estado.REC_SIGEXP);
				else
					error("El exponente solo puede comenzar por signo +/- o digito");
				break;
			case REC_EXP0:
				return unidadReal();
			case REC_SIGEXP:
				if (hayDigitoPos())
					transita(Estado.REC_EXP);
				if (hayCero())
					transita(Estado.REC_EXP0);
				break;
			case REC_EXP:
				if (hayDigito())
					transita(Estado.REC_EXP);
				else
					return unidadReal();
				break;
			case REC_MAS:
				if (hayDigitoPos())
					transita(Estado.REC_ENT);
				else if (hayCero())
					transita(Estado.REC_CERO);
				else
					return unidadMas();
				break;
			case REC_MENOS:
				if (hayDigitoPos())
					transita(Estado.REC_ENT);
				else if (hayCero())
					transita(Estado.REC_CERO);
				else
					return unidadMenos();
				break;
			case REC_POR:
				return unidadPor();
			case REC_DIV:
				return unidadDiv();
			case REC_PAP:
				return unidadPAp();
			case REC_PCIERR:
				return unidadPCierre();
			case REC_IGUALDAD:
				if (hayIgual()) transita(Estado.REC_IGUAL);
				else return unidadIgualdad();
				break;
			case REC_EXCL:
				if (hayIgual()) transita(Estado.REC_DESIGUAL);
				break;
			case REC_COMA:
				return unidadComa();
			case REC_PUNTOCOMA:
				return unidadPuntoComa();
			case REC_COM:
				if (hayNL())
					transitaIgnorando(Estado.INICIO);
				else if (hayEOF())
					transita(Estado.REC_EOF);
				else
					transitaIgnorando(Estado.REC_COM);
				break;
			case REC_EOF:
				return unidadEof();
			case REC_MENOR:
				if (hayIgual())
					transita(Estado.REC_MENIG);
				else
					return unidadMenor();
				break;
			case REC_MAYOR:
				if (hayIgual())
					transita(Estado.REC_MAYIG);
				else
					return unidadMayor();
				break;
			case REC_MAYIG:
				return unidadMayorIgual();
			case REC_MENIG:
				return unidadMenorIgual();
			case REC_IGUAL:
				return unidadIgual();
			case REC_DESIGUAL:
				return unidadDesigual();
			case REC_PRESEP:
				if (hayAnd())
					transita(Estado.REC_SEP);
				else
					error();
				break;
			case REC_SEP:
				return unidadSeparador();
			}
		}
	}

	private void transita(Estado sig) throws IOException {
		lex.append((char) sigCar);
		sigCar();
		estado = sig;
	}

	private void transitaIgnorando(Estado sig) throws IOException {
		sigCar();
		filaInicio = filaActual;
		columnaInicio = columnaActual;
		estado = sig;
	}

	private void sigCar() throws IOException {
		sigCar = input.read();
		if (sigCar == NL.charAt(0))
			saltaFinDeLinea();
		if (sigCar == '\n') {
			filaActual++;
			columnaActual = 0;
		} else {
			columnaActual++;
		}
	}

	private void saltaFinDeLinea() throws IOException {
		for (int i = 1; i < NL.length(); i++) {
			sigCar = input.read();
			if (sigCar != NL.charAt(i))
				error();
		}
		sigCar = '\n';
	}

	private boolean hayLetra() {
		return sigCar >= 'a' && sigCar <= 'z' || sigCar >= 'A' && sigCar <= 'z';
	}

	private boolean hayDigitoPos() {
		return sigCar >= '1' && sigCar <= '9';
	}

	private boolean hayCero() {
		return sigCar == '0';
	}

	private boolean hayDigito() {
		return hayDigitoPos() || hayCero();
	}

	private boolean haySuma() {
		return sigCar == '+';
	}

	private boolean hayResta() {
		return sigCar == '-';
	}

	private boolean hayMul() {
		return sigCar == '*';
	}

	private boolean hayDiv() {
		return sigCar == '/';
	}

	private boolean hayPAp() {
		return sigCar == '(';
	}

	private boolean hayPCierre() {
		return sigCar == ')';
	}

	private boolean hayIgual() {
		return sigCar == '=';
	}

	private boolean hayComa() {
		return sigCar == ',';
	}

	private boolean hayPunto() {
		return sigCar == '.';
	}
	
	private boolean hayPuntoComa() {
		return sigCar == ';';
	}

	private boolean hayAlmohadilla() {
		return sigCar == '#';
	}

	private boolean haySep() {
		return sigCar == ' ' || sigCar == '\t' || sigCar == '\n';
	}

	private boolean hayNL() {
		return sigCar == '\r' || sigCar == '\b' || sigCar == '\n';
	}

	private boolean hayEOF() {
		return sigCar == -1;
	}

	private boolean hayMayor() {
		return sigCar == '>';
	}

	private boolean hayMenor() {
		return sigCar == '<';
	}
	
	private boolean hayAnd() {
		return sigCar == '&';
	}
	
	private boolean hayExp() {
		return sigCar == 'E' || sigCar == 'e';
	}
	
	private boolean hayBarrabaja() {
		return sigCar == '_';
	}
	
	private boolean hayExcl() {
		return sigCar == '!';
	}

	private UnidadLexica unidadId() {
		switch (lex.toString()) {
		case "or":
			return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.OR);
		case "and":
			return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.AND);
		case "not":
			return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.NOT);
		case "int":
			return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.T_INT);
		case "real":
			return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.T_REAL);
		case "bool":
			return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.T_BOOL);
		case "true":
			return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.TRUE);
		case "false":
			return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.FALSE);
		default:
			return new UnidadLexicaMultivaluada(filaInicio, columnaInicio, ClaseLexica.IDEN, lex.toString());
		}
	}

	private UnidadLexica unidadEnt() {
		return new UnidadLexicaMultivaluada(filaInicio, columnaInicio, ClaseLexica.INT, lex.toString());
	}

	private UnidadLexica unidadReal() {
		return new UnidadLexicaMultivaluada(filaInicio, columnaInicio, ClaseLexica.REAL, lex.toString());
	}

	private UnidadLexica unidadMas() {
		return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.MAS);
	}

	private UnidadLexica unidadMenos() {
		return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.MENOS);
	}

	private UnidadLexica unidadPor() {
		return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.POR);
	}

	private UnidadLexica unidadDiv() {
		return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.DIV);
	}

	private UnidadLexica unidadPAp() {
		return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.PAP);
	}

	private UnidadLexica unidadPCierre() {
		return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.PCIERRE);
	}
	
	private UnidadLexica unidadIgual() {
		return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.IGUAL);
	}
	
	private UnidadLexica unidadDesigual() {
		return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.DESIGUAL);
	}

	private UnidadLexica unidadIgualdad() {
		return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.IGUALDAD);
	}

	private UnidadLexica unidadComa() {
		return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.COMA);
	}
	
	private UnidadLexica unidadPuntoComa() {
		return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.PUNTOCOMA);
	}

	private UnidadLexica unidadEof() {
		return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.EOF);
	}
	
	private UnidadLexica unidadMenor() {
		return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.MENOR);
	}
	
	private UnidadLexica unidadMayor() {
		return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.MAYOR);
	}
	
	private UnidadLexica unidadMenorIgual() {
		return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.MENIG);
	}
	
	private UnidadLexica unidadMayorIgual() {
		return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.MAYIG);
	}
	
	private UnidadLexica unidadSeparador() {
		return new UnidadLexicaUnivaluada(filaInicio, columnaInicio, ClaseLexica.SEPARADOR);
	}
	
	private void error(String msg) {
		System.err.println("(" + filaActual + ',' + columnaActual + "):" + msg);
		System.exit(1);
	}
	
	private void error() {
		errores.errorLexico(filaActual, columnaActual, Character.toString((char)sigCar));
	}

	public static void main(String arg[]) throws IOException {
		Reader input = new InputStreamReader(new FileInputStream("input.txt"));
		AnalizadorLexicoTiny al = new AnalizadorLexicoTiny(input);
		UnidadLexica unidad;
		do {
			unidad = al.sigToken();
			System.out.println(unidad);
		} while (unidad.clase() != ClaseLexica.EOF);
	}
}