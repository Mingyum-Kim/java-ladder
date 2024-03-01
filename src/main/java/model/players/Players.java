package model.players;

import exception.Message;
import java.util.List;
import java.util.Set;

public class Players {

    private static final int MIN_PLAYERS = 2;

    private final List<Player> players;

    public Players(List<String> players) {
        validate(players);
        this.players = convert(players);
    }

    private void validate(List<String> players) {
        validateSize(players);
        validateDuplicates(players);
    }

    private void validateSize(List<String> players) {
        if (players.size() < MIN_PLAYERS) {
            throw new IllegalArgumentException(Message.INVALID_PLAYER_ERROR.getMessage());
        }
    }

    private void validateDuplicates(List<String> players) {
        if (isDuplicated(players)) {
            throw new IllegalArgumentException(Message.INVALID_PLAYER_ERROR.getMessage());
        }
    }

    private boolean isDuplicated(List<String> players) {
        return Set.copyOf(players).size() != players.size();
    }

    private List<Player> convert(List<String> players) {
        return players.stream()
                .map(Player::new)
                .toList();
    }

    public List<String> getNames() {
        return players.stream()
                .map(Player::getName)
                .toList();
    }

    public int size() {
        return players.size();
    }

    public Position findPositionByName(final String name) {
        for (int index = 0; index < players.size(); index++) {
            if (players.get(index).getName().equals(name)) {
                return new Position(index);
            }
        }
        throw new IllegalArgumentException(Message.INVALID_PLAYER_ERROR.getMessage());
    }

    public Player findByName(final String name) {
        for (Player player : players) {
            if (player.getName().equals(name)) {
                return player;
            }
        }
        throw new IllegalArgumentException(Message.INVALID_PLAYER_ERROR.getMessage());
    }
}
