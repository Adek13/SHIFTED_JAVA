public class ArrayMulti {
    
    public static void main(String[] args) {
        switch (Integer.parseInt(args[0])) {
            case 1:
                System.out.print("Volume Kubus : " + Volume(Integer.parseInt(args[1])));
                break;
            case 2:
                System.out.print("Volume Kubus : " + Volume(Double.parseDouble(args[1])));
                break;
            case 3:
                Arrays(Integer.parseInt(args[1]));
                break;
            default:
                break;
        }
    }

    public static int Volume(int sisi){
        return sisi * sisi * sisi;
    }

    public static double Volume(Double jari){
        return (22/7) * jari * jari * jari;
    }

    public static void Arrays(int row){
        if(row==2){
            String[][] arrs = {
                {"satu", "dua", "tiga", "empat"},
                {"lima", "enam"}
            };
            for (int i = 0; i < arrs.length; i++) {
                for (int j = 0; j < arrs[i].length; j++) {
                    System.out.print(arrs[i][j] + " ");
                }
                System.out.println("");
            }
        }else if(row==3){
            String[][] arrs = {
                {"satu", "dua", "tiga", "empat"},
                {"lima", "enam"},
                {"tujuh", "delapan", "sembilan"}
            };
            for (int i = 0; i < arrs.length; i++) {
                for (int j = 0; j < arrs[i].length; j++) {
                    System.out.print(arrs[i][j] + " ");
                }
                System.out.println("");
            }
        }
    }
}
