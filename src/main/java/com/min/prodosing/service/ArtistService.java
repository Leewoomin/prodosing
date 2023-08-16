package com.min.prodosing.service;

import com.min.prodosing.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArtistService {
    private final MemberRepository memberRepository;
}
