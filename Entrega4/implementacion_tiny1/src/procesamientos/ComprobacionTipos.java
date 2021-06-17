package procesamientos;

import java.util.Iterator;
import java.util.List;

import asint.TinyASint.*;

public class ComprobacionTipos implements Procesamiento {
	private boolean _dirty = false;
	
	public static abstract class TTipo {
		public boolean isNum() { return false; }
	}
	
	public static class Tipo_OK extends TTipo {
		
	}
	
	public static class Tipo_Error extends TTipo {
		
	}
	
	public static class Tipo_Bool extends TTipo {
		
	}
	
	public static class Tipo_Entero extends TTipo {
		@Override
		public boolean isNum() { return true; }
	}
	
	public static class Tipo_Real extends TTipo {
		@Override
		public boolean isNum() { return true; }
	}
	
	public static class Tipo_String extends TTipo {
		
	}
	
	public static class Tipo_Pointer extends TTipo {
		public TTipo of;
	}
	
	public static class Tipo_Null extends TTipo {
		
	}
	
	public static class Tipo_Array extends TTipo {
		public TTipo of;
		public int n;
	}
	
	public static class Tipo_Record extends TTipo {
		public List<TTipo> campos;
	}
	
	public ComprobacionTipos() {
		
	}
	
	public boolean isDirty() {
		return _dirty;
	}
	
	private void error(Genero g) {
		System.out.println("Error de tipos :(");
		_dirty = true;
		g.tipo = new Tipo_Error();
	}

	private TTipo compatibleNumero(TTipo t0, TTipo t1) {
		if (t0 instanceof Tipo_Entero && t1 instanceof Tipo_Entero) {
			return new Tipo_Entero();
		} else if ( t0 instanceof Tipo_Real && t1.isNum()) {
			return new Tipo_Real();
		}
		
		return new Tipo_Error();
	}
	
	private boolean compatiblePointer(TTipo t0, TTipo t1) {
		if (!(t0 instanceof Tipo_Pointer)) return false;
		
		return t1 instanceof Tipo_Null 
			|| (t1 instanceof Tipo_Pointer && compatible(((Tipo_Pointer)t0).of, ((Tipo_Pointer)t1).of));
	}
	
	private boolean compatibleArray(TTipo t0, TTipo t1) {
		return t0 instanceof Tipo_Array && t1 instanceof Tipo_Array
			&& compatible(((Tipo_Array)t0).of, ((Tipo_Array)t1).of);
	}
	
	private boolean compatibleRecord(TTipo t0, TTipo t1) {
		if (t0 instanceof Tipo_Record && t1 instanceof Tipo_Record) {
			Tipo_Record tr0 = (Tipo_Record)t0;
			Tipo_Record tr1 = (Tipo_Record)t1;
			
			if (tr0.campos.size() != tr1.campos.size()) return false;
			
			Iterator<TTipo> it0 = tr0.campos.iterator();
			Iterator<TTipo> it1 = tr1.campos.iterator();
			
			while (it0.hasNext() && it1.hasNext()) {
				if (!compatible(it0.next(),it1.next())) {
					return false;
				}
			}
			
			// Everything compatible
			return true;
		}
		
		return false;
	}
	
	private boolean compatibleCmp(TTipo t0, TTipo t1) {
		return (t0 instanceof Tipo_Bool && t1 instanceof Tipo_Bool)
			|| (t0 instanceof Tipo_String && t1 instanceof Tipo_String)
			|| (t0.isNum() && t1.isNum());
	}
	
	private boolean compatibleMismoBasico(TTipo t0, TTipo t1)  {
		return ((t0 instanceof Tipo_Bool && t1 instanceof Tipo_Bool)
			|| (t0 instanceof Tipo_String && t1 instanceof Tipo_String));
	}
	
	private boolean compatible(TTipo t0, TTipo t1) {
		return (compatibleMismoBasico(t0,t1))
				|| compatibleNumero(t0,t1).isNum()
				|| compatiblePointer(t0,t1)
				|| compatibleArray(t0,t1)
				|| compatibleRecord(t0,t1);
	}

