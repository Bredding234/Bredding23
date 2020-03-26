/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads_synchronization;

/**
 *
 * @author Bredding
 */
public class Semaphore {
   private int people;

   public Semaphore(int value) {
      this.people = people;
   }

   public synchronized void acquire() {
      while (people <= 0) {
         try {
            wait();
         }
         catch (InterruptedException e) { }
      }

      people--;
   }

   public synchronized void release() {
      ++people;

      notify();
   }
    
}
