package gmklosinski.githubuseractivity;

import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

class ActivityTest {

    @org.junit.jupiter.api.Test
    void creationWithArgumentDoesNotThrow() {
        assertDoesNotThrow(() -> {
            final String username = "g-m-klosinski";

            new Activity(new String[]{username});
        });
    }

    @org.junit.jupiter.api.Test
    void creationWithoutArgumentsThrows() {
        assertThrows(
            IllegalArgumentException.class,
            () -> {
                new Activity(new String[]{});
            }
        );
    }

    @org.junit.jupiter.api.Test
    void creationWithTwoArgumentsThrows() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Activity(new String[]{})
        );
    }
}