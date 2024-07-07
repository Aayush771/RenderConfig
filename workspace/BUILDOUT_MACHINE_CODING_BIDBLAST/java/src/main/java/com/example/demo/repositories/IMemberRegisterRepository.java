package com.example.demo.repositories;

import java.util.List;
import java.util.Optional;

public interface IMemberRegisterRepository {
    public void registerMember(Long memberId,Long eventId);
    public List<Long> findAllMembersForEvent(Long eventId);
    Optional<Boolean> existsByeventId(Long id);

    
}
