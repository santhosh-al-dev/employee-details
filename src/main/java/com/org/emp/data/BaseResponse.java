package com.org.emp.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true) //ignore any unknown field if newly added
public class BaseResponse {

    @JsonProperty("status")
    private Boolean success;

    @JsonProperty("message")
    private String message;

    @JsonProperty("data")
    private Object data;

}
