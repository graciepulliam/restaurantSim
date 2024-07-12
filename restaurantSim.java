import java.util.concurrent.Semaphore;
import java.util.Random;

public class restaurantSim {

    //initialize each line counter to 0
    static int line01Len = 0;
    static int line02Len = 0;
    static int line03Len = 0;


    static Semaphore doorOne = new Semaphore(1);
    static Semaphore doorTwo = new Semaphore(1);


    static Semaphore tableOne = new Semaphore(4);
    static Semaphore tableTwo = new Semaphore(4);
    static Semaphore tableThree = new Semaphore(4);


    static Semaphore callWaiter01 = new Semaphore(0);
    static Semaphore callWaiter02 = new Semaphore(0);
    static Semaphore callWaiter03 = new Semaphore(0);

    static Semaphore requestOrder01 = new Semaphore(0);
    static Semaphore requestOrder02 = new Semaphore(0);
    static Semaphore requestOrder03 = new Semaphore(0);

    static Semaphore capacity = new Semaphore(10);

    static Semaphore kitchen = new Semaphore(1);
    static Semaphore register = new Semaphore(1);

    static int order01;
    static int order02;
    static int order03;


    static Semaphore wait01 = new Semaphore(0);
    static Semaphore wait02 = new Semaphore(0);
    static Semaphore wait03 = new Semaphore(0);

    static Semaphore waitForCustomer1 = new Semaphore(0);
    static Semaphore waitForCustomer2 = new Semaphore(0);
    static Semaphore waitForCustomer3 = new Semaphore(0);

    static int table1Count =0;
    static int table2Count =0;
    static int table3Count =0;

    static int totalCount =0;

    static int wrappingUp = 0;

    static int enter1 = 0;
    static int enter2 = 0;
    static int enter3 = 0;
    static int exit1 = 0;
    static int exit2 = 0;
    static int exit3 = 0;

    static Random rand = new Random();


    public static class randomChoices {

        int tableChoice01 = rand.nextInt(4 - 1) + 1;
        ;


        int tableChoice02;

        //create 50/50 chance that customer has a backup table
        //choice will also be used for which door to pick
        int choice = rand.nextInt(3 - 1) + 1;
        ;

        public int getTableChoice01() {
            return this.tableChoice01;
        }

        public int getChoice() {
            return choice;
        }

        public int getTableChoice02() {

            //if choice = 2 then we pick a second option
            if (choice == 2) {
                this.tableChoice02 = rand.nextInt(4 - 1) + 1;
                //if the randomly chosen number is equal to the first choice, choose again
                while (tableChoice02 == tableChoice01) {
                    this.tableChoice02 = rand.nextInt(4 - 1) + 1;
                }
            }
            //if no second option, then we will set it equal to zero representing no table
            else {
                this.tableChoice02 = 0;
            }
            return this.tableChoice02;
        }


    }


    static public class Waiter extends Thread {

        int id; //store the id of the customer;
        int order;




        boolean customerWaiting = true;

        int wait;



        Waiter(int id) {
            this.id = id;
        }



