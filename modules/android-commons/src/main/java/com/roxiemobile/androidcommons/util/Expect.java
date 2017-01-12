package com.roxiemobile.androidcommons.util;

import android.support.annotation.NonNull;

import com.roxiemobile.androidcommons.data.validator.Validatable;

/**
 * A set of expectation methods useful for validating objects states. Only failed expectations are recorded.
 * These methods can be used directly: <code>Expect.expectTrue(...)</code>, however,
 * they read better if they are referenced through static import:
 * <p>
 * <pre>
 * import static Expect.*;
 *    ...
 *    expectEqual(...);
 * </pre>
 */
public final class Expect
{
// MARK: - Construction

    private Expect() {
        // Do nothing
    }

// MARK: - Methods

    /**
     * Expects that a condition is true. If it isn't it throws an {@link ExpectationError} with the given message.
     *
     * @param condition Condition to be checked
     * @param message   The identifying message for the {@link ExpectationError} (<code>null</code> okay)
     */
    public static void expectTrue(boolean condition, String message) {
        if (!condition) {
            throwError(message);
        }
    }

    /**
     * Expects that a condition is true. If it isn't it throws an {@link ExpectationError} without a message.
     *
     * @param condition Condition to be checked
     */
    public static void expectTrue(boolean condition) {
        expectTrue(condition, null);
    }

    /**
     * Expects that a condition is false. If it isn't it throws an {@link ExpectationError} with the given message.
     *
     * @param condition Condition to be checked
     * @param message   The identifying message for the {@link ExpectationError} (<code>null</code> okay)
     */
    public static void expectFalse(boolean condition, String message) {
        expectTrue(!condition, message);
    }

    /**
     * Expects that a condition is false. If it isn't it throws an {@link ExpectationError} without a message.
     *
     * @param condition Condition to be checked
     */
    public static void expectFalse(boolean condition) {
        expectFalse(condition, null);
    }

// MARK: --

    /**
     * Expects that two objects are equal. If they are not, an {@link ExpectationError} is thrown with
     * the given message. If <code>expected</code> and <code>actual</code> are <code>null</code>,
     * they are considered equal.
     *
     * @param expected Expected value
     * @param actual   Actual value
     * @param message  The identifying message for the {@link ExpectationError} (<code>null</code> okay)
     */
    static public void expectEqual(Object expected, Object actual, String message)
    {
        if (safeEqual(expected, actual)) {
            // Do nothing
        }
        else if (expected instanceof String && actual instanceof String) {
            String cleanMessage = (message == null) ? "" : message;
            throw new ComparisonFailure(cleanMessage, (String) expected, (String) actual);
        }
        else {
            failNotEqual(expected, actual, message);
        }
    }

    /**
     * Expects that two objects are equal. If they are not, an {@link ExpectationError} without
     * a message is thrown. If <code>expected</code> and <code>actual</code> are <code>null</code>,
     * they are considered equal.
     *
     * @param expected Expected value
     * @param actual   The value to check against <code>expected</code>
     */
    public static void expectEqual(Object expected, Object actual) {
        expectEqual(expected, actual, null);
    }

    /**
     * Expects that two objects are <b>not</b> equals. If they are, an {@link ExpectationError}
     * is thrown with the given message. If <code>unexpected</code> and <code>actual</code>
     * are <code>null</code>, they are considered equal.
     *
     * @param unexpected Unexpected value to check
     * @param actual     The value to check against <code>unexpected</code>
     * @param message    The identifying message for the {@link ExpectationError} (<code>null</code> okay)
     */
    public static void expectNotEqual(Object unexpected, Object actual, String message) {
        if (safeEqual(unexpected, actual)) {
            failEqual(message, actual);
        }
    }

    /**
     * Expects that two objects are <b>not</b> equals. If they are, an {@link ExpectationError} without
     * a message is thrown. If <code>unexpected</code> and <code>actual</code> are <code>null</code>,
     * they are considered equal.
     *
     * @param unexpected Unexpected value to check
     * @param actual     The value to check against <code>unexpected</code>
     */
    public static void expectNotEqual(Object unexpected, Object actual) {
        expectNotEqual(unexpected, actual, null);
    }

// MARK: --

