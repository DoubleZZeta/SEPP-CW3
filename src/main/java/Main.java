import model.SharedContext;
import controller.MenuController;
import external.AuthenticationService;
import external.EmailService;
import external.MockAuthenticationService;
import external.MockEmailService;
import org.json.simple.parser.ParseException;
import view.TextUserInterface;
import view.View;

import java.io.IOException;
import java.net.URISyntaxException;

public class Main {
    public static void main(String[] args) {
        View view = new TextUserInterface();
        try {
            AuthenticationService auth = new MockAuthenticationService();
            EmailService email = new MockEmailService();
            SharedContext sharedContext = new SharedContext();
            MenuController menus = new MenuController(sharedContext, view, auth, email);
            menus.mainMenu();
        } catch (URISyntaxException | IOException | ParseException | NullPointerException e) {
            view.displayException(e);
        }
    }
}
