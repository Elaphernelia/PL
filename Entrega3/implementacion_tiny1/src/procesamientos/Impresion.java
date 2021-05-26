package procesamientos;

import asint.TinyASint.Acc_registro;
import asint.TinyASint.Acc_registro_indirecto;
import asint.TinyASint.And;
import asint.TinyASint.Bl;
import asint.TinyASint.Bloque_con;
import asint.TinyASint.Bloque_sin;
import asint.TinyASint.Cadena;
import asint.TinyASint.Call;
import asint.TinyASint.Campo;
import asint.TinyASint.Campos_muchos;
import asint.TinyASint.Campos_uno;
import asint.TinyASint.Decs_muchas;
import asint.TinyASint.Decs_una;
import asint.TinyASint.Delete;
import asint.TinyASint.Desigual;
import asint.TinyASint.Div;
import asint.TinyASint.E_igual;
import asint.TinyASint.Entero;
import asint.TinyASint.Exp;
import asint.TinyASint.Falso;
import asint.TinyASint.Identificador;
import asint.TinyASint.If;
import asint.TinyASint.Ifelse;
import asint.TinyASint.Igual;
import asint.TinyASint.Indexacion;
import asint.TinyASint.Indireccion;
import asint.TinyASint.Insts_muchas;
import asint.TinyASint.Insts_una;
import asint.TinyASint.Lista_con;
import asint.TinyASint.Lista_sin;
import asint.TinyASint.M_unario;
import asint.TinyASint.May_ig;
import asint.TinyASint.Mayor;
import asint.TinyASint.Men_ig;
import asint.TinyASint.Menor;
import asint.TinyASint.Modulo;
import asint.TinyASint.Mul;
import asint.TinyASint.New;
import asint.TinyASint.Nl;
import asint.TinyASint.Not;
import asint.TinyASint.Null;
import asint.TinyASint.Or;
import asint.TinyASint.Param_f_con_muchas;
import asint.TinyASint.Param_f_con_una;
import asint.TinyASint.Param_f_noref;
import asint.TinyASint.Param_f_ref;
import asint.TinyASint.Param_f_sin;
import asint.TinyASint.Param_r_con_muchas;
import asint.TinyASint.Param_r_con_una;
import asint.TinyASint.Param_r_sin;
import asint.TinyASint.Proc;
import asint.TinyASint.Prog_con_decs;
import asint.TinyASint.Prog_sin_decs;
import asint.TinyASint.Read;
import asint.TinyASint.Real;
import asint.TinyASint.Resta;
import asint.TinyASint.Suma;
import asint.TinyASint.Tipo_array;
import asint.TinyASint.Tipo_bool;
import asint.TinyASint.Tipo_iden;
import asint.TinyASint.Tipo_int;
import asint.TinyASint.Tipo_pointer;
import asint.TinyASint.Tipo_real;
import asint.TinyASint.Tipo_record;
import asint.TinyASint.Tipo_string;
import asint.TinyASint.Type;
import asint.TinyASint.Var;
import asint.TinyASint.Verdadero;
import asint.TinyASint.While;
import asint.TinyASint.Write;

public class Impresion implements Procesamiento {
	public static final int DEFAULT_IDENT = 4;
	
	private int _curri;
	private int _inci;
	
	public Impresion() {
		this(DEFAULT_IDENT);
	}
	
	public Impresion(int ident) {
		_inci = ident;
		_curri = 0;
	}
	
	private void in() {
		_curri += _inci;
	}
	
	private void un() {
		_curri -= _inci;
	}
	
	private void printin(String str) {
		for (int i = 0; i < _curri; i++) System.out.print(" ");
		System.out.print(str);
	}
	
	private void printarg(Exp arg, int p) {
		if (arg.prioridad() < p) {
			System.out.print("(");
			arg.procesa(this);
			System.out.print(")");
		} else {
			arg.procesa(this);
		}
	}

	@Override
	public void procesa(Prog_sin_decs prog) {
		prog.insts().procesa(this);
	}

	@Override
	public void procesa(Prog_con_decs prog) {
		prog.decs().procesa(this);
		System.out.println();
		printin("&&");
		System.out.println("");
		prog.insts().procesa(this);
	}

	@Override
	public void procesa(Decs_una dec) {
		dec.dec().procesa(this);
	}

	@Override
	public void procesa(Decs_muchas decs) {
		decs.decs().procesa(this);
		System.out.println(";");
		decs.dec().procesa(this);
	}

	@Override
	public void procesa(Var var) {
		printin("var ");
		var.tipo().procesa(this);
		System.out.print(" " + var.var());
	}

	@Override
	public void procesa(Type type) {
		printin("type ");
		type.tipo().procesa(this);
		System.out.print(" " + type.typeName());
	}

