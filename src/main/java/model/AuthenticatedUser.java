package model;

public class AuthenticatedUser extends User {
    private final String email;
    private final String role;

    public AuthenticatedUser(String email, String role) {
        if (email == null) {
            throw new IllegalArgumentException("User email cannot be null!");
        }
        if (role == null || (!role.equals("AdminStaff") && !role.equals("TeachingStaff") && !role.equals("Student"))) {
            throw new IllegalArgumentException("Unsupported user role " + role);
        }
        this.email = email;
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }
}
