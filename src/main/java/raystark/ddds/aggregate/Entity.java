package raystark.ddds.aggregate;

/**
 * 戦術的設計の構成要素であるエンティティの基底インターフェースです。
 *
 * <p>エンティティは自身を一意に識別する識別子を持っています。
 * エンティティの等価性は識別子の等価性により判断されます。
 *
 * <p>実装クラスは不変である必要があります。
 * ID属性の型はエンティティの実装型に対して固有である必要があります。
 * ID属性の型の実装は不変である必要があります。
 * このエンティティが集約ルードでない場合、ID値は集約について一意である必要があります。
 * このエンティティが集約ルートである場合、ID値はグローバルに一意である必要があります。
 *
 * @param <ID> 識別子の型
 */
public interface Entity<ID> {

    /**
     * このEntityの識別子を返します。
     *
     * @return 識別子
     */
    ID id();

    /**
     * このオブジェクトのハッシュコード値を返します。
     *
     * <p>実装ではentityHashCodeに処理を移譲する必要があります。
     *
     * @return ハッシュコード値
     */
    @Override
    int hashCode();

    /**
     * このオブジェクトが他のオブジェクトと等しいかどうかを示します。
     *
     * <p>実装ではentityEqualsに処理を異常する必要があります。
     *
     * @param o 比較対象のオブジェクト
     * @return このオブジェクトがoと等しい場合true、そうでない場合false
     */
    @Override
    boolean equals(Object o);

    /**
     * エンティティのハッシュコード値を計算します。
     *
     * @param entity エンティティ
     * @return エンティティのハッシュコード値
     */
    static int entityHashCode(Entity<?> entity) {
        return entity.id().hashCode();
    }

    /**
     * エンティティとオブジェクトが等しいかどうかを示します。
     *
     * @param entity エンティティ
     * @param o オブジェクト
     * @return entityがoと等しい場合true、そうでない場合false
     */
    static boolean entityEquals(Entity<?> entity, Object o) {
        return entity == o || (o instanceof Entity<?> e && entity.id().equals(e.id()));
    }
}