	@Override
	public void procesa(Proc proc) {
		printin("proc " + proc.procName() + "(");
		proc.pfs().procesa(this);
		System.out.print(") ");
		proc.bloque().procesa(this);
	}

	@Override
	public void procesa(Param_f_sin param_f_sin) {
		// Nothing to do
	}

	@Override
	public void procesa(Param_f_con_una param_f_con_una) {
		param_f_con_una.pf().procesa(this);
	}

	@Override
	public void procesa(Param_f_con_muchas param_f_con_muchas) {
		param_f_con_muchas.pfs().procesa(this);
		System.out.print(", ");
		param_f_con_muchas.pf().procesa(this);
	}

	@Override
	public void procesa(Param_f_ref param_f_ref) {
		param_f_ref.tipo().procesa(this);
		System.out.print("& " + param_f_ref.parametro());
	}

	@Override
	public void procesa(Param_f_noref param_f_noref) {
		param_f_noref.tipo().procesa(this);
		System.out.print(" " + param_f_noref.parametro());
	}

	@Override
	public void procesa(Tipo_array tipo_array) {
		System.out.print("array [" + tipo_array.tamanio() + "] of ");
		tipo_array.tipo().procesa(this);
	}

	@Override
	public void procesa(Tipo_record tipo_record) {
		System.out.println("record {");
		in();
		tipo_record.campos().procesa(this);
		un();
		printin("}");
	}

	@Override
	public void procesa(Tipo_pointer tipo_pointer) {
		System.out.print("pointer ");
		tipo_pointer.tipo().procesa(this);
	}

	@Override
	public void procesa(Tipo_iden tipo_iden) {
		System.out.print(tipo_iden.iden());
	}

	@Override
	public void procesa(Tipo_int tipo_int) {
		System.out.print("int");
	}

	@Override
	public void procesa(Tipo_real tipo_real) {
		System.out.print("real");
	}

	@Override
	public void procesa(Tipo_bool tipo_bool) {
		System.out.print("bool");
	}

	@Override
	public void procesa(Tipo_string tipo_string) {
		System.out.print("string"); 
	}

	@Override
	public void procesa(Campos_uno campos_uno) {
		campos_uno.campo().procesa(this);
	}

	@Override
	public void procesa(Campos_muchos campos_muchos) {
		campos_muchos.campos().procesa(this);
		campos_muchos.campo().procesa(this);
	}

	@Override
	public void procesa(Campo campo) {
		printin("");
		campo.tipo().procesa(this);
		System.out.print(" " + campo.identificador());
		System.out.println(";");
	}

	@Override
	public void procesa(Insts_una insts_una) {
		printin("");
		insts_una.inst().procesa(this);
	}

	@Override
	public void procesa(Insts_muchas insts_muchas) {
		insts_muchas.insts().procesa(this);
		System.out.println(";");
		printin("");
		insts_muchas.inst().procesa(this);
	}

	@Override
	public void procesa(E_igual e_igual) {
		e_igual.var().procesa(this);
		System.out.print(" = ");
		e_igual.val().procesa(this);
	}

	@Override
	public void procesa(If if_) {
		System.out.print("if ");
		if_.condicion().procesa(this);
		System.out.println(" then");
		in();
		if_.pinst().procesa(this);
		un();
		printin("endif");
	}

	@Override
	public void procesa(Ifelse ifelse) {
		System.out.print("if ");
		ifelse.condicion().procesa(this);
		System.out.println(" then");
		in();
		ifelse.pinst().procesa(this);
		un();
		printin("else");
		System.out.println();
		in();
		ifelse.pinstelse().procesa(this);
		un();
		printin("endif");
	}

	@Override
	public void procesa(While while_) {
		System.out.print("while ");
		while_.condicion().procesa(this);
		System.out.println(" do");
		in();	
		while_.pinst().procesa(this);	
		un();
		printin("endwhile");
	}

	@Override
	public void procesa(Read read) {
		System.out.print("read ");
		read.exp().procesa(this);
	}

	@Override
	public void procesa(Write write) {
		System.out.print("write ");
		write.exp().procesa(this);
	}

	@Override
	public void procesa(Nl nl) {
		System.out.print("nl");
	}

	@Override
	public void procesa(New new_) {
		System.out.print("new ");
		new_.exp().procesa(this);
	}

	@Override
	public void procesa(Delete delete) {
		System.out.print("delete ");
		delete.exp().procesa(this);
	}

	@Override
	public void procesa(Call call) {
		System.out.print("call " + call.procName() + "(");
		call.arguments().procesa(this);
		System.out.print(")");
	}

	@Override
	public void procesa(Bl bl) {
		bl.bloque().procesa(this);
	}

	@Override
	public void procesa(Lista_sin lista_sin) {
		System.out.println("");
	}

