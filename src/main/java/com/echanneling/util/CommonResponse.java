package com.echanneling.util;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CommonResponse {

    private Object payload = null;
    private List<String> messages = new ArrayList<>();
    private boolean status = false;

}
