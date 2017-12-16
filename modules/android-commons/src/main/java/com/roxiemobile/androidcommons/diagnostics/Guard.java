package com.roxiemobile.androidcommons.diagnostics;

import com.roxiemobile.androidcommons.data.model.Validatable;

import java.util.Collection;
import java.util.Map;

import static com.roxiemobile.androidcommons.diagnostics.Expect.expectAllBlank;
import static com.roxiemobile.androidcommons.diagnostics.Expect.expectAllEmpty;
import static com.roxiemobile.androidcommons.diagnostics.Expect.expectAllNotBlank;
import static com.roxiemobile.androidcommons.diagnostics.Expect.expectAllNotEmpty;
import static com.roxiemobile.androidcommons.diagnostics.Expect.expectAllNotNull;
import static com.roxiemobile.androidcommons.diagnostics.Expect.expectAllNotValid;
import static com.roxiemobile.androidcommons.diagnostics.Expect.expectAllNull;
import static com.roxiemobile.androidcommons.diagnostics.Expect.expectAllNullOrNotValid;
import static com.roxiemobile.androidcommons.diagnostics.Expect.expectAllNullOrValid;
import static com.roxiemobile.androidcommons.diagnostics.Expect.expectAllValid;
import static com.roxiemobile.androidcommons.diagnostics.Expect.expectBlank;
import static com.roxiemobile.androidcommons.diagnostics.Expect.expectEmpty;
import static com.roxiemobile.androidcommons.diagnostics.Expect.expectEqual;
import static com.roxiemobile.androidcommons.diagnostics.Expect.expectFalse;
import static com.roxiemobile.androidcommons.diagnostics.Expect.expectNotBlank;
import static com.roxiemobile.androidcommons.diagnostics.Expect.expectNotEmpty;
import static com.roxiemobile.androidcommons.diagnostics.Expect.expectNotEqual;
import static com.roxiemobile.androidcommons.diagnostics.Expect.expectNotNull;
import static com.roxiemobile.androidcommons.diagnostics.Expect.expectNotSame;
import static com.roxiemobile.androidcommons.diagnostics.Expect.expectNotValid;
import static com.roxiemobile.androidcommons.diagnostics.Expect.expectNull;
import static com.roxiemobile.androidcommons.diagnostics.Expect.expectNullOrNotValid;
import static com.roxiemobile.androidcommons.diagnostics.Expect.expectNullOrValid;
import static com.roxiemobile.androidcommons.diagnostics.Expect.expectSame;
import static com.roxiemobile.androidcommons.diagnostics.Expect.expectTrue;
import static com.roxiemobile.androidcommons.diagnostics.Expect.expectValid;

/**
 * A set of methods useful for validating objects states. Only failed checks are throws errors.
 * These methods can be used directly: <code>Guard.isTrue(...)</code>.
 */
public final class Guard
{
// MARK: - Construction

    private Guard() {
        // Do nothing
    }

// MARK: - Methods

    public static void isTrue(final boolean condition, final String message) {
        rethrowOnFailure(message, () -> expectTrue(condition));
    }

    public static void isTrue(final boolean condition) {
        isTrue(condition, null);
    }

    public static void isFalse(final boolean condition, final String message) {
        rethrowOnFailure(message, () -> expectFalse(condition));
    }

    public static void isFalse(final boolean condition) {
        isFalse(condition, null);
    }

// MARK: -

    public static void equal(final Object expected, final Object actual, final String message) {
        rethrowOnFailure(message, () -> expectEqual(expected, actual));
    }

    public static void equal(final Object expected, final Object actual) {
        equal(expected, actual, null);
    }

    public static void notEqual(final Object unexpected, final Object actual, final String message) {
        rethrowOnFailure(message, () -> expectNotEqual(unexpected, actual));
    }

    public static void notEqual(final Object unexpected, final Object actual) {
        notEqual(unexpected, actual, null);
    }

// MARK: -

