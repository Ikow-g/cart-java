import java.util.*;
import java.io.*;

public class Main {
	static Scanner read = new Scanner(System.in);
	static User student = new User("student", "student", "student");
	static User admin = new User("admin", "admin", "admin");
	static Module module = new Module(0, null, 0, 0, 0, null);
	static Prev_exam prev = new Prev_exam(0, null, 0, 0, 0, null);
	static Practice practice = new Practice(0, null, 0, 0, 0, null);
	static Total total = new Total(0, 0, 0);
	
	static ObjectOutputStream objOut = null;
	static ObjectInputStream readStream = null;
	
	static File fileSubject = new File("subject.txt");
	static File fileModule = new File("module.txt");
	static File filePrev = new File("prev.txt");
	static File filePrac = new File("practice.txt");
	
	static ListIterator li = null;
	
	public static ArrayList<Subject> aSubject = new ArrayList<>();
	public static ArrayList<Module> aModule = new ArrayList<>();
	public static ArrayList<Prev_exam> aPrev = new ArrayList<>();
	public static ArrayList<Practice> aPractice = new ArrayList<>();
	public static ArrayList<Cart> aCart = new ArrayList<>();
	public static ArrayList<Total> aTotal = new ArrayList<>();
	
	static String subj = null;
	static int print = 0;
	static int digital = 0;
	
	public static void judul() {
		System.out.println("===== W E L C O M E ====");
		System.out.println("==== e - smartplace ====");
		System.out.println("1. Login");
		System.out.println("2. Exit");
	}
	
	public static int login(){
		String username,password;
		System.out.print("Username : ");
		username = read.next();
		System.out.print("Password : ");
		password = read.next();
		
		if(username.equals(student.getUser())&&password.equals(student.getPass())){
			return 1;
		}else if(username.equals(admin.getUser())&&password.equals(admin.getPass())){
			return 2;
		}else{
			System.out.println("Username atau password salah!");
			return 0;
		}
	}
	
	//========================================================================================================================================
	
	public static void pick_subj() {
		int pick=0;
		do {
			if(aSubject.isEmpty()) {
				System.out.println("Belum ada subject, silahkan hubungi admin...");
				break;
			}else {
				System.out.println("_________________________________________________________________________________________________________________________");
				System.out.println("| ID | Subject |");
				System.out.println("|-----------------------------------------------------------------------------------------------------------------------|");
				
		  		if (aSubject.isEmpty()) {
		  		System.out.println("no data");
		  		} else {
		  			int i = 0;
		  			while (i < aSubject.size()) {
		  				System.out.print("| "+(i+1)+" | ");
		  				aSubject.get(i).show();
		  				i++;
		  			}
		  		}
		  		System.out.println("|-----------------------------------------------------------------------------------------------------------------------|");
		  		
				System.out.print("Pilih Subject [1 - "+aSubject.size()+"]: ");
				pick = read.nextInt();
			}
		}while(pick<1 || pick>aSubject.size());
		subj = aSubject.get(pick-1).getJudul_subj();
		System.out.println(subj);
	}
	
	public static void browse_lecture() {
		pick_subj();
		int id=0;
		String more = "y";
		do {
			System.out.println("_________________________________________________________________________________________________________________________");
			System.out.println("|  ID  |\tJudul\t| Tahun\t| Harga Cetak | Harga Digital |");
			System.out.println("|-----------------------------------------------------------------------------------------------------------------------|");
			
	  		if (aModule.isEmpty()) {
	  		System.out.println("no data");
	  		break;
	  		} else {
	  			int i = 0;
	  			while (i < aModule.size()) {
	  				if(aModule.get(i).getSubject().equals(subj)) {
	  					aModule.get(i).show();
	  				}
	  				i++;
	  			}
	  		}
	  		System.out.println("|-----------------------------------------------------------------------------------------------------------------------|");
	  		
	  		boolean found2 = false;
	  		String judul;
	  		int harga=0;
	  		String versi=null;
			do {
				System.out.print("Nomor ID yang ingin dibeli: ");
				id = read.nextInt();
				
				//cek
				li = aModule.listIterator();
				while(li.hasNext()) {
					Module e = (Module)li.next();
					if(e.getId() == id && e.getSubject().equals(subj)) {
						judul = e.getJudul();
						int pos=0;
						do {
							System.out.println("Versi Cetak atau Versi Digital?: ");
							System.out.println("1. Versi Cetak");
							System.out.println("2. Versi Digital");
							int ver = read.nextInt();
							if(ver==1) {
								harga = e.getHarga_printed();
								pos=1; versi = "Cetak";
							}else if(ver==2) {
								harga = e.getHarga_digital();
								pos=1; versi = "Digital";
							}else {
								System.out.println("pilihan tidak ada...");
							}
						}while(pos==0);
						
						aCart.add(new Cart(judul,1,harga,versi));
						found2=true;
					}
				}
				if(!found2) {
					System.out.println("ID tidak ditemukan...");
				}
			}while(found2==false);
			
			System.out.print("Tambah lagi?[y/n]: ");
			more = read.next();
		}while(more.equals("y"));
		
	}
	
