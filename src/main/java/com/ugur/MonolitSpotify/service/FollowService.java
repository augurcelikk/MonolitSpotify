package com.ugur.MonolitSpotify.service;

import com.ugur.MonolitSpotify.repository.FollowRepository;
import com.ugur.MonolitSpotify.repository.entity.Follow;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FollowService {
    private final FollowRepository repository;

    public void save(Follow entity){
        repository.save(entity);
    }
}
