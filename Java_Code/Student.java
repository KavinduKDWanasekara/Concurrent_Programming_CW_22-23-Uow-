package com.company;

import java.util.Random;

public class Student extends Thread{
    private final Printer printer;
    private int noOfPages =0 ;
    private int noOfDocuments = 0 ;
    public Student(ThreadGroup group, String threadName, Printer printer) {
        super(group, threadName);
        this.printer = printer;
    }

    public int RandomSleepTimeGenerator(){

        Random ran=new Random();
        return ran.nextInt(2000 - 1000 + 1) + 1000;
    }

    @Override
    public void run() {
        for (int documentNumber = 1; documentNumber < 6; documentNumber++){

            Random randomNumber = new Random();
            int randomNumberOfPages = randomNumber.nextInt(25 - 10 + 1) + 10;

            Document doc = new Document(this.getName(), "Document : ", randomNumberOfPages, documentNumber );

            this.printer.printDocument(doc);
            noOfPages = noOfPages + randomNumberOfPages;
            noOfDocuments = noOfDocuments + 1;

            try {
                sleep(RandomSleepTimeGenerator());
            } catch (InterruptedException e) {
                System.out.println(e.toString());
            }
        }
    }

    public String toString(){
        return  ServicePrinter.ANSI_WHITE +
                "Student "                +
                ServicePrinter.ANSI_RESET +
                ServicePrinter.ANSI_RED   +
                this.getName()            +
                ServicePrinter.ANSI_RESET +
                ServicePrinter.ANSI_WHITE +
                " printed "               +
                ServicePrinter.ANSI_RED   +
                noOfDocuments             +
                ServicePrinter.ANSI_RESET +
                ServicePrinter.ANSI_WHITE +
                " documents "             +
                ServicePrinter.ANSI_RESET +
                ServicePrinter.ANSI_WHITE +
                "with "                   +
                ServicePrinter.ANSI_RESET +
                ServicePrinter.ANSI_RED   +
                noOfPages                 +
                ServicePrinter.ANSI_RESET +
                ServicePrinter.ANSI_WHITE +
                " pages."                 +
                ServicePrinter.ANSI_RESET;
    }

}
