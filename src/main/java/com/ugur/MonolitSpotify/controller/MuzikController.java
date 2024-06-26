package com.ugur.MonolitSpotify.controller;

import com.ugur.MonolitSpotify.dto.request.AddMusicForArtistRequestDto;
import com.ugur.MonolitSpotify.dto.request.SaveMuzikRequestDto;
import com.ugur.MonolitSpotify.dto.response.FindAllMuzikResponseDto;
import com.ugur.MonolitSpotify.repository.entity.Muzik;
import com.ugur.MonolitSpotify.service.MuzikService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.ugur.MonolitSpotify.constants.RestApi.*;
@RestController
@RequestMapping(MUZIK)
@RequiredArgsConstructor
public class MuzikController {
    private final MuzikService muzikService;

    @PostMapping(SAVE)
    public ResponseEntity<Void> saveMuzik(@RequestBody @Valid SaveMuzikRequestDto dto){
        muzikService.save(dto);
        return ResponseEntity.ok().build();
    }

    @GetMapping(FINDALL)
    public ResponseEntity<FindAllMuzikResponseDto> findAll(){
        FindAllMuzikResponseDto result = muzikService.findAll();
        return ResponseEntity.ok(result);
    }

    @PostMapping(ADDMUSICFORARTIST)
    public ResponseEntity<Void> addMusicForArtist(@RequestBody @Valid AddMusicForArtistRequestDto dto){
        muzikService.addMusicForArtist(dto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/getAllMusicFromArtistId/{id}")
    public ResponseEntity<List<Muzik>> getAllMusicFromArtistId(@PathVariable("id") Long id){
        List<Muzik> result = muzikService.findAllMuzikFromArtistId(id);
        return ResponseEntity.ok(result);
    }
}
