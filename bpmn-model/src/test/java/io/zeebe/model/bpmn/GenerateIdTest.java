/*
 * Copyright © 2017 camunda services GmbH (info@camunda.com)
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

package io.zeebe.model.bpmn;

import static org.assertj.core.api.Assertions.assertThat;

import io.zeebe.model.bpmn.instance.Definitions;
import io.zeebe.model.bpmn.instance.Process;
import io.zeebe.model.bpmn.instance.StartEvent;
import io.zeebe.model.bpmn.instance.UserTask;
import org.junit.Test;

public class GenerateIdTest {

  @Test
  public void shouldNotGenerateIdsOnRead() {
    final BpmnModelInstance modelInstance =
        Bpmn.readModelFromStream(GenerateIdTest.class.getResourceAsStream("GenerateIdTest.bpmn"));
    final Definitions definitions = modelInstance.getDefinitions();
    assertThat(definitions.getId()).isNull();

    final Process process = modelInstance.getModelElementsByType(Process.class).iterator().next();
    assertThat(process.getId()).isNull();

    final StartEvent startEvent =
        modelInstance.getModelElementsByType(StartEvent.class).iterator().next();
    assertThat(startEvent.getId()).isNull();

    final UserTask userTask =
        modelInstance.getModelElementsByType(UserTask.class).iterator().next();
    assertThat(userTask.getId()).isNull();
  }

  @Test
  public void shouldGenerateIdsOnCreate() {
    final BpmnModelInstance modelInstance = Bpmn.createEmptyModel();
    final Definitions definitions = modelInstance.newInstance(Definitions.class);
    assertThat(definitions.getId()).isNotNull();

    final Process process = modelInstance.newInstance(Process.class);
    assertThat(process.getId()).isNotNull();

    final StartEvent startEvent = modelInstance.newInstance(StartEvent.class);
    assertThat(startEvent.getId()).isNotNull();

    final UserTask userTask = modelInstance.newInstance(UserTask.class);
    assertThat(userTask.getId()).isNotNull();
  }
}
