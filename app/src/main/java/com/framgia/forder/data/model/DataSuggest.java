package com.framgia.forder.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion;
import io.realm.RealmObject;

/**
 * Created by Age on 4/27/2017.
 */

@SuppressWarnings("DefaultFileTemplate")
public class DataSuggest extends RealmObject implements SearchSuggestion, Parcelable {
    private boolean mIsHistory = false;
    private String mName;
    private int mDomainId;

    public DataSuggest() {
    }

    public DataSuggest(int domainId, String name) {
        mDomainId = domainId;
        mName = name;
    }

    private DataSuggest(Parcel in) {
        mDomainId = in.readInt();
        mName = in.readString();
    }

    public static final Parcelable.Creator<DataSuggest> CREATOR =
            new Parcelable.Creator<DataSuggest>() {
                public DataSuggest createFromParcel(Parcel in) {
                    return new DataSuggest(in);
                }

                public DataSuggest[] newArray(int size) {
                    return new DataSuggest[size];
                }
            };

    public DataSuggest(String name) {
        mName = name;
    }

    public boolean isHistory() {
        return mIsHistory;
    }

    public void setHistory(boolean isHistory) {
        mIsHistory = isHistory;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public int getDomainId() {
        return mDomainId;
    }

    public void setDomainId(int domainId) {
        mDomainId = domainId;
    }

    @Override
    public String getBody() {
        return mName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mDomainId);
        dest.writeString(mName);
    }
}
