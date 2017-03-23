package lixiaoxiao.bwie.com.newstitlelixiaoxiao.bean;

/**
 * 1. 类的用途
 * 2. @author : do  you  heat
 * 3. @date 2017/3/23 08:39
 */
public class News {
    int   id;
    String  name;
    String  url;
    String  imageurl;

    public News(int id, String name, String url, String imageurl) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.imageurl = imageurl;
    }

    public News(String name, String url, String imageurl) {
        this.name = name;
        this.url = url;
        this.imageurl = imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getImageurl() {
        return imageurl;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "News{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", imageurl='" + imageurl + '\'' +
                '}';
    }
}
