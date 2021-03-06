/*
 * Copyright © 2011-2013 the spray project <http://spray.io>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package spray.routing

class ChunkingDirectivesSpec extends RoutingSpec {

  "The `autoChunk` directive" should {
    "produce a correct chunk stream" in {
      val text = "This is a somewhat lengthy text that is being chunked by the autochunk directive!"
      Get() ~> autoChunk(8) { complete(text) } ~> check {
        chunks must haveSize(10)
        val bytes = chunks.foldLeft(body.data.toByteArray)(_ ++ _.data.toByteArray)
        new String(bytes) === text
      }
    }
  }
}