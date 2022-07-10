package com.hh99team11.backend.controller;

import com.hh99team11.backend.dto.NearByUserResponseDto;
import com.hh99team11.backend.model.User;
import com.hh99team11.backend.security.UserDetailsImpl;
import com.hh99team11.backend.service.MapService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MapController {
    private final MapService mapService;

    @GetMapping("/location")
    public List<NearByUserResponseDto> getNearByUsers(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        User user = userDetails.getUser();
        return mapService.getNearByUsers(user);
    }
}
