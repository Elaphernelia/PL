package procesamientos;

import asint.TinyASint.*;
import maquinaP.MaquinaP;
import procesamientos.ComprobacionTipos.TTipo_Record;
import procesamientos.ComprobacionTipos.TTipo_Pointer;

public class GeneraCodigo implements Procesamiento {
	private MaquinaP _p;
	
	public GeneraCodigo(MaquinaP p) {
		_p = p;
	}
	
	private void checkNinstsi(Genero g) {
		if (_p.ninsts() != g.etqi) {
			System.err.println("Warning: El numero de instrucciones no coincide con la etqi en "+g+"...");
			System.err.printf("  ninsts: %3d, etqs: %3d%n", _p.ninsts(), g.etqi);
		}
	}
	
	private void checkNinsts(Genero g) {
		if (_p.ninsts() != g.etqs) {
			System.err.println("Warning: El numero de instrucciones no coincide con la etqs en "+g+"...");
			System.err.printf("  ninsts: %3d, etqs: %3d%n", _p.ninsts(), g.etqs);
		}
	}

	@Override
	public void procesa(Prog_sin_decs prog) {
		prog.insts().procesa(this);
		checkNinsts(prog);
	}

	@Override
	public void procesa(Prog_con_decs prog) {
		prog.decs().procesa(this); // Gen. Codigo de procedimientos
		prog.insts().procesa(this);
		checkNinsts(prog);
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
		// No genera instrucciones
	}

	@Override
	public void procesa(Type type) {
		// No genera instrucciones

	}

	@Override
	public void procesa(Tipo_array tipo_array) {
		// No genera instrucciones

	}

	@Override
	public void procesa(Tipo_record tipo_record) {
		// No genera instrucciones

	}

	@Override
	public void procesa(Tipo_pointer tipo_pointer) {
		// No genera instrucciones

	}

	@Override
	public void procesa(Tipo_iden tipo_iden) {
		// No genera instrucciones

	}

	@Override
	public void procesa(Tipo_int tipo_int) {
		// No genera instrucciones

	}

	@Override
	public void procesa(Tipo_real tipo_real) {
		// No genera instrucciones

	}

	@Override
	public void procesa(Tipo_bool tipo_bool) {
		// No genera instrucciones

	}

	@Override
	public void procesa(Tipo_string tipo_string) {
		// No genera instrucciones

	}

	@Override
	public void procesa(Campos_uno campos_uno) {
		// No genera instrucciones

	}

	@Override
	public void procesa(Campos_muchos campos_muchos) {
		// No genera instrucciones

	}

	@Override
	public void procesa(Campo campo) {
		// No genera instrucciones

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
		checkNinstsi(e_igual);
		e_igual.var().procesa(this);
		e_igual.val().procesa(this);
		
		if (e_igual.val().esDesignador()) {
			_p.ponInstruccion(_p.mueve(e_igual.val().size));
		} else {
			_p.ponInstruccion(_p.desapilaInd());
		}
		
		checkNinsts(e_igual);
	}

	@Override
	public void procesa(If if_) {
		if_.condicion().procesa(this);
		_p.ponInstruccion(_p.irF(if_.etqs));
		if_.pinst().procesa(this);
	}

	@Override
	public void procesa(Ifelse ifelse) {
		ifelse.condicion().procesa(this);
		// Si no se cumple la condicion, vamos al inicio del else
		_p.ponInstruccion(_p.irF(ifelse.pinstelse().etqi));
		ifelse.pinst().procesa(this);
		// Cuando acabamos la parte del if, vamos al final del todo saltando el else
		_p.ponInstruccion(_p.irA(ifelse.etqs));
		ifelse.pinstelse().procesa(this);
	}

	@Override
	public void procesa(While while_) {
		checkNinstsi(while_);
		while_.condicion().procesa(this);
		// Si no se cumple la condicion, salimos del bucle
		_p.ponInstruccion(_p.irF(while_.etqs));
		while_.pinst().procesa(this);
		// Vamos al comienzo para comprobar si se cumple la condicion
		_p.ponInstruccion(_p.irA(while_.etqi));
		checkNinsts(while_);
	}

