package com.ugur.MonolitSpotify.controller;

import com.ugur.MonolitSpotify.dto.request.SaveUserProfileRequestDto;
import com.ugur.MonolitSpotify.dto.response.FindAllUserProfileResponseDto;
import com.ugur.MonolitSpotify.service.UserProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.ugur.MonolitSpotify.constants.RestApi.*;
@RestController
@RequestMapping(USER)
@RequiredArgsConstructor
public class UserProfileController {

    private final UserProfileService userProfileService;

//    @PostMapping(SAVE)
//    public ResponseEntity<UserProfile> save(String userName, String name,String email,String phone){
//        UserProfile userProfile = UserProfile.builder()
//                .userName(userName)
//
//                .build();
//        userProfileService.save(userProfile);
//        return ResponseEntity.ok(userProfile);
//    }



//    @GetMapping(FINDALL)
//    public ResponseEntity<List<UserProfile>> findAll(){
//        return ResponseEntity.ok(userProfileService.findAll());
//    }

    @PostMapping(SAVE)
    public ResponseEntity<Void> saveUserProfile(@RequestBody @Valid SaveUserProfileRequestDto dto){
        userProfileService.save(dto);
        return ResponseEntity.ok().build();
    }

    @GetMapping(FINDALL)
    public ResponseEntity<FindAllUserProfileResponseDto> findAllUserProfile(){
        /**
         * ok -> 200 başarılı kodu
         * badRequest -> 400 hata kodu
         */
        return ResponseEntity.ok(userProfileService.findAllUserProfile());
    }

    @GetMapping("/testException")
    public ResponseEntity<String> testException(String ifade){
        if(ifade.length()<10){
            throw new RuntimeException("girdiğiniz ifade 10 karakterden az olamaz");
        }
        return ResponseEntity.ok("Aferin başarılı");
    }


}
