package com.isa.pad.sunglasseswarehouse.model;

/**
 * Created by Faust on 12/18/2017.
 */
public class Sunglasses {
    private long id;
    private String brand;
    private String frameColor;
    private String frameMaterial;
    private String model;
    private Glass glass;

    private Sunglasses() {
    }

    private Sunglasses(Builder builder) {
        this.id = builder.id;
        this.brand = builder.brand;
        this.frameColor = builder.frameColor;
        this.frameMaterial = builder.frameMaterial;
        this.model = builder.model;
        this.glass = builder.glass;
    }

    public static Builder newSunglasses() {
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

    public String getFrameColor() {
        return frameColor;
    }

    public void setFrameColor(String frameColor) {
        this.frameColor = frameColor;
    }

    public String getFrameMaterial() {
        return frameMaterial;
    }

    public void setFrameMaterial(String frameMaterial) {
        this.frameMaterial = frameMaterial;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Glass getGlass() {
        return glass;
    }

    public void setGlass(Glass glass) {
        this.glass = glass;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Sunglasses that = (Sunglasses) o;

        if (getId() != that.getId()) return false;
        if (getBrand() != null ? !getBrand().equals(that.getBrand()) : that.getBrand() != null) return false;
        if (getFrameColor() != null ? !getFrameColor().equals(that.getFrameColor()) : that.getFrameColor() != null)
            return false;
        if (getFrameMaterial() != null ? !getFrameMaterial().equals(that.getFrameMaterial()) : that.getFrameMaterial() != null)
            return false;
        if (getModel() != null ? !getModel().equals(that.getModel()) : that.getModel() != null) return false;
        return getGlass() != null ? getGlass().equals(that.getGlass()) : that.getGlass() == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (getId() ^ (getId() >>> 32));
        result = 31 * result + (getBrand() != null ? getBrand().hashCode() : 0);
        result = 31 * result + (getFrameColor() != null ? getFrameColor().hashCode() : 0);
        result = 31 * result + (getFrameMaterial() != null ? getFrameMaterial().hashCode() : 0);
        result = 31 * result + (getModel() != null ? getModel().hashCode() : 0);
        result = 31 * result + (getGlass() != null ? getGlass().hashCode() : 0);
        return result;
    }


    public static final class Builder {
        private long id;
        private String brand;
        private String frameColor;
        private String frameMaterial;
        private String model;
        private Glass glass;

        private Builder() {
        }

        public Sunglasses build() {
            return new Sunglasses(this);
        }

        public Builder id(long id) {
            this.id = id;
            return this;
        }

        public Builder brand(String brand) {
            this.brand = brand;
            return this;
        }

        public Builder frameColor(String frameColor) {
            this.frameColor = frameColor;
            return this;
        }

        public Builder frameMaterial(String frameMaterial) {
            this.frameMaterial = frameMaterial;
            return this;
        }

        public Builder model(String model) {
            this.model = model;
            return this;
        }

        public Builder glass(Glass glass) {
            this.glass = glass;
            return this;
        }
    }

    @Override
    public String toString() {
        return "Sunglasses{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", frameColor='" + frameColor + '\'' +
                ", frameMaterial='" + frameMaterial + '\'' +
                ", model='" + model + '\'' +
                ", glass=" + glass +
                '}';
    }
}
