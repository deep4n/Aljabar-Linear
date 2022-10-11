package aljin2;

import java.util.Scanner;
import java.lang.Math; 

public class ADTMat{
    /* Tipe data matriks */
    public class MATRIKS{
	public double [][] Mem = new double[100][100]; 
	public int NBrsEff;
	public int NKolEff; 
	public String [] desc = new String[100];
	public int NDesc;
	/* Indeks matriks yang digunakan: [0..NBrsEff-1][0..NBrsEff-1] */
	/* Memori matriks yang dipakai selalu di "ujung kiri atas" */
    }
    static final Scanner sc = new Scanner(System.in);

    /* ********** DEFINISI PROTOTIPE PRIMITIF ********** */
          /* *** Konstruktor membentuk MATRIKS *** */
    public void MakeMATRIKS (int NB, int NK, MATRIKS M) { 
	/* Membentuk sebuah MATRIKS "kosong" yang siap diisi berukuran NB x NK di "ujung kiri" memori */
	/* I.S. NB dan NK adalah valid untuk memori matriks yang dibuat */
	/* F.S. Matriks M sesuai dengan definisi di atas terbentuk */
        M.NBrsEff=NB;
	M.NKolEff=NK;
    }	
	
    /* ********** KELOMPOK BACA/TULIS ********** */
    public void BacaMATRIKS(MATRIKS M){
        /* I.S. IsIdxValid(NB,NK) */ 
	/* F.S. M terdefinisi nilai elemen efektifnya, berukuran NB x NK */
	/* Proses: Melakukan MakeMATRIKS(M,NB,NK) dan mengisi nilai efektifnya */
	/* Selanjutnya membaca nilai elemen per baris dan kolom */
	int i,j,n;
	
        Scanner input = new Scanner(System.in);
	System.out.print("Masukkan ordo matriks nxn : ");
	n = input.nextInt();
	System.out.println("Tulis Elemen Matriks ");
	
        for(i = 0; i < n; i++){
            for (j = 0; j < n; j++){
		M.Mem[i][j]= input.nextDouble();
            }
        }
	
        MakeMATRIKS(n,n,M);
	System.out.println("Matriks yang anda masukkan adalah :");
	TulisMATRIKS(M);
	System.out.println();
    }

    public void BacaMATRIKSAugmented(MATRIKS M){
	/* I.S. M kosong */ 
	/* F.S. M terdefinisi nilai elemen efektifnya, berukuran NB x NK */
	/* Proses: Melakukan MakeMATRIKS(NB,NK,M) dan mengisi nilai efektifnya */
	/* Selanjutnya membaca nilai elemen per baris dan kolom */
	int i,j,m,n;
	
        Scanner input = new Scanner(System.in);
	System.out.print("Masukkan ordo matriks mxn : ");
	m = input.nextInt();
	n = input.nextInt();
	System.out.println("Tulis Elemen Matriks ");
	
        for(i = 0; i < m; i++){
            for (j = 0; j < n; j++){
		M.Mem[i][j]= input.nextDouble();
            }
	}
	
        MakeMATRIKS(m,n,M);
	System.out.println("Matriks yang anda masukkan adalah :");
	TulisMATRIKS(M);
	System.out.println();
    }

    public void BacaMatriksHilbert (MATRIKS MAug){
	/* I.S. Maug */ 
	/* F.S. Maug terdefinisi nilai elemen efektifnya, berukuran NB x NK+1 */
	/* Proses: Melakukan MakeMATRIKS(NB,NB+1,M) dan mengisi nilai efektifnya */
	/* Selanjutnya membaca nilai elemen per baris dan kolom dan mengisi nilai matriks kolom terakhir sesuai kaidah matriks hilbert */
        int i, j, n;
        double d;
		
	System.out.print("Masukkan ordo n*n untuk matriks koefisien dari matriks hilbert: ");
	Scanner keyboard = new Scanner(System.in);
	n = keyboard.nextInt();
	
        for (i = 0; i < n; i ++) {
            for (j = 0; j <= n ; j ++) {
                d = i + 1 + j; 
		if (j != n ){
                    MAug.Mem[i][j] = (1/(d));
		} 
		else{
                    if (i == 0){
			MAug.Mem[i][j] = 1;
                    }
                    else{
			MAug.Mem[i][j] = 0;
                    }
                }
            }
	}
        
	MakeMATRIKS(n, n+1, MAug);
	System.out.println("Matriks yang anda masukkan adalah :");
	TulisMATRIKS(MAug);
	System.out.println();
    }
	
    public void TulisMATRIKS (MATRIKS M){
	/* I.S. M terdefinisi */
	/* F.S. Nilai M(i,j) ditulis ke layar per baris per kolom, masing-masing elemen per baris 
		dipisahkan sebuah spasi */
	/* Proses: Menulis nilai setiap elemen M ke layar dengan traversal per baris dan per kolom */
	int i,j ;
	
        for(i = 0; i <= M.NBrsEff-1; i++){
            for (j = 0; j <= M.NKolEff-1; j++){
		if (M.Mem[i][j]==-0.0){
                    M.Mem[i][j]= -0.0 + 0.0;
		}
		if (j == (M.NKolEff-1) && i == (M.NBrsEff-1)) {
                    System.out.printf("%.2f", M.Mem[i][j]);
		} 
                else if (j == (M.NKolEff-1)){
                    System.out.printf("%.2f", M.Mem[i][j]);
                    System.out.println();
		}
                else {
                    System.out.printf("%.2f", M.Mem[i][j]);
                    System.out.print(" ");
		}
            }
	}
    }
        
