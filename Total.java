import java.io.*;
public class Total <T> extends Payment implements Serializable{

	private int harga;
	private int pembayaran;
	private int kembalian;
	
	public Total(int harga, int pembayaran, int kembalian) {
		super();
		this.harga = harga;
		this.pembayaran = pembayaran;
		this.kembalian = kembalian;
	}

	public int getHarga() {
		return harga;
	}

	public void setHarga(int harga) {
		this.harga = harga;
	}

	public int getPembayaran() {
		return pembayaran;
	}

	public void setPembayaran(int pembayaran) {
		this.pembayaran = pembayaran;
	}

	public int getKembalian() {
		return kembalian;
	}

	public void setKembalian(int kembalian) {
		this.kembalian = kembalian;
	}

	@Override
	public <T> void print(T whatever) {
		System.out.println(whatever);
		
	}
	
	

}
