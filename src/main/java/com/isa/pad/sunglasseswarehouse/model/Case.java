package com.isa.pad.sunglasseswarehouse.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Faust on 12/18/2017.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder("case")
@XmlRootElement
public class Case {
    @JsonProperty("id")
    private long id;
    @JsonProperty("brand")
    private String brand;
    @JsonProperty("color")
    private String color;
    @JsonProperty("material")
    private String material;
    @JsonProperty("model")
    private String model;

    public Case() {
    }

    private Case(Builder builder) {
        this.id = builder.id;
        this.brand = builder.brand;
        this.color = builder.color;
        this.material = builder.material;
        this.model = builder.model;
    }

    public static Builder newCase() {
        return new Builder();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Case aCase = (Case) o;

        if (getId() != aCase.getId()) return false;
        if (getBrand() != null ? !getBrand().equals(aCase.getBrand()) : aCase.getBrand() != null) return false;
        if (getColor() != null ? !getColor().equals(aCase.getColor()) : aCase.getColor() != null) return false;
        if (getMaterial() != null ? !getMaterial().equals(aCase.getMaterial()) : aCase.getMaterial() != null)
            return false;
        return getModel() != null ? getModel().equals(aCase.getModel()) : aCase.getModel() == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (getId() ^ (getId() >>> 32));
        result = 31 * result + (getBrand() != null ? getBrand().hashCode() : 0);
        result = 31 * result + (getColor() != null ? getColor().hashCode() : 0);
        result = 31 * result + (getMaterial() != null ? getMaterial().hashCode() : 0);
        result = 31 * result + (getModel() != null ? getModel().hashCode() : 0);
        return result;
    }


    public static final class Builder {
        private long id;
        private String brand;
        private String color;
        private String material;
        private String model;

        private Builder() {
        }

        public Case build() {
            return new Case(this);
        }

        public Builder id(long id) {
            this.id = id;
            return this;
        }

        public Builder brand(String brand) {
            this.brand = brand;
            return this;
        }

        public Builder color(String color) {
            this.color = color;
            return this;
        }

        public Builder material(String material) {
            this.material = material;
            return this;
        }

        public Builder model(String model) {
            this.model = model;
            return this;
        }
    }

    @Override
    public String toString() {
        return "Case{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", color='" + color + '\'' +
                ", material='" + material + '\'' +
                ", model='" + model + '\'' +
                '}';
    }
}
