package procesamientos;

import asint.TinyASint.*;
import maquinaP.MaquinaP;
import procesamientos.ComprobacionTipos.*;

public class GeneraCodigo implements Procesamiento {
	private MaquinaP _p;
	
	public GeneraCodigo(MaquinaP p) {
		_p = p;
	}

	@Override
	public void procesa(Prog_sin_decs prog) {
		prog.insts().procesa(this);
	}

	@Override
	public void procesa(Prog_con_decs prog) {
		prog.decs().procesa(this); // Codigo de procedimientos
		prog.insts().procesa(this);
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
	}

	@Override
	public void procesa(Insts_muchas insts_muchas) {
		insts_muchas.insts().procesa(this);
		insts_muchas.inst().procesa(this);
	}

	@Override
	public void procesa(E_igual e_igual) {
		e_igual.var().procesa(this);
		e_igual.val().procesa(this);
		
		if (e_igual.val().esDesignador()) {
			_p.ponInstruccion(_p.mueve(e_igual.val().size));
		} else {
			_p.ponInstruccion(_p.desapilaInd());
		}
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
		read.exp().procesa(this);
		if (read.exp().getTipo() instanceof Tipo_Entero) {
			_p.ponInstruccion(_p.readInt());
		} else if (read.exp().getTipo() instanceof Tipo_Real) {
			_p.ponInstruccion(_p.readReal());
		} else if (read.exp().getTipo() instanceof Tipo_String) {
			_p.ponInstruccion(_p.readString());
		} else {
			throw new IllegalStateException("Hubo un error de tipos no capturado");
		}
		
		_p.ponInstruccion(_p.desapilaInd());
	}

	@Override
	public void procesa(Write write) {
		write.exp().procesa(this);
		if (write.exp().esDesignador()) {
			_p.ponInstruccion(_p.apilaInd());
		}
		if (write.exp().getTipo() instanceof Tipo_Entero) {
			_p.ponInstruccion(_p.writeInt());
		} else if (write.exp().getTipo() instanceof Tipo_Real) {
			_p.ponInstruccion(_p.writeReal());
		} else if (write.exp().getTipo() instanceof Tipo_Bool) {
			_p.ponInstruccion(_p.writeBool());
		} else if (write.exp().getTipo() instanceof Tipo_String) {
			_p.ponInstruccion(_p.writeString());
		} else {
			throw new IllegalStateException("Esto no debería pasar");
		}
	}

	@Override
	public void procesa(Nl nl) {
		_p.ponInstruccion(_p.apilaString("\n"));
		_p.ponInstruccion(_p.writeString());
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
		_p.ponInstruccion(_p.apilaInt(Integer.parseInt(entero.val().toString())));
	}

	@Override
	public void procesa(Real real) {
		_p.ponInstruccion(_p.apilaReal(Double.parseDouble(real.val().toString())));
	}

	@Override
	public void procesa(Cadena cadena) {
		_p.ponInstruccion(_p.apilaString(cadena.val().toString()));
	}

	@Override
	public void procesa(Verdadero verdadero) {
		_p.ponInstruccion(_p.apilaBool(true));
	}

	@Override
	public void procesa(Falso falso) {
		_p.ponInstruccion(_p.apilaBool(false));
	}

	@Override
	public void procesa(Null null_) {
		// TODO Auto-generated method stub

	}

	@Override
	public void procesa(Identificador identificador) {
		if (identificador.nivel == 0) {
			_p.ponInstruccion(_p.apilaInt(identificador.dir));
		} else {
			_p.ponInstruccion(_p.apilad(identificador.nivel));
			_p.ponInstruccion(_p.apilaInt(identificador.dir));
			_p.ponInstruccion(_p.suma());
		}
	}

	@Override
	public void procesa(Suma aop) {
		aop.arg0().procesa(this);
		if (aop.arg0().esDesignador()) _p.ponInstruccion(_p.apilaInd());
		aop.arg1().procesa(this);
		if (aop.arg1().esDesignador()) _p.ponInstruccion(_p.apilaInd());
		
		if (aop.getTipo() instanceof Tipo_Entero) {
			_p.ponInstruccion(_p.suma());
		} else if (aop.getTipo() instanceof Tipo_Real) {
			_p.ponInstruccion(_p.sumaReal());
		}
	}

