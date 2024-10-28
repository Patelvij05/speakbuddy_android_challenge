package jp.speakbuddy.edisonandroidexercise

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import jp.speakbudddy.edisonandroidexercise.feature.facthistory.FactHistoryRoute
import jp.speakbudddy.edisonandroidexercise.feature.facthistory.factHistoryNavGraph
import jp.speakbuddy.edisonandroidexercise.feature.fact.FactRoute
import jp.speakbuddy.edisonandroidexercise.feature.fact.factNavGraph

@Composable
fun EdisonNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = FactRoute,
        modifier = modifier,
    ) {
        factNavGraph(
            modifier = modifier,
            onTopAppBarActionClick = {
                navController.navigate(FactHistoryRoute)
            },
        )
        factHistoryNavGraph(
            modifier = modifier,
            onNavIconClick = {
                navController.popBackStack()
            },
        )
    }
}
