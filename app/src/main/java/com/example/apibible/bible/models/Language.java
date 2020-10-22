package com.example.apibible.bible.models;

import java.util.HashMap;
import java.util.Map;


public class Language {

    private String id;
    private String name;
    private String nameLocal;
    private String script;
    private String scriptDirection;
    private Map<String, Object> additionalProperties = new HashMap<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getScript() {
        return script;
    }

    public void setScript(String script) {
        this.script = script;
    }

    public String getScriptDirection() {
        return scriptDirection;
    }

    public void setScriptDirection(String scriptDirection) {
        this.scriptDirection = scriptDirection;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}