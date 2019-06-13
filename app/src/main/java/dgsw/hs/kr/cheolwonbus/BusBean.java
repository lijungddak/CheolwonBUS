package dgsw.hs.kr.cheolwonbus;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class BusBean implements Serializable{
    private int sequenceNumber;
    private String routeId;
    private String startStation;
    private String firstViaStation;
    private String returnStation;
    private String secondViaStation;
    private String endStation;
    private String remainTime;
    private String nextTime;
    private String route;
    private String type;
    private ArrayList<String> times = new ArrayList<>();
    private boolean isFavorite;

    public BusBean(String routeId, String startStation, String firstViaStation, String returnStation, String secondViaStation, String endStation, String route, String type) {
        this.routeId = routeId;
        this.startStation = startStation;
        this.firstViaStation = firstViaStation;
        this.returnStation = returnStation;
        this.secondViaStation = secondViaStation;
        this.endStation = endStation;
        this.route = route;
        this.type = type;
    }

 /*   protected BusBean(Parcel in) {
        routeId = in.readString();
        startStation = in.readString();
        firstViaStation = in.readString();
        returnStation = in.readString();
        secondViaStation = in.readString();
        endStation = in.readString();
        remainTime = in.readString();
        nextTime = in.readString();
        route = in.readString();
        type = in.readString();
        times = in.createStringArrayList();
        isFavorite = in.readByte() != 0;
    }

    @SuppressWarnings("rawtypes")
    public static final Creator<BusBean> CREATOR = new Creator<BusBean>() {
        @Override
        public BusBean createFromParcel(Parcel in) {
            return new BusBean(in);
        }

        @Override
        public BusBean[] newArray(int size) {
            return new BusBean[size];
        }
    };*/

    public int getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(int sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    public String getRouteId() {
        return routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }

    public String getStartStation() {
        return startStation;
    }

    public void setStartStation(String startStation) {
        this.startStation = startStation;
    }

    public String getFirstViaStation() {
        return firstViaStation;
    }

    public void setFirstViaStation(String firstViaStation) {
        this.firstViaStation = firstViaStation;
    }

    public String getReturnStation() {
        return returnStation;
    }

    public void setReturnStation(String returnStation) {
        this.returnStation = returnStation;
    }

    public String getSecondViaStation() {
        return secondViaStation;
    }

    public void setSecondViaStation(String secondViaStation) {
        this.secondViaStation = secondViaStation;
    }

    public String getEndStation() {
        return endStation;
    }

    public void setEndStation(String endStation) {
        this.endStation = endStation;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ArrayList<String> getTimes() {
        return times;
    }

    public void setTimes(ArrayList<String> times) {
        this.times = times;
    }

    public String getRemainTime() {
        return remainTime;
    }

    public void setRemainTime(String remainTime) {
        this.remainTime = remainTime;
    }

    public String getNextTime() {
        return nextTime;
    }

    public void setNextTime(String nextTime) {
        this.nextTime = nextTime;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

/*    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.routeId);
        dest.writeString(this.startStation);
        dest.writeString(this.firstViaStation);
        dest.writeString(this.returnStation);
        dest.writeString(this.secondViaStation);
        dest.writeString(this.endStation);
        dest.writeString(this.remainTime);
        dest.writeString(this.nextTime);
        dest.writeString(this.route);
        dest.writeString(this.type);
        dest.writeArray(this.times.toArray());
        dest.writeByte((byte) (this.isFavorite ? 1 : 0));
    }*/
}
