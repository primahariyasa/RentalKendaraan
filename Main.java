import java.util.*;
import java.io.*;

// Kelas abstrak sebagai induk
abstract class Kendaraan {
    private String nama;
    private double hargaSewa;
    private String jenis;
    private boolean status; // true = tersedia, false = tidak tersedia

    public Kendaraan(String nama, double hargaSewa, String jenis, boolean status) {
        this.nama = nama;
        this.hargaSewa = hargaSewa;
        this.jenis = jenis;
        this.status = status;
    }

    public String getNama() {
        return nama;
    }

    public double getHargaSewa() {
        return hargaSewa;
    }

    public String getJenis() {
        return jenis;
    }

    public boolean isTersedia() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public abstract void tampilKendaraan();
}

// Turunan kelas Mobil
class Mobil extends Kendaraan {
    private int jumlahKursi;

    public Mobil(String nama, double hargaSewa, int jumlahKursi) {
        super(nama, hargaSewa, "Mobil", true);
        this.jumlahKursi = jumlahKursi;
    }

    @Override
    public void tampilKendaraan() {
        System.out.println("Mobil - " + getNama() + " | Harga: " + getHargaSewa() + " | Kursi: " + jumlahKursi + " | Tersedia: " + isTersedia());
    }
}

// Turunan kelas Motor
class Motor extends Kendaraan {
    private int cc;

    public Motor(String nama, double hargaSewa, int cc) {
        super(nama, hargaSewa, "Motor", true);
        this.cc = cc;
    }

    @Override
    public void tampilKendaraan() {
        System.out.println("Motor - " + getNama() + " | Harga: " + getHargaSewa() + " | CC: " + cc + " | Tersedia: " + isTersedia());
    }
}

// Turunan kelas Bus
class Bus extends Kendaraan {
    private int kapasitas;

    public Bus(String nama, double hargaSewa, int kapasitas) {
        super(nama, hargaSewa, "Bus", true);
        this.kapasitas = kapasitas;
    }

    @Override
    public void tampilKendaraan() {
        System.out.println("Bus - " + getNama() + " | Harga: " + getHargaSewa() + " | Kapasitas: " + kapasitas + " | Tersedia: " + isTersedia());
    }
}

// Manajemen data kendaraan
class RentalKendaraan {
    private ArrayList<Kendaraan> daftarKendaraan = new ArrayList<>();

    public void tambahKendaraan(Kendaraan k) {
        daftarKendaraan.add(k);
    }

    public void tampilkanKendaraan() {
        for (int i = 0; i < daftarKendaraan.size(); i++) {
            System.out.print((i + 1) + ". ");
            daftarKendaraan.get(i).tampilKendaraan();
        }
    }

    public Kendaraan getKendaraan(int index) {
        return daftarKendaraan.get(index);
    }

    public int getJumlah() {
        return daftarKendaraan.size();
    }

    public ArrayList<Kendaraan> getDaftar() {
        return daftarKendaraan;
    }

    public void simpanKeFile(String namaFile) {
        try (PrintWriter out = new PrintWriter(new FileWriter(namaFile))) {
            for (Kendaraan k : daftarKendaraan) {
                out.println(k.getJenis() + ";" + k.getNama() + ";" + k.getHargaSewa() + ";" + k.isTersedia());
            }
        } catch (IOException e) {
            System.out.println("Gagal menyimpan ke file.");
        }
    }
}

// Data penyewaan pelanggan
class Penyewaan {
    private ArrayList<Kendaraan> kendaraanDisewa = new ArrayList<>();

    public void sewa(Kendaraan k) throws Exception {
        if (!k.isTersedia()) {
            throw new Exception("Kendaraan tidak tersedia untuk disewa.");
        }
        kendaraanDisewa.add(k);
        k.setStatus(false);
    }

    public double hitungTotal() {
        double total = 0;
        for (Kendaraan k : kendaraanDisewa) {
            total += k.getHargaSewa();
        }
        return total;
    }

