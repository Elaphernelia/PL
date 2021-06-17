package asint;

import alex.StringLocalizado;
import procesamientos.Procesamiento;

public class TinyASint {
	/***************************
	 ** INFO PARA GEN. CODIGO **
	 ***************************/
	public static abstract class Genero {
		public DescTipo tipo;
	}
	
	/*************
	 ** GENEROS **
	 *************/

	public static abstract class Prog extends Genero {
		public abstract void procesa(Procesamiento p);
	}
	
	public static abstract class Exp extends Genero {
		public abstract int prioridad();
		public abstract void procesa(Procesamiento p);
	}
	
	public static abstract class Decs extends Genero {
		public abstract void procesa(Procesamiento p);
	}
	
	public static abstract class Dec extends Genero {
		public abstract void procesa(Procesamiento p);
	}
	
	public static abstract class Insts extends Genero {
		public abstract void procesa(Procesamiento p);
	}
	
	public static abstract class Inst extends Genero {
		public abstract void procesa(Procesamiento p);
	}
	
	public static abstract class Tipo extends Genero {
		public abstract void procesa(Procesamiento p);
	}
	
	public static abstract class PFs extends Genero {
		public abstract void procesa(Procesamiento p);
	}
	
	public static abstract class PF extends Genero {
		public abstract void procesa(Procesamiento p);
	}
	
	public static abstract class Campos extends Genero {
		public abstract void procesa(Procesamiento p);
	}
	
	public static class Campo {
		private Tipo _tipo;
		private StringLocalizado _identificador;
		
		public Campo(Tipo tipo, StringLocalizado identificador) {
			_tipo = tipo; _identificador = identificador;
		}
		
		public Tipo tipo() {
			return _tipo;
		}
		
		public StringLocalizado identificador() {
			return _identificador;
		}
		
		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
	}
	
	public static abstract class PR extends Genero {
		public abstract void procesa(Procesamiento p);
	}
	
	public static abstract class PInst extends Genero {
		public abstract void procesa(Procesamiento p);
	}
	
	public static abstract class Bloque extends Genero {
		public abstract void procesa(Procesamiento p);
	}
	
	/************
	 ** CLASES **
	 ************/
	
	public static class Prog_sin_decs extends Prog {
		private Insts _insts;
		
		public Prog_sin_decs(Insts insts) {
			_insts = insts;
		}

		public Insts insts() {
			return _insts;
		}
		
		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
	}
	
	public static class Prog_con_decs extends Prog {
		private Decs _decs;
		private Insts _insts;
		
		public Prog_con_decs(Decs decs, Insts insts) {
			_decs = decs;
			_insts = insts;
		}
		
		public Decs decs() {
			return _decs;
		}
		
		public Insts insts() {
			return _insts;
		}
		
		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
	}
	
	public static class Decs_una extends Decs {
		private Dec _dec;
		
		public Decs_una(Dec dec) {
			_dec = dec;
		}
		
		public Dec dec() {
			return _dec;
		}
		
		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
	}
	
	public static class Decs_muchas extends Decs {
		private Decs _decs;
		private Dec _dec;
		
		public Decs_muchas(Decs decs, Dec dec) {
			_decs = decs;
			_dec = dec;
		}
		
		public Decs decs() {
			return _decs;
		}
		
		public Dec dec() {
			return _dec;
		}
		
		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
	}
	
	public static class Var extends Dec {
		private Tipo _tipo;
		private StringLocalizado _var;
		
		public Var(Tipo tipo, StringLocalizado var) {
			_tipo = tipo;
			_var = var;
		}
		
		public Tipo tipo() {
			return _tipo;
		}
		
		public StringLocalizado var() {
			return _var;
		}
		
		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
	}
	
	public static class Type extends Dec {
		private Tipo _tipo;
		private StringLocalizado _type;
		
		public Type(Tipo tipo, StringLocalizado type) {
			_tipo = tipo;
			_type = type;
		}
		
		public Tipo tipo() {
			return _tipo;
		}
		
		public StringLocalizado typeName() {
			return _type;
		}
		
		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
	}
	
	public static class Proc extends Dec {
		private StringLocalizado _procName;
		private PFs _pfs;
		private Bloque _bloque;
		
		public Proc(StringLocalizado procName, PFs pfs, Bloque bloque) {
			_procName = procName;
			_pfs = pfs;
			_bloque = bloque;
		}
		
		public StringLocalizado procName() {
			return _procName;
		}
		
