package xyz.sonion.rsa;


public class RsaSecret {
	private MyBigInteger p;
	private MyBigInteger q;
	private MyBigInteger d;
	private MyBigInteger n;

	RsaSecret(MyBigInteger p, MyBigInteger q, MyBigInteger d) {
		this.p = p;
		this.q = q;
		this.d = d;
		this.n=p.multiply(q);
	}

	public MyBigInteger getP() {
		return p;
	}

	public MyBigInteger getQ() {
		return q;
	}

	public MyBigInteger getD() {
		return d;
	}

	public MyBigInteger getN() {
		return n;
	}
}
