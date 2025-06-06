public class Mobil extends Kendaraan {
    private int jumlahPintu;

    public Mobil(String nama, double hargaSewa, int jumlahPintu) {
        super(nama, hargaSewa, "Mobil");
        this.jumlahPintu = jumlahPintu;
    }

    @Override
    public void tampilKendaraan() {
        System.out.println("Mobil: " + getNama() + ", Harga: " + getHargaSewa() +
                           ", Pintu: " + jumlahPintu + ", Tersedia: " + isTersedia());
    }
}