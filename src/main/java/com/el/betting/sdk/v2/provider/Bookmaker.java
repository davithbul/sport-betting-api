package com.el.betting.sdk.v2.provider;

import com.el.betting.exceptions.BookmakerNotFoundException;
import com.el.betting.sdk.v2.market.AffiliateStrategy;

import java.io.Serializable;
import java.util.*;

import static com.el.betting.sdk.v2.market.AffiliateStrategy.NONE;
import static com.el.betting.sdk.v2.market.AffiliateStrategy.grossStrategy;
import static com.el.betting.sdk.v2.market.AffiliateStrategy.netStrategy;


public enum Bookmaker implements GameOddsProvider, Serializable {
    Bet365("Bet 365", "", true, 100, NONE),
    SkyBet("Sky Bet", "", true, true, 100, grossStrategy(25)),
    Totesport("Totesport", "", false, 98, "Working only for UK (checking by ip)", NONE),
    Boylesports("Boylesports", "boylesports.com", true, 93, grossStrategy(25)),
    Betfred("Betfred", "betfred.com", true, 95, grossStrategy(20)),
    SportingBet("Sportingbet", "sportingbet.com", true, 99, grossStrategy(25)),
    BetVictor("Bet Victor", "betvictor.com", true, 100, grossStrategy(30)),
    PaddyPower("Paddy Power", "paddypower.com", true, 100, grossStrategy(25)),
    StanJames("Stan James", "stanjames.com", true, 100, NONE),
    _888sport("888sport", "888sport.com", false, 93, "Flash UI", 0, "default", grossStrategy(20)),
    Ladbrokes("Ladbrokes", "ladbrokes.com", true, true, 100, grossStrategy(25)),
    Coral("Coral", "*coral.co.uk", true, true, 100, netStrategy(25, 16.7)),
    WilliamHill("William Hill", "williamhill.com", true, true, 100, grossStrategy(30)),
    Winner("Winner", "*winner.com", true, 99, grossStrategy(20)),
    Spreadex("Spreadex", "*spreadex.com", true, 93, "working with one more click", NONE),
    BetfairSportsbook("Betfair Sportsbook", "betfair.com", true, 98, "working with one more click", grossStrategy(30)),
    Betway("Betway", "betway.com", true, 93, NONE),
    Bwin("Bwin", "*bwin.com", true, 100, grossStrategy(10)),
    Unibet("Unibet", "unibet.com", false, 100, "flash UI", grossStrategy(20)),
    YouWin("You Win", "youwin.com", true, 91, "working with one more click", NONE),
    _32RedBet("32Red Bet", "32redbet.com", false, "flash UI", NONE),
    Betfair("Betfair", "*betfair.com", true, true, 100, "Needs one more click and has back / lay logic", 5, NONE),
    Betdaq("Betdaq", "betdaq.com", true, 93, "", 2, NONE),
    Matchbook("Matchbook", "matchbook.com", true, true, 92, "Needs one more click and has back / lay logic", 1, NONE),
    _10BET("10Bet", "10bet.com", true, true, 100, grossStrategy(25)),
    BetButler("BetButler", "betbutler.co.uk", true, NONE),
    Betinternet("Betinternet", "Betinternet.com", true, 89, NONE),
    BetRally("BetRally", "betrally.com", true, 90, NONE),
    Marathonbet("Marathon Bet", "Marathonbet.co.uk", true, 94, grossStrategy(30)),
    TitanBet("Titan Bet", "sports.titanbet.com", true, 91, NONE),
    Comeon("comeon", "comeon.com", true, 90, NONE),
    _5Dimes("5Dimes", "5dimes.eu", false, 96, grossStrategy(22)),
    Pinnacle("Pinnacle Sports", "pinnaclesports.com", false, true, 100, NONE),
    SBOBET("SBOBET", "sbobet.com", false, 95, NONE),
    SportsBet("Sportsbet Australia", "sportsbet.com.au", true, true, 92, netStrategy(25, 11.6)),
    _188bet("188bet", "188bet.com", false, 94, NONE),
    RedKings("bet.redkings.com", "bet.redkings.com", false, 93, NONE),
    DafaBet("sportsbook.dafabet.com", "sportsbook.dafabet.com", false, 93, grossStrategy(25)),
    NordicBet("nordicbet.com", "nordicbet.com", false, 90, grossStrategy(20)),
    Bookmaker_eu("http://www.bookmaker.eu", "bookmaker.eu", false, 91, NONE),
    Betcris("betcris", "http://www.betcris.com", false, 91, NONE),
    Betdsi("betdsi.eu", "betdsi.eu", false, 90, NONE),
    Bovada("bovada.lv", "bovada.lv", false, 92, NONE),
    BetCity("www.betsbc.com", "www.betsbc.com", 87, NONE),
    Heritagesports("heritagesports.eu", "heritagesports.eu", false, 90, NONE),
    Betonline("betonline.ag", "betonline.ag", false, 89, NONE),
    SportsInteraction("sportsinteraction", "sportsinteraction.com", false, 90, NONE),
    SportsBetting("sportsbetting", "sportsbetting.ag", false, 90, NONE),
    Interwetten("Interwetten", "interwetten.com", false, 79, NONE),
    CetreBet("cetrebet", "cetrebet.com", false, 79, NONE),
    Bodog("bodog", "bodog.eu", false, 90, NONE),
    _12bet("12bet", "12bet.com", false, 90, NONE),
    Samvo("samvo", "samvo.com", false, 89, NONE),
    PokerStars("pokerstars", "pokerstars.com", false, 89, NONE),
    SuperLenny("superlenny", "superlenny.com", false, 89, NONE),
    PartyBets("partybets", "partybets.com", false, 88, NONE),
    BetAmerica("betamerica", "betamerica", false, 87, NONE),
    BetClic("betclic", "betclic.com", false, 92, NONE),
    Betsson("betsson", "betsson.com", false, 90, NONE),
    ColossusBets("ColossusBets", "ColossusBets.com", false, 88, NONE),
    MyBet("mybet", "mybet.com", false, 90, NONE),
    Fulltilt("fulltilt", "fulltilt", false, 87, NONE),
    Fun88("fun88.com", "fun88.com", false, 87, NONE),
    GoldBetSports("goldbetsports", "goldbetsports.com", false, 86, NONE),
    KingPlayer("kingplayer", "kingplayer.com", false, 89, NONE),
    Mansion88("mansion88", "mansion88.com", false, 89, NONE),
    MobilBet("mobilbet", "mobilbet.com", false, 90, NONE),
    BetRedKings("BetRedKings", "BetRedKings.com", false, 90, NONE),
    BetSafe("Betsafe", "Betsafe.com", false, 92, NONE),
    BetAtHome("bet-at-home", "bet-at-home.com", false, true, 91, grossStrategy(29.8)),
    Redkings("bet.redkings", "bet.redkings", false, 90, NONE),

