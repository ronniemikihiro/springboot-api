package br.com.springboot.api.util;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Error {

    private String msgUser;
    private String msgDev;

    public Error(String msgUser, String msgDev) {
        this.msgUser = msgUser;
        this.msgDev = msgDev;
    }

}
