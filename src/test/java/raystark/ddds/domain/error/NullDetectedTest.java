package raystark.ddds.domain.error;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class NullDetectedTest {
    @Nested
    class WithMessage {
        @Test
        void 引数がnullの場合指定しデフォルトのメッセージを持つNullDetectedが返される() {
            var nullPassed = assertDoesNotThrow(() -> NullDetected.withMessage(null));
            assertNotNull(nullPassed);
            assertNotNull(nullPassed.fold(
                DomainErrorDetected::message,
                right -> fail()
            ));
        }

        @Test
        void 正常系() {
            var notNullPassed = assertDoesNotThrow(
                () -> NullDetected.withMessage("myVariable must not be null.")
            );
            assertNotNull(notNullPassed);
            assertEquals("myVariable must not be null.", notNullPassed.fold(
                DomainErrorDetected::message,
                Object::toString
            ));
        }
    }
}
