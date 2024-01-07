import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;

//kelas Booking Tiket Kereta (KELAS ANAK) yang mewarisi kelas Jadwal KAI (KELAS INDUK)
    public class BookingTiketKereta extends JadwalKAI {

        //Untuk mengkoneksikan ke Database di MySQL 
        Connection conn;
        String link = "jdbc:mysql://localhost:3306/kai";
    
        //Atribut Kelas Booking Tiket Kereta
        Scanner input = new Scanner(System.in);
        String id_petugas;
        String nama_petugas;
        String tanggal_booking;  
        String id_penumpang;
        String nama_penumpang;
        String jumlah_tiket;
        Integer jumlahtiketMax = 50;
        Integer tiket_tersedia;
        Integer tiket_dipesan;
        Integer total_harga;

        //Menginputkan ID dan Nama Petugas
        public void id_petugas() {
            
        System.out.print("\nID Petugas          : ");
        this.id_petugas = input.nextLine();}

        public void nama_petugas() {
        System.out.print("Nama Petugas          : ");
        this.nama_petugas = input.nextLine();}

        //Menginputkan kapan tiket di Booking atau di pesan
        public void tanggal_booking() {
        System.out.print("Tanggal Booking       : ");
        this.tanggal_booking = input.nextLine(); }
        
        //Menginputkan ID dan Nama Penumpang
        public void id_penumpang() {
        System.out.print("ID Penumpang          : ");
        this.id_penumpang = input.nextLine(); }
        
        public void nama_penumpang() {
        System.out.print("Nama Penumpang        : ");
        this.nama_penumpang = input.nextLine(); }

        //Menginputkan ID Kereta yang akan di pesan
        public void id_kereta() {
        System.out.print("ID Kereta             : ");
        this.id_kereta = input.nextLine(); }
        
        
        //Menginputkan Harga dan Jumlah Tiket yang akan di pesan
        public void harga_tiket() {
        System.out.print("Harga Tiket           : Rp ");
        this.harga_tiket = input.nextInt(); }
        
        public void jumlah_tiket() {

            // Maximum penjualan atau Pembelian tiket kereta yaitu sebanyak 50 lembar pada setiap jadwal kereta
            System.out.print("Jumlah Tiket          : ");
            tiket_dipesan = input.nextInt(); {
                try {
                    if (tiket_dipesan <= 0 && tiket_dipesan > 50)  
                        System.out.print("Tiket Tidak Tersedia atau Sudah Habis"); }

                catch (Exception nullpException) {
                    System.out.println("\nERROR!\n"); }
            }
        }

        //Total Harga yang harus dibayarkan oleh penumpang
        public void total_harga() {

            // Menghitung Total harga yang harus dibayar penumpang
            this.total_harga = this.harga_tiket * this.tiket_dipesan;
            System.out.print("\nTotal Harga           : Rp " + this.total_harga + ""); }

        //Kelas Penumpang  
        public void penumpang() throws SQLException {
        Penumpang penumpang = new Penumpang(this.id_penumpang, this.nama_penumpang);
        penumpang.method(); }
    
        //Pewarisan dari Kelas Jadwal KAI
        //Proses Pembookingan atau Pemesanan Tiket Kereta
        @Override
        public void save() throws SQLException {
            try {
                
                view();
                System.out.print("\n         Booking Tiket Kereta");
                System.out.println("\n************************************");
                id_petugas();
                nama_petugas();
                tanggal_booking();
                id_penumpang();
                nama_penumpang();
                id_kereta();
                harga_tiket();
                jumlah_tiket();
                total_harga();

                penumpang();

                //Untuk memasukkan data ke Database KAI, dalam tabel Booking Tiket
                String sql = "INSERT INTO booking_tiket (tanggal_booking, id_penumpang, nama_penumpang, id_kereta, harga_tiket, jumlah_tiket, total_harga) VALUES ('"+tanggal_booking+"', '"+id_penumpang+"', '"+nama_penumpang+"', '"+id_kereta+"', '"+harga_tiket+"', '"+jumlah_tiket+"', '"+total_harga+"')";
                conn = DriverManager.getConnection(link,"root","");
	            Statement statement = conn.createStatement();
	            statement.execute(sql);

                System.out.println("\nTiket Berhasil Dipesan"); 
            }

            //Exception SQL
            catch(SQLException e) {
                System.err.println("\nTiket Gagal Dipesan"); }

            //Exception input tidak sesuai dengan tipe data
            catch(InputMismatchException e) {
                System.out.println("\nTipe Inputan Data Harus Benar"); }
        }

        //Proses untuk membatalkan Tiket yang telah di booking atau di pesan
        @Override
        public void delete() throws SQLException {
            try {

                System.out.println("Pembatalan Booking Tiket Kereta");
                System.out.println("\n************************************");
                System.out.println("ID Penumpang : " );
                this.id_penumpang = input.next();

                //Untuk menghapus data di Database KAI, dalam tabel Booking Tiket
                String sql = "DELETE FROM booking_tiket_kereta WHERE id_penumpang = "+id_penumpang;
	            conn = DriverManager.getConnection(link, "root","");
	            Statement statement = conn.createStatement();

	            //ResultSet result = statement.executeQuery(sql);
                if(statement.executeUpdate(sql) > 0){
	                System.out.println("***Booking "+id_penumpang+"Berhasil Di Batalkan***"); }
            }

            catch(SQLException e) {
	            System.out.println("Terjadi Kesalahan Penghapusan Data "); }

            catch(Exception e)  {
                System.out.println("Masukan ID Penumpang dengan Benar"); }

        }

    }