package jp.speakbuddy.edisonandroidexercise.feature.fact.navigation

import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import jp.speakbuddy.edisonandroidexercise.feature.fact.ui.FactScreen
import kotlinx.serialization.Serializable

@Serializable
object FactRoute

fun NavGraphBuilder.factNavGraph(
    modifier: Modifier = Modifier,
    onTopAppBarActionClick: () -> Unit,
) {
    composable<FactRoute> {
        FactScreen(
            modifier = modifier,
            factViewModel = hiltViewModel(),
            onTopAppBarActionClick = onTopAppBarActionClick,
        )
    }
}
