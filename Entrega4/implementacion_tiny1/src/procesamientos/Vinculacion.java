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
	private Map<String, DecInfo> _tabla_sim_act;       // la tabla de símbolos actual
	private Stack<Map<String, DecInfo>> _tablas_sim;   // Todas las tablas de símbolos
	private int _bloque_actual = 0;			       // indica el nivel de anidamiento del bloque
	
	// "struct" con la info para reportar errores mejor
	private class DecInfo {
		public int fila;
		public int col;
		public Dec dec;
		
		public DecInfo(Dec dec, StringLocalizado s) {
			this.dec = dec;
			this.fila = s.fila();
			this.col = s.col();
		}
		
		public DecInfo(Var var) {
			this(var, var.var());
		}
		
		public DecInfo(Type type) {
			this(type, type.typeName());
		}
		
		public DecInfo(Proc proc) {
			this(proc, proc.procName());
		}

		public String toString() {
			return Integer.toString(fila) + ":" + Integer.toString(col);
		}
	}
	
	public Vinculacion() {
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
	
	private void error(StringLocalizado id) {
		DecInfo di = _tabla_sim_act.get(id);
		
		System.out.println("Error de vinculación en " + Integer.toString(id.fila()) + ":" + Integer.toString(id.col()));
		System.out.print("  El identificador " + id + " ya ha sido declarado previamente en:" + di);
	}
	
	private void desanida() {
		_bloque_actual--;
		_tabla_sim_act = _tablas_sim.pop();
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
		if (_tabla_sim_act.containsKey(id.toString())) {
			error(id);
		} else {
			_tabla_sim_act.put(id.toString(), new DecInfo(var));
		}
	}

	@Override
	public void procesa(Type type) {
		// Construye
		StringLocalizado id = type.typeName();
		
		if (_tabla_sim_act.containsKey(id.toString())) {
			error(id);
		} else {
			_tabla_sim_act.put(id.toString(), new DecInfo(type));
		}
	}

	@Override
	public void procesa(Proc proc) {
		// Construye
		StringLocalizado id = proc.procName();
		
		if (_tabla_sim_act.containsKey(id.toString())) {
			error(id);
		} else {
			_tabla_sim_act.put(id.toString(), new DecInfo(proc));
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
		
		if (_tabla_sim_act.containsKey(id.toString())) {
			error(id);
		} else {
			_tabla_sim_act.put(id.toString(), new DecInfo(param_f_ref));
		}
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
		// TODO Auto-generated method stub

	}

	@Override
	public void procesa(Insts_muchas insts_muchas) {
		// TODO Auto-generated method stub

	}

	@Override
	public void procesa(E_igual e_igual) {
		// TODO Auto-generated methodtype stub

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
	public void procesa(Acc_registro_indirecto acc_registro_in) {
		// TODO Auto-generated method stub

	}

	@Override
	public void procesa(Indireccion indireccion) {
		// TODO Auto-generated method stub

	}

}
