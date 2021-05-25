package semops;

import asint.TinyASint;

public class SemOps extends TinyASint {
   public Exp opera_uno(String op, Exp arg0) {
       switch(op) {
           case "-": return m_unario(arg0);
	       case "not": return not(arg0);
       }
       throw new UnsupportedOperationException("exp "+op);
   }
   
   public Exp opera_dos(String op, Exp arg0, Exp arg1) {
       switch(op) {
           case "+": return suma(arg0,arg1);
           case "-": return resta(arg0,arg1);
           case "*": return mul(arg0,arg1);
           case "/": return div(arg0,arg1);
           case "and": return and(arg0, arg1);
   		   case "or": return or(arg0, arg1);
   		   case "<": return menor(arg0, arg1);
   		   case "<=": return men_ig(arg0, arg1);
   		   case ">": return mayor(arg0, arg1);
   		   case ">=": return may_ig(arg0, arg1);
   		   case "==": return igual(arg0, arg1);
   		   case "!=": return desigual(arg0, arg1);
       }
       throw new UnsupportedOperationException("exp "+op);
   }
      
}
