package Utils;

import org.testng.asserts.SoftAssert;

public class SoftAssertUtil {
    private static SoftAssertUtil instance;   // Single instance
    private SoftAssert softAssert;            // Single SoftAssert object

    // Private constructor so no one can create object directly
    public SoftAssertUtil() {
        this.softAssert = new SoftAssert();
    }

    // Global point of access
    public static synchronized SoftAssertUtil getInstance() {
        if (instance == null) {
            instance = new SoftAssertUtil();
        }
        return instance;
    }

    // --- Assertion Methods ---
    public void assertEquals(String actual, String expected, String message) {
        try {
            softAssert.assertEquals(actual, expected, message);
        } catch (AssertionError e) {
            System.out.println("❌ Assertion Failed [assertEquals]: " + message);
        }
    }

    public void assertEquals(int actual, int expected, String message) {
        try {
            softAssert.assertEquals(actual, expected, message);
        } catch (AssertionError e) {
            System.out.println("❌ Assertion Failed [assertEquals]: " + message);
        }
    }

    public void assertNotEquals(String actual, String expected, String message) {
        try {
            softAssert.assertNotEquals(actual, expected, message);
        } catch (AssertionError e) {
            System.out.println("❌ Assertion Failed [assertNotEquals]: " + message);
        }
    }

    public void assertNotEquals(int actual, int expected, String message) {
        try {
            softAssert.assertNotEquals(actual, expected, message);
        } catch (AssertionError e) {
            System.out.println("❌ Assertion Failed [assertNotEquals]: " + message);
        }
    }

    public void assertTrue(boolean condition, String message) {
        try {
            softAssert.assertTrue(condition, message);
        } catch (AssertionError e) {
            System.out.println("❌ Assertion Failed [assertTrue]: " + message);
        }
    }

    public void assertFalse(boolean condition, String message) {
        try {
            softAssert.assertFalse(condition, message);
        } catch (AssertionError e) {
            System.out.println("❌ Assertion Failed [assertFalse]: " + message);
        }
    }

    public void fail(String message) {
        try {
            softAssert.fail(message);
        } catch (AssertionError e) {
            System.out.println("❌ Forced Failure: " + message);
        }
    }

    public void assertAll() {
        try {
            softAssert.assertAll();
        } catch (AssertionError e) {
            System.out.println("❌ SoftAssert.assertAll() triggered failures.");
            throw e;
        }
    }

    public SoftAssert getSoftAssert() {
        return softAssert;
    }

    // Reset SoftAssert (useful between tests)
    public void reset() {
        this.softAssert = new SoftAssert();
    }
}
