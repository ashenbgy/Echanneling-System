package com.echanneling.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class EmailRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<String> emailList;

    private String emailSubject;

    private String emailBody;

    private List<byte[]> emailAttachments;

    private List<String> emailCcList;

    private List<String> emailBccList;

    @Override
    public String toString() {
        return "EmailRequest{" +
                "emailList=" + emailList +
                ", emailSubject='" + emailSubject + '\'' +
                ", emailBody='" + emailBody + '\'' +
                ", emailAttachment=" + emailAttachments +
                ", emailCcList=" + emailCcList +
                ", emailBccList=" + emailBccList +
                '}';
    }
}
