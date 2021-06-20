package procesamientos;

import asint.TinyASint;
import asint.TinyASint.*;

public interface Procesamiento {
	default void procesa(Prog_sin_decs prog) {
		throw new UnsupportedOperationException();
	}
	
	default void procesa(Prog_con_decs prog) {
		throw new UnsupportedOperationException();
	}
	default void procesa(Decs_una dec) {
		throw new UnsupportedOperationException();
	}
	
	default void procesa(Decs_muchas dec) {
		throw new UnsupportedOperationException();
	}
	
	default void procesa(Var var) {
		throw new UnsupportedOperationException();
	}
	
	default void procesa(Type type) {
		throw new UnsupportedOperationException();
	}
	
	default void procesa(Proc proc) {
		throw new UnsupportedOperationException();
	}
	
	default void procesa(Param_f_sin param_f_sin) {
		throw new UnsupportedOperationException();
	}
	
	default void procesa(Param_f_con_una param_f_con_una) {
		throw new UnsupportedOperationException();
	}
	
	default void procesa(Param_f_con_muchas param_f_con_muchas) {
		throw new UnsupportedOperationException();
	}
	
	default void procesa(Param_f_ref param_f_ref) {
		throw new UnsupportedOperationException();
	}
	
	default void procesa(Param_f_noref param_f_noref) {
		throw new UnsupportedOperationException();
	}
	
	default void procesa(Tipo_array tipo_array) {
		throw new UnsupportedOperationException();
	}
	
	default void procesa(Tipo_record tipo_record) {
		throw new UnsupportedOperationException();
	}
	
	default void procesa(Tipo_pointer tipo_pointer) {
		throw new UnsupportedOperationException();
	}
	
	default void procesa(Tipo_iden tipo_iden) {
		throw new UnsupportedOperationException();
	}
	
	default void procesa(Tipo_int tipo_int) {
		throw new UnsupportedOperationException();
	}
	
	default void procesa(Tipo_real tipo_real) {
		throw new UnsupportedOperationException();
	}
	
	default void procesa(Tipo_bool tipo_bool) {
		throw new UnsupportedOperationException();
	}
	
	default void procesa(Tipo_string tipo_string) {
		throw new UnsupportedOperationException();
	}
	
	default void procesa(Campos_uno campos_uno) {
		throw new UnsupportedOperationException();
	}
	
	default void procesa(Campos_muchos campos_muchos) {
		throw new UnsupportedOperationException();
	}
	
	default void procesa(Campo campo) {
		throw new UnsupportedOperationException();
	}
	
	default void procesa(Insts_una insts_una) {
		throw new UnsupportedOperationException();
	}
	
	default void procesa(Insts_muchas insts_muchas) {
		throw new UnsupportedOperationException();
	}
	
	default void procesa(E_igual e_igual) {
		throw new UnsupportedOperationException();
	}
	
	default void procesa(If if_) {
		throw new UnsupportedOperationException();
	}
	
	default void procesa(Ifelse ifelse) {
		throw new UnsupportedOperationException();
	}
	
	default void procesa(While while_) {
		throw new UnsupportedOperationException();
	}
	
	default void procesa(Read read) {
		throw new UnsupportedOperationException();
	}
	
	default void procesa(Write write) {
		throw new UnsupportedOperationException();
	}
	
	default void procesa(Nl nl) {
		throw new UnsupportedOperationException();
	}
	
	default void procesa(New new_) {
		throw new UnsupportedOperationException();
	}
	
	default void procesa(Delete delete) {
		throw new UnsupportedOperationException();
	}
	
	default void procesa(Call call) {
		throw new UnsupportedOperationException();
	}
	
	default void procesa(Bl bl) {
		throw new UnsupportedOperationException();
	}
	
	default void procesa(Lista_sin lista_sin) {
		throw new UnsupportedOperationException();
	}
	
	default void procesa(Lista_con lista_con) {
		throw new UnsupportedOperationException();
	}
	
	default void procesa(Param_r_sin param_r_sin) {
		throw new UnsupportedOperationException();
	}
	
	default void procesa(Param_r_con_una param_r_con_una) {
		throw new UnsupportedOperationException();
	}
	
	default void procesa(Param_r_con_muchas param_r_con_muchas) {
		throw new UnsupportedOperationException();
	}
	
	default void procesa(Bloque_sin bloque_sin) {
		throw new UnsupportedOperationException();
	}
	
	default void procesa(Bloque_con bloque_con) {
		throw new UnsupportedOperationException();
	}
	
	default void procesa(Entero entero) {
		throw new UnsupportedOperationException();
	}
	
	default void procesa(Real real) {
		throw new UnsupportedOperationException();
	}
	
	default void procesa(Cadena cadena) {
		throw new UnsupportedOperationException();
	}
	
	default void procesa(Verdadero verdadero) {
		throw new UnsupportedOperationException();
	}
	
	default void procesa(Falso falso) {
		throw new UnsupportedOperationException();
	}
	
	default void procesa(Null null_) {
		throw new UnsupportedOperationException();
	}
	
	default void procesa(Identificador identificador) {
		throw new UnsupportedOperationException();
	}
	
	default void procesa(Suma suma) {
		throw new UnsupportedOperationException();
	}
	
	default void procesa(Resta resta) {
		throw new UnsupportedOperationException();
	}
	
	default void procesa(And and) {
		throw new UnsupportedOperationException();
	}
	
	default void procesa(Or or) {
		throw new UnsupportedOperationException();
	}
	
	default void procesa(Menor menor) {
		throw new UnsupportedOperationException();
	}
	
	default void procesa(Men_ig men_ig) {
		throw new UnsupportedOperationException();
	}
	
	default void procesa(Mayor mayor) {
		throw new UnsupportedOperationException();
	}
	
	default void procesa(May_ig may_ig) {
		throw new UnsupportedOperationException();
	}
	
	default void procesa(Igual igual) {
		throw new UnsupportedOperationException();
	}
	
	default void procesa(Desigual desigual) {
		throw new UnsupportedOperationException();
	}
	
	default void procesa(Mul mul) {
		throw new UnsupportedOperationException();
	}
	
	default void procesa(Div div) {
		throw new UnsupportedOperationException();
	}
	
	default void procesa(Modulo modulo) {
		throw new UnsupportedOperationException();
	}
	
	default void procesa(M_unario m_unario) {
		throw new UnsupportedOperationException();
	}
	
	default void procesa(Not not) {
		throw new UnsupportedOperationException();
	}
	
	default void procesa(Indexacion indexacion) {
		throw new UnsupportedOperationException();
	}
	
	default void procesa(Acc_registro acc_registro) {
		throw new UnsupportedOperationException();
	}
	
	default void procesa(Acc_registro_indirecto acc_registro_in) {
		throw new UnsupportedOperationException();
	}
	
	default void procesa(Indireccion indireccion) {
		throw new UnsupportedOperationException();
	}
	
}
