package c_ast_descendente_manual;

import java.io.IOException;
import java.io.Reader;
import asint.TinyASint.Prog;
import asint.TinyASint.Decs;
import asint.TinyASint.Dec;
import asint.TinyASint.Exp;
import asint.TinyASint.Insts;
import asint.TinyASint.Inst;
import asint.TinyASint.Tipo;
import asint.TinyASint.StringLocalizado;
import semops.SemOps;

public class ConstructorAST {
	private UnidadLexica anticipo;
	   private AnalizadorLexicoTiny alex;
	   private GestionErrores errores;
	   private SemOps sem;
	   
	   public ConstructorAST(Reader input) throws IOException {
	      errores = new GestionErrores();
	      alex = new AnalizadorLexicoTiny(input);
	      alex.fijaGestionErrores(errores);
	      sem = new SemOps();
	      sigToken();
	   }
	   public Prog Sp() {
	      Prog prog = Programa();
	      empareja(ClaseLexica.EOF);
	      return prog;
	   }
	   
	   private Prog Programa() {
		     switch(anticipo.clase()) {
		         case T_INT: case T_BOOL: case T_REAL:
		        	 Decs decs = Decs();
		        	 empareja(ClaseLexica.SEPARADOR);
		        	 Insts insts = Insts();
		             return sem.prog(decs, insts);
		         default: errores.errorSintactico(anticipo.fila(),anticipo.columna(),anticipo.clase(),
	                     ClaseLexica.T_INT,ClaseLexica.T_BOOL,
	                     ClaseLexica.T_REAL); return null;                                          
		     }
		}
	   
	   private Decs Decs() {
		     switch(anticipo.clase()) {
		     	case T_INT: case T_BOOL: case T_REAL:
		     		Dec dec = Dec();
		     		return RDecs(sem.dec_una(dec));
		         default: errores.errorSintactico(anticipo.fila(),anticipo.columna(),anticipo.clase(),
		        		 ClaseLexica.T_INT,ClaseLexica.T_BOOL,
	                     ClaseLexica.T_REAL); return null;                                    
		     }
		}
	   
	   private Decs RDecs(Decs dech) {
		     switch(anticipo.clase()) {
		         case PUNTOCOMA:
		        	 empareja(ClaseLexica.PUNTOCOMA);
		        	 Dec dec = Dec();
			     	 return RDecs(sem.decs_muchas(dech, dec));
		         case SEPARADOR: return dech;
		         default: errores.errorSintactico(anticipo.fila(),anticipo.columna(),anticipo.clase(),
		                                          ClaseLexica.SEPARADOR, ClaseLexica.PUNTOCOMA); return null;                                           
		     }
		}
	   
	   private Dec Dec() {
		     switch(anticipo.clase()) {
		         case T_INT: case T_BOOL: case T_REAL:
		        	 Tipo typ = TypN();
		        	 StringLocalizado var = VarN();
		             return sem.dec(typ, var);
		         default: errores.errorSintactico(anticipo.fila(),anticipo.columna(),anticipo.clase(),
		        		 ClaseLexica.T_INT,ClaseLexica.T_BOOL,
	                     ClaseLexica.T_REAL); return null;                                          
		     }
		}
	   
	   private Insts Insts() {
		     switch(anticipo.clase()) {
		         case IDEN:
		        	 Inst inst = Inst();
		        	 return RInsts(sem.inst_una(inst));
		         default: errores.errorSintactico(anticipo.fila(),anticipo.columna(),anticipo.clase(),
		                                          ClaseLexica.IDEN); return null;                                          
		     }
		}
	   
	   private Insts RInsts(Insts insth) {
		     switch(anticipo.clase()) {
		         case PUNTOCOMA:
		        	 empareja(ClaseLexica.PUNTOCOMA);
		        	 Inst inst = Inst();
		        	 return RInsts(sem.inst_muchas(insth, inst));
		         case EOF: return insth;
		         default: errores.errorSintactico(anticipo.fila(),anticipo.columna(),anticipo.clase(),
		                                          ClaseLexica.PUNTOCOMA, ClaseLexica.EOF); return null;                                         
		     }
		}
	   
	   private Inst Inst() {
		     switch(anticipo.clase()) {
		         case IDEN:
		        	 StringLocalizado var = VarN();
		        	 empareja(ClaseLexica.IGUALDAD);
		        	 Exp exp = E0();
		             return sem.inst(var, exp);
		         default: errores.errorSintactico(anticipo.fila(),anticipo.columna(),anticipo.clase(),
		                                          ClaseLexica.IDEN); return null;                                         
		     }
		}
	   