	@Override
	public void procesa(Lista_con lista_con) {
		lista_con.insts().procesa(this);
		System.out.println("");
	}

	@Override
	public void procesa(Param_r_sin param_r_sin) {
		// Nothing to do
	}

	@Override
	public void procesa(Param_r_con_una param_r_con_una) {
		param_r_con_una.param().procesa(this);
	}

	@Override
	public void procesa(Param_r_con_muchas param_r_con_muchas) {
		param_r_con_muchas.pr().procesa(this);
		System.out.print(", ");
		param_r_con_muchas.param().procesa(this);
	}

	@Override
	public void procesa(Bloque_sin bloque_sin) {
		System.out.print("{}");
	}

	@Override
	public void procesa(Bloque_con bloque_con) {
		System.out.println("{");
		in();
		bloque_con.prog().procesa(this);
		un();
		System.out.println("");
		printin("}");
	}

	@Override
	public void procesa(Entero entero) {
		System.out.print(entero.val());
	}

	@Override
	public void procesa(Real real) {
		System.out.print(real.val());
	}

	@Override
	public void procesa(Cadena cadena) {
		System.out.print(cadena.val());
	}

	@Override
	public void procesa(Verdadero verdadero) {
		System.out.print("true");
	}

	@Override
	public void procesa(Falso falso) {
		System.out.print("false");
	}

	@Override
	public void procesa(Null null_) {
		System.out.print("null");
	}

	@Override
	public void procesa(Identificador identificador) {
		System.out.print(identificador.name());
	}

	@Override
	public void procesa(Suma suma) {
		printarg(suma.arg0(), 1);
		System.out.print(" + ");
		printarg(suma.arg1(), 0);
	}

	@Override
	public void procesa(Resta resta) {
		printarg(resta.arg0(), 1);
		System.out.print(" - ");
		printarg(resta.arg1(), 1);
	}

	@Override
	public void procesa(And and) {
		printarg(and.arg0(), 1);
		System.out.print(" and ");
		printarg(and.arg0(), 2);
	}

	@Override
	public void procesa(Or or) {
		printarg(or.arg0(), 1);
		System.out.print(" or ");
		printarg(or.arg1(), 2);
	}

	@Override
	public void procesa(Menor menor) {
		printarg(menor.arg0(), 2);
		System.out.print(" < ");
		printarg(menor.arg1(), 3);
	}

	@Override
	public void procesa(Men_ig men_ig) {
		printarg(men_ig.arg0(), 2);
		System.out.print(" <= ");
		printarg(men_ig.arg1(), 3);
	}

	@Override
	public void procesa(Mayor mayor) {
		printarg(mayor.arg0(), 2);
		System.out.print(" > ");
		printarg(mayor.arg1(), 3);
	}

	@Override
	public void procesa(May_ig may_ig) {
		printarg(may_ig.arg0(), 2);
		System.out.print(" >= ");
		printarg(may_ig.arg1(), 3);
	}

	@Override
	public void procesa(Igual igual) {
		printarg(igual.arg0(), 2);
		System.out.print(" == ");
		printarg(igual.arg1(), 3);
	}

	@Override
	public void procesa(Desigual desigual) {
		printarg(desigual.arg0(), 2);
		System.out.print(" != ");
		printarg(desigual.arg1(), 3);
	}

	@Override
	public void procesa(Mul mul) {
		printarg(mul.arg0(), 4);
		System.out.print(" * ");
		printarg(mul.arg1(), 4);
	}

	@Override
	public void procesa(Div div) {
		printarg(div.arg0(), 4);
		System.out.print(" / ");
		printarg(div.arg1(), 4);
	}

	@Override
	public void procesa(Modulo modulo) {
		printarg(modulo.arg0(), 4);
		System.out.print(" % ");
		printarg(modulo.arg1(), 4);
	}

	@Override
	public void procesa(M_unario m_unario) {
		System.out.print("-");
		printarg(m_unario.arg(), 5);
	}

	@Override
	public void procesa(Not not) {
		System.out.print("not ");
		printarg(not.arg(), 4);
	}

	@Override
	public void procesa(Indexacion indexacion) {
		printarg(indexacion.arg0(), 5);
		System.out.print("[");
		indexacion.arg1().procesa(this);
		System.out.print("]");
	}

	@Override
	public void procesa(Acc_registro acc_registro) {
		printarg(acc_registro.registro(), 5);
		System.out.print("." + acc_registro.campo());
	}
	
	@Override
	public void procesa(Acc_registro_indirecto acc_registro_in) {
		printarg(acc_registro_in.registro(), 5);
		System.out.print("->" + acc_registro_in.campo());
	}

	@Override
	public void procesa(Indireccion indireccion) {
		System.out.print("*");
		printarg(indireccion.arg(), 7);
	}

}
