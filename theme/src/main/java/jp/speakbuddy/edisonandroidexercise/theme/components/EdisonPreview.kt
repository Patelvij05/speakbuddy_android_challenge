package jp.speakbuddy.edisonandroidexercise.theme.components

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewDynamicColors
import androidx.compose.ui.tooling.preview.PreviewFontScale
import androidx.compose.ui.tooling.preview.PreviewScreenSizes

@Preview(showSystemUi = true)
@PreviewScreenSizes
@PreviewFontScale
@PreviewDynamicColors
@Preview(
    name = "Preview Day",
    showBackground = true,
    uiMode = UI_MODE_NIGHT_NO,
)
@Preview(
    name = "Preview Night",
    showBackground = true,
    uiMode = UI_MODE_NIGHT_YES,
)
annotation class EdisonPreview
