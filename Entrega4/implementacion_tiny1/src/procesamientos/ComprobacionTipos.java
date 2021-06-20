package procesamientos;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import alex.StringLocalizado;
import asint.TinyASint.*;

public class ComprobacionTipos implements Procesamiento {
	private boolean _dirty = false;
	
	public static abstract class TTipo {
		public boolean isNum() { return false; }
		public boolean isOK() { return false; }
		public boolean isError() { return false; }
		public boolean isBool() { return false; }
		public boolean isEntero() { return false; }
		public boolean isReal() { return false; }
		public boolean isString() { return false; }
		public boolean isArray() { return false; }
		public boolean isRecord() { return false; }
		public boolean isRef() { return false; }
	}
	
	public static class TTipo_OK extends TTipo {
		public boolean isOK() { return true; }
	}
	
	public static class Tipo_Error extends TTipo {
		public boolean isError() { return true; }
	}
	
	public static class Tipo_Bool extends TTipo {
		public boolean isBool() { return true; }
	}
	
	public static class Tipo_Entero extends TTipo {
		public boolean isNum() { return true; }
		public boolean isEntero() { return true; }
	}
	
	public static class Tipo_Real extends TTipo {
		public boolean isNum() { return true; }
		public boolean isReal() { return true; }
	}
	
	public static class Tipo_String extends TTipo {
		public boolean isString() { return true; }
	}
	
	public static class Tipo_Null extends TTipo {
		
	}
	
	public static abstract class Tipo_Ref extends TTipo {
		public TTipo of;
		
		public Tipo_Ref(TTipo of) {
			this.of = of;
		}
		
		public boolean isRef() { return true; }
	}
	
	public static class Tipo_Pointer extends Tipo_Ref {
		public Tipo_Pointer(TTipo of) {
			super(of);
		}
	}
	
	public static class Tipo_Array extends Tipo_Ref {
		public int _n;
		public Tipo_Array(int n, TTipo of) {
			super(of);
			_n = n;
		}
		
		public boolean isArray() { return true; }
	}
	
	public static class TTipo_Record extends TTipo {
		public Map<String, Campo> campos;
		
		public TTipo_Record(Campo c) {
			campos = new HashMap<String, Campo>();
			campos.put(c.identificador().toString(), c);
		}
		
		public TTipo_Record(TTipo_Record r, Campo c) {
			campos = r.campos;
			campos.put(c.identificador().toString(), c);
		}
		
		public boolean isRecord() { return true; }
	}
	
	public boolean isDirty() {
		return _dirty;
	}
	
	private void error(Genero g) {
		System.out.println("Error de tipos en " + g);
		_dirty = true;
		g.setTipo(new Tipo_Error());
	}

