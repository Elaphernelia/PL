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

public class AsignaEspacio implements Procesamiento {
	private int _dir = 0;
	private int _nivel = 0;

	@Override
	public void procesa(Prog_sin_decs prog) {
		prog.insts().procesa(this);
	}

	@Override
	public void procesa(Prog_con_decs prog) {
		prog.decs().procesa(this);
		prog.insts().procesa(this);
	}

	@Override
	public void procesa(Decs_una dec) {
		dec.dec().procesa(this);
	}

	@Override
	public void procesa(Decs_muchas dec) {
		dec.decs().procesa(this);
		dec.dec().procesa(this);
	}

	@Override
	public void procesa(Var var) {
		var.dir = _dir;
		var.nivel = _nivel;
		
		var.tipo().procesa(this);
		
		var.dir += var.size;
	}

	@Override
	public void procesa(Type type) {
		type.tipo().procesa(this);
		type.size = type.tipo().size;
	}

	@Override
	public void procesa(Proc proc) {
		int dir_ant = _dir;
		_nivel++;
		_dir = 0;
		
		proc.pfs().procesa(this);
		proc.bloque().procesa(this);
		
		proc.nivel = _nivel;
		proc.size = _dir;
		
		_nivel--;
		_dir = dir_ant;
	}

	@Override
	public void procesa(Param_f_sin param_f_sin) {
		param_f_sin.size = 0;
	}

	@Override
	public void procesa(Param_f_con_una param_f_con_una) {
		param_f_con_una.pf().procesa(this);
	}

	@Override
	public void procesa(Param_f_con_muchas param_f_con_muchas) {
		param_f_con_muchas.pfs().procesa(this);
		param_f_con_muchas.pf().procesa(this);
	}

	@Override
	public void procesa(Param_f_ref param_f_ref) {
		param_f_ref.tipo().procesa(this);
		param_f_ref.dir = _dir;
		param_f_ref.nivel = _nivel;
		param_f_ref.size = 1;
		_dir++;
	}

	@Override
	public void procesa(Param_f_noref param_f_noref) {
		param_f_noref.tipo().procesa(this);
		param_f_noref.dir = _dir;
		param_f_noref.nivel = _nivel;
		param_f_noref.size = param_f_noref.tipo().size;
		_dir += param_f_noref.size;
	}

	@Override
	public void procesa(Tipo_array tipo_array) {
		tipo_array.tipo().procesa(this);
		tipo_array.size = tipo_array.tipo().size * tipo_array.tamanioInt();
	}

	@Override
	public void procesa(Tipo_record tipo_record) {
		tipo_record.campos().procesa(this);
		tipo_record.size = tipo_record.campos().size;
	}

	@Override
	public void procesa(Tipo_pointer tipo_pointer) {
		tipo_pointer.tipo().procesa(this);
		tipo_pointer.size = 1;
	}

	@Override
	public void procesa(Tipo_iden tipo_iden) {
		// TODO Auto-generated method stub

	}

	@Override
	public void procesa(Tipo_int tipo_int) {
		// TODO Auto-generated method stub
		tipo_int.size = 1;
	}

	@Override
	public void procesa(Tipo_real tipo_real) {
		// TODO Auto-generated method stub
		tipo_real.size = 1;
	}

	@Override
	public void procesa(Tipo_bool tipo_bool) {
		// TODO Auto-generated method stub
		tipo_bool.size = 1;
	}

	@Override
	public void procesa(Tipo_string tipo_string) {
		// TODO Auto-generated method stub

	}

	@Override
	public void procesa(Campos_uno campos_uno) {
		campos_uno.campo().procesa(this);
		campos_uno.size = campos_uno.campo().size;
	}

	@Override
	public void procesa(Campos_muchos campos_muchos) {
		campos_muchos.campos().procesa(this);
		campos_muchos.campo().procesa(this);
		campos_muchos.size = campos_muchos.campos().size + campos_muchos.campo().size;
	}

	@Override
	public void procesa(Campo campo) {
		campo.tipo().procesa(this);
		campo.size = campo.tipo().size;
	}

	@Override
	public void procesa(Insts_una insts_una) {
		insts_una.inst().procesa(this);
	}

	@Override
	public void procesa(Insts_muchas insts_muchas) {
		insts_muchas.insts().procesa(this);;
		insts_muchas.inst().procesa(this);
	}

	@Override
	public void procesa(E_igual e_igual) {
		e_igual.var().procesa(this);
		e_igual.val().procesa(this);
	}

	@Override
	public void procesa(If if_) {
		if_.condicion().procesa(this);
		if_.pinst().procesa(this);
	}

	@Override
	public void procesa(Ifelse ifelse) {
		ifelse.condicion().procesa(this);
		ifelse.pinst().procesa(this);
		ifelse.pinstelse().procesa(this);
	}

	@Override
	public void procesa(While while_) {
		while_.condicion().procesa(this);
		while_.pinst().procesa(this);
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
	public void procesa(Acc_registro_indirecto acc_registro_in) {
		// TODO Auto-generated method stub

	}

	@Override
	public void procesa(Indireccion indireccion) {
		// TODO Auto-generated method stub

	}

}
