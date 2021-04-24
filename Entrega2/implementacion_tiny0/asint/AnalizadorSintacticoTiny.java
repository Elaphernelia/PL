
package asint;

import alex.UnidadLexica;
import alex.AnalizadorLexicoTiny;
import alex.ClaseLexica;
import errors.GestionErroresTiny0;
import java.io.IOException;
import java.io.Reader;

public class AnalizadorSintacticoTiny {
   private UnidadLexica anticipo;
   private AnalizadorLexicoTiny alex;
   private GestionErroresTiny0 errores;
   public AnalizadorSintacticoTiny(Reader input) throws IOException {
      errores = new GestionErroresTiny0();
      alex = new AnalizadorLexicoTiny(input);
      alex.fijaGestionErrores(errores);
      sigToken();
   }
   public void Sp() {
      Programa();
      empareja(ClaseLexica.EOF);
   }
   
   private void Programa() {
	     switch(anticipo.clase()) {
	         case T_INT: case T_BOOL: case T_REAL:
	        	 Decs();
	        	 empareja(ClaseLexica.SEPARADOR);
	        	 Insts();
	             break;
	         default: errores.errorSintactico(anticipo.fila(),anticipo.columna(),anticipo.clase(),
                     ClaseLexica.T_INT,ClaseLexica.T_BOOL,
                     ClaseLexica.T_REAL);                                            
	     }
	}
   
   private void Decs() {
	     switch(anticipo.clase()) {
	     	case T_INT: case T_BOOL: case T_REAL:
	     		Dec();
	     		RDecs();
	            break;
	         default: errores.errorSintactico(anticipo.fila(),anticipo.columna(),anticipo.clase(),
	        		 ClaseLexica.T_INT,ClaseLexica.T_BOOL,
                     ClaseLexica.T_REAL);                                        
	     }
	}
   
   private void RDecs() {
	     switch(anticipo.clase()) {
	         case PUNTOCOMA:
	        	 empareja(ClaseLexica.PUNTOCOMA);
	        	 Dec();
		     	 RDecs();
	        	 break;
	         case SEPARADOR: break;
	         default: errores.errorSintactico(anticipo.fila(),anticipo.columna(),anticipo.clase(),
	                                          ClaseLexica.SEPARADOR, ClaseLexica.PUNTOCOMA);                                            
	     }
	}
   
   private void Dec() {
	     switch(anticipo.clase()) {
	         case T_INT: case T_BOOL: case T_REAL:
	        	 TypN();
	        	 VarN();
	             break;
	         default: errores.errorSintactico(anticipo.fila(),anticipo.columna(),anticipo.clase(),
	        		 ClaseLexica.T_INT,ClaseLexica.T_BOOL,
                     ClaseLexica.T_REAL);                                            
	     }
	}
   
   private void Insts() {
	     switch(anticipo.clase()) {
	         case IDEN:
	        	 Inst();
	        	 RInsts();
	             break;
	         default: errores.errorSintactico(anticipo.fila(),anticipo.columna(),anticipo.clase(),
	                                          ClaseLexica.IDEN);                                            
	     }
	}
   
   private void RInsts() {
	     switch(anticipo.clase()) {
	         case PUNTOCOMA:
	        	 empareja(ClaseLexica.PUNTOCOMA);
	        	 Inst();
	        	 RInsts();
	         case EOF: break;
	         default: errores.errorSintactico(anticipo.fila(),anticipo.columna(),anticipo.clase(),
	                                          ClaseLexica.PUNTOCOMA, ClaseLexica.EOF);                                            
	     }
	}
   
   private void Inst() {
	     switch(anticipo.clase()) {
	         case IDEN:
	        	 VarN();
	        	 empareja(ClaseLexica.IGUALDAD);
	        	 E0();
	             break;
	         default: errores.errorSintactico(anticipo.fila(),anticipo.columna(),anticipo.clase(),
	                                          ClaseLexica.IDEN);                                            
	     }
	}
   
   private void TypN() {
	     switch(anticipo.clase()) {
	         case T_INT: empareja(ClaseLexica.T_INT); break;
	         case T_BOOL: empareja(ClaseLexica.T_BOOL); break;
	         case T_REAL: empareja(ClaseLexica.T_REAL); break;
	         default: errores.errorSintactico(anticipo.fila(),anticipo.columna(),anticipo.clase(),
	        		 ClaseLexica.T_INT,ClaseLexica.T_BOOL,
                     ClaseLexica.T_REAL);                                        
	     }
	}
   
   private void VarN() {
	     switch(anticipo.clase()) {
	         case IDEN: empareja(ClaseLexica.IDEN); break;
	         default: errores.errorSintactico(anticipo.fila(),anticipo.columna(),anticipo.clase(),
	                                          ClaseLexica.IDEN);                                            
	     }
	}
   
