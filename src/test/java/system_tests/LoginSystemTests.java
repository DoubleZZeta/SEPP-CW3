package system_tests;

import controller.GuestController;
import external.MockAuthenticationService;
import external.MockEmailService;
import model.AuthenticatedUser;
import model.SharedContext;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;
import view.TextUserInterface;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

public class LoginSystemTests extends TUITest {
    @Test
    public void testLoginAsAdminStaff() throws URISyntaxException, IOException, ParseException {
        setMockInput("admin1", "admin1pass");
        SharedContext context = new SharedContext();
        GuestController guestController = new GuestController(context, new TextUserInterface(), new MockAuthenticationService(), new MockEmailService());
        startOutputCapture();
        guestController.login();
        assertOutputContains("Logged in as admin1");
        assertInstanceOf(AuthenticatedUser.class, context.currentUser);
        assertEquals("AdminStaff", ((AuthenticatedUser) context.currentUser).getRole());
    }

    @Test
    public void testLoginAsTeachingStaff() throws URISyntaxException, IOException, ParseException {
        setMockInput("teacher1", "teacher1pass");
        SharedContext context = new SharedContext();
        GuestController guestController = new GuestController(context, new TextUserInterface(), new MockAuthenticationService(), new MockEmailService());
        startOutputCapture();
        guestController.login();
        assertOutputContains("Logged in as teacher1");
        assertInstanceOf(AuthenticatedUser.class, context.currentUser);
        assertEquals("TeachingStaff", ((AuthenticatedUser) context.currentUser).getRole());
    }

    @Test
    public void testLoginAsStudent() throws URISyntaxException, IOException, ParseException {
        setMockInput("student1", "student1pass");
        SharedContext context = new SharedContext();
        GuestController guestController = new GuestController(context, new TextUserInterface(), new MockAuthenticationService(), new MockEmailService());
        startOutputCapture();
        guestController.login();
        assertOutputContains("Logged in as student1");
        assertInstanceOf(AuthenticatedUser.class, context.currentUser);
        assertEquals("Student", ((AuthenticatedUser) context.currentUser).getRole());
    }
}