	public static void browse_prev() {
		pick_subj();
		int id=0;
		String more = "y";
		do {
			System.out.println("_________________________________________________________________________________________________________________________");
			System.out.println("|  ID  |\tJudul\t| Tahun\t| Harga Cetak | Harga Digital |");
			System.out.println("|-----------------------------------------------------------------------------------------------------------------------|");
			
	  		if (aPrev.isEmpty()) {
		  		System.out.println("no data");
		  		break;
	  		} else {
	  			int i = 0;
	  			while (i < aPrev.size()) {
	  				if(aPrev.get(i).getSubject().equals(subj)) {
	  					aPrev.get(i).show();
	  				}
	  				i++;
	  			}
	  		}
	  		System.out.println("|-----------------------------------------------------------------------------------------------------------------------|");
	  		
	  		boolean found2 = false;
	  		String judul;
	  		int harga=0;
	  		String versi=null;
			do {
				System.out.print("Nomor ID yang ingin dibeli: ");
				id = read.nextInt();
				
				//cek
				li = aPrev.listIterator();
				while(li.hasNext()) {
					Prev_exam e = (Prev_exam)li.next();
					if(e.getId() == id && e.getSubject().equals(subj)) {
						judul = e.getJudul();
						int pos=0;
						do {
							System.out.println("Versi Cetak atau Versi Digital?: ");
							System.out.println("1. Versi Cetak");
							System.out.println("2. Versi Digital");
							int ver = read.nextInt();
							if(ver==1) {
								harga = e.getHarga_printed();
								pos=1; versi = "Cetak";
							}else if(ver==2) {
								harga = e.getHarga_digital();
								pos=1; versi = "Digital";
							}else {
								System.out.println("pilihan tidak ada...");
							}
						}while(pos==0);
						
						aCart.add(new Cart(judul,1,harga,versi));
						found2=true;
					}
				}
				if(!found2) {
					System.out.println("ID tidak ditemukan...");
				}
			}while(found2==false);
			
			System.out.print("Tambah lagi?[y/n]: ");
			more = read.next();
		}while(more.equals("y"));
	}
	
	public static void browse_prac() {
		pick_subj();
		int id=0;
		String more = "y";
		do {
			System.out.println("_________________________________________________________________________________________________________________________");
			System.out.println("|  ID  |\tJudul\t| Tahun\t| Harga Cetak | Harga Digital |");
			System.out.println("|-----------------------------------------------------------------------------------------------------------------------|");
			
	  		if (aPractice.isEmpty()) {
		  		System.out.println("no data");
		  		break;
	  		} else {
	  			int i = 0;
	  			while (i < aPractice.size()) {
	  				if(aPractice.get(i).getSubject().equals(subj)) {
	  					aPractice.get(i).show();
	  				}
	  				i++;
	  			}
	  		}
	  		System.out.println("|-----------------------------------------------------------------------------------------------------------------------|");
	  		
	  		boolean found2 = false;
	  		String judul;
	  		int harga=0;
	  		String versi = null;
			do {
				System.out.print("Nomor ID yang ingin dibeli: ");
				id = read.nextInt();
				
				//cek
				li = aPractice.listIterator();
				while(li.hasNext()) {
					Practice e = (Practice)li.next();
					if(e.getId() == id && e.getSubject().equals(subj)) {
						judul = e.getJudul();
						int pos=0;
						do {
							System.out.println("Versi Cetak atau Versi Digital?: ");
							System.out.println("1. Versi Cetak");
							System.out.println("2. Versi Digital");
							int ver = read.nextInt();
							if(ver==1) {
								harga = e.getHarga_printed();
								pos=1; versi = "Cetak";
							}else if(ver==2) {
								harga = e.getHarga_digital();
								pos=1; versi = "Digital";
							}else {
								System.out.println("pilihan tidak ada...");
							}
						}while(pos==0);
						
						aCart.add(new Cart(judul,1,harga,versi));
						found2=true;
					}
				}
				if(!found2) {
					System.out.println("ID tidak ditemukan...");
				}
			}while(found2==false);
			
			System.out.print("Tambah lagi?[y/n]: ");
			more = read.next();
		}while(more.equals("y"));
	}
	
	public static void browse() {
		int choose = 0;
		do {
			System.out.println("1. Lecture Module");
			System.out.println("2. Previous Exam Paper");
			System.out.println("3. Practice Paper");
			System.out.println("4. Back");
			System.out.print("Pilih: ");
			choose = read.nextInt();
			switch(choose) {
				case 1:{
					browse_lecture();
					break;
				}
				case 2:{
					browse_prev();
					break;
				}
				case 3:{
					browse_prac();
					break;
				}
				case 4:{
					break;
				}
				default:{
					System.out.println("Pilihan tidak ada...");
					break;
				}
			}
		}while(choose!=4);
		
	}
	