   private void E0() {
	     switch(anticipo.clase()) {
	         case FALSE: case INT: case REAL: case MENOS: case NOT: case PAP: case TRUE: case IDEN:
	        	 E1();
	        	 RE0();
	             break;
	         default: errores.errorSintactico(anticipo.fila(),anticipo.columna(),anticipo.clase(),
	        		 ClaseLexica.NOT,ClaseLexica.REAL, ClaseLexica.TRUE, ClaseLexica.FALSE,
                     ClaseLexica.IDEN, ClaseLexica.MENOS, ClaseLexica.INT, ClaseLexica.PAP);                                            
	     }
	}
   
   private void RE0() {
	     switch(anticipo.clase()) {
	         case MAS:
	        	 OpIn0AsocD();
	        	 E0();
	             break;
	         case MENOS:
	        	 OpIn0NoAsoc();
	        	 E1();
	        	 break;
	         case PCIERRE: case PUNTOCOMA: case EOF: break;
	         default: errores.errorSintactico(anticipo.fila(),anticipo.columna(),anticipo.clase(),
	                                          ClaseLexica.MAS, ClaseLexica.MENOS);                                            
	     }
	}
   
   private void E1() {
	     switch(anticipo.clase()) {
	     	 case FALSE: case INT: case REAL: case MENOS: case NOT: case PAP: case TRUE: case IDEN:
	     		 E2();
	     		 RE1();
	             break;
	         default: errores.errorSintactico(anticipo.fila(),anticipo.columna(),anticipo.clase(),
	        		 ClaseLexica.NOT,ClaseLexica.REAL, ClaseLexica.TRUE, ClaseLexica.FALSE,
                     ClaseLexica.IDEN, ClaseLexica.MENOS, ClaseLexica.INT, ClaseLexica.PAP);                                         
	     }
	}
   
   private void RE1() {
	     switch(anticipo.clase()) {
	         case AND: case OR:
	        	 OpIn1AsocI();
	        	 E2();
	        	 RE1();
	             break;
	         case MAS: case MENOS: case PCIERRE: case PUNTOCOMA: case EOF: break;
	         default: errores.errorSintactico(anticipo.fila(),anticipo.columna(),anticipo.clase(),
	                                          ClaseLexica.OR, ClaseLexica.AND);                                            
	     }
	}
   
   private void E2() {
	     switch(anticipo.clase()) {
	     	 case FALSE: case INT: case REAL: case MENOS: case NOT: case PAP: case TRUE: case IDEN:
	     		 E3();
	     		 RE2();
	             break;
	         default: errores.errorSintactico(anticipo.fila(),anticipo.columna(),anticipo.clase(),
	        		 ClaseLexica.NOT,ClaseLexica.REAL, ClaseLexica.TRUE, ClaseLexica.FALSE,
                     ClaseLexica.IDEN, ClaseLexica.MENOS, ClaseLexica.INT, ClaseLexica.PAP);                                            
	     }
	}
   
   private void RE2() {
	     switch(anticipo.clase()) {
	         case IGUAL: case DESIGUAL: case MENOR: case MAYOR: case MENIG: case MAYIG:
	        	 OpIn2AsocI();
	        	 E3();
	        	 RE2();
	             break;
	         case AND: case MAS : case MENOS: case OR: case PCIERRE: case PUNTOCOMA: case EOF: break;
	         default: errores.errorSintactico(anticipo.fila(),anticipo.columna(),anticipo.clase(),
	                                          ClaseLexica.IGUAL, ClaseLexica.DESIGUAL, 
	                                          ClaseLexica.MENOR, ClaseLexica.MAYOR, ClaseLexica.MENIG,
	                                          ClaseLexica.MAYIG);                                            
	     }
	}
   
   private void E3() {
	     switch(anticipo.clase()) {
	     	case FALSE: case INT: case REAL: case MENOS: case NOT: case PAP: case TRUE: case IDEN:
	     		E4();
	     		RE3();
	            break;
	        default: errores.errorSintactico(anticipo.fila(),anticipo.columna(),anticipo.clase(),
	        		 ClaseLexica.NOT,ClaseLexica.REAL, ClaseLexica.TRUE, ClaseLexica.FALSE,
                     ClaseLexica.IDEN, ClaseLexica.MENOS, ClaseLexica.INT, ClaseLexica.PAP);
	     }
	}
   
   private void RE3() {
	     switch(anticipo.clase()) {
	         case DIV: case POR:
	        	 OpIn3NoAsoc();
	        	 E4();
	             break;
	         case AND: case OR: case DESIGUAL: case IGUAL: case MAS: case MENOS: case MENIG: case MAYIG: case MENOR: case MAYOR: case PCIERRE: case PUNTOCOMA: case EOF:
	        	break;
	         default: errores.errorSintactico(anticipo.fila(),anticipo.columna(),anticipo.clase(),
	                                          ClaseLexica.DIV, ClaseLexica.POR);                                            
	     }
	}
   