    public void tampilkanStruk() {
        System.out.println("\n--- Struk Penyewaan ---");
        for (Kendaraan k : kendaraanDisewa) {
            System.out.println(k.getJenis() + " - " + k.getNama() + ": Rp " + k.getHargaSewa());
        }
        System.out.println("Total Harga: Rp " + hitungTotal());
    }

    public void simpanStruk(String namaFile) {
        try (PrintWriter out = new PrintWriter(new FileWriter(namaFile))) {
            for (Kendaraan k : kendaraanDisewa) {
                out.println(k.getJenis() + " - " + k.getNama() + ": Rp " + k.getHargaSewa());
            }
            out.println("Total Harga: Rp " + hitungTotal());
        } catch (IOException e) {
            System.out.println("Gagal menyimpan struk.");
        }
    }
}

// Program utama
public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        RentalKendaraan rental = new RentalKendaraan();
        Penyewaan penyewaan = new Penyewaan();

        while (true) {
            System.out.println("\n===== MENU UTAMA RENTAL KENDARAAN =====");
            System.out.println("1. Tambah Kendaraan");
            System.out.println("2. Tampilkan Daftar Kendaraan");
            System.out.println("3. Sewa Kendaraan");
            System.out.println("4. Tampilkan Struk");
            System.out.println("5. Simpan ke File");
            System.out.println("6. Keluar");
            System.out.print("Pilih menu: ");
            int pilihan = input.nextInt();
            input.nextLine(); // flush newline

            try {
                switch (pilihan) {
                    case 1:
                        System.out.print("Jenis Kendaraan (Mobil/Motor/Bus): ");
                        String jenis = input.nextLine();
                        System.out.print("Nama Kendaraan: ");
                        String nama = input.nextLine();
                        System.out.print("Harga Sewa: ");
                        double harga = input.nextDouble();

                        if (jenis.equalsIgnoreCase("Mobil")) {
                            System.out.print("Jumlah Kursi: ");
                            int kursi = input.nextInt();
                            rental.tambahKendaraan(new Mobil(nama, harga, kursi));
                        } else if (jenis.equalsIgnoreCase("Motor")) {
                            System.out.print("CC Mesin: ");
                            int cc = input.nextInt();
                            rental.tambahKendaraan(new Motor(nama, harga, cc));
                        } else if (jenis.equalsIgnoreCase("Bus")) {
                            System.out.print("Kapasitas Penumpang: ");
                            int kapasitas = input.nextInt();
                            rental.tambahKendaraan(new Bus(nama, harga, kapasitas));
                        } else {
                            System.out.println("Jenis tidak dikenal.");
                        }
                        break;

                    case 2:
                        rental.tampilkanKendaraan();
                        break;

                    case 3:
                        rental.tampilkanKendaraan();
                        System.out.print("Pilih nomor kendaraan yang ingin disewa: ");
                        int index = input.nextInt() - 1;
                        if (index >= 0 && index < rental.getJumlah()) {
                            Kendaraan k = rental.getKendaraan(index);
                            penyewaan.sewa(k);
                            System.out.println("Berhasil disewa!");
                        } else {
                            System.out.println("Indeks tidak valid.");
                        }
                        break;

                    case 4:
                        penyewaan.tampilkanStruk();
                        break;

                    case 5:
                        rental.simpanKeFile("daftar_kendaraan.txt");
                        penyewaan.simpanStruk("struk_penyewaan.txt");
                        System.out.println("Data berhasil disimpan.");
                        break;

                    case 6:
                        System.out.println("Terima kasih telah menggunakan program rental kendaraan.");
                        return;

                    default:
                        System.out.println("Pilihan tidak valid.");
                }
            } catch (Exception e) {
                System.out.println("Terjadi kesalahan: " + e.getMessage());
            }
        }
    }
}
