package tdd.chap09.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ErrorResponse {
    private final String message;
    private final String errorType;

    public static ErrorResponse error(Exception e) {
        return new ErrorResponse(e.getMessage(), e.getClass().getSimpleName());
    }
}