	@Override
	public void procesa(Read read) {
		read.exp().procesa(this);
		if (read.exp().getTipo().isEntero()) {
			_p.ponInstruccion(_p.readInt());
		} else if (read.exp().getTipo().isReal()) {
			_p.ponInstruccion(_p.readReal());
		} else if (read.exp().getTipo().isString()) {
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
		if (write.exp().getTipo().isEntero()) {
			_p.ponInstruccion(_p.writeInt());
		} else if (write.exp().getTipo().isReal()) {
			_p.ponInstruccion(_p.writeReal());
		} else if (write.exp().getTipo().isBool()) {
			_p.ponInstruccion(_p.writeBool());
		} else if (write.exp().getTipo().isString()) {
			_p.ponInstruccion(_p.writeString());
		} else {
			throw new IllegalStateException("Esto no deber??a pasar");
		}
	}

	@Override
	public void procesa(Nl nl) {
		_p.ponInstruccion(_p.apilaString("\n"));
		_p.ponInstruccion(_p.writeString());
	}

	@Override
	public void procesa(New new_) {
		new_.exp().procesa(this);
		_p.ponInstruccion(_p.alloc(new_.exp().basesize));
		_p.ponInstruccion(_p.desapilaInd());
	}

	@Override
	public void procesa(Delete delete) {
		delete.exp().procesa(this);
		_p.ponInstruccion(_p.dealloc(delete.exp().basesize));
	}

	@Override
	public void procesa(Bl bl) {
		bl.bloque().procesa(this);
	}

	@Override
	public void procesa(Lista_sin lista_sin) {
		// Nada por hacer
	}

	@Override
	public void procesa(Lista_con lista_con) {
		lista_con.insts().procesa(this);
	}

	@Override
	public void procesa(Bloque_sin bloque_sin) {
		// No generamos c??digo
	}

	@Override
	public void procesa(Bloque_con bloque_con) {
		bloque_con.prog().procesa(this);
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
		_p.ponInstruccion(_p.apilaInt(-1));
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
		
		if (aop.getTipo().isEntero()) {
			_p.ponInstruccion(_p.suma());
		} else if (aop.getTipo().isReal()) {
			_p.ponInstruccion(_p.sumaReal());
		}
	}

	@Override
	public void procesa(Resta aop) {
		aop.arg0().procesa(this);
		if (aop.arg0().esDesignador()) _p.ponInstruccion(_p.apilaInd());
		aop.arg1().procesa(this);
		if (aop.arg1().esDesignador()) _p.ponInstruccion(_p.apilaInd());
		
		if (aop.getTipo().isEntero()) {
			_p.ponInstruccion(_p.resta());
		} else if (aop.getTipo().isReal()) {
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
		checkNinstsi(rop);
		
		rop.arg0().procesa(this);
		if (rop.arg0().esDesignador()) _p.ponInstruccion(_p.apilaInd());
		if (rop.arg0().getTipo().isBool()) _p.ponInstruccion(_p.not());
		rop.arg1().procesa(this);
		if (rop.arg1().esDesignador()) _p.ponInstruccion(_p.apilaInd());
		
		if (rop.arg0().getTipo().isNum()) {
			_p.ponInstruccion(_p.menorNum());
		} else if (rop.arg0().getTipo().isString()) {
			_p.ponInstruccion(_p.menorString());
		} else if (rop.arg0().getTipo().isBool()){
			_p.ponInstruccion(_p.and());
		} else {
			throw new IllegalStateException("Hubo un error de tipos no detectado");
		}
		
		checkNinsts(rop);
	}

	@Override
	public void procesa(Men_ig rop) {
		// Nota: En el caso de bool: a <= b <-> !a v b
		
		rop.arg0().procesa(this);
		if (rop.arg0().esDesignador()) _p.ponInstruccion(_p.apilaInd());
		if (rop.arg0().getTipo().isBool()) _p.ponInstruccion(_p.not());
		rop.arg1().procesa(this);
		if (rop.arg1().esDesignador()) _p.ponInstruccion(_p.apilaInd());
		
		if (rop.arg0().getTipo().isNum()) {
			_p.ponInstruccion(_p.menorIgNum());
		} else if (rop.arg0().getTipo().isString()) {
			_p.ponInstruccion(_p.menorIgString());
		} else if (rop.arg0().getTipo().isBool()){
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
		if (rop.arg0().getTipo().isBool()) _p.ponInstruccion(_p.not());
		rop.arg0().procesa(this);
		if (rop.arg0().esDesignador()) _p.ponInstruccion(_p.apilaInd());
		
		if (rop.arg0().getTipo().isNum()) {
			_p.ponInstruccion(_p.menorNum());
		} else if (rop.arg0().getTipo().isString()) {
			_p.ponInstruccion(_p.menorString());
		} else if (rop.arg0().getTipo().isBool()){
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
		if (rop.arg0().getTipo().isBool()) _p.ponInstruccion(_p.not());
		rop.arg0().procesa(this);
		if (rop.arg0().esDesignador()) _p.ponInstruccion(_p.apilaInd());
		
		if (rop.arg0().getTipo().isNum()) {
			_p.ponInstruccion(_p.menorIgNum());
		} else if (rop.arg0().getTipo().isString()) {
			_p.ponInstruccion(_p.menorIgString());
		} else if (rop.arg0().getTipo().isBool()){
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
		} else if (rop.arg0().getTipo().isString()) {
			_p.ponInstruccion(_p.igualString());
		} else if (rop.arg0().getTipo().isBool()) {
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
		} else if (rop.arg0().getTipo().isString()) {
			_p.ponInstruccion(_p.igualString());
		} else if (rop.arg0().getTipo().isBool()) {
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
		
		if (aop.getTipo().isEntero()) {
			_p.ponInstruccion(_p.mul());
		} else if (aop.getTipo().isReal()) {
			_p.ponInstruccion(_p.mulReal());
		}
	}

	@Override
	public void procesa(Div aop) {
		aop.arg0().procesa(this);
		if (aop.arg0().esDesignador()) _p.ponInstruccion(_p.apilaInd());
		aop.arg1().procesa(this);
		if (aop.arg1().esDesignador()) _p.ponInstruccion(_p.apilaInd());
		
		if (aop.getTipo().isEntero()) {
			_p.ponInstruccion(_p.div());
		} else if (aop.getTipo().isReal()) {
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
		if (m_unario.getTipo().isEntero()) {
			_p.ponInstruccion(_p.apilaInt(0));
			m_unario.arg().procesa(this);
			if (m_unario.arg().esDesignador()) _p.ponInstruccion(_p.apilaInd());
			_p.ponInstruccion(_p.resta());
		} else if (m_unario.getTipo().isReal()) {
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
		indexacion.arg0().procesa(this);
		indexacion.arg1().procesa(this);
		if (indexacion.arg1().esDesignador()) _p.ponInstruccion(_p.apilaInd());
		_p.ponInstruccion(_p.apilaInt(indexacion.arg0().basesize));
		_p.ponInstruccion(_p.mul());
		_p.ponInstruccion(_p.suma());
	}

	@Override
	public void procesa(Acc_registro acc_registro) {
		acc_registro.registro().procesa(this);
		// Apilar desplazamiento del campo
		TTipo_Record tr = (TTipo_Record) acc_registro.registro().getTipo();
		int despl = tr.campos.get(acc_registro.campo().toString()).despl;
		_p.ponInstruccion(_p.apilaInt(despl));
		_p.ponInstruccion(_p.suma());
	}

	@Override
	public void procesa(Acc_registro_indirecto acc_registro_in) {
		acc_registro_in.registro().procesa(this);
		_p.ponInstruccion(_p.apilaInd());
		
		TTipo_Pointer p = (TTipo_Pointer) acc_registro_in.registro().getTipo();
		TTipo_Record tr = (TTipo_Record) p.of;
		int despl = tr.campos.get(acc_registro_in.campo().toString()).despl;
		_p.ponInstruccion(_p.apilaInt(despl));
		_p.ponInstruccion(_p.suma());
	}

	@Override
	public void procesa(Indireccion indireccion) {
		indireccion.arg().procesa(this);
		_p.ponInstruccion(_p.apilaInd());
	}

}
