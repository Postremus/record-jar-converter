package com.pro_crafting.tools.recordjarconverter.rest.model;

import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.jboss.resteasy.reactive.PartType;
import org.jboss.resteasy.reactive.multipart.FileUpload;

import javax.ws.rs.FormParam;
import java.io.InputStream;

public class RecordJarFile {
    @FormParam("file")
    @PartType("application/octect-stream")
    @Schema(description = "Record Jar formatted file", required = true)
    private FileUpload file;

    @FormParam("encoding")
    @PartType("text/plain")
    @Schema(description = "Encoding of the specified record-jar formatted file.", example="UTF-8", defaultValue = "UTF-8")
    private String encoding;

    public FileUpload getFile() {
        return file;
    }

    public void setFile(FileUpload file) {
        this.file = file;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }
}
