package com.jhavidit.movielisting.movielisting

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.jhavidit.movielisting.R


@Composable
fun MovieDetailCard(data: MovieDetailCardData) {
    Column(modifier = Modifier.padding(12.dp)) {
        HorizontalDivider(
            thickness = 1.dp,
            color = Color.Gray
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 12.dp, end = 12.dp,top = 12.dp)
                .clickable {
                    data.onClick.invoke(data.id)
                }
        ) {
            Column(
                modifier = Modifier.weight(0.3f),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start
            ) {
                AsyncImage(
                    modifier = Modifier
                        .height(120.dp)
                        .clip(RoundedCornerShape(12.dp)),
                    contentScale = ContentScale.Crop,
                    model = data.image,
                    placeholder = painterResource(R.drawable.ic_launcher_background),
                    contentDescription = "Poster"
                )

                RatingCard(modifier = Modifier.padding(top = 12.dp), rating = data.rating)
            }
            Column(
                modifier = Modifier
                    .weight(0.7f)
                    .padding(start = 8.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start
            )
            {
                Text(
                    text = data.title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                )
                Text(
                    text = data.description,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp,
                )
            }
        }
    }

}


@Composable
fun RatingCard(modifier: Modifier = Modifier, rating: String) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Black)
    ) {
        Row(
            modifier = Modifier.padding(8.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(R.drawable.star_icon),
                contentDescription = "Rating Icon"
            )
            Text(
                modifier = Modifier.padding(horizontal = 4.dp),
                text = rating,
                color = Color.White
            )
        }
    }
}