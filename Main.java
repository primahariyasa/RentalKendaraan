import java.io.*;
import java.util.Scanner;

public class Main {
    static Scanner input = new Scanner(System.in);
    static RentalKendaraan rental = new RentalKendaraan();
    static Penyewaan penyewaan = new Penyewaan();

    public static void main(String[] args) {
        loadData();
        int pilihan;
        do {
            System.out.println("\n--- MENU UTAMA ---");
            System.out.println("1. Tambah Kendaraan");
            System.out.println("2. Tampilkan Kendaraan Tersedia");
            System.out.println("3. Sewa Kendaraan");
            System.out.println("4. Tampilkan Struk");
            System.out.println("5. Simpan & Keluar");
            System.out.print("Pilih: ");
            pilihan = Integer.parseInt(input.nextLine());

            switch (pilihan) {
                case 1 -> tambahKendaraan();
                case 2 -> tampilKendaraanTersedia();
                case 3 -> sewaKendaraan();
                case 4 -> penyewaan.tampilStruk();
                case 5 -> {
                    simpanData();
                    System.out.println("Data disimpan. Keluar...");
                }
                default -> System.out.println("Pilihan tidak valid.");
            }
        } while (pilihan != 5);
    }

    static void tambahKendaraan() {
        System.out.print("Jenis (Mobil/Motor/Bus): ");
        String jenis = input.nextLine();
        System.out.print("Nama: ");
        String nama = input.nextLine();
        System.out.print("Harga Sewa: ");
        double harga = Double.parseDouble(input.nextLine());

        switch (jenis.toLowerCase()) {
            case "mobil" -> {
                System.out.print("Jumlah Pintu: ");
                int pintu = Integer.parseInt(input.nextLine());
                rental.tambahKendaraan(new Mobil(nama, harga, pintu));
            }
            case "motor" -> {
                System.out.print("CC: ");
                int cc = Integer.parseInt(input.nextLine());
                rental.tambahKendaraan(new Motor(nama, harga, cc));
            }
            case "bus" -> {
                System.out.print("Kapasitas Penumpang: ");
                int kapasitas = Integer.parseInt(input.nextLine());
                rental.tambahKendaraan(new Bus(nama, harga, kapasitas));
            }
            default -> System.out.println("Jenis tidak dikenal.");
        }
    }

    static void tampilKendaraanTersedia() {
        for (Kendaraan k : rental.getKendaraanTersedia()) {
            k.tampilKendaraan();
        }
    }

    static void sewaKendaraan() {
        System.out.print("Masukkan nama kendaraan: ");
        String nama = input.nextLine();
        Kendaraan k = rental.cariKendaraan(nama);
        if (k == null) {
            System.out.println("Kendaraan tidak ditemukan.");
            return;
        }
        try {
            penyewaan.sewa(k);
            System.out.println("Berhasil disewa!");
        } catch (Exception e) {
            System.out.println("Gagal menyewa: " + e.getMessage());
        }
    }

    static void simpanData() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("data.ser"))) {
            oos.writeObject(rental.getSemuaKendaraan());
        } catch (IOException e) {
            System.out.println("Gagal menyimpan data: " + e.getMessage());
        }

        try (PrintWriter pw = new PrintWriter("struk.txt")) {
            for (Kendaraan k : penyewaan.getDisewa()) {
                pw.println(k.getNama() + "," + k.getHargaSewa());
            }
            pw.println("Total: " + penyewaan.hitungTotal());
        } catch (IOException e) {
            System.out.println("Gagal menyimpan struk: " + e.getMessage());
        }
    }

    static void loadData() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("data.ser"))) {
            rental.getSemuaKendaraan().addAll((ArrayList<Kendaraan>) ois.readObject());
        } catch (Exception e) {
            System.out.println("Tidak ada data sebelumnya.");
        }
    }
}