	@Override
	public void procesa(Prog_sin_decs prog) {
		prog.insts().procesa(this);
		prog.tipo = prog.insts().tipo;
	}

	@Override
	public void procesa(Prog_con_decs prog) {
		prog.insts().procesa(this);
		prog.tipo = prog.insts().tipo;
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
		insts_una.inst().procesa(this);
		insts_una.tipo = insts_una.inst().tipo;
	}

	@Override
	public void procesa(Insts_muchas insts_muchas) {
		insts_muchas.insts().procesa(this);
		insts_muchas.inst().procesa(this);
		if (insts_muchas.insts().tipo instanceof Tipo_OK
			&& insts_muchas.inst().tipo instanceof Tipo_OK) {
			insts_muchas.tipo = new Tipo_OK();
		} else {
			error(insts_muchas);
		}
	}

	@Override
	public void procesa(E_igual e_igual) {
		e_igual.var().procesa(this);
		e_igual.val().procesa(this);
		if (e_igual.var().esDesignador() && compatible(e_igual.var().tipo, e_igual.val().tipo)) {
			e_igual.tipo = new Tipo_OK();
		} else {
			error(e_igual);
		}
	}

	@Override
	public void procesa(If if_) {
		if_.condicion().procesa(this);
		if_.pinst().procesa(this);
		
		if (if_.condicion().tipo instanceof Tipo_Bool && if_.pinst().tipo instanceof Tipo_OK) {
			if_.tipo = new Tipo_OK();
		} else {
			error(if_);
		}
	}

	@Override
	public void procesa(Ifelse ifelse) {
		ifelse.condicion().procesa(this);
		ifelse.pinst().procesa(this);
		ifelse.pinstelse().procesa(this);
		
		if (ifelse.condicion().tipo instanceof Tipo_Bool && ifelse.pinst().tipo instanceof Tipo_OK && ifelse.pinstelse().tipo instanceof Tipo_OK) {
			ifelse.tipo = new Tipo_OK();
		} else {
			error(ifelse);
		}
	}

	@Override
	public void procesa(While while_) {
		while_.condicion().procesa(this);
		while_.pinst().procesa(this);
		
		if (while_.condicion().tipo instanceof Tipo_Bool && while_.pinst().tipo instanceof Tipo_OK) {
			while_.tipo = new Tipo_OK();
		} else {
			error(while_);
		}
	}

	@Override
	public void procesa(Read read) {
		read.exp().procesa(this);
		Exp e = read.exp();
		
		if (e.esDesignador() && (e.tipo instanceof Tipo_Entero || e.tipo instanceof Tipo_Real || e.tipo instanceof Tipo_String)) {
			read.tipo = new Tipo_OK();
		} else {
			error(read);
		}
	}

	@Override
	public void procesa(Write write) {
		write.exp().procesa(this);
		Exp e = write.exp();
		
		if (e.esDesignador() && (e.tipo instanceof Tipo_Entero || e.tipo instanceof Tipo_Real || e.tipo instanceof Tipo_String || e.tipo instanceof Tipo_Bool)) {
			write.tipo = new Tipo_OK();
		} else {
			error(write);
		}
	}

	@Override
	public void procesa(Nl nl) {
		nl.tipo = new Tipo_OK();
	}

	@Override
	public void procesa(New new_) {
		new_.exp().procesa(this);
		
		if (new_.exp().tipo instanceof Tipo_Pointer) {
			new_.tipo = new Tipo_OK();
		} else {
			new_.tipo = new Tipo_Error();
		}
	}

	@Override
	public void procesa(Delete delete) {
		delete.exp().procesa(this);
		
		if (delete.exp().tipo instanceof Tipo_Pointer) {
			delete.tipo = new Tipo_OK();
		} else {
			delete.tipo = new Tipo_Error();
		}
	}

	@Override
	public void procesa(Call call) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void procesa(Bl bl) {
		bl.bloque().procesa(this);;
		bl.tipo = bl.bloque().tipo;
	}

	@Override
	public void procesa(Lista_sin lista_sin) {
		lista_sin.tipo = new Tipo_OK();
	}