    public void eliminasiBaris(MATRIKS M){
	// menngeliminasi baris yang isallzero
	int i,j, eliminasi;
	
        MATRIKS M1 = new MATRIKS();
	eliminasi = 0;
	
        for (i = 0; i < M.NBrsEff; i++){
            if (isAllZero(M,i)){
		eliminasi +=1;
            }
	}	
        i =0;
	// BUAT matriks baru 
	MakeMATRIKS(M.NBrsEff - eliminasi, M.NKolEff, M1);
	
        for (i=0; i < M1.NBrsEff; i++){
            for (j=0; j< M1.NKolEff; j++){
		M1.Mem[i][j] = M.Mem[i][j];
            }
	}
	// Copy matriks 
	MakeMATRIKS(M1.NBrsEff, M1.NKolEff, M);
	for (i=0; i < M1.NBrsEff; i++){
            for (j=0; j< M1.NKolEff; j++){
		M.Mem[i][j] = M1.Mem[i][j];
            }
	}
    }
       
    public boolean isAllZero(MATRIKS M, int i){
        // apakah pada baris tersebut semuanya bernilai 0
	int j;
	boolean isZero = true;
	
        for (j =0; j <M.NKolEff; j++){
            if (M.Mem[i][j] != 0){
                isZero = false;
            }
        }	
        return isZero;
    }

    /* ********** KELOMPOK OPERASI TERHADAP MATRIKS ********** */
    public boolean IsBujurSangkar(MATRIKS M){
	/* Mengirimkan true jika M adalah matriks dg ukuran baris dan kolom sama */
	return (M.NBrsEff == M.NKolEff);
    }

    public MATRIKS KaliMATRIKS (MATRIKS M1, MATRIKS M2){
	/* Prekondisi : Ukuran kolom efektif M1 = ukuran baris efektif M2 */
	/* Mengirim hasil perkalian matriks: salinan M1 * M2 */
	int i,j,k;
	double el;
	
        MATRIKS M3 = new MATRIKS();
	MakeMATRIKS(M1.NBrsEff,M2.NKolEff,M3);
	for(i = 0; i <= M3.NBrsEff-1; i++){
            for (j = 0; j <= M3.NKolEff-1; j++){
		el = 0;
		for (k = 0 ; k <= M1.NKolEff; k++){
                    el = el + M1.Mem[i][k]*M2.Mem[k][j];
		}
		M3.Mem[i][j] = el;
            }
	}
	return M3;
    }

    public void Transpose(MATRIKS M){
	/* I.S. M terdefinisi dan IsBujursangkar(M) */
	/* F.S. M "di-transpose", yaitu setiap elemen M(i,j) ditukar nilainya dengan elemen M(j,i) */
	MATRIKS M3 = new MATRIKS();
        int i,j;
		
        MakeMATRIKS(M.NBrsEff,M.NKolEff,M3);
	for (i = 0; i < M.NBrsEff; i++){
            for (j = 0; j < M.NKolEff; j++){
		M3.Mem[i][j] = M.Mem[j][i] ;
            }
	}
	M.Mem = M3.Mem;
    }

    public MATRIKS Inverse (MATRIKS M){
	/* I.S. M terdefinisi dan pasti punya invers */
	/* F.S. return matriks hasil inverse */
	MATRIKS Invers = new MATRIKS();
	Invers = MATRIKSKofaktor(M);
	Transpose(Invers);
	double perdet ;
	perdet = 1/EkspansiKofaktor(M);
	PKaliKons(Invers, perdet);
	
        return (Invers);	
    }

    public void PKaliKons(MATRIKS M, double K){
	/* I.S. M terdefinisi, K terdefinisi */
	/* F.S. Mengalikan setiap elemen M dengan K */
	int i,j;
	for (i = 0; i < M.NBrsEff; i++){
            for (j = 0; j < M.NKolEff; j++){
		M.Mem[i][j] = M.Mem[i][j] * K;
            }
	}
    }

    public boolean IsPunyaInvers (MATRIKS M) {
	/* I.S. M terdefinisi */
	/* F.S. Mereturn true apabila matriks m mempunyai invers */
	return (EkspansiKofaktor(M) != 0);
    }

    public void CopyMATRIKS(MATRIKS MIn, MATRIKS MHsl){
        /* I.S. MIn, dan MHsl terdefinisi */
	/* F.S. Melakukan assignment MHsl  MIn */
	int i,j;
	MakeMATRIKS(MIn.NBrsEff, MIn.NKolEff, MHsl);
	for (i=0; i< MIn.NBrsEff; i++){
            for (j=0; j< MIn.NKolEff; j++){
		MHsl.Mem[i][j] = MIn.Mem[i][j];
            }
	}
    }

