package raystark.ddds.domain.error;

import io.vavr.control.Either;

import static java.util.Objects.isNull;

/**
 * nullを検出した事を表します。
 *
 * <p>利用者はコンストラクタを利用しないでください。
 *
 * @param message メッセージ
 */
public record NullDetected(String message) implements DomainErrorDetected {

    /**
     * NullDetectedを生成します。
     *
     * <p>messageがnullの場合、その事実をメッセージに持つNullDetectedが生成されます。
     *
     * @param message メッセージ
     * @return 指定したメッセージを持つNullDetected
     * @param <T> 任意の型
     */
    public static <T> Either<DomainErrorDetected, T> withMessage(String message) {
        return isNull(message)?
            Either.left(new NullDetected("NullDetected creation failed. message is null.")):
            Either.left(new NullDetected(message));
    }
}
