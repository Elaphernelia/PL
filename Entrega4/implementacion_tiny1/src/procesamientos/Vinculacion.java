package procesamientos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import alex.StringLocalizado;
import asint.TinyASint.*;
import procesamientos.ComprobacionTipos.Tipable;

public class Vinculacion implements Procesamiento {
	private TablaSimbolos _t_sim;
	private boolean _dirty = false;
	
	public Vinculacion() {
		_t_sim = new TablaSimbolos();
	}
	
	public boolean isDirty() {
		return _dirty;
	}
	
	private class TablaSimbolos {
		private Map<String, DecInfo> _tabla_sim_act;       // la tabla de símbolos actual
		private Stack<Map<String, DecInfo>> _tablas_sim;   // Todas las tablas de símbolos
		
		public TablaSimbolos() {
			_tabla_sim_act = new HashMap<String,DecInfo>();
			_tablas_sim = new Stack<Map<String, DecInfo>>();
			_tablas_sim.push(_tabla_sim_act);
		}

		private void anida() {
			_tabla_sim_act = new HashMap<String,DecInfo>();
			_tablas_sim.push(_tabla_sim_act);
		}
		
		private void desanida() {
			_tablas_sim.pop();
			_tabla_sim_act = _tablas_sim.peek();
		}
		
		public void put(StringLocalizado str, Tipable gen) {
			_tabla_sim_act.put(str.toString(), new DecInfo(gen, str));
		}
		
		public boolean contieneAny(String str) {
			for (Map<String, DecInfo> ts: _tablas_sim) {
				if (ts.containsKey(str)) return true;
			}
			
			return false;
		}
		
		public boolean contieneAny(StringLocalizado str) {
			return contieneAny(str.toString());
		}
		
		public boolean contieneAct(String str) {
			return _tabla_sim_act.containsKey(str);
		}
		
		public boolean contieneAct(StringLocalizado str) {
			return contieneAct(str.toString());
		}
		
		public DecInfo get(String str) {
			for (Map<String, DecInfo> ts : _tablas_sim) {
				if (ts.containsKey(str)) return ts.get(str);
			}
			
			return null;
		}
		
		public DecInfo get(StringLocalizado str) {
			return get(str.toString());
		}
	}
	
	// "struct" con la info para reportar errores mejor
	private class DecInfo {
		public int fila;
		public int col;
		public Tipable gen;
		
		public DecInfo(Tipable gen, StringLocalizado s) {
			this.gen = gen;
			this.fila = s.fila();
			this.col = s.col();
		}

		public String toString() {
			return Integer.toString(fila) + ":" + Integer.toString(col);
		}
	}
	
	private void errorCommon(StringLocalizado id) {
		_dirty = true;
		System.out.println("Error de vinculación en " + Integer.toString(id.fila()) + ":" + Integer.toString(id.col()));
	}
	
	private void errorDec(StringLocalizado id) {
		errorCommon(id);
		System.out.println("  El identificador " + id + " ya ha sido declarado previamente en:" + _t_sim.get(id.toString()));
	}
	
	private void errorNoDec(StringLocalizado id) {
		errorCommon(id);
		System.out.println("  El identificador " + id + " no ha sido declarado anteriormente");
	}
	
	

	@Override
	public void procesa(Prog_sin_decs prog) {
		prog.insts().procesa(this);
	}

	@Override
	public void procesa(Prog_con_decs prog) {
		// Crea (si el nivel es mayor que 0) y pobla la tabla de símbolos
		prog.decs().procesa(this);
		prog.insts().procesa(this);
	}

	@Override
	public void procesa(Decs_una decs) {
		// Construye
		decs.dec().procesa(this);
	}

	@Override
	public void procesa(Decs_muchas decs) {
		// Construye
		decs.decs().procesa(this);
		decs.dec().procesa(this);
	}

	@Override
	public void procesa(Var var) {
		// Construye
		StringLocalizado id = var.var();
		if (_t_sim.contieneAct(id)) {
			errorDec(id);
		} else {
			_t_sim.put(id, var);
		}
	}

	@Override
	public void procesa(Type type) {
		// Construye
		StringLocalizado id = type.typeName();
		
		if (_t_sim.contieneAct(id)) {
			errorDec(id);
		} else {
			_t_sim.put(id, type);
		}
	}

