public class TestMyDate {
    public static void main(String[] args) {
        MyDate my_birth = new MyDate(22, 7, 1964);
        MyDate the_next_week = my_birth.addDays(7);
        MyDate next_month = the_next_week.TambahBulan(2);
        MyDate month_backward = next_month.KurangBulan(1);
        MyDate next_year = month_backward.TambahTahun(1);
        MyDate day_backward = next_year.KurangHari(5);
    
        System.out.println(the_next_week);
        System.out.println(next_month);
        System.out.println(month_backward);
        System.out.println(next_year);
        System.out.println(day_backward);
    }
}