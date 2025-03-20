package controller;

import external.AuthenticationService;
import external.EmailService;
import model.Guest;
import model.SharedContext;
import view.View;

public class AuthenticatedUserController extends Controller {
    public AuthenticatedUserController(SharedContext sharedContext, View view, AuthenticationService auth, EmailService email) {
        super(sharedContext, view, auth, email);
    }

    public void logout() {
        if (sharedContext.currentUser instanceof Guest) {
            view.displayError("Guest users cannot logout!");
            return;
        }
        sharedContext.currentUser = new Guest();
        view.displaySuccess("Logged out!");
    }
}
