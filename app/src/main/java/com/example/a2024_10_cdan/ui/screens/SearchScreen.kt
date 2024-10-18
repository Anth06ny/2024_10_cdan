package com.example.a2024_10_cdan.ui.screens

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.example.a2024_10_cdan.R
import com.example.a2024_10_cdan.model.PictureBean
import com.example.a2024_10_cdan.ui.theme._2024_10_cdanTheme
import com.example.a2024_10_cdan.viewmodel.MainViewModel

@Preview(showBackground = true, showSystemUi = true)
@Preview(showBackground = true, showSystemUi = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun SearchScreenPreview() {
    //Il faut remplacer NomVotreAppliTheme par le thème de votre application
    //Utilisé par exemple dans MainActivity.kt sous setContent {...}
    _2024_10_cdanTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

            var viewModel = MainViewModel()
            SearchScreen(modifier = Modifier.padding(innerPadding),
            mainViewModel = viewModel
            )
        }
    }
}

@Composable
fun SearchScreen(modifier: Modifier = Modifier, mainViewModel: MainViewModel) {
    Column (modifier= modifier.fillMaxSize()) {

        repeat(mainViewModel.dataList.size) {
            PictureRowItem(data =  mainViewModel.dataList[it])
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable //Composable affichant 1 PictureBean
fun PictureRowItem(modifier: Modifier = Modifier, data: PictureBean) {
    Row(modifier= modifier.fillMaxWidth().background(MaterialTheme.colorScheme.tertiary)) {
        //Permission Internet nécessaire
        GlideImage(
            model = data.url,
            //Pour aller le chercher dans string.xml
            //contentDescription = getString(R.string.picture_of_cat),
            //En dur
            contentDescription = "une photo de chat",
            // Image d'attente. Permet également de voir l'emplacement de l'image dans la Preview
            loading = placeholder(R.mipmap.ic_launcher_round),
            // Image d'échec de chargement
            failure = placeholder(R.mipmap.ic_launcher),
            contentScale = ContentScale.Fit,
            //même autres champs qu'une Image classique
            modifier = Modifier.heightIn(max = 100.dp) //Sans hauteur il prendra tous l'écran
                .widthIn(max= 100.dp)
        )

        Column(modifier= Modifier.padding(4.dp)) {
            Text(text = data.title, fontSize = 20.sp, color = MaterialTheme.colorScheme.onPrimaryContainer                 )
            Text(data.longText.take(20) + "...",
                color =  MaterialTheme.colorScheme.onTertiary,
                fontSize = 14.sp )
        }
    }
}