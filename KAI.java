import java.sql.*;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

    //Driver Kelas
    public class KAI {
    
        //Untuk mengkoneksikan ke Database di MySQL
        static Connection conn;
        static String link = "jdbc:mysql://localhost:3306/kai";
        static Scanner input = new Scanner(System.in);
    
        //Main Method
        public static void main(String[] args) throws Exception {
        
            System.out.println("\n===============KAI===============");
            System.out.println("\n   Bagaimana Kabarmu Hari Ini?");
            String salamSapa = "-Semoga Perjalananmu Menyenangkan-";
            System.out.println(salamSapa.toUpperCase());

            //Method
            admin();
            menu();
        
            //String and Date
            DateFormat formatTanggal = new SimpleDateFormat("dd MMMM yyyy");
            DateFormat formatJam = new SimpleDateFormat("HH:mm:ss");
            Date tanggal = new Date();

            System.out.println("====================================");
            System.out.println("= Dibuat pada     : "+formatTanggal.format(tanggal)+" =");
            System.out.println("= Diupdate pada   : "+formatJam.format(tanggal)+" WIB    =");
            System.out.println("====================================");
        }

        private static void admin() throws SQLException {

            //Membuat Objek HashMap Baru
            Map<String, String> Login = new HashMap<String, String>();

            //Membaca Data di Database KAI, dalam tabel LOGIN
            String inputUsername;
            String inputPassword;

            String sql = "SELECT * FROM login";
            boolean relogin = true;
            conn = DriverManager.getConnection(link,"root","");
            Statement statement = conn.createStatement();
		    ResultSet result = statement.executeQuery(sql);

            //Perulangan while
            while (result.next()) {
            
                //Mengambil nilai di Database dan menyimpannya ke dalam variable
                String username = result.getString("username");
                String password = result.getString("password");

                //Input key dan value 
                Login.put(username, password);
            }

            System.out.println("------------------------------------");

            //Perulangan while untuk Login
            while (relogin) {

                System.out.print("Username : ");
                inputUsername = input.nextLine();

                System.out.print("Password : ");
                inputPassword = input.nextLine();

                //Jika Login Berhasil
                if (Login.get(inputUsername).equals(inputPassword)==true) {
                    System.out.println("\n       ***Login Berhasil***");
                    relogin = false; }
                //Jika Login Gagal
                if (Login.get(inputUsername).equals(inputPassword)==false) {
                    System.out.println("***Login Gagal***");
                    System.out.println("\n Masukkan Username atau Password yang Benar!");
                    relogin = true; }
            }

            statement.close();
        }

        private static void menu() throws SQLException {
        
            boolean MenuKembali = true;
            boolean TanyaBalik = true;
            Integer pilihan;
            String pertanyaan;

            //Perulangan while
            while (MenuKembali) {

            //Menampilkan List Menu
            System.out.println("------------------------------------");
            System.out.println("\n              LIST MENU           ");
            System.out.println("************************************");
            System.out.println("1.Lihat Jadwal Kereta");
            System.out.println("2.Input Jadwal Kereta");
            System.out.println("3.Ubah Jadwal Kereta");
            System.out.println("4.Hapus Jadwal Kereta");
            System.out.println("5.Booking Tiket Kereta");
            System.out.println("6.Pembatalan Booking Tiket Kereta");
            System.out.println("7.Keluar Program");
            System.out.print("\n Pilih [1-7]!: ");
            
            pilihan = input.nextInt();
            input.nextLine();

            JadwalKAI jadwal_kereta = new JadwalKAI();
            BookingTiketKereta booking_tiket = new BookingTiketKereta();
            
                //Percabangan switch case
                switch (pilihan) {

                    //Melihat Jadwal Kereta
                    case 1:
                        jadwal_kereta.view();
                        TanyaBalik = true;
                    break;
                 
                    //Menginputkan Jadwal Kereta
                    case 2:
                        jadwal_kereta.insert();
                        TanyaBalik= true;
                    break;

                    //Mengupdate Jadwal Kereta
                    case 3:
                        jadwal_kereta.update();
                        TanyaBalik = true;
                    break;

                    //Menghapus Jadwal Kereta
                    case 4:
                        jadwal_kereta.delete();
                        TanyaBalik = true;
                    break;

                    //Membooking Tiket
                    case 5:
                        booking_tiket.save();
                        TanyaBalik = true;
                    break;

                    //Membatalkan Tiket yang telah di booking atau di pesan
                    case 6:
                        booking_tiket.delete();
                        TanyaBalik = true;
                    break;

                    //Keluar Program
                    case 7:
                        TanyaBalik = false;
                        MenuKembali = false;
                    break;

                    //Jika Inputan tidak Sesuai
                    default :
                        System.out.println("Inputkan Angka  [1 - 7]!");
                    break;
                }
            
                //Perulangan while
                while (TanyaBalik) {
                    System.out.print("\n Kembali ke menu utama [Ya/Tidak]? ");
                    pertanyaan = input.nextLine();

                    //Percabangan if else if
                        if (pertanyaan.equalsIgnoreCase("Tidak")) { //Method String
                            MenuKembali = false;
                            TanyaBalik = false; }

                        else if (pertanyaan.equalsIgnoreCase("Ya")) { //Method String
                            MenuKembali = true;
                            TanyaBalik = false; }
                
                        else {
                            System.out.println("Inputkan 'Ya' atau 'Tidak' saja"); }
                }
            }  

            //Program Selesai
            System.out.println("\n\n\t\tSelesai\n");
        }

        //Method Penghapusan Booking Tiket Kereta
        private static void booking_tiket_keretadelete() { }

    }