	public static void checkout() {
		int totals = 0;
		int count;
		int jumlah, harga;
		if (aCart.isEmpty()) {
	  		System.out.println("no data");
	  	} else {
	  		int i = 0;
	  		print = 0;
	  		digital = 0;
	  		while (i < aCart.size()) {
	  			jumlah = aCart.get(i).getJumlah();
	  			harga = aCart.get(i).getHarga();
	  			if(aCart.get(i).getVersi().equals("Cetak")) {
	  				print += 1;
	  			}
	  			if(aCart.get(i).getVersi().equals("Digital")) {
	  				digital += 1;
	  			}
	  			count = jumlah*harga;
	  			totals += count;
	  			i++;
	  		}
	  	}
		int pos1=0;
		int pos2=0;
		String email = null;
		String post = null;
		String alamat = null;
		System.out.println("Total Harga = "+totals);
		
		if(print!=0 ) {
			System.out.print("Masukkan Alamat: ");read.nextLine();
			alamat = read.nextLine();
			System.out.print("Masukkan Postal Code: ");
			post = read.nextLine();pos1=1;
		}
		if(digital!=0) {
			System.out.print("Masukkan Email: ");read.nextLine();
			email = read.nextLine();pos2=1;
		}
		aCart.removeAll(aCart);
		
		int metode=0;
		int bayar=0;
		int kembalian = 0;
		do {
			System.out.println("Metode pembayaran");
			System.out.println("1. Bayar di toko");
			System.out.println("2. Credit");
			System.out.print("Pilih: ");
			metode = read.nextInt();
			if(metode==1) {
				break;
			}else if(metode==2) {
				
				do {
					System.out.print("Input jumlah pembayaran: ");
					bayar = read.nextInt();
					if(bayar<totals) {
						System.out.println("Jumlah yang dibayarkan kurang...");
					}else {
						kembalian = bayar-totals;
					}
				}while(bayar==0||bayar<totals);
				System.out.println("Jumlah Kembalian: "+kembalian);
			}else {
				System.out.println("Pilihan tidak ada");
				metode=0;
			}
		}while(metode==0);
		
		aTotal.add(new Total(totals,bayar,kembalian));
		aTotal.get(0).print("Pesanan Diterima");
		
		if(pos1!=0) {
			System.out.println("Alamat : "+alamat);
			System.out.println("Kode Pos : "+post);
		}
		if(pos2!=0) {
			System.out.println("Email : "+email);
		}
		System.out.println("Terima kasih sudah memesan di e-smartplace");
	}
	
	public static void cart() {
		int pick=0;
		int no;
		int jumlah;
		do {
			System.out.println("_________________________________________________________________________________________________________________________");
			System.out.println("| No | Item\t| Jumlah\t| Harga\t| Versi\t|");
			System.out.println("|-----------------------------------------------------------------------------------------------------------------------|");
			
			if (aCart.isEmpty()) {
		  		System.out.println("no data");
		  	} else {
		  		int i = 0;
		  		while (i < aCart.size()) {
		  			System.out.print("| "+(i+1)+" |\t");
		  			aCart.get(i).show();
		  			i++;
		  		}
		  	}
		  	System.out.println("|-----------------------------------------------------------------------------------------------------------------------|");
		  	
		  	System.out.println("1. Ganti jumlah Item");
		  	System.out.println("2. Hapus Item");
		  	System.out.println("3. Checkout");
		  	System.out.println("4. Back");
		  	System.out.print("Input Pilihan: ");
		  	pick = read.nextInt();
		  	if(pick==1) {
		  		do {
		  			System.out.print("Input Nomor[1 - "+aCart.size()+"]: ");
			  		no = read.nextInt();
		  		}while(no==0 || no>aCart.size());
		  		
		  		do {
		  			System.out.print("Input Jumlah barang: ");
		  			jumlah = read.nextInt();
		  		}while(jumlah<=0);
		  		
		  		aCart.get(no-1).setJumlah(jumlah);
		  		System.out.println("Barang berhasil dirubah");
		  		
		  	}else if(pick==2) {
		  		do {
		  			System.out.print("Input Nomor[1 - "+aCart.size()+"]: ");
			  		no = read.nextInt();
		  		}while(no==0 || no>aCart.size());
		  		
		  		aCart.remove(no-1);
		  		System.out.println("Barang berhasil dihapus");
		  		
		  	}else if(pick==3) {
		  		checkout();
		  	}else if(pick==4) {
		  		break;
		  	}else {
		  		System.out.println("Pilihan tidak ada...");
		  	}
		  	
		}while(pick!=4);
		
	}
	
	public static void menu_student() {
		int pilih=0;
		do {
			System.out.println("=== MENU STUDENTS ===");
			System.out.println("1. Browse");
			System.out.println("2. Shopping Cart");
			System.out.println("3. Logout");
			System.out.print("Pilih: ");
			pilih = read.nextInt();
			switch(pilih) {
				case 1:{
					browse();
					break;
				}
				case 2:{
					cart();
					break;
				}
				case 3:{
					break;
				}
				default:{
					System.out.println("Pilihan tidak ada...");
				}
			}
		}while(pilih!=3);
		
	}
	
