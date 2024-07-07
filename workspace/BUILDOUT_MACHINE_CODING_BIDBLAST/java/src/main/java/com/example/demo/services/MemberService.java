package com.example.demo.services;

import java.util.List;
import com.example.demo.entities.Members;
import com.example.demo.repositories.IMemberRepository;

public class MemberService {

    private IMemberRepository memberRepository;

    

    public MemberService(IMemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }



    public Members AddMembers(List<String> values){
        return memberRepository.saveMember(new Members(null, values.get(1), Long.parseLong(values.get(2))));
    }
    
}
