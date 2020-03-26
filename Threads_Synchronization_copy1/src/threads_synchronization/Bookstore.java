
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads_synchronization;

import java.lang.reflect.Array;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Scanner;
import javax.swing.JFrame;

/**
 *
 * @author Bredding
 */
public class Bookstore {

    //Array for the number of visitors being served in each line
    private static String[] arr = {"Visitors being served in Cashier Line 1: ", "Visitors being served in Cashier Line 2: ", "Visitor being served in Cashier Line 3: ", "Visitors being served in Cashier Line 4: ", "Visitors being served in Cashier Line 5: "};

    private static String[] arr2 = {"Visitors being served in Cashier Line 6: ", "Visitors being served in Cashier Line 7: ", "Visitor being served in Cashier Line 8: ", "Visitors being served in Cashier Line 9: ", "Visitors being served in Cashier Line 10: "};

    //Array for the number of people waiting in each line.
    private static String[] waitArray = {"The number of people waiting in cashier line 1: ", "The number of people waiting in cashier line 2: ", "The number of people waiting in cashier line 3: ", "The number of people waiting in cashier line 4: ", "The number of people waiting in cashier line 5: "};
    private static String[] waitArray2 = {"The number of people waiting in cashier line 6: ", "The number of people waiting in cashier line 7: ", "The number of people waiting in cashier line 8: ", "The number of people waiting in cashier line 9: ", "The number of people waiting in cashier line 10: "};

    private static int visNum[] = new int[50];

    public static void main(String[] args) {
        int count = 0;
        int M = 50; // upper bound for visitors
        int N = 10; // upper bound for cashiers

        Random r = new Random();
        // there are random variables create because I use them for the index position in both arrays
        int randomNumber = r.nextInt(arr.length); // the random amount of visitors being served
        int randomWaitVisitors = randomNumber; // // the random amount of people waiting
        //System.out.println(arr[randomNumber]);
        Cashier_Bounded_Buffer<Thread> server = new Cashier_Bounded_Buffer<Thread>();
        //The title of bookstore
        String title = "University Of Hartford Bookstore";
        // N cashiers
        // M visitors
        Semaphore semaphore = new Semaphore(N);

        Scanner scan = new Scanner(System.in);

        Cashier_Bounded_Buffer<Thread> buffers = new Cashier_Bounded_Buffer();
        System.out.println(title);
        //frame.setTitle("University Of Hartford Bookstore");
        // The user enters an amount for the visitors  
        LinkedList v = new LinkedList<Thread>();
        System.out.println("Enter the amount of visitor that are less than or equal 50 ?");
        M = scan.nextInt();

        while (M > 50) {
            System.out.println("Enter the amount of visitor that are less than 50?");
            M = scan.nextInt();

        }
        // The user enters an amount for the cashiers  
        System.out.println("Enter the amount of cashiers that are less than or equal 10 ? ");
        N = scan.nextInt();
        while (N > 10) {
            System.out.println("Enter the amount of cashiers that are less than or equal 10 ?");
            N = scan.nextInt();
            semaphore = new Semaphore(N);

        }

        System.out.println("The number of people shopping is: " + M); // The current amount of visitors who are shopping

        Vistor p[] = new Vistor[M];

//        Cashier cashiers_people = new Cashier(semaphore, server);
        //Vistor vistors = M;
        //cashiers_people.Visitors();

       
        for (int i = 0; i < N; i++) {
            count = 0;
            //Thread visitor = new Thread(new Vistor());
            //buffers.Customer_Line(visitor);
            //visitor.start();
            //p[i] = new Vistor(semaphore);        
            p[i] = new Vistor(semaphore, buffers);
            count++;
             
        }
        Thread visitor = new Thread(new Vistor(semaphore, server));
           Vistor vistors = new Vistor(semaphore,buffers);
        Thread cashiers = new Thread(new Cashier(M,semaphore, server)); // Threads I created for cashiers
        cashiers.run(); //   This a method call onto the run method in cashiers class
        /*
         The for loop performs an loop through the amount of each visitors that have an random amount of items.
         */
                  for (int i = 1; i<= M; i++){   
         vistors.items = r.nextInt(6)+1;
        visNum[i] = i;
         System.out.println("The number of items that customer " + (visNum[i]) + " has " + vistors.items + " items. ");
         SleepUtilities.nap(6);
      }   
                  
        Run_Store_Cashiers(N, M); // method call run store cashiers, which runs the method based on each case for the cashiers the user enters.

    
    }

