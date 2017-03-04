package com.roxiemobile.androidcommons.data.model;

import com.google.gson.annotations.SerializedName;

import static com.roxiemobile.androidcommons.diagnostics.Expect.expectAllValid;
import static com.roxiemobile.androidcommons.diagnostics.Expect.expectNotBlank;
import static com.roxiemobile.androidcommons.diagnostics.Expect.expectNotEmpty;

public class ParkingModel extends ValidatableModel
{
    @SerializedName("watcher")
    public String mWatcher;

    @SerializedName("vehicles")
    public VehicleModel[] mVehicles;

    @Override
    public void validate() {
        super.validate();

        // Validate instance variables
        expectNotBlank(mWatcher);
        expectNotEmpty(mVehicles);
        expectAllValid(mVehicles);
    }
}