    public void TukarBaris(MATRIKS M, int i1, int i2) {
	int j;
	double temp; 
	for(j=0;j < M.NKolEff;j++){
            temp = M.Mem[i1][j];
            M.Mem[i1][j] = M.Mem[i2][j];
            M.Mem[i2][j] = temp;
        } 
    }

    public boolean IsTidakAdaSolusi(MATRIKS M){
	MATRIKS M2 = new MATRIKS();
	CopyMATRIKS(M, M2);
	GaussJordan(M2);
	eliminasiBaris(M2);
	int i,j;
	boolean solusi, hasil;
	hasil = false;
	for (i=0; i< M2.NBrsEff; i++){
            solusi = true;
            for (j=0; j< M2.NKolEff-1; j++){
                if (M2.Mem[i][j] != 0){
                    solusi = false;
		}
            }
            if (solusi){
		if (M2.Mem[i][j] != 0){
                    hasil = true;
		}
            }
	}
	return hasil;
    }

    public void BagiBaris(MATRIKS M, int i){
	int j, j1, j2;
	double penyebut;
	j = 0;
	
        for (j2=0;j2<M.NKolEff;j2++){
            if (M.Mem[i][j2] >= -0.00000000000001 && M.Mem[i][j2] < 0 ){
		M.Mem[i][j2] = 0;
            }
	}
	while (M.Mem[i][j] == 0 && j < M.NKolEff){
            j +=1; 
	}
	if (j < M.NKolEff){
            penyebut = M.Mem[i][j];
            for (j1 = j; j1 < M.NKolEff;j1++){
                M.Mem[i][j1] = M.Mem[i][j1]/penyebut;
            }
	}
    }

    public int IndeksKolom(MATRIKS M, int i){
	//  mencari indeks kolom yang berawal nilai 0 
	int j, idxKol;
	boolean isKetemu;
        isKetemu = false;
	j = 0;
	idxKol = 0;
        
        while ( j < M.NKolEff && !isKetemu){
            if (M.Mem[i][j] != 0){
		isKetemu = true;
		idxKol = j;
            }
            else{
		j +=1;
            }
        } 
	if (j >= M.NKolEff){
            idxKol = M.NKolEff;
	}	
        return idxKol;	
    }

    public void SortBaris(MATRIKS M){
        int i, j; 
	
        for (i= 0; i < M.NBrsEff-1; i++){
            for (j = i+1; j < M.NBrsEff; j++){
                if (IndeksKolom(M,i) > IndeksKolom(M,j)){
                    TukarBaris(M,i,j);
		}
            }
	}
    }

    // DETERMINAN
    public double EkspansiKofaktor(MATRIKS M){
	/* Prekondisi: IsBujurSangkar(M) */
	/* Menghitung nilai determinan M menggunakan ekspansi kofaktor pada baris ke-1 */
	if (M.NBrsEff == 1){
            return M.Mem[0][0];
	}
	else{
            MATRIKS Minor = new MATRIKS();
            MakeMATRIKS((M.NBrsEff - 1), (M.NKolEff - 1), Minor);
            int i, j, k, ci, cj;
            double det;
            int koef = 1;
            det = 0;
            
            if (M.NBrsEff == 2){
		det = M.Mem[0][0] * M.Mem[1][1] - M.Mem[0][1] * M.Mem[1][0];
		return det;
            }
            else{
		for (i = 0; i < M.NBrsEff; i++){
                    ci = 0; 
                    cj = 0;
                    for (j = 0; j < M.NBrsEff; j++){
                        for (k = 0; k < M.NBrsEff; k++){
                            if (j != 0 && k != i){
				Minor.Mem[ci][cj] = M.Mem[j][k];
				cj = cj + 1;
                                if (cj > M.NBrsEff - 2){
                                    ci = ci + 1;
                                    cj = 0;
				}
                            }
			}
                    }
                    det = det + koef * ((M.Mem[0][i]) * EkspansiKofaktor(Minor));
                    koef = -1 * koef;
                }
            }
            return det;
	}
    }

    public void DeterminanKofaktor (MATRIKS M ){
	double det = EkspansiKofaktor(M);
	MATRIKSKofaktor2(M);
	System.out.println("Matriks kofaktornya adalah: ");
	TulisMATRIKS(M);
	System.out.println("");
	System.out.print("Determinannya adalah: ");
	System.out.println(det);
	M.NDesc = 1;
	M.desc[0] = "Matriks Kofaktor, Determinannya adalah " + String.valueOf(det);
    }

