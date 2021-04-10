package com.pro_crafting.tools.recordjarconverter.rest;

import com.pro_crafting.tools.recordjarconverter.IntegrationTestBase;
import com.pro_crafting.tools.recordjarconverter.RestApplication;
import io.restassured.builder.MultiPartSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import static io.restassured.RestAssured.given;
import static org.apache.commons.io.IOUtils.resourceToByteArray;
import static org.apache.commons.io.IOUtils.resourceToString;
import static org.junit.jupiter.api.Assertions.assertEquals;

class RecordJarResourceIT extends IntegrationTestBase {

    private static final String MULTIPART_FORM_FILE_PATH = RestApplication.VERSION_PATH + RecordJarResource.RESOURCE_PATH + "multipart/file";
    private static final String MULTIPART_FORM_TEXT_PATH = RestApplication.VERSION_PATH + RecordJarResource.RESOURCE_PATH + "multipart/text";
    private static final String TEXT_PATH = RestApplication.VERSION_PATH + RecordJarResource.RESOURCE_PATH + "text";

    @Test
    void testUploadMultipartFile() throws IOException {
        byte[] bytes = resourceToByteArray("taoup-rj-example.rj", getClass().getClassLoader());
        String expected = resourceToString("taoup-rj-converted.json", StandardCharsets.UTF_8, getClass().getClassLoader());
        Response response = given()
                .multiPart("file", "language-subtag-registry.rj", bytes)
                .multiPart("encoding", "UTF-8")
                .expect()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .when()
                .post(MULTIPART_FORM_FILE_PATH);

        assertEquals(expected, response.asString());
    }

    @Test
    void testUploadMultipartFileLarge() throws IOException {
        byte[] bytes = resourceToByteArray("language-subtag-registry.rj", getClass().getClassLoader());
        String expected = resourceToString("language-subtag-registry-converted.json", StandardCharsets.UTF_8, getClass().getClassLoader());
        Response response = given()
                .multiPart("file", "language-subtag-registry.rj", bytes)
                .multiPart("encoding", "UTF-8")
                .expect()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .when()
                .post(MULTIPART_FORM_FILE_PATH);

        assertEquals(expected, response.asString());
    }

    @Test
    void testUploadMultipartText() throws IOException {
        String body = resourceToString("taoup-rj-example.rj", StandardCharsets.UTF_8, getClass().getClassLoader());
        String expected = resourceToString("taoup-rj-converted.json", StandardCharsets.UTF_8, getClass().getClassLoader());
        Response response = given()
                .multiPart("text", body)
                .multiPart("encoding", "UTF-8")
                .expect()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .when()
                .post(MULTIPART_FORM_TEXT_PATH);

        assertEquals(expected, response.asString());
    }

    @Test
    void testUploadMultipartTextLarge() throws IOException {
        String body = resourceToString("language-subtag-registry.rj", StandardCharsets.UTF_8, getClass().getClassLoader());
        String expected = resourceToString("language-subtag-registry-converted.json", StandardCharsets.UTF_8, getClass().getClassLoader());
        Response response = given()
                .multiPart("text", body)
                .multiPart("encoding", "UTF-8")
                .expect()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .when()
                .post(MULTIPART_FORM_TEXT_PATH);

        assertEquals(expected, response.asString());
    }

    @Test
    void testUploadText() throws IOException {
        String body = resourceToString("taoup-rj-example.rj", StandardCharsets.UTF_8, getClass().getClassLoader());
        String expected = resourceToString("taoup-rj-converted.json", StandardCharsets.UTF_8, getClass().getClassLoader());
        Response response = given()
                .body(body)
                .queryParam("encoding", "UTF-8")
                .expect()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .when()
                .post(TEXT_PATH);

        assertEquals(expected, response.asString());
    }

    @Test
    void testUploadTextLarge() throws IOException {
        String body = resourceToString("language-subtag-registry.rj", StandardCharsets.UTF_8, getClass().getClassLoader());
        String expected = resourceToString("language-subtag-registry-converted.json", StandardCharsets.UTF_8, getClass().getClassLoader());
        Response response = given()
                .body(body)
                .contentType("text/plain; charset=UTF-8")
                .queryParam("encoding", "UTF-8")
                .expect()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .when()
                .post(TEXT_PATH);

        assertEquals(expected, response.asString());
    }
}