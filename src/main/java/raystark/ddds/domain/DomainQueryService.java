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

/**
 * ドメイン知識のクエリサービスに対する基底マーカーインターフェースです。
 *
 * <p>ドメインクエリサービスはCQRSのクエリサービスではありません。
 * ドメインクエリサービスは単一の集約からは得られない、複数の集約にまたがるドメイン知識の検索を担当します。
 * 必要に応じてコマンドの引数として渡される事で、複数集約間の制約を表現出来ます。
 * ドメイン知識の検索はトランザクション内で行われます。
 *
 * <p>ドメインクエリサービスはデータストアに対して生成、更新、削除といった更新を行いません。
 */
public interface DomainQueryService {}
