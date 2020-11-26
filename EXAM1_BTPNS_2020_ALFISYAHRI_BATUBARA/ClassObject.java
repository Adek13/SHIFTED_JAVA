class Laptop {

    String sMerek, sVga;
    int iRam = 0;
    
    Laptop(String merek, int ram, String vga) {
        this.sMerek = merek;
        this.iRam = ram;
        this.sVga = vga;
    }
    void setMerek (String newValue){
        this.sMerek = newValue;
    }
    void setRam (int newValue){
        this.iRam = newValue;
    }
    void setVga (String newValue){
        this.sVga = newValue;
    }
    void print(){
        System.out.println("Merek : "+this.sMerek+" Ram : "+this.iRam+" VGA : "+this.sVga);
    }
}

public class ClassObject {
    public static void main(String[] args) {
        Laptop laptop1 = new Laptop("HP", 4, "Radeon R5");
        Laptop laptop2 = new Laptop("Acer", 8, "GTX 1650");
        Laptop laptop3 = new Laptop("ASUS", 4, "GTX 1050");
        laptop1.print();
        laptop2.print();
        laptop3.print();
    }
}