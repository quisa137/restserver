package com.jindata.restserver.apis.beans;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

@XmlRootElement(name="Information")
@Data public class Information {
    private String name;
    private String version;
    private String description;
}
