public class ThreadPool implements Runnable{
    int Str;
    public void run() {  
        System.out.println(Thread.currentThread().getName()+" (Start) message = "+this.Str);  
        processmessage();//call processmessage method that sleeps the thread for 2 seconds  
        System.out.println(Thread.currentThread().getName()+" (End)");//prints thread name  
    }  
    private void processmessage() {  
        try {  Thread.sleep(1000);  } catch (InterruptedException e) { e.printStackTrace(); }  
    }  
    ThreadPool(int string){
        this.Str = string;
    }
}
