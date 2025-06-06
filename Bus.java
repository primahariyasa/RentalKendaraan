public class Bus extends Kendaraan {
    private int kapasitasPenumpang;

    public Bus(String nama, double hargaSewa, int kapasitas) {
        super(nama, hargaSewa, "Bus");
        this.kapasitasPenumpang = kapasitas;
    }

    @Override
    public void tampilKendaraan() {
        System.out.println("Bus: " + getNama() + ", Harga: " + getHargaSewa() +
                           ", Kapasitas: " + kapasitasPenumpang + ", Tersedia: " + isTersedia());
    }
}