package com.roxiemobile.androidcommons.util;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.roxiemobile.androidcommons.data.mapper.DataMapper;
import com.roxiemobile.androidcommons.data.model.ParkingModel;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

import static com.roxiemobile.androidcommons.util.ThrowIf.throwIfEqual;
import static com.roxiemobile.androidcommons.util.ThrowIf.throwIfFalse;
import static com.roxiemobile.androidcommons.util.ThrowIf.throwIfNotEqual;
import static com.roxiemobile.androidcommons.util.ThrowIf.throwIfNotNull;
import static com.roxiemobile.androidcommons.util.ThrowIf.throwIfNotValid;
import static com.roxiemobile.androidcommons.util.ThrowIf.throwIfNull;
import static com.roxiemobile.androidcommons.util.ThrowIf.throwIfNullOrEmpty;
import static com.roxiemobile.androidcommons.util.ThrowIf.throwIfNullOrNotValid;
import static com.roxiemobile.androidcommons.util.ThrowIf.throwIfNullOrWhiteSpace;
import static com.roxiemobile.androidcommons.util.ThrowIf.throwIfTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@SuppressWarnings({"CodeBlock2Expr", "ConstantConditions"})
public final class ThrowIfTests
{
// MARK: - Tests

    @Test
    public void testThrowIfTrue()
    {
        expectThrowsException("throwIfTrue", () -> {
            throwIfTrue(2 > 1);
        });

        expectNotThrowsException("throwIfTrue", () -> {
            throwIfTrue(1 > 2);
        });
    }

    @Test
    public void testThrowIfFalse()
    {
        expectThrowsException("throwIfFalse", () -> {
            throwIfFalse(1 > 2);
        });

        expectNotThrowsException("throwIfFalse", () -> {
            throwIfFalse(2 > 1);
        });
    }

// MARK: - Tests

    @Test
    public void testThrowIfEqual()
    {
        final String string = "value";
        final String nilString = null;


        expectThrowsException("throwIfEqual", () -> {
            throwIfEqual(2, 2);
        });
        expectThrowsException("throwIfEqual", () -> {
            throwIfEqual(string, string);
        });

        expectNotThrowsException("throwIfEqual", () -> {
            throwIfEqual(1, 2);
        });
        expectNotThrowsException("throwIfEqual", () -> {
            throwIfEqual(string, nilString);
        });
    }

    @Test
    public void testThrowIfNotEqual()
    {
        final String string = "value";
        final String nilString = null;


        expectThrowsException("throwIfNotEqual", () -> {
            throwIfNotEqual(1, 2);
        });
        expectThrowsException("throwIfNotEqual", () -> {
            throwIfNotEqual(string, nilString);
        });

        expectNotThrowsException("throwIfNotEqual", () -> {
            throwIfNotEqual(2, 2);
        });
        expectNotThrowsException("throwIfNotEqual", () -> {
            throwIfNotEqual(string, string);
        });
    }

// MARK: - Tests

    @Test
    public void testThrowIfNull()
    {
        expectThrowsException("throwIfNull", () -> {
            throwIfNull(null);
        });

        expectNotThrowsException("throwIfNull", () -> {
            throwIfNull(2);
        });
    }

    @Test
    public void testThrowIfNotNull()
    {
        expectThrowsException("throwIfNotNull", () -> {
            throwIfNotNull(2);
        });

        expectNotThrowsException("throwIfNotNull", () -> {
            throwIfNotNull(null);
        });
    }

// MARK: - Tests

    @Test
    public void testThrowIfNullOrEmpty()
    {
        final String string = "value";
        final String nilString = null;
        final String emptyString = "";


        expectThrowsException("throwIfNullOrEmpty", () -> {
            throwIfNullOrEmpty(nilString);
        });
        expectThrowsException("throwIfNullOrEmpty", () -> {
            throwIfNullOrEmpty(emptyString);
        });

        expectNotThrowsException("throwIfNullOrEmpty", () -> {
            throwIfNullOrEmpty(string);
        });
    }

