package maquinaP;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class MaquinaP {
	public static class EAccesoIlegitimo extends RuntimeException {
	}

	public static class EAccesoAMemoriaNoInicializada extends RuntimeException {
		public EAccesoAMemoriaNoInicializada(int pc, int dir) {
			super("pinst:" + pc + " dir:" + dir);
		}
	}

	public static class EAccesoFueraDeRango extends RuntimeException {
	}

	private GestorMemoriaDinamica gestorMemoriaDinamica;
	private GestorPilaActivaciones gestorPilaActivaciones;

	private class Valor {
		public int valorInt() {
			throw new EAccesoIlegitimo();
		}

		public boolean valorBool() {
			throw new EAccesoIlegitimo();
		}
		
		public double valorReal() {
			throw new EAccesoIlegitimo();
		}
		
		public String valorString() {
			throw new EAccesoIlegitimo();
		}
	}

	private class ValorInt extends Valor {
		private int valor;

		public ValorInt(int valor) {
			this.valor = valor;
		}

		public int valorInt() {
			return valor;
		}
		
		public double valorReal() {
			return valor;
		}

		public String toString() {
			return String.valueOf(valor);
		}
	}

	private class ValorBool extends Valor {
		private boolean valor;

		public ValorBool(boolean valor) {
			this.valor = valor;
		}

		public boolean valorBool() {
			return valor;
		}

		public String toString() {
			return String.valueOf(valor);
		}
	}
	
	private class ValorString extends Valor {
		private String valor;
		
		public ValorString(String v) {
			valor = v;
		}
		
		public String valorString() {
			return valor;
		}
		
		public String toString() {
			return valor;
		}
	}
	
	private class ValorReal extends Valor {
		private double valor;
		
		public ValorReal(double v) {
			valor = v;
		}
		
		public double valorReal() {
			return valor;
		}
		
		public String toString() {
			return String.valueOf(valor);
		}
	}

	private List<Instruccion> codigoP;
	private Stack<Valor> pilaEvaluacion;
	private Valor[] datos;
	private int pc;

	public interface Instruccion {
		void ejecuta();
	}

	private ISuma ISUMA;

	private class ISuma implements Instruccion {
		public void ejecuta() {
			Valor opnd2 = pilaEvaluacion.pop();
			Valor opnd1 = pilaEvaluacion.pop();
			pilaEvaluacion.push(new ValorInt(opnd1.valorInt() + opnd2.valorInt()));
			pc++;
		}

		public String toString() {
			return "suma";
		};
	}
	
	private ISumaReal ISUMAREAL = new ISumaReal();
	
	private class ISumaReal implements Instruccion {
		public void ejecuta() {
			Valor opnd2 = pilaEvaluacion.pop();
			Valor opnd1 = pilaEvaluacion.pop();
			pilaEvaluacion.push(new ValorReal(opnd1.valorReal() + opnd2.valorReal()));
			pc++;
		}
		
		public String toString() {
			return "sumareal";
		}
	}
	
	private IResta IRESTA = new IResta();

	private class IResta implements Instruccion {
		public void ejecuta() {
			Valor opnd2 = pilaEvaluacion.pop();
			Valor opnd1 = pilaEvaluacion.pop();
			pilaEvaluacion.push(new ValorInt(opnd1.valorInt() - opnd2.valorInt()));
			pc++;
		}

		public String toString() {
			return "resta";
		};
	}
	
	private IRestaReal IRESTAREAL = new IRestaReal();
	
	private class IRestaReal implements Instruccion {
		public void ejecuta() {
			Valor opnd2 = pilaEvaluacion.pop();
			Valor opnd1 = pilaEvaluacion.pop();
			pilaEvaluacion.push(new ValorReal(opnd1.valorReal() - opnd2.valorReal()));
			pc++;
		}
		
		public String toString() {
			return "restareal";
		}
	}

	private IMul IMUL;

	private class IMul implements Instruccion {
		public void ejecuta() {
			Valor opnd2 = pilaEvaluacion.pop();
			Valor opnd1 = pilaEvaluacion.pop();
			pilaEvaluacion.push(new ValorInt(opnd1.valorInt() * opnd2.valorInt()));
			pc++;
		}

		public String toString() {
			return "mul";
		};
	}
	
	private IMulReal IMULREAL;
	
	private class IMulReal implements Instruccion {
		public void ejecuta() {
			Valor opnd2 = pilaEvaluacion.pop();
			Valor opnd1 = pilaEvaluacion.pop();
			pilaEvaluacion.push(new ValorReal(opnd1.valorReal() * opnd2.valorReal()));
			pc++;
		}

		public String toString() {
			return "mulreal";
		};
	}
	
	private IDiv IDIV = new IDiv();

	private class IDiv implements Instruccion {
		public void ejecuta() {
			Valor opnd2 = pilaEvaluacion.pop();
			Valor opnd1 = pilaEvaluacion.pop();
			pilaEvaluacion.push(new ValorInt(opnd1.valorInt() / opnd2.valorInt()));
			pc++;
		}

		public String toString() {
			return "div";
		};
	}
	
	private IDivReal IDIVREAL = new IDivReal();
	
	private class IDivReal implements Instruccion {
		public void ejecuta() {
			Valor opnd2 = pilaEvaluacion.pop();
			Valor opnd1 = pilaEvaluacion.pop();
			pilaEvaluacion.push(new ValorReal(opnd1.valorReal() * opnd2.valorReal()));
			pc++;
		}

		public String toString() {
			return "divreal";
		};
	}
	
	private IModulo IMODULO = new IModulo();
	
	private class IModulo implements Instruccion {
		public void ejecuta() {
			Valor opnd2 = pilaEvaluacion.pop();
			Valor opnd1 = pilaEvaluacion.pop();
			pilaEvaluacion.push(new ValorInt(opnd1.valorInt() % opnd2.valorInt()));
			pc++;
		}

		public String toString() {
			return "modulo";
		};
	}

	private IAnd IAND;

	private class IAnd implements Instruccion {
		public void ejecuta() {
			Valor opnd2 = pilaEvaluacion.pop();
			Valor opnd1 = pilaEvaluacion.pop();
			pilaEvaluacion.push(new ValorBool(opnd1.valorBool() && opnd2.valorBool()));
			pc++;
		}

		public String toString() {
			return "and";
		};
	}
	
	private IOr IOR = new IOr();
	
	private class IOr implements Instruccion {
		public void ejecuta() {
			Valor opnd2 = pilaEvaluacion.pop();
			Valor opnd1 = pilaEvaluacion.pop();
			pilaEvaluacion.push(new ValorBool(opnd1.valorBool() || opnd2.valorBool()));
			pc++;
		}
		
		public String toString() {
			return "or";
		}
	}
	
	private INot INOT = new INot();
	
	private class INot implements Instruccion {
		public void ejecuta() {
			Valor opnd1 = pilaEvaluacion.pop();
			pilaEvaluacion.push(new ValorBool(!opnd1.valorBool()));
			pc++;
		}
		
		public String toString() {
			return "not";
		}
	}	

	private class IApilaInt implements Instruccion {
		private int valor;

		public IApilaInt(int valor) {
			this.valor = valor;
		}

		public void ejecuta() {
			pilaEvaluacion.push(new ValorInt(valor));
			pc++;
		}

		public String toString() {
			return "apilaInt(" + valor + ")";
		};
	}

	private class IApilaBool implements Instruccion {
		private boolean valor;

		public IApilaBool(boolean valor) {
			this.valor = valor;
		}

		public void ejecuta() {
			pilaEvaluacion.push(new ValorBool(valor));
			pc++;
		}

		public String toString() {
			return "apilaBool(" + valor + ")";
		};
	}
	
	private class IApilaReal implements Instruccion {
		private double valor;

		public IApilaReal(double valor) {
			this.valor = valor;
		}

		public void ejecuta() {
			pilaEvaluacion.push(new ValorReal(valor));
			pc++;
		}

		public String toString() {
			return "apilaReal(" + valor + ")";
		};
	}
	
	private class IApilaString implements Instruccion {
		private String valor;
		
		public IApilaString(String v) {
			valor = v;
		}
		
		public void ejecuta() {
			pilaEvaluacion.push(new ValorString(valor));
			pc++;
		}
		
		private String escape(String str) {
			return str.replace("\n", "\\n");
		}
		
		public String toString() {
			return "apilaString(\"" + escape(valor) + "\")";
		}
	}

	private class IIrA implements Instruccion {
		private int dir;

		public IIrA(int dir) {
			this.dir = dir;
		}

		public void ejecuta() {
			pc = dir;
		}

		public String toString() {
			return "ira(" + dir + ")";
		};
	}

	private class IIrF implements Instruccion {
		private int dir;

		public IIrF(int dir) {
			this.dir = dir;
		}

		public void ejecuta() {
			if (!pilaEvaluacion.pop().valorBool()) {
				pc = dir;
			} else {
				pc++;
			}
		}

		public String toString() {
			return "irf(" + dir + ")";
		};
	}

	private class IMueve implements Instruccion {
		private int tam;

		public IMueve(int tam) {
			this.tam = tam;
		}

		public void ejecuta() {
			int dirOrigen = pilaEvaluacion.pop().valorInt();
			int dirDestino = pilaEvaluacion.pop().valorInt();
			if ((dirOrigen + (tam - 1)) >= datos.length)
				throw new EAccesoFueraDeRango();
			if ((dirDestino + (tam - 1)) >= datos.length)
				throw new EAccesoFueraDeRango();
			for (int i = 0; i < tam; i++)
				datos[dirDestino + i] = datos[dirOrigen + i];
			pc++;
		}

		public String toString() {
			return "mueve(" + tam + ")";
		};
	}

	private IApilaind IAPILAIND;

	private class IApilaind implements Instruccion {
		public void ejecuta() {
			int dir = pilaEvaluacion.pop().valorInt();
			if (dir >= datos.length)
				throw new EAccesoFueraDeRango();
			if (datos[dir] == null)
				throw new EAccesoAMemoriaNoInicializada(pc, dir);
			pilaEvaluacion.push(datos[dir]);
			pc++;
		}

		public String toString() {
			return "apilaind";
		};
	}

	private IDesapilaind IDESAPILAIND;

	private class IDesapilaind implements Instruccion {
		public void ejecuta() {
			Valor valor = pilaEvaluacion.pop();
			int dir = pilaEvaluacion.pop().valorInt();
			if (dir >= datos.length)
				throw new EAccesoFueraDeRango();
			datos[dir] = valor;
			pc++;
		}

		public String toString() {
			return "desapilaind";
		};
	}

	private class IAlloc implements Instruccion {
		private int tam;

		public IAlloc(int tam) {
			this.tam = tam;
		}

		public void ejecuta() {
			int inicio = gestorMemoriaDinamica.alloc(tam);
			pilaEvaluacion.push(new ValorInt(inicio));
			pc++;
		}

		public String toString() {
			return "alloc(" + tam + ")";
		};
	}

	private class IDealloc implements Instruccion {
		private int tam;

		public IDealloc(int tam) {
			this.tam = tam;
		}

		public void ejecuta() {
			int inicio = pilaEvaluacion.pop().valorInt();
			gestorMemoriaDinamica.free(inicio, tam);
			pc++;
		}

		public String toString() {
			return "dealloc(" + tam + ")";
		};
	}

	private class IActiva implements Instruccion {
		private int nivel;
		private int tamdatos;
		private int dirretorno;

		public IActiva(int nivel, int tamdatos, int dirretorno) {
			this.nivel = nivel;
			this.tamdatos = tamdatos;
			this.dirretorno = dirretorno;
		}

		public void ejecuta() {
			int base = gestorPilaActivaciones.creaRegistroActivacion(tamdatos);
			datos[base] = new ValorInt(dirretorno);
			datos[base + 1] = new ValorInt(gestorPilaActivaciones.display(nivel));
			pilaEvaluacion.push(new ValorInt(base + 2));
			pc++;
		}

		public String toString() {
			return "activa(" + nivel + "," + tamdatos + "," + dirretorno + ")";
		}
	}

	private class IDesactiva implements Instruccion {
		private int nivel;
		private int tamdatos;

		public IDesactiva(int nivel, int tamdatos) {
			this.nivel = nivel;
			this.tamdatos = tamdatos;
		}

		public void ejecuta() {
			int base = gestorPilaActivaciones.liberaRegistroActivacion(tamdatos);
			gestorPilaActivaciones.fijaDisplay(nivel, datos[base + 1].valorInt());
			pilaEvaluacion.push(datos[base]);
			pc++;
		}

		public String toString() {
			return "desactiva(" + nivel + "," + tamdatos + ")";
		}

	}

	private class IDesapilad implements Instruccion {
		private int nivel;

		public IDesapilad(int nivel) {
			this.nivel = nivel;
		}

		public void ejecuta() {
			gestorPilaActivaciones.fijaDisplay(nivel, pilaEvaluacion.pop().valorInt());
			pc++;
		}

		public String toString() {
			return "setd(" + nivel + ")";
		}
	}
	
	private IMenorNum IMENORNUM = new IMenorNum();
	
	private class IMenorNum implements Instruccion {
		public void ejecuta() {
			Valor opnd2 = pilaEvaluacion.pop();
			Valor opnd1 = pilaEvaluacion.pop();
			
			pilaEvaluacion.push(new ValorBool(opnd1.valorReal() < opnd2.valorReal()));
			pc++;
		}
		
		public String toString() {
			return "menornum";
		}
	}
	
	private IMenorString IMENORSTRING = new IMenorString();
	
	private class IMenorString implements Instruccion {
		public void ejecuta() {
			Valor opnd2 = pilaEvaluacion.pop();
			Valor opnd1 = pilaEvaluacion.pop();
			
			pilaEvaluacion.push(new ValorBool(opnd1.valorString().compareTo(opnd2.valorString()) < 0));
			pc++;
		}
		
		public String toString() {
			return "menorstring";
		}
	}
	
private IMenorIgNum IMENORIGNUM = new IMenorIgNum();
	
	private class IMenorIgNum implements Instruccion {
		public void ejecuta() {
			Valor opnd2 = pilaEvaluacion.pop();
			Valor opnd1 = pilaEvaluacion.pop();
			
			pilaEvaluacion.push(new ValorBool(opnd1.valorReal() <= opnd2.valorReal()));
			pc++;
		}
		
		public String toString() {
			return "menorignum";
		}
	}
	
	private IMenorIgString IMENORIGSTRING = new IMenorIgString();
	
	private class IMenorIgString implements Instruccion {
		public void ejecuta() {
			Valor opnd2 = pilaEvaluacion.pop();
			Valor opnd1 = pilaEvaluacion.pop();
			
			pilaEvaluacion.push(new ValorBool(opnd1.valorString().compareTo(opnd2.valorString()) <= 0));
			pc++;
		}
		
		public String toString() {
			return "menorigstring";
		}
	}
	
	private IIgualNum IIGUALNUM = new IIgualNum();
	
	private class IIgualNum implements Instruccion {
		public void ejecuta() {
			Valor opnd2 = pilaEvaluacion.pop();
			Valor opnd1 = pilaEvaluacion.pop();
			
			pilaEvaluacion.push(new ValorBool(opnd1.valorReal() == opnd2.valorReal()));
			pc++;
		}
		
		public String toString() {
			return "igualnum";
		}
	}
	
	private IIgualString IIGUALSTRING = new IIgualString();
	
	private class IIgualString implements Instruccion {
		public void ejecuta() {
			Valor opnd2 = pilaEvaluacion.pop();
			Valor opnd1 = pilaEvaluacion.pop();
			
			pilaEvaluacion.push(new ValorBool(opnd1.valorString().compareTo(opnd2.valorString()) == 0));
			pc++;
		}
		
		public String toString() {
			return "igualstring";
		}
	}
	

	private IDup IDUP;

	private class IDup implements Instruccion {
		public void ejecuta() {
			pilaEvaluacion.push(pilaEvaluacion.peek());
			pc++;
		}

		public String toString() {
			return "dup";
		}
	}

	private Instruccion ISTOP;

	private class IStop implements Instruccion {
		public void ejecuta() {
			pc = codigoP.size();
		}

		public String toString() {
			return "stop";
		}
	}

	private class IApilad implements Instruccion {
		private int nivel;

		public IApilad(int nivel) {
			this.nivel = nivel;
		}

		public void ejecuta() {
			pilaEvaluacion.push(new ValorInt(gestorPilaActivaciones.display(nivel)));
			pc++;
		}

		public String toString() {
			return "apilad(" + nivel + ")";
		}

	}

	private Instruccion IIRIND;

	private class IIrind implements Instruccion {
		public void ejecuta() {
			pc = pilaEvaluacion.pop().valorInt();
		}

		public String toString() {
			return "irind";
		}
	}
	
	private Instruccion IWRITEINT;
	
	private class IWriteInt implements Instruccion {
		public void ejecuta() {
			System.out.print(pilaEvaluacion.pop().valorInt());
			pc++;
		}
		
		public String toString() {
			return "writeint";
		}
	}
	
	private Instruccion IWRITEBOOL = new IWriteBool();
	
	private class IWriteBool implements Instruccion {
		public void ejecuta() {
			System.out.print(pilaEvaluacion.pop().valorBool());
			pc++;
		}
		
		public String toString() {
			return "writebool";
		}
	}
	
	private Instruccion IWRITEREAL = new IWriteReal();
	
	private class IWriteReal implements Instruccion {
		public void ejecuta() {
			System.out.print(pilaEvaluacion.pop().valorReal());
			pc++;
		}
		
		public String toString() {
			return "writereal";
		}
	}
	
	private Instruccion IWRITESTRING = new IWriteString();
	
	private class IWriteString implements Instruccion {
		public void ejecuta() {
			System.out.print(pilaEvaluacion.pop().valorString());
			pc++;
		}
		
		public String toString() {
			return "writestring";
		}
	}

	public Instruccion suma() {
		return ISUMA;
	}
	
	public Instruccion sumaReal() {
		return ISUMAREAL;
	}
	
	public Instruccion resta() {
		return IRESTA;
	}
	
	public Instruccion restaReal() {
		return IRESTAREAL;
	}

	public Instruccion mul() {
		return IMUL;
	}
	
	public Instruccion mulReal() {
		return IMULREAL;
	}
	
	public Instruccion div() {
		return IDIV;
	}
	
	public Instruccion divReal() {
		return IDIVREAL;
	}
	
	public Instruccion modulo() {
		return IMODULO;
	}

	public Instruccion and() {
		return IAND;
	}
	
	public Instruccion or() {
		return IOR;
	}
	
	public Instruccion not() {
		return INOT;
	}

	public Instruccion apilaInt(int val) {
		return new IApilaInt(val);
	}

	public Instruccion apilaBool(boolean val) {
		return new IApilaBool(val);
	}
	
	public Instruccion apilaReal(double val) {
		return new IApilaReal(val);
	}
	
	public Instruccion apilaString(String string) {
		return new IApilaString(string);
	}

	public Instruccion apilad(int nivel) {
		return new IApilad(nivel);
	}

	public Instruccion apilaInd() {
		return IAPILAIND;
	}

	public Instruccion desapilaInd() {
		return IDESAPILAIND;
	}

	public Instruccion mueve(int tam) {
		return new IMueve(tam);
	}

	public Instruccion irA(int dir) {
		return new IIrA(dir);
	}

	public Instruccion irF(int dir) {
		return new IIrF(dir);
	}

	public Instruccion irInd() {
		return IIRIND;
	}

	public Instruccion alloc(int tam) {
		return new IAlloc(tam);
	}

	public Instruccion dealloc(int tam) {
		return new IDealloc(tam);
	}

	public Instruccion activa(int nivel, int tam, int dirretorno) {
		return new IActiva(nivel, tam, dirretorno);
	}

	public Instruccion desactiva(int nivel, int tam) {
		return new IDesactiva(nivel, tam);
	}

	public Instruccion desapilad(int nivel) {
		return new IDesapilad(nivel);
	}
	
	public Instruccion menorNum() {
		return IMENORNUM;
	}
	
	public Instruccion menorString() {
		return IMENORSTRING;
	}
	
	public Instruccion menorIgNum() {
		return IMENORIGNUM;
	}
	
	public Instruccion menorIgString() {
		return IMENORIGSTRING;
	}
	
	public Instruccion igualNum() {
		return IIGUALNUM;
	}
	
	public Instruccion igualString() {
		return IIGUALSTRING;
	}
	
	public Instruccion writeInt() {
		return IWRITEINT;
	}
	
	public Instruccion writeBool() {
		return IWRITEBOOL;
	}
	
	public Instruccion writeReal() {
		return IWRITEREAL;
	}
	
	public Instruccion writeString() {
		return IWRITESTRING;
	}

	public Instruccion dup() {
		return IDUP;
	}

	public Instruccion stop() {
		return ISTOP;
	}

	public void ponInstruccion(Instruccion i) {
		codigoP.add(i);
	}

	private int tamdatos;
	private int tamheap;
	private int ndisplays;

	public MaquinaP(int tamdatos, int tampila, int tamheap, int ndisplays) {
		this.tamdatos = tamdatos;
		this.tamheap = tamheap;
		this.ndisplays = ndisplays;
		this.codigoP = new ArrayList<>();
		pilaEvaluacion = new Stack<>();
		datos = new Valor[tamdatos + tampila + tamheap];
		this.pc = 0;
		ISUMA = new ISuma();
		IAND = new IAnd();
		IMUL = new IMul();
		IAPILAIND = new IApilaind();
		IDESAPILAIND = new IDesapilaind();
		IIRIND = new IIrind();
		IWRITEINT = new IWriteInt();
		IDUP = new IDup();
		ISTOP = new IStop();
		gestorPilaActivaciones = new GestorPilaActivaciones(tamdatos, (tamdatos + tampila) - 1, ndisplays);
		gestorMemoriaDinamica = new GestorMemoriaDinamica(tamdatos + tampila, (tamdatos + tampila + tamheap) - 1);
	}

	public void ejecuta() {
		while (pc != codigoP.size()) {
			codigoP.get(pc).ejecuta();
		}
	}

	public void muestraCodigo() {
		for (int i = 0; i < codigoP.size(); i++) {
			System.out.printf("%03d %s%n", i, codigoP.get(i));
		}
	}

	public void muestraEstado() {
		System.out.println("Tam datos:" + tamdatos);
		System.out.println("Tam heap:" + tamheap);
		System.out.println("PP:" + gestorPilaActivaciones.pp());
		System.out.print("Displays:");
		for (int i = 1; i <= ndisplays; i++)
			System.out.print(i + ":" + gestorPilaActivaciones.display(i) + " ");
		System.out.println();
		System.out.println("Pila de evaluacion");
		for (int i = 0; i < pilaEvaluacion.size(); i++) {
			System.out.println(" " + i + ":" + pilaEvaluacion.get(i));
		}
		System.out.println("Datos");
		for (int i = 0; i < datos.length; i++) {
			System.out.println(" " + i + ":" + datos[i]);
		}
		System.out.println("PC:" + pc);
	}

	public static void main(String[] args) {
		MaquinaP m = new MaquinaP(5, 10, 10, 2);

		/*
		 * int x; proc store(int v) { x = v } && call store(5)
		 */

		m.ponInstruccion(m.activa(1, 1, 8));
		m.ponInstruccion(m.dup());
		m.ponInstruccion(m.apilaInt(0));
		m.ponInstruccion(m.suma());
		m.ponInstruccion(m.apilaInt(5));
		m.ponInstruccion(m.desapilaInd());
		m.ponInstruccion(m.desapilad(1));
		m.ponInstruccion(m.irA(9));
		m.ponInstruccion(m.stop());
		m.ponInstruccion(m.apilaInt(0));
		m.ponInstruccion(m.apilad(1));
		m.ponInstruccion(m.apilaInt(0));
		m.ponInstruccion(m.suma());
		m.ponInstruccion(m.mueve(1));
		m.ponInstruccion(m.desactiva(1, 1));
		m.ponInstruccion(m.irInd());
		m.ejecuta();
		m.muestraEstado();
	}
}