    public static int Run_Store_Cashiers(int N, int M) {
        int update = M / N; // update variable is the count that updates the remaining of people in each cashier line after customer checksout.
        int count = 0; // Counting variable for number of people 
        count++; // Counting variable for number of people being served, which is 1.
       /*This if statement run when the lower cashiers is equal to 1, 
         it prints out the remaining of people waiting line 1 and there is an response that all Visitors have been served in Cashier Line 1.*/
        if (N == 1) {
            try {

                //System.out.println("The number of people waiting in cashier line 1: " + (M / N) + " People");
                System.out.println("The number of people waiting: " + (M / N) + " People");
                SleepUtilities.nap(6);
                for (int i = 0; i < M && update > 0; i++) {
                    System.out.println(arr[0] + count);
                    update--;
                    System.out.println(waitArray[0] + update + " People");
                    SleepUtilities.nap(6);

                }
                System.out.print("All visitors have been served and there are no more people waiting in Cashier Line 1!!");

            } catch (Exception e) {
                System.out.println("Error!");
            }
        }
   /*This if statement run when the lower cashiers is equal to 2, 
         it prints out the remaining of people waiting line 1 and 2 and there is an response that all Visitors have been served in Cashier Line 1 and 2.*/
        if (N == 2) {
            try {
                System.out.println("The number of people waiting in cashier line 1: " + (M / N) + " People");
                System.out.println("The number of people waiting in cashier line 2: " + (M / N) + " People");
                SleepUtilities.nap(6);

                for (int i = 0; i < M && update > 0; i++) {
                    System.out.println(arr[0] + count); //visitors being served in line 1
                    update--;
                    System.out.println(waitArray[0] + update + " People"); // Line 1
                    SleepUtilities.nap(6);

                    System.out.println(arr[1] + count); //visitors being served in line 2
                    //update--;
                    SleepUtilities.nap(6);
                    System.out.println(waitArray[1] + update + " People"); // Line 2
                    SleepUtilities.nap(6);

                }
                System.out.println("All visitors have been served and there are no more people waiting in Cashier Line 1 and 2!!");
            } catch (Exception e) {
                System.out.println("Error!");
            }
            //System.out.print("All visitors have been served and there are no more people waiting in Cashier Line 1 and 2!!"); 
        }
   /*This if statement run when the lower cashiers is equal to 3, 
         it prints out the remaining of people waiting line 1, 2,  and 3 and there is an response that all Visitors have been served in Cashier Line 1 ,2, and 3.*/
        if (N == 3) {
            try {
                System.out.println("The number of people waiting in cashier line 1: " + (M / N) + " People");
                System.out.println("The number of people waiting in cashier line 2: " + (M / N) + " People");
                System.out.println("The number of people waiting in cashier line 3: " + (M / N) + " People");
                SleepUtilities.nap(6);

                for (int i = 0; i < M && update > 0; i++) {
                    System.out.println(arr[0] + count);
                    update--;
                    System.out.println(waitArray[0] + update + " People"); // Line 1
                    SleepUtilities.nap(6);

                    System.out.println(arr[1] + count);
                    //update--;
                    System.out.println(waitArray[1] + update + " People"); // Line 2
                    SleepUtilities.nap(6);

                    System.out.println(arr[2] + count);
                    SleepUtilities.nap(6);
                    System.out.println(waitArray[2] + update + " People"); // Line 3

                }
                System.out.print("All visitors have been served and there are no more people waiting in Cashier Line 1, 2, and 3!!");

            } catch (Exception e) {
                System.out.println("Error!");
            }
        }
        /*This if statement run when the lower cashiers is equal to 4, 
         it prints out the remaining of people waiting line 1, 2, 3, and 4 and there is an response that all Visitors have been served in Cashier Line 1, 2, 3, and 4.*/
        if (N == 4 & arr[0] == arr[0] && arr[1] == arr[1] && arr[2] == arr[2] && arr[3] == arr[3]) {
            try {
                System.out.println("The number of people waiting in cashier line 1: " + (M / N) + " People");
                System.out.println("The number of people waiting in cashier line 2: " + (M / N) + " People");
                System.out.println("The number of people waiting in cashier line 3: " + (M / N) + " People");
                System.out.println("The number of people waiting in cashier line 4: " + (M / N) + " People");
                SleepUtilities.nap(6);
                for (int i = 0; i < M && update > 0; i++) {
                    System.out.println(arr[0] + count);
                    update--;
                    System.out.println(waitArray[0] + update + " People"); // Line 1
                    SleepUtilities.nap(6);

                    System.out.println(arr[1] + count);
                    //update--;
                    System.out.println(waitArray[1] + update + " People"); // Line 2
                    SleepUtilities.nap(6);

                    System.out.println(arr[2] + count);
                    //update--; 
                    System.out.println(waitArray[2] + update + " People"); // Line 3
                    SleepUtilities.nap(6);

                    System.out.println(arr[3] + count);

                    System.out.println(waitArray[3] + update + " People"); // Line 4
                    SleepUtilities.nap(6);

                }
                System.out.print("All visitors have been served and there are no more people waiting in Cashier Line 1, 2, 3, and 4!!");
            } catch (Exception e) {
                System.out.println("Error!");
            }
        }
         /*This if statement run when the lower cashiers is equal to 5, 
         it prints out the remaining of people waiting line 1, 2, 3, 4, and 5. There is an response that all Visitors have been served in Cashier Line 1, 2, 3, 4, and 5.*/
        if (N == 5 & arr[0] == arr[0] && arr[1] == arr[1] && arr[2] == arr[2] && arr[3] == arr[3] && arr[4] == arr[4]) {
            try {
                System.out.println("The number of people waiting in cashier line 1: " + (M / N) + " People");
                System.out.println("The number of people waiting in cashier line 2: " + (M / N) + " People");
                System.out.println("The number of people waiting in cashier line 3: " + (M / N) + " People");
                System.out.println("The number of people waiting in cashier line 4: " + (M / N) + " People");
                System.out.println("The number of people waiting in cashier line 5: " + (M / N) + " People");
                SleepUtilities.nap(6);
                for (int i = 0; i < M && update > 0; i++) {

                    System.out.println(arr[0] + count);
                    update--;
                    System.out.println(waitArray[0] + update + " People");// Line 1
                    SleepUtilities.nap(6);

                    System.out.println(arr[1] + count);
                    //update--;
                    System.out.println(waitArray[1] + update + " People"); // Line 2
                    SleepUtilities.nap(6);

                    System.out.println(arr[2] + count);
                    //update--; 
                    System.out.println(waitArray[2] + update + " People"); // Line 3
                    SleepUtilities.nap(6);

                    System.out.println(arr[3] + count);

                    //update--;
                    System.out.println(waitArray[3] + update + " People"); // Line 4
                    SleepUtilities.nap(6);

                    System.out.println(arr[4] + count);
                    //update--;
                    System.out.println(waitArray[4] + update + " People"); // Line 5
                    SleepUtilities.nap(6);

                }
                System.out.print("All visitors have been served and there are no more people waiting in Cashier Line 1, 2, 3, 4, and 5!!");
            } catch (Exception e) {
                System.out.println("Error!");
            }
        }
 /*This if statement run when the lower cashiers is equal to 6, 
         it prints out the remaining of people waiting line 1, 2, 3,4,5, and 6. There is an response that all Visitors have been served in Cashier Line 1, 2, 3, 4, 5, and 6.*/
        if (N == 6) {
            try {
                System.out.println("The number of people waiting in cashier line 1: " + (M / N) + " People");
                System.out.println("The number of people waiting in cashier line 2: " + (M / N) + " People");
                System.out.println("The number of people waiting in cashier line 3: " + (M / N) + " People");
                System.out.println("The number of people waiting in cashier line 4: " + (M / N) + " People");
                System.out.println("The number of people waiting in cashier line 5: " + (M / N) + " People");
                System.out.println("The number of people waiting in cashier line 6: " + (M / N) + " People");
                SleepUtilities.nap(6);
                for (int i = 0; i < M && update > 0; i++) {

                    System.out.println(arr[0] + count);
                    update--;
                    System.out.println(waitArray[0] + update + " People");// Line 1
                    SleepUtilities.nap(6);

                    System.out.println(arr[1] + count);
                    //update--;
                    System.out.println(waitArray[1] + update + " People"); // Line 2
                    SleepUtilities.nap(6);

                    System.out.println(arr[2] + count);
                    //update--; 
                    System.out.println(waitArray[2] + update + " People"); // Line 3
                    SleepUtilities.nap(6);

                    System.out.println(arr[3] + count);

                    //update--;
                    System.out.println(waitArray[3] + update + " People"); // Line 4
                    SleepUtilities.nap(6);

                    System.out.println(arr[4] + count);
                    //update--;
                    System.out.println(waitArray[4] + update + " People"); // Line 5
                    SleepUtilities.nap(6);

                    System.out.println(arr2[0] + count);
                    //update--;
                    System.out.println(waitArray2[0] + update + " People");// Line 6
                    SleepUtilities.nap(6);
                }
                System.out.print("All visitors have been served and there are no more people waiting in Cashier Line 1, 2, 3, 4, 5, and 6!!");
            } catch (Exception e) {
                System.out.println("Error!");
            }
        }
 /*This if statement run when the lower cashiers is equal to 7, 
         it prints out the remaining of people waiting line 1, 2, 3, 4, 5, 6, and 7. There is an response that all Visitors have been served in Cashier Line 1, 2, 3, 4, 5, 6, and 7.*/
        if (N == 7) {
            try {
                System.out.println("The number of people waiting in cashier line 1: " + (M / N) + " People");
                System.out.println("The number of people waiting in cashier line 2: " + (M / N) + " People");
                System.out.println("The number of people waiting in cashier line 3: " + (M / N) + " People");
                System.out.println("The number of people waiting in cashier line 4: " + (M / N) + " People");
                System.out.println("The number of people waiting in cashier line 5: " + (M / N) + " People");
                System.out.println("The number of people waiting in cashier line 6: " + (M / N) + " People");
                System.out.println("The number of people waiting in cashier line 7: " + (M / N) + " People");
                SleepUtilities.nap(6);
                for (int i = 0; i < M && update > 0; i++) {

                    System.out.println(arr[0] + count);
                    update--;
                    System.out.println(waitArray[0] + update + " People");// Line 1
                    SleepUtilities.nap(6);

                    System.out.println(arr[1] + count);
                    //update--;
                    System.out.println(waitArray[1] + update + " People"); // Line 2
                    SleepUtilities.nap(6);

                    System.out.println(arr[2] + count);
                    //update--; 
                    System.out.println(waitArray[2] + update + " People"); // Line 3
                    SleepUtilities.nap(6);

                    System.out.println(arr[3] + count);

                    //update--;
                    System.out.println(waitArray[3] + update + " People"); // Line 4
                    SleepUtilities.nap(6);

                    System.out.println(arr[4] + count);
                    //update--;
                    System.out.println(waitArray[4] + update + " People"); // Line 5
                    SleepUtilities.nap(6);

                    System.out.println(arr2[0] + count);
                    //update--;
                    System.out.println(waitArray2[0] + update + " People");// Line 6
                    SleepUtilities.nap(6);

                    System.out.println(arr2[1] + count);
                    //update--;
                    System.out.println(waitArray2[1] + update + " People"); // Line 7
                    SleepUtilities.nap(6);
                }
                System.out.print("All visitors have been served and there are no more people waiting in Cashier Line 1, 2, 3, 4 ,5 ,6, and 7!!");
            } catch (Exception e) {
                System.out.println("Error!");
            }
        }
 /*This if statement run when the lower cashiers is equal to 8, 
         it prints out the remaining of people waiting line 1, 2, 3, 4, 5, 6, 7, and 8. There is an response that all Visitors have been served in Cashier Line 1, 2, 3, 4, 5, 6, 7, and 8.*/
        if (N == 8) {
            try {
                System.out.println("The number of people waiting in cashier line 1: " + (M / N) + " People");
                System.out.println("The number of people waiting in cashier line 2: " + (M / N) + " People");
                System.out.println("The number of people waiting in cashier line 3: " + (M / N) + " People");
                System.out.println("The number of people waiting in cashier line 4: " + (M / N) + " People");
                System.out.println("The number of people waiting in cashier line 5: " + (M / N) + " People");
                System.out.println("The number of people waiting in cashier line 6: " + (M / N) + " People");
                System.out.println("The number of people waiting in cashier line 7: " + (M / N) + " People");
                System.out.println("The number of people waiting in cashier line 8: " + (M / N) + " People");
                SleepUtilities.nap(6);
                for (int i = 0; i < M && update > 0; i++) {

                    System.out.println(arr[0] + count);
                    update--;
                    System.out.println(waitArray[0] + update + " People");// Line 1
                    SleepUtilities.nap(6);

                    System.out.println(arr[1] + count);
                    //update--;
                    System.out.println(waitArray[1] + update + " People"); // Line 2
                    SleepUtilities.nap(6);

                    System.out.println(arr[2] + count);
                    //update--; 
                    System.out.println(waitArray[2] + update + " People"); // Line 3
                    SleepUtilities.nap(6);

                    System.out.println(arr[3] + count);

                    //update--;
                    System.out.println(waitArray[3] + update + " People"); // Line 4
                    SleepUtilities.nap(6);

                    System.out.println(arr[4] + count);
                    //update--;
                    System.out.println(waitArray[4] + update + " People"); // Line 5
                    SleepUtilities.nap(6);

                    System.out.println(arr2[0] + count);
                    //update--;
                    System.out.println(waitArray2[0] + update + " People");// Line 6
                    SleepUtilities.nap(6);

                    System.out.println(arr2[1] + count);
                    //update--;
                    System.out.println(waitArray2[1] + update + " People"); // Line 7
                    SleepUtilities.nap(6);

                    System.out.println(arr2[2] + count);
                    //update--; 
                    System.out.println(waitArray2[2] + update + " People"); // Line 8
                    SleepUtilities.nap(6);
                }
                System.out.print("All visitors have been served and there are no more people waiting in Cashier Line 1, 2, 3, 4, 5, 6, 7, and 8!!");
            } catch (Exception e) {
                System.out.println("Error!");
            }
        }
 /*This if statement run when the lower cashiers is equal to 9, 
         it prints out the remaining of people waiting line 1, 2, 3, 4, 5, 6, 7, 8, and 9. There is an response that all Visitors have been served in Cashier Line 1, 2, 3, 4, 5, 6, 7, 8, and 9.*/
        if (N == 9) {
            try {
                System.out.println("The number of people waiting in cashier line 1: " + (M / N) + " People");
                System.out.println("The number of people waiting in cashier line 2: " + (M / N) + " People");
                System.out.println("The number of people waiting in cashier line 3: " + (M / N) + " People");
                System.out.println("The number of people waiting in cashier line 4: " + (M / N) + " People");
                System.out.println("The number of people waiting in cashier line 5: " + (M / N) + " People");
                System.out.println("The number of people waiting in cashier line 6: " + (M / N) + " People");
                System.out.println("The number of people waiting in cashier line 7: " + (M / N) + " People");
                System.out.println("The number of people waiting in cashier line 8: " + (M / N) + " People");
                System.out.println("The number of people waiting in cashier line 9: " + (M / N) + " People");

                SleepUtilities.nap(6);
                for (int i = 0; i < M && update > 0; i++) {

                    System.out.println(arr[0] + count);
                    update--;
                    System.out.println(waitArray[0] + update + " People");// Line 1
                    SleepUtilities.nap(6);

                    System.out.println(arr[1] + count);
                    //update--;
                    System.out.println(waitArray[1] + update + " People"); // Line 2
                    SleepUtilities.nap(6);

                    System.out.println(arr[2] + count);
                    //update--; 
                    System.out.println(waitArray[2] + update + " People"); // Line 3
                    SleepUtilities.nap(6);

                    System.out.println(arr[3] + count);

                    //update--;
                    System.out.println(waitArray[3] + update + " People"); // Line 4
                    SleepUtilities.nap(6);

                    System.out.println(arr[4] + count);
                    //update--;
                    System.out.println(waitArray[4] + update + " People"); // Line 5
                    SleepUtilities.nap(6);

                    System.out.println(arr2[0] + count);
                    //update--;
                    System.out.println(waitArray2[0] + update + " People");// Line 6
                    SleepUtilities.nap(6);

                    System.out.println(arr2[1] + count);
                    //update--;
                    System.out.println(waitArray2[1] + update + " People"); // Line 7
                    SleepUtilities.nap(6);

                    System.out.println(arr2[2] + count);
                    //update--; 
                    System.out.println(waitArray2[2] + update + " People"); // Line 8
                    SleepUtilities.nap(6);

                    System.out.println(arr2[3] + count);

                    //update--;
                    System.out.println(waitArray2[3] + update + " People"); // Line 9
                    SleepUtilities.nap(6);
                }
                System.out.print("All visitors have been served and there are no more people waiting in Cashier Line 1, 2, 3, 4, 5, 6, 7, 8, and 9!!");
            } catch (Exception e) {
                System.out.println("Error!");
            }
        }
 /*This if statement run when the lower cashiers is equal to 10, 
         it prints out the remaining of people waiting line 1, 2, 3, 4, 5, 6, 7, 8, 9, and 10. There is an response that all Visitors have been served in Cashier Line 1, 2, 3, 4, 5, 6, 7, 8, 9, and 10.*/
        if (N == 10) {
            try {
                System.out.println("The number of people waiting in cashier line 1: " + (M / N) + " People");
                System.out.println("The number of people waiting in cashier line 2: " + (M / N) + " People");
                System.out.println("The number of people waiting in cashier line 3: " + (M / N) + " People");
                System.out.println("The number of people waiting in cashier line 4: " + (M / N) + " People");
                System.out.println("The number of people waiting in cashier line 5: " + (M / N) + " People");
                System.out.println("The number of people waiting in cashier line 6: " + (M / N) + " People");
                System.out.println("The number of people waiting in cashier line 7: " + (M / N) + " People");
                System.out.println("The number of people waiting in cashier line 8: " + (M / N) + " People");
                System.out.println("The number of people waiting in cashier line 9: " + (M / N) + " People");
                System.out.println("The number of people waiting in cashier line 10: " + (M / N) + " People");
                SleepUtilities.nap(6);
                for (int i = 0; i < M && update > 0; i++) {

                    System.out.println(arr[0] + count);
                    update--;
                    System.out.println(waitArray[0] + update + " People");// Line 1
                    SleepUtilities.nap(6);

                    System.out.println(arr[1] + count);
                    //update--;
                    System.out.println(waitArray[1] + update + " People"); // Line 2
                    SleepUtilities.nap(6);

                    System.out.println(arr[2] + count);
                    //update--; 
                    System.out.println(waitArray[2] + update + " People"); // Line 3
                    SleepUtilities.nap(6);

                    System.out.println(arr[3] + count);

                    //update--;
                    System.out.println(waitArray[3] + update + " People"); // Line 4
                    SleepUtilities.nap(6);

                    System.out.println(arr[4] + count);
                    //update--;
                    System.out.println(waitArray[4] + update + " People"); // Line 5
                    SleepUtilities.nap(6);

                    System.out.println(arr2[0] + count);
                    //update--;
                    System.out.println(waitArray2[0] + update + " People");// Line 6
                    SleepUtilities.nap(6);

                    System.out.println(arr2[1] + count);
                    //update--;
                    System.out.println(waitArray2[1] + update + " People"); // Line 7
                    SleepUtilities.nap(6);

                    System.out.println(arr2[2] + count);
                    //update--; 
                    System.out.println(waitArray2[2] + update + " People"); // Line 8
                    SleepUtilities.nap(6);

                    System.out.println(arr2[3] + count);

                    //update--;
                    System.out.println(waitArray2[3] + update + " People"); // Line 9
                    SleepUtilities.nap(6);

                    System.out.println(arr2[4] + count);
                    //update--;
                    System.out.println(waitArray2[4] + update + " People"); // Line 10
                    SleepUtilities.nap(6);

                }
                System.out.print("All visitors have been served and there are no more people waiting in Cashier Line 1, 2, 3, 4 ,5, 6, 7, 8, 9, and 10!!");
            } catch (Exception e) {
                System.out.println("Error!");
            }
        }
        return count;
    }

}

                      //System.out.println("The number of people being served: " + count);
//count = 0;
//Thread visitor = new Thread(new Vistor());
//buffers.Customer_Line(visitor);
//visitor.start();
//Thread cashiers = new Thread(new Cashier(buffers));
//cashiers = cashiers_people[i];
//buffers = cashiers;
// cashiers.start();
//M.run();  
//System.out.println("The number of people shopping are: " + M);
// System.out.println("The number of people waiting in cashier line: " + (M - N));

