package model;

/**
 * @Description:
 * @Author:
 * @Date:
 **/
public class Picture {

    private String name;

    private String path;

    private String oid;

    public String getName() {
        return name;
    }

    public Picture setName(String name) {
        this.name = name;
        return this;
    }

    public String getPath() {
        return path;
    }

    public Picture setPath(String path) {
        this.path = path;
        return this;
    }

    public String getOid() {
        return oid;
    }

    public Picture setOid(String oid) {
        this.oid = oid;
        return this;
    }
}
