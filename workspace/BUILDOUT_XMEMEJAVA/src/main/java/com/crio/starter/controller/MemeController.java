package com.crio.starter.controller;

import lombok.RequiredArgsConstructor;
import java.util.List;
import com.crio.starter.data.Meme;
import com.crio.starter.service.MemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MemeController {
    @Autowired
    private MemeService memeService;
    @PostMapping("/memes/")
    public ResponseEntity<Meme> saveUsers(@RequestBody Meme meme){
        System.out.println(meme);
        if(meme.getName() == null || meme.getUrl() == null || meme.getCaption() == null ) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       Meme responce =  memeService.saveMeme(meme);
       if(responce == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       return new ResponseEntity<>(responce, HttpStatus.ACCEPTED);
    }
    @GetMapping("/memes/")
    public ResponseEntity<List<Meme>> getLatest100Memes(){
        List<Meme> rMemes =  memeService.latest100Meme();
        if(rMemes.size() == 0) return new ResponseEntity<>(rMemes,HttpStatus.OK);
        return new ResponseEntity<>(rMemes,HttpStatus.OK);

    }
    @GetMapping("/memes/{id}")
    public ResponseEntity<Meme> getMemeById(@PathVariable(value = "id") Long id){
        Meme meme = memeService.getMemeById(id);
       if(meme == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(meme,HttpStatus.OK);
    }

    @GetMapping("/src")
    public ResponseEntity<String> getMemes(){
        String str = "welcome";
       return new ResponseEntity<>(str,HttpStatus.OK);
    }
    
}
