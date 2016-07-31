package com.alexberemart.jHattrick;

import com.alexberemart.hattrickCore.model.enums.SourceSystem;
import com.alexberemart.jHattrick.model.vo.HtCredentials;
import com.alexberemart.jHattrick.model.vo.economy.Economy;
import com.alexberemart.jHattrick.model.vo.matchLineUp.MatchLineUp;
import com.alexberemart.jHattrick.model.vo.match_details.HtMatchDetails;
import com.alexberemart.jHattrick.model.vo.matches.HtMatches;
import com.alexberemart.jHattrick.model.vo.players.HtPlayers;
import com.alexberemart.jHattrick.model.vo.team_detail.HtTeamDetail;
import com.alexberemart.jHattrick.model.vo.training.Training;
import com.alexberemart.jHattrick.model.vo.transfers_team.TransfersTeam;
import com.alexberemart.jHattrick.model.vo.youthPlayerDetail.YouthPlayer;
import com.alexberemart.jHattrick.model.vo.youthPlayerList.YouthPlayerList;
import com.alexberemart.jHattrick.services.jHattrickServices;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.text.ParseException;

public class jHattrickAPI {

    public static HtTeamDetail getTeamDetail(HtCredentials htCredentials) throws ParseException, MalformedURLException, URISyntaxException {
        return jHattrickServices.getInstance().getTeamDetail(htCredentials);
    }

    public static HtPlayers getPlayers(HtCredentials htCredentials) throws ParseException, MalformedURLException, URISyntaxException {
        return jHattrickServices.getInstance().getPlayers(htCredentials);
    }

    public static HtMatches getMatches(HtCredentials htCredentials, Boolean isYouth) throws ParseException, MalformedURLException, URISyntaxException {
        return jHattrickServices.getInstance().getMatches(htCredentials, isYouth);
    }

    public static HtMatchDetails getMatchDetails(HtCredentials htCredentials,Integer matchId, SourceSystem sourceSystem) throws ParseException, MalformedURLException, URISyntaxException {
        return jHattrickServices.getInstance().getMatchDetails(htCredentials, matchId, sourceSystem);
    }

    public static TransfersTeam getTransfersTeam(HtCredentials htCredentials) throws ParseException, MalformedURLException, URISyntaxException {
        return jHattrickServices.getInstance().getTransfersTeam(htCredentials);
    }

    public static Economy getEconomy(HtCredentials htCredentials) throws ParseException, MalformedURLException, URISyntaxException {
        return jHattrickServices.getInstance().getEconomy(htCredentials);
    }

    public static Training getTraining(HtCredentials htCredentials) throws ParseException, MalformedURLException, URISyntaxException {
        return jHattrickServices.getInstance().getTraining(htCredentials);
    }

    public static YouthPlayerList getYouthPlayers(HtCredentials htCredentials) throws ParseException, MalformedURLException, URISyntaxException {
        return jHattrickServices.getInstance().getYouthPlayers(htCredentials);
    }

    public static YouthPlayer getYouthPlayerDetail(Integer playerId, HtCredentials htCredentials) throws ParseException, MalformedURLException, URISyntaxException {
        return jHattrickServices.getInstance().getYouthPlayerDetail(playerId, htCredentials);
    }

    public static MatchLineUp getMatchLineUp(HtCredentials htCredentials, Integer matchId) throws ParseException, URISyntaxException, MalformedURLException {
        return jHattrickServices.getInstance().getMatchLineUp(htCredentials, matchId);
    }

}
