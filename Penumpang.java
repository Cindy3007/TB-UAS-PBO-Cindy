//Kelas Penumpang
public class Penumpang {

    //Atribut Kelas Penumpang
    String id_penumpang;
    String nama_penumpang;

    //Konstructor
    public Penumpang() { }

    //konstructor
    public Penumpang (String id_penumpang, String nama_penumpang) {

        this.id_penumpang = id_penumpang;
        this.nama_penumpang = nama_penumpang;

        System.out.println("\n");
        System.out.println("Penumpang dengan ID" +this.id_penumpang+" telah tercatat sebagai member baru!");
        System.out.println("Atas Nama Mr./Mrs. " +this.nama_penumpang);
    }

    public void method() { }
}