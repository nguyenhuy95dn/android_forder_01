package com.framgia.forder.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import io.realm.annotations.PrimaryKey;

/**
 * Created by le.quang.dao on 10/03/2017.
 */

public class User extends BaseModel implements Parcelable {

    @SerializedName("login")
    @Expose
    @PrimaryKey
    private String mLogin;
    @SerializedName("id")
    @Expose
    private Integer mId;
    @SerializedName("avatar_url")
    @Expose
    private String mAvatarUrl;
    @SerializedName("gravatar_id")
    @Expose
    private String mGravatarId;
    @SerializedName("url")
    @Expose
    private String mUrl;
    @SerializedName("html_url")
    @Expose
    private String mHtmlUrl;
    @SerializedName("followers_url")
    @Expose
    private String mFollowersUrl;
    @SerializedName("following_url")
    @Expose
    private String mFollowingUrl;
    @SerializedName("gists_url")
    @Expose
    private String mGistsUrl;
    @SerializedName("starred_url")
    @Expose
    private String mStarredUrl;
    @SerializedName("subscriptions_url")
    @Expose
    private String mSubscriptionsUrl;
    @SerializedName("organizations_url")
    @Expose
    private String mOrganizationsUrl;
    @SerializedName("repos_url")
    @Expose
    private String mReposUrl;
    @SerializedName("events_url")
    @Expose
    private String mEventsUrl;
    @SerializedName("received_events_url")
    @Expose
    private String mReceivedEventsUrl;
    @SerializedName("type")
    @Expose
    private String mType;
    @SerializedName("site_admin")
    @Expose
    private Boolean siteAdmin;
    @SerializedName("name")
    @Expose
    private String mName;
    @SerializedName("company")
    @Expose
    private String mCompany;
    @SerializedName("blog")
    @Expose
    private String mBlog;
    @SerializedName("location")
    @Expose
    private String mLocation;
    @SerializedName("email")
    @Expose
    private String mEmail;
    @SerializedName("hireable")
    @Expose
    private String mHireable;
    @SerializedName("bio")
    @Expose
    private String mBio;
    @SerializedName("public_repos")
    @Expose
    private Integer mBublicRepos;
    @SerializedName("public_gists")
    @Expose
    private Integer mPublicGists;
    @SerializedName("followers")
    @Expose
    private Integer mFollowers;
    @SerializedName("following")
    @Expose
    private Integer mFollowing;
    @SerializedName("created_at")
    @Expose
    private String mCreatedAt;
    @SerializedName("updated_at")
    @Expose
    private String mUpdatedAt;
    @SerializedName("authentication_token")
    @Expose
    private String mToken;

    public User() {
    }

    public User(final String userLogin, final String name, final String avatarUrl,
            final String bio) {
        mLogin = userLogin;
        mName = name;
        mAvatarUrl = avatarUrl;
        mBio = bio;
    }

    public User(final String userLogin) {
        mLogin = userLogin;
    }