	//========================================================================================================================================
	
	public static void lecture() {
		String more="y";
		do {
			System.out.println("=============== Tabel Module ==============");
			System.out.println("_________________________________________________________________________________________________________________________");
			System.out.println("|  ID  |\tJudul\t| Tahun\t| Harga Cetak | Harga Digital |");
			System.out.println("|-----------------------------------------------------------------------------------------------------------------------|");
			
      		if (aModule.isEmpty()) {
      		System.out.println("no data");
      		} else {
      			int i = 0;
      			while (i < aModule.size()) {
      				aModule.get(i).show();
      				i++;
      			}
      		}
      		System.out.println("|-----------------------------------------------------------------------------------------------------------------------|");
      		
			int id;
			String judul=null;
			int harga_cetak;
			int harga_digital;
			int tahun=0;
			
			
			boolean found2 = false;
			
			do {
				int pos = 0;
				System.out.print("Nomor ID: ");
				id = read.nextInt();
				
				//cek
				li = aModule.listIterator();
				while(li.hasNext()) {
					Module e = (Module)li.next();
					if(e.getId() == id) {
						System.out.println("ID duplikat, mohon input kembali...");
						pos=1;
					}
				}
				if(pos==0) {
					found2=true;
					break;
				}
			}while(found2==false);
			
			
			System.out.print("Judul : ");read.nextLine();
			judul = read.nextLine();
			do {
				System.out.print("Tahun : ");
				tahun = read.nextInt();
				if(tahun<2017||tahun>2022) System.out.println("Hanya dokumen 5 tahun terakhir yang dapat ditambah!");
			}while(tahun<2017||tahun>2022);
			
			System.out.print("Harga versi cetak: ");
			harga_cetak = read.nextInt();
			
			do {
				System.out.print("Harga versi digital: ");
				harga_digital = read.nextInt();
				if(harga_digital>harga_cetak) {
					System.out.println("Harga versi digital harus lebih murah dari harga versi cetak!");
				}
			}while(harga_digital>harga_cetak);
			
			System.out.print("Nama subject: ");read.nextLine();
			String subj = read.nextLine();
			
			li = aSubject.listIterator();
			int pos = 0;
			while(li.hasNext()) {
				Subject e = (Subject)li.next();
				if(e.getJudul_subj().equals(subj)) {
					pos=1;
				}
			}
			if(pos==0) {
				aSubject.add(new Subject(subj));
			}
			aModule.add(new Module(id,judul,tahun,harga_cetak,harga_digital,subj));
			
			System.out.print("Tambah lagi?[y/n]: ");
			more = read.next();
		}while(more.equals("y"));
	}
	
	public static void prev_exam() {
		String more="y";
		do {
			System.out.println("=============== Tabel Previous Exam ==============");
			System.out.println("_________________________________________________________________________________________________________________________");
			System.out.println("|  ID  |\tJudul\t| Tahun\t| Harga Cetak | Harga Digital |");
			System.out.println("|-----------------------------------------------------------------------------------------------------------------------|");
			
      		if (aPrev.isEmpty()) {
      		System.out.println("no data");
      		} else {
      			int i = 0;
      			while (i < aPrev.size()) {
      				aPrev.get(i).show();
      				i++;
      			}
      		}
      		System.out.println("|-----------------------------------------------------------------------------------------------------------------------|");
      		
			int id;
			String judul=null;
			int harga_cetak;
			int harga_digital;
			int tahun=0;
			
			
			boolean found2 = false;
			
			do {
				int pos = 0;
				System.out.print("Nomor ID: ");
				id = read.nextInt();
				
				//cek
				li = aPrev.listIterator();
				while(li.hasNext()) {
					Prev_exam e = (Prev_exam)li.next();
					if(e.getId() == id) {
						System.out.println("ID duplikat, mohon input kembali...");
						pos=1;
					}
				}
				if(pos==0) {
					found2=true;
					break;
				}
			}while(found2==false);
			
			
			System.out.print("Judul : ");read.nextLine();
			judul = read.nextLine();
			do {
				System.out.print("Tahun : ");
				tahun = read.nextInt();
				if(tahun<2017||tahun>2022) System.out.println("Hanya dokumen 5 tahun terakhir yang dapat ditambah!");
			}while(tahun<2017||tahun>2022);
			
			System.out.print("Harga versi cetak: ");
			harga_cetak = read.nextInt();
			
			do {
				System.out.print("Harga versi digital: ");
				harga_digital = read.nextInt();
				if(harga_digital>harga_cetak) {
					System.out.println("Harga versi digital harus lebih murah dari harga versi cetak!");
				}
			}while(harga_digital>harga_cetak);
			
			System.out.print("Nama subject: ");read.nextLine();
			String subj = read.nextLine();
			
			li = aSubject.listIterator();
			int pos = 0;
			while(li.hasNext()) {
				Subject e = (Subject)li.next();
				if(e.getJudul_subj().equals(subj)) {
					pos=1;
				}
			}
			if(pos==0) {
				aSubject.add(new Subject(subj));
			}
			aPrev.add(new Prev_exam(id,judul,tahun,harga_cetak,harga_digital,subj));
			System.out.print("Tambah lagi?[y/n]: ");
			more = read.next();
		}while(more.equals("y"));
	}
	
