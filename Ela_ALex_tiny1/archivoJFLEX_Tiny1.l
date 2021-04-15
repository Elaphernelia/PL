package alex;
%EL QUE FUNCIONA
%%
%line
%column
%class AnalizadorLexicoTinyUno
%type  UnidadLexica
%unicode

%{
  private ALexOperations ops;
  public String lexema() {return yytext();}
  public int fila() {return yyline+1;}
  public int columna() {return yycolumn+1;}
%}

%eofval{
  return ops.unidadEof();
%eofval}

%init{
  ops = new ALexOperations(this);
%init}

letra  = ([A-Z]|[a-z])
digito = [0-9]
dig_nozero = [1-9]
entero = [\+|\-]?(0|{dig_nozero}{digito}*)
decimal = .(0|{digito}*{dig_nozero})
exponencial = [e|E]{entero}
real = {entero}({decimal}|{exponencial}|{decimal}{exponencial})
not = not
por = \*
div = \/
menor = <
mayor = >
menig = <\=
mayig = >\=
igual = \=\=
desigual = \!\=
and = and
or = or
mas = \+
menos = \-
igualdad = \=
pap = \(
pcier = \)
puntocom = ;
separador = &&
modulo = %
sqap = \[
sqcier = \]
corap = \{
corcier = \}
punto = \.
coma = \,
flecha = \->
ampsand = &
int = int
realw = real
bool = bool
true = true
false = false
string = string
null = null
proc = proc
if = if
then = then
else = else
endif = endif
while = while
do = do
endwhile = endwhile
call = call
record = record
array = array
of = of
pointer = pointer
new = new
delete = delete
read = read
write = write
nl = nl
var = var
type = type
identificador = {letra}({letra}|{digito}|_)*
litent = {entero}
litreal = {real}
litcad = \"[^\"\b\r\n]*\"
sep = [ |\n|\r|\t|\b]
comentario = #[^\n]*
%%
{sep}               	  {}
{comentario}              {}
{not}                     {return ops.unidadNot();}
{por}                     {return ops.unidadPor();}
{div}          			  {return ops.unidadDiv();}
{menor}            		  {return ops.unidadMenor();}
{mayor}              	  {return ops.unidadMayor();}
{menig}            		  {return ops.unidadMenIg();}
{mayig}           		  {return ops.unidadMayIg();}
{igual}  				  {return ops.unidadIgual();}
{desigual}        		  {return ops.unidadDesigual();}
{and}      				  {return ops.unidadAnd();}
{or}        			  {return ops.unidadOr();} 
{mas}                     {return ops.unidadMas();} 
{menos}                   {return ops.unidadMenos();}
{igualdad} 				  {return ops.unidadIgualdad();}
{pap} 					  {return ops.unidadPAp();}
{pcier} 				  {return ops.unidadPCier();}
{puntocom} 			      {return ops.unidadPuntoCom();}
{coma} 					  {return ops.unidadComa();}
{ampsand} 				  {return ops.unidadAmpsand();}
{separador} 			  {return ops.unidadSeparador();}
{modulo} 			      {return ops.unidadModulo();}
{sqap} 					  {return ops.unidadSqAp();}
{sqcier} 				  {return ops.unidadSqCier();}
{corap} 				  {return ops.unidadCorAp();}
{corcier} 				  {return ops.unidadCorCier();}
{punto} 				  {return ops.unidadPunto();}
{flecha} 				  {return ops.unidadFlecha();}
{int} 					  {return ops.unidadInt();}
{realw} 				  {return ops.unidadReal();}
{bool} 					  {return ops.unidadBool();}
{true} 					  {return ops.unidadTrue();}
{false} 				  {return ops.unidadFalse();}
{string} 			      {return ops.unidadString();}
{null} 					  {return ops.unidadNull();}
{proc} 					  {return ops.unidadProc();}
{if} 					  {return ops.unidadIf();}
{then} 					  {return ops.unidadThen();}
{else} 					  {return ops.unidadElse();}
{endif} 				  {return ops.unidadEndif();}
{while} 				  {return ops.unidadWhile();}
{do} 					  {return ops.unidadDo();}
{endwhile} 				  {return ops.unidadEndwhile();}
{call} 					  {return ops.unidadCall();}
{record} 				  {return ops.unidadRecord();}
{array} 				  {return ops.unidadArray();}
{of} 					  {return ops.unidadOf();}
{pointer} 				  {return ops.unidadPointer();}
{new} 					  {return ops.unidadNew();}
{delete}				  {return ops.unidadDelete();}
{read} 					  {return ops.unidadRead();}
{write} 				  {return ops.unidadWrite();}
{nl} 					  {return ops.unidadNl();}
{var} 					  {return ops.unidadVar();}
{type} 					  {return ops.unidadType();}
{identificador} 		  {return ops.unidadIdentificador();}
{litreal} 				  {return ops.unidadLitreal();}
{litent} 				  {return ops.unidadLitent();}
{litcad} 				  {return ops.unidadLitcad();}
[^]                       {ops.error();}  