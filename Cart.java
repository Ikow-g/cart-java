
public class Cart {
	private String judul;
	private int jumlah;
	private int harga;
	private String versi;
	
	public Cart(String judul, int jumlah, int harga, String versi) {
		super();
		this.judul = judul;
		this.jumlah = jumlah;
		this.harga = harga;
		this.versi = versi;
	}

	public String getVersi() {
		return versi;
	}

	public void setVersi(String versi) {
		this.versi = versi;
	}

	public String getJudul() {
		return judul;
	}

	public void setJudul(String judul) {
		this.judul = judul;
	}

	public int getJumlah() {
		return jumlah;
	}

	public void setJumlah(int jumlah) {
		this.jumlah = jumlah;
	}

	public int getHarga() {
		return harga;
	}

	public void setHarga(int harga) {
		this.harga = harga;
	}
	
	public void show() {
		System.out.println(this.judul+"\t| "+this.jumlah+"\t| "+this.harga*this.jumlah+"\t| "+this.versi+"\t|");
	}
	
}
