package alex;

import asint.asc.ClaseLexica;

public class ALexOperations {
  private AnalizadorLexicoTinyUno alex;
  public ALexOperations(AnalizadorLexicoTinyUno alex) {
   this.alex = alex;   
  }
  
  public UnidadLexica unidadEof() {
     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.EOF, "<EOF>"); 
  }
  public UnidadLexica unidadNot() {
     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.NOT, "not"); 
  } 
  public UnidadLexica unidadPor() {
     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.POR, "*"); 
  } 
  public UnidadLexica unidadDiv() {
     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.DIV, "/"); 
  } 
  public UnidadLexica unidadMenor() {
     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.MENOR, "<"); 
  } 
  public UnidadLexica unidadMayor() {
     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.MAYOR, ">"); 
  } 
  public UnidadLexica unidadMenIg() {
     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.MENIG, "<="); 
  } 
  public UnidadLexica unidadMayIg() {
     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.MAYIG, ">="); 
  } 
  public UnidadLexica unidadIgual() {
     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.IGUAL, "=="); 
  } 
  public UnidadLexica unidadDesigual() {
     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.DESIGUAL, "!="); 
  } 
  public UnidadLexica unidadAnd() {
     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.AND, "and"); 
  } 
  public UnidadLexica unidadOr() {
     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.OR, "or"); 
  }
  public UnidadLexica unidadMas() {
     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.MAS, "+"); 
  }
  public UnidadLexica unidadMenos() {
     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.MENOS, "-"); 
  }
  public UnidadLexica unidadIgualdad() {
     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.IGUALDAD, "="); 
  }
  public UnidadLexica unidadPAp() {
     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.PAP, "("); 
  }
  public UnidadLexica unidadPCier() {
     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.PCIER, ")"); 
  }
  public UnidadLexica unidadPuntoCom() {
     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.PUNTOCOM, ";"); 
  }
  public UnidadLexica unidadSeparador() {
     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.SEPARADOR, "&&"); 
  }
  public UnidadLexica unidadModulo() {
     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.MODULO, "%"); 
  }
  public UnidadLexica unidadSqAp() {
     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.SQAP, "["); 
  }
  public UnidadLexica unidadSqCier() {
     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.SQCIER, "]"); 
  }
  public UnidadLexica unidadCorAp() {
     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.CORAP, "{"); 
  }
  public UnidadLexica unidadCorCier() {
     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.CORCIER, "}"); 
  }
  public UnidadLexica unidadPunto() {
     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.PUNTO, "."); 
  }
  public UnidadLexica unidadComa() {
     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.COMA, ","); 
  }
  public UnidadLexica unidadFlecha() {
     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.FLECHA, "->"); 
  }
  public UnidadLexica unidadAmpsand() {
     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.AMPSAND, "&"); 
  }
  public UnidadLexica unidadInt() {
     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.INT, "int"); 
  }
  public UnidadLexica unidadReal() {
     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.REAL, "real"); 
  }
  public UnidadLexica unidadBool() {
     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.BOOL, "bool"); 
  }
  public UnidadLexica unidadTrue() {
     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.TRUE, "true"); 
  }
  public UnidadLexica unidadFalse() {
     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.FALSE, "false"); 
  }
  public UnidadLexica unidadString() {
     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.STRING, "string"); 
  }
  public UnidadLexica unidadNull() {
     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.NULL, "null"); 
  }
  public UnidadLexica unidadProc() {
     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.PROC, "proc"); 
  }
  public UnidadLexica unidadIf() {
     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.IF, "if"); 
  }
  public UnidadLexica unidadThen() {
     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.THEN, "then"); 
  }
  public UnidadLexica unidadElse() {
     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.ELSE, "else"); 
  }
  public UnidadLexica unidadEndif() {
     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.ENDIF, "endif"); 
  }
  public UnidadLexica unidadWhile() {
     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.WHILE, "while"); 
  }
  public UnidadLexica unidadDo() {
     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.DO, "do");	 
  }
  public UnidadLexica unidadEndwhile() {
     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.ENDWHILE, "endwhile"); 
  }
  public UnidadLexica unidadCall() {
     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.CALL, "call"); 
  }
  public UnidadLexica unidadRecord() {
     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.RECORD, "record"); 
  }
  public UnidadLexica unidadArray() {
     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.ARRAY, "array"); 
  }
  public UnidadLexica unidadOf() {
     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.OF, "of"); 
  }
  public UnidadLexica unidadPointer() {
     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.POINTER, "pointer"); 
  }
  public UnidadLexica unidadNew() {
     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.NEW, "new"); 
  }
  public UnidadLexica unidadDelete() {
     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.DELETE, "delete"); 
  }
  public UnidadLexica unidadRead() {
     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.READ, "read"); 
  }
  public UnidadLexica unidadWrite() {
     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.WRITE, "write"); 
  }
  public UnidadLexica unidadNl() {
     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.NL, "nl"); 
  }
  public UnidadLexica unidadVar() {
     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.VAR, "var"); 
  }
  public UnidadLexica unidadType() {
     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.TYPE, "type"); 
  }
    public UnidadLexica unidadIdentificador() {
     return new UnidadLexicaMultivaluada(alex.fila(), alex.columna(),ClaseLexica.IDENTIFICADOR,alex.lexema()); 
  } 
  public UnidadLexica unidadLitent() {
     return new UnidadLexicaMultivaluada(alex.fila(), alex.columna(),ClaseLexica.LITENT,alex.lexema()); 
  } 
      public UnidadLexica unidadLitreal() {
     return new UnidadLexicaMultivaluada(alex.fila(), alex.columna(),ClaseLexica.LITREAL,alex.lexema()); 
  } 
      public UnidadLexica unidadLitcad() {
     return new UnidadLexicaMultivaluada(alex.fila(), alex.columna(),ClaseLexica.LITCAD,alex.lexema()); 
  } 
  public void error() {
    System.err.println("***"+alex.fila()+" "+alex.columna()+" Caracter inesperado: "+alex.lexema());
    System.exit(1);
  }
}
