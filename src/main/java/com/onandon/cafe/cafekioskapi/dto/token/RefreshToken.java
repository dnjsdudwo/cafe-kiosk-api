package com.onandon.cafe.cafekioskapi.dto.token;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class RefreshToken {
    private Long refreshTokenId;
    private String refreshToken;
    private String keyId;
}
