package asint;

import alex.StringLocalizado;
import asint.TinyASint.Exp;

public class OpInfo {
	public String op;
	public Exp a;
	public StringLocalizado var;

	public OpInfo(String op, Exp a, StringLocalizado var) {
		this.op = op; this.a = a; this.var = var;
	}
}
