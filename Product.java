import java.io.Serializable;
public class Product implements Serializable{
	private int id;
	private String judul;
	private int tahun;
	private int harga_printed;
	private int harga_digital;
	private String subject;
	
	public Product(int id, String judul, int tahun, int harga_printed, int harga_digital, String subject) {
		this.id = id;
		this.judul = judul;
		this.tahun = tahun;
		this.harga_printed = harga_printed;
		this.harga_digital = harga_digital;
		this.subject = subject;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getJudul() {
		return judul;
	}

	public void setJudul(String judul) {
		this.judul = judul;
	}

	public int getTahun() {
		return tahun;
	}

	public void setTahun(int tahun) {
		this.tahun = tahun;
	}

	public int getHarga_printed() {
		return harga_printed;
	}

	public void setHarga_printed(int harga_printed) {
		this.harga_printed = harga_printed;
	}

	public int getHarga_digital() {
		return harga_digital;
	}

	public void setHarga_digital(int harga_digital) {
		this.harga_digital = harga_digital;
	}
	
	public void show() {
		System.out.println("| "+this.id+" | "+this.judul+"\t| "+this.tahun+"\t| "+this.harga_printed+"\t| "+this.harga_digital+"\t\t|");
	}
	
}
