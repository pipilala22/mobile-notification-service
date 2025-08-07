package com.bsb.mobilenotificationservice.api.sms.repository;

import com.bsb.mobilenotificationservice.api.sms.entity.sms.Sms;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SmsCodeRepository extends JpaRepository<Sms, Long> {

  @Query("SELECT s FROM Sms s WHERE s.recipientUserId = :userId and s.sessionId = :sessionId and s.codeValue = :codeValue and s.isChecked = false and s.isDetached = false")
  Optional<Sms> findActualSmsCode(@Param("userId") Long userId,
      @Param("sessionId") String sessionId, @Param("codeValue") String codeVale);

  @Query("SELECT s FROM Sms s WHERE s.recipientUserId = :userId and s.sessionId = :sessionId and s.isChecked = false and s.isDetached = false")
  Optional<Sms> findLastActualSmsCode(@Param("userId") Long userId,
      @Param("sessionId") String sessionId);

  @Modifying
  @Query("UPDATE Sms s SET s.isDetached = true, s.updatedAt = current_timestamp WHERE s.recipientUserId = :userId and s.sessionId = :sessionId and s.isDetached = false")
  void detachOldSmsCode(@Param("userId") Long userId, @Param("sessionId") String sessionId);

}
