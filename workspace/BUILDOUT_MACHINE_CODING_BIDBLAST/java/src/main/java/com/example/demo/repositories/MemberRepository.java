package com.example.demo.repositories;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import com.example.demo.entities.Members;

public class MemberRepository implements IMemberRepository {

      private Map<Long,Members> membersMap = new LinkedHashMap<>();
      private AtomicLong autoIncrement = new AtomicLong(1);

    public MemberRepository(Map<Long, Members> membersMap) {
        this.membersMap = membersMap;
        this.autoIncrement = new AtomicLong(membersMap.size());
    }
    public MemberRepository() {

    }
     
    @Override
    public Members saveMember(Members members) {
        // TODO Auto-generated method stub
        Members g = new Members(autoIncrement.getAndIncrement(), members.getMemberName(),members.getCrioCoins());
        membersMap.putIfAbsent(g.getId(),g);
        return g;

    }

    @Override
    public Members findMemberById(Long id) {
        // TODO Auto-generated method stub
        return membersMap.get(id);
    }

    @Override
    public Optional<Members> findMemberByName(String name) {
        // User user = this.userMap.get(name);
        // userMap.values().stream().filter(t->t.getName().equals(name)).anyMatch().tocollect();
        Optional<Members> optUser = Optional.empty();
        for (Members i : membersMap.values()) {
            if (i.getMemberName().equals(name))
                optUser = Optional.of(i);
        }
        return optUser;
    }
    public Optional<Boolean> existsById(Long id) {
        return Optional.ofNullable(membersMap.containsKey(id));
    }
    @Override
    public Members updateMember(Members members) {
        // TODO Auto-generated method stub

        return  membersMap.put(members.getId(), members);
        
    }
}