	public static void practice() {
		String more="y";
		do {
			System.out.println("=============== Tabel Practice Paper ==============");
			System.out.println("_________________________________________________________________________________________________________________________");
			System.out.println("|  ID  |\tJudul\t| Tahun\t| Harga Cetak | Harga Digital |");
			System.out.println("|-----------------------------------------------------------------------------------------------------------------------|");
			
      		if (aPractice.isEmpty()) {
      		System.out.println("no data");
      		} else {
      			int i = 0;
      			while (i < aPractice.size()) {
      				aPractice.get(i).show();
      				i++;
      			}
      		}
      		System.out.println("|-----------------------------------------------------------------------------------------------------------------------|");
      		
			int id;
			String judul=null;
			int harga_cetak;
			int harga_digital;
			int tahun=0;
			
			
			boolean found2 = false;
			
			do {
				int pos = 0;
				System.out.print("Nomor ID: ");
				id = read.nextInt();
				
				//cek
				li = aPractice.listIterator();
				while(li.hasNext()) {
					Practice e = (Practice)li.next();
					if(e.getId() == id) {
						System.out.println("ID duplikat, mohon input kembali...");
						pos=1;
					}
				}
				if(pos==0) {
					found2=true;
					break;
				}
			}while(found2==false);
			
			
			System.out.print("Judul : ");read.nextLine();
			judul = read.nextLine();
			do {
				System.out.print("Tahun : ");
				tahun = read.nextInt();
				if(tahun<2017||tahun>2022) System.out.println("Hanya dokumen 5 tahun terakhir yang dapat ditambah!");
			}while(tahun<2017||tahun>2022);
			
			System.out.print("Harga versi cetak: ");
			harga_cetak = read.nextInt();
			
			do {
				System.out.print("Harga versi digital: ");
				harga_digital = read.nextInt();
				if(harga_digital>harga_cetak) {
					System.out.println("Harga versi digital harus lebih murah dari harga versi cetak!");
				}
			}while(harga_digital>harga_cetak);
			
			System.out.print("Nama subject: ");read.nextLine();
			String subj = read.nextLine();
			
			li = aSubject.listIterator();
			int pos = 0;
			while(li.hasNext()) {
				Subject e = (Subject)li.next();
				if(e.getJudul_subj().equals(subj)) {
					pos=1;
				}
			}
			if(pos==0) {
				aSubject.add(new Subject(subj));
			}
			aPractice.add(new Practice(id,judul,tahun,harga_cetak,harga_digital,subj));
			System.out.print("Tambah lagi?[y/n]: ");
			more = read.next();
		}while(more.equals("y"));
	}
	
	public static void add_product() throws Exception {
		int flag=1;
		while(flag==1) {
			System.out.println("1. Lecture Module");
			System.out.println("2. Previous Exam Paper");
			System.out.println("3. Practice Paper");
			System.out.println("4. Back");
			
			System.out.print("Pilih menu: ");
			
			int choose = read.nextInt();
			switch(choose) {
				case 1:{
					lecture();
					writeModule(aModule);
					writeSubject(aSubject);
					break;
				}
				case 2:{
					prev_exam();
					writePrev(aPrev);
					writeSubject(aSubject);
					break;
				}
				case 3:{
					practice();
					writePrac(aPractice);
					writeSubject(aSubject);
					break;
				}
				case 4:{
					flag=0;
					break;
				}
				default:{
					System.out.println("Pilihan tidak ada...");
				}
			}
		}
		
	}
	
	//=======================================================================================================================================
	
