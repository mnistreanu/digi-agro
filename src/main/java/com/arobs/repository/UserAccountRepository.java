package com.arobs.repository;

import com.arobs.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {

    @Query("SELECT ua FROM UserAccount ua WHERE ua.username = :username AND ua.active = true")
    UserAccount findByUsername(@Param("username") String username);

    @Query("SELECT ua FROM UserAccount ua LEFT JOIN ua.authorities authorities " +
            "WHERE ua.active = true AND " +
            " (SELECT auth FROM Authority auth WHERE auth.name = com.arobs.enums.AuthorityName.ROLE_USER) IN (authorities)")
    List<UserAccount> findUsers();

    @Query("SELECT ua FROM UserAccount ua LEFT JOIN ua.authorities authorities " +
            "WHERE ua.active = true AND " +
            " (SELECT auth FROM Authority auth WHERE auth.name = com.arobs.enums.AuthorityName.ROLE_ADMIN) IN (authorities)")
    List<UserAccount> findAdmins();

    @Modifying
    @Query("UPDATE UserAccount ua SET ua.active = false WHERE ua.id = :id")
    void remove(@Param("id") Long id);

    @Query("SELECT COUNT(ua) FROM UserAccount ua WHERE ua.username = :username AND ua.active = true")
    long countByUsername(@Param("username") String username);

    @Query("SELECT COUNT(ua) FROM UserAccount ua WHERE ua.username = :username AND ua.id <> :id AND ua.active = true")
    long countByUsernameEscapeId(@Param("id") Long id, @Param("username") String username);

}
