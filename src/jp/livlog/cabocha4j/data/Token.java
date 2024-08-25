package jp.livlog.cabocha4j.data;

public class Token {

    private String id;

    private String feature;

    private String ne;      // Named Entity

    private String surface; // Original text

    // Getter and Setter methods
    public String getId() {

        return this.id;
    }


    public void setId(final String id) {

        this.id = id;
    }


    public String getFeature() {

        return this.feature;
    }


    public void setFeature(final String feature) {

        this.feature = feature;
    }


    public String getNe() {

        return this.ne;
    }


    public void setNe(final String ne) {

        this.ne = ne;
    }


    public String getSurface() {

        return this.surface;
    }


    public void setSurface(final String surface) {

        this.surface = surface;
    }
}