		public PFs pfs() {
			return _pfs;
		}
		
		public Bloque bloque() {
			return _bloque;
		}
		
		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
	}
	
	public static class Param_f_sin extends PFs {
		public Param_f_sin() {
			
		}
		
		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
	}
	
	public static class Param_f_con_una extends PFs {
		private PF _pf;
		
		public Param_f_con_una(PF pf) {
			_pf = pf;
		}
		
		public PF pf() {
			return _pf;
		}
		
		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
	}
	
	public static class Param_f_con_muchas extends PFs {
		private PFs _pfs;
		private PF _pf;
		
		public Param_f_con_muchas(PFs pfs, PF pf) {
			_pfs = pfs; _pf = pf;
		}
		
		public PFs pfs() {
			return _pfs;
		}
		
		public PF pf() {
			return _pf;
		}
		
		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
	}
	
	public static class Param_f_ref extends PF {
		private Tipo _tipo;
		private StringLocalizado _parametro;
		
		public Param_f_ref(Tipo tipo, StringLocalizado parametro) {
			_tipo = tipo; _parametro = parametro;
		}
		
		public Tipo tipo() {
			return _tipo;
		}
		
		public StringLocalizado parametro() {
			return _parametro;
		}
		
		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
	}
	
	public static class Param_f_noref extends PF {
		private Tipo _tipo;
		private StringLocalizado _parametro;
		
		public Param_f_noref(Tipo tipo, StringLocalizado parametro) {
			_tipo = tipo; _parametro = parametro;
		}
		
		public Tipo tipo() {
			return _tipo;
		}
		
		public StringLocalizado parametro() {
			return _parametro;
		}
		
		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
	}
	
	public static class Tipo_array extends Tipo {
		private StringLocalizado _tamanio;
		private Tipo _tipo;
		
		public Tipo_array(StringLocalizado tamanio, Tipo tipo) {
			_tamanio = tamanio; _tipo = tipo;
		}
		
		public StringLocalizado tamanio() {
			return _tamanio;
		}
		
		public Tipo tipo() {
			return _tipo;
		}
		
		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
	}
	
	public static class Tipo_record extends Tipo {
		private Campos _campos;
		
		public Tipo_record(Campos campos) {
			_campos = campos;
		}
		
		public Campos campos() {
			return _campos;
		}
		
		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
	}
	
	public static class Tipo_pointer extends Tipo {
		private Tipo _tipo;
		
		public Tipo_pointer(Tipo tipo) {
			_tipo = tipo;
		}
		
		public Tipo tipo() {
			return _tipo;
		}
		
		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
	}
	
	public static class Tipo_iden extends Tipo {
		private StringLocalizado _iden;
		
		public Tipo_iden(StringLocalizado iden) {
			_iden = iden;
		}
		
		public StringLocalizado iden() {
			return _iden;
		}
		
		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
	}
	
	public static class Tipo_int extends Tipo {
		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
	}
	
	public static class Tipo_real extends Tipo {
		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
	}
	
	public static class Tipo_bool extends Tipo {
		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
	}
	
	public static class Tipo_string extends Tipo {
		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
	}
	
	public static class Campos_uno extends Campos {
		private Campo _campo;
		
		public Campos_uno(Campo campo) {
			_campo = campo;
		}
		
		public Campo campo() {
			return _campo;
		}
		
		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
	}
	
	public static class Campos_muchos extends Campos {
		private Campos _campos;
		private Campo _campo;
		
		public Campos_muchos(Campos campos, Campo campo) {
			_campos = campos; _campo = campo;
		}
		
		public Campos campos() {
			return _campos;
		}
		
		public Campo campo() {
			return _campo;
		}
		
		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
	}
	
	public static class Insts_una extends Insts {
		private Inst _inst;
		
		public Insts_una(Inst inst) {
			_inst = inst;
		}
		
		public Inst inst() {
			return _inst;
		}
		
		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
	}
	
	public static class Insts_muchas extends Insts {
		private Insts _insts;
		private Inst _inst;
		
		public Insts_muchas(Insts insts, Inst inst) {
			_insts = insts; _inst = inst;
		}
		
		public Insts insts() {
			return _insts;
		}
		
		public Inst inst() {
			return _inst;
		}
		
		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
	}
	
	public static class E_igual extends Inst {
		private Exp _var;
		private Exp _val;
		
		public E_igual(Exp var, Exp val) {
			_var = var; _val = val;
		}
		
		public Exp var() {
			return _var;
		}
		
