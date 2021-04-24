package alex;

public class ALexOperations {
  private AnalizadorLexicoTinyUno alex;
  public ALexOperations(AnalizadorLexicoTinyUno alex) {
   this.alex = alex;   
  }
  
  public UnidadLexica unidadEof() {
     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.EOF); 
  }
  public UnidadLexica unidadNot() {
     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.NOT); 
  } 
  public UnidadLexica unidadPor() {
     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.POR); 
  } 
  public UnidadLexica unidadDiv() {
     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.DIV); 
  } 
  public UnidadLexica unidadMenor() {
     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.MENOR); 
  } 
  public UnidadLexica unidadMayor() {
     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.MAYOR); 
  } 
  public UnidadLexica unidadMenIg() {
     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.MENIG); 
  } 
  public UnidadLexica unidadMayIg() {
     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.MAYIG); 
  } 
  public UnidadLexica unidadIgual() {
     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.IGUAL); 
  } 
  public UnidadLexica unidadDesigual() {
     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.DESIGUAL); 
  } 
  public UnidadLexica unidadAnd() {
     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.AND); 
  } 
  public UnidadLexica unidadOr() {
     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.OR); 
  }
  public UnidadLexica unidadMas() {
     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.MAS); 
  }
  public UnidadLexica unidadMenos() {
     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.MENOS); 
  }
  public UnidadLexica unidadIgualdad() {
     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.IGUALDAD); 
  }
  public UnidadLexica unidadPAp() {
     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.PAP); 
  }
  public UnidadLexica unidadPCier() {
     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.PCIER); 
  }
  public UnidadLexica unidadPuntoCom() {
     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.PUNTOCOM); 
  }
  public UnidadLexica unidadSeparador() {
     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.SEPARADOR); 
  }
  public UnidadLexica unidadModulo() {
     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.MODULO); 
  }
  public UnidadLexica unidadSqAp() {
     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.SQAP); 
  }
  public UnidadLexica unidadSqCier() {
     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.SQCIER); 
  }
  public UnidadLexica unidadCorAp() {
     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.CORAP); 
  }
  public UnidadLexica unidadCorCier() {
     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.CORCIER); 
  }
  public UnidadLexica unidadPunto() {
     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.PUNTO); 
  }
  public UnidadLexica unidadComa() {
     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.COMA); 
  }
  public UnidadLexica unidadFlecha() {
     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.FLECHA); 
  }
  public UnidadLexica unidadAmpsand() {
     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.AMPSAND); 
  }
  public UnidadLexica unidadInt() {
     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.INT); 
  }
  public UnidadLexica unidadReal() {
     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.REAL); 
  }
  public UnidadLexica unidadBool() {
     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.BOOL); 
  }
  public UnidadLexica unidadTrue() {
     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.TRUE); 
  }
  public UnidadLexica unidadFalse() {
     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.FALSE); 
  }
  public UnidadLexica unidadString() {
     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.STRING); 
  }
  public UnidadLexica unidadNull() {
     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.NULL); 
  }
  public UnidadLexica unidadProc() {
     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.PROC); 
  }
  public UnidadLexica unidadIf() {
     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.IF); 
  }
  public UnidadLexica unidadThen() {
     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.THEN); 
  }
  public UnidadLexica unidadElse() {
     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.ELSE); 
  }
  public UnidadLexica unidadEndif() {
     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.ENDIF); 
  }
  public UnidadLexica unidadWhile() {
     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.WHILE); 
  }
  public UnidadLexica unidadDo() {
     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.DO);	 
  }
  public UnidadLexica unidadEndwhile() {
     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.ENDWHILE); 
  }
  public UnidadLexica unidadCall() {
     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.CALL); 
  }
  public UnidadLexica unidadRecord() {
     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.RECORD); 
  }
  public UnidadLexica unidadArray() {
     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.ARRAY); 
  }
  public UnidadLexica unidadOf() {
     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.OF); 
  }
  public UnidadLexica unidadPointer() {
     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.POINTER); 
  }
  public UnidadLexica unidadNew() {
     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.NEW); 
  }
  public UnidadLexica unidadDelete() {
     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.DELETE); 
  }
  public UnidadLexica unidadRead() {
     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.READ); 
  }
  public UnidadLexica unidadWrite() {
     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.WRITE); 
  }
  public UnidadLexica unidadNl() {
     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.NL); 
  }
  public UnidadLexica unidadVar() {
     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.VAR); 
  }
  public UnidadLexica unidadType() {
     return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.TYPE); 
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
