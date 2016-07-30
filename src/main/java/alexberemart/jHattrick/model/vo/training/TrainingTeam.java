package alexberemart.jHattrick.model.vo.training;

import alexberemart.hattrickCore.model.enums.SelfConfidence;
import alexberemart.hattrickCore.model.enums.TeamSpirit;

public class TrainingTeam {

    protected TeamSpirit morale;
    protected SelfConfidence selfConfidence;

    public TeamSpirit getMorale() {
        return morale;
    }

    public void setMorale(TeamSpirit morale) {
        this.morale = morale;
    }

    public SelfConfidence getSelfConfidence() {
        return selfConfidence;
    }

    public void setSelfConfidence(SelfConfidence selfConfidence) {
        this.selfConfidence = selfConfidence;
    }
}
