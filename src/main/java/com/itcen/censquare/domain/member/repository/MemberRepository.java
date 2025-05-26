package com.itcen.censquare.domain.member.repository;

import com.itcen.censquare.domain.member.entity.Member;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

  Optional<Member> findByOauthIdAndProvider(String oauthId, String provider);

}