    public static void same(final Object expected, final Object actual, final String message) {
        rethrowOnFailure(message, () -> expectSame(expected, actual));
    }

    public static void same(final Object expected, final Object actual) {
        same(expected, actual, null);
    }

    public static void notSame(final Object unexpected, final Object actual, final String message) {
        rethrowOnFailure(message, () -> expectNotSame(unexpected, actual));
    }

    public static void notSame(final Object unexpected, final Object actual) {
        notSame(unexpected, actual, null);
    }

// MARK: -

    public static void isNull(final Object object, final String message) {
        rethrowOnFailure(message, () -> expectNull(object));
    }

    public static void isNull(final Object object) {
        isNull(object, null);
    }

    public static <T> void allNull(final T[] objects, final String message) {
        rethrowOnFailure(message, () -> expectAllNull(objects));
    }

    public static <T> void allNull(final T[] objects) {
        allNull(objects, null);
    }

    public static <T> void allNull(final Collection<T> collection, final String message) {
        rethrowOnFailure(message, () -> expectAllNull(collection));
    }

    public static <T> void allNull(final Collection<T> collection) {
        allNull(collection, null);
    }

    public static void notNull(final Object object, final String message) {
        rethrowOnFailure(message, () -> expectNotNull(object));
    }

    public static void notNull(final Object object) {
        notNull(object, null);
    }

    public static <T> void allNotNull(final T[] objects, final String message) {
        rethrowOnFailure(message, () -> expectAllNotNull(objects));
    }

    public static <T> void allNotNull(final T[] objects) {
        allNotNull(objects, null);
    }

    public static <T> void allNotNull(final Collection<T> collection, final String message) {
        rethrowOnFailure(message, () -> expectAllNotNull(collection));
    }

    public static <T> void allNotNull(final Collection<T> collection) {
        allNotNull(collection, null);
    }

// MARK: -

    public static void empty(final CharSequence value, final String message) {
        rethrowOnFailure(message, () -> expectEmpty(value));
    }

    public static void empty(final CharSequence value) {
        empty(value, null);
    }

    public static void allEmpty(final CharSequence[] values, final String message) {
        rethrowOnFailure(message, () -> expectAllEmpty(values));
    }

    public static void allEmpty(final CharSequence[] values) {
        allEmpty(values, null);
    }

    public static void notEmpty(final CharSequence value, final String message) {
        rethrowOnFailure(message, () -> expectNotEmpty(value));
    }

    public static void notEmpty(final CharSequence value) {
        notEmpty(value, null);
    }

    public static void allNotEmpty(final CharSequence[] values, final String message) {
        rethrowOnFailure(message, () -> expectAllNotEmpty(values));
    }

    public static void allNotEmpty(final CharSequence[] values) {
        allNotEmpty(values, null);
    }

// MARK: -

    /**
     * TODO
     */
    public static <T> void empty(final T[] array, final String message) {
        rethrowOnFailure(message, () -> expectEmpty(array));
    }

    /**
     * TODO
     */
    public static <T> void empty(final T[] array) {
        empty(array, null);
    }

    /**
     * TODO
     */
    public static <T> void empty(final Collection<T> collection, final String message) {
        rethrowOnFailure(message, () -> expectEmpty(collection));
    }

    /**
     * TODO
     */
    public static <T> void empty(final Collection<T> collection) {
        empty(collection, null);
    }

    /**
     * TODO
     */
    public static <K, V> void empty(final Map<K, V> map, final String message) {
        rethrowOnFailure(message, () -> expectEmpty(map));
    }

    /**
     * TODO
     */
    public static <K, V> void empty(final Map<K, V> map) {
        empty(map, null);
    }

    /**
     * TODO
     */
    public static <T> void notEmpty(final T[] array, final String message) {
        rethrowOnFailure(message, () -> expectNotEmpty(array));
    }

    /**
     * TODO
     */
    public static <T> void notEmpty(final T[] array) {
        notEmpty(array, null);
    }

