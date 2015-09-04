package com.example.penumbra.hiporam.model;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Pagination {

    private String nextMaxTagId;
    private String deprecationWarning;
    private String nextMaxId;
    private String nextMinId;
    private String minTagId;
    private String nextUrl;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     * The nextMaxTagId
     */
    public String getNextMaxTagId() {
        return nextMaxTagId;
    }

    /**
     *
     * @param nextMaxTagId
     * The next_max_tag_id
     */
    public void setNextMaxTagId(String nextMaxTagId) {
        this.nextMaxTagId = nextMaxTagId;
    }

    /**
     *
     * @return
     * The deprecationWarning
     */
    public String getDeprecationWarning() {
        return deprecationWarning;
    }

    /**
     *
     * @param deprecationWarning
     * The deprecation_warning
     */
    public void setDeprecationWarning(String deprecationWarning) {
        this.deprecationWarning = deprecationWarning;
    }

    /**
     *
     * @return
     * The nextMaxId
     */
    public String getNextMaxId() {
        return nextMaxId;
    }

    /**
     *
     * @param nextMaxId
     * The next_max_id
     */
    public void setNextMaxId(String nextMaxId) {
        this.nextMaxId = nextMaxId;
    }

    /**
     *
     * @return
     * The nextMinId
     */
    public String getNextMinId() {
        return nextMinId;
    }

    /**
     *
     * @param nextMinId
     * The next_min_id
     */
    public void setNextMinId(String nextMinId) {
        this.nextMinId = nextMinId;
    }

    /**
     *
     * @return
     * The minTagId
     */
    public String getMinTagId() {
        return minTagId;
    }

    /**
     *
     * @param minTagId
     * The min_tag_id
     */
    public void setMinTagId(String minTagId) {
        this.minTagId = minTagId;
    }

    /**
     *
     * @return
     * The nextUrl
     */
    public String getNextUrl() {
        return nextUrl;
    }

    /**
     *
     * @param nextUrl
     * The next_url
     */
    public void setNextUrl(String nextUrl) {
        this.nextUrl = nextUrl;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
