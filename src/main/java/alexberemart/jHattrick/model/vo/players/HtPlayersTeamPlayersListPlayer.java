package alexberemart.jHattrick.model.vo.players;

import com.alexberemart.hattrickCore.model.enums.PlayerAggressiveness;
import com.alexberemart.hattrickCore.model.enums.PlayerAgreeability;
import com.alexberemart.hattrickCore.model.enums.PlayerHonesty;
import com.alexberemart.hattrickCore.model.enums.SkillLevel;

public class HtPlayersTeamPlayersListPlayer {

    protected Integer playerId;
    protected String firstName;
    protected String nickName;
    protected String lastName;
    protected Integer age;
    protected Integer ageDays;
    protected PlayerAggressiveness aggressiveness;
    protected PlayerAgreeability agreeability;
    protected SkillLevel defenderSkill;
    protected SkillLevel keeperSkill;
    protected SkillLevel passingSkill;
    protected SkillLevel playmakerSkill;
    protected SkillLevel scorerSkill;
    protected SkillLevel setPiecesSkill;
    protected SkillLevel staminaSkill;
    protected SkillLevel wingerSkill;
    protected SkillLevel experience;
    protected SkillLevel playerForm;
    protected PlayerHonesty honesty;
    protected SkillLevel loyalty;
    protected Integer injuryLevel;
    protected Integer salary;
    protected Integer tsi;
    protected Boolean motherClubBonus;

    public Integer getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Integer playerId) {
        this.playerId = playerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public SkillLevel getStaminaSkill() {
        return staminaSkill;
    }

    public SkillLevel getWingerSkill() {
        return wingerSkill;
    }

    public void setWingerSkill(SkillLevel wingerSkill) {
        this.wingerSkill = wingerSkill;
    }

    public Integer getTsi() {
        return tsi;
    }

    public void setTsi(Integer tsi) {
        this.tsi = tsi;
    }

    public void setStaminaSkill(SkillLevel staminaSkill) {
        this.staminaSkill = staminaSkill;

    }

    public SkillLevel getSetPiecesSkill() {
        return setPiecesSkill;
    }

    public void setSetPiecesSkill(SkillLevel setPiecesSkill) {
        this.setPiecesSkill = setPiecesSkill;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getLastName() {
        return lastName;
    }

    public SkillLevel getScorerSkill() {
        return scorerSkill;
    }

    public void setScorerSkill(SkillLevel scorerSkill) {
        this.scorerSkill = scorerSkill;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getAgeDays() {
        return ageDays;
    }

    public void setAgeDays(Integer ageDays) {
        this.ageDays = ageDays;
    }

    public PlayerAggressiveness getAggressiveness() {
        return aggressiveness;
    }

    public void setAggressiveness(PlayerAggressiveness aggressiveness) {
        this.aggressiveness = aggressiveness;
    }

    public PlayerAgreeability getAgreeability() {
        return agreeability;
    }

    public void setAgreeability(PlayerAgreeability agreeability) {
        this.agreeability = agreeability;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public SkillLevel getDefenderSkill() {
        return defenderSkill;
    }

    public void setDefenderSkill(SkillLevel defenderSkill) {
        this.defenderSkill = defenderSkill;
    }

    public SkillLevel getExperience() {
        return experience;
    }

    public void setExperience(SkillLevel experience) {
        this.experience = experience;
    }

    public SkillLevel getPlayerForm() {
        return playerForm;
    }

    public void setPlayerForm(SkillLevel playerForm) {
        this.playerForm = playerForm;
    }

    public PlayerHonesty getHonesty() {
        return honesty;
    }

    public void setHonesty(PlayerHonesty honesty) {
        this.honesty = honesty;
    }

    public Integer getInjuryLevel() {
        return injuryLevel;
    }

    public void setInjuryLevel(Integer injuryLevel) {
        this.injuryLevel = injuryLevel;
    }

    public SkillLevel getKeeperSkill() {
        return keeperSkill;
    }

    public void setKeeperSkill(SkillLevel keeperSkill) {
        this.keeperSkill = keeperSkill;
    }

    public SkillLevel getLoyalty() {
        return loyalty;
    }

    public void setLoyalty(SkillLevel loyalty) {
        this.loyalty = loyalty;
    }

    public Boolean getMotherClubBonus() {
        return motherClubBonus;
    }

    public void setMotherClubBonus(Boolean motherClubBonus) {
        this.motherClubBonus = motherClubBonus;
    }

    public SkillLevel getPassingSkill() {
        return passingSkill;
    }

    public void setPassingSkill(SkillLevel passingSkill) {
        this.passingSkill = passingSkill;
    }

    public SkillLevel getPlaymakerSkill() {
        return playmakerSkill;
    }

    public void setPlaymakerSkill(SkillLevel playmakerSkill) {
        this.playmakerSkill = playmakerSkill;
    }
}
