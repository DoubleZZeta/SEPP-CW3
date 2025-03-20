package system_tests;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.URISyntaxException;

import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;

import controller.GuestController;
import external.MockAuthenticationService;
import external.MockEmailService;
import model.SharedContext;
import view.TextUserInterface;

public class TUITest {
    private PrintStream backupOut;
    private InputStream backupIn;

    @BeforeEach
    public void backupSystemStreams() {
        backupOut = System.out;
        backupIn = System.in;
    }

    @AfterEach
    public void restoreSystemStreams() {
        System.setOut(backupOut);
        System.setIn(backupIn);
    }

    private ByteArrayOutputStream out;

    protected void setMockInput(String... inputLines) {
        StringBuilder sb = new StringBuilder();
        for (String line : inputLines) {
            sb.append(line);
            sb.append(System.lineSeparator());
        }
        ByteArrayInputStream in = new ByteArrayInputStream(sb.toString().getBytes());
        System.setIn(in);
    }

    protected void startOutputCapture() {
        // NOTE: be careful, if the captured output exceeds 8192 bytes, the remainder will be lost!
        out = new ByteArrayOutputStream(8192);
        System.setOut(new PrintStream(out));
    }

    protected void assertOutputContains(String expected) {
        String output = out.toString();
        assertTrue(output.contains(expected), "Output does not contain expected '" + expected + "':" + System.lineSeparator() + output);
    }

    protected void loginAsAdminStaff(SharedContext context) throws URISyntaxException, IOException, ParseException {
        setMockInput("admin1", "admin1pass");
        GuestController guestController = new GuestController(context, new TextUserInterface(), new MockAuthenticationService(), new MockEmailService());
        guestController.login();
    }
 
    protected void loginAsTeachingStaff(SharedContext context) throws URISyntaxException, IOException, ParseException {
        setMockInput("teacher1", "teacher1pass");
        GuestController guestController = new GuestController(context, new TextUserInterface(), new MockAuthenticationService(), new MockEmailService());
        guestController.login();
    }
 
    protected void loginAsStudent(SharedContext context) throws URISyntaxException, IOException, ParseException {
        setMockInput("student1", "student1pass");
        GuestController guestController = new GuestController(context, new TextUserInterface(), new MockAuthenticationService(), new MockEmailService());
        guestController.login();
    }

}
