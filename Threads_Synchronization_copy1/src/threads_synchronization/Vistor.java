/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads_synchronization;

import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

/**
 *
 * @author Bredding
 */
public class Vistor extends Thread{

    /**
     * @param args the command line arguments
     */
    private StoreLock storeLocks;
private Buffer<Thread> buffer;
private int visNum;
static int times = (int) (Math.random()*((75-50)+1)+50);
     Random ran = new Random();
   public int items = ran.nextInt(6)+1;
    private Semaphore semaphore;


    /*
  public Vistor(){
     Random ran = new Random();
     this.items = ran.nextInt(6)+1;
  }
  */
public Vistor(Semaphore semaphore, Buffer<Thread> buffer) {

    this.semaphore = semaphore;
    this.buffer = buffer;

}
  public void addItem(){
      items++;
  }
  
  public void removeItem(){
      items--;
  }
  
  private void criticalCode()

{
    try {

     sleep(items*Cashier.time);

    } catch(InterruptedException e) {

    }

}
    public int count = 0;

 public void run(){   
       
       
             //System.out.println("The Visitor has checked out of the store");
         

       //System.out.println("Vistors are waiting to go to the cashier.");
       //SleepUtilities.nap(6);   
  for(int i = 1; i < times; i++){
      //Random s = new Random();
     //int range = s.nextInt(25)+ 50;
      
     count+=1;
  /*
     try{   
     Thread.sleep(range);
     semaphore.acquire();
    criticalCode();
    semaphore.release();
    count++;
     } catch (InterruptedException e){
System.out.println("Exception " + e.toString());
     }
   */
     

     //if(count == 1){
     //System.out.println("The number of people or visitors being served in cashier line 1: " + count + " Person");
     //break;
    // } 
     
    }
  
  //System.out.println("Visitor has shopped!");

 }
}

