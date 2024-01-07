import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;

    //Kelas Jadwal KAI yang mengimplementasikan Interface Database
    public class JadwalKAI implements Database {

        //Untuk mengkoneksikan ke Database di MySQL 
        Connection conn;
        String link = "jdbc:mysql://localhost:3306/kai";
        Scanner input = new Scanner(System.in);

        //Atribut Kelas Jadwal KAI
        String id_kereta;
        String lokasi_berangkat_kereta; 
        String lokasi_tujuan_kereta;
        String jadwal_kereta;
        Integer harga_tiket;

        //Method
        public void method() { }
        
        //Implementasi dari Interface Database
        //Proses Penginputan Jadwal Kereta
        @Override
        public void insert() throws SQLException {

            System.out.println("--------------------------------------");
            System.out.print("\n          Tambah Jadwal Kereta");
            System.out.println("\n************************************");
            System.out.print("\nID Kereta                   : ");
            this.id_kereta = input.nextLine();

            System.out.print("Lokasi Berangkat Kereta       : ");
            this.lokasi_berangkat_kereta = input.nextLine();

            System.out.print("Lokasi Tujuan Kereta          : ");
            this.lokasi_tujuan_kereta = input.nextLine();

            System.out.print("Jadwal Kereta                 : ");
            this.jadwal_kereta = input.nextLine();

            System.out.print("Harga Tiket                   : Rp ");
            this.harga_tiket = input.nextInt();
    
             //Untuk memasukkan data ke Database KAI, dalam tabel Jadwal Kereta
            String sql = "INSERT INTO jadwal_kereta (id_kereta, lokasi_berangkat_kereta, lokasi_tujuan_kereta,  jadwal_kereta, harga_tiket) VALUES ('"+id_kereta+"','"+lokasi_berangkat_kereta+"','"+lokasi_tujuan_kereta+"','"+jadwal_kereta+"', '"+harga_tiket+"' )";
            conn = DriverManager.getConnection(link,"root","");
	        Statement statement = conn.createStatement();
	        statement.execute(sql);

	        System.out.println("\n ***Input Data Berhasil!***");
        
            statement.close();
        }

        //Proses Melihat Jadwal Kereta yang telah di inputkan
        @Override
        public void view() throws SQLException {

            //Mengakses data Jadwal Kereta yang berada di Database KAI
            String sql = "SELECT * FROM jadwal_kereta";
            conn = DriverManager.getConnection(link,"root","");
            Statement statement = conn.createStatement();
		    ResultSet result = statement.executeQuery(sql);

            //Menampilkan data Jadwal Kereta
            while (result.next()) {
                System.out.print("\nID Kereta                   : ");
                System.out.println(result.getString("id_kereta"));
                System.out.print("Lokasi Berangkat Kereta       : ");
                System.out.println(result.getString("lokasi_berangkat_kereta"));
                System.out.print("Lokasi Tujuan Kereta          : ");
                System.out.println(result.getString("lokasi_tujuan_kereta"));
                System.out.print("Jadwal Kereta                 : ");
                System.out.println(result.getString("jadwal_kereta"));
                System.out.print("Harga Tiket                   : Rp ");
                System.out.println(result.getInt("harga_tiket")); 
            }
        
            statement.close();
        }
    
        //Proses Mengupdate Jadwal Kereta
        @Override
        public void update() throws SQLException {
            try {

            view();
            Integer pil;
            System.out.print("\n         Ubah Jadwal Kereta");
            System.out.println("************************************");
            System.out.print("\nID Kereta : ");
            String ubah = input.nextLine();

            //Mengakses data Jadwal Kereta yang berada di Database KAI
            String sql = "SELECT * FROM jadwal_kereta WHERE id_kereta ='"+ubah+"'";
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);

                //Percabangan  if
                if (result.next()) {

                    System.out.println("\nData yang akan diubah: ");
                    System.out.println("1.Jadwal Kereta");
                    System.out.println("2.Harga Tiket");
                    System.out.print("Pilih [1/2] : ");

                    pil = input.nextInt();
                    input.nextLine();
        
                    //Percabangan switch case
                    switch (pil) {

                        case 1:

                            System.out.print("\nJadwal Kereta ["+result.getString("jadwal_kereta")+"]\t: ");
                            String ubah1 = input.nextLine();

                            //Mengupdate data Jadwal Kereta di tabel Jadwal Kereta
                            sql = "UPDATE jadwal_kereta SET jadwal_kereta = '"+ubah1+"' WHERE id_kereta ='"+ubah+"'";

                                if(statement.executeUpdate(sql) > 0) {
                                    System.out.println("Jadwal Berhasil Di Perbaharui! (ID Kereta "+ubah+")"); }

                        break;
        
                        case 2:

                            System.out.print("\nHarga Tiket ["+result.getInt("harga_tiket")+"]\t: ");
                            Integer ubah2 = input.nextInt();

                            //Mengupdate data Harga Tiket di tabel Jadwal Kereta
                            sql = "UPDATE jadwal_kereta SET harga_tiket = "+ubah2+" WHERE id_kereta ='"+ubah+"'";
                            input.nextLine();

                                if(statement.executeUpdate(sql) > 0) {
                                    System.out.println("Harga Berhasil Di Perbaharui! (ID Travel "+ubah+")"); }

                        break;

                        default :

                            System.out.println("\n\t***ERROR***");
                            System.out.println("Inputkan Angka 1 atau 2 Saja!");

                        break;
                    }
                }

                else {
                    System.out.println("**Error!**");
                }
            }

            //Exeption SQL 
            catch (SQLException e) {
                System.err.println("Update Data Tidak Berhasil"); }

            //Exception input tidak sesuai jenis data
            catch (InputMismatchException e) {
                System.err.println("Gagal! Masukkan Data yang Benar"); }
        }

        //Proses penghapusan Jadwal Kereta
        @Override
        public void delete() throws SQLException {
            
            try {

            System.out.println("Penghapusan Jadwal Kereta");
            System.out.println("\n************************************ ");
            System.out.println("ID Kereta : " );
            this.id_kereta = input.next();

            //Menghapus data Jadwal Kereta di Database KAI, dalam tabel Jadwal Kereta
            String sql = "DELETE FROM jadwal_kereta WHERE id_kereta = "+id_kereta;
	        conn = DriverManager.getConnection(link, "root","");
	        Statement statement = conn.createStatement();

            //ResultSet result = statement.executeQuery(sql);
            if(statement.executeUpdate(sql) > 0){
	            System.out.println("\n***Jadwal Kereta dengan ID:  " +id_kereta+ "Berhasil Dihapus***"); }

            }

            catch(SQLException e) {
	            System.out.println("Terjadi Kesalahan Penghapusan Data "); }

            catch(Exception e)  {
                System.out.println("Masukan ID Kereta dengan Benar"); }
            
            }

        //Method Save
        public void save() throws SQLException { }
    
    }