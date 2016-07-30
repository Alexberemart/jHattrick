package alexberemart.jHattrick.model.vo.match_details;

public class HtMatchDetailsMatchScorersGoal {

    protected Integer scorerPlayerId;
    protected String scorerPlayerName;
    protected Integer scorerMinute;
    protected Integer scorerTeamId;

    public Integer getScorerPlayerId() {
        return scorerPlayerId;
    }

    public void setScorerPlayerId(Integer scorerPlayerId) {
        this.scorerPlayerId = scorerPlayerId;
    }

    public String getScorerPlayerName() {
        return scorerPlayerName;
    }

    public void setScorerPlayerName(String scorerPlayerName) {
        this.scorerPlayerName = scorerPlayerName;
    }

    public Integer getScorerMinute() {
        return scorerMinute;
    }

    public void setScorerMinute(Integer scorerMinute) {
        this.scorerMinute = scorerMinute;
    }

    public Integer getScorerTeamId() {
        return scorerTeamId;
    }

    public void setScorerTeamId(Integer scorerTeamId) {
        this.scorerTeamId = scorerTeamId;
    }
}
