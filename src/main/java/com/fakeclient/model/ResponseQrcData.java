package com.fakeclient.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseQrcData {

    private String code;
    private String message;
    private String result;
    private String description;
    private String transactionId;
    private com.fakeclient.model.Data data;
}