	@Override
	public void procesa(Resta aop) {
		aop.arg0().procesa(this);
		if (aop.arg0().esDesignador()) _p.ponInstruccion(_p.apilaInd());
		aop.arg1().procesa(this);
		if (aop.arg1().esDesignador()) _p.ponInstruccion(_p.apilaInd());
		
		if (aop.getTipo() instanceof Tipo_Entero) {
			_p.ponInstruccion(_p.resta());
		} else if (aop.getTipo() instanceof Tipo_Real) {
			_p.ponInstruccion(_p.restaReal());
		}
	}

	@Override
	public void procesa(And bop) {
		bop.arg0().procesa(this);
		if (bop.arg0().esDesignador()) _p.ponInstruccion(_p.apilaInd());
		bop.arg1().procesa(this);
		if (bop.arg1().esDesignador()) _p.ponInstruccion(_p.apilaInd());
		_p.ponInstruccion(_p.and());
	}

	@Override
	public void procesa(Or bop) {
		bop.arg0().procesa(this);
		if (bop.arg0().esDesignador()) _p.ponInstruccion(_p.apilaInd());
		bop.arg1().procesa(this);
		if (bop.arg1().esDesignador()) _p.ponInstruccion(_p.apilaInd());
		_p.ponInstruccion(_p.or());
	}

	@Override
	public void procesa(Menor rop) {
		// Nota: En el caso de bool: a < b <-> !a ^ b
		
		rop.arg0().procesa(this);
		if (rop.arg0().esDesignador()) _p.ponInstruccion(_p.apilaInd());
		if (rop.arg0().getTipo() instanceof Tipo_Bool) _p.ponInstruccion(_p.not());
		rop.arg1().procesa(this);
		if (rop.arg1().esDesignador()) _p.ponInstruccion(_p.apilaInd());
		
		if (rop.arg0().getTipo().isNum()) {
			_p.ponInstruccion(_p.menorNum());
		} else if (rop.arg0().getTipo() instanceof Tipo_String) {
			_p.ponInstruccion(_p.menorString());
		} else if (rop.arg0().getTipo() instanceof Tipo_Bool){
			_p.ponInstruccion(_p.and());
		} else {
			throw new IllegalStateException("Hubo un error de tipos no detectado");
		}
	}

	@Override
	public void procesa(Men_ig rop) {
		// Nota: En el caso de bool: a <= b <-> !a v b
		
		rop.arg0().procesa(this);
		if (rop.arg0().esDesignador()) _p.ponInstruccion(_p.apilaInd());
		if (rop.arg0().getTipo() instanceof Tipo_Bool) _p.ponInstruccion(_p.not());
		rop.arg1().procesa(this);
		if (rop.arg1().esDesignador()) _p.ponInstruccion(_p.apilaInd());
		
		if (rop.arg0().getTipo().isNum()) {
			_p.ponInstruccion(_p.menorIgNum());
		} else if (rop.arg0().getTipo() instanceof Tipo_String) {
			_p.ponInstruccion(_p.menorIgString());
		} else if (rop.arg0().getTipo() instanceof Tipo_Bool){
			_p.ponInstruccion(_p.or());
		} else {
			throw new IllegalStateException("Hubo un error de tipos no detectado");
		}
	}

	@Override
	public void procesa(Mayor rop) {
		// a > b <-> b < a
	
		rop.arg1().procesa(this);
		if (rop.arg1().esDesignador()) _p.ponInstruccion(_p.apilaInd());
		if (rop.arg0().getTipo() instanceof Tipo_Bool) _p.ponInstruccion(_p.not());
		rop.arg0().procesa(this);
		if (rop.arg0().esDesignador()) _p.ponInstruccion(_p.apilaInd());
		
		if (rop.arg0().getTipo().isNum()) {
			_p.ponInstruccion(_p.menorNum());
		} else if (rop.arg0().getTipo() instanceof Tipo_String) {
			_p.ponInstruccion(_p.menorString());
		} else if (rop.arg0().getTipo() instanceof Tipo_Bool){
			_p.ponInstruccion(_p.and());
		} else {
			throw new IllegalStateException("Hubo un error de tipos no detectado");
		}
	}

	@Override
	public void procesa(May_ig rop) {
		// a >= b <-> b <= a

		rop.arg1().procesa(this);
		if (rop.arg1().esDesignador()) _p.ponInstruccion(_p.apilaInd());
		if (rop.arg0().getTipo() instanceof Tipo_Bool) _p.ponInstruccion(_p.not());
		rop.arg0().procesa(this);
		if (rop.arg0().esDesignador()) _p.ponInstruccion(_p.apilaInd());
		
		if (rop.arg0().getTipo().isNum()) {
			_p.ponInstruccion(_p.menorIgNum());
		} else if (rop.arg0().getTipo() instanceof Tipo_String) {
			_p.ponInstruccion(_p.menorIgString());
		} else if (rop.arg0().getTipo() instanceof Tipo_Bool){
			_p.ponInstruccion(_p.or());
		} else {
			throw new IllegalStateException("Hubo un error de tipos no detectado");
		}
	}

