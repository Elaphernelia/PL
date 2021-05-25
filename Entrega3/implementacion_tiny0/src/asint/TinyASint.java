package asint;

public class TinyASint {

	public static abstract class Exp  {
       public Exp() {
       }   
       public abstract int prioridad();
       public abstract void procesa(Procesamiento procesamiento);
    }
    
    public static class StringLocalizado {
	     private String s;
	     private int fila;
	     private int col;
	     public StringLocalizado(String s, int fila, int col) {
	         this.s = s;
	         this.fila = fila;
	         this.col = col;
	     }
	     public int fila() {return fila;}
	     public int col() {return col;}
	     public String toString() {
	       return s;
	     }
	     public boolean equals(Object o) {
	         return (o == this) || (
	                (o instanceof StringLocalizado) &&
	                (((StringLocalizado)o).s.equals(s)));                
	     }
	     public int hashCode() {
	         return s.hashCode();
	     }
	  }

 //--------------------Exp Tipos---------------------------------------------   
    private static abstract class ExpBin extends Exp {
        private Exp arg0;
        private Exp arg1;
        public Exp arg0() {return arg0;}
        public Exp arg1() {return arg1;}
        public ExpBin(Exp arg0, Exp arg1) {
            super();
            this.arg0 = arg0;
            this.arg1 = arg1;
        }
    }
    
    private static abstract class ExpUna extends Exp {
        private Exp arg0;
        public Exp arg0() {return arg0;}
        public ExpUna(Exp arg0) {
            super();
            this.arg0 = arg0;
        }
    }
//----------------------Exp subtipos------------------------------------------        
    private static abstract class ExpAditiva extends ExpBin {
        public ExpAditiva(Exp arg0, Exp arg1) {
            super(arg0,arg1);
        }
        public final int prioridad() {
            return 0;
        }
    }
    
    private static abstract class ExpBooleana extends ExpBin {
        public ExpBooleana(Exp arg0, Exp arg1) {
            super(arg0,arg1);
        }
        public final int prioridad() {
            return 1;
        }
    }
    
    private static abstract class ExpRelacional extends ExpBin {
        public ExpRelacional(Exp arg0, Exp arg1) {
            super(arg0,arg1);
        }
        public final int prioridad() {
            return 2;
        }
    }
    
    private static abstract class ExpMultiplicativa extends ExpBin {
        public ExpMultiplicativa(Exp arg0, Exp arg1) {
            super(arg0,arg1);
        }
        public final int prioridad() {
            return 3;
        }
    }
    
    private static abstract class ExpUnaria extends ExpUna {
        public ExpUnaria(Exp arg0) {
            super(arg0);
        }
        public final int prioridad() {
            return 4;
        }
    }
//-----------------------Exp específico--------------------------------------------  
    public static class M_unario extends ExpUnaria {
        public M_unario(Exp arg0) {
            super(arg0);
        }
        public void procesa(Procesamiento p) {
           p.procesa(this); 
        }     
    }
    
    public static class Not extends ExpUnaria {
        public Not(Exp arg0) {
            super(arg0);
        }
        public void procesa(Procesamiento p) {
           p.procesa(this); 
        }     
    }
    
    public static class Suma extends ExpAditiva {
        public Suma(Exp arg0, Exp arg1) {
            super(arg0,arg1);
        }
        public void procesa(Procesamiento p) {
           p.procesa(this); 
        }     
    }
    public static class Resta extends ExpAditiva {
        public Resta(Exp arg0, Exp arg1) {
            super(arg0,arg1);
        }
        public void procesa(Procesamiento p) {
           p.procesa(this); 
        }     
    }
    
    public static class And extends ExpBooleana {
        public And(Exp arg0, Exp arg1) {
            super(arg0,arg1);
        }
        public void procesa(Procesamiento p) {
           p.procesa(this); 
        }     
    }
    
    public static class Or extends ExpBooleana {
        public Or(Exp arg0, Exp arg1) {
            super(arg0,arg1);
        }
        public void procesa(Procesamiento p) {
           p.procesa(this); 
        }     
    }
    
    public static class Menor extends ExpRelacional {
        public Menor(Exp arg0, Exp arg1) {
            super(arg0,arg1);
        }
        public void procesa(Procesamiento p) {
           p.procesa(this); 
        }     
    }
    
    public static class Men_ig extends ExpRelacional {
        public Men_ig(Exp arg0, Exp arg1) {
            super(arg0,arg1);
        }
        public void procesa(Procesamiento p) {
           p.procesa(this); 
        }     
    }
    
