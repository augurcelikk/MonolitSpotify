package com.ugur.MonolitSpotify.service;

import com.ugur.MonolitSpotify.repository.CalmaListesiMuzikRepository;
import com.ugur.MonolitSpotify.repository.entity.CalmaListesiMuzik;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CalmaListesiMuzikService {
    private final CalmaListesiMuzikRepository repository;

    public void save(CalmaListesiMuzik entity){
        repository.save(entity);
    }

}
