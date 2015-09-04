package com.example.penumbra.hiporam.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;
import com.example.penumbra.hiporam.model.Datum;
import com.example.penumbra.hiporam.model.Pagination;
import com.example.penumbra.hiporam.model.Meta;

@Generated("org.jsonschema2pojo")
public class InstagramItem {

    private Pagination pagination;
    private Meta meta;
    private List<Datum> data = new ArrayList<Datum>();
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     * The pagination
     */
    public Pagination getPagination() {
        return pagination;
    }

    /**
     *
     * @param pagination
     * The pagination
     */
    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }

    /**
     *
     * @return
     * The meta
     */
    public Meta getMeta() {
        return meta;
    }

    /**
     *
     * @param meta
     * The meta
     */
    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    /**
     *
     * @return
     * The data
     */
    public List<Datum> getData() {
        return data;
    }

    /**
     *
     * @param data
     * The data
     */
    public void setData(List<Datum> data) {
        this.data = data;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}

