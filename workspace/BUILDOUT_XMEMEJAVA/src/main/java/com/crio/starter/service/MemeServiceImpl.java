package com.crio.starter.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.crio.starter.data.Meme;
import com.crio.starter.repository.MemeRepository;
import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
@Service

public class MemeServiceImpl implements MemeService {
    private Long autoIncrement = (long) 0;
    @Autowired
    private MemeRepository memeRepository;
    // @Autowired
    // private MongoTemplate mongoTemplate;

    // @Override
    public Meme saveMeme(Meme meme) {
       // System.out.println(meme);
        List<Meme> memes =  memeRepository.findAll();
        for(Meme i:memes){
           if(i.getName().equals(meme.getName()) && i.getUrl().equals(meme.getUrl()) && i.getCaption().equals(meme.getCaption())) return null;
        }
        if(memes.size() == 0){
            autoIncrement++;
        }else {
            autoIncrement = memes.get(memes.size()-1).getId()+1;
        }
         
        meme.setId(autoIncrement);
        Meme meme2 = memeRepository.save(meme);
        System.out.println(meme2);
        return meme2;
    }

    @Override
    public List<Meme> latest100Meme() {
        List<Meme> memes =  memeRepository.findAll();
         if(memes.size() > 100){
            List<Meme> memes2 = new ArrayList<>();
            for(int i = memes.size()-1; i>memes.size()-100-1; i--){
                memes2.add(memes.get(i));
            }
            
            return memes2;
         }
         Collections.sort(memes, (a, b) -> Long.compare(b.getId(), a.getId()));

         return memes;
    }

    @Override
    public Meme getMemeById(Long id) {
        // TODO Auto-generated method stub
       Meme meme = memeRepository.getMemeById(id);
       return meme;
    }
    
}
