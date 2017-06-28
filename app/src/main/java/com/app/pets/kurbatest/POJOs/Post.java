package com.app.pets.kurbatest.POJOs;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Comparator;

/**
 * Created by macbookpro on 6/27/17.
 */

public class Post implements Parcelable,Comparator<Post>{

    /**
     * userId : 1
     * id : 1
     * title : sunt aut facere repellat provident occaecati excepturi optio reprehenderit
     * body : quia et suscipit
     suscipit recusandae consequuntur expedita et cum
     reprehenderit molestiae ut ut quas totam
     nostrum rerum est autem sunt rem eveniet architecto
     */

    private int userId;
    private int id;
    private String title;
    private String body;

    protected Post(Parcel in) {
        userId = in.readInt();
        id = in.readInt();
        title = in.readString();
        body = in.readString();
    }

    public static final Creator<Post> CREATOR = new Creator<Post>() {
        @Override
        public Post createFromParcel(Parcel in) {
            return new Post(in);
        }

        @Override
        public Post[] newArray(int size) {
            return new Post[size];
        }
    };

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(userId);
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(body);
    }



    @Override
    public int compare(Post o1, Post o2) {
        return o1.getTitle().compareTo(o2.getTitle());
    }
}
