package il.co.amits.proteintracker.food;

import android.graphics.Picture;

/**
 * Created by Administrator on 19/03/2016.
 *
 * FOOD item:
 * has: Name,shortDescription , ProteinIn100Gram , Picture
 *
 *
 *
 */
public class FoodItem {

    public String mName;
    public String mShortDesc;
    public double mProteinIn100g;
    public String mPicUrl;

    public FoodItem(String Name, String ShortDesc, double ProteinIn100g, String PicUrl) {
        this.mName = Name;
        this.mShortDesc = ShortDesc;
        this.mProteinIn100g =ProteinIn100g;
        this.mPicUrl = PicUrl;
    }
    public FoodItem(String Name, double ProteinIn100g) {
        this.mName = Name;
        this.mShortDesc = "";
        this.mProteinIn100g = ProteinIn100g;
        this.mPicUrl = "";
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public String getmShortDesc() {
        return mShortDesc;
    }

    public void setmShortDesc(String mShortDesc) {
        this.mShortDesc = mShortDesc;
    }

    public String getmPicUrl() {
        return mPicUrl;
    }

    public void setmPicUrl(String mPicUrl) {
        this.mPicUrl = mPicUrl;
    }

    public double getmProteinIn100g() {
        return mProteinIn100g;
    }

    public void setmProteinIn100g(double mProteinIn100g) {
        this.mProteinIn100g = mProteinIn100g;
    }
}
