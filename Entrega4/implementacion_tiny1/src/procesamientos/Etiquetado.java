package procesamientos;

import asint.TinyASint.*;
import procesamientos.ComprobacionTipos.*;

public class Etiquetado implements Procesamiento {
	private int _etq = 0;
	
	@Override
	public void procesa(Prog_sin_decs prog) {
		prog.etqi = _etq;
		prog.insts().procesa(this);
		prog.etqs = _etq;
	}

	@Override
	public void procesa(Prog_con_decs prog) {
		prog.etqi = _etq;
		prog.decs().procesa(this);
		prog.insts().procesa(this);
		prog.etqs = _etq;
	}

	@Override
	public void procesa(Decs_una dec) {
		// TODO Auto-generated method stub

	}

	@Override
	public void procesa(Decs_muchas dec) {
		// TODO Auto-generated method stub

	}

	@Override
	public void procesa(Var var) {
		// TODO Auto-generated method stub

	}

	@Override
	public void procesa(Type type) {
		// TODO Auto-generated method stub

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
		// TODO Auto-generated method stub

	}

	@Override
	public void procesa(Tipo_pointer tipo_pointer) {
		// TODO Auto-generated method stub

	}

	@Override
	public void procesa(Tipo_iden tipo_iden) {
		// TODO Auto-generated method stub

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
		// TODO Auto-generated method stub

	}

	@Override
	public void procesa(Campos_uno campos_uno) {
		// TODO Auto-generated method stub

	}

	@Override
	public void procesa(Campos_muchos campos_muchos) {
		// TODO Auto-generated method stub

	}

	@Override
	public void procesa(Campo campo) {
		// TODO Auto-generated method stub

	}

	@Override
	public void procesa(Insts_una insts_una) {
		insts_una.etqi = _etq;
		insts_una.inst().procesa(this);
		insts_una.etqs = _etq;
	}

	@Override
	public void procesa(Insts_muchas insts_muchas) {
		insts_muchas.etqi = _etq;
		insts_muchas.insts().procesa(this);
		insts_muchas.inst().procesa(this);
		insts_muchas.etqs = _etq;
	}

	@Override
	public void procesa(E_igual e_igual) {
		e_igual.etqi = _etq;
		e_igual.var().procesa(this);
		e_igual.val().procesa(this);
		_etq++;
		e_igual.etqs = _etq;
	}

	@Override
	public void procesa(If if_) {
		if_.etqi = _etq;
		if_.condicion().procesa(this);
		_etq++;
		if_.pinst().procesa(this);
		if_.etqs = _etq;
	}

	@Override
	public void procesa(Ifelse ifelse) {
		ifelse.etqi = _etq;
		ifelse.condicion().procesa(this);
		_etq++;
		ifelse.pinst().procesa(this);
		_etq++;
		ifelse.pinstelse().procesa(this);
		ifelse.etqs = _etq;
	}

	@Override
	public void procesa(While while_) {
		while_.etqi = _etq;
		while_.condicion().procesa(this);
		_etq++;
		while_.pinst().procesa(this);
		_etq++;
		while_.etqs = _etq;
	}

	@Override
	public void procesa(Read read) {
		read.etqi = _etq;
		read.exp().procesa(this);
		_etq++; _etq++;
		read.etqs = _etq;
	}

	@Override
	public void procesa(Write write) {
		write.etqi = _etq;
		write.exp().procesa(this);
		if (write.exp().esDesignador()) _etq++;
		_etq++;
		write.etqs = _etq;
	}

	@Override
	public void procesa(Nl nl) {
		nl.etqi = _etq;
		_etq += 2;
		nl.etqs = _etq;
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
		lista_sin.etqi = lista_sin.etqs = _etq;
	}

	@Override
	public void procesa(Lista_con lista_con) {
		lista_con.etqi = _etq;
		lista_con.insts().procesa(this);
		lista_con.etqs = _etq;
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
		entero.etqi = _etq;
		_etq++;
		entero.etqs = _etq;
	}

	@Override
	public void procesa(Real real) {
		real.etqi = _etq;
		_etq++;
		real.etqs = _etq;
	}

	@Override
	public void procesa(Cadena cadena) {
		cadena.etqi = _etq;
		_etq++;
		cadena.etqs = _etq;
	}

	@Override
	public void procesa(Verdadero verdadero) {
		verdadero.etqi = _etq;
		_etq++;
		verdadero.etqs = _etq;
	}

	@Override
	public void procesa(Falso falso) {
		falso.etqi = _etq;
		_etq++;
		falso.etqs = _etq;
	}

	@Override
	public void procesa(Null null_) {
		null_.etqi = _etq;
		_etq++;
		null_.etqs = _etq;
	}

	@Override
	public void procesa(Identificador identificador) {
		identificador.etqi = _etq;
		if (identificador.nivel == 0) {
			_etq++;
		} else {
			_etq += 3;
		}
		identificador.etqs = _etq;
	}