	@Override
	public void procesa(Proc proc) {
		// Construye
		StringLocalizado id = proc.procName();
		
		if (_t_sim.contieneAct(id)) {
			errorDec(id);
		} else {
			_t_sim.put(id, proc);
			_t_sim.anida();
			proc.pfs().procesa(this);    // Construye
			proc.bloque().procesa(this); // Vincula
			_t_sim.desanida();
		}
	}

	@Override
	public void procesa(Param_f_sin param_f_sin) {
		// No hacemos nada
	}

	@Override
	public void procesa(Param_f_con_una param_f_con_una) {
		// Construye
		param_f_con_una.pf().procesa(this);
	}

	@Override
	public void procesa(Param_f_con_muchas param_f_con_muchas) {
		// Construye
		param_f_con_muchas.pfs().procesa(this);
		param_f_con_muchas.pf().procesa(this);
	}

	@Override
	public void procesa(Param_f_ref param_f_ref) {
		// Construye
		StringLocalizado id = param_f_ref.parametro();
		
		if (_t_sim.contieneAct(id)) {
			errorDec(id);
		} else {
			_t_sim.put(id, param_f_ref);
		}
	}

	@Override
	public void procesa(Param_f_noref param_f_noref) {
		// Construye
		StringLocalizado id = param_f_noref.parametro();
		
		if (_t_sim.contieneAct(id)) {
			errorDec(id);
		} else {
			_t_sim.put(id, param_f_noref);
		}
	}

	@Override
	public void procesa(Tipo_array tipo_array) {
		// Nada
	}

	@Override
	public void procesa(Tipo_record tipo_record) {
		// Nada
	}

	@Override
	public void procesa(Tipo_pointer tipo_pointer) {
		// Nada
	}

	@Override
	public void procesa(Tipo_iden tipo_iden) {
		// Nada
	}

	@Override
	public void procesa(Tipo_int tipo_int) {
		// Nada
	}

	@Override
	public void procesa(Tipo_real tipo_real) {
		// Nada
	}

	@Override
	public void procesa(Tipo_bool tipo_bool) {
		// Nada
	}

	@Override
	public void procesa(Tipo_string tipo_string) {
		// Nada
	}

	@Override
	public void procesa(Campos_uno campos_uno) {
		// Nada
	}

	@Override
	public void procesa(Campos_muchos campos_muchos) {
		// Nada
	}

	@Override
	public void procesa(Campo campo) {
		// Nada
	}

	@Override
	public void procesa(Insts_una insts_una) {
		// Vincula
		insts_una.inst().procesa(this);
	}

	@Override
	public void procesa(Insts_muchas insts_muchas) {
		// Vincula
		insts_muchas.insts().procesa(this);
		insts_muchas.inst().procesa(this);
	}

	@Override
	public void procesa(E_igual e_igual) {
		// Vincula
		e_igual.var().procesa(this);
		e_igual.val().procesa(this);
	}

	@Override
	public void procesa(If if_) {
		// Vincula
		if_.condicion().procesa(this);
		if_.pinst().procesa(this);
	}

	@Override
	public void procesa(Ifelse ifelse) {
		// Vincula
		ifelse.condicion().procesa(this);
		ifelse.pinst().procesa(this);
		ifelse.pinstelse().procesa(this);
	}

	@Override
	public void procesa(While while_) {
		// Vincula
		while_.condicion().procesa(this);
		while_.pinst().procesa(this);
	}

	@Override
	public void procesa(Read read) {
		// Vincula
		read.exp().procesa(this);
	}

	@Override
	public void procesa(Write write) {
		// Vincula
		write.exp().procesa(this);
	}

	@Override
	public void procesa(Nl nl) {
		// Nothing to do
	}

	@Override
	public void procesa(New new_) {
		// Vincula
		new_.exp().procesa(this);
	}

	@Override
	public void procesa(Delete delete) {
		// Vincula
		delete.exp().procesa(this);
	}

	@Override
	public void procesa(Call call) {
		// Vincula
		StringLocalizado id = call.procName();
		
		if (!_t_sim.contieneAny(id)) {
			errorNoDec(id);
		} else {
			call.vinculo = _t_sim.get(id).gen;
			call.arguments().procesa(this);
		}
	}

	@Override
	public void procesa(Bl bl) {
		// Vincula
		_t_sim.anida();
		bl.bloque().procesa(this);
		_t_sim.desanida();
	}

	@Override
	public void procesa(Lista_sin lista_sin) {
		// No hacemos nada
	}

