package com.company;

public class PrintingSystem {

    public static void main(String[] args) {
        ThreadGroup studentGroup = new ThreadGroup("Student Thread Group");
        ThreadGroup technicianGroup = new ThreadGroup("Technician Thread Group");

        LaserPrinter laserPrinter = new LaserPrinter("lp-N1-01", studentGroup);

        Student student1 = new Student(studentGroup, "A", laserPrinter);
        Student student2 = new Student(studentGroup, "B", laserPrinter);
        Student student3 = new Student(studentGroup, "C", laserPrinter);
        Student student4 = new Student(studentGroup, "D", laserPrinter);

        PaperTechnician paperTechnician = new PaperTechnician(technicianGroup, "paper technician", laserPrinter);
        TonerTechnician tonerTechnician = new TonerTechnician(technicianGroup, "Toner Technician", laserPrinter);

        student1.start();
        student2.start();
        student3.start();
        student4.start();
        paperTechnician.start();
        tonerTechnician.start();

        try {
            student1.join();
            student2.join();
            student3.join();
            student4.join();
            paperTechnician.join();
            tonerTechnician.join();
        } catch (InterruptedException ex) {
            System.out.println(ex.toString());
        }

        System.out.println(ServicePrinter.ANSI_PURPLE + "End of the processes . . ." +ServicePrinter.ANSI_RESET);

        System.out.print(ServicePrinter.ANSI_WHITE +"----------------------------------------------------------------------------------------------------------------------------------- \n" +
                        "                                                        PRINTER SUMMARY                                                            \n" +
                        "----------------------------------------------------------------------------------------------------------------------------------- \n");
        System.out.println(laserPrinter.toString());
        System.out.println(student1.toString());
        System.out.println(student2.toString());
        System.out.println(student3.toString());
        System.out.println(student4.toString());
        System.out.print(ServicePrinter.ANSI_WHITE +"----------------------------------------------------------------------------------------------------------------------------------- ");
    }
}
