package com.tugas03;

public class PrintToScreen extends Thread{
    String data;
    public void run(){
        System.out.println(data);
    }
    PrintToScreen(String data){
        this.data = data;
    }
}
