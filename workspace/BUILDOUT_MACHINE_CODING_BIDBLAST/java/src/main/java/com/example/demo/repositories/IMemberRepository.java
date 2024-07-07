package com.example.demo.repositories;

import java.util.Optional;
import com.example.demo.entities.Members;

public interface IMemberRepository{
    public Members saveMember(Members members);
    public Members updateMember(Members members);
    public Members findMemberById(Long id);
    public Optional<Members> findMemberByName(String name);
    Optional<Boolean> existsById(Long id);


}