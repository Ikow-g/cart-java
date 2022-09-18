import java.io.*;

public class Subject implements Serializable{
	private String judul_subj;
	
	public Subject(String judul_subj) {
		this.judul_subj = judul_subj;
	}
	public String getJudul_subj() {
		return judul_subj;
	}
	public void setJudul_subj(String judul_subj) {
		this.judul_subj = judul_subj;
	}

	public void show() {
		System.out.println(this.judul_subj);
	}
	
}
