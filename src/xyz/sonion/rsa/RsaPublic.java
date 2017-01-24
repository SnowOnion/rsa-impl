package xyz.sonion.rsa;

public class RsaPublic {
	private MyBigInteger n;
	private MyBigInteger e;

	RsaPublic(MyBigInteger n, MyBigInteger e) {
		this.n = n;
		this.e = e;
	}

	public MyBigInteger getN() {
		return n;
	}

	public MyBigInteger getE() {
		return e;
	}
}