    public double KofakElmt(MATRIKS M, int i, int j){
	/* I.S. M terdefinisi, i, j dalam range matriks */
	/* F.S. Mengeluarkan elemen kofaktor(i,j) dari sebuah matriks */
	MATRIKS Minor = new MATRIKS();
	MakeMATRIKS((M.NBrsEff - 1), (M.NKolEff - 1), Minor);
	int ii, jj, mi, mj;
	double koef = Math.pow(-1, (i+j+2)) ;
	mi = 0; 
	mj = 0;
	
        for (ii = 0; ii < M.NBrsEff; ii++){
            for (jj = 0; jj < M.NKolEff; jj++){
		if (ii != i && jj != j){
                    Minor.Mem[mi][mj] = M.Mem[ii][jj];
                    mj = mj + 1;
                    if (mj > M.NBrsEff - 2) {
                        mi = mi + 1;
			mj = 0;
                    }
		}
            }
	}
	return EkspansiKofaktor(Minor)*koef ;			
    }

    public MATRIKS MATRIKSKofaktor (MATRIKS M){
        /* I.S. M terdefinisi */
	/* F.S. Mengeluarkan Matriks kofaktor dari sebuah matriks */
	int i,j;
	MATRIKS Kofaktor = new MATRIKS();
	MakeMATRIKS(M.NBrsEff,M.NKolEff,Kofaktor);
	
        for (i = 0; i < M.NBrsEff; i++){
            for (j = 0; j < M.NKolEff; j++){
		Kofaktor.Mem[i][j] = KofakElmt(M, i, j);
            }
	}
	return Kofaktor;
    }

    public void MATRIKSKofaktor2 (MATRIKS M){
	/* I.S. M terdefinisi */
	/* F.S. Mengeluarkan Matriks kofaktor dari sebuah matriks */
	int i,j;
	MATRIKS Kofaktor = new MATRIKS();
	MakeMATRIKS(M.NBrsEff,M.NKolEff,Kofaktor);
	
        for (i = 0; i < M.NBrsEff; i++){
            for (j = 0; j < M.NKolEff; j++){
		Kofaktor.Mem[i][j] = KofakElmt(M, i, j);
            }
	}
	M.Mem = Kofaktor.Mem;
    }

    public void MenuDeterminan(){
        MATRIKS M = new MATRIKS();
	int op,op2,op3;
	System.out.println("Anda telah memilih menu Determinan");
	op = 1;
	System.out.println("");
            while (op != 1){
		System.out.println("Pilihan salah !! Silahkan pilih metode pembacaan matriks [1= file, 2= keyboard]");
		
		op = 1;
		System.out.println("");
            }
            if (op == 1) {
		BacaMATRIKS(M);
            }
            if (!IsBujurSangkar(M)){
		System.out.println("Tidak punya determinan karena bukan matriks persegi");
		System.out.println("Kembali ke Menu Utama");
		System.out.println("");
		MainMenu();	
            } 
            else {
		System.out.println("Silahkan pilih metode pencarian determinan [1= Ekspansi Kofaktor, 2= Segitiga Atas]");
		System.out.print("Masukkan pilihan : ");
		op2 = sc.nextInt();
		while (op2 != 1 && op2 != 2 && op2 != 3){
                    System.out.println("Pilihan salah !! Silahkan pilih metode pencarian determinan [1= Ekspansi Kofaktor, 2= Segitiga Atas, 3=Segitiga Bawah]");
                    System.out.print("Masukkan pilihan : ");
                    op2 = sc.nextInt();
                    System.out.println("");
		}
                if (op2 == 1) {
                    DeterminanKofaktor(M);
		} 
                else if (op2 == 2){
                    Segiatas(M);
		} 
		op3 = 1;
                    while (op3 != 1){
			System.out.println("Pilihan salah !! Ketik 1 untuk kembali ke Menu Utama");
			System.out.print("Masukkan pilihan : ");
			op3 = sc.nextInt();
			System.out.println("");
                    }
                    if (op3 == 1) {
			System.out.println("\n1 - Menu utama");
			System.out.println("2 - Input matrix kembali");
			System.out.print("Masukkan pilihan : ");
			int pili = sc.nextInt();
			if (pili == 1) {
				MainMenu();
			}else{
				System.out.println("Kembali ke Input Matrix");
			System.out.println("");
				backToDeterminan();
			}
			
                    } 
            }
    }

    public void Segiatas(MATRIKS M){
        /* I.S. M terdefinisi, IsBujurSangkar(M) */
        /* F.S. Menghitung nilai determinan matriks M dengan metode segitiga atas. 
		Menulis ke layar matriks segitiga atas yang terbentuk dan determinan matriks awal. */
	int n, i, j; 
	double line1, line2;
	float count = 1;
	boolean found;
	for (n = 0; n < M.NBrsEff; n++){
            if (M.Mem[n][n] == 0){
		found = false;
		i = n+1;
		while (!found && i < M.NBrsEff){
                    if (M.Mem[i][n] != 0){
			found = true;
                        for (j = n; j < M.NKolEff; j++){
                            M.Mem[n][j] += M.Mem[i][j];
			}
                    }
                    i++;
		}
		if (!found){
                    count = 0;
                    n = M.NBrsEff;
                    System.out.println("Matriks tidak dapat membentuk matriks segitiga atas.");
                    System.out.println("Kondisi matriks setelah melakukan OBE:");
                    TulisMATRIKS(M);
                    System.out.println();
                    System.out.println("Determinan matriks = 0.0");
		}  
            }
            for (i = n+1; i < M.NBrsEff; i++){       
                if (M.Mem[i][n] != 0){        
                    line1 = M.Mem[n][n];
                    line2 = M.Mem[i][n]; 
                    for (j = n; j < M.NKolEff; j++){
			M.Mem[n][j] /= line1;
			M.Mem[i][j] /= line2;
			M.Mem[i][j] -= M.Mem[n][j];
                    }
                    count *= (line1 * line2);
		}
            }
            count *= M.Mem[n][n];
    }
		if (count != 0){
			System.out.println("Matriks segitiga atas berhasil terbentuk.");
			System.out.println("Matriks segitiga atas tersebut adalah:");
			TulisMATRIKS(M);
			System.out.println();
			System.out.println("Determinan matriks = " + count);
			M.NDesc = 1;
			M.desc[0] = "Matriks Segitiga Atas, Determinannya adalah " + String.valueOf(count);
		}
	}

