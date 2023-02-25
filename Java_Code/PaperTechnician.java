package com.company;

import java.util.Random;

public class PaperTechnician extends Thread {

    private final ServicePrinter printer ;
    private int noOfPaperPacks ;

    public PaperTechnician(ThreadGroup group, String name, ServicePrinter printer) {
        super(group, name);
        this.printer = printer;
    }

    @Override
    public void run() {
        Random random = new Random();
        int numberOfRefills = 3;

        for (int i = 1; i <= numberOfRefills; i++) {

            printer.refillPaper();
            noOfPaperPacks = noOfPaperPacks + 1;

            int MINIMUM_SLEEPING_TIME = 1000;
            int MAXIMUM_SLEEPING_TIME = 5000;
            int sleepingTime = MINIMUM_SLEEPING_TIME + random.nextInt(MAXIMUM_SLEEPING_TIME - MINIMUM_SLEEPING_TIME);

            try {
                Thread.sleep(sleepingTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.printf("Paper Technician %s was interrupted during sleeping time " + "after refilling paper pack no. %d.\n", sleepingTime, i);
            }
        }

        System.out.printf(
                ServicePrinter.ANSI_CYAN +
                "Paper Technician finished attempts to refill printer with paper packs.\n" +
                ServicePrinter.ANSI_RESET
        );
    }
}