    public static class Mayor extends ExpRelacional {
        public Mayor(Exp arg0, Exp arg1) {
            super(arg0,arg1);
        }
        public void procesa(Procesamiento p) {
           p.procesa(this); 
        }     
    }
    
    public static class May_ig extends ExpRelacional {
        public May_ig(Exp arg0, Exp arg1) {
            super(arg0,arg1);
        }
        public void procesa(Procesamiento p) {
           p.procesa(this); 
        }     
    }
    
    public static class Igual extends ExpRelacional {
        public Igual(Exp arg0, Exp arg1) {
            super(arg0,arg1);
        }
        public void procesa(Procesamiento p) {
           p.procesa(this); 
        }     
    }
    
    public static class Desigual extends ExpRelacional {
        public Desigual(Exp arg0, Exp arg1) {
            super(arg0,arg1);
        }
        public void procesa(Procesamiento p) {
           p.procesa(this); 
        }     
    }
     
    public static class Mul extends ExpMultiplicativa {
        public Mul(Exp arg0, Exp arg1) {
            super(arg0,arg1);
        }
        public void procesa(Procesamiento p) {
           p.procesa(this); 
        }     
    }
    public static class Div extends ExpMultiplicativa {
        public Div(Exp arg0, Exp arg1) {
            super(arg0,arg1);
        }
        public void procesa(Procesamiento p) {
           p.procesa(this); 
        }     
    }
    
    public static class Entero extends Exp {
        private StringLocalizado num;
        public Entero(StringLocalizado num) {
            super();
            this.num = num;
        }
        public StringLocalizado num() {return num;}
        public void procesa(Procesamiento p) {
           p.procesa(this); 
        }     
        public final int prioridad() {
            return 5;
        }
    }
    
    public static class Real extends Exp {
        private StringLocalizado num;
        public Real(StringLocalizado num) {
            super();
            this.num = num;
        }
        public StringLocalizado num() {return num;}
        public void procesa(Procesamiento p) {
           p.procesa(this); 
        }     
        public final int prioridad() {
            return 5;
        }
    }
    
    public static class Variable extends Exp {
        private StringLocalizado id;
        public Variable(StringLocalizado id) {
            super();
            this.id = id;
        }
        public StringLocalizado id() {return id;}
        public void procesa(Procesamiento p) {
           p.procesa(this); 
        }     
        public final int prioridad() {
            return 5;
        }
    }
    
    public static class Verdadero extends Exp {
        public Verdadero() {
            super();
        }
        public void procesa(Procesamiento p) {
           p.procesa(this); 
        }     
        public final int prioridad() {
            return 5;
        }
    }
    
    public static class Falso extends Exp {
        public Falso() {
            super();
        }
        public void procesa(Procesamiento p) {
           p.procesa(this); 
        }     
        public final int prioridad() {
            return 5;
        }
    }
 //----------------Instrucciones------------------------------
    public static abstract class Insts {
        public Insts() {
        }
        public abstract void procesa(Procesamiento p);
     }
     
     public static class Inst_una extends Insts {
        private Inst inst; 
        public Inst_una(Inst inst) {
           super();
           this.inst = inst;
        }   
        public Inst inst() {return inst;}
        public void procesa(Procesamiento p) {
            p.procesa(this); 
         }     
     }
     
     public static class Inst_muchas extends Insts {
        private Inst inst;
        private Insts insts;
        public Inst_muchas(Insts insts, Inst inst) {
           super();
           this.inst = inst;
           this.insts = insts;
        }
        public Inst inst() {return inst;}
        public Insts insts() {return insts;}
        public void procesa(Procesamiento p) {
            p.procesa(this); 
         }     
     }
     
     public static class Inst  {
         private Exp exp;
         private StringLocalizado var;
         public Inst(StringLocalizado var, Exp exp) {
             this.exp = exp;
             this.var = var;
         }
         public Exp exp() {return exp;}
         public StringLocalizado var() {return var;}
         public void procesa(Procesamiento p) {
            p.procesa(this); 
         }     
     }
 //--------------------Tipos--------------------------------
    public static abstract class Tipo {
        public Tipo() {
        }
        public abstract void procesa(Procesamiento p);
     }
    
    public static class Int extends Tipo { 
        public Int() {
           super();
        }   
        public void procesa(Procesamiento p) {
            p.procesa(this); 
         }     
     }
    
    public static class Realw extends Tipo { 
        public Realw() {
           super();
        }   
        public void procesa(Procesamiento p) {
            p.procesa(this); 
         }     
     }
    
