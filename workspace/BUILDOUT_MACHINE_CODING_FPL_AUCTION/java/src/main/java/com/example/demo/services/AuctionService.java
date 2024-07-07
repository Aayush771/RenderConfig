package com.example.demo.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import com.example.demo.entities.Auction;
import com.example.demo.entities.AuctionStatus;
import com.example.demo.entities.AuctionedPlayers;
import com.example.demo.entities.BidResult;
import com.example.demo.entities.Bids;
import com.example.demo.entities.Players;
import com.example.demo.entities.Team;
//import com.example.demo.repositories.AuctionedPlayersRepository;
import com.example.demo.repositories.IAuctionRepository;
import com.example.demo.repositories.IAuctionedPlayersRepository;
import com.example.demo.repositories.IPlayersRepository;
import com.example.demo.repositories.ITeamRepository;

public class AuctionService implements IAuctionService {

    private final IAuctionRepository auctionRepository;
    private final IPlayersRepository playersRepository;
    private final ITeamRepository teamRepository;
    private final IAuctionedPlayersRepository auctionedPlayersRepository;

    public AuctionService(IAuctionRepository auctionRepository,
            IPlayersRepository playersRepository, ITeamRepository teamRepository,
            IAuctionedPlayersRepository auctionedPlayersRepository) {
        this.auctionRepository = auctionRepository;
        this.playersRepository = playersRepository;
        this.teamRepository = teamRepository;
        this.auctionedPlayersRepository = auctionedPlayersRepository;
    }

    @Override
    public Auction create(Auction auction) {
        // TODO Auto-generated method stub
        playersRepository.findById(auction.getPlayerId())
                .orElseThrow(() -> new RuntimeException("PLAYER_NOT_EXIST"));
        teamRepository.findTeamByPlayerId(auction.getPlayerId()).ifPresent(i -> {
            throw new RuntimeException("TEAM_ALREADY_EXISTS");
        });
        return auctionRepository.save(auction);
    }



    @Override
    public BidResult registerBids(Long auctionId, Bids bids) {
        // TODO Auto-generated method stub
        // auctionRepository.findById(auctionId).orElseThrow()
        Optional<Auction> optAuction = auctionRepository.findById(auctionId);
        playersRepository.findById(optAuction.get().getPlayerId())
                .orElseThrow(() -> new RuntimeException("PLAYER_NOT_EXIST"));
        Team team = teamRepository.findById(bids.getTeamId())
                .orElseThrow(() -> new RuntimeException("TEAM_NOT_EXIST"));

        BidResult bidResult = null;
        if (optAuction.isPresent()) {
            Auction auction = optAuction.get();
            List<Bids> bids2 = auction.getBids();
            bids2.add(bids);
            auction.setBids(bids2);
            bidResult = new BidResult(team.getTeamName(), bids.getBidAmount());
        }

        return bidResult;
    }

    public Team declearWinner(Long auctionId) {
        Auction auction = auctionRepository.findById(auctionId).get();
        Players player = playersRepository.findById(auction.getPlayerId())
                .orElseThrow(() -> new RuntimeException("PLAYER_NOT_EXIST"));
        List<Bids> bList = auction.getBids();

        Collections.sort(bList, (a, b) -> b.getBidAmount().compareTo(a.getBidAmount()));
        Bids winnerBid = bList.get(0);
        Long winnerTeamId = winnerBid.getTeamId();

        Team team = teamRepository.findById(winnerTeamId).get();
        List<Long> playeList = team.getPlayerId();
        playeList.add(auction.getPlayerId());
        team.setPlayerId(playeList);
        team.setWalletMoney(team.getWalletMoney() - winnerBid.getBidAmount());;
        teamRepository.save(team);
        player.setAuctionStatus(AuctionStatus.SOLD);
        playersRepository.save(player);
        auctionedPlayersRepository.save(new AuctionedPlayers(auction.getPlayerId(),
                player.getPlayerName(), team.getTeamName(), winnerBid.getBidAmount()));
        return team;

    }

    public String getAllPlyaersByTeamId(Long id) {
        Team team = teamRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("TEAM_NOT_EXIST"));
        List<Long> playerIds = team.getPlayerId();
        List<Players> pList = new ArrayList<>();

        for (Long i : playerIds) {
            pList.add(playersRepository.findById(i).get());
        }
        StringBuilder sb = new StringBuilder();
        sb.append(team.getTeamName() + " " + team.getWalletMoney() + " ");
        for (Players i : pList) {
            sb.append(i.getPlayerName() + " ");
        }
        return sb.toString();
    }

    public String getPlayerDetails(Long playerId){
        Players player = playersRepository.findById(playerId)
                .orElseThrow(() -> new RuntimeException("PLAYER_NOT_EXIST"));
        if(player.getAuctionStatus()==AuctionStatus.UNSOLD){
            return player.getPlayerName()+" "+player.getAuctionStatus();
        }else {
            AuctionedPlayers auctionedPlayers = auctionedPlayersRepository.findById(player.getId()).get();
            return auctionedPlayers.getPlayerName()+" "+player.getAuctionStatus()+" "+auctionedPlayers.getTeamName()+" "+auctionedPlayers.getAuctionedAmount();
        }
        // auctioned players 
     }
}
