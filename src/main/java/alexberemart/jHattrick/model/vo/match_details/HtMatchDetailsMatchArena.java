package alexberemart.jHattrick.model.vo.match_details;

import alexberemart.hattrickCore.model.enums.Weather;

public class HtMatchDetailsMatchArena {

    protected Weather weatherId;
    protected Integer soldTotal;

    public Weather getWeatherId() {
        return weatherId;
    }

    public void setWeatherId(Weather weatherId) {
        this.weatherId = weatherId;
    }

    public Integer getSoldTotal() {
        return soldTotal;
    }

    public void setSoldTotal(Integer soldTotal) {
        this.soldTotal = soldTotal;
    }
}
