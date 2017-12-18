package com.isa.pad.sunglasseswarehouse.model;

/**
 * Created by Faust on 12/18/2017.
 */
public class Glass {
    private String material;
    private String color;

    public Glass() {
    }

    public Glass(String material, String color) {
        this.material = material;
        this.color = color;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Glass glass = (Glass) o;

        if (getMaterial() != null ? !getMaterial().equals(glass.getMaterial()) : glass.getMaterial() != null)
            return false;
        return getColor() != null ? getColor().equals(glass.getColor()) : glass.getColor() == null;

    }

    @Override
    public int hashCode() {
        int result = getMaterial() != null ? getMaterial().hashCode() : 0;
        result = 31 * result + (getColor() != null ? getColor().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Glass{" +
                "material='" + material + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}
