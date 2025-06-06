import java.util.ArrayList;

public class Penyewaan {
    private ArrayList<Kendaraan> disewa = new ArrayList<>();

    public void sewa(Kendaraan k) throws Exception {
        if (!k.isTersedia()) {
            throw new Exception("Kendaraan tidak tersedia untuk disewa.");
        }
        k.setStatus(false);
        disewa.add(k);
    }

    public double hitungTotal() {
        double total = 0;
        for (Kendaraan k : disewa) {
            total += k.getHargaSewa();
        }
        return total;
    }

    public void tampilStruk() {
        System.out.println("Struk Penyewaan:");
        for (Kendaraan k : disewa) {
            k.tampilKendaraan();
        }
        System.out.println("Total Harga: " + hitungTotal());
    }

    public ArrayList<Kendaraan> getDisewa() {
        return disewa;
    }
}