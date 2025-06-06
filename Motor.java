public class Motor extends Kendaraan {
    private int cc;

    public Motor(String nama, double hargaSewa, int cc) {
        super(nama, hargaSewa, "Motor");
        this.cc = cc;
    }

    @Override
    public void tampilKendaraan() {
        System.out.println("Motor: " + getNama() + ", Harga: " + getHargaSewa() +
                           ", CC: " + cc + ", Tersedia: " + isTersedia());
    }
}