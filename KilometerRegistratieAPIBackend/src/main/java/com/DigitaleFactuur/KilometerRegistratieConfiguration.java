package com.DigitaleFactuur;

import io.dropwizard.Configuration;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.db.DataSourceFactory;
import javax.validation.Valid;
import javax.validation.constraints.*;

class KilometerRegistratieConfiguration extends Configuration {

    @NotNull
    @Valid
    private DataSourceFactory dataSourceFactory
            = new DataSourceFactory();

    @JsonProperty("database")
    DataSourceFactory getDataSourceFactory() {
        return dataSourceFactory;
    }
}
