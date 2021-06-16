package procesamientos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import alex.StringLocalizado;
import asint.TinyASint.*;

public class Vinculacion implements Procesamiento {
	TablaSimbolos _t_sim;
	
	public Vinculacion() {
		_t_sim = new TablaSimbolos();
	}
	
	private class TablaSimbolos {
		private Map<String, DecInfo> _tabla_sim_act;       // la tabla de símbolos actual
		private Stack<Map<String, DecInfo>> _tablas_sim;   // Todas las tablas de símbolos
		private int _bloque_actual = 0;			       // indica el nivel de anidamiento del bloque
		
		public void put(StringLocalizado str, Genero gen) {
			_tabla_sim_act.put(str.toString(), new DecInfo(gen, str));
		}
		
		public boolean contains(String str) {
			for (Map<String, DecInfo> ts: _tablas_sim) {
				if (ts.containsKey(str)) return true;
			}
			
			return false;
		}
		
		public boolean contains(StringLocalizado str) {
			return contains(str.toString());
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
		
		public TablaSimbolos() {
			_bloque_actual = 0;
			_tabla_sim_act = new HashMap<String,DecInfo>();
			_tablas_sim = new Stack<Map<String, DecInfo>>();
			_tablas_sim.push(_tabla_sim_act);
		}
		
		private void anida() {
			_bloque_actual++;
			_tabla_sim_act = new HashMap<String,DecInfo>();
			_tablas_sim.push(_tabla_sim_act);
		}
		
		private void desanida() {
			_bloque_actual--;
			_tabla_sim_act = _tablas_sim.pop();
		}
	}
	
	// "struct" con la info para reportar errores mejor
	private class DecInfo {
		public int fila;
		public int col;
		public Genero gen;
		
		public DecInfo(Genero gen, StringLocalizado s) {
			this.gen = gen;
			this.fila = s.fila();
			this.col = s.col();
		}

		public String toString() {
			return Integer.toString(fila) + ":" + Integer.toString(col);
		}
	}
	
	private void errorDec(StringLocalizado id) {
		DecInfo di = _t_sim.get(id.toString());
		
		System.out.println("Error de vinculación en " + Integer.toString(id.fila()) + ":" + Integer.toString(id.col()));
		System.out.println("  El identificador " + id + " ya ha sido declarado previamente en:" + di);
	}
	
	private void errorNoDec(StringLocalizado id) {
		System.out.println("Error de vinculación en " + Integer.toString(id.fila()) + ":" + Integer.toString(id.col()));
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
		if (_t_sim.contains(id)) {
			errorDec(id);
		} else {
			_t_sim.put(id, var);
		}
	}

	@Override
	public void procesa(Type type) {
		// Construye
		StringLocalizado id = type.typeName();
		
		if (_t_sim.contains(id)) {
			errorDec(id);
		} else {
			_t_sim.put(id, type);
		}
	}

	@Override
	public void procesa(Proc proc) {
		// Construye
		StringLocalizado id = proc.procName();
		
		if (_t_sim.contains(id)) {
			errorDec(id);
		} else {
			_t_sim.put(id, proc);
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
		
		if (_t_sim.contains(id)) {
			errorDec(id);
		} else {
			_t_sim.put(id, param_f_ref);
		}
	}

	@Override
	public void procesa(Param_f_noref param_f_noref) {
		// Construye
		StringLocalizado id = param_f_noref.parametro();
		
		if (_t_sim.contains(id)) {
			errorDec(id);
		} else {
			_t_sim.put(id, param_f_noref);
		}
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
		
		if (!_t_sim.contains(id)) {
			errorNoDec(id);
		} else {
			call.vincula = _t_sim.get(id).gen;
		}
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
