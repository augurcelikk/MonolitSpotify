package com.ugur.MonolitSpotify.mapper;

import com.ugur.MonolitSpotify.dto.request.SaveUserProfileRequestDto;
import com.ugur.MonolitSpotify.repository.entity.UserProfile;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;



@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserProfileMapper {
    UserProfileMapper INSTANCE = Mappers.getMapper(UserProfileMapper.class);

    UserProfile userProfileFromDto(SaveUserProfileRequestDto dto);

    SaveUserProfileRequestDto toDto_Method_Adi_Cok_Onemli_Degil(UserProfile userProfile);
}
