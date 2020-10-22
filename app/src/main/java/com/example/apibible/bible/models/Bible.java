package com.example.apibible.bible.models;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Bible {

    private String id;
    private String dblId;
    private Object relatedDbl;
    private String name;
    private String nameLocal;
    private String abbreviation;
    private String abbreviationLocal;
    private String description;
    private String descriptionLocal;
    private Language language;
    private List<Country> countries = null;
    private String type;
    private String updatedAt;
    private String copyright;
    private String info;
    private List<Object> audioBibles = null;
    private Map<String, Object> additionalProperties = new HashMap<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDblId() {
        return dblId;
    }

    public void setDblId(String dblId) {
        this.dblId = dblId;
    }

    public Object getRelatedDbl() {
        return relatedDbl;
    }

    public void setRelatedDbl(Object relatedDbl) {
        this.relatedDbl = relatedDbl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameLocal() {
        return nameLocal;
    }

    public void setNameLocal(String nameLocal) {
        this.nameLocal = nameLocal;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getAbbreviationLocal() {
        return abbreviationLocal;
    }

    public void setAbbreviationLocal(String abbreviationLocal) {
        this.abbreviationLocal = abbreviationLocal;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescriptionLocal() {
        return descriptionLocal;
    }

    public void setDescriptionLocal(String descriptionLocal) {
        this.descriptionLocal = descriptionLocal;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public List<Country> getCountries() {
        return countries;
    }

    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public List<Object> getAudioBibles() {
        return audioBibles;
    }

    public void setAudioBibles(List<Object> audioBibles) {
        this.audioBibles = audioBibles;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
