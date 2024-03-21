package com.ugur.MonolitSpotify.repository.entity;

import com.ugur.MonolitSpotify.utility.enums.State;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "tbl_muzik_turu")
public class MuzikTuru {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String tur;
    @Enumerated(EnumType.STRING)
    State state;
}