	   private Tipo TypN() {
		     switch(anticipo.clase()) {
		         case T_INT: empareja(ClaseLexica.T_INT); return sem.int_();
		         case T_BOOL: empareja(ClaseLexica.T_BOOL); return sem.realw();
		         case T_REAL: empareja(ClaseLexica.T_REAL); return sem.bool();
		         default: errores.errorSintactico(anticipo.fila(),anticipo.columna(),anticipo.clase(),
		        		 ClaseLexica.T_INT,ClaseLexica.T_BOOL,
	                     ClaseLexica.T_REAL); return null;                                 
		     }
		}
	   
	   private StringLocalizado VarN() {
		     switch(anticipo.clase()) {
		         case IDEN: 
		        	 UnidadLexica tkVar = anticipo;
		        	 empareja(ClaseLexica.IDEN); 
		        	 return sem.str(tkVar.lexema(), tkVar.fila(), 
                             tkVar.columna());
		         default: errores.errorSintactico(anticipo.fila(),anticipo.columna(),anticipo.clase(),
		                                          ClaseLexica.IDEN); return null;                                          
		     }
		}
	   
	   private Exp E0() {
		     switch(anticipo.clase()) {
		         case FALSE: case INT: case REAL: case MENOS: case NOT: case PAP: case TRUE: case IDEN:
		        	 Exp exp = E1();
		        	 return RE0(exp);
		         default: errores.errorSintactico(anticipo.fila(),anticipo.columna(),anticipo.clase(),
		        		 ClaseLexica.NOT,ClaseLexica.REAL, ClaseLexica.TRUE, ClaseLexica.FALSE,
	                     ClaseLexica.IDEN, ClaseLexica.MENOS, ClaseLexica.INT, ClaseLexica.PAP); return null;                                           
		     }
		}
	   
	   private Exp RE0(Exp exph) {
		     switch(anticipo.clase()) {
		         case MAS:
		        	 String op = OpIn0AsocD();
		        	 Exp exp = E0();
		             return sem.opera_dos(op, exph, exp);
		         case MENOS:
		        	 String op_ = OpIn0NoAsoc();
		        	 Exp exp_ = E1();
		        	 return sem.opera_dos(op_, exph, exp_);
		         case PCIERRE: case PUNTOCOMA: case EOF: return exph;
		         default: errores.errorSintactico(anticipo.fila(),anticipo.columna(),anticipo.clase(),
		                                          ClaseLexica.MAS, ClaseLexica.MENOS); return null;                                          
		     }
		}
	   
	   private Exp E1() {
		     switch(anticipo.clase()) {
		     	 case FALSE: case INT: case REAL: case MENOS: case NOT: case PAP: case TRUE: case IDEN:
		     		 Exp exp = E2();
		     		 return RE1(exp);
		         default: errores.errorSintactico(anticipo.fila(),anticipo.columna(),anticipo.clase(),
		        		 ClaseLexica.NOT,ClaseLexica.REAL, ClaseLexica.TRUE, ClaseLexica.FALSE,
	                     ClaseLexica.IDEN, ClaseLexica.MENOS, ClaseLexica.INT, ClaseLexica.PAP); return null;                                        
		     }
		}
	   
	   private Exp RE1(Exp exph) {
		     switch(anticipo.clase()) {
		         case AND: case OR:
		        	 String op =OpIn1AsocI();
		        	 Exp exp = E2();
		        	 return RE1(sem.opera_dos(op, exph, exp));
		         case MAS: case MENOS: case PCIERRE: case PUNTOCOMA: case EOF: return exph;
		         default: errores.errorSintactico(anticipo.fila(),anticipo.columna(),anticipo.clase(),
		                                          ClaseLexica.OR, ClaseLexica.AND); return null;                                          
		     }
		}
	   
	   private Exp E2() {
		     switch(anticipo.clase()) {
		     	 case FALSE: case INT: case REAL: case MENOS: case NOT: case PAP: case TRUE: case IDEN:
		     		 Exp exp = E3();
		     		 return RE2(exp);
		         default: errores.errorSintactico(anticipo.fila(),anticipo.columna(),anticipo.clase(),
		        		 ClaseLexica.NOT,ClaseLexica.REAL, ClaseLexica.TRUE, ClaseLexica.FALSE,
	                     ClaseLexica.IDEN, ClaseLexica.MENOS, ClaseLexica.INT, ClaseLexica.PAP); return null;                                           
		     }
		}
	   
