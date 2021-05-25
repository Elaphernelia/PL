package procesamientos;

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
import asint.ProcesamientoPorDefecto;
import asint.TinyASint.Exp;


public class Impresion extends ProcesamientoPorDefecto {
   public Impresion() {
   }
   //---------------------------------------------
   public void procesa(Prog prog) {
	   prog.decs().procesa(this);
       System.out.println();
       System.out.println("&&");
       prog.insts().procesa(this);
       System.out.println(); 
   }
   
   public void procesa(Dec_una dec) {
	   dec.dec().procesa(this);
   }
   
   public void procesa(Decs_muchas decs) {
	   decs.decs().procesa(this);
       System.out.println(";");
       decs.dec().procesa(this);
   }
   
   public void procesa(Dec dec) {
	   dec.tipo().procesa(this);
       System.out.print(" "+dec.var());
   }
   
   public void procesa(Int type) {
	   System.out.print("int");
   }
   
   public void procesa(Realw type) {
	   System.out.print("real");
   }
   
   public void procesa(Bool type) {
	   System.out.print("bool");
   }
   
   public void procesa(Inst_una inst) {
	   inst.inst().procesa(this);
   }
   
   public void procesa(Inst_muchas inst) {
	   inst.insts().procesa(this);
       System.out.println(";");
       inst.inst().procesa(this);
   }
   
   public void procesa(Inst inst) {
	   System.out.print(inst.var()+" = ");
	   inst.exp().procesa(this);
   }
   
   public void procesa(Suma exp) {
	      imprime_arg(exp.arg0(),1); 
	      System.out.print(" + ");
	      imprime_arg(exp.arg1(),0); 
   }
   
   public void procesa(Resta exp) {
	      imprime_arg(exp.arg0(),1); 
	      System.out.print(" - ");
	      imprime_arg(exp.arg1(),1); 
   }
   
   public void procesa(And exp) {
	      imprime_arg(exp.arg0(),1); 
	      System.out.print(" and ");
	      imprime_arg(exp.arg1(),2); 
   }
   
   public void procesa(Or exp) {
	      imprime_arg(exp.arg0(),1); 
	      System.out.print(" or ");
	      imprime_arg(exp.arg1(),2); 
   }
   
   public void procesa(Menor exp) {
	      imprime_arg(exp.arg0(),2); 
	      System.out.print(" < ");
	      imprime_arg(exp.arg1(),3); 
   }
   
   public void procesa(Men_ig exp) {
	      imprime_arg(exp.arg0(),2); 
	      System.out.print(" <= ");
	      imprime_arg(exp.arg1(),3); 
   }
   
   public void procesa(Mayor exp) {
	      imprime_arg(exp.arg0(),2); 
	      System.out.print(" > ");
	      imprime_arg(exp.arg1(),3); 
   }
   
   public void procesa(May_ig exp) {
	      imprime_arg(exp.arg0(),2); 
	      System.out.print(" >= ");
	      imprime_arg(exp.arg1(),3); 
   }
   
   public void procesa(Igual exp) {
	      imprime_arg(exp.arg0(),2); 
	      System.out.print(" == ");
	      imprime_arg(exp.arg1(),3); 
   }
   
   public void procesa(Desigual exp) {
	      imprime_arg(exp.arg0(),2); 
	      System.out.print(" != ");
	      imprime_arg(exp.arg1(),3); 
   }
   
   public void procesa(Mul exp) {
	      imprime_arg(exp.arg0(),4); 
	      System.out.print(" * ");
	      imprime_arg(exp.arg1(),4);       
   }
   
	public void procesa(Div exp) {
	      imprime_arg(exp.arg0(),4); 
	      System.out.print(" / ");
	      imprime_arg(exp.arg1(),4);       
	}

   public void procesa(M_unario exp) {
	   System.out.print(" -");
	   imprime_arg(exp.arg0(),5); 
   }
   
   public void procesa(Not exp) {
	   System.out.print(" not ");
	   imprime_arg(exp.arg0(),4); 
   }
   
   public void procesa(Entero exp) {
	   System.out.print(exp.num());
   }
   
   public void procesa(Real exp) {
	   System.out.print(exp.num());
   }
   
   public void procesa(Variable exp) {
	   System.out.print(exp.id());
   }
   
   public void procesa(Verdadero exp) {
	   System.out.print(" true ");
   }
   
   public void procesa(Falso exp) {
	   System.out.print(" false ");
   }	
   //--------------------------------------------
   
   private void imprime_arg(Exp arg, int p) {
       if (arg.prioridad() < p) {
           System.out.print("(");
           arg.procesa(this);
           System.out.print(")");
       }
       else {
           arg.procesa(this);
       }
   }
}   

            