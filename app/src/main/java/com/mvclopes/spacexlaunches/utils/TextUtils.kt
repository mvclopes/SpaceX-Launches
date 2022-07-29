package com.mvclopes.spacexlaunches.utils

import androidx.annotation.StringRes
import androidx.core.text.parseAsHtml
import androidx.fragment.app.Fragment

fun Fragment.getFormattedString(@StringRes resId: Int, text: String) = getString(resId, text).parseAsHtml()
