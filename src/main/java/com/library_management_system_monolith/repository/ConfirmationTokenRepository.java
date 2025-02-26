package com.library_management_system_monolith.repository;

import com.library_management_system_monolith.entity.User;
import com.library_management_system_monolith.entity.UserConfirmationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
@Repository
@Transactional(readOnly = true)
public interface ConfirmationTokenRepository extends JpaRepository<UserConfirmationToken, Integer> {
    Optional<UserConfirmationToken> findByToken(String token);

    @Transactional
    @Modifying
    @Query(value = "UPDATE UserConfirmationToken a SET a.confirmedAt = ?2 WHERE a.token = ?1")
    public int updateConfirmationToken(String token, LocalDateTime confirmedAt);
}