    @Deprecated
    _1Bet2Bet("1Bet2Bet", "1Bet2Bet", false, -100, NONE),
    _1XBet("1xBet", "1xbet.com", false, -100, NONE),
    BetBright("BetBright", "Betbright.com", false, 3, NONE),
    FIVE_PERCENT("Test", "test.com", false, -100, "", 5, grossStrategy(30)),
    ZERO_PERCENT("Test2", "test.com", false, -100, "", 0, grossStrategy(0)),

    //testing
    ZERO_PERCENT_AFFILIATE_25("Test", "", false, -1, grossStrategy(25)),
    FIVE_PERCENT_AFFILIATE("Test", "", false, -1, "", 5, grossStrategy(25)),
    ZERO_PERCENT_AFFILIATE("Test", "", false, -1, "", 0, grossStrategy(20)),
    TEN_PERCENT_AFFILIATE( "Test", "", false, -1, "", 10, grossStrategy(30));

    private AffiliateStrategy affiliateStrategy;
    private String name;
    private String url;
    private final String profileStrategy;
    private boolean webCrawler;
    private int confidence;
    private String info;
    private final double taxPercent;
    private boolean httpCrawler = false;

    private static Map<String, Bookmaker> bookmakerMap = new HashMap<>();
    private static Map<String, Bookmaker> httpBookmakers = new HashMap<>();
    private static List<Bookmaker> supportedBookmakers = new LinkedList<>();

