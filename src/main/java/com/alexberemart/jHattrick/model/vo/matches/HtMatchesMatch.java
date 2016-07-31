package com.alexberemart.jHattrick.model.vo.matches;

import com.alexberemart.hattrickCore.model.enums.MatchStatus;
import com.alexberemart.hattrickCore.model.enums.MatchType;

import java.util.Date;

public class HtMatchesMatch {

    protected Integer matchId;
    protected Date matchDate;
    protected MatchStatus status;
    protected MatchType matchType;
    protected HtMatchesMatchHomeTeam htMatchesMatchHomeTeam;
    protected HtMatchesMatchAwayTeam htMatchesMatchAwayTeam;

    public Integer getMatchId() {
        return matchId;
    }

    public void setMatchId(Integer matchId) {
        this.matchId = matchId;
    }

    public HtMatchesMatchHomeTeam getHtMatchesMatchHomeTeam() {
        return htMatchesMatchHomeTeam;
    }

    public void setHtMatchesMatchHomeTeam(HtMatchesMatchHomeTeam htMatchesMatchHomeTeam) {
        this.htMatchesMatchHomeTeam = htMatchesMatchHomeTeam;
    }

    public HtMatchesMatchAwayTeam getHtMatchesMatchAwayTeam() {
        return htMatchesMatchAwayTeam;
    }

    public void setHtMatchesMatchAwayTeam(HtMatchesMatchAwayTeam htMatchesMatchAwayTeam) {
        this.htMatchesMatchAwayTeam = htMatchesMatchAwayTeam;
    }

    public Date getMatchDate() {
        return matchDate;
    }

    public void setMatchDate(Date matchDate) {
        this.matchDate = matchDate;
    }

    public MatchStatus getStatus() {
        return status;
    }

    public void setStatus(MatchStatus status) {
        this.status = status;
    }

    public MatchType getMatchType() {
        return matchType;
    }

    public void setMatchType(MatchType matchType) {
        this.matchType = matchType;
    }
}
