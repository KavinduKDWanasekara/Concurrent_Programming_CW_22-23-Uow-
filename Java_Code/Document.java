package com.company;

public class Document {

    private final String studentName;
    private final String documentName;
    private final int numberOfPages;
    private final int documentNumber;

    public Document(String studentName, String documentName, int numberOfPages, int documentNumber) {
        this.studentName = studentName;
        this.documentName = documentName;
        this.numberOfPages = numberOfPages;
        this.documentNumber =  documentNumber;
    }
    public int getNumberOfPages() {
        return numberOfPages;
    }

    public int getDocumentNumber() {
        return documentNumber;
    }

    public String getStudentName() {
        return studentName;
    }
}
