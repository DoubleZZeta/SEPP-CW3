package controller;

import external.AuthenticationService;
import external.EmailService;
import model.AuthenticatedUser;
import model.SharedContext;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import view.View;

public class GuestController extends Controller {
    public GuestController(SharedContext sharedContext, View view, AuthenticationService auth, EmailService email) {
        super(sharedContext, view, auth, email);
    }

    public void login() {
        String username = view.getInput("Enter your username: ");
        String password = view.getInput("Enter your password: ");
        String response = auth.login(username, password);

        JSONParser parser = new JSONParser();
        String email = null;
        String role = null;
        try {
            JSONObject result = (JSONObject) parser.parse(response);
            if (result.containsKey("error")) {
                String errorMessage = (String) result.get("error");
                view.displayError(errorMessage);
                return;
            }
            email = (String) result.get("email");
            role = (String) result.get("role");
        } catch (ParseException e) {
            view.displayException(e);
        }

        try {
            sharedContext.currentUser = new AuthenticatedUser(email, role);
        } catch (IllegalArgumentException e) {
            view.displayException(e);
        }

        view.displaySuccess("Logged in as " + username);
    }
}
