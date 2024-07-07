package com.example.demo.repositories;

import java.util.*;

public class MemberRegisterRepository implements IMemberRegisterRepository {

    private Map<Long, Set<Long>> eventResisterMap = new LinkedHashMap<>();

    @Override
    public void registerMember(Long memberId, Long eventId) {
        // TODO Auto-generated method stub
        
        Set<Long> memberSet = eventResisterMap.getOrDefault(eventId, new HashSet<>());

        // Add the memberId to the set
        memberSet.add(memberId);

        // Put the updated set back into the map
        eventResisterMap.put(eventId, memberSet);
    }

    @Override
    public List<Long> findAllMembersForEvent(Long eventId) {
        // TODO Auto-generated method stub
        return new ArrayList<>(eventResisterMap.get(eventId));
    }

    @Override
    public Optional<Boolean> existsByeventId(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

}
