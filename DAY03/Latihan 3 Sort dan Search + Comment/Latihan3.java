import java.util.*;
public class Latihan3 {
    
    public static void main(String[] args) {
        ArrayList<Integer> data = InputData(21, 23, 26, 12, 15); // Inisialisasi data input menggunajan method InputData()
        ArrayList<Integer> dataSortir = SortData(data); // Inisialisasi data hasil sortir menggunakan method SortData() yang inputnya dari data
        String Search = SearchData(dataSortir, 23);// Inisialisasi data hasil pencarian data 23 menggunakan method SearchData() yang inputnya dari dataSortir

        // <-- Print Hasil -->
        System.out.println("Data Input : " + data);
        System.out.println("Data setelah di sortir : " + dataSortir);
        System.out.println("Data Ditemukan : " + Search);
        // <-- ! Print Hasil -->
    }

    //Method untuk mengembalikan arraylist dengan tipe integer dari parameter data yang dimasukkan
    public static ArrayList<Integer> InputData(int num1, int num2, int num3, int num4, int num5){
        ArrayList <Integer> data = new ArrayList <Integer>(); //Inisialisasi object baru berupa arraylist integer
        data.add(num1);// Menambahkan data ke dalam array data
        data.add(num2);// Menambahkan data ke dalam array data
        data.add(num3);// Menambahkan data ke dalam array data
        data.add(num4);// Menambahkan data ke dalam array data
        data.add(num5);// Menambahkan data ke dalam array data
        return(data);//mengembalikan nilai arraylist data
    }


    //Method untuk mengembalikan arraylist yang telah disortir dari yang terkecil ke terbesar menggunakan Algoritma Bubble Sort
    public static ArrayList<Integer> SortData(ArrayList<Integer> arr){
        for (int i = 0; i < arr.size() - 1; i++) { // perulangan berdasarkan size atau banyak data dari input arraylist
            for (int j = 0; j < arr.size() - 1 - i; j++) { // perulangan untuk pengecekan element array ke kanan
                if (arr.get(j + 1) < arr.get(j)) {// pengecekan nilai data, jika data array pada posisi saat ini lebih besar dari data di kanannya maka eksekusi statement
                    int temp = arr.get(j); // variabel sementara untuk menampung data array pada posisi saat ini yang akan di pindahkan ke data array di kanannya
                    arr.set(j, arr.get(j+1)); // set nilai array saat ini dengan nilai array di kanannya
                    arr.set(j + 1, temp); // set nilai array di kanannya dengan variabel sementara
                }
            }
        }
        return(arr); // mengembalikan arraylist yang telah disortir  
    }


    //Method untuk menemukan data dan mengembalikan string posisi index data berada
    public static String SearchData(ArrayList<Integer> arr, int target ){
        int left = 0; // inisialisasi nilai untuk batas kiri
        int middle; // deklarasi variabel middle
        int right = arr.size() - 1; // deklarasi untuk menentukan batas kanan array berdasarkan size atau ukuran array
        String Hasil = target + " Tidak Ditemukan"; // inisialisasi hasil jika tidak ditemukan
        while (left <= right) { // perulangan selama batas kiri kecil sama dari batas kanan (range pencarian)
            middle = (left + right) / 2; // inisialisasi nilai tengah dari range pencarian
            if (arr.get(middle) == target) { // jika array dengan index nilai tengah adalah target maka set nilai hasil dan hentikan pencarian
                Hasil = target + " found at index " + middle;
                break;
            } else if (arr.get(middle) < target) {// jika array dengan index nilai tengah lebih kecil dari target maka nilai tengah + 1 dijadikan batas kiri untuk pencarian selanjutnya
                left = middle + 1;
            } else if (arr.get(middle) > target) {// jika array dengan index nilai tengah lebih besar dari target maka nilai tengah - 1 dijadikan batas kanan untuk pencarian selanjutnya
                right = middle - 1;
            }
        }
        return Hasil;// Mengembalikan nilai hasil
    }
}
