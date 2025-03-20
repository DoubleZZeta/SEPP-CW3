package controller;

import external.AuthenticationService;
import external.EmailService;
import model.*;
import view.View;

public class MenuController extends Controller {
    public MenuController(SharedContext sharedContext, View view, AuthenticationService auth, EmailService email) {
        super(sharedContext, view, auth, email);
    }

    public enum GuestMainMenuOption {
        LOGIN,
        CONSULT_FAQ,
        CONTACT_STAFF,
    }

    public enum StudentMainMenuOption {
        LOGOUT,
        CONSULT_FAQ,
        CONTACT_STAFF,
    }

    public enum TeachingStaffMainMenuOption {
        LOGOUT,
        MANAGE_RECEIVED_QUERIES,
    }

    public enum AdminStaffMainMenuOption {
        LOGOUT,
        MANAGE_QUERIES,
        MANAGE_FAQ,
    }

    public void mainMenu() {
        boolean endLoop = false;
        while (!endLoop) {
            String userRole;
            if (sharedContext.currentUser instanceof Guest) {
                userRole = "Guest";
            } else if (sharedContext.currentUser instanceof AuthenticatedUser) {
                userRole = ((AuthenticatedUser) sharedContext.currentUser).getRole();
            } else {
                view.displayError("Main menu not implemented for current user type");
                return;
            }

            view.displayInfo("Hello! What would you like to do?");

            switch (userRole) {
                case "Guest" -> endLoop = handleGuestMainMenu();
                case "Student" -> endLoop = handleStudentMainMenu();
                case "TeachingStaff" -> endLoop = handleTeachingStaffMainMenu();
                case "AdminStaff" -> endLoop = handleAdminStaffMainMenu();
            }
        }

        view.displayInfo("Bye bye!");
    }

    private boolean handleGuestMainMenu() {
        int optionNo = selectFromMenu(GuestMainMenuOption.values(), "Exit");
        if (optionNo == -1) {
            return true;
        }
        GuestMainMenuOption option = GuestMainMenuOption.values()[optionNo];
        switch (option) {
            case LOGIN -> new GuestController(sharedContext, view, auth, email).login();
            case CONSULT_FAQ -> new InquirerController(sharedContext, view, auth, email).consultFAQ();
            case CONTACT_STAFF -> new InquirerController(sharedContext, view, auth, email).contactStaff();
        }
        return false;
    }

    private boolean handleStudentMainMenu() {
        int optionNo = selectFromMenu(StudentMainMenuOption.values(), "Exit");
        if (optionNo == -1) {
            return true;
        }
        StudentMainMenuOption option = StudentMainMenuOption.values()[optionNo];
        switch (option) {
            case LOGOUT -> new AuthenticatedUserController(sharedContext, view, auth, email).logout();
            case CONSULT_FAQ -> new InquirerController(sharedContext, view, auth, email).consultFAQ();
            case CONTACT_STAFF -> new InquirerController(sharedContext, view, auth, email).contactStaff();
        }
        return false;
    }

    private boolean handleTeachingStaffMainMenu() {
        int optionNo = selectFromMenu(TeachingStaffMainMenuOption.values(), "Exit");
        if (optionNo == -1) {
            return true;
        }
        TeachingStaffMainMenuOption option = TeachingStaffMainMenuOption.values()[optionNo];
        switch (option) {
            case LOGOUT -> new AuthenticatedUserController(sharedContext, view, auth, email).logout();
            case MANAGE_RECEIVED_QUERIES -> new TeachingStaffController(sharedContext, view, auth, email).manageReceivedInquiries();
        }
        return false;
    }

    private boolean handleAdminStaffMainMenu() {
        int optionNo = selectFromMenu(AdminStaffMainMenuOption.values(), "Exit");
        if (optionNo == -1) {
            return true;
        }
        AdminStaffMainMenuOption option = AdminStaffMainMenuOption.values()[optionNo];
        switch (option) {
            case LOGOUT -> new AuthenticatedUserController(sharedContext, view, auth, email).logout();
            case MANAGE_FAQ -> new AdminStaffController(sharedContext, view, auth, email).manageFAQ();
            case MANAGE_QUERIES -> new AdminStaffController(sharedContext, view, auth, email).manageInquiries();
        }
        return false;
    }
}