	public static void manage_lecture() {
		int more = 1;
		do {
			System.out.println("_________________________________________________________________________________________________________________________");
			System.out.println("|  ID  | judul\t| tahun\t| harga cetak | harga digital |");
			System.out.println("|-----------------------------------------------------------------------------------------------------------------------|");
			
	  		if (aModule.isEmpty()) {
	  		System.out.println("no data");
	  		} else {
	  			int i = 0;
	  			while (i < aModule.size()) {
	  				aModule.get(i).show();
	  				i++;
	  			}
	  		}
	  		System.out.println("|-----------------------------------------------------------------------------------------------------------------------|");
	  		
	  		int pilih;
	  		do {
	  			System.out.println("1. Update");
		  		System.out.println("2. Delete");
		  		System.out.println("3. Back");
		  		System.out.print("Pilih: ");
		  		pilih = read.nextInt();
		  		if(pilih==1) {
		  			//pilih id
		  			boolean found2 = false;
					int id;
					int Nid;
					String Njudul=null;
					int Nharga_cetak;
					int Nharga_digital;
					int Ntahun=0;
					String subj;
					do {
						System.out.print("Input ID: ");
						id = read.nextInt();
							
						
						//cek
						li = aModule.listIterator();
						while(li.hasNext()) {
							Module e = (Module)li.next();
							if(e.getId() == id) {
								Nid = e.getId();
								Njudul = e.getJudul();
								Nharga_cetak = e.getHarga_printed();
								Nharga_digital = e.getHarga_digital();
								Ntahun = e.getTahun();
								subj = e.getSubject();
								System.out.println("Apa yang ingin dirubah?");
								System.out.println("1. Judul");
								System.out.println("2. Tahun");
								System.out.println("3. Harga Cetak");
								System.out.println("4. harga Digital");
								System.out.println("5. Back");
								int pick = read.nextInt();
								
								if(pick==1) {
									System.out.print("Input Judul baru: ");read.nextLine();
									Njudul = read.nextLine();
								}else if(pick==2) {
									do {
										System.out.print("Input Tahun baru:");
										Ntahun = read.nextInt();
									}while(Ntahun<2017||Ntahun>2022);
								}else if(pick==3) {
									do {
										System.out.print("Input Harga Cetak baru");
										Nharga_cetak = read.nextInt();
									}while(Nharga_cetak<Nharga_digital);
								}else if(pick==4) {
									do {
										System.out.print("Input Harga Digital baru: ");
										Nharga_digital = read.nextInt();
									}while(Nharga_digital>Nharga_cetak);
								}else if(pick==5) {
									li.set(new Module(Nid,Njudul,Ntahun,Nharga_cetak,Nharga_digital,subj));
									found2 = true;
									break;
								}else {
									
								}

								li.set(new Module(Nid,Njudul,Ntahun,Nharga_cetak,Nharga_digital,subj));
								found2 = true;
							}
						}
						if(!found2) {
								System.out.println("ID tidak ditemukan...");
						}
					}while(found2==false);
		  			
		  		}else if(pilih==2) {
		  			boolean found2 = false;
					int id;
					do {
						System.out.print("Input ID yang ingin dihapus: ");
						id = read.nextInt();
						
						//cek
						li = aModule.listIterator();
						while(li.hasNext()) {
							Module e = (Module)li.next();
							if(e.getId() == id) {
								li.remove();
								found2=true;
							}
						}
						if(!found2) {
							System.out.println("ID tidak ditemukan...");
						}else {
							System.out.println("Dokumen berhasil dihapus");
						}
					}while(found2==false);
		  		}else if(pilih==3) {
		  			more=0;
		  			break;
		  		}else {
		  			System.out.println("Pilihan tidak ada...");
		  		}
		  		
	  		}while(pilih!=3);
	  		
	  		
		}while(more==1);
		
	}
	
	public static void manage_prev() {
		int more = 1;
		do {
			System.out.println("_________________________________________________________________________________________________________________________");
			System.out.println("|  ID  | judul\t| tahun\t| harga cetak | harga digital |");
			System.out.println("|-----------------------------------------------------------------------------------------------------------------------|");
			
	  		if (aPrev.isEmpty()) {
	  		System.out.println("no data");
	  		} else {
	  			int i = 0;
	  			while (i < aPrev.size()) {
	  				aPrev.get(i).show();
	  				i++;
	  			}
	  		}
	  		System.out.println("|-----------------------------------------------------------------------------------------------------------------------|");
	  		
	  		int pilih;
	  		do {
	  			System.out.println("1. Update");
		  		System.out.println("2. Delete");
		  		System.out.println("3. Back");
		  		System.out.print("Pilih: ");
		  		pilih = read.nextInt();
		  		if(pilih==1) {
		  			//pilih id
		  			boolean found2 = false;
					int id;
					int Nid;
					String Njudul=null;
					int Nharga_cetak;
					int Nharga_digital;
					int Ntahun=0;
					String subj;
					do {
						System.out.print("Input ID: ");
						id = read.nextInt();
							
						
						//cek
						li = aPrev.listIterator();
						while(li.hasNext()) {
							Prev_exam e = (Prev_exam)li.next();
							if(e.getId() == id) {
								Nid = e.getId();
								Njudul = e.getJudul();
								Nharga_cetak = e.getHarga_printed();
								Nharga_digital = e.getHarga_digital();
								Ntahun = e.getTahun();
								subj = e.getSubject();
								System.out.println("Apa yang ingin dirubah?");
								System.out.println("1. Judul");
								System.out.println("2. Tahun");
								System.out.println("3. Harga Cetak");
								System.out.println("4. harga Digital");
								System.out.println("5. Back");
								int pick = read.nextInt();
								
								if(pick==1) {
									System.out.print("Input Judul baru: ");read.nextLine();
									Njudul = read.nextLine();
								}else if(pick==2) {
									do {
										System.out.print("Input Tahun baru:");
										Ntahun = read.nextInt();
									}while(Ntahun<2017||Ntahun>2022);
								}else if(pick==3) {
									do {
										System.out.print("Input Harga Cetak baru");
										Nharga_cetak = read.nextInt();
									}while(Nharga_cetak<Nharga_digital);
								}else if(pick==4) {
									do {
										System.out.print("Input Harga Digital baru: ");
										Nharga_digital = read.nextInt();
									}while(Nharga_digital>Nharga_cetak);
								}else if(pick==5) {
									found2 = true;
									break;
								}else {
									System.out.println("Pilihan tidak ada...");
									break;
								}

								li.set(new Prev_exam(Nid,Njudul,Ntahun,Nharga_cetak,Nharga_digital,subj));
								found2 = true;
							}
						}
						if(!found2) {
								System.out.println("ID tidak ditemukan...");
						}
					}while(found2==false);
		  			
		  		}else if(pilih==2) {
		  			boolean found2 = false;
					int id;
					do {
						System.out.print("Input ID yang ingin dihapus: ");
						id = read.nextInt();
						
						//cek
						li = aPrev.listIterator();
						while(li.hasNext()) {
							Prev_exam e = (Prev_exam)li.next();
							if(e.getId() == id) {
								li.remove();
								found2=true;
							}
						}
						if(!found2) {
							System.out.println("ID tidak ditemukan...");
						}else {
							System.out.println("Dokumen berhasil dihapus");
						}
					}while(found2==false);
		  		}else if(pilih==3) {
		  			more=0;
		  			break;
		  		}else {
		  			System.out.println("Pilihan tidak ada...");
		  		}
		  		
	  		}while(pilih!=3);
	  		
	  		
		}while(more==1);
	}
	
