package controller;

import external.AuthenticationService;
import external.EmailService;
import model.AuthenticatedUser;
import model.Inquiry;
import model.SharedContext;
import view.View;

import java.util.List;

public class StaffController extends Controller {
    public StaffController(SharedContext sharedContext, View view, AuthenticationService auth, EmailService email) {
        super(sharedContext, view, auth, email);
    }

    protected String[] getInquiryTitles(List<Inquiry> inquiries) {
        String[] inquiryTitles = new String[inquiries.size()];

        for (int i = 0; i < inquiryTitles.length; ++i) {
            inquiryTitles[i] = inquiries.get(i).getSubject().strip();
        }

        return inquiryTitles;
    }

    protected void respondToInquiry(Inquiry inquiry) {
        String subject = view.getInput("Enter subject: ");
        String response = view.getInput("Enter response:\n");
        String currentEmail = ((AuthenticatedUser) sharedContext.currentUser).getEmail();
        email.sendEmail(currentEmail, inquiry.getInquirerEmail(), subject, response);
        sharedContext.inquiries.remove(inquiry);
        view.displaySuccess("Email response sent!");
    }
}
