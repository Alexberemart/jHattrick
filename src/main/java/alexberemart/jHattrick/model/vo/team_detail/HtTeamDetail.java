package alexberemart.jHattrick.model.vo.team_detail;

import java.net.URI;
import java.util.Date;

public class HtTeamDetail {

    protected Integer teamId;
    protected String teamName;
    protected String shortTeamName;
    protected Boolean isPrimaryClub;
    protected Date foundedDate;
    protected URI homePage;
    protected Integer youthTeamID;
    protected String youthTeamName;
    protected HtTeamDetailArena htTeamDetailArena;
    protected HtTeamDetailLeague htTeamDetailLeague;
    protected HtTeamDetailTrainer htTeamDetailTrainer;

    public HtTeamDetailTrainer getHtTeamDetailTrainer() {
        return htTeamDetailTrainer;
    }

    public void setHtTeamDetailTrainer(HtTeamDetailTrainer htTeamDetailTrainer) {
        this.htTeamDetailTrainer = htTeamDetailTrainer;
    }

    public HtTeamDetailRegion getHtTeamDetailRegion() {
        return htTeamDetailRegion;
    }

    public void setHtTeamDetailRegion(HtTeamDetailRegion htTeamDetailRegion) {
        this.htTeamDetailRegion = htTeamDetailRegion;
    }

    protected HtTeamDetailRegion htTeamDetailRegion;

    public Integer getTeamId() {
        return teamId;
    }

    public HtTeamDetailLeague getHtTeamDetailLeague() {
        return htTeamDetailLeague;
    }

    public void setHtTeamDetailLeague(HtTeamDetailLeague htTeamDetailLeague) {
        this.htTeamDetailLeague = htTeamDetailLeague;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public String getShortTeamName() {
        return shortTeamName;
    }

    public void setShortTeamName(String shortTeamName) {
        this.shortTeamName = shortTeamName;
    }

    public Boolean getIsPrimaryClub() {
        return isPrimaryClub;
    }

    public void setIsPrimaryClub(Boolean isPrimaryClub) {
        this.isPrimaryClub = isPrimaryClub;
    }

    public Date getFoundedDate() {
        return foundedDate;
    }

    public void setFoundedDate(Date foundedDate) {
        this.foundedDate = foundedDate;
    }

    public HtTeamDetailArena getHtTeamDetailArena() {

        return htTeamDetailArena;
    }

    public void setHtTeamDetailArena(HtTeamDetailArena htTeamDetailArena) {
        this.htTeamDetailArena = htTeamDetailArena;
    }

    public URI getHomePage() {
        return homePage;
    }

    public void setHomePage(URI homePage) {
        this.homePage = homePage;
    }

    public Integer getYouthTeamID() {
        return youthTeamID;
    }

    public void setYouthTeamID(Integer youthTeamID) {
        this.youthTeamID = youthTeamID;
    }

    public String getYouthTeamName() {
        return youthTeamName;
    }

    public void setYouthTeamName(String youthTeamName) {
        this.youthTeamName = youthTeamName;
    }
}