	public static void manage_prac() {
		int more = 1;
		do {
			System.out.println("_________________________________________________________________________________________________________________________");
			System.out.println("|  ID  | judul\t| tahun\t| harga cetak | harga digital |");
			System.out.println("|-----------------------------------------------------------------------------------------------------------------------|");
			
	  		if (aPractice.isEmpty()) {
	  		System.out.println("no data");
	  		} else {
	  			int i = 0;
	  			while (i < aPractice.size()) {
	  				aPractice.get(i).show();
	  				i++;
	  			}
	  		}
	  		System.out.println("|-----------------------------------------------------------------------------------------------------------------------|");
	  		
	  		int pilih;
	  		do {
	  			System.out.println("1. Update");
		  		System.out.println("2. Delete");
		  		System.out.println("3. Back");
		  		System.out.print("Pilih: ");
		  		pilih = read.nextInt();
		  		if(pilih==1) {
		  			//pilih id
		  			boolean found2 = false;
					int id;
					int Nid;
					String Njudul=null;
					int Nharga_cetak;
					int Nharga_digital;
					int Ntahun=0;
					String subj;
					do {
						System.out.print("Input ID: ");
						id = read.nextInt();
							
						
						//cek
						li = aPractice.listIterator();
						while(li.hasNext()) {
							Practice e = (Practice)li.next();
							if(e.getId() == id) {
								Nid = e.getId();
								Njudul = e.getJudul();
								Nharga_cetak = e.getHarga_printed();
								Nharga_digital = e.getHarga_digital();
								Ntahun = e.getTahun();
								subj = e.getSubject();
								System.out.println("Apa yang ingin dirubah?");
								System.out.println("1. Judul");
								System.out.println("2. Tahun");
								System.out.println("3. Harga Cetak");
								System.out.println("4. harga Digital");
								System.out.println("5. Back");
								int pick = read.nextInt();
								
								if(pick==1) {
									System.out.print("Input Judul baru: ");read.nextLine();
									Njudul = read.nextLine();
								}else if(pick==2) {
									do {
										System.out.print("Input Tahun baru:");
										Ntahun = read.nextInt();
									}while(Ntahun<2017||Ntahun>2022);
								}else if(pick==3) {
									do {
										System.out.print("Input Harga Cetak baru");
										Nharga_cetak = read.nextInt();
									}while(Nharga_cetak<Nharga_digital);
								}else if(pick==4) {
									do {
										System.out.print("Input Harga Digital baru: ");
										Nharga_digital = read.nextInt();
									}while(Nharga_digital>Nharga_cetak);
								}else if(pick==5) {
									found2 = true;
									break;
								}else {
									System.out.println("Pilihan tidak ada...");
									break;
								}

								li.set(new Practice(Nid,Njudul,Ntahun,Nharga_cetak,Nharga_digital,subj));
								found2 = true;
							}
						}
						if(!found2) {
								System.out.println("ID tidak ditemukan...");
						}
					}while(found2==false);
		  			
		  		}else if(pilih==2) {
		  			boolean found2 = false;
					int id;
					do {
						System.out.print("Input ID yang ingin dihapus: ");
						id = read.nextInt();
						
						//cek
						li = aPractice.listIterator();
						while(li.hasNext()) {
							Practice e = (Practice)li.next();
							if(e.getId() == id) {
								li.remove();
								found2=true;
							}
						}
						if(!found2) {
							System.out.println("ID tidak ditemukan...");
						}else {
							System.out.println("Dokumen berhasil dihapus");
						}
					}while(found2==false);
		  		}else if(pilih==3) {
		  			more=0;
		  			break;
		  		}else {
		  			System.out.println("Pilihan tidak ada...");
		  		}
		  		
	  		}while(pilih!=3);
	  		
	  		
		}while(more==1);
	}
	
