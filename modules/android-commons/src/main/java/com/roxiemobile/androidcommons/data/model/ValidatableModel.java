package com.roxiemobile.androidcommons.data.model;

import com.roxiemobile.androidcommons.logging.Logger;

import java.io.Serializable;

public abstract class ValidatableModel
        implements Serializable, Validatable
{
// MARK: - Methods

    /**
     * TODO
     */
    public boolean isValid() {
        boolean result = true;

        try {
            // Check object's state
            validate();
        }
        catch (Exception ex) {
            String className = getClass().getSimpleName();
            result = false;

            // Log validation error
            Logger.w(className, String.format("%s is invalid", className), ex);
        }

        // Done
        return result;
    }

    /**
     * Checks attribute values or a combination of attribute values for correctness (cross validation).
     */
    public void validate() {
        // Do nothing
    }
}
