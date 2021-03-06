package procesamientos;

import asint.TinyASint;
import asint.TinyASint.*;

public interface Procesamiento {
	void procesa(Prog_sin_decs prog);
	void procesa(Prog_con_decs prog);
	void procesa(Decs_una dec);
	void procesa(Decs_muchas dec);
	void procesa(Var var);
	void procesa(Type type);
	void procesa(Proc proc);
	void procesa(Param_f_sin param_f_sin);
	void procesa(Param_f_con_una param_f_con_una);
	void procesa(Param_f_con_muchas param_f_con_muchas);
	void procesa(Param_f_ref param_f_ref);
	void procesa(Param_f_noref param_f_noref);
	void procesa(Tipo_array tipo_array);
	void procesa(Tipo_record tipo_record);
	void procesa(Tipo_pointer tipo_pointer);
	void procesa(Tipo_iden tipo_iden);
	void procesa(Tipo_int tipo_int);
	void procesa(Tipo_real tipo_real);
	void procesa(Tipo_bool tipo_bool);
	void procesa(Tipo_string tipo_string);
	void procesa(Campos_uno campos_uno);
	void procesa(Campos_muchos campos_muchos);
	void procesa(Campo campo);
	void procesa(Insts_una insts_una);
	void procesa(Insts_muchas insts_muchas);
	void procesa(E_igual e_igual);
	void procesa(If if_);
	void procesa(Ifelse ifelse);
	void procesa(While while_);
	void procesa(Read read);
	void procesa(Write write);
	void procesa(Nl nl);
	void procesa(New new_);
	void procesa(Delete delete);
	void procesa(Call call);
	void procesa(Bl bl);
	void procesa(Lista_sin lista_sin);
	void procesa(Lista_con lista_con);
	void procesa(Param_r_sin param_r_sin);
	void procesa(Param_r_con_una param_r_con_una);
	void procesa(Param_r_con_muchas param_r_con_muchas);
	void procesa(Bloque_sin bloque_sin);
	void procesa(Bloque_con bloque_con);
	void procesa(Entero entero);
	void procesa(Real real);
	void procesa(Cadena cadena);
	void procesa(Verdadero verdadero);
	void procesa(Falso falso);
	void procesa(Null null_);
	void procesa(Identificador identificador);
	void procesa(Suma suma);
	void procesa(Resta resta);
	void procesa(And and);
	void procesa(Or or);
	void procesa(Menor menor);
	void procesa(Men_ig men_ig);
	void procesa(Mayor mayor);
	void procesa(May_ig may_ig);
	void procesa(Igual igual);
	void procesa(Desigual desigual);
	void procesa(Mul mul);
	void procesa(Div div);
	void procesa(Modulo modulo);
	void procesa(M_unario m_unario);
	void procesa(Not not);
	void procesa(Indexacion indexacion);
	void procesa(Acc_registro acc_registro);
	void procesa(Acc_registro_indirecto acc_registro_in);
	void procesa(Indireccion indireccion);
}
