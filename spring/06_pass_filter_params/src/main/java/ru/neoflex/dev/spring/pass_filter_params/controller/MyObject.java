package ru.neoflex.dev.spring.pass_filter_params.controller;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class MyObject {

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") // add @JsonFormat annotation //
    private LocalDateTime date;

    @JsonProperty("my_name") // add @JsonProperty annotation //
    private String otherValue;

    @JsonProperty("very_other_value") // add @JsonProperty annotation //
    private String veryOtherValue;

    public MyObject() {}

    public LocalDateTime getDate() {
        return date;
    }

    public String getOtherValue() {
        return otherValue;
    }

    public String getVeryOtherValue() {
        return veryOtherValue;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public void setOtherValue(String otherValue) {
        this.otherValue = otherValue;
    }

    public void setVeryOtherValue(String veryOtherValue) {
        this.veryOtherValue = veryOtherValue;
    }
}

