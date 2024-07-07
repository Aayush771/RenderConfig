package com.example.demo.services;

import java.util.*;
import com.example.demo.Exceptions.MemberNotFoundException;
import com.example.demo.entities.Bids;
import com.example.demo.entities.Members;
import com.example.demo.repositories.IBidsRepository;
import com.example.demo.repositories.IEventRepository;
import com.example.demo.repositories.IMemberRepository;

public class BidService {
    private final IBidsRepository bidsRepository;
    private final IMemberRepository memberRepository;
    private final IEventRepository eventRepository;
    public BidService(IBidsRepository bidsRepository, IMemberRepository memberRepository,
            IEventRepository eventRepository) {
        this.bidsRepository = bidsRepository;
        this.memberRepository = memberRepository;
        this.eventRepository = eventRepository;
    }
     public Bids submitBid(List<String> values){

        Long memberId = Long.parseLong(values.get(1));
        Long eventId = Long.parseLong(values.get(2));
        memberRepository.existsById(memberId).orElseThrow(()-> new MemberNotFoundException("MEMBER_NOT_EXIST"));
        eventRepository.existsById(eventId).orElseThrow(()-> new MemberNotFoundException("EVENT_NOT_EXIST"));
  //      List<Long> bidAmounts = new ArrayList<>();
        Members member = memberRepository.findMemberById(memberId);
      Long maxValue = (long) 0;  
         for(int i = 3; i<values.size(); i++){
            Long value = Long.parseLong(values.get(i));
            if(member.getCrioCoins() >= value && value > 0){
               if(value>maxValue){
                  maxValue = value;
               }
            }else throw new MemberNotFoundException("You don't have enough crioCoins "+value);
            
         } 
          Long dedutedCoin = member.getCrioCoins()-maxValue;
          member.setCrioCoins(dedutedCoin);
          memberRepository.updateMember(member);
        // Collections.sort(bidAmounts);
        return bidsRepository.saveBids(new Bids(memberId, eventId, maxValue));
     }
    
      public String declareWinner(Long eventId){
      //   eventRepository.existsById(eventId).orElseThrow(()-> new MemberNotFoundException("Event Not Found"));
         
         List<Bids> bidsList = bidsRepository.findAll();
         List<Bids> eventBids = new ArrayList<>();
         for(Bids i:bidsList){
            if(i.getEventId()==eventId){
               eventBids.add(i);
            }
         }
      Collections.sort(eventBids,(a,b)-> b.getAmounts().compareTo(a.getAmounts()));
   List<Bids> winnerList = new ArrayList<>();
     Long winnerAmount = eventBids.get(0).getAmounts();
      for(int i = 1; i<eventBids.size();i++){
         Bids bids = eventBids.get(i);
          if(bids.getAmounts()==winnerAmount){
            winnerList.add(bids);
          } 
      }

      Bids winnerBid = null;
      if(winnerList.size()==1) winnerBid = winnerList.get(0);
      else{
           winnerBid = bidsList.stream()
                .filter(i -> i.getAmounts()==winnerAmount)
                .findFirst().get();  

                
      }

     
     //  if(bid1.getAmounts().get(bid1.getAmounts().size()-1))
       // System.out.println(eventBids);

         return memberRepository.findMemberById(winnerBid.getBidderId()).getMemberName();

      }
}