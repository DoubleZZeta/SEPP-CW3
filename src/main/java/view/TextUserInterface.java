package view;

import model.*;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import java.util.Scanner;

public class TextUserInterface implements View {
    private final Scanner scanner = new Scanner(System.in);
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_RESET = "\u001B[0m";

    @Override
    public String getInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    @Override
    public boolean getYesNoInput(String prompt) {
        System.out.println(prompt + " [Y/n]");
        String line = scanner.nextLine();
        if (line.equalsIgnoreCase("y") || line.equalsIgnoreCase("yes")) {
            return true;
        } else if (line.equalsIgnoreCase("n") || line.equalsIgnoreCase("no")) {
            return false;
        }
        return Boolean.parseBoolean(line);
    }

    @Override
    public void displayInfo(String text) {
        System.out.println(text);
    }

    @Override
    public void displaySuccess(String text) {
        System.out.println(ANSI_GREEN + text + ANSI_RESET);
    }

    @Override
    public void displayWarning(String text) {
        System.out.println(ANSI_YELLOW + text + ANSI_RESET);
    }

    @Override
    public void displayError(String text) {
        System.out.println(ANSI_RED + text + ANSI_RESET);
    }

    @Override
    public void displayException(Exception e) {
        StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw));
        displayError(sw.toString());
    }

    @Override
    public void displayDivider() {
        System.out.println("-------------------------");
    }

    @Override
    public void displayFAQ(FAQ faq) {
        System.out.println("Frequently Asked Questions");
        displayDivider();
        int i = 0;
        for (FAQSection section : faq.getSections()) {
            System.out.print("[");
            System.out.print(i++);
            System.out.print("] ");
            System.out.println(section.getTopic());
        }
    }

    @Override
    public void displayFAQSection(FAQSection section) {
        System.out.println(section.getTopic());
        displayDivider();
        for (FAQItem item : section.getItems()) {
            System.out.println(item.getQuestion());
            System.out.print("> ");
            System.out.println(item.getAnswer());
        }

        System.out.println("Subsections:");
        int i = 0;
        for (FAQSection subsection : section.getSubsections()) {
            System.out.print("[");
            System.out.print(i++);
            System.out.print("] ");
            System.out.println(subsection.getTopic());
        }
    }

    @Override
    public void displayInquiry(Inquiry inquiry) {
        System.out.println("Inquirer: " + inquiry.getInquirerEmail());
        System.out.println("Created at: " + inquiry.getCreatedAt());
        System.out.println("Assigned to: " + (inquiry.getAssignedTo() == null ? "No one" : inquiry.getAssignedTo()));
        System.out.println("Query:");
        System.out.println(inquiry.getContent());
    }

    
}