	private TTipo compatibleNumero(TTipo t0, TTipo t1) {
		if (t0.isEntero() && t1.isEntero()) {
			return new Tipo_Entero();
		} else if ( t0.isReal() && t1.isNum()) {
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
		return t0.isArray() && t1.isArray()
			&& compatible(((Tipo_Array)t0).of, ((Tipo_Array)t1).of);
	}
	
	private boolean compatibleRecord(TTipo t0, TTipo t1) {
		if (t0.isRecord() && t1.isRecord()) {
			TTipo_Record tr0 = (TTipo_Record)t0;
			TTipo_Record tr1 = (TTipo_Record)t1;
			
			if (tr0.campos.size() != tr1.campos.size()) return false;
			
			Iterator<Campo> it0 = tr0.campos.values().iterator();
			Iterator<Campo> it1 = tr1.campos.values().iterator();
			
			while (it0.hasNext() && it1.hasNext()) {
				if (!compatible(it0.next().getTipo(),it1.next().getTipo())) {
					return false;
				}
			}
			
			// Everything compatible
			return true;
		}
		
		return false;
	}
	
	private boolean compatibleCmp(TTipo t0, TTipo t1) {
		return (t0.isBool() && t1.isBool())
			|| (t0.isString() && t1.isString())
			|| (t0.isNum() && t1.isNum());
	}
	
	private boolean compatibleMismoBasico(TTipo t0, TTipo t1)  {
		return ((t0.isBool() && t1.isBool())
			|| (t0.isString() && t1.isString()));
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
		prog.setTipo(prog.insts().getTipo());
	}

	@Override
	public void procesa(Prog_con_decs prog) {
		prog.decs().procesa(this);
		prog.insts().procesa(this);
		prog.setTipo(prog.insts().getTipo());
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
		var.tipo().procesa(this);
		var.setTipo(var.tipo().getTipo());
	}

	@Override
	public void procesa(Type type) {
		type.tipo().procesa(this);
	}

	@Override
	public void procesa(Proc proc) {
		proc.bloque().procesa(this);
		proc.setTipo(proc.bloque().getTipo());
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
		param_f_ref.setTipo(param_f_ref.tipo().getTipo());
	}

	@Override
	public void procesa(Param_f_noref param_f_noref) {
		param_f_noref.setTipo(param_f_noref.tipo().getTipo());
	}

	@Override
	public void procesa(Tipo_array tipo_array) {
		tipo_array.tipo().procesa(this);
		tipo_array.setTipo(new Tipo_Array(tipo_array.tamanioInt(), tipo_array.tipo().getTipo()));
	}

	@Override
	public void procesa(Tipo_record tipo_record) {
		tipo_record.campos().procesa(this);
		tipo_record.setTipo(tipo_record.campos().getTipo());
	}

	@Override
	public void procesa(Tipo_pointer tipo_pointer) {
		tipo_pointer.tipo().procesa(this);
		tipo_pointer.setTipo(tipo_pointer.tipo().getTipo());
	}

	@Override
	public void procesa(Tipo_iden tipo_iden) {
		tipo_iden.setTipo(tipo_iden.getVinculo().getTipo());
	}

	@Override
	public void procesa(Tipo_int tipo_int) {
		tipo_int.setTipo(new Tipo_Entero());
	}

	@Override
	public void procesa(Tipo_real tipo_real) {
		tipo_real.setTipo(new Tipo_Real());
	}

	@Override
	public void procesa(Tipo_bool tipo_bool) {
		tipo_bool.setTipo(new Tipo_Bool());
	}

	@Override
	public void procesa(Tipo_string tipo_string) {
		tipo_string.setTipo(new Tipo_String());
	}

	@Override
	public void procesa(Campos_uno campos_uno) {
		campos_uno.campo().procesa(this);
		campos_uno.setRecord(new TTipo_Record(campos_uno.campo()));
	}

	@Override
	public void procesa(Campos_muchos campos_muchos) {
		campos_muchos.campos().procesa(this);
		campos_muchos.campo().procesa(this);
		campos_muchos.setRecord(new TTipo_Record(campos_muchos.campos().getRecord(), campos_muchos.campo()));
	}

	@Override
	public void procesa(Campo campo) {
		campo.tipo().procesa(this);
		campo.setTipo(campo.tipo().getTipo());
	}

	@Override
	public void procesa(Insts_una insts_una) {
		insts_una.inst().procesa(this);
		insts_una.setTipo(insts_una.inst().getTipo());
	}

	@Override
	public void procesa(Insts_muchas insts_muchas) {
		insts_muchas.insts().procesa(this);
		insts_muchas.inst().procesa(this);
		if (insts_muchas.insts().getTipo().isOK()
			&& insts_muchas.inst().getTipo().isOK()) {
			insts_muchas.setTipo(new TTipo_OK());
			
		} else {
			error(insts_muchas);
		}
	}

	@Override
	public void procesa(E_igual e_igual) {
		e_igual.var().procesa(this);
		e_igual.val().procesa(this);
		
		if (e_igual.var().esDesignador()) {
			if (compatible(e_igual.var().getTipo(), e_igual.val().getTipo())) {
				e_igual.setTipo(new TTipo_OK());
			} else {
				error(e_igual);
			}	
		} else {
			error(e_igual);
			System.out.println("La parte izquierda no es un designador");
		}
	}

	@Override
	public void procesa(If if_) {
		if_.condicion().procesa(this);
		if_.pinst().procesa(this);
		
		if (if_.condicion().getTipo().isBool() && if_.pinst().getTipo().isOK()) {
			if_.setTipo(new TTipo_OK());
		} else {
			error(if_);
		}
	}

	@Override
	public void procesa(Ifelse ifelse) {
		ifelse.condicion().procesa(this);
		ifelse.pinst().procesa(this);
		ifelse.pinstelse().procesa(this);
		
		if (ifelse.condicion().getTipo().isBool() && ifelse.pinst().getTipo().isOK() && ifelse.pinstelse().getTipo().isOK()) {
			ifelse.setTipo(new TTipo_OK());
		} else {
			error(ifelse);
		}
	}

	@Override
	public void procesa(While while_) {
		while_.condicion().procesa(this);
		while_.pinst().procesa(this);
		
		if (while_.condicion().getTipo().isBool() && while_.pinst().getTipo().isOK()) {
			while_.setTipo(new TTipo_OK());
		} else {
			error(while_);
		}
	}

	@Override
	public void procesa(Read read) {
		read.exp().procesa(this);
		Exp e = read.exp();
		
		if (e.esDesignador() && (e.getTipo().isEntero() || e.getTipo().isReal() || e.getTipo().isString())) {
			read.setTipo(new TTipo_OK());
		} else {
			error(read);
		}
	}

	@Override
	public void procesa(Write write) {
		write.exp().procesa(this);
		Exp e = write.exp();
		
		if (e.getTipo().isEntero() || e.getTipo().isReal() || e.getTipo().isString() || e.getTipo().isBool()) {
			write.setTipo(new TTipo_OK());
		} else {
			error(write);
		}
	}

	@Override
	public void procesa(Nl nl) {
		nl.setTipo(new TTipo_OK());
	}

	@Override
	public void procesa(New new_) {
		new_.exp().procesa(this);
		
		if (new_.exp().getTipo() instanceof Tipo_Pointer) {
			new_.setTipo(new TTipo_OK());
		} else {
			error(new_);
		}
	}

	@Override
	public void procesa(Delete delete) {
		delete.exp().procesa(this);
		
		if (delete.exp().getTipo() instanceof Tipo_Pointer) {
			delete.setTipo(new TTipo_OK());
		} else {
			error(delete);
		}
	}

	@Override
	public void procesa(Call call) {
		// TODO this
	}

	@Override
	public void procesa(Bl bl) {
		bl.bloque().procesa(this);;
		bl.setTipo(bl.bloque().getTipo());
	}

	@Override
	public void procesa(Lista_sin lista_sin) {
		lista_sin.setTipo(new TTipo_OK());
	}

	@Override
	public void procesa(Lista_con lista_con) {
		lista_con.insts().procesa(this);
		lista_con.setTipo(lista_con.insts().getTipo());
	}

	@Override
	public void procesa(Param_r_sin param_r_sin) {
		param_r_sin.setTipo(new TTipo_OK());
	}

	@Override
	public void procesa(Param_r_con_una param_r_con_una) {
		param_r_con_una.param().procesa(this);
		param_r_con_una.setTipo(param_r_con_una.param().getTipo());
	}

	@Override
	public void procesa(Param_r_con_muchas param_r_con_muchas) {
		param_r_con_muchas.pr().procesa(this);
		param_r_con_muchas.param().procesa(this);
		if (param_r_con_muchas.pr().getTipo().isOK()) {
			param_r_con_muchas.setTipo(param_r_con_muchas.param().getTipo());
		} else {
			error(param_r_con_muchas);
		}
	}

	@Override
	public void procesa(Bloque_sin bloque_sin) {
		bloque_sin.setTipo(new TTipo_OK());
	}

	@Override
	public void procesa(Bloque_con bloque_con) {
		bloque_con.prog().procesa(this);
		bloque_con.setTipo(bloque_con.prog().getTipo());
	}

	@Override
	public void procesa(Entero entero) {
		entero.setTipo(new Tipo_Entero());
	}

	@Override
	public void procesa(Real real) {
		real.setTipo(new Tipo_Real());
	}

	@Override
	public void procesa(Cadena cadena) {
		cadena.setTipo(new Tipo_String());
	}

	@Override
	public void procesa(Verdadero verdadero) {
		verdadero.setTipo(new Tipo_Bool());
	}

	@Override
	public void procesa(Falso falso) {
		falso.setTipo(new Tipo_Bool());
	}

	@Override
	public void procesa(Null null_) {
		null_.setTipo(new Tipo_Null());
	}

	@Override
	public void procesa(Identificador identificador) {
		identificador.setTipo(identificador.getVinculo().tipo().getTipo());
	}

	@Override
	public void procesa(Suma aop) {
		aop.arg0().procesa(this);
		aop.arg1().procesa(this);
		
		if (aop.arg0().getTipo().isEntero() && aop.arg1().getTipo().isEntero()) {
			aop.setTipo(new Tipo_Entero());
		} else if (aop.arg0().getTipo().isNum() && aop.arg1().getTipo().isNum()) {
			aop.setTipo(new Tipo_Real());
		} else {
			error(aop);
		}
	}

	@Override
	public void procesa(Resta aop) {
		aop.arg0().procesa(this);
		aop.arg1().procesa(this);
		
		if (aop.arg0().getTipo().isEntero() && aop.arg1().getTipo().isEntero()) {
			aop.setTipo(new Tipo_Entero());
		} else if (aop.arg0().getTipo().isNum() && aop.arg1().getTipo().isNum()) {
			aop.setTipo(new Tipo_Real());
		} else {
			error(aop);
		}
	}

	@Override
	public void procesa(And bop) {
		bop.arg0().procesa(this);
		bop.arg1().procesa(this);
		
		if (bop.arg0().getTipo().isBool() && bop.arg1().getTipo().isBool()) {
			bop.setTipo(new Tipo_Bool());
		} else {
			error(bop);
		}
	}

	@Override
	public void procesa(Or bop) {
		bop.arg0().procesa(this);
		bop.arg1().procesa(this);
		
		if (bop.arg0().getTipo().isBool() && bop.arg1().getTipo().isBool()) {
			bop.setTipo(new Tipo_Bool());
		} else {
			error(bop);
		}
	}

	@Override
	public void procesa(Menor rop) {
		rop.arg0().procesa(this);
		rop.arg1().procesa(this);

		if (compatibleCmp(rop.arg0().getTipo(), rop.arg1().getTipo())) {
			rop.setTipo(new Tipo_Bool());
		} else {
			error(rop);
		}
	}

	@Override
	public void procesa(Men_ig rop) {
		rop.arg0().procesa(this);
		rop.arg1().procesa(this);

		if (compatibleCmp(rop.arg0().getTipo(), rop.arg1().getTipo())) {
			rop.setTipo(new Tipo_Bool());
		} else {
			error(rop);
		}
	}

	@Override
	public void procesa(Mayor rop) {
		rop.arg0().procesa(this);
		rop.arg1().procesa(this);

		if (compatibleCmp(rop.arg0().getTipo(), rop.arg1().getTipo())) {
			rop.setTipo(new Tipo_Bool());
		} else {
			error(rop);
		}
	}

	@Override
	public void procesa(May_ig rop) {
		rop.arg0().procesa(this);
		rop.arg1().procesa(this);

		if (compatibleCmp(rop.arg0().getTipo(), rop.arg1().getTipo())) {
			rop.setTipo(new Tipo_Bool());
		} else {
			error(rop);
		}
	}

	@Override
	public void procesa(Igual rop) {
		rop.arg0().procesa(this);
		rop.arg1().procesa(this);

		if (compatibleCmp(rop.arg0().getTipo(), rop.arg1().getTipo())) {
			rop.setTipo(new Tipo_Bool());
		} else {
			error(rop);
		}
	}

	@Override
	public void procesa(Desigual rop) {
		rop.arg0().procesa(this);
		rop.arg1().procesa(this);

		if (compatibleCmp(rop.arg0().getTipo(), rop.arg1().getTipo())) {
			rop.setTipo(new Tipo_Bool());
		} else {
			error(rop);
		}
	}

	@Override
	public void procesa(Mul aop) {
		aop.arg0().procesa(this);
		aop.arg1().procesa(this);
		
		if (aop.arg0().getTipo().isEntero() && aop.arg1().getTipo().isEntero()) {
			aop.setTipo(new Tipo_Entero());
		} else if (aop.arg0().getTipo().isNum() && aop.arg1().getTipo().isNum()) {
			aop.setTipo(new Tipo_Real());
		} else {
			error(aop);
		}
	}

	@Override
	public void procesa(Div aop) {
		aop.arg0().procesa(this);
		aop.arg1().procesa(this);
		
		if (aop.arg0().getTipo().isEntero() && aop.arg1().getTipo().isEntero()) {
			aop.setTipo(new Tipo_Entero());
		} else if (aop.arg0().getTipo().isNum() && aop.arg1().getTipo().isNum()) {
			aop.setTipo(new Tipo_Real());
		} else {
			error(aop);
		}
	}

	@Override
	public void procesa(Modulo aop) {
		aop.arg0().procesa(this);
		aop.arg1().procesa(this);
		
		if (aop.arg0().getTipo().isEntero() && aop.arg1().getTipo().isEntero()) {
			aop.setTipo(new Tipo_Entero());
		} else {
			error(aop);
		}
	}

	@Override
	public void procesa(M_unario m_unario) {
		m_unario.arg().procesa(this);
		
		if (m_unario.arg().getTipo().isEntero()) {
			m_unario.setTipo(new Tipo_Entero());
		} else if (m_unario.arg().getTipo().isReal()) {
			m_unario.setTipo(new Tipo_Real());
		} else {
			error(m_unario);
		}
	}

	@Override
	public void procesa(Not not) {
		not.arg().procesa(this);

		if (not.getTipo().isBool()) {
			not.setTipo(new Tipo_Bool());
		} else {
			error(not);
		}
	}

	@Override
	public void procesa(Indexacion indexacion) {
		indexacion.arg0().procesa(this);
		indexacion.arg1().procesa(this);
		
		if (indexacion.arg1().getTipo().isEntero() && indexacion.arg0().getTipo().isArray()) {
			indexacion.setTipo(((Tipo_Ref) indexacion.arg0().getTipo()).of);
		} else {
			error(indexacion);
		}
	}

	@Override
	public void procesa(Acc_registro acc_registro) {
		// TODO Auto-generated method stub
		acc_registro.registro().procesa(this);
		if (acc_registro.registro().getTipo().isRecord()) {
			TTipo_Record r = (TTipo_Record) acc_registro.registro().getTipo();
			acc_registro.setTipo(r.campos.get(acc_registro.campo().toString()).getTipo());
		} else {
			error(acc_registro);
		}
	}

	@Override
	public void procesa(Acc_registro_indirecto acc_registro_in) {
		// TODO Auto-generated method stub
		acc_registro_in.registro().procesa(this);
		if (acc_registro_in.registro().getTipo().isRecord()) {
			TTipo_Record r = (TTipo_Record) acc_registro_in.registro().getTipo();
			acc_registro_in.setTipo(r.campos.get(acc_registro_in.campo().toString()).getTipo());
		} else {
			error(acc_registro_in);
		}
	}

	@Override
	public void procesa(Indireccion indireccion) {
		indireccion.arg().procesa(this);
		
		if (indireccion.arg().getTipo() instanceof Tipo_Pointer) {
			indireccion.setTipo(((Tipo_Pointer)indireccion.arg().getTipo()).of);
		} else {
			error(indireccion);
		}
	}

}
