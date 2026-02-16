package com.example.shopnest.components

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.tbuonomo.viewpagerdotsindicator.compose.DotsIndicator
import com.tbuonomo.viewpagerdotsindicator.compose.model.DotGraphic
import com.tbuonomo.viewpagerdotsindicator.compose.type.ShiftIndicatorType

/**
 * @author EMRAN AHMED
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BannerView(modifier: Modifier = Modifier) {
    var bannerList by remember {
        mutableStateOf<List<String>>(emptyList())
    }

    LaunchedEffect(Unit) {

        Firebase.firestore.collection("data")
            .document("banners")
            .get()
            .addOnCompleteListener { doc ->
                bannerList = doc.result.get("urls") as List<String> // ?: emptyList()
                Log.d("HEADER", "DATA = ${doc.result.data}")
            }
    }

    Column {
        val pagerState = rememberPagerState(
            initialPage = 0,
            pageCount = { bannerList.size }
        )
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.padding(2.dp), verticalAlignment = Alignment.CenterVertically

        ) { page ->
            AsyncImage(
                model = bannerList[page],
                contentDescription = null,
                modifier = Modifier.clip(RoundedCornerShape(12.dp))
            )
        }

        Spacer(modifier = Modifier.height(5.dp))

        DotsIndicator(
            dotCount = bannerList.size,
            pagerState = pagerState,
            type = ShiftIndicatorType(
                DotGraphic(
                    color = MaterialTheme.colorScheme.onSurface,
                    size = 8.dp
                )
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HeaderViewPreview() {
    BannerView()
}
