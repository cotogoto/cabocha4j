package jp.livlog.cabocha4j.data;

import java.util.List;

public class Chunk {

    private String       id;

    private String       link;

    private String       rel;

    private String       score;

    private String       head;

    private String       func;

    private List <Token> tokenList;

    // Getter and Setter methods
    public String getId() {

        return this.id;
    }


    public void setId(final String id) {

        this.id = id;
    }


    public String getLink() {

        return this.link;
    }


    public void setLink(final String link) {

        this.link = link;
    }


    public String getRel() {

        return this.rel;
    }


    public void setRel(final String rel) {

        this.rel = rel;
    }


    public String getScore() {

        return this.score;
    }


    public void setScore(final String score) {

        this.score = score;
    }


    public String getHead() {

        return this.head;
    }


    public void setHead(final String head) {

        this.head = head;
    }


    public String getFunc() {

        return this.func;
    }


    public void setFunc(final String func) {

        this.func = func;
    }


    public List <Token> getTokenList() {

        return this.tokenList;
    }


    public void setTokenList(final List <Token> tokenList) {

        this.tokenList = tokenList;
    }
}
