package jp.speakbudddy.edisonandroidexercise.feature.facthistory

import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import jp.speakbudddy.edisonandroidexercise.feature.facthistory.ui.FactHistoryScreen
import kotlinx.serialization.Serializable

@Serializable
object FactHistoryRoute

fun NavGraphBuilder.factHistoryNavGraph(
    modifier: Modifier = Modifier,
    onNavIconClick: () -> Unit,
) {
    composable<FactHistoryRoute> {
        FactHistoryScreen(
            modifier = modifier,
            factHistoryViewModel = hiltViewModel(),
            onNavIconClick = onNavIconClick,
        )
    }
}
