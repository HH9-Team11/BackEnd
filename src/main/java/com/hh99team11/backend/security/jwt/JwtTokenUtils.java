package com.hh99team11.backend.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.hh99team11.backend.security.UserDetailsImpl;

import java.util.Date;

public final class JwtTokenUtils {

    private static final int SEC = 1;
    private static final int MINUTE = 60 * SEC;
    private static final int HOUR = 60 * MINUTE;
    private static final int DAY = 24 * HOUR;

    // JWT 토큰의 유효기간: 3일 (단위: seconds)
    private static final int JWT_TOKEN_VALID_SEC = 3 * DAY;
    // JWT 토큰의 유효기간: 3일 (단위: milliseconds)
    private static final int JWT_TOKEN_VALID_MILLI_SEC = JWT_TOKEN_VALID_SEC * 1000;

    public static final String CLAIM_EXPIRED_DATE = "EXPIRED_DATE";
    public static final String CLAIM_USER_NAME = "USER_NAME";
    public static final String CLAIM_USER_ID = "USER_ID";
    public static final String CLAIM_PET_NAME = "PET_NAME";
    public static final String CLAIM_PET_SIZE = "PET_SIZE";
    public static final String CLAIM_PET_GENDER = "PET_GENDER";
    public static final String CLAIM_PET_AGE = "PET_AGE";
    public static final String CLAIM_USER_ADDRESS = "USER_ADDRESS";
    public static final String CLAIM_USER_LAT = "USER_LAT";
    public static final String CLAIM_USER_LNG = "USER_LNG";

    public static final String JWT_SECRET = "jwt_secret_!@#$%";

    public static String generateJwtToken(UserDetailsImpl userDetails) {
        String token = null;
        try {
            token = JWT.create()
                    .withIssuer("dogdog")
                    .withClaim(CLAIM_USER_NAME, userDetails.getUsername())
                    .withClaim(CLAIM_USER_ID, userDetails.getUser().getId())
                    .withClaim(CLAIM_PET_NAME, userDetails.getUser().getPetName())
                    .withClaim(CLAIM_PET_SIZE, String.valueOf(userDetails.getUser().getPetSizeType()))
                    .withClaim(CLAIM_PET_GENDER, String.valueOf(userDetails.getUser().getPetGender()))
                    .withClaim(CLAIM_PET_AGE, userDetails.getUser().getPetAge())
                    .withClaim(CLAIM_USER_ADDRESS, userDetails.getUser().getAddress())
                    .withClaim(CLAIM_USER_LAT, userDetails.getUser().getLat())
                    .withClaim(CLAIM_USER_LNG, userDetails.getUser().getLng())
                     // 토큰 만료 일시 = 현재 시간 + 토큰 유효기간)
                    .withClaim(CLAIM_EXPIRED_DATE, new Date(System.currentTimeMillis() + JWT_TOKEN_VALID_MILLI_SEC))
                    .sign(generateAlgorithm());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return token;
    }

    private static Algorithm generateAlgorithm() {
        return Algorithm.HMAC256(JWT_SECRET);
    }
}
