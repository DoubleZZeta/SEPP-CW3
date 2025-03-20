package external;

/**
 * API for interacting with an Authentication Service Provider,
 * which offers user management functionality.
 */
public interface AuthenticationService {
    /**
     *
     * @param username unique user identifier
     * @param password user password corresponding to the email in plaintext
     * @return JSON string response from the Authentication Service Provider
     */
    String login(String username, String password);
}