    /**
     * Expects that two objects refer to the same object. If they are not,
     * an {@link ExpectationError} is thrown with the given message.
     *
     * @param expected The expected object
     * @param actual   The object to compare to <code>expected</code>
     * @param message  The identifying message for the {@link ExpectationError} (<code>null</code> okay)
     */
    public static void expectSame(Object expected, Object actual, String message) {
        if (expected == actual) {
            return;
        }
        failNotSame(message, expected, actual);
    }

    /**
     * Expects that two objects refer to the same object. If they are not the same,
     * an {@link ExpectationError} without a message is thrown.
     *
     * @param expected The expected object
     * @param actual   The object to compare to <code>expected</code>
     */
    public static void expectSame(Object expected, Object actual) {
        expectSame(expected, actual, null);
    }

    /**
     * Expects that two objects do not refer to the same object. If they do refer to the same object,
     * an {@link ExpectationError} is thrown with the given message.
     *
     * @param unexpected The object you don't expect
     * @param actual     The object to compare to <code>unexpected</code>
     * @param message    The identifying message for the {@link ExpectationError} (<code>null</code> okay)
     */
    public static void expectNotSame(Object unexpected, Object actual, String message) {
        if (unexpected == actual) {
            failSame(message);
        }
    }

    /**
     * Expects that two objects do not refer to the same object. If they do refer
     * to the same object, an {@link ExpectationError} without a message is thrown.
     *
     * @param unexpected The object you don't expect
     * @param actual     The object to compare to <code>unexpected</code>
     */
    public static void expectNotSame(Object unexpected, Object actual) {
        expectNotSame(unexpected, actual, null);
    }

// MARK: --

    /**
     * Expects that an object is null. If it is not, an {@link ExpectationError} is thrown with the given message.
     *
     * @param object  Object to check or <code>null</code>
     * @param message The identifying message for the {@link ExpectationError} (<code>null</code> okay)
     */
    public static void expectNull(Object object, String message) {
        if (object == null) {
            return;
        }
        failNotNull(message, object);
    }

    /**
     * Expects that an object is null. If it isn't an {@link ExpectationError} is thrown.
     *
     * @param object Object to check or <code>null</code>
     */
    public static void expectNull(Object object) {
        expectNull(object, null);
    }

    /**
     * Expects that an object isn't null. If it is an {@link ExpectationError} is thrown with the given message.
     *
     * @param object  Object to check or <code>null</code>
     * @param message The identifying message for the {@link ExpectationError} (<code>null</code> okay)
     */
    public static void expectNotNull(Object object, String message) {
        expectTrue(object != null, message);
    }

    /**
     * Expects that an object isn't null. If it is an {@link ExpectationError} is thrown.
     *
     * @param object Object to check or <code>null</code>
     */
    public static void expectNotNull(Object object) {
        expectNotNull(object, null);
    }

// MARK: --

    /**
     * TODO
     */
    public static void expectNullOrEmpty(CharSequence str, String message) {
        expectTrue(StringUtils.isNullOrEmpty(str), message);
    }

    /**
     * TODO
     */
    public static void expectNullOrEmpty(CharSequence str) {
        expectNullOrEmpty(str, null);
    }

    /**
     * TODO
     */
    public static void expectNullOrEmpty(@NonNull CharSequence[] values, String message) {
        expectTrue(StringUtils.isNullOrEmpty(values), message);
    }

    /**
     * TODO
     */
    public static void expectNullOrEmpty(@NonNull CharSequence[] values) {
        expectNullOrEmpty(values, null);
    }

    /**
     * TODO
     */
    public static void expectNotEmpty(CharSequence str, String message) {
        expectTrue(StringUtils.isNotEmpty(str), message);
    }

    /**
     * TODO
     */
    public static void expectNotEmpty(CharSequence str) {
        expectNotEmpty(str, null);
    }

