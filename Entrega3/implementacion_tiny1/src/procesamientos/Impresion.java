package procesamientos;

import asint.TinyASint.Acc_registro;
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
		System.out.println("# Imprimiendo Programa");
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

	@Override
	public void procesa(Prog_sin_decs prog) {
		prog.insts().procesa(this);
	}

	@Override
	public void procesa(Prog_con_decs prog) {
		prog.decs().procesa(this);
		printin("&&");
		prog.insts().procesa(this);
	}

	@Override
	public void procesa(Decs_una dec) {
		dec.dec().procesa(this);
		System.out.println(";");
	}

	@Override
	public void procesa(Decs_muchas decs) {
		decs.decs().procesa(this);
		decs.dec().procesa(this);
		System.out.println(";");
	}

	@Override
	public void procesa(Var var) {
		// TODO Auto-generated method stub

	}

	@Override
	public void procesa(Type type) {
		// TODO Auto-generated method stub
		printin("type ");
		type.tipo().procesa(this);
		System.out.print(type.typeName());
	}

	@Override
	public void procesa(Proc proc) {
		// TODO Auto-generated method stub

	}

	@Override
	public void procesa(Param_f_sin param_f_sin) {
		// TODO Auto-generated method stub

	}

	@Override
	public void procesa(Param_f_con_una param_f_con_una) {
		// TODO Auto-generated method stub

	}

	@Override
	public void procesa(Param_f_con_muchas param_f_con_muchas) {
		// TODO Auto-generated method stub

	}

	@Override
	public void procesa(Param_f_ref param_f_ref) {
		// TODO Auto-generated method stub

	}

	@Override
	public void procesa(Param_f_noref param_f_noref) {
		// TODO Auto-generated method stub

	}

	@Override
	public void procesa(Tipo_array tipo_array) {
		// TODO Auto-generated method stub

	}

	@Override
	public void procesa(Tipo_record tipo_record) {
		System.out.println("record {");
		in();
		tipo_record.campos().procesa(this);
		un();
		printin("} ");
	}

	@Override
	public void procesa(Tipo_pointer tipo_pointer) {
		System.out.print("pointer ");
		tipo_pointer.tipo().procesa(this);
	}

	@Override
	public void procesa(Tipo_iden tipo_iden) {
		System.out.print(tipo_iden.iden() + " ");
	}

	@Override
	public void procesa(Tipo_int tipo_int) {
		// TODO Auto-generated method stub

	}

	@Override
	public void procesa(Tipo_real tipo_real) {
		// TODO Auto-generated method stub

	}

	@Override
	public void procesa(Tipo_bool tipo_bool) {
		// TODO Auto-generated method stub

	}

	@Override
	public void procesa(Tipo_string tipo_string) {
		System.out.print("string "); 
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
		System.out.print(campo.identificador());
		System.out.println(";");
	}

	@Override
	public void procesa(Insts_una insts_una) {
		// TODO Auto-generated method stub

	}

	@Override
	public void procesa(Insts_muchas insts_muchas) {
		// TODO Auto-generated method stub

	}

	@Override
	public void procesa(E_igual e_igual) {
		// TODO Auto-generated method stub

	}

	@Override
	public void procesa(If if_) {
		// TODO Auto-generated method stub

	}

	@Override
	public void procesa(Ifelse ifelse) {
		// TODO Auto-generated method stub

	}

	@Override
	public void procesa(While while_) {
		// TODO Auto-generated method stub

	}

	@Override
	public void procesa(Read read) {
		// TODO Auto-generated method stub

	}

	@Override
	public void procesa(Write write) {
		// TODO Auto-generated method stub

	}

	@Override
	public void procesa(Nl nl) {
		// TODO Auto-generated method stub

	}

	@Override
	public void procesa(New new_) {
		// TODO Auto-generated method stub

	}

	@Override
	public void procesa(Delete delete) {
		// TODO Auto-generated method stub

	}

	@Override
	public void procesa(Call call) {
		// TODO Auto-generated method stub

	}

	@Override
	public void procesa(Bl bl) {
		// TODO Auto-generated method stub

	}

	@Override
	public void procesa(Lista_sin lista_sin) {
		// TODO Auto-generated method stub

	}

	@Override
	public void procesa(Lista_con lista_con) {
		// TODO Auto-generated method stub

	}

	@Override
	public void procesa(Param_r_sin param_r_sin) {
		// TODO Auto-generated method stub

	}

	@Override
	public void procesa(Param_r_con_una param_r_con_una) {
		// TODO Auto-generated method stub

	}

	@Override
	public void procesa(Param_r_con_muchas param_r_con_muchas) {
		// TODO Auto-generated method stub

	}

	@Override
	public void procesa(Bloque_sin bloque_sin) {
		// TODO Auto-generated method stub

	}

	@Override
	public void procesa(Bloque_con bloque_con) {
		// TODO Auto-generated method stub

	}

	@Override
	public void procesa(Entero entero) {
		// TODO Auto-generated method stub

	}

	@Override
	public void procesa(Real real) {
		// TODO Auto-generated method stub

	}

	@Override
	public void procesa(Cadena cadena) {
		// TODO Auto-generated method stub

	}

	@Override
	public void procesa(Verdadero verdadero) {
		// TODO Auto-generated method stub

	}

	@Override
	public void procesa(Falso falso) {
		// TODO Auto-generated method stub

	}

	@Override
	public void procesa(Null null_) {
		// TODO Auto-generated method stub

	}

	@Override
	public void procesa(Identificador identificador) {
		// TODO Auto-generated method stub

	}

	@Override
	public void procesa(Suma suma) {
		// TODO Auto-generated method stub

	}

	@Override
	public void procesa(Resta resta) {
		// TODO Auto-generated method stub

	}

	@Override
	public void procesa(And and) {
		// TODO Auto-generated method stub

	}

	@Override
	public void procesa(Or or) {
		// TODO Auto-generated method stub

	}

	@Override
	public void procesa(Menor menor) {
		// TODO Auto-generated method stub

	}

	@Override
	public void procesa(Men_ig men_ig) {
		// TODO Auto-generated method stub

	}

	@Override
	public void procesa(Mayor mayor) {
		// TODO Auto-generated method stub

	}

	@Override
	public void procesa(May_ig may_ig) {
		// TODO Auto-generated method stub

	}

	@Override
	public void procesa(Igual igual) {
		// TODO Auto-generated method stub

	}

	@Override
	public void procesa(Desigual desigual) {
		// TODO Auto-generated method stub

	}

	@Override
	public void procesa(Mul mul) {
		// TODO Auto-generated method stub

	}

	@Override
	public void procesa(Div div) {
		// TODO Auto-generated method stub

	}

	@Override
	public void procesa(Modulo modulo) {
		// TODO Auto-generated method stub

	}

	@Override
	public void procesa(M_unario m_unario) {
		// TODO Auto-generated method stub

	}

	@Override
	public void procesa(Not not) {
		// TODO Auto-generated method stub

	}

	@Override
	public void procesa(Indexacion indexacion) {
		// TODO Auto-generated method stub

	}

	@Override
	public void procesa(Acc_registro acc_registro) {
		// TODO Auto-generated method stub

	}

	@Override
	public void procesa(Indireccion indireccion) {
		// TODO Auto-generated method stub

	}

}
