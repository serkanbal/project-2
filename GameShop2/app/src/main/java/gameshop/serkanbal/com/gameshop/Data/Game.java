package gameshop.serkanbal.com.gameshop.Data;

/**
 * Created by Serkan on 31/10/16.
 */

public class Game {
    private String mName, mDescription, mPlatform, mAvailability, mCompany;
    private double mRating, mPrice;
    private int mIdDetail;

    public Game(String name, String description, String company, String platform,
                String availability, double rating, double price, int idDetail) {
        mName = name;
        mDescription = description;
        mPlatform = platform;
        mAvailability = availability;
        mRating = rating;
        mPrice = price;
        mCompany = company;
        mIdDetail = idDetail;
    }

    public String getName() {
        return mName;
    }

    public String getDescription() {
        return mDescription;
    }

    public String getPlatform() {
        return mPlatform;
    }

    public String getAvailability() {
        return mAvailability;
    }

    public double getRating() {
        return mRating;
    }

    public double getPrice() {
        return mPrice;
    }

    public String getCompany() {
        return mCompany;
    }

    public int getIdDetail() {
        return mIdDetail;
    }
}
