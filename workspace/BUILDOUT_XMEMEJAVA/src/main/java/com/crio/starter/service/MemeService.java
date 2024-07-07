package com.crio.starter.service;

import java.util.List;
import com.crio.starter.data.Meme;

public interface MemeService {
    public Meme saveMeme(Meme meme);
    public List<Meme> latest100Meme();
    public Meme getMemeById(Long id);
}