    // INVERS
    public void MenuInvers(){
	MATRIKS M = new MATRIKS();
	int op,op2,op3;
	System.out.println("Anda telah memilih menu Invers");
	op = 1;
	System.out.println("");
            while (op != 1){
		System.out.println("Pilihan salah !! Silahkan pilih metode pembacaan matriks [1= file, 2= keyboard]");
		System.out.print("Masukkan pilihan : ");
		op = 1;
		System.out.println("");
            }
            if (op == 1) {
		BacaMATRIKS(M);
            } 
            if (!IsBujurSangkar(M)){
		System.out.println("Tidak punya determinan karena bukan matriks persegi");
		System.out.println("Invers Tidak bisa dicari");	
		System.out.println("Kembali ke Menu Utama");
		System.out.println("");
		MainMenu();
            } 
            else if (!IsPunyaInvers(M)) {
		System.out.println("Tidak mempunyai invers karena determinan 0");
		System.out.println("Kembali ke Menu Utama");
		System.out.println("");
		MainMenu();
            } 
            else {
		op2 = 1;
		while (op2 != 1 && op2 != 2){
                    System.out.println("Pilihan salah !! Silahkan pilih metode pencarian invers [1= Adjoin , 2= Gauss Jordan ]");
                    System.out.print("Masukkan pilihan : ");
                    op2 = sc.nextInt();
                    System.out.println("");
		}
		if (op2 == 2) {
                    InverseKofaktor(M);
		}
                else if (op2 == 1){
                    InverseGaussJordan(M);
		}
		op3 = 1;
		while (op3 != 1){
                    System.out.println("Pilihan salah !! Ketik 1 untuk kembali ke Menu Utama");
                    System.out.print("Masukkan pilihan : ");
                    op3 = sc.nextInt();
                    System.out.println("");
		}
		if (op3 == 1) {
			System.out.println("\n1 - Menu utama");
			System.out.println("2 - Input matrix kembali");
			System.out.print("Masukkan pilihan : ");
			int pili = sc.nextInt();
			if (pili == 1) {
				MainMenu();
			}else{
				System.out.println("Kembali ke Input Matrix");
			System.out.println("");
				backToInverse();
			}
		} 
            }
    }

    public void InverseKofaktor (MATRIKS M){
	/* I.S. M terdefinisi */
	/* F.S. Memprint matriks invers dari matriks M dengan metode determinant dan adjoint */
	double det = EkspansiKofaktor(M);
        MATRIKSKofaktor2(M);
	Transpose(M);
	System.out.println("Matriks adjoint nya adalah :");
	TulisMATRIKS(M);
	System.out.println("");
	System.out.printf("Determinannya adalah : %.2f", det );
	System.out.println("");
	det = 1/det;
	PKaliKons(M, det);
	System.out.println("Matriks inverse nya adalah :");
	TulisMATRIKS(M);	
	System.out.println();
    }

    public void InverseGaussJordan (MATRIKS M) {
        System.out.println("Menggabungkan dengan matriks identitas!");
        System.out.println("Matriksnya adalah :");
        TulisMATRIKS(MergeInverseJordan(M));
        System.out.println();
        System.out.println("Melakukan GaussJordan");
        TulisMATRIKS(AugmentedInverseJordan(M));
        System.out.println();
        System.out.println("Matriksnya  inversnya adalah :");
        HasilInverseJordan(M);
        TulisMATRIKS(M);
        System.out.println();
    }
        
    public void GaussJordan(MATRIKS M){
	// I.S MATRIKS M
	// F.S matriks echelon tereduksi 
	int i;
	GaussSPL(M);
	for (i = 1; i < M.NBrsEff; i++){
            EliminasiOBEjordan(M, i);
	}
    }
        
    public void GaussSPL(MATRIKS M){
	// I.S Matriks M 
	// F.S Matriks echelon
	int i;
	for (i = 0; i < M.NBrsEff-1; i++){
            // kurangkan semua
            SortBaris(M); 
            EliminasiOBE(M,i);
            // SortBaris(M); 
	}
	BagiBaris(M,i);
    }
        
