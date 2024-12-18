package com.example.a2024_10_cdan.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.a2024_10_cdan.model.PictureBean
import com.example.a2024_10_cdan.ui.screens.DetailScreen
import com.example.a2024_10_cdan.ui.screens.SearchScreen
import com.example.a2024_10_cdan.viewmodel.MainViewModel

//sealed permet de dire qu'une classe est héritable (ici par SearchScreen et DetailScreen)
//Uniquement par les sous classes qu'elle contient
sealed class Routes(val route: String) {
    //Route 1
    data object SearchScreen : Routes("homeScreen")

    //Route 2 avec paramètre
    data object DetailScreen : Routes("detailScreen/{id}") {
        //Méthode(s) qui vienne(nt) remplit le ou les paramètres
        fun withId(id: Int) = "detailScreen/$id"

        fun withObject(data: PictureBean) = "detailScreen/${data.id}"
    }
}

@Composable
fun AppNavigation(modifier: Modifier = Modifier) {

    val navHostController: NavHostController = rememberNavController()
    //viewModel() en dehors de NavHost lie à l'Activité donc partagé entre les écrans
    //viewModel() dans le NavHost lié à la stack d'écran. Une instance par stack d'écran
    val mainViewModel: MainViewModel = viewModel()

    //Import version avec Composable
    NavHost(
        navController = navHostController, startDestination = Routes.SearchScreen.route,
        modifier = modifier
    ) {
        //Route 1 vers notre SearchScreen
        composable(Routes.SearchScreen.route) {
            //on peut passer le navHostController à un écran s'il déclenche des navigations
            SearchScreen(navHostController = navHostController, mainViewModel = mainViewModel)
        }

        //Route 2 vers un écran de détail
        composable(
            route = Routes.DetailScreen.route,
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) {
            val id = it.arguments?.getInt("id") ?: 1
            val pictureBean = mainViewModel.dataList.first { it.id == id }
            DetailScreen(
                pictureBean = pictureBean,
                navHostController = navHostController
            )
        }
    }
}



