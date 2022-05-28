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

/**
 * Unit of Work APIを定義します。
 *
 * <p>このパッケージで実装されるUnit of Workパターンは実際にはUnit of Work in Repositoryパターンに近いです。
 * ただしRepositoryはUoW APIのクライアントには直接公開されず、
 * 代わりにCommandModelの読み取り専用APIであるRepositoryRegistryが公開されています。
 */
package raystark.ddds.uow;