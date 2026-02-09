package com.example.shopnest.screen

import android.graphics.drawable.Icon
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.shopnest.R
import com.example.shopnest.navigation.Screen
import com.google.firebase.auth.FirebaseAuth

/**
 * @author EMRAN AHMED
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavHostController){

    val navItems = listOf(
        NavItems("Home", Icons.Default.Home),
        NavItems("Cart", Icons.Default.ShoppingCart),
        NavItems("Favourite", Icons.Default.Favorite),
        NavItems("Profile", Icons.Default.Person)
    )

    var selectedIndex by remember{
        mutableIntStateOf(0)
    }

    Scaffold (
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "ShopNest",
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(end = 16.dp),
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surfaceContainerHigh,
                    titleContentColor = MaterialTheme.colorScheme.onSurface
                )
            )
        }
        ,bottomBar = {
            NavigationBar {
                navItems.forEachIndexed { index, navItems ->
                    NavigationBarItem(
                        icon = {
                            Icon(navItems.icon, contentDescription = navItems.label)
                        },
                        label = {
                            Text(navItems.label)
                        },
                        selected = index==selectedIndex,
                        onClick = {
                            selectedIndex = index
                        }
                    )
                }

            }
        }
    ) {

        when(selectedIndex){
            0 -> HomeScreenMain(navController, modifier = Modifier.padding(it), selectedIndex)
            1 -> CartScreenScreen(navController, modifier = Modifier.padding(it), selectedIndex)
            2 -> FavouriteScreenScreen(navController, modifier = Modifier.padding(it), selectedIndex)
            3 -> ProfileScreenScreen(navController, modifier = Modifier.padding(it), selectedIndex)
        }


    }
}

data class NavItems(
    val label: String,
    val icon: ImageVector
)


@Composable
fun HomeScreenMain(navController: NavHostController, modifier: Modifier = Modifier, index: Int){
    Column (
        modifier = modifier.fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Home Screen",
            style = TextStyle(
                fontSize = 30.sp,
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.Bold
            )
        )

        Spacer(Modifier.height(20.dp))

        Icon(imageVector = Icons.Default.Home,
            contentDescription = null,
            Modifier.size(200.dp))

        Spacer(Modifier.height(20.dp))

        Button(onClick = {
            FirebaseAuth.getInstance().signOut()
            navController.navigate(Screen.Auth.route){
                popUpTo(Screen.Home.route){
                    inclusive = true
                }
            }
        },
            Modifier.fillMaxWidth()
                .height(60.dp)) {
            Text("Sign Out", fontSize = 22.sp)
        }
    }
}


@Composable
fun CartScreenScreen(navController: NavHostController, modifier: Modifier = Modifier, index: Int){
    Column (
        modifier = modifier.fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Cart Screen",
            style = TextStyle(
                fontSize = 30.sp,
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.Bold
            )
        )

        Spacer(Modifier.height(20.dp))

        Icon(imageVector = Icons.Default.ShoppingCart,
            contentDescription = null,
            Modifier.size(200.dp))
    }
}

@Composable
fun FavouriteScreenScreen(navController: NavHostController, modifier: Modifier = Modifier, index: Int){
    Column (
        modifier = modifier.fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Favourite Screen",
            style = TextStyle(
                fontSize = 30.sp,
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.Bold
            )
        )

        Spacer(Modifier.height(20.dp))

        Icon(imageVector = Icons.Default.Favorite,
            contentDescription = null,
            Modifier.size(200.dp))
    }
}

@Composable
fun ProfileScreenScreen(navController: NavHostController, modifier: Modifier = Modifier, index: Int){
    Column (
        modifier = modifier.fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Profile Screen",
            style = TextStyle(
                fontSize = 30.sp,
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.Bold
            )
        )

        Spacer(Modifier.height(20.dp))

        Icon(imageVector = Icons.Default.Person,
            contentDescription = null,
            Modifier.size(200.dp))
    }
}