	@Override
	public void procesa(Lista_con lista_con) {
		lista_con.insts().procesa(this);
		lista_con.tipo = lista_con.insts().tipo;
	}

	@Override
	public void procesa(Param_r_sin param_r_sin) {
		param_r_sin.tipo = new Tipo_OK();
	}

	@Override
	public void procesa(Param_r_con_una param_r_con_una) {
		param_r_con_una.param().procesa(this);
		param_r_con_una.tipo = param_r_con_una.param().tipo;
	}

	@Override
	public void procesa(Param_r_con_muchas param_r_con_muchas) {
		param_r_con_muchas.pr().procesa(this);
		param_r_con_muchas.param().procesa(this);
		if (param_r_con_muchas.pr().tipo instanceof Tipo_OK) {
			param_r_con_muchas.tipo = param_r_con_muchas.param().tipo;
		} else {
			error(param_r_con_muchas);
		}
	}

	@Override
	public void procesa(Bloque_sin bloque_sin) {
		bloque_sin.tipo = new Tipo_OK();
	}

	@Override
	public void procesa(Bloque_con bloque_con) {
		bloque_con.prog().procesa(this);
		bloque_con.tipo = bloque_con.prog().tipo;
	}

	@Override
	public void procesa(Entero entero) {
		entero.tipo = new Tipo_Entero();
	}

	@Override
	public void procesa(Real real) {
		real.tipo = new Tipo_Real();
	}

	@Override
	public void procesa(Cadena cadena) {
		cadena.tipo = new Tipo_String();
	}

	@Override
	public void procesa(Verdadero verdadero) {
		verdadero.tipo = new Tipo_Bool();
	}

	@Override
	public void procesa(Falso falso) {
		falso.tipo = new Tipo_Bool();
	}

	@Override
	public void procesa(Null null_) {
		null_.tipo = new Tipo_Null();
	}

	@Override
	public void procesa(Identificador identificador) {
		identificador.tipo = identificador.vinculo.tipo;
	}

	@Override
	public void procesa(Suma aop) {
		aop.arg0().procesa(this);
		aop.arg1().procesa(this);
		
		if (aop.arg0().tipo instanceof Tipo_Entero && aop.arg1().tipo instanceof Tipo_Entero) {
			aop.tipo = new Tipo_Entero();
		} else if (aop.arg0().tipo.isNum() && aop.arg1().tipo.isNum()) {
			aop.tipo = new Tipo_Real();
		} else {
			error(aop);
		}
	}

	@Override
	public void procesa(Resta aop) {
		aop.arg0().procesa(this);
		aop.arg1().procesa(this);
		
		if (aop.arg0().tipo instanceof Tipo_Entero && aop.arg1().tipo instanceof Tipo_Entero) {
			aop.tipo = new Tipo_Entero();
		} else if (aop.arg0().tipo.isNum() && aop.arg1().tipo.isNum()) {
			aop.tipo = new Tipo_Real();
		} else {
			error(aop);
		}
	}

	@Override
	public void procesa(And bop) {
		bop.arg0().procesa(this);
		bop.arg1().procesa(this);
		
		if (bop.arg0().tipo instanceof Tipo_Bool && bop.arg1().tipo instanceof Tipo_Bool) {
			bop.tipo = new Tipo_Bool();
		} else {
			error(bop);
		}
	}

	@Override
	public void procesa(Or bop) {
		bop.arg0().procesa(this);
		bop.arg1().procesa(this);
		
		if (bop.arg0().tipo instanceof Tipo_Bool && bop.arg1().tipo instanceof Tipo_Bool) {
			bop.tipo = new Tipo_Bool();
		} else {
			error(bop);
		}
	}

	@Override
	public void procesa(Menor rop) {
		rop.arg0().procesa(this);
		rop.arg1().procesa(this);

		if (compatibleCmp(rop.arg0().tipo, rop.arg1().tipo)) {
			rop.tipo = new Tipo_Bool();
		} else {
			error(rop);
		}
	}

