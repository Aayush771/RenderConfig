package com.example.demo.services;

import java.util.List;
import com.example.demo.Exceptions.MemberNotFoundException;
import com.example.demo.entities.Event;
import com.example.demo.entities.Members;
import com.example.demo.entities.RegistrationResult;
import com.example.demo.repositories.IEventRepository;
import com.example.demo.repositories.IMemberRegisterRepository;
import com.example.demo.repositories.IMemberRepository;

public class MemberRegisterService {
    private IMemberRegisterRepository memberRegisterRepository;
    private IMemberRepository memberRepository;
    private IEventRepository eventRepository;
    public MemberRegisterService(IMemberRegisterRepository memberRegisterRepository,
            IMemberRepository memberRepository, IEventRepository eventRepository) {
        this.memberRegisterRepository = memberRegisterRepository;
        this.memberRepository = memberRepository;
        this.eventRepository = eventRepository;
    }

    // public MemberRegisterService(MemberRegisterRepository memberRegisterRepository2,
    //         IMemberRepository memberRepository2, IEventRepository eventRepository2) {}

    public RegistrationResult registerMember(List<String> values){

        Long memberId = Long.parseLong(values.get(1));
        Long eventId = Long.parseLong(values.get(2));
        memberRepository.existsById(memberId).orElseThrow(()-> new MemberNotFoundException("MEMBER_NOT_EXIST"));
        eventRepository.existsById(eventId).orElseThrow(()-> new MemberNotFoundException("EVENT_NOT_EXIST"));
         Members member = memberRepository.findMemberById(memberId);
         Event event = eventRepository.findEventById(eventId);
         
       memberRegisterRepository.registerMember(memberId, eventId);
       return new RegistrationResult(member.getMemberName(),event.getEventName());

    }
    
}
