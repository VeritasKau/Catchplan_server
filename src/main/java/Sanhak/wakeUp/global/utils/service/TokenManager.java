package Sanhak.wakeUp.global.utils.service;

import Sanhak.wakeUp.global.utils.GrantType;
import Sanhak.wakeUp.global.utils.TokenType;
import Sanhak.wakeUp.global.utils.dto.JwtTokenDto;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;
import java.util.Date;

@Slf4j
@RequiredArgsConstructor
public class TokenManager {
    private final String accessTokenExpirationTime;
    private final String refreshTokenExpirationTime;
    private final String tokenSecret;

    public JwtTokenDto createJwtTokenDto(String uniqueUserInfo) {
        Date accessTokenExpireTime = createAccessTokenExpireTime();
        Date refreshTokenExpireTime = createRefreshTokenExpireTime();

        String accessToken = createAccessToken(uniqueUserInfo, accessTokenExpireTime);
        String refreshToken = createRefreshToken(uniqueUserInfo, refreshTokenExpireTime);
        return JwtTokenDto.builder()
                .grantType(GrantType.BEARER.getType())
                .accessToken(accessToken)
                .accessTokenExpireTime(accessTokenExpireTime)
                .refreshToken(refreshToken)
                .refreshTokenExpireTime(refreshTokenExpireTime)
                .build();

    }

    public Date createAccessTokenExpireTime() {
        return new Date(System.currentTimeMillis() + Long.parseLong(accessTokenExpirationTime));
    }

    public Date createRefreshTokenExpireTime() {
        return new Date(System.currentTimeMillis() + Long.parseLong(refreshTokenExpirationTime));
    }

    public String createAccessToken(String uniqueUserInfo, Date expirationTime) {
        String accessToken = Jwts.builder()
                .setSubject(TokenType.ACCESS.name()) //토큰 제목
                .setIssuedAt(new Date())             //토큰 발급 시간
                .setExpiration(expirationTime)       //토큰 만료 시간
                .claim("uniqueUserInfo", uniqueUserInfo) // 고유 사용자 정보
                .signWith(SignatureAlgorithm.HS256, tokenSecret.getBytes(StandardCharsets.UTF_8))
                .setHeaderParam("typ", "JWT")
                .compact();
        return accessToken;
    }


    public String createRefreshToken(String uniqueUserInfo, Date expirationTime) {
        String refreshToken = Jwts.builder()
                .setSubject(TokenType.REFRESH.name()) //토큰 제목
                .setIssuedAt(new Date())             //토큰 발급 시간
                .setExpiration(expirationTime)       //토큰 만료 시간
                .claim("uniqueUserInfo", uniqueUserInfo) // 고유 사용자 정보
                .signWith(SignatureAlgorithm.HS256, tokenSecret.getBytes(StandardCharsets.UTF_8))
                .setHeaderParam("typ", "JWT")
                .compact();
        return refreshToken;
    }
}