		public Exp val() {
			return _val;
		}
		
		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
	}
	
	public static class If extends Inst {
		private Exp _condicion;
		private PInst _pinst;
		
		public If(Exp condicion, PInst pinst) {
			_condicion = condicion; _pinst = pinst;
		}
		
		public Exp condicion() {
			return _condicion;
		}
		
		public PInst pinst() {
			return _pinst;
		}
		
		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
	}
	
	public static class Ifelse extends Inst {
		private Exp _condicion;
		private PInst _pinst;
		private PInst _pinstelse;
		
		public Ifelse(Exp condicion, PInst pinst, PInst pinstelse) {
			_condicion = condicion; _pinst = pinst; _pinstelse = pinstelse;
		}
		
		public Exp condicion() {
			return _condicion;
		}
		
		public PInst pinst() {
			return _pinst;
		}
		
		public PInst pinstelse() {
			return _pinstelse;
		}
		
		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
	}
	
	public static class While extends Inst {
		private Exp _condicion;
		private PInst _pinst;
		
		public While(Exp condicion, PInst pinst) {
			_condicion = condicion; _pinst = pinst;
		}
		
		public Exp condicion() {
			return _condicion;
		}
		
		public PInst pinst() {
			return _pinst;
		}
		
		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
	}
	
	public static class Read extends Inst {
		private Exp _exp;
		
		public Read(Exp exp) {
			_exp = exp;
		}
		
		public Exp exp() {
			return _exp;
		}
		
		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
	}
	
	public static class Write extends Inst {
		private Exp _exp;
		
		public Write(Exp exp) {
			_exp = exp;
		}
		
		public Exp exp() {
			return _exp;
		}
		
		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
	}
	
	public static class Nl extends Inst {
		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
	}
	
	public static class New extends Inst {
		private Exp _exp;
		
		public New(Exp exp) {
			_exp = exp;
		}
		
		public Exp exp() {
			return _exp;
		}
		
		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
	}
	
	public static class Delete extends Inst {
		private Exp _exp;
		
		public Delete(Exp exp) {
			_exp = exp;
		}
		
		public Exp exp() {
			return _exp;
		}
		
		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
	}
	
	public static class Call extends Inst {
		private StringLocalizado _procName;
		private PR _pr;
		public Genero vinculo;
		
		public Call(StringLocalizado procName, PR pr) {
			_procName = procName; _pr = pr;
		}
		
		public StringLocalizado procName() {
			return _procName;
		}
		
		public PR arguments() {
			return _pr;
		}
		
		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
	}
	
	public static class Bl extends Inst {
		private Bloque _bloque;
		
		public Bl(Bloque bloque) {
			_bloque = bloque;
		}
		
		public Bloque bloque() {
			return _bloque;
		}
		
		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
	}
	
	public static class Lista_sin extends PInst {
		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
	}
	
	public static class Lista_con extends PInst {
		private Insts _insts;
		
		public Lista_con(Insts insts) {
			_insts = insts;
		}
		
		public Insts insts() {
			return _insts;
		}
		
		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
	}
	
	public static class Param_r_sin extends PR {
		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
	}
	
	public static class Param_r_con_una extends PR {
		private Exp _param;
		
		public Param_r_con_una(Exp param) {
			_param = param;
		}
		
		public Exp param() {
			return _param;
		}
		
		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
	}
	
	public static class Param_r_con_muchas extends PR {
		private PR _pr;
		private Exp _param;
		
		public Param_r_con_muchas(PR pr, Exp param) {
			_pr = pr; _param = param;
		}
		
		public PR pr() {
			return _pr;
		}
		
		public Exp param() {
			return _param;
		}
		
		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
	}
	
	public static class Bloque_sin extends Bloque {
		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
	}
	
	public static class Bloque_con extends Bloque {
		private Prog _prog;
		
		public Bloque_con(Prog prog) {
			_prog = prog;
		}
		
		public Prog prog() {
			return _prog;
		}

		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
	}
	
	public static abstract class ExpLit extends Exp {
		private StringLocalizado _val;
		
		public ExpLit(StringLocalizado val) {
			_val = val;
		}
		
		public StringLocalizado val() {
			return _val;
		}
		
		@Override
		public int prioridad() {
			return 7;
		}
	}
	
	public static class Entero extends ExpLit {
		public Entero(StringLocalizado val) {
			super(val);
		}

		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
	}
	
