package com.example.shopnest.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.shopnest.pages.CartPage
import com.example.shopnest.components.Categories
import com.example.shopnest.pages.FavouritePage
import com.example.shopnest.components.BannerView
import com.example.shopnest.pages.ProfilePage
import com.example.shopnest.navigation.Screen
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.firestore

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
            0 -> HomeScreenMain(navController, modifier = Modifier.padding(it))
            1 -> CartPage(navController, modifier = Modifier.padding(it))
            2 -> FavouritePage(navController, modifier = Modifier.padding(it))
            3 -> ProfilePage(navController, modifier = Modifier.padding(it))
        }
    }
}

data class NavItems(
    val label: String,
    val icon: ImageVector
)


@Composable
fun HomeScreenMain(navController: NavHostController, modifier: Modifier){
    var name by remember {
        mutableStateOf("")
    }

    Column (
        modifier = modifier
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Column (verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier) {

            Spacer(Modifier.height(10.dp))

            Row ( modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "Welcome back",
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontFamily = FontFamily.Monospace
                        )
                    )

                    LaunchedEffect(Unit) {
                        Firebase.firestore.collection("users")
                            .document(FirebaseAuth.getInstance().currentUser?.uid!!)
                            .get()
                            .addOnCompleteListener {
                                name = it.result.get("name").toString().split(" ")[0]
                            }
                    }

                    Text( text = name,
                        style = TextStyle(
                            fontSize = 18.sp,
                            fontFamily = FontFamily.Monospace,
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }

                Row (horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ExitToApp,
                        contentDescription = "Sign Out",
                        modifier = Modifier.clickable{
                            FirebaseAuth.getInstance().signOut()
                            navController.navigate(Screen.Auth.route){
                                popUpTo(Screen.Home.route){
                                    inclusive = true
                                }
                            }
                        }
                    )
                }
            }
            Spacer(modifier = Modifier.height(12.dp))

            BannerView(modifier)

            Spacer(Modifier.height(10.dp))
            Text(text = "Categories",
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.Start))
            Spacer(Modifier.height(10.dp))
            Categories(modifier)
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview(){
    HomeScreen(navController = rememberNavController())
}

