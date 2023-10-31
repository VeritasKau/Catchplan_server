

package Sanhak.wakeUp.team.global.utils.valid;

import Sanhak.wakeUp.team.member.controller.MemberInfoController;
import io.jsonwebtoken.*;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class TokenValidator {
      private static final Logger logger = LoggerFactory.getLogger(MemberInfoController.class);




    @Value("${token.secret}")
    private String secret;

    public Claims validateToken(String token) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(secret.getBytes())
                    .parseClaimsJws(token)
                    .getBody();
            return claims;
        } catch (ExpiredJwtException e) {
            //      logger.error("Token is expired: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            //     logger.error("Malformed token: {}", e.getMessage());
            // MalformedJwtException가 발생했을 때 추가 작업을 수행하려면 여기에 추가 코드를 작성하세요.
            // 예를 들어, 잘못된 토큰을 처리하거나 적절한 응답을 반환할 수 있습니다.
        } catch (SignatureException e) {
            //     logger.error("Invalid token signature: {}", e.getMessage());
            // SignatureException가 발생했을 때 추가 작업을 수행하려면 여기에 추가 코드를 작성하세요.
            // 예를 들어, 잘못된 서명을 처리하거나 적절한 응답을 반환할 수 있습니다.
        } catch (Exception e) {
            //    logger.error("Token validation failed: {}", e.getMessage());
        }
        return null;
    }
}