    public void EliminasiOBE(MATRIKS M, int indeks){
        // Prekondisi bahwa matriks udah di sort
	// I.S MATRIKS 
	// F.S dieliminasikan baris dengan indeks indeks
	int i,j,a; 
	
        // buat semua dimulai dengan angka 1 
	for (i=0; i < M.NBrsEff; i++){
            BagiBaris(M,i);
	}
	j=0;
	while (M.Mem[indeks][j] != 1 && j < M.NKolEff){
            j+=1;
	}
        if (j < M.NKolEff){
            for (i=indeks+1;i < M.NBrsEff;i++){
		if (M.Mem[i][j] == 1){
                    for (a=j; a < M.NKolEff; a++){
			M.Mem[i][a] -= M.Mem[indeks][a];
			}
		}
            }
	}
    }
        
    public void EliminasiOBEjordan(MATRIKS M , int indeks){
        // I.S INPUTAN MATRIKS dan Indeks yang akan menjadi acuan untuk dikurangi 
        // F.S semua diatas angka 1 adalah 0 
	int i,j,a;
	double kali; 
	j = 0;
	while (M.Mem[indeks][j] != 1 && j < M.NKolEff){
            j+=1;
	}
	if (j < M.NKolEff){
            for (i=0; i < indeks; i++){
		if (M.Mem[i][j] != 0){
                    kali = M.Mem[i][j];
                    for (a=j; a < M.NKolEff; a++){
			if (j < M.NKolEff -1){
                            M.Mem[i][a] = M.Mem[i][a] - (M.Mem[indeks][a] * kali);
			}
                    }
		}
            }
	}
    }

    public MATRIKS AugmentedInverseJordan(MATRIKS M){
        // I.S MATRIKS TELAH DI MERGE 
	// F.S MATRIKS DI OBE
	MATRIKS M1 = new MATRIKS();
	M1 = MergeInverseJordan(M);
	GaussJordan(M1);
	return M1 ;
    }

    public MATRIKS MergeInverseJordan (MATRIKS M){
        // I.S inputan matriks yang mau di inverse 
	// F.S MERGE MATRIKS
	int i,j;
	MATRIKS M1 = new MATRIKS();
	MakeMATRIKS(M.NBrsEff, 2*M.NKolEff, M1);
        for (i=0; i <M1.NBrsEff; i++){
            for (j=0; j<M1.NKolEff; j++){
		if (j < M.NKolEff){
                    M1.Mem[i][j] = M.Mem[i][j];
		}
                else{
                    if ((j- M.NKolEff) == i){
			M1.Mem[i][j] = 1.0;
                    }
                    else{
			M1.Mem[i][j] = 0.0;
                    }				
		}
            }
	}
	return M1;
    }

    public void HasilInverseJordan(MATRIKS M){
	// I.S MATRIKS TELAH DI OBE KAN  
	// F.S MATRIKS INVERSE
	int i,j;
	MATRIKS M1 = new MATRIKS();
	M1 = AugmentedInverseJordan(M);
        
        for (i=0; i<M.NBrsEff;i++){
            for (j=0; j <M.NKolEff; j++){
		M.Mem[i][j] = M1.Mem[i][j+M.NKolEff];
		}
            }
    }

    //SPL
    public void MenuSPL(){
	MATRIKS Maug = new MATRIKS();
	int op,op2,op3;
	System.out.println("Anda telah memilih menu SPL");
	op = 1;
	System.out.println("");
	while (op != 1){
            System.out.println("Pilihan salah !! Ketik 1 untuk melanjutkan");
            System.out.print("Masukkan pilihan : ");
            op = sc.nextInt();
            System.out.println("");
	}
	if (op == 1) {
            BacaMATRIKSAugmented(Maug);
	} 
	if (IsTidakAdaSolusi(Maug)){
            System.out.println("Matriks tidak konsisten sehingga tidak memiliki solusi");
            System.out.println("Kembali ke menu SPL");
            System.out.println("");
            MenuSPL();
        }
        System.out.println("Silahkan pilih metode penyelesaian SPL [1= Invers, 2= Metode Cramer,3= Gauss Jordan]");
        System.out.print("Masukkan pilihan : ");
        op2 = sc.nextInt();
        while (op2 != 1 && op2 != 2 && op2 != 3){
            System.out.println("Pilihan salah !! Silahkan pilih metode penyelesaian SPL [1= Invers, 2= Metode Cramer]");
            System.out.print("Masukkan pilihan : ");
            op2 = sc.nextInt();
            System.out.println("");
	}
        if (op2 == 1 || op2 == 2){
            if (Maug.NKolEff-1 > Maug.NBrsEff){
		System.out.println("Tidak punya determinan karena matriks koefisien bukan matriks persegi");
		System.out.println("Silahkan gunakan metode lain untuk menyelesaikan");	
		System.out.println("Kembali ke Menu Utama");
		System.out.println("");
		MainMenu();
            } 
            else {	
		MATRIKS MH = new MATRIKS();
		MATRIKS MK = new MATRIKS();
		GetMATRIKSHasil(Maug, MH);
		GetMATRIKSKoefisien(Maug, MK);
		if (!IsPunyaInvers(MK)) {
                    System.out.println("Tidak bisa menggunakan metode cramer maupun metode invers karena determinannya 0");
                    System.out.println("Silahkan gunakan metode lain untuk menyelesaikan");	
                    System.out.println("Kembali ke Menu Utama");
                    System.out.println("");
                    MainMenu();
		}
                else {
                    if (op2 == 1 ){
			SPLInvers(Maug, MK, MH);
                    } 
                    else if (op2 == 2){
			MetodeCramer(Maug, MK, MH);
                    }
		}
            }
        }
        else if (op2 == 3){
            // Gauss Jordan
            GaussJordan(Maug);
            System.out.println("Matriks Hasil Gauss Jordannya adalah");
            TulisMATRIKS(Maug);
            System.out.println("");
            eliminasiBaris(Maug);
            solusiGaussJordan(Maug);
        }
        
	System.out.println("");
	op3 = 1;
	while (op3 != 1){
            System.out.println("Pilihan salah !! Ketik 1 untuk kembali ke Menu Utama");
            System.out.print("Masukkan pilihan : ");
            op3 = sc.nextInt();
            System.out.println("");
	}
	if (op3 == 1){
		System.out.println("1 - Menu utama");
		System.out.println("2 - Input matrix kembali");
		System.out.print("Masukkan pilihan : ");
		int pili = sc.nextInt();
		if (pili == 1) {
			MainMenu();
		}else{
			System.out.println("Kembali ke Input Matrix");
		System.out.println("");
			backToSPL();
		}
	}         
    }
    
