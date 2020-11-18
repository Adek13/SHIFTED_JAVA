public class MyDate {
	private int day = 1;
	private int month = 1;
	private int year = 2000;
	public MyDate(int day, int month, int year) {
		this.day = day;
		this.month = month;
		this.year = year;
	}
	public MyDate(MyDate date) {
		this.day = date.day;
		this.month = date.month;
		this.year = date.year;
    }
    public MyDate addDays(int moreDays) {
        MyDate newDate = new MyDate(this);
        newDate.day = newDate.day + moreDays;
        // Not Yet Implemented: wrap around code...
        return newDate;
    }
    public void setDay(int days){
        this.day = days;
    }
    public MyDate TambahBulan(int months) {
        MyDate newMonth = new MyDate(this);
        newMonth.month = newMonth.month + months;
        return newMonth;
    }
    public MyDate KurangBulan(int months) {
        MyDate newMonth = new MyDate(this);
        newMonth.month = newMonth.month - months;
        return newMonth;
    }
    public MyDate TambahTahun(int year) {
        MyDate newYear = new MyDate(this);
        newYear.year = newYear.year + year;
        return newYear;
    }
    public MyDate KurangHari(int day) {
        MyDate newDate = new MyDate(this);
        newDate.day = newDate.day - day;
        return newDate;
    }
    public String toString() {
        String retString = "" + day + "-" + month + "-" + year;
        return retString;
    }
}
        
                    
    