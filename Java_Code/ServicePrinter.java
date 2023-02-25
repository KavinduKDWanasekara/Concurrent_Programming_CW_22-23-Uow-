package com.company;

public interface ServicePrinter extends Printer {

    public final int Full_Paper_Tray  = 250 ;
    public final int Full_Toner_Level = 500 ;
    public final int Minimum_Toner_Level = 10 ;
    public final int SheetsPerPack = 50 ;

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public void replaceTonerCartridge() ;
    public void refillPaper() ;

}