        public void run(){
            try{

                System.out.println("Waiter " + Integer.toString(id) + " chooses table " + Integer.toString(id)+" to serve.");
                sleep(100);


                    if (id == 1) {
                        while(customerWaiting==true) {


                            //wait for a customer to call for the waiter
                            waitForCustomer1.acquire();
                            System.out.println("Waiter " + Integer.toString(id) + " approaches table " + Integer.toString(id) + " and informs the customer he is ready to take the order.");
                            //signal the customer that the waiter is here to take order
                            callWaiter01.release();

                            //request the customer's order
                            requestOrder01.acquire();

                            order = order01;
                            System.out.println("Waiter " + Integer.toString(id) + " takes Customer " + Integer.toString(order) + "'s order.");

                            kitchen.acquire();
                            wait = rand.nextInt(500 - 100) + 100;
                            sleep(wait);
                            System.out.println("Waiter " + Integer.toString(id) + " enters the kitchen.");
                            kitchen.release();
                            System.out.println("Waiter " + Integer.toString(id) + " waits outside of the kitchen.");
                            wait = rand.nextInt(1000 - 300) + 300;
                            sleep(wait);
                            kitchen.acquire();
                            wait = rand.nextInt(500 - 100) + 100;
                            sleep(wait);
                            System.out.println("Waiter " + Integer.toString(id) + " enters the kitchen to get the order.");

                            kitchen.release();
                            System.out.println("Waiter " + Integer.toString(id) + " brings Customer " + Integer.toString(order) + " the order.");
                            wait01.release();

                            sleep(1000);
                            if(enter1==exit1){
                                customerWaiting = false;
                            }

                        }


                    }

                    else if( id == 2){

                        while(customerWaiting==true) {

                            waitForCustomer2.acquire();
                            System.out.println("Waiter " + Integer.toString(id) + " approaches table " + Integer.toString(id) + " and informs the customer he is ready to take the order.");

                            callWaiter02.release();

                            requestOrder02.acquire();
                            order = order02;
                            System.out.println("Waiter " + Integer.toString(id) + " takes Customer " + Integer.toString(order) + "'s order.");

                            kitchen.acquire();
                            wait = rand.nextInt(500 - 100) + 100;
                            sleep(wait);
                            System.out.println("Waiter " + Integer.toString(id) + " enters the kitchen.");
                            kitchen.release();
                            System.out.println("Waiter " + Integer.toString(id) + " waits outside of the kitchen.");
                            wait = rand.nextInt(1000 - 300) + 300;
                            sleep(wait);
                            kitchen.acquire();
                            wait = rand.nextInt(500 - 100) + 100;
                            sleep(wait);
                            System.out.println("Waiter " + Integer.toString(id) + " enters the kitchen to get the order.");

                            kitchen.release();
                            System.out.println("Waiter " + Integer.toString(id) + " brings Customer " + Integer.toString(order) + " the order.");
                            wait02.release();

                            sleep(1000);
                            if(enter2==exit2){
                                customerWaiting = false;
                            }

                        }


                    }
                    else {
                        while(customerWaiting==true) {

                            waitForCustomer3.acquire();
                            System.out.println("Waiter " + Integer.toString(id) + " approaches table " + Integer.toString(id) + " and informs the customer he is ready to take the order.");

                            callWaiter03.release();

                            requestOrder03.acquire();
                            //customerOrder03.acquire();
                            order = order03;
                            System.out.println("Waiter " + Integer.toString(id) + " takes Customer " + Integer.toString(order) + "'s order.");
                            kitchen.acquire();
                            wait = rand.nextInt(500 - 100) + 100;
                            sleep(wait);
                            System.out.println("Waiter " + Integer.toString(id) + " enters the kitchen.");
                            kitchen.release();
                            System.out.println("Waiter " + Integer.toString(id) + " waits outside of the kitchen.");
                            wait = rand.nextInt(1000 - 300) + 300;
                            sleep(wait);
                            kitchen.acquire();
                            wait = rand.nextInt(500 - 100) + 100;
                            sleep(wait);
                            System.out.println("Waiter " + Integer.toString(id) + " enters the kitchen to get the order.");

                            kitchen.release();
                            System.out.println("Waiter " + Integer.toString(id) + " brings Customer " + Integer.toString(order) + " the order.");
                            wait03.release();

                            sleep(1000);

                            if(enter3==exit3){
                                customerWaiting=false;
                            }
                        }


                    }




                wrappingUp ++;
                while(wrappingUp != 3){
                    sleep(100);
                }


                System.out.println("Waiter " + Integer.toString(id) + " cleans table " + Integer.toString(id)+" and exits.");


            }
            catch (Exception e) {
                System.err.println("Error in Thread " + id + ": " + e);
            }
        }




    }

    static public class Customer extends Thread {

        int id; //store the id of the customer
        int eat; //time it takes for customer to eat


        Customer(int id) {
            this.id = id;
        }





