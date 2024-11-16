package com.authenticationservice.domain.models.redis;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
@RedisHash("jwt_token")
public class JwtTokenRedisHash {
    @Id
    private String token;
    @TimeToLive
    private Long expiration;
    private String userEmail;
    private String tokenType;
}
