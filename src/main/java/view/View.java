package view;

import model.FAQ;
import model.FAQSection;
import model.Inquiry;

public interface View {
    String getInput(String prompt);
    boolean getYesNoInput(String prompt);
    void displayInfo(String text);
    void displaySuccess(String text);
    void displayWarning(String text);
    void displayError(String text);
    void displayException(Exception e);
    void displayDivider();
    void displayFAQ(FAQ faq);
    void displayFAQSection(FAQSection section);
    void displayInquiry(Inquiry inquiry);
}
