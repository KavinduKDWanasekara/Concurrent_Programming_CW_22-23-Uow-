package com.company;

import java.util.Random;

public class TonerTechnician extends Thread {
    private ServicePrinter printer;
    private int numberOfRefills = 3;

    public TonerTechnician(ThreadGroup group, String name, ServicePrinter printer) {
        super(group, name);
        this.printer = printer;
    }

    @Override
    public void run() {
        Random random = new Random();

        for (int i =0 ; i <= numberOfRefills; i++){
            printer.replaceTonerCartridge();
            numberOfRefills = numberOfRefills -1;
            int minSleepTime = 1000;
            int maxSleepTime = 5000;
            int sleepingTime = minSleepTime + random.nextInt(maxSleepTime - minSleepTime);

            try {
                Thread.sleep(sleepingTime);
            } catch (InterruptedException e) {
                System.out.printf("Toner Technician %s was interrupted during sleeping time " +
                                "after replacing toner cartridge no. %d.\n",
                        sleepingTime, i);
            }
        }
        System.out.printf(
                ServicePrinter.ANSI_CYAN +
                "Toner Technician finished attempts to replace toner cartridges.\n"+
                ServicePrinter.ANSI_RESET
        );
    }
}