    public void SPLInvers (MATRIKS Maug, MATRIKS MK, MATRIKS MH) {
	/* I.S. Maug terdefinisi */
	/* F.S. Menyelesaikan SPL dengan metode invers */
	int i ;
        MATRIKS Solusi = new MATRIKS();
        System.out.println("Matriks Koef");
        TulisMATRIKS(MK);
	System.out.println();
	System.out.println("Matriks Hasil");
	TulisMATRIKS(MH);
	MakeMATRIKS(MK.NBrsEff, MH.NKolEff, Solusi);
	System.out.println();
	MK = Inverse(MK);
	Solusi = KaliMATRIKS(MK, MH);
	Maug.NDesc = MK.NBrsEff+1;
	Maug.desc[0] = "Diselesaikan dengan Invers SPL";
	for (i = 0 ; i< MK.NBrsEff; i++){
            System.out.printf("x"+ String.valueOf(i+1)+": "+ String.format("%.2f",Solusi.Mem[i][0]));
            System.out.println("");
            Maug.desc[i+1] = "\nx"+ String.valueOf(i+1)+": "+ String.format("%.2f",Solusi.Mem[i][0]);
	}
    }
    
    public MATRIKS MATRIKSCramer(MATRIKS MK, MATRIKS MH, int kol) {
	/* I.S. Mk, Mh, dan kol terdefinisi */
	/* F.S. Membentuk matriks cramer dengan Mk kolom indeks kol diganti dengan MH */
	MATRIKS Cramer = new MATRIKS();
	int i;
	MakeMATRIKS(MK.NBrsEff, MK.NKolEff, Cramer);
	CopyMATRIKS(MK, Cramer);
	for (i=0; i< Cramer.NBrsEff; i++){
            Cramer.Mem[i][kol] = MH.Mem[i][0];
	}
	return Cramer;
    }

    public void MetodeCramer (MATRIKS Maug, MATRIKS MK, MATRIKS MH) {
	/* I.S. Maug terdefinisi */
	/* F.S. Menyelesaikan SPL dengan metode Cramer */
	double[] solusi = new double[100];
	int i;
	System.out.println("Matriks Koef");
	TulisMATRIKS(MK);
	System.out.println();
	System.out.println("Matriks Hasil");
	TulisMATRIKS(MH);
	System.out.println();
	System.out.println("Nilai koef");
	Maug.NDesc = MK.NBrsEff+1;
	Maug.desc[0] = "Diselesaikan dengan metode cramer";
	for (i=0; i < MK.NKolEff; i++){
            solusi[i] = EkspansiKofaktor(MATRIKSCramer(MK, MH, i))/EkspansiKofaktor(MK);
            System.out.printf("x"+ String.valueOf(i+1)+": "+ String.format("%.2f",solusi[i]));
            Maug.desc[i+1] = "\nx"+ String.valueOf(i+1)+": "+ String.format("%.2f",solusi[i]);
            System.out.println("");
	}
    }
    
    public void GetMATRIKSKoefisien (MATRIKS MAug, MATRIKS MK){	
	/* I.S. MAug, dan MK terdefinisi */
	/* F.S. Mengambil matriks yang merupakan matriks koefisien dari variabel di matriks augmented */
	int i,j;
	MakeMATRIKS(MAug.NKolEff-1, MAug.NKolEff-1, MK);
	for (i=0; i< MK.NBrsEff;i++){
            for (j=0; j< MK.NKolEff;j++){
		MK.Mem[i][j] = MAug.Mem[i][j];
            }
	}
    }

