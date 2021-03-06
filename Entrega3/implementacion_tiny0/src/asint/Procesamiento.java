package asint;

import asint.TinyASint.Prog;
import asint.TinyASint.Dec_una;
import asint.TinyASint.Decs_muchas;
import asint.TinyASint.Dec;
import asint.TinyASint.Int;
import asint.TinyASint.Realw;
import asint.TinyASint.Bool;
import asint.TinyASint.Inst_una;
import asint.TinyASint.Inst_muchas;
import asint.TinyASint.Inst;
import asint.TinyASint.Suma;
import asint.TinyASint.Resta;
import asint.TinyASint.And;
import asint.TinyASint.Or;
import asint.TinyASint.Menor;
import asint.TinyASint.Men_ig;
import asint.TinyASint.Mayor;
import asint.TinyASint.May_ig;
import asint.TinyASint.Igual;
import asint.TinyASint.Desigual;
import asint.TinyASint.Mul;
import asint.TinyASint.Div;
import asint.TinyASint.M_unario;
import asint.TinyASint.Not;
import asint.TinyASint.Entero;
import asint.TinyASint.Real;
import asint.TinyASint.Variable;
import asint.TinyASint.Verdadero;
import asint.TinyASint.Falso;

public interface Procesamiento {
    void procesa(Prog prog);
    void procesa(Dec_una dec);
    void procesa(Decs_muchas decs);
    void procesa(Dec dec);
    void procesa(Int tipo);
    void procesa(Realw tipo);
    void procesa(Bool tipo);
    void procesa(Inst_una inst);
    void procesa(Inst_muchas insts);
    void procesa(Inst inst);
    void procesa(Suma num);
    void procesa(Resta num);
    void procesa(And exp);
    void procesa(Or exp);
    void procesa(Menor exp);
    void procesa(Men_ig exp);
    void procesa(Mayor exp);
    void procesa(May_ig exp);
    void procesa(Igual exp);
    void procesa(Desigual exp);
    void procesa(Mul exp);
    void procesa(Div exp);
    void procesa(M_unario exp);
    void procesa(Not exp);
    void procesa(Entero num);
    void procesa(Real num);
    void procesa(Variable var);  
    void procesa(Verdadero exp);
    void procesa(Falso exp);
}