package controller;

import external.AuthenticationService;
import external.EmailService;
import model.AuthenticatedUser;
import model.Inquiry;
import model.SharedContext;
import view.View;

import java.util.ArrayList;
import java.util.List;

public class TeachingStaffController extends StaffController {
    public TeachingStaffController(SharedContext sharedContext, View view, AuthenticationService auth, EmailService email) {
        super(sharedContext, view, auth, email);
    }

    public void manageReceivedInquiries() {
        String userEmail = ((AuthenticatedUser) sharedContext.currentUser).getEmail();
        List<Inquiry> assignedInquiries = new ArrayList<>();
        for (Inquiry inquiry : sharedContext.inquiries) {
            if (userEmail.equals(inquiry.getAssignedTo())) {
                assignedInquiries.add(inquiry);
            }
        }
        String[] inquiryTitles = getInquiryTitles(assignedInquiries);

        while (true) {
            view.displayInfo("Assigned inquiries");
            int selection = selectFromMenu(inquiryTitles, "Back to main menu");
            if (selection == -1) {
                return;
            }
            Inquiry selectedInquiry = assignedInquiries.get(selection);

            while (true) {
                view.displayDivider();
                view.displayInquiry(selectedInquiry);
                view.displayDivider();
                String[] followUpOptions = { "Respond to inquiry" };
                int followUpSelection = selectFromMenu(followUpOptions, "Back to assigned inquiries");

                if (followUpSelection == -1) {
                    break;
                } else if (followUpOptions[followUpSelection].equals("Respond to inquiry")) {
                    respondToInquiry(selectedInquiry);
                    inquiryTitles = getInquiryTitles(sharedContext.inquiries); // required to remove responded inquiry from titles
                    break;
                }
            }
        }
    }
}