	public static void manage_product() throws Exception {
		int flag=1;
		while(flag==1) {
			System.out.println("1. Lecture Module");
			System.out.println("2. Previous Exam Paper");
			System.out.println("3. Practice Paper");
			System.out.println("4. Back");
			
			System.out.print("Pilih menu: ");
			
			int choose = read.nextInt();
			switch(choose) {
				case 1:{
					manage_lecture();
					writeModule(aModule);
					writeSubject(aSubject);
					break;
				}
				case 2:{
					manage_prev();
					writePrev(aPrev);
					writeSubject(aSubject);
					break;
				}
				case 3:{
					manage_prac();
					writePrac(aPractice);
					writeSubject(aSubject);
					break;
				}
				case 4:{
					flag=0;
					break;
				}
				default:{
					System.out.println("Pilihan tidak ada...");
				}
			}
		}
	}
	
	public static void menu_admin() throws Exception {
		int pilih = 0;
		do {
			System.out.println("=== MENU ADMIN ===");
			System.out.println("1. Add Product");
			System.out.println("2. Manage Product");
			System.out.println("3. Logout");
			System.out.print("Pilih menu: ");
			pilih = read.nextInt();
			switch(pilih) {
				case 1:{
					add_product();
					break;
				}
				case 2:{
					manage_product();
					break;
				}
				case 3:{
					break;
				}
				default:{
					System.out.println("Pilihan tidak ada...");
				}
			}
		}while(pilih!=3);
		
	}
	
	//========================================================================================================================================

	public static void writeSubject(ArrayList<Subject>list) throws Exception {
		
		objOut = new ObjectOutputStream(new FileOutputStream(fileSubject));
		objOut.writeObject(list);
		objOut.close();
                  
    }
	
	public static void writeModule(ArrayList<Module>list) throws Exception {
		objOut = new ObjectOutputStream(new FileOutputStream(fileModule));
		objOut.writeObject(list);
		objOut.close();
                  
    }
	
	public static void writePrev(ArrayList<Prev_exam>list) throws Exception {
		objOut = new ObjectOutputStream(new FileOutputStream(filePrev));
		objOut.writeObject(list);
		objOut.close();
                  
    }
	
	public static void writePrac(ArrayList<Practice>list) throws Exception {
		objOut = new ObjectOutputStream(new FileOutputStream(filePrac));
		objOut.writeObject(list);
		objOut.close();
                  
    }
	
//	public static void writeTotal(ArrayList<Total>list) throws Exception {
//		objOut = new ObjectOutputStream(new FileOutputStream(fileTotal));
//		objOut.writeObject(list);
//		objOut.close();
//                  
//    }
	
	//===================================================================================================================================
	
	public static void readSubject() throws Exception {
		if(fileSubject.isFile()) {
			readStream = new ObjectInputStream(new FileInputStream(fileSubject));
	        aSubject = (ArrayList<Subject>) readStream.readObject();
	        readStream.close();
		}
	}
	
	public static void readModule() throws Exception {
        if(fileModule.isFile()) {
        	readStream = new ObjectInputStream(new FileInputStream(fileModule));
            aModule = (ArrayList<Module>) readStream.readObject();
            readStream.close();
        }
	}
	
	public static void readPrev() throws Exception {
        if(filePrev.isFile()) {
        	readStream = new ObjectInputStream(new FileInputStream(filePrev));
            aPrev = (ArrayList<Prev_exam>) readStream.readObject();
            readStream.close();
        }
	}
	
	public static void readPrac() throws Exception {
        if(filePrac.isFile()) {
        	readStream = new ObjectInputStream(new FileInputStream(filePrac));
            aPractice = (ArrayList<Practice>) readStream.readObject();
            readStream.close();
        }
	}
	
//	public static void readTotal() throws Exception {
//        if(fileTotal.isFile()) {
//        	readStream = new ObjectInputStream(new FileInputStream(fileTotal));
//            aTotal = (ArrayList<Total>) readStream.readObject();
//            readStream.close();
//        }
//	}
	
	//==================================================================================================================================
	
	public static void main(String[] args) throws Exception {
		
		readSubject();
		readModule();
		readPrev();
		readPrac();
//		readTotal();
		int flag=1;
		while(flag==1) {
			
			judul();
			System.out.print("Pilih: ");
			int choose;
			choose = read.nextInt();
			
			if(choose==1) {
				int log=0;
				log = login();
				if(log==1) {
					menu_student();
				}else if(log==2) {
					menu_admin();
				}
			}else if(choose==2) {
				System.out.println("Terima kasih sudah menggunakan e-smartplace");
				flag=0;
			}else {
				System.out.println("Pilihan tidak ada");
			}
		}
		
	}

}
