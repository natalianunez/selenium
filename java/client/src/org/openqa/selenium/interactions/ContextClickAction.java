// Licensed to the Software Freedom Conservancy (SFC) under one
// or more contributor license agreements.  See the NOTICE file
// distributed with this work for additional information
// regarding copyright ownership.  The SFC licenses this file
// to you under the Apache License, Version 2.0 (the
// "License"); you may not use this file except in compliance
// with the License.  You may obtain a copy of the License at
//
//   http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing,
// software distributed under the License is distributed on an
// "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
// KIND, either express or implied.  See the License for the
// specific language governing permissions and limitations
// under the License.

package org.openqa.selenium.interactions;

import com.google.common.collect.ImmutableList;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.internal.MouseAction;
import org.openqa.selenium.interactions.internal.Locatable;

import java.util.List;

/**
 * Context-clicks an element
 *
 * @deprecated Use {@link Actions#contextClick(WebElement)}
 */
public class ContextClickAction extends MouseAction implements Action {
  public ContextClickAction(Mouse mouse, Locatable where) {
    super(mouse, where);
  }

  /**
   * Emulates clicking on the mouse button that would bring up contextual menus (usually
   * right-clicking).
   */
  public void perform() {
    moveToLocation();
    mouse.contextClick(getActionLocation());
  }

  @Override
  public List<Interaction> asInteractions(PointerInput mouse, KeyInput keyboard) {
    ImmutableList.Builder<Interaction> interactions = ImmutableList.builder();

    moveToLocation(mouse, interactions);
    interactions.add(mouse.createPointerDown(Button.RIGHT.asArg()));
    interactions.add(mouse.createPointerUp(Button.RIGHT.asArg()));

    return interactions.build();
  }
}
