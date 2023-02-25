package com.company;

public class LaserPrinter implements ServicePrinter {

    private int currentPapers;
    private int currentToner;
    private int printedDocs;
    private final String printerName;
    private final ThreadGroup students;
    private int noOfCartridges = 0;
    private int noOfPaperPacks = 0;

    public LaserPrinter(String printerName, ThreadGroup students) {
        this.printerName = printerName;
        this.currentPapers = ServicePrinter.Full_Paper_Tray;
        this.currentToner = ServicePrinter.Full_Toner_Level;
        this.printedDocs = 0;
        this.students=students;
    }

    @Override
    public synchronized void replaceTonerCartridge() {
        while (this.currentToner > LaserPrinter.Minimum_Toner_Level) {
            try {
                if (this.checkStudentAvailability()) {
                    System.out.println(
                            ServicePrinter.ANSI_YELLOW +
                            "Still not reached to minimum toner level " +
                            ServicePrinter.ANSI_RESET +
                            ServicePrinter.ANSI_WHITE +
                            " [ Current toner level : "+
                            ServicePrinter.ANSI_RED +
                            this.currentToner+
                            ServicePrinter.ANSI_RESET +
                            ServicePrinter.ANSI_WHITE +
                            " ]" +
                            ServicePrinter.ANSI_RESET
                    );
                    wait(5000);
                } else {
                    System.out.println(
                            ServicePrinter.ANSI_CYAN +
                            ". . . "+
                            ServicePrinter.ANSI_RESET
                    );
                    break;
                }

            } catch (InterruptedException ex) {
               System.out.println("Toner Technician was interrupted during sleeping time.");
            }
        }

        if (this.currentToner < LaserPrinter.Minimum_Toner_Level) {
            System.out.println(
                    ServicePrinter.ANSI_RED +
                    "Alert ! ! !  -->  Reached to minimum toner level"+
                    ServicePrinter.ANSI_RESET
            );
            System.out.println(
                    ServicePrinter.ANSI_BLUE +
                    "Refilling . . . . "+
                    ServicePrinter.ANSI_RESET
            );

            this.currentToner = LaserPrinter.Full_Toner_Level;
            noOfCartridges = noOfCartridges + 1;

            System.out.println(
                    ServicePrinter.ANSI_BLUE +
                    "Toner Cartridge Replaced Successfully !"+
                    ServicePrinter.ANSI_RESET
            );
        }
        notifyAll();

    }

    @Override
    public synchronized void refillPaper() {

        while (this.currentPapers + LaserPrinter.SheetsPerPack > LaserPrinter.Full_Paper_Tray) {
            try {
                if (this.checkStudentAvailability()) {
                    System.out.println(
                            "Enough sheets for the job [ Current sheets count : " +
                            this.currentPapers                                    +
                            " ]"
                    );

                    wait(5000);

                } else {
                    System.out.println(
                            ServicePrinter.ANSI_CYAN +
                            ". . . " +
                            ServicePrinter.ANSI_RESET
                    );
                    break;
                }


            } catch (InterruptedException ex) {
                System.out.println("Paper Technician was interrupted during sleeping time.");
            }
        }

        if (this.currentPapers + LaserPrinter.SheetsPerPack < LaserPrinter.Full_Paper_Tray) {
            System.out.println(
                    ServicePrinter.ANSI_RED                            +
                    "Alert ! ! !  -->  Not enough papers in the tray " +
                    ServicePrinter.ANSI_RESET
            );
            System.out.println(
                    ServicePrinter.ANSI_BLUE +
                    "Refilling . . . . "+
                    ServicePrinter.ANSI_RESET
            );

            this.currentPapers =  this.currentPapers + LaserPrinter.SheetsPerPack;
            noOfPaperPacks = noOfPaperPacks + 1;

            System.out.println(
                    ServicePrinter.ANSI_BLUE +
                    "Papers refilled  Successfully ! "+
                    ServicePrinter.ANSI_RESET
            );

        }
        notifyAll();
    }

    @Override
    public synchronized void printDocument(Document document) {
        while (this.currentPapers < document.getNumberOfPages() || this.currentToner < document.getNumberOfPages()) {
            try {
                System.out.println("Printing machine is free to use.");
                wait();
            } catch (InterruptedException ex) {
                System.out.println("Printing machine got interrupted.");
            }
        }
        if (this.currentPapers > document.getNumberOfPages() && this.currentToner > document.getNumberOfPages()) {
            currentPapers -= document.getNumberOfPages();
            currentToner -= document.getNumberOfPages();
            printedDocs += 1;
            System.out.println(
                    ServicePrinter.ANSI_WHITE +
                    "-----------------------------------------------------------------------------------------------------------------------------------"+
                    ServicePrinter.ANSI_RESET
            );
            System.out.println(ServicePrinter.ANSI_GREEN +
                    " -- > Student " +
                    document.getStudentName() +
                    " printed the document number " +
                    document.getDocumentNumber() +
                    " including "+ document.getNumberOfPages() +
                    " pages"
            );
            System.out.println(ServicePrinter.ANSI_WHITE +
                    "Available sheets count : " +
                    ServicePrinter.ANSI_RESET +
                    ServicePrinter.ANSI_RED +
                    currentPapers +
                    ServicePrinter.ANSI_RESET  +
                    "\t" +
                    ServicePrinter.ANSI_WHITE +
                    "Current toner level : " +
                    ServicePrinter.ANSI_RED +
                    currentToner +
                    ServicePrinter.ANSI_RESET
            );
            System.out.println(ServicePrinter.ANSI_WHITE +
                    "-----------------------------------------------------------------------------------------------------------------------------------"+
                    ServicePrinter.ANSI_RESET
            );
        }
        notifyAll();
    }

    @Override
    public synchronized String toString() {
        return  ServicePrinter.ANSI_WHITE +
                "Available sheets count : " +
                ServicePrinter.ANSI_RESET +
                ServicePrinter.ANSI_RED   +
                this.currentPapers        +
                ServicePrinter.ANSI_RESET +
                "\t"                      +
                ServicePrinter.ANSI_WHITE +
                " Current toner level : " +
                ServicePrinter.ANSI_RED   +
                this.currentToner         +
                ServicePrinter.ANSI_RESET +
                "\t"                      +
                ServicePrinter.ANSI_WHITE +
                " Number of printed documents : " +
                ServicePrinter.ANSI_RED   +
                printedDocs               +
                ServicePrinter.ANSI_RESET +
                "\t"                      +
                ServicePrinter.ANSI_WHITE +
                "Printer ID : "           +
                ServicePrinter.ANSI_RED   +
                this.printerName          +
                ServicePrinter.ANSI_RESET +
                "\n"                      +
                ServicePrinter.ANSI_WHITE +
                "Paper Technician replaced "                +
                ServicePrinter.ANSI_RESET +
                ServicePrinter.ANSI_RED   +
                noOfPaperPacks            +
                ServicePrinter.ANSI_RESET +
                ServicePrinter.ANSI_WHITE +
                " Cartridges "            +
                ServicePrinter.ANSI_RESET +
                "\n"                      +
                ServicePrinter.ANSI_WHITE +
                "Toner Technician replaced "                +
                ServicePrinter.ANSI_RESET +
                ServicePrinter.ANSI_RED   +
                noOfCartridges            +
                ServicePrinter.ANSI_RESET +
                ServicePrinter.ANSI_WHITE +
                " Cartridges "            +
                ServicePrinter.ANSI_RESET ;
    }

    private boolean checkStudentAvailability() {
        if(students.activeCount()>0){
            return true;
        }
        else{
            return false;
        }

    }


}