/*
 * Copyright 2022 RayStarkMC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package raystark.ddds.domain;

import io.vavr.control.Either;
import raystark.ddds.domain.error.DomainError;

/**
 * コマンドモデルに対するリポジトリを表すマーカーインターフェース。
 *
 * @param <A_ID> コマンドモデルの識別子の型
 * @param <A> コマンドモデルの型
 */
public interface CommandModelRepository<A_ID, A extends CommandModel<A_ID, ?, ?>> extends Repository<A_ID, A> {

    /**
     * 指定したIDのコマンドモデルを検索します。
     *
     * <p>発見されたコマンドモデルのイベントキューは空に指定されます。その他の仕様はRepositoryに準拠します。
     *
     * @param id ID値
     * @return 失敗情報、又は存在する場合指定したIDを持つコマンドモデル
     */
    @Override
    Either<DomainError, A> findByID(A_ID id);
}
