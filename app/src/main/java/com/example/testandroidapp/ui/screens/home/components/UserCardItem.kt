package com.example.testandroidapp.ui.screens.home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.testandroidapp.domain.models.UserModel

@Composable
fun UserCardItem(user: UserModel, navController: NavController) {

    Card() {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Spacer(modifier = Modifier.padding(top = 10.dp))
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(user.avatar_url)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(150.dp)
                    .align(Alignment.CenterHorizontally)
                    .clip(CircleShape)
            )
            Text(
                text = user.login,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 14.dp, bottom = 14.dp),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Button(
                modifier = Modifier
                    .height(40.dp)
                    .padding(horizontal = 20.dp),
                onClick = {
                    navController.navigate("detail" + "/${user.login}")

                }) {
                Text(text = "View Mode Details")
            }
            Spacer(modifier = Modifier.padding(bottom = 10.dp))

        }
    }
}