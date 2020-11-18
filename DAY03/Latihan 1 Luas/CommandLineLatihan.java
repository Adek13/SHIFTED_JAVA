public class CommandLineLatihan {
    public static void main(String[] args) {
        switch (Integer.parseInt(args[0])) {
            case 1:
                System.out.println("Luas Persegi : " + Luas(Integer.parseInt(args[1])));
                break;
            case 2:
                System.out.println("Luas Lingkaran : " + Luas(Double.parseDouble(args[1])));
                break;
            case 3:
                System.out.println("Luas Segitiga : " + Luas(Integer.parseInt(args[1]), Integer.parseInt(args[2])));
                break;
            case 4:
                System.out.println("Umur : " + Umur(2020, Integer.parseInt(args[3])));
                break;
            default:
                System.out.println("Wrong Arguments!");
                break;
        }
    }

    public static int Luas(int sisi){
        return sisi * sisi;
    }

    public static double Luas(double jari){
        return (22/7) * Math.pow(jari, 2);
    }

    public static float Luas(int alas, int tinggi){
        return (alas * tinggi) / 2;
    }

    public static int Umur(int CurrentYear, int BirthYear){
        return CurrentYear - BirthYear;
    }
}