	   private Exp RE2(Exp exph) {
		     switch(anticipo.clase()) {
		         case IGUAL: case DESIGUAL: case MENOR: case MAYOR: case MENIG: case MAYIG:
		        	 String op = OpIn2AsocI();
		        	 Exp exp = E3();
		        	 return RE2(sem.opera_dos(op, exph, exp));
		         case AND: case MAS : case MENOS: case OR: case PCIERRE: case PUNTOCOMA: case EOF: return exph;
		         default: errores.errorSintactico(anticipo.fila(),anticipo.columna(),anticipo.clase(),
		                                          ClaseLexica.IGUAL, ClaseLexica.DESIGUAL, 
		                                          ClaseLexica.MENOR, ClaseLexica.MAYOR, ClaseLexica.MENIG,
		                                          ClaseLexica.MAYIG); return null;                                       
		     }
		}
	   
	   private Exp E3() {
		     switch(anticipo.clase()) {
		     	case FALSE: case INT: case REAL: case MENOS: case NOT: case PAP: case TRUE: case IDEN:
		     		Exp exp = E4();
		     		return RE3(exp);
		        default: errores.errorSintactico(anticipo.fila(),anticipo.columna(),anticipo.clase(),
		        		 ClaseLexica.NOT,ClaseLexica.REAL, ClaseLexica.TRUE, ClaseLexica.FALSE,
	                     ClaseLexica.IDEN, ClaseLexica.MENOS, ClaseLexica.INT, ClaseLexica.PAP); return null;
		     }
		}
	   
	   private Exp RE3(Exp exph) {
		     switch(anticipo.clase()) {
		         case DIV: case POR:
		        	 String op = OpIn3NoAsoc();
		        	 Exp exp = E4();
		        	 return sem.opera_dos(op, exph, exp);
		         case AND: case OR: case DESIGUAL: case IGUAL: case MAS: case MENOS: case MENIG: case MAYIG: case MENOR: case MAYOR: case PCIERRE: case PUNTOCOMA: case EOF:
		        	 return exph;
		         default: errores.errorSintactico(anticipo.fila(),anticipo.columna(),anticipo.clase(),
		                                          ClaseLexica.DIV, ClaseLexica.POR); return null;                                         
		     }
		}
	   
	   private Exp E4() {
		     switch(anticipo.clase()) {
		         case MENOS:
		        	 String op = OpPre4NoAsoc();
		        	 Exp exp = E5();
		             return sem.opera_uno(op, exp);
		         case NOT:
		        	 String op_ = OpPre4Asoc();
		        	 Exp exp_ = E4();
		        	 return sem.opera_uno(op_, exp_);
		         case FALSE: case INT: case REAL: case PAP: case TRUE: case IDEN:
		        	 return E5();
		         default: errores.errorSintactico(anticipo.fila(),anticipo.columna(),anticipo.clase(),
		                                          ClaseLexica.NOT, ClaseLexica.REAL, ClaseLexica.IDEN,
		                                          ClaseLexica.MENOS, ClaseLexica.INT, ClaseLexica.TRUE,
		                                          ClaseLexica.FALSE, ClaseLexica.PCIERRE); return null;                                        
		     }
		}
	   
	   private Exp E5() {
		     switch(anticipo.clase()) {
		         case PAP:
		        	 empareja(ClaseLexica.PAP);
		        	 Exp exp = E0();
		        	 empareja(ClaseLexica.PCIERRE);
		             return exp;
		         case INT: 
		        	 UnidadLexica tkInt = anticipo;
		        	 empareja(ClaseLexica.INT); 
		        	 return sem.entero(sem.str(tkInt.lexema(), tkInt.fila(), 
                             tkInt.columna()));
		         case REAL: 
		        	 UnidadLexica tkReal = anticipo;
		        	 empareja(ClaseLexica.REAL); 
		        	 return sem.real(sem.str(tkReal.lexema(), tkReal.fila(), 
                             tkReal.columna()));
		         case TRUE: 
		        	 empareja(ClaseLexica.TRUE); 
		        	 return sem.verdadero();
		         case FALSE: 
		        	 empareja(ClaseLexica.FALSE);
		        	 return sem.falso();
		         case IDEN: 
		        	 UnidadLexica tkIden = anticipo;
		        	 empareja(ClaseLexica.IDEN); 
		        	 return sem.variable(sem.str(tkIden.lexema(), tkIden.fila(), 
                             tkIden.columna()));
		         default: errores.errorSintactico(anticipo.fila(),anticipo.columna(),anticipo.clase(),
		                                          ClaseLexica.TRUE, ClaseLexica.FALSE, ClaseLexica.INT,
		                                          ClaseLexica.REAL, ClaseLexica.IDEN, ClaseLexica.PAP); return null;                                           
		     }
		}
	   