	@Override
	public void procesa(Igual rop) {
		// Nota: Con bool, a == b <-> a ^ b
		
		rop.arg0().procesa(this);
		if (rop.arg0().esDesignador()) _p.ponInstruccion(_p.apilaInd());
		rop.arg1().procesa(this);
		if (rop.arg1().esDesignador()) _p.ponInstruccion(_p.apilaInd());
		
		if (rop.arg0().getTipo().isNum()) {
			_p.ponInstruccion(_p.igualNum());
		} else if (rop.arg0().getTipo() instanceof Tipo_String) {
			_p.ponInstruccion(_p.igualString());
		} else if (rop.arg0().getTipo() instanceof Tipo_Bool) {
			_p.ponInstruccion(_p.and());
		}
	}

	@Override
	public void procesa(Desigual rop) {
		// Negamos la salida de Igual
		
		rop.arg0().procesa(this);
		if (rop.arg0().esDesignador()) _p.ponInstruccion(_p.apilaInd());
		rop.arg1().procesa(this);
		if (rop.arg1().esDesignador()) _p.ponInstruccion(_p.apilaInd());
		
		if (rop.arg0().getTipo().isNum()) {
			_p.ponInstruccion(_p.igualNum());
		} else if (rop.arg0().getTipo() instanceof Tipo_String) {
			_p.ponInstruccion(_p.igualString());
		} else if (rop.arg0().getTipo() instanceof Tipo_Bool) {
			_p.ponInstruccion(_p.and());
		}
	
		_p.ponInstruccion(_p.not());
	}

	@Override
	public void procesa(Mul aop) {
		aop.arg0().procesa(this);
		if (aop.arg0().esDesignador()) _p.ponInstruccion(_p.apilaInd());
		aop.arg1().procesa(this);
		if (aop.arg1().esDesignador()) _p.ponInstruccion(_p.apilaInd());
		
		if (aop.getTipo() instanceof Tipo_Entero) {
			_p.ponInstruccion(_p.mul());
		} else if (aop.getTipo() instanceof Tipo_Real) {
			_p.ponInstruccion(_p.mulReal());
		}
	}

	@Override
	public void procesa(Div aop) {
		aop.arg0().procesa(this);
		if (aop.arg0().esDesignador()) _p.ponInstruccion(_p.apilaInd());
		aop.arg1().procesa(this);
		if (aop.arg1().esDesignador()) _p.ponInstruccion(_p.apilaInd());
		
		if (aop.getTipo() instanceof Tipo_Entero) {
			_p.ponInstruccion(_p.div());
		} else if (aop.getTipo() instanceof Tipo_Real) {
			_p.ponInstruccion(_p.divReal());
		}
	}

	@Override
	public void procesa(Modulo aop) {
		aop.arg0().procesa(this);
		if (aop.arg0().esDesignador()) _p.ponInstruccion(_p.apilaInd());
		aop.arg1().procesa(this);
		if (aop.arg1().esDesignador()) _p.ponInstruccion(_p.apilaInd());
		
		_p.ponInstruccion(_p.modulo());
	}

	@Override
	public void procesa(M_unario m_unario) {
		if (m_unario.getTipo() instanceof Tipo_Entero) {
			_p.ponInstruccion(_p.apilaInt(0));
			m_unario.arg().procesa(this);
			if (m_unario.arg().esDesignador()) _p.ponInstruccion(_p.apilaInd());
			_p.ponInstruccion(_p.resta());
		} else if (m_unario.getTipo() instanceof Tipo_Real) {
			_p.ponInstruccion(_p.apilaReal(0));
			m_unario.arg().procesa(this);
			if (m_unario.arg().esDesignador()) _p.ponInstruccion(_p.apilaInd());
			_p.ponInstruccion(_p.restaReal());
		} else {
			throw new IllegalStateException("Hubo un error de tipos no detectado");
		}
	}

	@Override
	public void procesa(Not not) {
		not.arg().procesa(this);
		if (not.arg().esDesignador()) _p.ponInstruccion(_p.apilaInd());
		_p.ponInstruccion(_p.not());
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
