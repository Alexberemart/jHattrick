package com.alexberemart.jHattrick.model.vo.matches;

import java.util.List;

public class HtMatches {

    protected Integer teamId;
    protected List<HtMatchesMatch> htMatchesMatchList;

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public List<HtMatchesMatch> getHtMatchesMatchList() {
        return htMatchesMatchList;
    }

    public void setHtMatchesMatchList(List<HtMatchesMatch> htMatchesMatchList) {
        this.htMatchesMatchList = htMatchesMatchList;
    }
}
