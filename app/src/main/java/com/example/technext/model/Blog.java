package com.example.technext.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.technext.database.AuthorConverter;
import com.example.technext.database.CategoryTypeConverter;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Anik Roy on 4/8/2021.
 */
@Entity(tableName = "Blog")
public class Blog implements Parcelable {

    @SerializedName("id")
    @Expose
    @PrimaryKey(autoGenerate = true)
    private Integer id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("cover_photo")
    @Expose
    private String coverPhoto;
    @SerializedName("categories")
    @Expose
    @TypeConverters(CategoryTypeConverter.class)
    private List<String> categories = null;
    @SerializedName("author")
    @Expose
    @TypeConverters(AuthorConverter.class)
    private Author author;

    public Blog() {
    }

    public Blog(String title, String description, String coverPhoto, List<String> categories, Author author) {
        this.title = title;
        this.description = description;
        this.coverPhoto = coverPhoto;
        this.categories = categories;
        this.author = author;
    }

    protected Blog(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        title = in.readString();
        description = in.readString();
        coverPhoto = in.readString();
        categories = in.createStringArrayList();
    }

    public static final Creator<Blog> CREATOR = new Creator<Blog>() {
        @Override
        public Blog createFromParcel(Parcel in) {
            return new Blog(in);
        }

        @Override
        public Blog[] newArray(int size) {
            return new Blog[size];
        }
    };

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCoverPhoto() {
        return coverPhoto;
    }

    public void setCoverPhoto(String coverPhoto) {
        this.coverPhoto = coverPhoto;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", coverPhoto='" + coverPhoto + '\'' +
                ", categories=" + categories +
                ", author=" + author +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(id);
        }
        dest.writeString(title);
        dest.writeString(description);
        dest.writeString(coverPhoto);
        dest.writeStringList(categories);
    }
}
