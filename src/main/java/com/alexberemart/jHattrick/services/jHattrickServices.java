package com.alexberemart.jHattrick.services;

import com.alexberemart.core.util.ApplicationContextProvider;
import com.alexberemart.hattrickCore.model.enums.*;
import com.alexberemart.jHattrick.model.vo.HtCredentials;
import com.alexberemart.jHattrick.model.vo.economy.Economy;
import com.alexberemart.jHattrick.model.vo.economy.EconomyTeam;
import com.alexberemart.jHattrick.model.vo.matchLineUp.LineUp;
import com.alexberemart.jHattrick.model.vo.matchLineUp.MatchLineUp;
import com.alexberemart.jHattrick.model.vo.matchLineUp.Player;
import com.alexberemart.jHattrick.model.vo.matchLineUp.Team;
import com.alexberemart.jHattrick.model.vo.match_details.*;
import com.alexberemart.jHattrick.model.vo.matches.HtMatches;
import com.alexberemart.jHattrick.model.vo.matches.HtMatchesMatch;
import com.alexberemart.jHattrick.model.vo.matches.HtMatchesMatchAwayTeam;
import com.alexberemart.jHattrick.model.vo.matches.HtMatchesMatchHomeTeam;
import com.alexberemart.jHattrick.model.vo.players.HtPlayers;
import com.alexberemart.jHattrick.model.vo.players.HtPlayersTeam;
import com.alexberemart.jHattrick.model.vo.players.HtPlayersTeamPlayersList;
import com.alexberemart.jHattrick.model.vo.players.HtPlayersTeamPlayersListPlayer;
import com.alexberemart.jHattrick.model.vo.team_detail.*;
import com.alexberemart.jHattrick.model.vo.training.Training;
import com.alexberemart.jHattrick.model.vo.training.TrainingTeam;
import com.alexberemart.jHattrick.model.vo.transfers_team.*;
import com.alexberemart.jHattrick.model.vo.youthPlayerDetail.PlayerSkill;
import com.alexberemart.jHattrick.model.vo.youthPlayerDetail.YouthPlayer;
import com.alexberemart.jHattrick.model.vo.youthPlayerList.YouthPlayerList;
import com.alexberemart.jHattrick.model.vo.youthPlayerList.YouthPlayerList_PlayerList;
import com.alexberemart.jHattrick.model.vo.youthPlayerList.YouthPlayerList_PlayerList_YouthPlayer;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.basic.DefaultOAuthConsumer;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.io.InputStream;
import java.net.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class jHattrickServices {

    @Autowired
    protected String hattrickProtectedResourcesURL;

    final protected SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    final protected String defaultCurrency = "SEK";

    public static jHattrickServices getInstance() {
        return (jHattrickServices) ApplicationContextProvider.getInstance().getBean("jHattrickServices");
    }

    public HtTeamDetail getTeamDetail(HtCredentials htCredentials) throws ParseException, MalformedURLException, URISyntaxException {

        HtTeamDetail htTeamDetail = new HtTeamDetail();

        URL url = new URL(hattrickProtectedResourcesURL + "?file=teamdetails&version=3.2");
        Map<String, Object> requestResult = sendProtectedRequest(url, htCredentials);
        Document document = (Document) requestResult.get("responseData");
        Element rootNode = document.getRootElement();
        Element teamsNode = (Element) rootNode.getChildren("Teams").get(0);
        List<Element> teamNodeList = (List<Element>) teamsNode.getChildren();
        for (Element teamNode : teamNodeList) {
            htTeamDetail.setTeamId(Integer.parseInt(teamNode.getChildText("TeamID")));
            htTeamDetail.setTeamName(teamNode.getChildText("TeamName"));
            htTeamDetail.setShortTeamName(teamNode.getChildText("ShortTeamName"));
            htTeamDetail.setIsPrimaryClub(Boolean.parseBoolean(teamNode.getChildText("IsPrimaryClub")));
            htTeamDetail.setFoundedDate(formatter.parse(teamNode.getChildText("FoundedDate")));
            htTeamDetail.setHomePage(new URI(teamNode.getChildText("HomePage")));
            htTeamDetail.setYouthTeamID(Integer.parseInt(teamNode.getChildText("YouthTeamID")));
            htTeamDetail.setYouthTeamName(teamNode.getChildText("YouthTeamName"));

            //ARENA

            Element arenaNode = (Element) teamNode.getChildren("Arena").get(0);

            HtTeamDetailArena htTeamDetailArena = new HtTeamDetailArena();
            htTeamDetailArena.setArenaId(Integer.parseInt(arenaNode.getChildText("ArenaID")));
            htTeamDetailArena.setArenaName(arenaNode.getChildText("ArenaName"));

            htTeamDetail.setHtTeamDetailArena(htTeamDetailArena);

            //LEAGUE

            Element leagueNode = (Element) teamNode.getChildren("League").get(0);

            HtTeamDetailLeague htTeamDetailLeague = new HtTeamDetailLeague();
            htTeamDetailLeague.setLeagueId(Integer.parseInt(leagueNode.getChildText("LeagueID")));
            htTeamDetailLeague.setLeagueName(leagueNode.getChildText("LeagueName"));

            htTeamDetail.setHtTeamDetailLeague(htTeamDetailLeague);

            //REGION

            Element regionNode = (Element) teamNode.getChildren("Region").get(0);

            HtTeamDetailRegion htTeamDetailRegion = new HtTeamDetailRegion();
            htTeamDetailRegion.setRegionId(Integer.parseInt(regionNode.getChildText("RegionID")));
            htTeamDetailRegion.setRegionName(regionNode.getChildText("RegionName"));

            htTeamDetail.setHtTeamDetailRegion(htTeamDetailRegion);

            //TRAINER

            Element trainerNode = (Element) teamNode.getChildren("Trainer").get(0);

            HtTeamDetailTrainer htTeamDetailTrainer = new HtTeamDetailTrainer();
            htTeamDetailTrainer.setPlayerID(Integer.parseInt(trainerNode.getChildText("PlayerID")));

            htTeamDetail.setHtTeamDetailTrainer(htTeamDetailTrainer);
        }

        return htTeamDetail;
    }

    public HtPlayers getPlayers(HtCredentials htCredentials) throws ParseException, MalformedURLException, URISyntaxException {

        HtPlayers htPlayers = new HtPlayers();
        List<HtPlayersTeamPlayersListPlayer> htPlayersTeamPlayersListPlayerList = new ArrayList<>();

        URL url = new URL(hattrickProtectedResourcesURL + "?file=players&version=2.3");
        Map<String, Object> requestResult = sendProtectedRequest(url, htCredentials);
        Document document = (Document) requestResult.get("responseData");
        Element rootNode = document.getRootElement();
        Element teamNode = (Element) rootNode.getChildren("Team").get(0);
        Element playerListNode = (Element) teamNode.getChildren("PlayerList").get(0);
        List<Element> playerNodeList = (List<Element>) playerListNode.getChildren();
        for (Element playerNode : playerNodeList) {

            HtPlayersTeamPlayersListPlayer htPlayersTeamPlayersListPlayer = new HtPlayersTeamPlayersListPlayer();
            htPlayersTeamPlayersListPlayer.setPlayerId(Integer.parseInt(playerNode.getChildText("PlayerID")));
            htPlayersTeamPlayersListPlayer.setAge(Integer.parseInt(playerNode.getChildText("Age")));
            htPlayersTeamPlayersListPlayer.setAgeDays(Integer.parseInt(playerNode.getChildText("AgeDays")));
            htPlayersTeamPlayersListPlayer.setAggressiveness(PlayerAggressiveness.parse(Integer.parseInt(playerNode.getChildText("Aggressiveness"))));
            htPlayersTeamPlayersListPlayer.setAgreeability(PlayerAgreeability.parse(Integer.parseInt(playerNode.getChildText("Agreeability"))));
            htPlayersTeamPlayersListPlayer.setAgreeability(PlayerAgreeability.parse(Integer.parseInt(playerNode.getChildText("Agreeability"))));
            htPlayersTeamPlayersListPlayer.setDefenderSkill(SkillLevel.parse(Integer.parseInt(playerNode.getChildText("DefenderSkill"))));
            htPlayersTeamPlayersListPlayer.setKeeperSkill(SkillLevel.parse(Integer.parseInt(playerNode.getChildText("KeeperSkill"))));
            htPlayersTeamPlayersListPlayer.setPassingSkill(SkillLevel.parse(Integer.parseInt(playerNode.getChildText("PassingSkill"))));
            htPlayersTeamPlayersListPlayer.setPlaymakerSkill(SkillLevel.parse(Integer.parseInt(playerNode.getChildText("PlaymakerSkill"))));
            htPlayersTeamPlayersListPlayer.setScorerSkill(SkillLevel.parse(Integer.parseInt(playerNode.getChildText("ScorerSkill"))));
            htPlayersTeamPlayersListPlayer.setSetPiecesSkill(SkillLevel.parse(Integer.parseInt(playerNode.getChildText("SetPiecesSkill"))));
            htPlayersTeamPlayersListPlayer.setStaminaSkill(SkillLevel.parse(Integer.parseInt(playerNode.getChildText("StaminaSkill"))));
            htPlayersTeamPlayersListPlayer.setWingerSkill(SkillLevel.parse(Integer.parseInt(playerNode.getChildText("WingerSkill"))));
            htPlayersTeamPlayersListPlayer.setExperience(SkillLevel.parse(Integer.parseInt(playerNode.getChildText("Experience"))));
            htPlayersTeamPlayersListPlayer.setPlayerForm(SkillLevel.parse(Integer.parseInt(playerNode.getChildText("PlayerForm"))));
            htPlayersTeamPlayersListPlayer.setHonesty(PlayerHonesty.parse(Integer.parseInt(playerNode.getChildText("Honesty"))));

            //Cuando el partido esta en curso la información de las lesiones no esta habilitada.
            //When a match is running injury info is not available
            String injuryLevelText = playerNode.getChildText("InjuryLevel");
            if (!injuryLevelText.equals("Not available")) {
                htPlayersTeamPlayersListPlayer.setInjuryLevel(Integer.parseInt(playerNode.getChildText("InjuryLevel")));
            }

            htPlayersTeamPlayersListPlayer.setTsi(Integer.parseInt(playerNode.getChildText("TSI")));
            htPlayersTeamPlayersListPlayer.setLoyalty(SkillLevel.parse(Integer.parseInt(playerNode.getChildText("Loyalty"))));
            htPlayersTeamPlayersListPlayer.setMotherClubBonus(Boolean.parseBoolean(playerNode.getChildText("MotherClubBonus")));
            htPlayersTeamPlayersListPlayer.setFirstName(playerNode.getChildText("FirstName"));
            htPlayersTeamPlayersListPlayer.setNickName(playerNode.getChildText("NickName"));
            htPlayersTeamPlayersListPlayer.setLastName(playerNode.getChildText("LastName"));
            htPlayersTeamPlayersListPlayer.setSalary(Integer.parseInt(playerNode.getChildText("Salary")));
            htPlayersTeamPlayersListPlayerList.add(htPlayersTeamPlayersListPlayer);

        }

        HtPlayersTeamPlayersList htPlayersTeamPlayersList = new HtPlayersTeamPlayersList();
        htPlayersTeamPlayersList.setHtPlayersTeamPlayersListPlayerList(htPlayersTeamPlayersListPlayerList);
        HtPlayersTeam htPlayersTeam = new HtPlayersTeam();
        htPlayersTeam.setHtPlayersTeamPlayersList(htPlayersTeamPlayersList);
        htPlayers.setHtPlayersTeam(htPlayersTeam);

        return htPlayers;
    }

    public HtMatches getMatches(HtCredentials htCredentials, Boolean isYouth) throws ParseException, MalformedURLException, URISyntaxException {

        HtMatches htMatches = new HtMatches();
        List<HtMatchesMatch> htMatchesMatchList = new ArrayList<>();

        String urlText = "?file=matches&version=2.8";

        if (isYouth == Boolean.TRUE) {
            urlText = urlText + "&isYouth=true";
        }

        URL url = new URL(hattrickProtectedResourcesURL + urlText);
        Map<String, Object> requestResult = sendProtectedRequest(url, htCredentials);
        Document document = (Document) requestResult.get("responseData");
        Element rootNode = document.getRootElement();
        Element teamNode = (Element) rootNode.getChildren("Team").get(0);

        htMatches.setTeamId(Integer.parseInt(teamNode.getChildText("TeamID")));

        Element matchesNode = (Element) teamNode.getChildren("MatchList").get(0);
        List<Element> matchNodeList = (List<Element>) matchesNode.getChildren();
        for (Element matchNode : matchNodeList) {

            HtMatchesMatch htMatchesMatch = new HtMatchesMatch();
            htMatchesMatch.setMatchId(Integer.parseInt(matchNode.getChildText("MatchID")));
            htMatchesMatch.setMatchDate(formatter.parse(matchNode.getChildText("MatchDate")));
            htMatchesMatch.setStatus(MatchStatus.valueOf(matchNode.getChildText("Status")));
            htMatchesMatch.setMatchType(MatchType.parse(Integer.parseInt(matchNode.getChildText("MatchType"))));
            htMatchesMatchList.add(htMatchesMatch);

            //HOME TEAM

            Element homeTeamNode = (Element) matchNode.getChildren("HomeTeam").get(0);
            HtMatchesMatchHomeTeam htMatchesMatchHomeTeam = new HtMatchesMatchHomeTeam();
            htMatchesMatchHomeTeam.setHomeTeamId(Integer.parseInt(homeTeamNode.getChildText("HomeTeamID")));
            htMatchesMatchHomeTeam.setHomeTeamName(homeTeamNode.getChildText("HomeTeamName"));
            htMatchesMatch.setHtMatchesMatchHomeTeam(htMatchesMatchHomeTeam);

            //AWAY TEAM

            Element awayTeamNode = (Element) matchNode.getChildren("AwayTeam").get(0);
            HtMatchesMatchAwayTeam htMatchesMatchAwayTeam = new HtMatchesMatchAwayTeam();
            htMatchesMatchAwayTeam.setAwayTeamId(Integer.parseInt(awayTeamNode.getChildText("AwayTeamID")));
            htMatchesMatchAwayTeam.setAwayTeamName(awayTeamNode.getChildText("AwayTeamName"));
            htMatchesMatch.setHtMatchesMatchAwayTeam(htMatchesMatchAwayTeam);
        }

        htMatches.setHtMatchesMatchList(htMatchesMatchList);

        return htMatches;
    }

    public HtMatchDetails getMatchDetails(HtCredentials htCredentials, Integer matchId, SourceSystem sourceSystem) throws ParseException, MalformedURLException, URISyntaxException {

        HtMatchDetails htMatchDetails = new HtMatchDetails();

        String urlText;
        urlText = hattrickProtectedResourcesURL + "?file=matchdetails&sourceSystem=" + sourceSystem.getValue() + "&matchID=" + matchId;
        URL url = new URL(urlText);
        Map<String, Object> requestResult = sendProtectedRequest(url, htCredentials);
        Document document = (Document) requestResult.get("responseData");
        Element rootNode = document.getRootElement();
        Element matchNode = (Element) rootNode.getChildren("Match").get(0);

        HtMatchDetailsMatch htMatchDetailsMatch = new HtMatchDetailsMatch();

        String finishedDateText = matchNode.getChildText("FinishedDate");
        if (finishedDateText != null) {
            htMatchDetailsMatch.setFinishedDate(formatter.parse(finishedDateText));
        }

        //ARENA

        Element arenaNode = (Element) matchNode.getChildren("Arena").get(0);

        HtMatchDetailsMatchArena htMatchDetailsMatchArena = new HtMatchDetailsMatchArena();

        String weatherText = arenaNode.getChildText("WeatherID");
        if (weatherText != null) {
            htMatchDetailsMatchArena.setWeatherId(Weather.parse(Integer.parseInt(weatherText)));
        }

        String soldTotalText = arenaNode.getChildText("SoldTotal");
        if (soldTotalText != null) {
            htMatchDetailsMatchArena.setSoldTotal(Integer.parseInt(soldTotalText));
        }

        htMatchDetailsMatch.setArena(htMatchDetailsMatchArena);

        //SCORERS

        try {
            Element scorersNode = (Element) matchNode.getChildren("Scorers").get(0);
            List<Element> scorersNodeList = (List<Element>) scorersNode.getChildren();

            HtMatchDetailsMatchScorers htMatchDetailsMatchScorers = new HtMatchDetailsMatchScorers();
            List<HtMatchDetailsMatchScorersGoal> htMatchDetailsMatchScorersGoalList = new ArrayList<>();

            for (Element goalNode : scorersNodeList) {

                HtMatchDetailsMatchScorersGoal htMatchDetailsMatchScorersGoal = new HtMatchDetailsMatchScorersGoal();
                htMatchDetailsMatchScorersGoal.setScorerPlayerId(Integer.parseInt(goalNode.getChildText("ScorerPlayerID")));
                htMatchDetailsMatchScorersGoal.setScorerPlayerName(goalNode.getChildText("ScorerPlayerName"));
                htMatchDetailsMatchScorersGoal.setScorerMinute(Integer.parseInt(goalNode.getChildText("ScorerMinute")));
                htMatchDetailsMatchScorersGoal.setScorerTeamId(Integer.parseInt(goalNode.getChildText("ScorerTeamID")));
                htMatchDetailsMatchScorersGoalList.add(htMatchDetailsMatchScorersGoal);

            }

            htMatchDetailsMatchScorers.setMatchDetailsMatchScorersGoalList(htMatchDetailsMatchScorersGoalList);
            htMatchDetailsMatch.setMatchDetailsMatchScorers(htMatchDetailsMatchScorers);
        } catch (IndexOutOfBoundsException e) {
        }

        htMatchDetails.setMatch(htMatchDetailsMatch);

        return htMatchDetails;
    }

    public TransfersTeam getTransfersTeam(HtCredentials htCredentials) throws ParseException, MalformedURLException, URISyntaxException {

        TransfersTeam transfersTeam = new TransfersTeam();
        TransfersTeamTransfers transfersTeamTransfers = new TransfersTeamTransfers();
        List<TransfersTeamTransfersTransfer> transfersTeamTransfersTransferList = new ArrayList<>();

        URL url = new URL(hattrickProtectedResourcesURL + "?file=transfersteam&version=1.2");
        Map<String, Object> requestResult = sendProtectedRequest(url, htCredentials);
        Document document = (Document) requestResult.get("responseData");
        Element rootNode = document.getRootElement();
        Element transfersNode = (Element) rootNode.getChildren("Transfers").get(0);
        List<Element> transferNodeList = (List<Element>) transfersNode.getChildren();

        for (Element transferNode : transferNodeList) {

            if (transferNode.getName().equals("Transfer")) {

                TransfersTeamTransfersTransfer transfersTeamTransfersTransfer = new TransfersTeamTransfersTransfer();
                transfersTeamTransfersTransfer.setTransferId(Integer.parseInt(transferNode.getChildText("TransferID")));
                transfersTeamTransfersTransfer.setPrice(Integer.parseInt(transferNode.getChildText("Price")));
                transfersTeamTransfersTransfer.setDeadLine(formatter.parse((transferNode.getChildText("Deadline"))));

                Element playerNode = (Element) transferNode.getChildren("Player").get(0);
                TransfersTeamTransfersTransferPlayer transfersTeamTransfersTransferPlayer = new TransfersTeamTransfersTransferPlayer();
                transfersTeamTransfersTransferPlayer.setPlayerId(Integer.parseInt(playerNode.getChildText("PlayerID")));
                transfersTeamTransfersTransferPlayer.setPlayerName(playerNode.getChildText("PlayerName"));

                transfersTeamTransfersTransfer.setTransfersTeamTransfersTransferPlayer(transfersTeamTransfersTransferPlayer);

                Element buyerNode = (Element) transferNode.getChildren("Buyer").get(0);
                TransfersTeamTransfersTransferBuyer transfersTeamTransfersTransferBuyer = new TransfersTeamTransfersTransferBuyer();
                transfersTeamTransfersTransferBuyer.setBuyerTeamId(Integer.parseInt(buyerNode.getChildText("BuyerTeamID")));
                transfersTeamTransfersTransferBuyer.setBuyerTeamName(buyerNode.getChildText("BuyerTeamName"));

                transfersTeamTransfersTransfer.setTransfersTeamTransfersTransferBuyer(transfersTeamTransfersTransferBuyer);

                Element sellerNode = (Element) transferNode.getChildren("Seller").get(0);
                TransfersTeamTransfersTransferSeller transfersTeamTransfersTransferSeller = new TransfersTeamTransfersTransferSeller();
                transfersTeamTransfersTransferSeller.setSellerTeamId(Integer.parseInt(sellerNode.getChildText("SellerTeamID")));
                transfersTeamTransfersTransferSeller.setSellerTeamName(sellerNode.getChildText("SellerTeamName"));

                transfersTeamTransfersTransfer.setTransfersTeamTransfersTransferSeller(transfersTeamTransfersTransferSeller);

                transfersTeamTransfersTransferList.add(transfersTeamTransfersTransfer);

            }

        }

        transfersTeamTransfers.setTransfersTeamTransfersTransferList(transfersTeamTransfersTransferList);
        transfersTeam.setTransfersTeamTransfers(transfersTeamTransfers);
        return transfersTeam;
    }

    public Economy getEconomy(HtCredentials htCredentials) throws ParseException, MalformedURLException, URISyntaxException {

        Economy economy = new Economy();
        EconomyTeam economyTeam = new EconomyTeam();

        URL url = new URL(hattrickProtectedResourcesURL + "?file=economy&version=1.3");
        Map<String, Object> requestResult = sendProtectedRequest(url, htCredentials);
        Document document = (Document) requestResult.get("responseData");
        Element rootNode = document.getRootElement();
        Element teamNode = (Element) rootNode.getChildren("Team").get(0);

        String sponsorsPopularityText = teamNode.getChildText("SponsorsPopularity");
        if ((sponsorsPopularityText != null) && (!sponsorsPopularityText.isEmpty())) {
            economyTeam.setSponsorsPopularity(Integer.parseInt(sponsorsPopularityText));
        }

        String supportersPopularityText = teamNode.getChildText("SupportersPopularity");
        if ((supportersPopularityText != null) && (!supportersPopularityText.isEmpty())) {
            economyTeam.setSupportersPopularity(Integer.parseInt(supportersPopularityText));
        }

        economyTeam.setFanClubSize(Integer.parseInt(teamNode.getChildText("FanClubSize")));
        economyTeam.setCash(Money.parse(defaultCurrency + " " + teamNode.getChildText("Cash")));
        economyTeam.setIncomeSum(Money.parse(defaultCurrency + " " + teamNode.getChildText("IncomeSum")));
        economyTeam.setCostsSum(Money.parse(defaultCurrency + " " + teamNode.getChildText("CostsSum")));
        economyTeam.setIncomeSpectators(Money.parse(defaultCurrency + " " + teamNode.getChildText("IncomeSpectators")));
        economyTeam.setIncomeSponsors(Money.parse(defaultCurrency + " " + teamNode.getChildText("IncomeSponsors")));
        economyTeam.setIncomeFinancial(Money.parse(defaultCurrency + " " + teamNode.getChildText("IncomeFinancial")));
        economyTeam.setIncomeSoldPlayers(Money.parse(defaultCurrency + " " + teamNode.getChildText("IncomeSoldPlayers")));
        economyTeam.setIncomeSoldPlayersCommission(Money.parse(defaultCurrency + " " + teamNode.getChildText("IncomeSoldPlayersCommission")));
        economyTeam.setCostsArena(Money.parse(defaultCurrency + " " + teamNode.getChildText("CostsArena")));
        economyTeam.setCostsPlayer(Money.parse(defaultCurrency + " " + teamNode.getChildText("CostsPlayers")));
        economyTeam.setCostsFinancial(Money.parse(defaultCurrency + " " + teamNode.getChildText("CostsFinancial")));
        economyTeam.setCostsBoughtPlayers(Money.parse(defaultCurrency + " " + teamNode.getChildText("CostsBoughtPlayers")));
        economyTeam.setCostsArenaBuilding(Money.parse(defaultCurrency + " " + teamNode.getChildText("CostsArenaBuilding")));
        economyTeam.setCostsStaff(Money.parse(defaultCurrency + " " + teamNode.getChildText("CostsStaff")));
        economyTeam.setCostsYouth(Money.parse(defaultCurrency + " " + teamNode.getChildText("CostsYouth")));

        economy.setEconomyTeam(economyTeam);

        return economy;
    }

    public Training getTraining(HtCredentials htCredentials) throws ParseException, MalformedURLException, URISyntaxException {

        Training training = new Training();
        TrainingTeam trainingTeam = new TrainingTeam();

        URL url = new URL(hattrickProtectedResourcesURL + "?file=training&version=2.2");
        Map<String, Object> requestResult = sendProtectedRequest(url, htCredentials);
        Document document = (Document) requestResult.get("responseData");
        Element rootNode = document.getRootElement();
        Element teamNode = (Element) rootNode.getChildren("Team").get(0);

        String moraleText = teamNode.getChildText("Morale");
        if ((moraleText != null) && (!moraleText.isEmpty())) {
            trainingTeam.setMorale(TeamSpirit.parse(Integer.parseInt(moraleText)));
        }

        String selfConfidenceText = teamNode.getChildText("SelfConfidence");
        if ((selfConfidenceText != null) && (!selfConfidenceText.isEmpty())) {
            trainingTeam.setSelfConfidence(SelfConfidence.parse(Integer.parseInt(selfConfidenceText)));
        }

        training.setTrainingTeam(trainingTeam);

        return training;
    }

    public YouthPlayerList getYouthPlayers(HtCredentials htCredentials) throws ParseException, MalformedURLException, URISyntaxException {

        YouthPlayerList youthPlayerList = new YouthPlayerList();
        YouthPlayerList_PlayerList youthPlayerList_playerList = new YouthPlayerList_PlayerList();
        List<YouthPlayerList_PlayerList_YouthPlayer> youthPlayerList_playerList_youthPlayers = new ArrayList<>();

        URL url = new URL(hattrickProtectedResourcesURL + "?file=youthplayerlist&version==1.1");
        Map<String, Object> requestResult = sendProtectedRequest(url, htCredentials);
        Document document = (Document) requestResult.get("responseData");
        Element rootNode = document.getRootElement();
        Element playerListNode = (Element) rootNode.getChildren("PlayerList").get(0);
        List<Element> playerNodeList = (List<Element>) playerListNode.getChildren();
        for (Element playerNode : playerNodeList) {

            YouthPlayerList_PlayerList_YouthPlayer youthPlayerList_playerList_youthPlayer = new YouthPlayerList_PlayerList_YouthPlayer();
            youthPlayerList_playerList_youthPlayer.setYouthPlayerId(Integer.parseInt(playerNode.getChildText("YouthPlayerID")));
            youthPlayerList_playerList_youthPlayer.setFirstName(playerNode.getChildText("FirstName"));
            youthPlayerList_playerList_youthPlayer.setLastName(playerNode.getChildText("LastName"));
            youthPlayerList_playerList_youthPlayer.setNickName(playerNode.getChildText("NickName"));
            youthPlayerList_playerList_youthPlayers.add(youthPlayerList_playerList_youthPlayer);

        }

        youthPlayerList_playerList.setYouthPlayerList_playerList_youthPlayers(youthPlayerList_playerList_youthPlayers);
        youthPlayerList.setYouthPlayerList_playerList(youthPlayerList_playerList);
        return youthPlayerList;
    }

    public YouthPlayer getYouthPlayerDetail(Integer playerId, HtCredentials htCredentials) throws ParseException, MalformedURLException, URISyntaxException {

        URL url = new URL(hattrickProtectedResourcesURL + "?file=youthplayerdetails&version=1.1&youthPlayerId=" + playerId);
        Map<String, Object> requestResult = sendProtectedRequest(url, htCredentials);
        Document document = (Document) requestResult.get("responseData");
        Element rootNode = document.getRootElement();
        Element youthPlayerNode = (Element) rootNode.getChildren("YouthPlayer").get(0);

        YouthPlayer youthPlayer = new YouthPlayer();
        youthPlayer.setAge(Integer.parseInt(youthPlayerNode.getChildText("Age")));
        youthPlayer.setAgeDays(Integer.parseInt(youthPlayerNode.getChildText("AgeDays")));
        youthPlayer.setCanBePromotedIn(Integer.parseInt(youthPlayerNode.getChildText("CanBePromotedIn")));

        Element playerSkillsNode = (Element) youthPlayerNode.getChildren("PlayerSkills").get(0);

        youthPlayer.setKeeperSkill(getYouthPlayerSkill(playerSkillsNode, "KeeperSkill"));
        youthPlayer.setDefenderSkill(getYouthPlayerSkill(playerSkillsNode, "DefenderSkill"));
        youthPlayer.setPlayMakerSkill(getYouthPlayerSkill(playerSkillsNode, "PlaymakerSkill"));
        youthPlayer.setWingerSkill(getYouthPlayerSkill(playerSkillsNode, "WingerSkill"));
        youthPlayer.setScorerSkill(getYouthPlayerSkill(playerSkillsNode, "ScorerSkill"));
        youthPlayer.setPassingSkill(getYouthPlayerSkill(playerSkillsNode, "PassingSkill"));
        youthPlayer.setSetPiecesSkill(getYouthPlayerSkill(playerSkillsNode, "SetPiecesSkill"));

        youthPlayer.setKeeperSkillMax(getYouthPlayerSkill(playerSkillsNode, "KeeperSkillMax"));
        youthPlayer.setDefenderSkillMax(getYouthPlayerSkill(playerSkillsNode, "DefenderSkillMax"));
        youthPlayer.setPlayMakerSkillMax(getYouthPlayerSkill(playerSkillsNode, "PlaymakerSkillMax"));
        youthPlayer.setWingerSkillMax(getYouthPlayerSkill(playerSkillsNode, "WingerSkillMax"));
        youthPlayer.setScorerSkillMax(getYouthPlayerSkill(playerSkillsNode, "ScorerSkillMax"));
        youthPlayer.setPassingSkillMax(getYouthPlayerSkill(playerSkillsNode, "PassingSkillMax"));
        youthPlayer.setSetPiecesSkillMax(getYouthPlayerSkill(playerSkillsNode, "SetPiecesSkillMax"));

        return youthPlayer;
    }

    public MatchLineUp getMatchLineUp(HtCredentials htCredentials, Integer matchId) throws ParseException, MalformedURLException, URISyntaxException {

        Player player;
        LineUp lineUp = new LineUp();

        URL url = new URL(hattrickProtectedResourcesURL + "?file=matchlineup&version=1.9&matchID=" + matchId);
        Map<String, Object> requestResult = sendProtectedRequest(url, htCredentials);
        Document document = (Document) requestResult.get("responseData");
        Element rootNode = document.getRootElement();
        Element teamNode = (Element) rootNode.getChildren("Team").get(0);
        Element lineUpNode = (Element) teamNode.getChildren("Lineup").get(0);
        List<Element> playerNodeList = (List<Element>) lineUpNode.getChildren();
        for (Element playerNode : playerNodeList) {
            player = new Player();
            player.setPlayerId(Integer.parseInt(playerNode.getChildText("PlayerID")));
            lineUp.getPlayers().add(player);
        }

        Team team = new Team();
        team.setLineUp(lineUp);

        MatchLineUp matchLineUp = new MatchLineUp();
        matchLineUp.setTeam(team);
        return matchLineUp;
    }

    protected static Map<String, Object> sendProtectedRequest(URL url, HtCredentials htCredentials) {

        // create a consumer object and configure it with the access
        // token and token secret obtained from the service provider
        OAuthConsumer consumer = new DefaultOAuthConsumer(htCredentials.getConsumerKey(), htCredentials.getConsumerSecretKey());
        consumer.setTokenWithSecret(htCredentials.getToken(), htCredentials.getSecretToken());

        // create an HTTP request to a protected resource
        HttpURLConnection request;
        String response;
        Document document;
        Map<String, Object> result = new HashMap<>();

        try {
            request = (HttpURLConnection) url.openConnection();
            consumer.sign(request);
            request.connect();
            response = request.getResponseMessage();
            InputStream inputStream = request.getInputStream();

            SAXBuilder builder = new SAXBuilder();
            document = builder.build(inputStream);

            result.put("responseData", document);
            result.put("responseCode", response);

        } catch (IOException | OAuthCommunicationException | OAuthExpectationFailedException | OAuthMessageSignerException | JDOMException e) {
            e.printStackTrace();
        }

        return result;
    }

    protected PlayerSkill getYouthPlayerSkill(Element mainNode, String searchText) {
        Element skillNode = (Element) mainNode.getChildren(searchText).get(0);

        PlayerSkill playerSkill = new PlayerSkill();
        playerSkill.setIsAvailable(Boolean.parseBoolean(skillNode.getAttribute("IsAvailable").getValue()));
        if (playerSkill.getIsAvailable() == Boolean.TRUE) {
            playerSkill.setValue(Integer.parseInt(skillNode.getValue()));
        }

        return (playerSkill);
    }

}
