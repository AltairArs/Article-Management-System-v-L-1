package com.authenticationservice.repo.redis;

import com.authenticationservice.domain.models.redis.JwtTokenRedisHash;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JwtTokenRepository extends CrudRepository<JwtTokenRedisHash, String> {
}
