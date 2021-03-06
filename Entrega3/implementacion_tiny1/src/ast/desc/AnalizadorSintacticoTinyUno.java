/* Generated By:JavaCC: Do not edit this line. AnalizadorSintacticoTinyUno.java */
package ast.desc;

import alex.StringLocalizado;
import asint.OpInfo;
import asint.TinyASint.*;
import semops.SemOps;

public class AnalizadorSintacticoTinyUno implements AnalizadorSintacticoTinyUnoConstants {
  private SemOps sem = new SemOps();

  final public Prog Sp() throws ParseException {
                        Prog p;
    p = Programa();
    jj_consume_token(0);
                               {if (true) return p;}
    throw new Error("Missing return statement in function");
  }

  final public Prog Programa() throws ParseException {
                        Decs ds; Insts i;
    ds = Decs();
    i = Insts();
                               {if (true) return sem.prog(ds,i);}
    throw new Error("Missing return statement in function");
  }

  final public Decs Decs() throws ParseException {
                        Decs ds;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case Proc:
    case Var:
    case Type:
      ds = LDecs();
      jj_consume_token(44);
                            {if (true) return ds;}
      break;
    default:
      jj_la1[0] = jj_gen;
            {if (true) return null;}
    }
    throw new Error("Missing return statement in function");
  }

  final public Decs LDecs() throws ParseException {
                  Dec d;Decs ds;
    d = Dec();
    ds = RLDecs(sem.decs_una(d));
                                               {if (true) return ds;}
    throw new Error("Missing return statement in function");
  }

  final public Decs RLDecs(Decs decsh) throws ParseException {
                             Dec d;Decs ds;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 45:
      jj_consume_token(45);
      d = Dec();
      ds = RLDecs(sem.decs_muchas(decsh,d));
                                                            {if (true) return ds;}
      break;
    default:
      jj_la1[1] = jj_gen;
            {if (true) return decsh;}
    }
    throw new Error("Missing return statement in function");
  }

  final public Dec Dec() throws ParseException {
               Token id; Tipo t; PFs pfs; Bloque bloq;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case Var:
      jj_consume_token(Var);
      t = TypN();
      id = jj_consume_token(Identificador);
                                         {if (true) return sem.var(t,new StringLocalizado(id));}
      break;
    case Type:
      jj_consume_token(Type);
      t = TypN();
      id = jj_consume_token(Identificador);
                                              {if (true) return sem.type(t,new StringLocalizado(id));}
      break;
    case Proc:
      jj_consume_token(Proc);
      id = jj_consume_token(Identificador);
      pfs = ParamsF();
      bloq = Bloq();
                                                               {if (true) return sem.proc(new StringLocalizado(id),pfs,bloq);}
      break;
    default:
      jj_la1[2] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  final public PFs ParamsF() throws ParseException {
                    PFs pfs;
    jj_consume_token(46);
    pfs = LParamFOpc();
    jj_consume_token(47);
                                     {if (true) return pfs;}
    throw new Error("Missing return statement in function");
  }

  final public PFs LParamFOpc() throws ParseException {
                       PFs pfs;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case Int:
    case Realw:
    case Bool:
    case String:
    case Record:
    case Array:
    case Pointer:
    case Identificador:
      pfs = LParamF();
                          {if (true) return pfs;}
      break;
    default:
      jj_la1[3] = jj_gen;
            {if (true) return sem.param_f_sin();}
    }
    throw new Error("Missing return statement in function");
  }

  final public PFs LParamF() throws ParseException {
                    PFs pfs; PF pf;
    pf = ParamF();
    pfs = RLParamF(sem.param_f_con_una(pf));
                                                          {if (true) return pfs;}
    throw new Error("Missing return statement in function");
  }

  final public PFs RLParamF(PFs pfsh) throws ParseException {
                             PFs pfs; PF pf;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 48:
      jj_consume_token(48);
      pf = ParamF();
      pfs = RLParamF(sem.param_f_con_muchas(pfsh,pf));
                                                                          {if (true) return pfs;}
      break;
    default:
      jj_la1[4] = jj_gen;
            {if (true) return pfsh;}
    }
    throw new Error("Missing return statement in function");
  }

  final public PF ParamF() throws ParseException {
                 Tipo typn;PF pf;
    typn = TypN();
    pf = RParamF(typn);
                                         {if (true) return pf;}
    throw new Error("Missing return statement in function");
  }

  final public PF RParamF(Tipo typn) throws ParseException {
                           Token t;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case Identificador:
      t = jj_consume_token(Identificador);
                              {if (true) return sem.param_f_noref(typn, new StringLocalizado(t));}
      break;
    case 49:
      jj_consume_token(49);
      t = jj_consume_token(Identificador);
                                  {if (true) return sem.param_f_ref(typn, new StringLocalizado(t));}
      break;
    default:
      jj_la1[5] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  final public Tipo TypN() throws ParseException {
                 Token t;Campos c;Tipo b;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case Int:
    case Realw:
    case Bool:
    case String:
    case Identificador:
      b = BaseType();
                     {if (true) return b;}
      break;
    case Array:
      jj_consume_token(Array);
      jj_consume_token(50);
      t = jj_consume_token(LitEnt);
      jj_consume_token(51);
      jj_consume_token(Of);
      b = BaseType();
                                                     {if (true) return sem.tipo_array(new StringLocalizado(t),b);}
      break;
    case Record:
      jj_consume_token(Record);
      jj_consume_token(52);
      c = LCampos();
      jj_consume_token(53);
                                     {if (true) return sem.tipo_record(c);}
      break;
    case Pointer:
      jj_consume_token(Pointer);
      b = BaseType();
                               {if (true) return sem.tipo_pointer(b);}
      break;
    default:
      jj_la1[6] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  final public Campos LCampos() throws ParseException {
                      Campo c; Campos cs;
    c = Campo();
    cs = RLCampos(sem.campos_uno(c));
                                                     {if (true) return cs;}
    throw new Error("Missing return statement in function");
  }

  final public Campos RLCampos(Campos csh) throws ParseException {
                                 Campo c; Campos cs;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 45:
      jj_consume_token(45);
      c = Campo();
      cs = RLCampos(sem.campos_muchos(csh,c));
                                                                {if (true) return cs;}
      break;
    default:
      jj_la1[7] = jj_gen;
            {if (true) return csh;}
    }
    throw new Error("Missing return statement in function");
  }

  final public Campo Campo() throws ParseException {
                   Token t;Tipo typn;
    typn = TypN();
    t = jj_consume_token(Identificador);
                                          {if (true) return sem.campo(typn,new StringLocalizado(t));}
    throw new Error("Missing return statement in function");
  }

  final public Tipo BaseType() throws ParseException {
                        Tipo tipo;Token t;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case Int:
    case Realw:
    case Bool:
    case String:
      tipo = BasicType();
                             {if (true) return tipo;}
      break;
    case Identificador:
      t = jj_consume_token(Identificador);
                              {if (true) return sem.tipo_iden(new StringLocalizado(t));}
      break;
    default:
      jj_la1[8] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  final public Tipo BasicType() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case Int:
      jj_consume_token(Int);
                  {if (true) return sem.tipo_int();}
      break;
    case Realw:
      jj_consume_token(Realw);
                    {if (true) return sem.tipo_real();}
      break;
    case Bool:
      jj_consume_token(Bool);
                   {if (true) return sem.tipo_bool();}
      break;
    case String:
      jj_consume_token(String);
                     {if (true) return sem.tipo_string();}
      break;
    default:
      jj_la1[9] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  final public Insts Insts() throws ParseException {
                         Inst i;Insts is;
    i = Inst();
    is = RInsts(sem.insts_una(i));
                                                 {if (true) return is;}
    throw new Error("Missing return statement in function");
  }

  final public Insts RInsts(Insts ish) throws ParseException {
                             Inst i;Insts is;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 45:
      jj_consume_token(45);
      i = Inst();
      is = RInsts(sem.insts_muchas(ish,i));
                                                            {if (true) return is;}
      break;
    default:
      jj_la1[10] = jj_gen;
            {if (true) return ish;}
    }
    throw new Error("Missing return statement in function");
  }

  final public Inst Inst() throws ParseException {
                        Exp e1,e2;PInst p;Inst i;Token id;PR pr;Bloque bl;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case Not:
    case True:
    case False:
    case Null:
    case Identificador:
    case LitEnt:
    case LitReal:
    case LitCad:
    case 46:
    case 56:
    case 63:
      e1 = E0();
      jj_consume_token(54);
      e2 = E0();
                                {if (true) return sem.e_igual(e1,e2);}
      break;
    case If:
      jj_consume_token(If);
      e1 = E0();
      jj_consume_token(Then);
      p = LInsts();
      i = RInst(e1,p);
                                                         {if (true) return i;}
      break;
    case While:
      jj_consume_token(While);
      e1 = E0();
      jj_consume_token(Do);
      p = LInsts();
      jj_consume_token(Endwhile);
                                                       {if (true) return sem.while_(e1,p);}
      break;
    case Read:
      jj_consume_token(Read);
      e1 = E0();
                           {if (true) return sem.read(e1);}
      break;
    case Write:
      jj_consume_token(Write);
      e1 = E0();
                            {if (true) return sem.write(e1);}
      break;
    case Nl:
      jj_consume_token(Nl);
                 {if (true) return sem.nl();}
      break;
    case New:
      jj_consume_token(New);
      e1 = E0();
                          {if (true) return sem.new_(e1);}
      break;
    case Delete:
      jj_consume_token(Delete);
      e1 = E0();
                             {if (true) return sem.delete(e1);}
      break;
    case Call:
      jj_consume_token(Call);
      id = jj_consume_token(Identificador);
      pr = ParamsR();
                                                   {if (true) return sem.call(new StringLocalizado(id),pr);}
      break;
    case 52:
      bl = Bloq();
                      {if (true) return sem.bl(bl);}
      break;
    default:
      jj_la1[11] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  final public Inst RInst(Exp eh,PInst ph) throws ParseException {
                                 PInst p;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case Endif:
      jj_consume_token(Endif);
                    {if (true) return sem.if_(eh,ph);}
      break;
    case Else:
      jj_consume_token(Else);
      p = LInsts();
      jj_consume_token(Endif);
                                      {if (true) return sem.ifelse(eh,ph,p);}
      break;
    default:
      jj_la1[12] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  final public PInst LInsts() throws ParseException {
                    Insts is;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case Not:
    case True:
    case False:
    case Null:
    case If:
    case While:
    case Call:
    case New:
    case Delete:
    case Read:
    case Write:
    case Nl:
    case Identificador:
    case LitEnt:
    case LitReal:
    case LitCad:
    case 46:
    case 52:
    case 56:
    case 63:
      is = Insts();
                       {if (true) return sem.lista_con(is);}
      break;
    default:
      jj_la1[13] = jj_gen;
            {if (true) return sem.lista_sin();}
    }
    throw new Error("Missing return statement in function");
  }

  final public PR ParamsR() throws ParseException {
                  PR pr;
    jj_consume_token(46);
    pr = LParamROpc();
    jj_consume_token(47);
                                    {if (true) return pr;}
    throw new Error("Missing return statement in function");
  }

  final public PR LParamROpc() throws ParseException {
                     PR pr;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case Not:
    case True:
    case False:
    case Null:
    case Identificador:
    case LitEnt:
    case LitReal:
    case LitCad:
    case 46:
    case 56:
    case 63:
      pr = LParamR();
                           {if (true) return pr;}
      break;
    default:
      jj_la1[14] = jj_gen;
            {if (true) return sem.param_r_sin();}
    }
    throw new Error("Missing return statement in function");
  }

  final public PR LParamR() throws ParseException {
                  Exp e;PR pr;
    e = E0();
    pr = RLParamR(sem.param_r_con_una(e));
                                                   {if (true) return pr;}
    throw new Error("Missing return statement in function");
  }

  final public PR RLParamR(PR prh) throws ParseException {
                         Exp e;PR pr;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 48:
      jj_consume_token(48);
      e = E0();
      pr = RLParamR(sem.param_r_con_muchas(prh,e));
                                                                  {if (true) return pr;}
      break;
    default:
      jj_la1[15] = jj_gen;
            {if (true) return prh;}
    }
    throw new Error("Missing return statement in function");
  }

  final public Bloque Bloq() throws ParseException {
                        Bloque b;
    jj_consume_token(52);
    b = BloqOpc();
    jj_consume_token(53);
                                {if (true) return b;}
    throw new Error("Missing return statement in function");
  }

  final public Bloque BloqOpc() throws ParseException {
                        Prog p;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case Not:
    case True:
    case False:
    case Null:
    case Proc:
    case If:
    case While:
    case Call:
    case New:
    case Delete:
    case Read:
    case Write:
    case Nl:
    case Var:
    case Type:
    case Identificador:
    case LitEnt:
    case LitReal:
    case LitCad:
    case 46:
    case 52:
    case 56:
    case 63:
      p = Programa();
                         {if (true) return sem.bloque_con(p);}
      break;
    default:
      jj_la1[16] = jj_gen;
            {if (true) return sem.bloque_sin();}
    }
    throw new Error("Missing return statement in function");
  }

  final public Exp E0() throws ParseException {
                        Exp e1, re0;
    e1 = E1();
    re0 = RE0(e1);
                                {if (true) return re0;}
    throw new Error("Missing return statement in function");
  }

  final public Exp RE0(Exp exph) throws ParseException {
                        String op;Exp e;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 55:
      op = OpIn0AsocD();
      e = E0();
                                   {if (true) return sem.opera_dos(op,exph,e);}
      break;
    case 56:
      op = OpIn0NoAsoc();
      e = E1();
                                    {if (true) return sem.opera_dos(op,exph,e);}
      break;
    default:
      jj_la1[17] = jj_gen;
            {if (true) return exph;}
    }
    throw new Error("Missing return statement in function");
  }

  final public Exp E1() throws ParseException {
                        Exp e2, re1;
    e2 = E2();
    re1 = RE1(e2);
                                {if (true) return re1;}
    throw new Error("Missing return statement in function");
  }

  final public Exp RE1(Exp exph) throws ParseException {
                        String op; Exp e2, exp;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case And:
    case Or:
      op = OpIn1AsocI();
      e2 = E2();
      exp = RE1(sem.opera_dos(op,exph,e2));
                                                                       {if (true) return exp;}
      break;
    default:
      jj_la1[18] = jj_gen;
            {if (true) return exph;}
    }
    throw new Error("Missing return statement in function");
  }

  final public Exp E2() throws ParseException {
                        Exp e3, re2;
    e3 = E3();
    re2 = RE2(e3);
                                {if (true) return re2;}
    throw new Error("Missing return statement in function");
  }

  final public Exp RE2(Exp exph) throws ParseException {
                        String op; Exp e3, re2;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 57:
    case 58:
    case 59:
    case 60:
    case 61:
    case 62:
      op = OpIn2AsocI();
      e3 = E3();
      re2 = RE2(sem.opera_dos(op,exph,e3));
                                                                       {if (true) return re2;}
      break;
    default:
      jj_la1[19] = jj_gen;
            {if (true) return exph;}
    }
    throw new Error("Missing return statement in function");
  }

  final public Exp E3() throws ParseException {
                        Exp e4, re3;
    e4 = E4();
    re3 = RE3(e4);
                                {if (true) return re3;}
    throw new Error("Missing return statement in function");
  }

  final public Exp RE3(Exp exph) throws ParseException {
                        String op; Exp e4;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 63:
    case 64:
    case 65:
      op = OpIn3NoAsoc();
      e4 = E4();
                                     {if (true) return sem.opera_dos(op,exph,e4);}
      break;
    default:
      jj_la1[20] = jj_gen;
            {if (true) return exph;}
    }
    throw new Error("Missing return statement in function");
  }

  final public Exp E4() throws ParseException {
                        String op;Exp e;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case Not:
      op = OpPre4Asoc();
      e = E4();
                                   {if (true) return sem.opera_uno(op,e);}
      break;
    case 56:
      op = OpPre4NoAsoc();
      e = E5();
                                     {if (true) return sem.opera_uno(op,e);}
      break;
    case True:
    case False:
    case Null:
    case Identificador:
    case LitEnt:
    case LitReal:
    case LitCad:
    case 46:
    case 63:
      e = E5();
                   {if (true) return e;}
      break;
    default:
      jj_la1[21] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  final public Exp E5() throws ParseException {
                        Exp e, re;
    e = E6();
    re = RE5(e);
                             {if (true) return re;}
    throw new Error("Missing return statement in function");
  }

  final public Exp RE5(Exp exph) throws ParseException {
                        OpInfo opinfo;Exp re;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 50:
    case 66:
    case 67:
      opinfo = OpPos5Asoc();
      re = RE5(sem.opera_opposcincoasoc(opinfo.op,opinfo.a,opinfo.var,exph));
                                                                                                     {if (true) return re;}
      break;
    default:
      jj_la1[22] = jj_gen;
            {if (true) return exph;}
    }
    throw new Error("Missing return statement in function");
  }

  final public Exp E6() throws ParseException {
                        String op;Exp e;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 63:
      op = OpPre6Asoc();
      e = E6();
                                   {if (true) return sem.opera_uno(op,e);}
      break;
    case True:
    case False:
    case Null:
    case Identificador:
    case LitEnt:
    case LitReal:
    case LitCad:
    case 46:
      e = E7();
                   {if (true) return e;}
      break;
    default:
      jj_la1[23] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  final public Exp E7() throws ParseException {
                        Exp e;Token t;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 46:
      jj_consume_token(46);
      e = E0();
      jj_consume_token(47);
                           {if (true) return e;}
      break;
    case LitEnt:
      t = jj_consume_token(LitEnt);
                       {if (true) return sem.entero(new StringLocalizado(t));}
      break;
    case LitReal:
      t = jj_consume_token(LitReal);
                        {if (true) return sem.real(new StringLocalizado(t));}
      break;
    case LitCad:
      t = jj_consume_token(LitCad);
                       {if (true) return sem.cadena(new StringLocalizado(t));}
      break;
    case True:
      jj_consume_token(True);
                   {if (true) return sem.verdadero();}
      break;
    case False:
      jj_consume_token(False);
                    {if (true) return sem.falso();}
      break;
    case Identificador:
      t = jj_consume_token(Identificador);
                              {if (true) return sem.identificador(new StringLocalizado(t));}
      break;
    case Null:
      jj_consume_token(Null);
                   {if (true) return sem.null_();}
      break;
    default:
      jj_la1[24] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  final public String OpIn0AsocD() throws ParseException {
    jj_consume_token(55);
                {if (true) return "+";}
    throw new Error("Missing return statement in function");
  }

  final public String OpIn0NoAsoc() throws ParseException {
    jj_consume_token(56);
                {if (true) return "-";}
    throw new Error("Missing return statement in function");
  }

  final public String OpIn1AsocI() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case And:
      jj_consume_token(And);
                  {if (true) return "and";}
      break;
    case Or:
      jj_consume_token(Or);
                 {if (true) return "or";}
      break;
    default:
      jj_la1[25] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  final public String OpIn2AsocI() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 57:
      jj_consume_token(57);
                {if (true) return "<";}
      break;
    case 58:
      jj_consume_token(58);
                 {if (true) return "<=";}
      break;
    case 59:
      jj_consume_token(59);
                {if (true) return ">";}
      break;
    case 60:
      jj_consume_token(60);
                 {if (true) return ">=";}
      break;
    case 61:
      jj_consume_token(61);
                 {if (true) return "==";}
      break;
    case 62:
      jj_consume_token(62);
                 {if (true) return "!=";}
      break;
    default:
      jj_la1[26] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  final public String OpIn3NoAsoc() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 63:
      jj_consume_token(63);
                {if (true) return "*";}
      break;
    case 64:
      jj_consume_token(64);
                {if (true) return "/";}
      break;
    case 65:
      jj_consume_token(65);
                {if (true) return "%";}
      break;
    default:
      jj_la1[27] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  final public String OpPre4NoAsoc() throws ParseException {
    jj_consume_token(56);
                {if (true) return "-";}
    throw new Error("Missing return statement in function");
  }

  final public String OpPre4Asoc() throws ParseException {
    jj_consume_token(Not);
                  {if (true) return "not";}
    throw new Error("Missing return statement in function");
  }

  final public OpInfo OpPos5Asoc() throws ParseException {
                          Exp e;Token id;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 50:
      jj_consume_token(50);
      e = E0();
      jj_consume_token(51);
                           {if (true) return new OpInfo("index",e,null);}
      break;
    case 66:
      jj_consume_token(66);
      id = jj_consume_token(Identificador);
                                   {if (true) return new OpInfo("reg",null,new StringLocalizado(id));}
      break;
    case 67:
      jj_consume_token(67);
      id = jj_consume_token(Identificador);
                                    {if (true) return new OpInfo("regin",null,new StringLocalizado(id));}
      break;
    default:
      jj_la1[28] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  final public String OpPre6Asoc() throws ParseException {
    jj_consume_token(63);
                {if (true) return "*";}
    throw new Error("Missing return statement in function");
  }

  public AnalizadorSintacticoTinyUnoTokenManager token_source;
  SimpleCharStream jj_input_stream;
  public Token token, jj_nt;
  private int jj_ntk;
  private int jj_gen;
  final private int[] jj_la1 = new int[29];
  static private int[] jj_la1_0;
  static private int[] jj_la1_1;
  static private int[] jj_la1_2;
  static {
      jj_la1_0();
      jj_la1_1();
      jj_la1_2();
   }
   private static void jj_la1_0() {
      jj_la1_0 = new int[] {0x100000,0x0,0x100000,0x6004e000,0x0,0x0,0x6004e000,0x0,0x4e000,0x4e000,0x0,0x122b0400,0x1800000,0x122b0400,0xb0400,0x0,0x123b0400,0x0,0x1800,0x0,0x0,0xb0400,0x0,0xb0000,0xb0000,0x1800,0x0,0x0,0x0,};
   }
   private static void jj_la1_1() {
      jj_la1_1 = new int[] {0xc0,0x2000,0xc0,0x101,0x10000,0x20100,0x101,0x2000,0x100,0x0,0x2000,0x81104f3e,0x0,0x81104f3e,0x81004f00,0x10000,0x81104ffe,0x1800000,0x0,0x7e000000,0x80000000,0x81004f00,0x40000,0x80004f00,0x4f00,0x0,0x7e000000,0x80000000,0x40000,};
   }
   private static void jj_la1_2() {
      jj_la1_2 = new int[] {0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x3,0x0,0xc,0x0,0x0,0x0,0x0,0x3,0xc,};
   }

  public AnalizadorSintacticoTinyUno(java.io.InputStream stream) {
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new AnalizadorSintacticoTinyUnoTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 29; i++) jj_la1[i] = -1;
  }

  public void ReInit(java.io.InputStream stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 29; i++) jj_la1[i] = -1;
  }

  public AnalizadorSintacticoTinyUno(java.io.Reader stream) {
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new AnalizadorSintacticoTinyUnoTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 29; i++) jj_la1[i] = -1;
  }

  public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 29; i++) jj_la1[i] = -1;
  }

  public AnalizadorSintacticoTinyUno(AnalizadorSintacticoTinyUnoTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 29; i++) jj_la1[i] = -1;
  }

  public void ReInit(AnalizadorSintacticoTinyUnoTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 29; i++) jj_la1[i] = -1;
  }

  final private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token.kind == kind) {
      jj_gen++;
      return token;
    }
    token = oldToken;
    jj_kind = kind;
    throw generateParseException();
  }

  final public Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    jj_gen++;
    return token;
  }

  final public Token getToken(int index) {
    Token t = token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  final private int jj_ntk() {
    if ((jj_nt=token.next) == null)
      return (jj_ntk = (token.next=token_source.getNextToken()).kind);
    else
      return (jj_ntk = jj_nt.kind);
  }

  private java.util.Vector jj_expentries = new java.util.Vector();
  private int[] jj_expentry;
  private int jj_kind = -1;

  public ParseException generateParseException() {
    jj_expentries.removeAllElements();
    boolean[] la1tokens = new boolean[68];
    for (int i = 0; i < 68; i++) {
      la1tokens[i] = false;
    }
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 29; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
          }
          if ((jj_la1_1[i] & (1<<j)) != 0) {
            la1tokens[32+j] = true;
          }
          if ((jj_la1_2[i] & (1<<j)) != 0) {
            la1tokens[64+j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 68; i++) {
      if (la1tokens[i]) {
        jj_expentry = new int[1];
        jj_expentry[0] = i;
        jj_expentries.addElement(jj_expentry);
      }
    }
    int[][] exptokseq = new int[jj_expentries.size()][];
    for (int i = 0; i < jj_expentries.size(); i++) {
      exptokseq[i] = (int[])jj_expentries.elementAt(i);
    }
    return new ParseException(token, exptokseq, tokenImage);
  }

  final public void enable_tracing() {
  }

  final public void disable_tracing() {
  }

}