	public static class Real extends ExpLit {
		public Real(StringLocalizado val) {
			super(val);
		}
		
		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
	}
	
	public static class Cadena extends ExpLit {
		public Cadena(StringLocalizado val) {
			super(val);
		}
		
		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
	}
	
	public static class Verdadero extends Exp {
		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);
		}

		@Override
		public int prioridad() {
			return 7;
		}
	}
	
	public static class Falso extends Exp {
		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
		
		@Override
		public int prioridad() {
			return 7;
		}
	}
	
	public static class Null extends Exp {
		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
		
		@Override
		public int prioridad() {
			return 7;
		}
	}
	
	public static class Identificador extends Exp {
		private StringLocalizado _name;
		public Genero vinculo;
		
		public Identificador(StringLocalizado name) {
			_name = name;
		}
		
		public StringLocalizado name() {
			return _name;
		}
		
		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);
		}

		@Override
		public int prioridad() {
			return 7;
		}
	}
	
	private static abstract class ExpBin extends Exp {
		private Exp _arg0;
		private Exp _arg1;
		
		public ExpBin(Exp arg0, Exp arg1) {
			_arg0 = arg0;
			_arg1 = arg1;
		}
		
		public Exp arg0() { return _arg0; }
		public Exp arg1() { return _arg1; }
	}
	
	public static class Suma extends ExpBin {
		public Suma(Exp arg0, Exp arg1) {
			super(arg0, arg1);
		}

		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
		
		@Override
		public int prioridad() {
			return 0;
		}
	}
	
	public static class Resta extends ExpBin {
		public Resta(Exp arg0, Exp arg1) {
			super(arg0, arg1);
		}

		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
		
		@Override
		public int prioridad() {
			return 0;
		}
	}
	
	public static class And extends ExpBin {
		public And(Exp arg0, Exp arg1) {
			super(arg0, arg1);
		}

		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
		
		@Override
		public int prioridad() {
			return 1;
		}
	}
	
	public static class Or extends ExpBin {
		public Or(Exp arg0, Exp arg1) {
			super(arg0, arg1);
		}

		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
		
		@Override
		public int prioridad() {
			return 1;
		}
	}
	
	public static class Menor extends ExpBin {
		public Menor(Exp arg0, Exp arg1) {
			super(arg0, arg1);
		}

		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
		
		@Override
		public int prioridad() {
			return 2;
		}
	}
	
	public static class Men_ig extends ExpBin {
		public Men_ig(Exp arg0, Exp arg1) {
			super(arg0, arg1);
		}

		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
		
		@Override
		public int prioridad() {
			return 2;
		}
	}
	
	public static class Mayor extends ExpBin {
		public Mayor(Exp arg0, Exp arg1) {
			super(arg0, arg1);
		}

		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
		
		@Override
		public int prioridad() {
			return 2;
		}
	}
	
	public static class May_ig extends ExpBin {
		public May_ig(Exp arg0, Exp arg1) {
			super(arg0, arg1);
		}

		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
		
		@Override
		public int prioridad() {
			return 2;
		}
	}
	
	public static class Igual extends ExpBin {
		public Igual(Exp arg0, Exp arg1) {
			super(arg0, arg1);
		}

		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
		
		@Override
		public int prioridad() {
			return 2;
		}
	}
	
	public static class Desigual extends ExpBin {
		public Desigual(Exp arg0, Exp arg1) {
			super(arg0, arg1);
		}

		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
		
		@Override
		public int prioridad() {
			return 2;
		}
	}
	
	public static class Mul extends ExpBin {
		public Mul(Exp arg0, Exp arg1) {
			super(arg0, arg1);
		}

		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
		
		@Override
		public int prioridad() {
			return 3;
		}
	}
	
	public static class Div extends ExpBin {
		public Div(Exp arg0, Exp arg1) {
			super(arg0, arg1);
		}

		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
		
		@Override
		public int prioridad() {
			return 3;
		}
	}
	
	public static class Modulo extends ExpBin {
		public Modulo(Exp arg0, Exp arg1) {
			super(arg0, arg1);
		}

		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
		
		@Override
		public int prioridad() {
			return 3;
		}
	}
	
	public static abstract class ExpUn extends Exp {
		private Exp _arg;
		
		public ExpUn(Exp arg) {
			_arg = arg;
		}
		
		public Exp arg() { return _arg; }
	}
	
	public static class M_unario extends ExpUn {
		public M_unario(Exp arg) {
			super(arg);
		}

		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
		
		@Override
		public int prioridad() {
			return 4;
		}
	}
	
	public static class Not extends ExpUn {
		public Not(Exp arg) {
			super(arg);
		}

		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
		
		@Override
		public int prioridad() {
			return 4;
		}
	}
	
	public static class Indexacion extends ExpBin {
		public Indexacion(Exp arg0, Exp arg1) {
			super(arg0, arg1);
		}

		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
		
		@Override
		public int prioridad() {
			return 5;
		}
	}
	
	public static class Acc_registro extends Exp {
		private Exp _registro;
		private StringLocalizado _campo;
		
		public Acc_registro(Exp registro, StringLocalizado campo) {
			_registro = registro; _campo = campo;
		}
		
		public Exp registro() {
			return _registro;
		}
		
		public StringLocalizado campo() {
			return _campo;
		}
		
		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
		
		@Override
		public int prioridad() {
			return 5;
		}
	}
	
	public static class Acc_registro_indirecto extends Exp {
		private Exp _registro;
		private StringLocalizado _campo;
		
		public Acc_registro_indirecto(Exp registro, StringLocalizado campo) {
			_registro = registro; _campo = campo;
		}
		
		public Exp registro() {
			return _registro;
		}
		
		public StringLocalizado campo() {
			return _campo;
		}
		
		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
		
		@Override
		public int prioridad() {
			return 5;
		}
	}
	
	public static class Indireccion extends ExpUn {
		public Indireccion(Exp arg) {
			super(arg);
		}

		@Override
		public void procesa(Procesamiento p) {
			p.procesa(this);
		}
		
		@Override
		public int prioridad() {
			return 6;
		}		
	}
	
	/*******************
	 ** CONSTRUCTORAS **
	 *******************/
	public Prog prog_sin_decs(Insts insts) {
		return new Prog_sin_decs(insts);
	}
	
	public Prog prog_con_decs(Decs decs, Insts insts) {
		return new Prog_con_decs(decs, insts);
	}
	
	public Decs decs_una(Dec dec) {
		return new Decs_una(dec);
	}
	
	public Decs decs_muchas(Decs decs, Dec dec) {
		return new Decs_muchas(decs, dec);
	}
	
	public Dec var(Tipo tipo, StringLocalizado string) {
		return new Var(tipo, string);
	}
	
	public Dec type(Tipo tipo, StringLocalizado string) {
		return new Type(tipo, string);
	}
	
	public Dec proc(StringLocalizado string, PFs pfs, Bloque bloque) {
		return new Proc(string, pfs, bloque);
	}
	
	public PFs param_f_sin() {
		return new Param_f_sin();
	}
	
	public PFs param_f_con_una(PF pf) {
		return new Param_f_con_una(pf);
	}
	
	public PFs param_f_con_muchas(PFs pfs, PF pf) {
		return new Param_f_con_muchas(pfs, pf);
	}
	
	public PF param_f_ref(Tipo tipo, StringLocalizado string) {
		return new Param_f_ref(tipo, string);
	}
	
	public PF param_f_noref(Tipo tipo, StringLocalizado string) {
		return new Param_f_noref(tipo, string);
	}
	
	public Tipo tipo_array(StringLocalizado exp, Tipo tipo) {
		return new Tipo_array(exp, tipo);
	}
	
	public Tipo tipo_record(Campos campos) {
		return new Tipo_record(campos);
	}
	
	public Tipo tipo_pointer(Tipo tipo) {
		return new Tipo_pointer(tipo);
	}
	
	public Tipo tipo_iden(StringLocalizado string) {
		return new Tipo_iden(string);
	}
	
	public Tipo tipo_int() {
		return new Tipo_int();
	}
	
	public Tipo tipo_real() {
		return new Tipo_real();
	}
	
	public Tipo tipo_bool() {
		return new Tipo_bool();
	}
	
	public Tipo tipo_string() {
		return new Tipo_string();
	}
	
	public Campos campos_uno(Campo campo) {
		return new Campos_uno(campo);
	}
	
	public Campos campos_muchos(Campos campos, Campo campo) {
		return new Campos_muchos(campos, campo);
	}
	
	public Campo campo(Tipo tipo, StringLocalizado string) {
		return new Campo(tipo, string);
	}
	
	public Insts insts_una(Inst inst) {
		return new Insts_una(inst);
	}
	
	public Insts insts_muchas(Insts insts, Inst inst) {
		return new Insts_muchas(insts, inst);
	}
	
	public Inst e_igual(Exp exp1, Exp exp2) {
		return new E_igual(exp1, exp2);
	}
	
	public Inst if_(Exp exp, PInst pinst) {
		return new If(exp, pinst);
	}
	
	public Inst ifelse(Exp exp, PInst pinst1, PInst pinst2) {
		return new Ifelse(exp, pinst1, pinst2);
	}
	
	public Inst while_(Exp exp, PInst pinst) {
		return new While(exp, pinst);
	}
	
	public Inst read(Exp exp) {
		return new Read(exp);
	}
	
	public Inst write(Exp exp) {
		return new Write(exp);
	}
	
	public Inst nl() {
		return new Nl();
	}
	
	public Inst new_(Exp exp) {
		return new New(exp);
	}
	
	public Inst delete(Exp exp) {
		return new Delete(exp);
	}
	
	public Inst call(StringLocalizado string, PR pr) {
		return new Call(string, pr);
	}
	
	public Inst bl(Bloque bloque) {
		return new Bl(bloque);
	}
	
	public PInst lista_sin() {
		return new Lista_sin();
	}
	
	public PInst lista_con(Insts insts) {
		return new Lista_con(insts);
	}
	
	public PR param_r_sin() {
		return new Param_r_sin();
	}
	
	public PR param_r_con_una(Exp exp) {
		return new Param_r_con_una(exp);
	}
	
	public PR param_r_con_muchas(PR pr, Exp exp) {
		return new Param_r_con_muchas(pr, exp);
	}
	
	public Bloque bloque_sin() {
		return new Bloque_sin();
	}
	
	public Bloque bloque_con(Prog prog) {
		return new Bloque_con(prog);
	}
	
	public Exp entero(StringLocalizado string) {
		return new Entero(string);
	}
	
	public Exp real(StringLocalizado string) {
		return new Real(string);
	}
	
	public Exp cadena(StringLocalizado string) {
		return new Cadena(string);
	}
	
	public Exp verdadero() {
		return new Verdadero();
	}
	
	public Exp falso() {
		return new Falso();
	}
	
	public Exp null_() {
		return new Null();
	}
	
	public Exp identificador(StringLocalizado string) {
		return new Identificador(string);
	}
	
	public Exp suma(Exp exp1, Exp exp2) {
		return new Suma(exp1, exp2);
	}
	
	public Exp resta(Exp exp1, Exp exp2) {
		return new Resta(exp1, exp2);
	}
	
	public Exp and(Exp exp1, Exp exp2) {
		return new And(exp1, exp2);
	}
	
	public Exp or(Exp exp1, Exp exp2) {
		return new Or(exp1, exp2);
	}
	
	public Exp menor(Exp exp1, Exp exp2) {
		return new Menor(exp1, exp2);
	}
	
	public Exp men_ig(Exp exp1, Exp exp2) {
		return new Men_ig(exp1, exp2);
	}
	
	public Exp mayor(Exp exp1, Exp exp2) {
		return new Mayor(exp1, exp2);
	}
	
	public Exp may_ig(Exp exp1, Exp exp2) {
		return new May_ig(exp1, exp2);
	}
	
	public Exp igual(Exp exp1, Exp exp2) {
		return new Igual(exp1, exp2);
	}
	
	public Exp desigual(Exp exp1, Exp exp2) {
		return new Desigual(exp1, exp2);
	}
	
	public Exp mul(Exp exp1, Exp exp2) {
		return new Mul(exp1, exp2);
	}
	
	public Exp div(Exp exp1, Exp exp2) {
		return new Div(exp1, exp2);
	}
	
	public Exp modulo(Exp exp1, Exp exp2) {
		return new Modulo(exp1, exp2);
	}
	
	public Exp m_unario(Exp exp) {
		return new M_unario(exp);
	}
	
	public Exp not(Exp exp) {
		return new Not(exp);
	}
	
	public Exp indexacion(Exp exp1, Exp exp2) {
		return new Indexacion(exp1, exp2);
	}
	
	public Exp acc_registro(Exp exp1, StringLocalizado string) {
		return new Acc_registro(exp1, string);
	}
	
	public Exp acc_registro_indirecto(Exp exp1, StringLocalizado string) {
		return new Acc_registro_indirecto(exp1, string);
	}
	
	public Exp indireccion(Exp exp) {
		return new Indireccion(exp);
	}
	
	public StringLocalizado str(String s, int fila, int col) {
		return new StringLocalizado(s,fila,col);
	}
}
