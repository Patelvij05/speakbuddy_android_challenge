package jp.speakbuddy.edisonandroidexercise.theme.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.rounded.History
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import jp.speakbuddy.edisonandroidexercise.theme.EdisonAndroidExerciseTheme

@Composable
fun AppBar(
    title: String,
    navIcon: ImageVector? = null,
    onNav: () -> Unit = {},
    actionIcon: ImageVector? = null,
    actionIconContentDescription: String? = "",
    onActionClick: () -> Unit = {},
) {
    CenterAlignedTopAppBar(
        colors =
            TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                titleContentColor = MaterialTheme.colorScheme.primary,
            ),
        title = {
            Text(text = title)
        },
        navigationIcon = {
            navIcon?.let {
                IconButton(onClick = { onNav() }) {
                    Icon(navIcon, contentDescription = "Nav Icon")
                }
            }
        },
        actions = {
            IconButton(onClick = onActionClick) {
                actionIcon?.let {
                    Icon(
                        imageVector = actionIcon,
                        contentDescription = actionIconContentDescription,
                        tint = MaterialTheme.colorScheme.onSurface,
                    )
                }
            }
        },
    )
}

@Composable
@EdisonPreview
private fun AppBarPreview(modifier: Modifier = Modifier) {
    EdisonAndroidExerciseTheme {
        Surface {
            AppBar(
                title = "Fact",
                navIcon = Icons.AutoMirrored.Filled.ArrowBack,
                actionIcon = Icons.Rounded.History,
                actionIconContentDescription = "History",
            )
        }
    }
}