    @Test
    public void testThrowIfNullOrEmpty_Array()
    {
        final String string = "value";
        final String nilString = null;
        final String emptyString = "";

        final String otherString = "otherValue";
        final String[] array = new String[]{string, otherString};
        final String[] nilArray = null;
        final String[] emptyArray = new String[]{};


        expectThrowsException("throwIfNullOrEmpty_Array", () -> {
            throwIfNullOrEmpty(new String[]{emptyString});
        });
        expectThrowsException("throwIfNullOrEmpty_Array", () -> {
            throwIfNullOrEmpty(new String[]{string, nilString});
        });
        expectThrowsException("throwIfNullOrEmpty_Array", () -> {
            throwIfNullOrEmpty(new String[]{string, emptyString});
        });
        expectThrowsException("throwIfNullOrEmpty_Array", () -> {
            throwIfNullOrEmpty(nilArray);
        });
        expectThrowsException("throwIfNullOrEmpty_Array", () -> {
            throwIfNullOrEmpty(emptyArray);
        });

        expectNotThrowsException("throwIfNullOrEmpty_Array", () -> {
            throwIfNullOrEmpty(array);
        });
    }

// MARK: - Tests

    @Test
    public void testThrowIfNullOrWhiteSpace()
    {
        final String string = "value";
        final String nilString = null;
        final String emptyString = "";
        final String whitespaceString = " \t\r\n";


        expectThrowsException("throwIfNullOrWhiteSpace", () -> {
            throwIfNullOrWhiteSpace(nilString);
        });
        expectThrowsException("throwIfNullOrWhiteSpace", () -> {
            throwIfNullOrWhiteSpace(emptyString);
        });
        expectThrowsException("throwIfNullOrWhiteSpace", () -> {
            throwIfNullOrWhiteSpace(whitespaceString);
        });

        expectNotThrowsException("throwIfNullOrWhiteSpace", () -> {
            throwIfNullOrWhiteSpace(string);
        });
    }

    @Test
    public void testThrowIfNullOrWhiteSpace_Array()
    {
        final String string = "value";
        final String nilString = null;
        final String emptyString = "";
        final String whitespaceString = " \t\r\n";

        final String otherString = "otherValue";
        final String[] array = new String[]{string, otherString};
        final String[] nilArray = null;
        final String[] emptyArray = new String[]{};


        expectThrowsException("throwIfNullOrWhiteSpace_Array", () -> {
            throwIfNullOrWhiteSpace(new String[]{whitespaceString});
        });
        expectThrowsException("throwIfNullOrWhiteSpace_Array", () -> {
            throwIfNullOrWhiteSpace(new String[]{string, nilString});
        });
        expectThrowsException("throwIfNullOrWhiteSpace_Array", () -> {
            throwIfNullOrWhiteSpace(new String[]{string, emptyString});
        });
        expectThrowsException("throwIfNullOrWhiteSpace_Array", () -> {
            throwIfNullOrWhiteSpace(new String[]{string, whitespaceString});
        });
        expectThrowsException("throwIfNullOrWhiteSpace_Array", () -> {
            throwIfNullOrWhiteSpace(nilArray);
        });
        expectThrowsException("throwIfNullOrWhiteSpace_Array", () -> {
            throwIfNullOrWhiteSpace(emptyArray);
        });

        expectNotThrowsException("throwIfNullOrWhiteSpace_Array", () -> {
            throwIfNullOrWhiteSpace(array);
        });
    }

// MARK: - Tests

    @Test
    public void testThrowIfNotValid()
    {
        final Validatable validObject = new ValidModel();
        final Validatable nilObject = null;
        final Validatable notValidObject = new NotValidModel();


        expectThrowsException("throwIfNotValid", () -> {
            throwIfNotValid(notValidObject);
        });

        expectNotThrowsException("throwIfNotValid", () -> {
            throwIfNotValid(validObject);
        });
        expectNotThrowsException("throwIfNotValid", () -> {
            throwIfNotValid(nilObject);
        });
    }

    @Test
    public void testThrowIfNotValid_Array()
    {
        final Validatable validObject = new ValidModel();
        final Validatable nilObject = null;
        final Validatable notValidObject = new NotValidModel();

        final Validatable[] array = new Validatable[]{validObject, nilObject};
        final Validatable[] nilArray = null;
        final Validatable[] emptyArray = new Validatable[]{};


        expectThrowsException("throwIfNotValid_Array", () -> {
            throwIfNotValid(new Validatable[]{notValidObject});
        });
        expectThrowsException("throwIfNotValid_Array", () -> {
            throwIfNotValid(nilArray);
        });

        expectNotThrowsException("throwIfNotValid_Array", () -> {
            throwIfNotValid(array);
        });
        expectNotThrowsException("throwIfNotValid_Array", () -> {
            throwIfNotValid(emptyArray);
        });
    }

// MARK: - Tests

