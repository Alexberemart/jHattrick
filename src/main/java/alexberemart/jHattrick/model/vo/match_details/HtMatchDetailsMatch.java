package alexberemart.jHattrick.model.vo.match_details;

import java.util.Date;

public class HtMatchDetailsMatch {

    protected Date finishedDate;
    protected HtMatchDetailsMatchArena arena;

    protected HtMatchDetailsMatchScorers matchDetailsMatchScorers;

    public Date getFinishedDate() {
        return finishedDate;
    }

    public void setFinishedDate(Date finishedDate) {
        this.finishedDate = finishedDate;
    }

    public HtMatchDetailsMatchArena getArena() {
        return arena;
    }

    public void setArena(HtMatchDetailsMatchArena arena) {
        this.arena = arena;
    }

    public HtMatchDetailsMatchScorers getMatchDetailsMatchScorers() {
        return matchDetailsMatchScorers;
    }

    public void setMatchDetailsMatchScorers(HtMatchDetailsMatchScorers matchDetailsMatchScorers) {
        this.matchDetailsMatchScorers = matchDetailsMatchScorers;
    }
}
