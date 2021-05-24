package semops;

import asint.TinyASint;

public class SemOps extends TinyASint {
	public Prog prog(Decs decs, Insts insts) {
		if (decs == null) return prog_sin_decs(insts);
		else return prog_con_decs(decs, insts);
	}
	
	public Exp opera_uno(String op, Exp arg) {
		switch (op) {
			case "-": return m_unario(arg);
			case "not": return not(arg);
			case "*": return indireccion(arg);
		}
		throw new UnsupportedOperationException("exp " + op);
	}
	
	public Exp opera_dos(String op, Exp arg0, Exp arg1) {
		switch (op) {
			case "+": return suma(arg0, arg1);
			case "-": return resta(arg0, arg1);
			case "and": return and(arg0, arg1);
			case "or": return or(arg0, arg1);
			case "<": return menor(arg0, arg1);
			case "<=": return men_ig(arg0, arg1);
			case ">": return mayor(arg0, arg1);
			case ">=": return may_ig(arg0, arg1);
			case "==": return igual(arg0, arg1);
			case "!=": return desigual(arg0, arg1);
			case "*": return mul(arg0, arg1);
			case "/": return div(arg0, arg1);
			case "%": return modulo(arg0, arg1);
		}
		throw new UnsupportedOperationException("exp "+op);
	}
	
	public Exp opera_opposcincoasoc(String op, Exp arg_a, StringLocalizado arg_v, Exp arg1) {
		switch (op) {
			case "index": return indexacion(arg1, arg_a);
			case "reg": return acc_registro(arg1, arg_v);
		}
		
		throw new UnsupportedOperationException("exp "+op);
	}
}
