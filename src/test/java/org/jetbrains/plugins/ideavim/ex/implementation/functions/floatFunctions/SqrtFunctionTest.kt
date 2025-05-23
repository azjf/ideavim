/*
 * Copyright 2003-2025 The IdeaVim authors
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE.txt file or at
 * https://opensource.org/licenses/MIT.
 */

package org.jetbrains.plugins.ideavim.ex.implementation.functions.floatFunctions

import org.jetbrains.plugins.ideavim.VimTestCase
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInfo

class SqrtFunctionTest : VimTestCase() {
  @BeforeEach
  override fun setUp(testInfo: TestInfo) {
    super.setUp(testInfo)
    configureByText("\n")
  }

  @Test
  fun `test sqrt with integer value`() {
    assertCommandOutput("echo sqrt(100)", "10.0")
  }

  @Test
  fun `test sqrt with float value`() {
    assertCommandOutput("echo sqrt(4.0)", "2.0")
  }

  @Test
  fun `test sqrt with negative float value`() {
    assertCommandOutput("echo sqrt(-4.01)", "nan")
  }

  @Test
  fun `test sqrt with string causes errors`() {
    enterCommand("echo sqrt('1.0')")
    assertPluginError(true)
    assertPluginErrorMessageContains("E808: Number or Float required")
  }

  @Test
  fun `test sqrt with invalid string causes errors`() {
    enterCommand("echo sqrt('cheese')")
    assertPluginError(true)
    assertPluginErrorMessageContains("E808: Number or Float required")
  }

  @Test
  fun `test sqrt with list causes errors`() {
    enterCommand("echo sqrt([1.0])")
    assertPluginError(true)
    assertPluginErrorMessageContains("E808: Number or Float required")
  }

  @Test
  fun `test sqrt with dictionary causes errors`() {
    enterCommand("echo sqrt({1: 1.0})")
    assertPluginError(true)
    assertPluginErrorMessageContains("E808: Number or Float required")
  }
}