    /**
     * TODO
     */
    public static void expectNotEmpty(@NonNull CharSequence[] values, String message) {
        expectTrue(StringUtils.isNotEmpty(values), message);
    }

    /**
     * TODO
     */
    public static void expectNotEmpty(@NonNull CharSequence[] values) {
        expectNotEmpty(values, null);
    }

// MARK: --

    /**
     * TODO
     */
    public static void expectNullOrWhiteSpace(CharSequence str, String message) {
        expectTrue(StringUtils.isNullOrWhiteSpace(str), message);
    }

    /**
     * TODO
     */
    public static void expectNullOrWhiteSpace(CharSequence str) {
        expectNullOrWhiteSpace(str, null);
    }

    /**
     * TODO
     */
    public static void expectNullOrWhiteSpace(@NonNull CharSequence[] values, String message) {
        expectTrue(StringUtils.isNullOrWhiteSpace(values), message);
    }

    /**
     * TODO
     */
    public static void expectNullOrWhiteSpace(@NonNull CharSequence[] values) {
        expectNullOrWhiteSpace(values, null);
    }

    /**
     * TODO
     */
    public static void expectNotWhiteSpace(CharSequence str, String message) {
        expectTrue(StringUtils.isNotWhiteSpace(str), message);
    }

    /**
     * TODO
     */
    public static void expectNotWhiteSpace(CharSequence str) {
        expectNotWhiteSpace(str, null);
    }

    /**
     * TODO
     */
    public static void expectNotWhiteSpace(@NonNull CharSequence[] values, String message) {
        expectTrue(StringUtils.isNotWhiteSpace(values), message);
    }

    /**
     * TODO
     */
    public static void expectNotWhiteSpace(@NonNull CharSequence[] values) {
        expectNotWhiteSpace(values, null);
    }

// MARK: --

    /**
     * TODO
     */
    public static void expectValid(Validatable obj, String message) {
        expectTrue(ValidatableUtils.isValid(obj), message);
    }

    /**
     * TODO
     */
    public static void expectValid(Validatable obj) {
        expectValid(obj, null);
    }

    /**
     * TODO
     */
    public static void expectValid(@NonNull Validatable[] objects, String message) {
        expectTrue(ValidatableUtils.isValid(objects), message);
    }

    /**
     * TODO
     */
    public static void expectValid(@NonNull Validatable[] objects) {
        expectValid(objects, null);
    }

    /**
     * TODO
     */
    public static void expectNotValid(Validatable obj, String message) {
        expectTrue(ValidatableUtils.isNotValid(obj), message);
    }

    /**
     * TODO
     */
    public static void expectNotValid(Validatable obj) {
        expectNotValid(obj, null);
    }

    /**
     * TODO
     */
    public static void expectNotValid(@NonNull Validatable[] objects, String message) {
        expectTrue(ValidatableUtils.isNotValid(objects), message);
    }

    /**
     * TODO
     */
    public static void expectNotValid(@NonNull Validatable[] objects) {
        expectNotValid(objects, null);
    }

// MARK: --

    /**
     * TODO
     */
    public static void expectNullOrValid(Validatable obj, String message) {
        expectTrue(ValidatableUtils.isNullOrValid(obj), message);
    }

    /**
     * TODO
     */
    public static void expectNullOrValid(Validatable obj) {
        expectNullOrValid(obj, null);
    }

    /**
     * TODO
     */
    public static void expectNullOrValid(@NonNull Validatable[] objects, String message) {
        expectTrue(ValidatableUtils.isNullOrValid(objects), message);
    }

    /**
     * TODO
     */
    public static void expectNullOrValid(@NonNull Validatable[] objects) {
        expectNullOrValid(objects, null);
    }

    /**
     * TODO
     */
    public static void expectNullOrNotValid(Validatable obj, String message) {
        expectTrue(ValidatableUtils.isNullOrNotValid(obj), message);
    }

    /**
     * TODO
     */
    public static void expectNullOrNotValid(Validatable obj) {
        expectNullOrNotValid(obj, null);
    }