	@Override
	public void procesa(Suma bop) {
		bop.etqi = _etq;
		bop.arg0().procesa(this);
		if (bop.arg0().esDesignador()) _etq++;
		bop.arg1().procesa(this);
		if (bop.arg1().esDesignador()) _etq++;
		
		_etq++;
		
		bop.etqs = _etq;
	}

	@Override
	public void procesa(Resta bop) {
		bop.etqi = _etq;
		bop.arg0().procesa(this);
		if (bop.arg0().esDesignador()) _etq++;
		bop.arg1().procesa(this);
		if (bop.arg1().esDesignador()) _etq++;
		
		_etq++;
		
		bop.etqs = _etq;
	}

	@Override
	public void procesa(And bop) {
		bop.etqi = _etq;
		bop.arg0().procesa(this);
		if (bop.arg0().esDesignador()) _etq++;
		bop.arg1().procesa(this);
		if (bop.arg1().esDesignador()) _etq++;
		
		_etq++;
		
		bop.etqs = _etq;
	}

	@Override
	public void procesa(Or bop) {
		bop.etqi = _etq;
		bop.arg0().procesa(this);
		if (bop.arg0().esDesignador()) _etq++;
		bop.arg1().procesa(this);
		if (bop.arg1().esDesignador()) _etq++;
		
		_etq++;
		
		bop.etqs = _etq;
	}

	@Override
	public void procesa(Menor menor) {
		menor.etqi = _etq;
		menor.arg0().procesa(this);
		if (menor.arg0().esDesignador()) _etq++;
		if (menor.arg0().getTipo().isBool()) _etq++;
		menor.arg1().procesa(this);
		if (menor.arg1().esDesignador()) _etq++;
		_etq++;
		menor.etqs = _etq;
	}

	@Override
	public void procesa(Men_ig men_ig) {
		men_ig.etqi = _etq;
		men_ig.arg0().procesa(this);
		if (men_ig.arg0().esDesignador()) _etq++;
		if (men_ig.arg0().getTipo().isBool()) _etq++;
		men_ig.arg1().procesa(this);
		if (men_ig.arg1().esDesignador()) _etq++;
		_etq++;
		men_ig.etqs = _etq;
	}

	@Override
	public void procesa(Mayor mayor) {
		mayor.etqi = _etq;
		mayor.arg1().procesa(this);
		if (mayor.arg1().esDesignador()) _etq++;
		if (mayor.arg0().getTipo().isBool()) _etq++;
		mayor.arg0().procesa(this);
		if (mayor.arg0().esDesignador()) _etq++;
		_etq++;
		mayor.etqs = _etq;
	}

	@Override
	public void procesa(May_ig may_ig) {
		may_ig.etqi = _etq;
		may_ig.arg1().procesa(this);
		if (may_ig.arg1().esDesignador()) _etq++;
		if (may_ig.arg0().getTipo().isBool()) _etq++;
		may_ig.arg0().procesa(this);
		if (may_ig.arg0().esDesignador()) _etq++;
		_etq++;
		may_ig.etqs = _etq;
	}

	@Override
	public void procesa(Igual igual) {
		igual.etqi = _etq;
		igual.arg0().procesa(this);
		if (igual.arg0().esDesignador()) _etq++;
		igual.arg1().procesa(this);
		if (igual.arg1().esDesignador()) _etq++;
		_etq++;
		igual.etqs = _etq;
	}

	@Override
	public void procesa(Desigual desigual) {
		desigual.etqi = _etq;
		desigual.arg0().procesa(this);
		if (desigual.arg0().esDesignador()) _etq++;
		desigual.arg1().procesa(this);
		if (desigual.arg1().esDesignador()) _etq++;
		_etq++; _etq++;
		desigual.etqs = _etq;
	}

	@Override
	public void procesa(Mul bop) {
		bop.etqi = _etq;
		bop.arg0().procesa(this);
		if (bop.arg0().esDesignador()) _etq++;
		bop.arg1().procesa(this);
		if (bop.arg1().esDesignador()) _etq++;
		
		_etq++;
		
		bop.etqs = _etq;
	}

	@Override
	public void procesa(Div bop) {
		bop.etqi = _etq;
		bop.arg0().procesa(this);
		if (bop.arg0().esDesignador()) _etq++;
		bop.arg1().procesa(this);
		if (bop.arg1().esDesignador()) _etq++;
		
		_etq++;
		
		bop.etqs = _etq;
	}

	@Override
	public void procesa(Modulo bop) {
		bop.etqi = _etq;
		bop.arg0().procesa(this);
		if (bop.arg0().esDesignador()) _etq++;
		bop.arg1().procesa(this);
		if (bop.arg1().esDesignador()) _etq++;
		
		_etq++;
		
		bop.etqs = _etq;
	}

	@Override
	public void procesa(M_unario m_unario) {
		m_unario.etqi = _etq;
		_etq++;
		m_unario.arg().procesa(this);
		if (m_unario.arg().esDesignador()) _etq++;
		_etq++;
		m_unario.etqs = _etq;
	}

	@Override
	public void procesa(Not not) {
		not.etqi = _etq;
		if (not.arg().esDesignador()) _etq++;
		_etq++;
		not.etqs = _etq;
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