    public static class Bool extends Tipo { 
        public Bool() {
           super();
        }   
        public void procesa(Procesamiento p) {
            p.procesa(this); 
         }     
     }
 //------------------Declaraciones----------------------------
    public static abstract class Decs {
       public Decs() {
       }
       public abstract void procesa(Procesamiento p);
    }
    
    public static class Dec_una extends Decs {
       private Dec dec; 
       public Dec_una(Dec dec) {
          super();
          this.dec = dec;
       }   
       public Dec dec() {return dec;}
       public void procesa(Procesamiento p) {
           p.procesa(this); 
        }     
    }
    
    public static class Decs_muchas extends Decs {
       private Dec dec;
       private Decs decs;
       public Decs_muchas(Decs decs, Dec dec) {
          super();
          this.dec = dec;
          this.decs = decs;
       }
       public Dec dec() {return dec;}
       public Decs decs() {return decs;}
       public void procesa(Procesamiento p) {
           p.procesa(this); 
        }     
    }
    
    public static class Dec  {
        private Tipo tipo;
        private StringLocalizado var;
        public Dec(Tipo tipo, StringLocalizado var) {
            this.tipo = tipo;
            this.var = var;
        }
        public Tipo tipo() {return tipo;}
        public StringLocalizado var() {return var;}
        public void procesa(Procesamiento p) {
           p.procesa(this); 
        }     
    }
//-------------------Programa-------------------------- 
    public static class Prog {
      private Decs decs;
      private Insts insts;
       public Prog(Decs decs, Insts insts) {
          this.decs = decs;
          this.insts = insts;
       }   
       public Decs decs() {return decs;}
       public Insts insts() {return insts;}
       public void procesa(Procesamiento p) {
           p.procesa(this); 
        }     
    }

//---------------Constructoras-------------------------------- 
    
   public Prog prog(Decs decs, Insts insts) {
       return new Prog(decs, insts);
   }
   public Decs dec_una(Dec dec) {
       return new Dec_una(dec);
   }
   public Decs decs_muchas(Decs decs, Dec dec) {
       return new Decs_muchas(decs, dec);
   }
   public Dec dec(Tipo tipo, StringLocalizado variable) {
       return new Dec(tipo, variable);
   }
   public Tipo int_() { //no podemos usar la palabra reservada "int" para el nombre del método
       return new Int();
   }
   public Tipo realw() {
       return new Realw();
   }
   public Tipo bool() {
       return new Bool();
   }
   public Insts inst_una(Inst inst) {
	   return new Inst_una(inst);
   }
   public Insts inst_muchas(Insts insts, Inst inst) {
	   return new Inst_muchas(insts, inst);
   }
   public Inst inst(StringLocalizado variable, Exp arg) {
	   return new Inst(variable, arg);
   }
   public Exp suma(Exp arg0, Exp arg1) {
       return new Suma(arg0,arg1);
   }
   public Exp resta(Exp arg0, Exp arg1) {
       return new Resta(arg0,arg1);
   }
   public Exp and(Exp arg0, Exp arg1) {
       return new And(arg0,arg1);
   }
   public Exp or(Exp arg0, Exp arg1) {
       return new Or(arg0,arg1);
   }
   public Exp menor(Exp arg0, Exp arg1) {
       return new Menor(arg0,arg1);
   }
   public Exp men_ig(Exp arg0, Exp arg1) {
       return new Men_ig(arg0,arg1);
   }
   public Exp mayor(Exp arg0, Exp arg1) {
       return new Mayor(arg0,arg1);
   }
   public Exp may_ig(Exp arg0, Exp arg1) {
       return new May_ig(arg0,arg1);
   }
   public Exp igual(Exp arg0, Exp arg1) {
       return new Igual(arg0,arg1);
   }
   public Exp desigual(Exp arg0, Exp arg1) {
       return new Desigual(arg0,arg1);
   }
   public Exp mul(Exp arg0, Exp arg1) {
       return new Mul(arg0,arg1);
   }
   public Exp div(Exp arg0, Exp arg1) {
       return new Div(arg0,arg1);
   }
   public Exp m_unario(Exp arg) {
       return new M_unario(arg);
   }
   public Exp not(Exp arg) {
       return new Not(arg);
   }
   public Exp entero(StringLocalizado num) {
       return new Entero(num);
   }
   public Exp real(StringLocalizado num) {
       return new Real(num);
   }
   public Exp variable(StringLocalizado variable) {
       return new Variable(variable);
   }
   public Exp verdadero() {
       return new Verdadero();
   }
   public Exp falso() {
       return new Falso();
   }
   public StringLocalizado str(String s, int fila, int col) {
       return new StringLocalizado(s,fila,col);
   }
}
