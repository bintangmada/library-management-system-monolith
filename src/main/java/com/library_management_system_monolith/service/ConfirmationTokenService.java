package com.library_management_system_monolith.service;

import com.library_management_system_monolith.entity.UserConfirmationToken;
import com.library_management_system_monolith.repository.ConfirmationTokenRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ConfirmationTokenService {

    private final ConfirmationTokenRepository confirmationTokenRepository;

    public ConfirmationTokenService(ConfirmationTokenRepository confirmationTokenRepository) {
        this.confirmationTokenRepository = confirmationTokenRepository;
    }

    public void saveConfirmationToken(UserConfirmationToken token){
        confirmationTokenRepository.save(token);
    }

    public Optional<UserConfirmationToken> getToken(String token){
        return confirmationTokenRepository.findByToken(token);
    }

    public int setConfirmedAt(String token) {
        return confirmationTokenRepository.updateConfirmationToken(
                token, LocalDateTime.now());
    }


}
