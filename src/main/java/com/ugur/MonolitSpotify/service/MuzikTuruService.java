package com.ugur.MonolitSpotify.service;

import com.ugur.MonolitSpotify.repository.MuzikTuruRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MuzikTuruService{

    private final MuzikTuruRepository repository;

}
