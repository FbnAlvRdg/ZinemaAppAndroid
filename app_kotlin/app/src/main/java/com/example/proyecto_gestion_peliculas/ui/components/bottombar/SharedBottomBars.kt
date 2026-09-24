package com.example.proyecto_gestion_peliculas.ui.components.bottombar


import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import com.example.proyecto_gestion_peliculas.R

@Composable
fun AppBottomBar(
    onHome: () -> Unit,
    onList: () -> Unit,
    onMostPopular: () -> Unit,
    onTopRated: () -> Unit,
    onLogOut: () -> Unit
) {
    BottomAppBar(
        containerColor = MaterialTheme.colorScheme.secondary,
        contentColor = MaterialTheme.colorScheme.onSecondary
    ) {
        NavigationBarItem(
            selected = false,
            onClick = {},
            icon = {
                IconButton(
                    onClick = { onHome() },
                    colors = IconButtonDefaults.iconButtonColors(
                        contentColor = MaterialTheme.colorScheme.onSecondary
                    )
                ) {
                    Icon(
                        painter = painterResource(R.drawable.home_icon),
                        contentDescription = "Home"
                    )
                }
            }
        )
        NavigationBarItem(
            selected = false,
            onClick = {},
            icon = {
                IconButton(
                    onClick = { onMostPopular() },
                    colors = IconButtonDefaults.iconButtonColors(
                        contentColor = MaterialTheme.colorScheme.onSecondary
                    )
                ) {
                    Icon(
                        painterResource(R.drawable.most_popular_icon),
                        contentDescription = "Most Popular"

                    )
                }
            }
        )
        NavigationBarItem(
            selected = false,
            onClick = {},
            icon = {
                IconButton(
                    onClick = { onTopRated() },
                    colors = IconButtonDefaults.iconButtonColors(
                        contentColor = MaterialTheme.colorScheme.onSecondary
                    )
                ) {
                    Icon(
                        painterResource(R.drawable.top_rated_icon),
                        contentDescription = "Top Rated"
                    )
                }
            }
        )

        NavigationBarItem(
            selected = false,
            onClick = {},
            icon = {
                IconButton(
                    onClick = { onList() },
                    colors = IconButtonDefaults.iconButtonColors(
                        contentColor = MaterialTheme.colorScheme.onSecondary
                    )
                ) {
                    Icon(
                        painterResource(R.drawable.lists_icon),
                        contentDescription = "Lists"
                    )
                }
            }
        )

        NavigationBarItem(
            selected = false,
            onClick = {},
            icon = {
                IconButton(
                    onClick = { onLogOut() },
                    colors = IconButtonDefaults.iconButtonColors(
                        contentColor = MaterialTheme.colorScheme.onSecondary
                    )
                ) {
                    Icon(
                        painterResource(R.drawable.log_out_icon),
                        contentDescription = "Log out"
                    )
                }
            }
        )


    }
}