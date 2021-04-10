package com.pro_crafting.tools.recordjarconverter.rest.model;

import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.jboss.resteasy.reactive.PartType;

import javax.ws.rs.FormParam;

public class RecordJarText {
    @FormParam("text")
    @PartType("text/plain")
    @Schema(description = "Record Jar formatted text", required = true)
    private String text;

    @FormParam("encoding")
    @PartType("text/plain")
    @Schema(description = "Encoding of the specified record-jar formatted file.", example="UTF-8", defaultValue = "UTF-8")
    private String encoding;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }
}