	@Override
	public void procesa(Men_ig rop) {
		rop.arg0().procesa(this);
		rop.arg1().procesa(this);

		if (compatibleCmp(rop.arg0().tipo, rop.arg1().tipo)) {
			rop.tipo = new Tipo_Bool();
		} else {
			error(rop);
		}
	}

	@Override
	public void procesa(Mayor rop) {
		rop.arg0().procesa(this);
		rop.arg1().procesa(this);

		if (compatibleCmp(rop.arg0().tipo, rop.arg1().tipo)) {
			rop.tipo = new Tipo_Bool();
		} else {
			error(rop);
		}
	}

	@Override
	public void procesa(May_ig rop) {
		rop.arg0().procesa(this);
		rop.arg1().procesa(this);

		if (compatibleCmp(rop.arg0().tipo, rop.arg1().tipo)) {
			rop.tipo = new Tipo_Bool();
		} else {
			error(rop);
		}
	}

	@Override
	public void procesa(Igual rop) {
		rop.arg0().procesa(this);
		rop.arg1().procesa(this);

		if (compatibleCmp(rop.arg0().tipo, rop.arg1().tipo)) {
			rop.tipo = new Tipo_Bool();
		} else {
			error(rop);
		}
	}

	@Override
	public void procesa(Desigual rop) {
		rop.arg0().procesa(this);
		rop.arg1().procesa(this);

		if (compatibleCmp(rop.arg0().tipo, rop.arg1().tipo)) {
			rop.tipo = new Tipo_Bool();
		} else {
			error(rop);
		}
	}

	@Override
	public void procesa(Mul aop) {
		aop.arg0().procesa(this);
		aop.arg1().procesa(this);
		
		if (aop.arg0().tipo instanceof Tipo_Entero && aop.arg1().tipo instanceof Tipo_Entero) {
			aop.tipo = new Tipo_Entero();
		} else if (aop.arg0().tipo.isNum() && aop.arg1().tipo.isNum()) {
			aop.tipo = new Tipo_Real();
		} else {
			error(aop);
		}
	}

	@Override
	public void procesa(Div aop) {
		aop.arg0().procesa(this);
		aop.arg1().procesa(this);
		
		if (aop.arg0().tipo instanceof Tipo_Entero && aop.arg1().tipo instanceof Tipo_Entero) {
			aop.tipo = new Tipo_Entero();
		} else if (aop.arg0().tipo.isNum() && aop.arg1().tipo.isNum()) {
			aop.tipo = new Tipo_Real();
		} else {
			error(aop);
		}
	}

	@Override
	public void procesa(Modulo aop) {
		aop.arg0().procesa(this);
		aop.arg1().procesa(this);
		
		if (aop.arg0().tipo instanceof Tipo_Entero && aop.arg1().tipo instanceof Tipo_Entero) {
			aop.tipo = new Tipo_Entero();
		} else {
			error(aop);
		}
	}

	@Override
	public void procesa(M_unario m_unario) {
		m_unario.arg().procesa(this);
		
		if (m_unario.arg().tipo instanceof Tipo_Entero) {
			m_unario.tipo = new Tipo_Entero();
		} else if (m_unario.arg().tipo instanceof Tipo_Real) {
			m_unario.tipo = new Tipo_Real();
		} else {
			error(m_unario);
		}
	}

	@Override
	public void procesa(Not not) {
		not.arg().procesa(this);

		if (not.tipo instanceof Tipo_Bool) {
			not.tipo = new Tipo_Bool();
		} else {
			error(not);
		}
	}

	@Override
	public void procesa(Indexacion indexacion) {
		indexacion.arg0().procesa(this);
		indexacion.arg1().procesa(this);
		
		if (indexacion.arg1().tipo instanceof Tipo_Entero && indexacion.arg0().tipo instanceof Tipo_Array) {
			indexacion.tipo = ((Tipo_Array) indexacion.arg0().tipo).of;
		} else {
			error(indexacion);
		}
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
		indireccion.arg().procesa(this);
		
		if (indireccion.arg().tipo instanceof Tipo_Pointer) {
			indireccion.tipo = ((Tipo_Pointer)indireccion.arg().tipo).of;
		} else {
			error(indireccion);
		}
	}

}