    public void GetMATRIKSHasil (MATRIKS MAug, MATRIKS MH) {
	/* I.S. MAug, dan MH terdefinisi */
	/* F.S. Membentuk matriks MK berukuran Nx1 yang berisikan bagian konstanta dari MAug */
	int i;
	MakeMATRIKS(MAug.NKolEff-1, 1, MH);
	for (i=0; i< MAug.NBrsEff; i++){
            MH.Mem[i][0]=MAug.Mem[i][MAug.NKolEff-1];
	}
    }
    
    public Boolean isFree(MATRIKS M, int j){
		//  APAKAH MATRIKS dengan indeks  kolom j free
		int i;
		boolean isfree;
		isfree = true;
		for (i=0; i < M.NBrsEff; i++ ){
			if (M.Mem[i][j]  != 0){
				isfree = false;
			}
		}
		return isfree;

	}
    
    public String ParametrikOrUnik(MATRIKS M, int j){
		//  APAKAH MATRIKS dengan indeks  kolom parametrik
		int i, notzero, satu;
		notzero = 0;
		satu = 0;
		for (i=0; i < M.NBrsEff; i++ ){
			if (M.Mem[i][j]  != 0){
				notzero = notzero + 1;
			}
			if (M.Mem[i][j]  == 1){
				satu = satu + 1;
			}
		}
		if (satu ==1 && notzero ==1){
			return "Unik";
		} else {
			return "Parametrik";
		}
	}
    
    public void solusiGaussJordan(MATRIKS M){
		// I.S MATRIKS M 
		// F.S SOLUSI DARI MATRIKS 
		int i,j,now;
		boolean firstparam;
		String[] solusi = new String[100];
		
		// variabel yang sebenarnya tidak berguna
		for (j=0; j < M.NKolEff - 1; j++){
			if (isFree(M,j)){
				solusi[j] = "Free";
			} else {
				solusi[j] = ParametrikOrUnik(M,j);
			}
		}
		for (i=0; i < M.NBrsEff; i++){
			now = 99;
			firstparam = true;
			for (j=0; j <M.NKolEff-1; j++){
				if (M.Mem[i][j]!= 0){
					if (solusi[j] == "Unik" && now != 99){
						solusi[j] = "Parametrik";
					}
					if (solusi[j] == "Unik" && M.Mem[i][j]==1){
						now = j ;
						if (M.Mem[i][M.NKolEff-1]==0){
							solusi[j] ="X"+String.valueOf(j+1)+" :"+" 0"  ;
						} else {
							solusi[j] ="X"+String.valueOf(j+1)+" : " + String.format("%.2f",M.Mem[i][M.NKolEff-1]);
						}
					}
					if (solusi[j] == "Parametrik"){
						if (firstparam && M.Mem[i][M.NKolEff-1]==0){
							solusi[now] = solusi[now] +" "+String.valueOf(M.Mem[i][j]*-1)+"X"+String.valueOf(j+1);
							firstparam = false;
						} else {
							solusi[now] = solusi[now] +" + "+String.valueOf(M.Mem[i][j]*-1)+"X"+String.valueOf(j+1);
						} 
					}
				}
			}
		}
		System.out.println("Solusinya adalah :");
		for (j=0; j < M.NKolEff - 1; j++){
			if (solusi[j]=="Free" || solusi[j]=="Parametrik"){
				solusi[j] = "X"+String.valueOf(j+1)+" : " + "Free";
			}
			System.out.println(solusi[j]);
			M.desc[j] = "\n" + solusi[j];
		}
		M.NDesc = M.NKolEff - 1;
	}
    
    // TESTING
    public void Testing(){
	MainMenu();
    }

    public void MainMenu(){
	System.out.println("\n\n\n\n\n");
	int op;
	System.out.println("MENU PROGRAM");
	System.out.println("1. Determinan");
	System.out.println("2. Invers (Gauss-Jordan / OBE )");
        System.out.println("3. Sistem Persamaan Linear");
	System.out.println("4. Keluar");
	System.out.print("Masukkan pilihan menu yang diinginkan : ");
	op = sc.nextInt();
	
        while (op != 1 && op != 2 && op != 3){
            System.out.println("");
            System.out.println("Anda Memasukkan Menu yang salah, silahkan ulangi");
            System.out.println("Masukkan pilihan menu yang diinginkan : ");
            System.out.println("MENU PROGRAM");
            System.out.println("1. Determinan");
            System.out.println("2. Invers");
            System.out.println("3. Sistem Persamaan Linear");
            System.out.println("4. Keluar");
            System.out.print("Masukkan pilihan menu yang diinginkan : ");
            op = sc.nextInt();
	}
        System.out.println("");
	if (op == 1) {
            MenuDeterminan();
	} 
        else if(op == 2){
            MenuInvers();
	} 
        else if(op == 3){
            MenuSPL();
	} 
        else if(op == 4){
            Exit();
        } 
    }

    public void Exit(){
        // Exit  
        System.exit(0);
    }

	public void backToDeterminan(){
		MenuDeterminan();
	}

	public void backToInverse(){
		MenuInvers();
	}

	public void backToSPL(){
		MenuSPL();
	}
}


	