    /**
     * TODO
     */
    public static <T> void notEmpty(final Collection<T> collection, final String message) {
        rethrowOnFailure(message, () -> expectNotEmpty(collection));
    }

    /**
     * TODO
     */
    public static <T> void notEmpty(final Collection<T> collection) {
        notEmpty(collection, null);
    }

    /**
     * TODO
     */
    public static <K, V> void notEmpty(final Map<K, V> map, final String message) {
        rethrowOnFailure(message, () -> expectNotEmpty(map));
    }

    /**
     * TODO
     */
    public static <K, V> void notEmpty(final Map<K, V> map) {
        notEmpty(map, null);
    }

// MARK: -

    public static void blank(final CharSequence value, final String message) {
        rethrowOnFailure(message, () -> expectBlank(value));
    }

    public static void blank(final CharSequence value) {
        blank(value, null);
    }

    public static void allBlank(final CharSequence[] values, final String message) {
        rethrowOnFailure(message, () -> expectAllBlank(values));
    }

    public static void allBlank(final CharSequence[] values) {
        allBlank(values, null);
    }

    public static void notBlank(final CharSequence value, final String message) {
        rethrowOnFailure(message, () -> expectNotBlank(value));
    }

    public static void notBlank(final CharSequence value) {
        notBlank(value, null);
    }

    public static void allNotBlank(final CharSequence[] values, final String message) {
        rethrowOnFailure(message, () -> expectAllNotBlank(values));
    }

    public static void allNotBlank(final CharSequence[] values) {
        allNotBlank(values, null);
    }

// MARK: -

    public static void valid(final Validatable object, final String message) {
        rethrowOnFailure(message, () -> expectValid(object));
    }

    public static void valid(final Validatable object) {
        valid(object, null);
    }

    public static void allValid(final Validatable[] objects, final String message) {
        rethrowOnFailure(message, () -> expectAllValid(objects));
    }

    public static void allValid(final Validatable[] objects) {
        allValid(objects, null);
    }

    public static void notValid(final Validatable object, final String message) {
        rethrowOnFailure(message, () -> expectNotValid(object));
    }

    public static void notValid(final Validatable object) {
        notValid(object, null);
    }

    public static void allNotValid(final Validatable[] objects, final String message) {
        rethrowOnFailure(message, () -> expectAllNotValid(objects));
    }

    public static void allNotValid(final Validatable[] objects) {
        allNotValid(objects, null);
    }

// MARK: -

    public static void nullOrValid(final Validatable object, final String message) {
        rethrowOnFailure(message, () -> expectNullOrValid(object));
    }

    public static void nullOrValid(final Validatable object) {
        nullOrValid(object, null);
    }

    public static void allNullOrValid(final Validatable[] objects, final String message) {
        rethrowOnFailure(message, () -> expectAllNullOrValid(objects));
    }

    public static void allNullOrValid(final Validatable[] objects) {
        allNullOrValid(objects, null);
    }

    public static void nullOrNotValid(final Validatable object, final String message) {
        rethrowOnFailure(message, () -> expectNullOrNotValid(object));
    }

    public static void nullOrNotValid(final Validatable object) {
        nullOrNotValid(object, null);
    }

    public static void allNullOrNotValid(final Validatable[] objects, final String message) {
        rethrowOnFailure(message, () -> expectAllNullOrNotValid(objects));
    }

    public static void allNullOrNotValid(final Validatable[] objects) {
        allNullOrNotValid(objects, null);
    }

// MARK: - Private Methods

    private static void rethrowOnFailure(final String message, final Runnable task) {
        if (task == null) {
            throw new NullPointerException();
        }

        try {
            task.run();
        }
        catch (ExpectationException e) {
            throwError(message, e);
        }
    }

    private static void throwError(final String message, final Throwable cause) {
        if (message == null) {
            throw new GuardError(cause);
        }
        throw new GuardError(message, cause);
    }
}
