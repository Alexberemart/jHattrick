package alexberemart.jHattrick;

import alexberemart.hattrickCore.model.enums.SourceSystem;
import alexberemart.jHattrick.model.vo.HtCredentials;
import alexberemart.jHattrick.model.vo.economy.Economy;
import alexberemart.jHattrick.model.vo.matchLineUp.MatchLineUp;
import alexberemart.jHattrick.model.vo.match_details.HtMatchDetails;
import alexberemart.jHattrick.model.vo.matches.HtMatches;
import alexberemart.jHattrick.model.vo.players.HtPlayers;
import alexberemart.jHattrick.model.vo.team_detail.HtTeamDetail;
import alexberemart.jHattrick.model.vo.training.Training;
import alexberemart.jHattrick.model.vo.transfers_team.TransfersTeam;
import alexberemart.jHattrick.model.vo.youthPlayerDetail.YouthPlayer;
import alexberemart.jHattrick.model.vo.youthPlayerList.YouthPlayerList;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.text.ParseException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
        "classpath:/alexberemart/jHattrick/context.xml"
})
@Transactional
public class jHattrickAPITest
        extends AbstractTransactionalJUnit4SpringContextTests {

    HtCredentials htCredentials = new HtCredentials();

    @Before
    public void setUp() throws ParseException, MalformedURLException, URISyntaxException {


        htCredentials.setConsumerKey("AJQS4PJAKWPxfTTgT1Zq6a");
        htCredentials.setConsumerSecretKey("dBSy7HNAs4X4UnSeBUlqiAr5hYLLZNtwLIFmZHQ5Nul");
        htCredentials.setToken("INNCqCBhXuG8pNST");
        htCredentials.setSecretToken("95CbS1jPb4hdHJ2d");
    }

    @Test
    public void getTeamDetail() throws ParseException, MalformedURLException, URISyntaxException {
        HtTeamDetail htTeamDetail = jHattrickAPI.getTeamDetail(htCredentials);
        Assert.assertNotNull(htTeamDetail);
    }

    @Test
    public void getMatches() throws ParseException, MalformedURLException, URISyntaxException {
        HtMatches htMatches = jHattrickAPI.getMatches(htCredentials, false);
        Assert.assertNotNull(htMatches);
    }

    @Test
    public void getPlayers() throws ParseException, MalformedURLException, URISyntaxException {
        HtPlayers htPlayers = jHattrickAPI.getPlayers(htCredentials);
        Assert.assertNotNull(htPlayers);
    }

    @Test
     public void getMatchDetails() throws ParseException, MalformedURLException, URISyntaxException {
        //?file=matchdetails&version=2.7&isYouth=true&matchID=52282171
        SourceSystem sourceSystem = SourceSystem.HATTRICK;
        HtMatchDetails htMatchDetails = jHattrickAPI.getMatchDetails(htCredentials, 52282171, sourceSystem);
        Assert.assertNotNull(htMatchDetails);
    }

    @Test
     public void getTransfersTeam() throws ParseException, MalformedURLException, URISyntaxException {
        TransfersTeam transfersTeam = jHattrickAPI.getTransfersTeam(htCredentials);
        Assert.assertNotNull(transfersTeam);
    }

    @Test
    public void getEconomy() throws ParseException, MalformedURLException, URISyntaxException {
        Economy economy = jHattrickAPI.getEconomy(htCredentials);
        Assert.assertNotNull(economy);
    }

    @Test
    public void getTraining() throws ParseException, MalformedURLException, URISyntaxException {
        Training training = jHattrickAPI.getTraining(htCredentials);
        Assert.assertNotNull(training);
    }

    @Test
    public void getYouthPlayers() throws ParseException, MalformedURLException, URISyntaxException {
        YouthPlayerList youthPlayerList = jHattrickAPI.getYouthPlayers(htCredentials);
        Assert.assertNotNull(youthPlayerList);
    }

    @Test
    public void getYouthPlayerDetail() throws ParseException, MalformedURLException, URISyntaxException {
        YouthPlayerList youthPlayerList = jHattrickAPI.getYouthPlayers(htCredentials);
        Integer playerId = youthPlayerList.getYouthPlayerList_playerList().getYouthPlayerList_playerList_youthPlayers().get(0).getYouthPlayerId();
        YouthPlayer youthPlayer = jHattrickAPI.getYouthPlayerDetail(playerId, htCredentials);
        Assert.assertNotNull(youthPlayer);
    }

    @Test
    public void getYouthMatches() throws ParseException, MalformedURLException, URISyntaxException {
        HtMatches htMatches = jHattrickAPI.getMatches(htCredentials, true);
        Assert.assertNotNull(htMatches);
    }

    @Test
    public void getMatchLineUp() throws ParseException, MalformedURLException, URISyntaxException {
        HtMatches htMatches = jHattrickAPI.getMatches(htCredentials, false);
        MatchLineUp matchLineUp = jHattrickAPI.getMatchLineUp(htCredentials, htMatches.getHtMatchesMatchList().get(0).getMatchId());
        Assert.assertNotNull(matchLineUp);
    }
}