    @Test
    public void testThrowIfNullOrNotValid()
    {
        final Validatable validObject = new ValidModel();
        final Validatable nilObject = null;
        final Validatable notValidObject = new NotValidModel();


        expectThrowsException("throwIfNullOrNotValid", () -> {
            throwIfNullOrNotValid(nilObject);
        });
        expectThrowsException("throwIfNullOrNotValid", () -> {
            throwIfNullOrNotValid(notValidObject);
        });

        expectNotThrowsException("throwIfNullOrNotValid", () -> {
            throwIfNullOrNotValid(validObject);
        });
    }

    @Test
    public void testThrowIfNullOrNotValid_Array()
    {
        final Validatable validObject = new ValidModel();
        final Validatable nilObject = null;
        final Validatable notValidObject = new NotValidModel();

        final Validatable[] array = new Validatable[]{validObject};
        final Validatable[] nilArray = null;
        final Validatable[] emptyArray = new Validatable[]{};


        expectThrowsException("throwIfNullOrNotValid_Array", () -> {
            throwIfNullOrNotValid(new Validatable[]{notValidObject});
        });
        expectThrowsException("throwIfNullOrNotValid_Array", () -> {
            throwIfNullOrNotValid(new Validatable[]{validObject, nilObject});
        });
        expectThrowsException("throwIfNullOrNotValid_Array", () -> {
            throwIfNullOrNotValid(new Validatable[]{validObject, notValidObject});
        });
        expectThrowsException("throwIfNullOrNotValid_Array", () -> {
            throwIfNullOrNotValid(nilArray);
        });

        expectNotThrowsException("throwIfNullOrNotValid_Array", () -> {
            throwIfNullOrNotValid(array);
        });
        expectNotThrowsException("throwIfNullOrNotValid_Array", () -> {
            throwIfNullOrNotValid(emptyArray);
        });
    }


// MARK: - Tests

    @Test
    public void testNotThrowIfValidModel() {
        ParkingModel parking = null;

        JsonObject jsonObject = loadJson("test_parking_model_with_valid_vehicles_in_array");
        assertNotNull(jsonObject);

        try {
            parking = DataMapper.fromJson(jsonObject, ParkingModel.class);
        }
        catch (JsonSyntaxException | ValidationException e) {
            Assert.fail("notThrowIfValidModel: Method thrown an exception");
        }
        catch (Throwable t) {
            Assert.fail("notThrowIfValidModel: Unknown exception is thrown");
        }

        assertNotNull(parking);
        assertTrue(parking.isValid());
    }

    @Test
    public void testThrowIfNotValidModel() {
        ParkingModel parking = null;

        JsonObject jsonObject = loadJson("test_parking_model_with_one_non_valid_vehicle_in_array");
        assertNotNull(jsonObject);

        try {
            parking = DataMapper.fromJson(jsonObject, ParkingModel.class);
        }
        catch (JsonSyntaxException | ValidationException e) {
            Assert.fail("throwIfNotValidModel: Method thrown an exception");
        }
        catch (Throwable t) {
            Assert.fail("throwIfNotValidModel: Unknown exception is thrown");
        }

        assertNotNull(parking);
        assertFalse(parking.isValid());
    }

// MARK: - Private Methods

     private JsonObject loadJson(String filename)
     {
         ClassLoader loader = this.getClass().getClassLoader();
         JsonObject jsonObject = null;

         try {
             InputStream in = loader.getResourceAsStream(filename + ".json");
             String jsonString = StringUtils.streamToString(in);
             jsonObject = new JsonParser().parse(jsonString).getAsJsonObject();
         }
         catch (IOException e) {
             Assert.fail("Could not load file: " + filename + ".json");
         }
         return jsonObject;
     }

// MARK: - Private Methods

    private void expectThrowsException(String method, Runnable task) {
        if (task == null) {
            throw new NullPointerException();
        }

        try {
            task.run();
            Assert.fail(method + ": Method not thrown an exception");
        }
        catch (ValidationException e) {
            // Do nothing
        }
        catch (Throwable t) {
            Assert.fail(method + ": Unknown exception is thrown");
        }
    }

    private void expectNotThrowsException(String method, Runnable task) {
        if (task == null) {
            throw new NullPointerException();
        }

        try {
            task.run();
        }
        catch (ValidationException e) {
            Assert.fail(method + ": Method thrown an exception");
        }
        catch (Throwable t) {
            Assert.fail(method + ": Unknown exception is thrown");
        }
    }

// MARK: - Inner Types

    private static class ValidModel implements Validatable {
        @Override
        public boolean isValid() {
            return true;
        }
    }

    private static class NotValidModel implements Validatable {
        @Override
        public boolean isValid() {
            return false;
        }
    }

}