    protected User(Parcel in) {
        mLogin = in.readString();
        mAvatarUrl = in.readString();
        mGravatarId = in.readString();
        mUrl = in.readString();
        mHtmlUrl = in.readString();
        mFollowersUrl = in.readString();
        mFollowingUrl = in.readString();
        mGistsUrl = in.readString();
        mStarredUrl = in.readString();
        mSubscriptionsUrl = in.readString();
        mOrganizationsUrl = in.readString();
        mReposUrl = in.readString();
        mEventsUrl = in.readString();
        mReceivedEventsUrl = in.readString();
        mType = in.readString();
        mName = in.readString();
        mCompany = in.readString();
        mBlog = in.readString();
        mLocation = in.readString();
        mEmail = in.readString();
        mHireable = in.readString();
        mBio = in.readString();
        mCreatedAt = in.readString();
        mUpdatedAt = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public String getLogin() {
        return mLogin;
    }

    public void setLogin(String login) {
        mLogin = login;
    }

    public Integer getId() {
        return mId;
    }

    public void setId(Integer id) {
        mId = id;
    }

    public String getAvatarUrl() {
        return mAvatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        mAvatarUrl = avatarUrl;
    }

    public String getGravatarId() {
        return mGravatarId;
    }

    public void setGravatarId(String gravatarId) {
        mGravatarId = gravatarId;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }

    public String getHtmlUrl() {
        return mHtmlUrl;
    }

    public void setHtmlUrl(String htmlUrl) {
        mHtmlUrl = htmlUrl;
    }

    public String getFollowersUrl() {
        return mFollowersUrl;
    }

    public void setFollowersUrl(String followersUrl) {
        mFollowersUrl = followersUrl;
    }

    public String getFollowingUrl() {
        return mFollowingUrl;
    }

    public void setFollowingUrl(String followingUrl) {
        mFollowingUrl = followingUrl;
    }

    public String getGistsUrl() {
        return mGistsUrl;
    }

    public void setGistsUrl(String gistsUrl) {
        mGistsUrl = gistsUrl;
    }

    public String getStarredUrl() {
        return mStarredUrl;
    }

    public void setStarredUrl(String starredUrl) {
        mStarredUrl = starredUrl;
    }

    public String getSubscriptionsUrl() {
        return mSubscriptionsUrl;
    }

    public void setSubscriptionsUrl(String subscriptionsUrl) {
        mSubscriptionsUrl = subscriptionsUrl;
    }

    public String getOrganizationsUrl() {
        return mOrganizationsUrl;
    }

    public void setOrganizationsUrl(String organizationsUrl) {
        mOrganizationsUrl = organizationsUrl;
    }

    public String getReposUrl() {
        return mReposUrl;
    }

    public void setReposUrl(String reposUrl) {
        mReposUrl = reposUrl;
    }

    public String getEventsUrl() {
        return mEventsUrl;
    }

    public void setEventsUrl(String eventsUrl) {
        mEventsUrl = eventsUrl;
    }

    public String getReceivedEventsUrl() {
        return mReceivedEventsUrl;
    }

    public void setReceivedEventsUrl(String receivedEventsUrl) {
        mReceivedEventsUrl = receivedEventsUrl;
    }

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        mType = type;
    }

    public Boolean getSiteAdmin() {
        return siteAdmin;
    }

    public void setSiteAdmin(Boolean siteAdmin) {
        this.siteAdmin = siteAdmin;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getCompany() {
        return mCompany;
    }

    public void setCompany(String company) {
        mCompany = company;
    }

    public String getBlog() {
        return mBlog;
    }

    public void setBlog(String blog) {
        mBlog = blog;
    }

    public String getLocation() {
        return mLocation;
    }

    public void setLocation(String location) {
        mLocation = location;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    public String getHireable() {
        return mHireable;
    }

    public void setHireable(String hireable) {
        mHireable = hireable;
    }

    public String getBio() {
        return mBio;
    }

    public void setBio(String bio) {
        mBio = bio;
    }

    public Integer getBublicRepos() {
        return mBublicRepos;
    }

    public void setBublicRepos(Integer bublicRepos) {
        mBublicRepos = bublicRepos;
    }

    public Integer getPublicGists() {
        return mPublicGists;
    }

    public void setPublicGists(Integer publicGists) {
        mPublicGists = publicGists;
    }

    public Integer getFollowers() {
        return mFollowers;
    }

    public void setFollowers(Integer followers) {
        mFollowers = followers;
    }

    public Integer getFollowing() {
        return mFollowing;
    }

    public void setFollowing(Integer following) {
        mFollowing = following;
    }

    public String getCreatedAt() {
        return mCreatedAt;
    }

    public void setCreatedAt(String createdAt) {
        mCreatedAt = createdAt;
    }

    public String getUpdatedAt() {
        return mUpdatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        mUpdatedAt = updatedAt;
    }

    public String getToken() {
        return mToken;
    }

    public void setToken(String token) {
        mToken = token;
    }

    public static Creator<User> getCREATOR() {
        return CREATOR;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mLogin);
        dest.writeString(mAvatarUrl);
        dest.writeString(mGravatarId);
        dest.writeString(mUrl);
        dest.writeString(mHtmlUrl);
        dest.writeString(mFollowersUrl);
        dest.writeString(mFollowingUrl);
        dest.writeString(mGistsUrl);
        dest.writeString(mStarredUrl);
        dest.writeString(mSubscriptionsUrl);
        dest.writeString(mOrganizationsUrl);
        dest.writeString(mReposUrl);
        dest.writeString(mEventsUrl);
        dest.writeString(mReceivedEventsUrl);
        dest.writeString(mType);
        dest.writeString(mName);
        dest.writeString(mCompany);
        dest.writeString(mBlog);
        dest.writeString(mLocation);
        dest.writeString(mEmail);
        dest.writeString(mHireable);
        dest.writeString(mBio);
        dest.writeString(mCreatedAt);
        dest.writeString(mUpdatedAt);
    }
}
