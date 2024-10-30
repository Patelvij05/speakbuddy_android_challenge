package jp.speakbuddy.edisonandroidexercise.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import jp.speakbuddy.edisonandroidexercise.feature.fact.navigation.FactRoute
import jp.speakbuddy.edisonandroidexercise.feature.fact.navigation.factNavGraph
import jp.speakbuddy.edisonandroidexercise.feature.facthistory.navigation.FactHistoryRoute
import jp.speakbuddy.edisonandroidexercise.feature.facthistory.navigation.factHistoryNavGraph

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
