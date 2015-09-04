package com.example.penumbra.hiporam.model;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Videos {

    private LowBandwidth lowBandwidth;
    private StandardResolution_ standardResolution;
    private LowResolution_ lowResolution;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     * The lowBandwidth
     */
    public LowBandwidth getLowBandwidth() {
        return lowBandwidth;
    }

    /**
     *
     * @param lowBandwidth
     * The low_bandwidth
     */
    public void setLowBandwidth(LowBandwidth lowBandwidth) {
        this.lowBandwidth = lowBandwidth;
    }

    /**
     *
     * @return
     * The standardResolution
     */
    public StandardResolution_ getStandardResolution() {
        return standardResolution;
    }

    /**
     *
     * @param standardResolution
     * The standard_resolution
     */
    public void setStandardResolution(StandardResolution_ standardResolution) {
        this.standardResolution = standardResolution;
    }

    /**
     *
     * @return
     * The lowResolution
     */
    public LowResolution_ getLowResolution() {
        return lowResolution;
    }

    /**
     *
     * @param lowResolution
     * The low_resolution
     */
    public void setLowResolution(LowResolution_ lowResolution) {
        this.lowResolution = lowResolution;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
