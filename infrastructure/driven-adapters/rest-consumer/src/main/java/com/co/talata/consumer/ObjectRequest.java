package com.co.talata.consumer;

    import lombok.Builder;
    import lombok.Data;

    @Data
    @Builder(toBuilder = true)
public class ObjectRequest {

private String val1;
private String val2;

}