   private void E4() {
	     switch(anticipo.clase()) {
	         case MENOS:
	        	 OpPre4NoAsoc();
	        	 E5();
	             break;
	         case NOT:
	        	 OpPre4Asoc();
	        	 E4();
	        	 break;
	         case FALSE: case INT: case REAL: case PAP: case TRUE: case IDEN:
	        	 E5();
	        	 break;
	         default: errores.errorSintactico(anticipo.fila(),anticipo.columna(),anticipo.clase(),
	                                          ClaseLexica.NOT, ClaseLexica.REAL, ClaseLexica.IDEN,
	                                          ClaseLexica.MENOS, ClaseLexica.INT, ClaseLexica.TRUE,
	                                          ClaseLexica.FALSE, ClaseLexica.PCIERRE);                                            
	     }
	}
   
   private void E5() {
	     switch(anticipo.clase()) {
	         case PAP:
	        	 empareja(ClaseLexica.PAP);
	        	 E0();
	        	 empareja(ClaseLexica.PCIERRE);
	             break;
	         case INT: empareja(ClaseLexica.INT); break;
	         case REAL: empareja(ClaseLexica.REAL); break;
	         case TRUE: empareja(ClaseLexica.TRUE); break;
	         case FALSE: empareja(ClaseLexica.FALSE); break;
	         case IDEN: empareja(ClaseLexica.IDEN); break;
	         default: errores.errorSintactico(anticipo.fila(),anticipo.columna(),anticipo.clase(),
	                                          ClaseLexica.TRUE, ClaseLexica.FALSE, ClaseLexica.INT,
	                                          ClaseLexica.REAL, ClaseLexica.IDEN, ClaseLexica.PAP);                                            
	     }
	}
   
   private void OpIn0AsocD() {
	     switch(anticipo.clase()) {
	         case MAS: empareja(ClaseLexica.MAS);
	              break;
	         default: errores.errorSintactico(anticipo.fila(),anticipo.columna(),anticipo.clase(),
	                                          ClaseLexica.MAS);                                            
	     }
	}
   
   private void OpIn0NoAsoc() {
	     switch(anticipo.clase()) {
	         case MENOS: empareja(ClaseLexica.MENOS);
	              break;
	         default: errores.errorSintactico(anticipo.fila(),anticipo.columna(),anticipo.clase(),
	                                          ClaseLexica.MENOS);                                            
	     }
	}
   
   private void OpIn1AsocI() {
	     switch(anticipo.clase()) {
	         case AND: empareja(ClaseLexica.AND);
	              break;
	         case OR: empareja(ClaseLexica.OR); break;
	         default: errores.errorSintactico(anticipo.fila(),anticipo.columna(),anticipo.clase(),
	                                          ClaseLexica.OR, ClaseLexica.AND);                                            
	     }
	}
   
   private void OpIn2AsocI() {
	     switch(anticipo.clase()) {
	         case MENOR: empareja(ClaseLexica.MENOR); break;
	         case MENIG: empareja(ClaseLexica.MENIG); break;
	         case MAYIG: empareja(ClaseLexica.MAYIG); break;
	         case MAYOR: empareja(ClaseLexica.MAYOR); break;
	         case IGUAL: empareja(ClaseLexica.IGUAL); break;
	         case DESIGUAL: empareja(ClaseLexica.DESIGUAL); break;
	         default: errores.errorSintactico(anticipo.fila(),anticipo.columna(),anticipo.clase(),
	        		 ClaseLexica.IGUAL, ClaseLexica.DESIGUAL, 
                     ClaseLexica.MENOR, ClaseLexica.MAYOR, ClaseLexica.MENIG,
                     ClaseLexica.MAYIG);                                             
	     }
	}
   
   private void OpIn3NoAsoc() {
	     switch(anticipo.clase()) {
	         case POR: empareja(ClaseLexica.POR); break;
	         case DIV: empareja(ClaseLexica.DIV); break;
	         default: errores.errorSintactico(anticipo.fila(),anticipo.columna(),anticipo.clase(),
	                                          ClaseLexica.DIV, ClaseLexica.POR);                                            
	     }
	}
   
   private void OpPre4NoAsoc() {
	     switch(anticipo.clase()) {
	         case MENOS: empareja(ClaseLexica.MENOS); break;
	         default: errores.errorSintactico(anticipo.fila(),anticipo.columna(),anticipo.clase(),
	                                          ClaseLexica.MENOS);                                            
	     }
	}
   
   private void OpPre4Asoc() {
	     switch(anticipo.clase()) {
	     	 case NOT: empareja(ClaseLexica.NOT); break;
	         default: errores.errorSintactico(anticipo.fila(),anticipo.columna(),anticipo.clase(),
	                                          ClaseLexica.NOT);                                            
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
