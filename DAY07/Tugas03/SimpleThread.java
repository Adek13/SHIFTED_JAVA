public class SimpleThread extends Thread{
    String Str;
    public void run(){
        try {
            System.out.println(Str);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    SimpleThread(String string){
        this.Str = string;
    }
}
