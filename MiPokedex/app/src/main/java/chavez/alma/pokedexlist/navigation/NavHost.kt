package chavez.alma.pokedexlist.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import chavez.alma.pokedexlist.model.data.getPokemonByNumber
import chavez.alma.pokedexlist.view.screens.MenuPokedexScreen
import chavez.alma.pokedexlist.view.screens.PokemonDetailScreen
import chavez.alma.pokedexlist.view.screens.PokemonHuntScreen
import chavez.alma.pokedexlist.view.screens.ProfileScreen
import chavez.alma.pokedexlist.viewmodel.PokemonDetailViewModel
import com.tu_paquete.pokedexlist.ui.LoginScreen
import com.tu_paquete.pokedexlist.viewmodel.UserViewModel

@Composable
fun MyApp(
    innerPadding: PaddingValues,
    userViewModel: UserViewModel,
    pokemonDetailViewModel: PokemonDetailViewModel
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Login
    ) {
        composable<Login> {
            LoginScreen(
                viewModel = userViewModel,
                onLoginSuccess = {
                    navController.navigate(PokemonList) {
                        // Limpia Login del historial para no regresar al presionar atrás
                        popUpTo<Login> { inclusive = true }
                    }
                }
            )
        }

        composable<PokemonList> {
            MenuPokedexScreen(
                innerPadding = innerPadding,
                onNavigateToDetail = { id ->
                    navController.navigate(route = PokemonDetail(id))
                },
                onNavigateToHunt = {
                    navController.navigate(route = PokemonHunt)
                },
                onNavigateToProfile = {
                    navController.navigate(route = Profile)
                }
            )
        }

        composable<PokemonDetail> { backStackEntry ->
            val detail: PokemonDetail = backStackEntry.toRoute()
            val pokemon = getPokemonByNumber(detail.pokemon)
            PokemonDetailScreen(
                innerPadding = innerPadding,
                pokemon = pokemon,
                pokemonDetailViewModel = pokemonDetailViewModel
            )
        }

        composable<PokemonHunt> {
            PokemonHuntScreen(innerPadding)
        }

        composable<Profile> {
            ProfileScreen(
                innerPadding = innerPadding,
                userViewModel = userViewModel
            )
        }
    }
}
