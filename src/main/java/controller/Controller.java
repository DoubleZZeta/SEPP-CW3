package controller;

import external.AuthenticationService;
import external.EmailService;
import model.SharedContext;
import view.View;

public abstract class Controller {
    protected final SharedContext sharedContext;
    protected final View view;
    protected final AuthenticationService auth;
    protected final EmailService email;

    protected Controller(SharedContext sharedContext, View view, AuthenticationService auth, EmailService email) {
        this.sharedContext = sharedContext;
        this.view = view;
        this.auth = auth;
        this.email = email;
    }

    protected <T> int selectFromMenu(T[] options, String exitOption) {
        while (true) {
            view.displayDivider();
            int i = 0;
            for (T option : options) {
                view.displayInfo("[" + i + "] " + option);
                i++;
            }
            view.displayInfo("[-1] " + exitOption);
            view.displayDivider();
            String input = view.getInput("Please choose an option: ");
            try {
                int optionNo = Integer.parseInt(input);
                if (optionNo == -1) {
                    return -1;
                }
                if (optionNo >= 0 && optionNo < options.length) {
                    return optionNo;
                }
                view.displayError("Invalid option " + optionNo);
            } catch (NumberFormatException e) {
                view.displayError("Invalid option " + input);
            }
        }
    }
}
