package alexberemart.jHattrick.model.vo.matchLineUp;

import java.util.ArrayList;
import java.util.List;

public class LineUp {

    List<Player> players = new ArrayList<>();

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }
}
