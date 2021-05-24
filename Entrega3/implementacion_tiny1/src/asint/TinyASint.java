package asint;

public class TinyASint {
	public enum TNodo { 
		PROG_SIN_DECS, PROG_CON_DECS, DECS_UNA, DECS_MUCHAS, VAR, TYPE, PROC,
		PARAM_F_SIN, PARAM_F_CON_UNA, PARAM_F_CON_MUCHAS, PARAM_F_REF,
		PARAM_F_NOREF, TIPO_ARRAY, TIPO_RECORD, TIPO_POINTER, TIPO_IDEN,
		TIPO_INT, TIPO_REAL, TIPO_BOOL, TIPO_STRING, CAMPO_UNO, CAMPO_MUCHOS,
		CAMPO, INST_UNA, INST_MUCHAS, E_IGUAL, IF, IFELSE, WHILE, READ, WRITE,
		NL, NEW, DELETE, CALL, BL, LISTA_SIN, LISTA_CON, PARAM_R_SIN,
		PARAM_R_CON_UNA, PARAM_R_CON_MUCHAS, BLOQUE_SIN, BLOQUE_CON, ENTERO,
		REAL, CADENA, VERDADERO, FALSO, NULL, IDENTIFICADOR, SUMA, RESTA,
		AND, OR, MENOR, MEN_IG, MAYOR, MAY_IG, IGUAL, DESIGUAL, MUL, DIV,
		MODULO, M_UNARIO, NOT, INDEXACION, ACC_REGISTRO, INDIRECCION
	}
	
	public static abstract class Exp {
		public abstract TNodo tipo();
		public Exp arg0() {throw new UnsupportedOperationException("arg0");}
		public Exp arg1() {throw new UnsupportedOperationException("arg1");}
		public StringLocalizado id() {throw new UnsupportedOperationException("id");}
		public StringLocalizado real() {throw new UnsupportedOperationException("real");}
	}
	
	public static class StringLocalizado {
		private String _s;
		private int _fila;
		private int _col;
		
		public StringLocalizado(String s, int fila, int col) {
			_s = s;
			_fila = fila;
			_col = col;
		}
		
		public int fila() {
			return _fila;
		}
		
		public int col() {
			return _col;
		}
		
		@Override
		public String toString() {
			return _s;
		}
		
		@Override
		public boolean equals(Object o) {
			return (o == this) || (
				   (o instanceof StringLocalizado) &&
				   (((StringLocalizado) o)._s.equals(_s)));
		}
		
		@Override
		public int hashCode() {
			return _s.hashCode();
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
	
	public static class Mul extends ExpBin {
		public Mul(Exp arg0, Exp arg1) {
			super(arg0, arg1);
		}
		
		public TNodo tipo () {return TNodo.MUL;}
	}
	
	public static class Dec {
		private StringLocalizado _id;
		private StringLocalizado _val;
		
		public Dec(StringLocalizado id, StringLocalizado val) {
			_id = id;
			_val = val;
		}
		
		public StringLocalizado id() { return _id; }
		public StringLocalizado val() { return _val; }
	}
	
	public static abstract class Decs {
		public abstract TNodo tipo();
		public Decs decs() { throw new UnsupportedOperationException("decs"); }
		public Dec dec() { throw new UnsupportedOperationException("dec"); }
	}
	
	public static class Decs_una extends Decs {
		private Dec _dec;
		
		public Decs_una(Dec dec) {
			_dec = dec;
		}
		
		public TNodo tipo() { return TNodo.DECS_UNA; }
		public Dec dec() { return _dec; }
	}
	
	public static class Decs_muchas extends Decs {
		private Dec _dec;
		private Decs _decs;
		
		public Decs_muchas(Decs decs, Dec dec) {
			_dec = dec;
			_decs = decs;
		}
		
		public TNodo tipo() { return TNodo.DECS_MUCHAS; }
		public Dec dec() { return _dec; }
		public Decs decs() { return _decs; }
	}
	
	public static abstract class Prog {
		public abstract void procesa(Procesamiento proc);
	}
	
	public static class Prog_sin_decs extends Prog {
		private Insts _insts;
		
		public Prog_sin_decs(Insts insts) {
			_insts = insts;
		}
	}
	
	public static class Prog_con_decs extends Prog {
		private Decs _decs;
		private Insts _insts;
		
		public Prog_con_decs(Decs decs, Insts insts) {
			_decs = decs;
			_insts = insts;
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
	
	public Dec proc(StringLocalizado string, PFs pfs, Inst inst) {
		return new Proc(string, pfs, inst);
	}
	
	public PFs param_f_sin(PFs pfs) {
		return new Param_F_sin(pfs);
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
	
	public Tipo tipo_array(Exp exp, Tipo tipo) {
		return new Tipo_array(exp, tipo);
	}
	
	public Tipo tipo_record(Campos campos) {
		return new Tipo_record(campos);
	}
	
	public Tipo tipo_pointer(Tipo tipo) {
		return new Tipo_pointer(tipo);
	}
	
	public Tipo tipo_iden(SringLocalizado string) {
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
	
	public Campos campo_uno(Campo campo) {
		return new Campo_uno(campo);
	}
	
	public Campos campo_muchos(Campos campos, Campo campo) {
		return new Campo_muchos(campos, campo);
	}
	
	public Campo campo(Tipo tipo, StringLocalizado string) {
		return new Campo(tipo, string);
	}
	
	public Insts insts_una(Inst inst) {
		return new Insts_una(insts);
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
	
	public Inst call(StringLocalizada string, PR pr) {
		return new Call(string, pr);
	}
	
	public Inst bl(Bloque bloque) {
		return new bl(bloque);
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
	
	public Exp entero(StringLocalizada string) {
		return new Entero(string);
	}
	
	public Exp real(StringLocalizada string) {
		return new Real(string);
	}
	
	public Exp cadena(StringLocalizada string) {
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
	
	public Exp identificador(StringLocalizada string) {
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
		return new m_unario(exp);
	}
	
	public Exp not(Exp exp) {
		return new Not(exp);
	}
	
	public Exp indexacion(Exp exp1, Exp exp2) {
		return Indexacion(exp1, exp2);
	}
	
	public Exp acc_registro(Exp exp1, StringLocalizada string) {
		return new Acc_registro(exp1, string);
	}
	
	public Exp indireccion(Exp exp) {
		return new Indireccion(exp);
	}
}