    /**
     * TODO
     */
    public static void expectNullOrNotValid(@NonNull Validatable[] objects, String message) {
        expectTrue(ValidatableUtils.isNullOrNotValid(objects), message);
    }

    /**
     * TODO
     */
    public static void expectNullOrNotValid(@NonNull Validatable[] objects) {
        expectNullOrNotValid(objects, null);
    }

// MARK: --

    /**
     * This interface facilitates the use of expectThrows from Java 8. It allows method references
     * to void methods (that declare checked exceptions) to be passed directly into expectThrows
     * without wrapping. It is not meant to be implemented directly.
     */
    public interface ThrowingRunnable {
        void run() throws Throwable;
    }

    /**
     * Expects that {@code runnable} throws an exception of type {@code expectedThrowable} when
     * executed. If it does, the exception object is returned. If it does not throw an exception, an
     * {@link ExpectationError} is thrown. If it throws the wrong type of exception, an {@code
     * ExpectationError} is thrown describing the mismatch; the exception that was actually thrown can
     * be obtained by calling {@link ExpectationError#getCause}.
     *
     * @param expectedThrowable The expected type of the exception
     * @param runnable          A function that is expected to throw an exception when executed
     * @return The exception thrown by {@code runnable}
     */
    public static <T extends Throwable> T expectThrows(Class<T> expectedThrowable, ThrowingRunnable runnable)
    {
        try {
            runnable.run();
        }
        catch (Throwable actualThrown)
        {
            if (expectedThrowable.isInstance(actualThrown)) {
                @SuppressWarnings("unchecked")
                T retVal = (T) actualThrown;
                return retVal;
            }
            else
            {
                String mismatchMessage = format("Unexpected exception type thrown;",
                        expectedThrowable.getSimpleName(), actualThrown.getClass().getSimpleName());
                throw new ExpectationError(mismatchMessage, actualThrown);
            }
        }

        String message = String.format("Expected %s to be thrown, but nothing was thrown", expectedThrowable.getSimpleName());
        throw new ExpectationError(message);
    }

// MARK: - Private Methods

    private static boolean safeEqual(Object expected, Object actual) {
        return (expected == null && actual == null) || (expected != null && actual != null && expected.equals(actual));
    }

    private static void failNotEqual(Object expected, Object actual, String message) {
        throwError(format(message, expected, actual));
    }

    private static void failEqual(String message, Object actual) {
        String formatted = "Values should be different. ";
        if (message != null) {
            formatted = message + ". ";
        }

        formatted += "Actual: " + actual;
        throwError(formatted);
    }

    private static void failSame(String message) {
        String formatted = "";

        if (message != null) {
            formatted = message + " ";
        }
        throwError(formatted + "expected not same");
    }

    private static void failNotSame(String message, Object expected, Object actual) {
        String formatted = "";

        if (message != null) {
            formatted = message + " ";
        }
        throwError(formatted + "expected same:<" + expected + "> was not:<" + actual + ">");
    }

    private static void failNotNull(String message, Object actual) {
        String formatted = "";

        if (message != null) {
            formatted = message + " ";
        }
        throwError(formatted + "expected null, but was:<" + actual + ">");
    }

    static String format(String message, Object expected, Object actual) {
        String formatted = "";

        if (message != null && !message.equals("")) {
            formatted = message + " ";
        }

        String expectedString = String.valueOf(expected);
        String actualString = String.valueOf(actual);

        if (expectedString.equals(actualString)) {
            return formatted + "expected: " + formatClassAndValue(expected, expectedString)
                    + " but was: " + formatClassAndValue(actual, actualString);
        }
        else {
            return formatted + "expected:<" + expectedString + "> but was:<" + actualString + ">";
        }
    }

    private static String formatClassAndValue(Object value, String valueString) {
        String className = (value == null) ? "null" : value.getClass().getName();
        return className + "<" + valueString + ">";
    }

    private static void throwError(String message) {
        if (message == null) {
            throw new ExpectationError();
        }
        throw new ExpectationError(message);
    }

}
