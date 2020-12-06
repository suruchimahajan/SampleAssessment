package com.techmahindra.sampletest.model;

public class AlbumData {

    int albumId;            // album id
    int id;                 // photo id
    String title;           // image title
    String url;             // image url
    String thumbnailUrl;    // image thumbnail url


    public AlbumData(int albumId, int id, String t, String url, String tburl)
    {
        this.albumId=albumId;
        this.id=id;
        this.title=t;
        this.url=url;
        this.thumbnailUrl=tburl;

    }


    public int getAlbumId() {
        return albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }


}




