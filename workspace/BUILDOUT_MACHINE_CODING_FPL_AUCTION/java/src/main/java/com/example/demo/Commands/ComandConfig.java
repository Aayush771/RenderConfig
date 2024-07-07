package com.example.demo.Commands;

import java.util.List;
import com.example.demo.entities.Auction;
import com.example.demo.entities.BidResult;
import com.example.demo.entities.Bids;
import com.example.demo.entities.Players;
import com.example.demo.entities.Team;
import com.example.demo.repositories.AuctionRepository;
import com.example.demo.repositories.AuctionedPlayersRepository;
import com.example.demo.repositories.IAuctionRepository;
import com.example.demo.repositories.IAuctionedPlayersRepository;
import com.example.demo.repositories.IPlayersRepository;
import com.example.demo.repositories.ITeamRepository;
import com.example.demo.repositories.PlayerRepository;
import com.example.demo.repositories.TeamRepository;
import com.example.demo.services.AuctionService;
import com.example.demo.services.IAuctionService;
import com.example.demo.services.IPlayerService;
import com.example.demo.services.ITeamService;
import com.example.demo.services.PlayersService;
import com.example.demo.services.TeamService;

public class ComandConfig implements ICommandConfig {

    private final IPlayersRepository playersRepository = new PlayerRepository();
    private final IPlayerService playerService = new PlayersService(playersRepository);
    private final ITeamRepository teamRepository = new TeamRepository();
    private final ITeamService teamService = new TeamService(teamRepository);
    private final IAuctionRepository auctionRepository = new AuctionRepository();
    private final IAuctionedPlayersRepository auctionedPlayersRepository =
            new AuctionedPlayersRepository();
    private final IAuctionService auctionService = new AuctionService(auctionRepository,
            playersRepository, teamRepository, auctionedPlayersRepository);

    public void REGISTER_PLAYER(List<String> tokens) {
        Players player = playerService
                .create(new Players(null, tokens.get(1), Long.parseLong(tokens.get(2))));

        System.out.println("PLAYER_REGISTERED " + player.getId());
    }

    // LIST_GREETING
    public void REGISTER_TEAM(List<String> tokens) {
        Team team =
                teamService.create(new Team(null, tokens.get(1), Long.parseLong(tokens.get(2))));
        System.out.println("TEAM_REGISTERED " + team.getId());
    }

    @Override
    public void START_AUCTION(List<String> tokens) {
        // TODO Auto-generated method stub
        Auction auction = auctionService.create(new Auction(null, Long.parseLong(tokens.get(1))));
        System.out.println("AUCTION_STARTED " + auction.getId());
    }

    @Override
    public void BID_PLAYER(List<String> tokens) {

        Bids bids = new Bids(Long.parseLong(tokens.get(2)), Long.parseLong(tokens.get(3)));
        // TODO Auto-generated method stub
        BidResult bidResult = auctionService.registerBids(Long.parseLong(tokens.get(1)), bids);
        System.out.println(bidResult.getName() + " BID " + bidResult.getBidAmount());
    }

    @Override
    public void CLOSE_AUCTION(List<String> tokens) {
        // TODO Auto-generated method stub

        Team team = auctionService.declearWinner(Long.parseLong(tokens.get(1)));
        System.out.println(team.getTeamName() + " WON");

    }

    @Override
    public void TEAM_OVERVIEW(List<String> tokens) {
        // TODO Auto-generated method stub
        String response = auctionService.getAllPlyaersByTeamId(Long.parseLong(tokens.get(1)));
        System.out.println(response.trim());

    }

    @Override
    public void PLAYER_OVERVIEW(List<String> tokens) {
        // TODO Auto-generated method stub
        String response = auctionService.getPlayerDetails(Long.parseLong(tokens.get(1)));
        System.out.println(response);
    }


}