	   private String OpIn0AsocD() {
		     switch(anticipo.clase()) {
		         case MAS: empareja(ClaseLexica.MAS);
		              return "+";
		         default: errores.errorSintactico(anticipo.fila(),anticipo.columna(),anticipo.clase(),
		                                          ClaseLexica.MAS); return "?";                                        
		     }
		}
	   
	   private String OpIn0NoAsoc() {
		     switch(anticipo.clase()) {
		         case MENOS: empareja(ClaseLexica.MENOS);
		              return "-";
		         default: errores.errorSintactico(anticipo.fila(),anticipo.columna(),anticipo.clase(),
		                                          ClaseLexica.MENOS); return "?";                                          
		     }
		}
	   
	   private String OpIn1AsocI() {
		     switch(anticipo.clase()) {
		         case AND: empareja(ClaseLexica.AND);
		              return "and";
		         case OR: empareja(ClaseLexica.OR); 
		              return "or";
		         default: errores.errorSintactico(anticipo.fila(),anticipo.columna(),anticipo.clase(),
		                                          ClaseLexica.OR, ClaseLexica.AND); return "?";                                          
		     }
		}
	   
	   private String OpIn2AsocI() {
		     switch(anticipo.clase()) {
		         case MENOR: empareja(ClaseLexica.MENOR); return "<";
		         case MENIG: empareja(ClaseLexica.MENIG); return "<=";
		         case MAYIG: empareja(ClaseLexica.MAYIG); return ">=";
		         case MAYOR: empareja(ClaseLexica.MAYOR); return ">";
		         case IGUAL: empareja(ClaseLexica.IGUAL); return "==";
		         case DESIGUAL: empareja(ClaseLexica.DESIGUAL); return "!=";
		         default: errores.errorSintactico(anticipo.fila(),anticipo.columna(),anticipo.clase(),
		        		 ClaseLexica.IGUAL, ClaseLexica.DESIGUAL, 
	                     ClaseLexica.MENOR, ClaseLexica.MAYOR, ClaseLexica.MENIG,
	                     ClaseLexica.MAYIG); return "?";                                            
		     }
		}
	   
	   private String OpIn3NoAsoc() {
		     switch(anticipo.clase()) {
		         case POR: empareja(ClaseLexica.POR); return "*";
		         case DIV: empareja(ClaseLexica.DIV); return "/";
		         default: errores.errorSintactico(anticipo.fila(),anticipo.columna(),anticipo.clase(),
		                                          ClaseLexica.DIV, ClaseLexica.POR); return "?";                                          
		     }
		}
	   
	   private String OpPre4NoAsoc() {
		     switch(anticipo.clase()) {
		         case MENOS: empareja(ClaseLexica.MENOS); return "-";
		         default: errores.errorSintactico(anticipo.fila(),anticipo.columna(),anticipo.clase(),
		                                          ClaseLexica.MENOS); return "?";                                          
		     }
		}
	   
	   private String OpPre4Asoc() {
		     switch(anticipo.clase()) {
		     	 case NOT: empareja(ClaseLexica.NOT); return "not";
		         default: errores.errorSintactico(anticipo.fila(),anticipo.columna(),anticipo.clase(),
		                                          ClaseLexica.NOT); return "?";                                          
		     }
		}
	   
	   private void empareja(ClaseLexica claseEsperada) {
	      if (anticipo.clase() == claseEsperada)
	          sigToken();
	      else errores.errorSintactico(anticipo.fila(),anticipo.columna(),anticipo.clase(),claseEsperada);
	   }
	   private void sigToken() {
	      try {
	        anticipo = alex.sigToken();
	      }
	      catch(IOException e) {
	        errores.errorFatal(e);
	      }
	   }
}
