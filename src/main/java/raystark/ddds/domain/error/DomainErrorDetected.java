package raystark.ddds.domain.error;

/**
 * ドメインレイヤーで何らかの処理が失敗した事を表す型です。
 *
 * <p>DomainErrorDetectedの実装者は次の様な形式のメソッドを提供するべきです。
 *
 * <pre> {@code
 *     public static <T> Either<DomainErrorDetected, T> withMessage(String message)
 * }</pre>
 *
 * このメソッドは指定したメッセージを保持したDomainErrorDetectedを生成します。実装、及び利用例はNullDetectedを参照してください。
 */
public interface DomainErrorDetected {

    /**
     * この失敗のメッセージを返します。
     *
     * <p>メッセージは開発者が呼んで理解できる形式の文字列です。
     * このメソッドから返される文字列の形式は実装者の自由です。
     * 利用者はこのメソッドから返される文字列を解析するプログラムを書くべきではありません。
     *
     * @return 失敗メッセージ
     */
    String message();
}
