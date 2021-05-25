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


public class ProcesamientoPorDefecto implements Procesamiento {
   public void procesa(Prog prog) {}
   public void procesa(Dec_una dec) {}
   public void procesa(Decs_muchas decs) {}
   public void procesa(Dec dec) {}
   public void procesa(Int tipo) {}
   public void procesa(Realw tipo) {}
   public void procesa(Bool tipo) {}
   public void procesa(Inst_una inst) {}
   public void procesa(Inst_muchas insts) {}
   public void procesa(Inst inst) {}
   public void procesa(Suma num) {}
   public void procesa(Resta num) {}
   public void procesa(And exp) {}
   public void procesa(Or exp) {}
   public void procesa(Menor exp) {}
   public void procesa(Men_ig exp) {}
   public void procesa(Mayor exp) {}
   public void procesa(May_ig exp) {}
   public void procesa(Igual exp) {}
   public void procesa(Desigual exp) {}
   public void procesa(Mul exp) {}
   public void procesa(Div exp) {}
   public void procesa(M_unario exp) {}
   public void procesa(Not exp) {}
   public void procesa(Entero num) {}
   public void procesa(Real num) {}
   public void procesa(Variable var) {}  
   public void procesa(Verdadero exp) {}
   public void procesa(Falso exp) {}  
}
