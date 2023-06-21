/*
 * Copyright 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.zj.smart.widget.theme

import androidx.glance.color.ColorProvider
import androidx.glance.material3.ColorProviders
import com.zj.smart.ui.theme.DarkColorScheme
import com.zj.smart.ui.theme.LightColorScheme


object GlanceColorScheme {
    val colors = ColorProviders(
        light = LightColorScheme, dark = DarkColorScheme
    )

    val outlineVariant = ColorProvider(
        day = LightColorScheme.onSurface.copy(alpha = 0.1f),
        night = DarkColorScheme.onSurface.copy(alpha = 0.1f)
    )
}
