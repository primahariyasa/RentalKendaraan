import java.util.ArrayList;

public class RentalKendaraan {
    private ArrayList<Kendaraan> daftarKendaraan = new ArrayList<>();

    public void tambahKendaraan(Kendaraan k) {
        daftarKendaraan.add(k);
    }

    public ArrayList<Kendaraan> getKendaraanTersedia() {
        ArrayList<Kendaraan> tersedia = new ArrayList<>();
        for (Kendaraan k : daftarKendaraan) {
            if (k.isTersedia()) {
                tersedia.add(k);
            }
        }
        return tersedia;
    }

    public ArrayList<Kendaraan> getSemuaKendaraan() {
        return daftarKendaraan;
    }

    public Kendaraan cariKendaraan(String nama) {
        for (Kendaraan k : daftarKendaraan) {
            if (k.getNama().equalsIgnoreCase(nama)) {
                return k;
            }
        }
        return null;
    }
}