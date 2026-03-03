package com.example.shopnest.screen

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
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
import com.example.shopnest.components.BannerView
import com.example.shopnest.components.CategoriesView
import com.example.shopnest.components.TopBar
import com.example.shopnest.model.UserModel
import com.example.shopnest.pages.CartPage
import com.example.shopnest.pages.FavouritePage
import com.example.shopnest.pages.ProfilePage
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.firestore
import kotlin.collections.forEachIndexed

/**
 * @author EMRAN AHMED
 */

data class NavItem(
    val label: String,
    val icon: ImageVector,
    val selectedIcon: ImageVector
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavHostController,
    onThemeToggle: () -> Unit,
){

    val navItems = listOf(
        NavItem("Home",
            Icons.Outlined.Home,
            Icons.Filled.Home),
        NavItem("Cart",
            Icons.Outlined.ShoppingCart,
            Icons.Filled.ShoppingCart),
        NavItem("Favourite",
            Icons.Outlined.FavoriteBorder,
            Icons.Filled.Favorite),
        NavItem("Profile",
            Icons.Outlined.Person,
            Icons.Filled.Person)
    )

    var selectedIndex by rememberSaveable{
        mutableIntStateOf(0)
    }

    var userModel by remember {
        mutableStateOf(UserModel())
    }

    DisposableEffect(Unit) {
        val listener = Firebase.firestore.collection("users")
            .document(FirebaseAuth.getInstance().currentUser?.uid!!)

            .addSnapshotListener { snapshot, _ ->
                if (snapshot != null && snapshot.exists()){
                    val result = snapshot.toObject(UserModel::class.java)

                    if (result != null) {
                        userModel = result
                    }
                }
            }

        onDispose {
            listener.remove()
        }
    }

    val cartSize = userModel.cartItems.size

    Scaffold (
        topBar = {
            TopBar(
                title = "ShopNest"
            )
        }
        ,bottomBar = {
            BottomBar(
                navItems = navItems,
                selectedIndex = selectedIndex,
                cartSize = cartSize,
                onItemSelected = { selectedIndex = it }
            )
        }
    ) {

        when(selectedIndex){
            0 -> HomeScreenMain(navController, modifier = Modifier.padding(it))
            1 -> CartPage(navController, modifier = Modifier.padding(it))
            2 -> FavouritePage(navController, modifier = Modifier.padding(it))
            3 -> ProfilePage(navController, modifier = Modifier.padding(it), onThemeToggle =  onThemeToggle)
        }
    }
}

@Composable
fun BottomBar(
    navItems: List<NavItem> ,
    selectedIndex: Int,
    cartSize : Int,
    onItemSelected: (Int) -> Unit
) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surface,
        tonalElevation = 0.dp
    ) {
        navItems.forEachIndexed { index, item ->
            val isSelected = index == selectedIndex

            NavigationBarItem(
                selected = isSelected,
                onClick = { onItemSelected(index) },
                label = null,
                icon = {
                    if (index == 1){
                        BadgedBox(
                            badge = {
                                if (cartSize > 0){
                                    Badge(
                                        containerColor = Color.Red,
                                        contentColor = Color.White
                                    ) {
                                        Text(text = cartSize.toString())
                                    }
                                }
                            }
                        ) {
                            Icon(
                                imageVector = if (isSelected) item.selectedIcon else item.icon,
                                contentDescription = item.label,
                                modifier = Modifier
                                    .size(26.dp)
                            )
                        }
                    } else{

                        Icon(
                            imageVector = if (isSelected) item.selectedIcon else item.icon,
                            contentDescription = item.label,
                            modifier = Modifier
                                .size(26.dp)
                        )
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)
                )
            )
        }
    }
}

@Composable
fun HomeScreenMain(
    navController: NavHostController,
    modifier: Modifier
){
    var name by remember {
        mutableStateOf("")
    }

    Column (
        modifier = modifier
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Column (
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
        ) {

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
                }
            }
            Spacer(modifier = Modifier.height(12.dp))

            BannerView(modifier)

            Spacer(Modifier.height(10.dp))
            Text(text = "Categories",
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.Start))
            Spacer(Modifier.height(10.dp))

            CategoriesView(modifier, navController)
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview(){
    HomeScreen(
        navController = rememberNavController(),
        onThemeToggle = {})
}