	@Override
	public void procesa(Lista_con lista_con) {
		lista_con.insts().procesa(this);
	}

	@Override
	public void procesa(Param_r_sin param_r_sin) {
		// No hacemos nada
	}

	@Override
	public void procesa(Param_r_con_una param_r_con_una) {
		// Vincula
		param_r_con_una.param().procesa(this);
	}

	@Override
	public void procesa(Param_r_con_muchas param_r_con_muchas) {
		// Vincula
		param_r_con_muchas.pr().procesa(this);
		param_r_con_muchas.param().procesa(this);
	}

	@Override
	public void procesa(Bloque_sin bloque_sin) {
		// No hacemos nada
	}

	@Override
	public void procesa(Bloque_con bloque_con) {
		// Vincula
		bloque_con.prog().procesa(this);
	}

	@Override
	public void procesa(Entero entero) {
		// No hacemos nada
	}

	@Override
	public void procesa(Real real) {
		// No hacemos nada
	}

	@Override
	public void procesa(Cadena cadena) {
		// No hacemos nada
	}

	@Override
	public void procesa(Verdadero verdadero) {
		// No hacemos nada
	}

	@Override
	public void procesa(Falso falso) {
		// No hacemos nada
	}

	@Override
	public void procesa(Null null_) {
		// No hacemos nada
	}

	@Override
	public void procesa(Identificador identificador) {
		// Vinculacion
		StringLocalizado id = identificador.name();
		if (!_t_sim.contieneAny(id)) {
			errorNoDec(id);
		} else {
			identificador.vinculo = _t_sim.get(id).gen;
		}
	}

	@Override
	public void procesa(Suma suma) {
		// Vincula
		suma.arg0().procesa(this);
		suma.arg1().procesa(this);
	}

	@Override
	public void procesa(Resta resta) {
		// Vincula
		resta.arg0().procesa(this);
		resta.arg1().procesa(this);
	}

	@Override
	public void procesa(And and) {
		// Vincula
		and.arg0().procesa(this);
		and.arg1().procesa(this);
	}

	@Override
	public void procesa(Or or) {
		// Vincula
		or.arg0().procesa(this);
		or.arg1().procesa(this);
	}

	@Override
	public void procesa(Menor menor) {
		// Vincula
		menor.arg0().procesa(this);
		menor.arg1().procesa(this);
	}

	@Override
	public void procesa(Men_ig men_ig) {
		// Vincula
		men_ig.arg0().procesa(this);
		men_ig.arg1().procesa(this);
	}

	@Override
	public void procesa(Mayor mayor) {
		// Vincula
		mayor.arg0().procesa(this);
		mayor.arg1().procesa(this);
	}

	@Override
	public void procesa(May_ig may_ig) {
		// Vincula
		may_ig.arg0().procesa(this);
		may_ig.arg1().procesa(this);
	}

	@Override
	public void procesa(Igual igual) {
		// Vincula
		igual.arg0().procesa(this);
		igual.arg1().procesa(this);
	}

	@Override
	public void procesa(Desigual desigual) {
		// Vincula
		desigual.arg0().procesa(this);
		desigual.arg1().procesa(this);
	}

	@Override
	public void procesa(Mul mul) {
		// Vincula
		mul.arg0().procesa(this);
		mul.arg1().procesa(this);
	}

	@Override
	public void procesa(Div div) {
		// Vincula
		div.arg0().procesa(this);
		div.arg1().procesa(this);
	}

	@Override
	public void procesa(Modulo modulo) {
		// Vincula
		modulo.arg0().procesa(this);
		modulo.arg1().procesa(this);
	}

	@Override
	public void procesa(M_unario m_unario) {
		// Vincula
		m_unario.arg().procesa(this);
	}

	@Override
	public void procesa(Not not) {
		// Vincula
		not.arg().procesa(this);
	}

	@Override
	public void procesa(Indexacion indexacion) {
		// Vincula
		indexacion.arg0().procesa(this);
		indexacion.arg1().procesa(this);
	}

	@Override
	public void procesa(Acc_registro acc_registro) {
		acc_registro.registro().procesa(this);
	}

	@Override
	public void procesa(Acc_registro_indirecto acc_registro_in) {
		acc_registro_in.registro().procesa(this);
	}

	@Override
	public void procesa(Indireccion indireccion) {
		indireccion.arg().procesa(this);
	}

}