    static {
        for (Bookmaker bookmaker : Bookmaker.values()) {
            bookmakerMap.put(bookmaker.getName(), bookmaker);
            if (bookmaker.isHttpCrawler()) {
                httpBookmakers.put(bookmaker.getName(), bookmaker);
            }

            if(bookmaker.getConfidence() > 88) {
                supportedBookmakers.add(bookmaker);
            }
        }
    }

    Bookmaker(String name, int confidence) {
        this(name, "", confidence);
    }

    Bookmaker(String name, String url) {
        this(name, url, true, NONE);
    }

    Bookmaker(String name, String url, int confidence) {
        this(name, url, true, confidence, "", NONE);
    }

    Bookmaker(String name, String url, int confidence, AffiliateStrategy affiliateStrategy) {
        this(name, url, true, confidence, "", affiliateStrategy);
    }

    Bookmaker(String name, String url, boolean webCrawler, AffiliateStrategy affiliateStrategy) {
        this(name, url, webCrawler, -1, "", affiliateStrategy);
    }

    Bookmaker(String name, String url, boolean webCrawler, int confidence, AffiliateStrategy affiliateStrategy) {
        this(name, url, webCrawler, confidence, "", affiliateStrategy);
    }

    Bookmaker(String name, String url, boolean webCrawler, boolean httpCrawler, int confidence, AffiliateStrategy affiliateStrategy) {
        this(name, url, webCrawler, confidence, "", affiliateStrategy);
        this.httpCrawler = httpCrawler;
    }

    Bookmaker(String name, String url, boolean webCrawler, String info, AffiliateStrategy affiliateStrategy) {
        this(name, url, webCrawler, -1, info, affiliateStrategy);
    }

    Bookmaker(String name, String url, boolean webCrawler, int confidence, String info, AffiliateStrategy affiliateStrategy) {
        this(name, url, webCrawler, confidence, info, 0, affiliateStrategy);
    }

    Bookmaker(String name, String url, boolean webCrawler, int confidence, String info, double taxPercent, AffiliateStrategy affiliateStrategy) {
        this(name, url, webCrawler, confidence, info, taxPercent, "DEFAULT", affiliateStrategy);
    }

    Bookmaker(String name, String url, boolean webCrawler, boolean httpCrawler, int confidence, String info, double taxPercent, AffiliateStrategy affiliateStrategy) {
        this(name, url, webCrawler, httpCrawler, confidence, info, taxPercent, "DEFAULT", affiliateStrategy);
    }

    Bookmaker(String name, String url, boolean webCrawler, int confidence, String info, double taxPercent, String profileStrategy, AffiliateStrategy affiliateStrategy) {
        this(name, url, webCrawler, false, confidence, info, taxPercent, profileStrategy, affiliateStrategy);
    }

    Bookmaker(String name, String url, boolean webCrawler, boolean httpCrawler, int confidence, String info, double taxPercent, String profileStrategy, AffiliateStrategy affiliateStrategy) {
        this.name = name;
        this.url = url;
        this.webCrawler = webCrawler;
        this.httpCrawler = httpCrawler;
        this.confidence = confidence;
        this.info = info;
        this.taxPercent = taxPercent;
        this.profileStrategy = profileStrategy;
        this.affiliateStrategy = affiliateStrategy;
    }

    public String getName() {
        return name;
    }

    public boolean isWebCrawler() {
        return webCrawler;
    }

    public String getUrl() {
        return url;
    }

    public int getConfidence() {
        return confidence;
    }

    public double getTaxPercent() {
        return taxPercent;
    }

    public boolean isHttpCrawler() {
        return httpCrawler;
    }

    public String getProfileStrategy() {
        return profileStrategy;
    }

    public AffiliateStrategy getAffiliateStrategy() {
        return affiliateStrategy;
    }

    public static Bookmaker getBookmaker(String bookmakerName) throws BookmakerNotFoundException {
        if (bookmakerMap.containsKey(bookmakerName)) {
            return bookmakerMap.get(bookmakerName);
        }

        throw new BookmakerNotFoundException(bookmakerName);
    }

    public static List<Bookmaker> getSupportedBookmakers() {
        return supportedBookmakers;
    }

    public static Collection<Bookmaker> getHttpCrawlerBookmakers() {
        return httpBookmakers.values();
    }

    public boolean isExchangeBookie() {
        return Arrays.asList(Matchbook, Betfair).contains(this);
    }
}