        public void run() {
            //boolean strat;
            try {

                randomChoices chooseTable = new randomChoices();
                randomChoices chooseDoor = new randomChoices();

                int choice1 = chooseTable.getTableChoice01();
                int choice2 = chooseTable.getTableChoice02();
                int finalTable = 0;

                //print the customers first and second choice of table (if the customer has a backup choice)
                System.out.println("Customer " + Integer.toString(id) + " wants to sit at table " + choice1+" .");
                if(choice2 == 0){
                    System.out.println("Customer " + Integer.toString(id) + " did not pick a backup table.");
                }else{
                    System.out.println("Customer " + Integer.toString(id) + " chooses table " + choice2+" as a backup.");
                }

                //randomly pick whether to enter door one or door two and aquire that door
                if (chooseDoor.getChoice() == 1) {
                    doorOne.acquire();
                    System.out.println("Customer " + Integer.toString(id) + " entering door 1.");
                } else {
                    doorTwo.acquire();
                    System.out.println("Customer " + Integer.toString(id) + " entering door 2.");
                }

                //choose which line to get into
                //if the first choice has a long line and there is a backup choice then get in that line
                if (choice1 == 1) {
                    if (line01Len < 7 || choice2 == 0) {
                        finalTable = 1;
                    } else if (choice2 == 2) {
                        finalTable = 2;
                    } else {
                        finalTable = 3;
                    }
                } else if (choice1 == 2) {
                    if (line02Len < 7 || choice2 == 0) {
                        finalTable = 2;
                    } else if (choice2 == 1) {
                        finalTable = 1;
                    } else {
                        finalTable = 3;
                    }
                } else if (choice1 == 3) {
                    if (line03Len < 7 || choice2 == 0) {
                        finalTable = 3;
                    } else if (choice2 == 1) {
                        finalTable = 1;
                    } else {
                        finalTable = 2;
                    }
                }

                //print out which line the customer is in
                System.out.println("Customer " + Integer.toString(id) + " gets in line "+ finalTable + " .");

                //the doorway is now empty so release it for the next customer
                if (chooseDoor.getChoice() == 1) {
                    doorOne.release();

                } else {
                    doorTwo.release();
                }

                sleep(100);

                //acquire the table the customer is waiting in line for
                if(finalTable == 1){
                    enter1++;
                    tableOne.acquire(1);
                    //print out that the customer has sat down
                    System.out.println("Customer " + Integer.toString(id) + " sits at table " + finalTable+ " and calls the waiter.");
                    waitForCustomer1.release();
                    callWaiter01.acquire();

                    //place order
                    System.out.println("Customer "+Integer.toString(id)+" gives the order to Waiter 1");
                    order01 = id;
                    requestOrder01.release();
                    wait01.acquire();
                    System.out.println("Customer " + Integer.toString(id) +" eats.");
                    eat = rand.nextInt(1000-200) + 200;
                    sleep(eat);
                    System.out.println("Customer " + Integer.toString(id) +" leaves the table.");
                    tableOne.release();
                    table1Count--;
                    register.acquire();
                    System.out.println("Customer " + Integer.toString(id) +" pays the bill.");
                    register.release();
                    System.out.println("Customer " + Integer.toString(id) +" exits.");
                    exit1++;

                }
                else if(finalTable ==2) {
                    enter2++;
                    tableTwo.acquire(1);
                    //print out that the customer has sat down
                    System.out.println("Customer " + Integer.toString(id) + " sits at table " + finalTable+ " and calls the waiter.");
                    waitForCustomer2.release();
                    callWaiter02.acquire();

                    //place order
                    //customerOrder02.release();
                    System.out.println("Customer "+Integer.toString(id)+" gives the order to Waiter 2");

                    order02 = id;
                    requestOrder02.release();
                    wait02.acquire();
                    System.out.println("Customer " + Integer.toString(id) +" eats.");
                    eat = rand.nextInt(1000-200) + 200;
                    sleep(eat);
                    System.out.println("Customer " + Integer.toString(id) +" leaves the table.");
                    tableTwo.release();
                    table2Count--;
                    register.acquire();
                    System.out.println("Customer " + Integer.toString(id) +" pays the bill.");
                    register.release();
                    System.out.println("Customer " + Integer.toString(id) +" exits.");
                    exit2++;

                }
                else{
                    enter3++;
                    tableThree.acquire(1);
                    //print out that the customer has sat down
                    System.out.println("Customer " + Integer.toString(id) + " sits at table " + finalTable+ " and calls the waiter.");
                    waitForCustomer3.release();
                    callWaiter03.acquire();

                    //place order
                    //customerOrder02.release();
                    System.out.println("Customer "+Integer.toString(id)+" gives the order to Waiter 2");
                    order03 = id;
                    requestOrder03.release();
                    wait03.acquire();
                    System.out.println("Customer " + Integer.toString(id) +" eats.");
                    eat = rand.nextInt(1000-200) + 200;
                    sleep(eat);
                    System.out.println("Customer " + Integer.toString(id) +" leaves the table.");
                    tableThree.release();
                    table3Count--;
                    register.acquire();
                    System.out.println("Customer " + Integer.toString(id) +" pays the bill.");
                    register.release();
                    System.out.println("Customer " + Integer.toString(id) +" exits.");
                    exit3++;

                }



                sleep(100);

            } catch (Exception e) {
                System.err.println("Error in Thread " + id + ": " + e);
            }


        }
    }
        public static void main(String[] args) {

            Waiter[] waiterThreads = new Waiter[4];
            Customer[] customerThreads = new Customer[41];

            for (int i = 1; i < 4; i++) {
                // Create instances of your custom thread class
                waiterThreads[i] = new Waiter(i);
                // Create and run the thread
                waiterThreads[i].start();
            }


            for (int i = 1; i < 11; i++) {
                // Create instances of your custom thread class
                customerThreads[i] = new Customer(i);
                // Create and run the thread
                customerThreads[i].start();
            }


        }
    }


