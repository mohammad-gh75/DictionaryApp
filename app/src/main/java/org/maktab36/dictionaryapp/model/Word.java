package org.maktab36.dictionaryapp.model;

import java.util.UUID;

public class Word {
    private UUID mId;
    private String mEnglish;
    private String mPersian;
    private String mArabic;
    private String mFrench;

    public UUID getId() {
        return mId;
    }

    public void setId(UUID id) {
        mId = id;
    }

    public String getEnglish() {
        return mEnglish;
    }

    public void setEnglish(String english) {
        mEnglish = english;
    }

    public String getPersian() {
        return mPersian;
    }

    public void setPersian(String persian) {
        mPersian = persian;
    }

    public String getArabic() {
        return mArabic;
    }

    public void setArabic(String arabic) {
        mArabic = arabic;
    }

    public String getFrench() {
        return mFrench;
    }

    public void setFrench(String french) {
        mFrench = french;
    }

    public Word() {
        this(UUID.randomUUID(),null,null,null,null);
    }

    public Word(UUID id, String english, String persian, String arabic, String french) {
        mId = id;
        mEnglish = english;
        mPersian = persian;
        mArabic = arabic;
        mFrench = french;
    }
}
