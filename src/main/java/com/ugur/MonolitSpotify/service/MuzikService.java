package com.ugur.MonolitSpotify.service;

import com.ugur.MonolitSpotify.dto.request.AddMusicForArtistRequestDto;
import com.ugur.MonolitSpotify.dto.request.SaveMuzikRequestDto;
import com.ugur.MonolitSpotify.dto.response.FindAllMuzikResponseDto;
import com.ugur.MonolitSpotify.exception.ErrorType;
import com.ugur.MonolitSpotify.exception.MonolitSpotifyException;
import com.ugur.MonolitSpotify.mapper.MuzikMapper;
import com.ugur.MonolitSpotify.repository.MuzikRepository;
import com.ugur.MonolitSpotify.repository.MuzikSanatciRepository;
import com.ugur.MonolitSpotify.repository.entity.Muzik;
import com.ugur.MonolitSpotify.repository.entity.MuzikSanatci;
import com.ugur.MonolitSpotify.utility.enums.State;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MuzikService {
    private final MuzikRepository repository;
    private final UserProfileService userProfileService;
    private final MuzikSanatciRepository muzikSanatciRepository;

    public void save(SaveMuzikRequestDto dto){
//        Muzik muzik = Muzik.builder()
//                .muzikUrl(dto.getMuzikUrl())
//                .aciklama(dto.getAciklama())
//                .tur(dto.getTur())
//                .name(dto.getName())
//                .sure(dto.getSure())
//                .kapakResmi(dto.getKapakResmi())
//                .build();
//        Muzik muzik = new Muzik();
//        muzik.setMuzikUrl(dto.getMuzikUrl());
//        muzik.setName(dto.getName());

       Muzik muzik = MuzikMapper.INSTANCE.muzikFromDto(dto);
       muzik.setState(State.PENDING);
       repository.save(muzik);
       // repository.save(MuzikMapper.INSTANCE.muzikFromDto(dto));
    }

    public FindAllMuzikResponseDto findAll() {
        return FindAllMuzikResponseDto.builder()
                .statusCode(100)
                .message("Müzik listesi getirildi.")
                .data(repository.findAll())
                .build();
    }

    /**
     * TODO: 1- Kullanıcı id mevcut mu bakılmalı
     * TODO: 2- Muzik id varmı kontrol edilmeli
     * TODO: 3- Kullanıcı id si verilen kişi sanatçı mı? kontrol edilecek.
     * TODO: 4- Eğer aynı kullanıcı id ve muzik id daha önce kayıt edilmiş ise tekrar kayıt edilememeli
     */
    public void addMusicForArtist(AddMusicForArtistRequestDto dto) {
       if(!userProfileService.existsById(dto.getArtistId()) || !userProfileService.isArtist(dto.getArtistId()))
           throw new MonolitSpotifyException(ErrorType.ARTIST_ERROR);
       else if (!repository.existsById(dto.getMusicId()))
           throw new MonolitSpotifyException(ErrorType.MUSIC_NOT_FOUND);
       muzikSanatciRepository.save(MuzikSanatci.builder()
                       .muzikId(dto.getMusicId())
                       .sanatciId(dto.getArtistId())
               .build());
    }

    /**
     * TODO: 1- ArtistId sini (UserProfileId si) kullanarak tbl_muziksanatci tablosundan ilgili id ye ait tüm kayıtlar çekilir.
     * TODO: 2- tbl_muziksanatci tablosundan gelen tüm kayıtların içinden muzik id leri bir liste içine alınır.
     * TODO: 3- muzik id leri bilinen liste muzikRepository e verilerek tüm müzik listesi çekilir.
     * TODO: YADA, bir @Query yazılarak aynı işlem tanımlanbilir.
     *
     * @param id
     * @return
     */
    public List<Muzik> findAllMuzikFromArtistId(Long id) {
        List<MuzikSanatci> muzikSanatciList = muzikSanatciRepository.findAllBySanatciId(id);
        List<Long> muzikIds = muzikSanatciList.stream().map(MuzikSanatci::getMuzikId).toList();
        return repository.findAllById(muzikIds);
    }
}
