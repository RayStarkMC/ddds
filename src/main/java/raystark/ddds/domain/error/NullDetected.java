package raystark.ddds.domain.error;

import io.vavr.control.Either;

import static java.util.Objects.isNull;

public record NullDetected(String message) implements DomainError {
    public static <T> Either<DomainError, T> withMessage(String message) {
        return isNull(message)?
            Either.left(new NullDetected("NullDetected creation failed. message is null.")):
            Either.left(new NullDetected(message));
    }
}
