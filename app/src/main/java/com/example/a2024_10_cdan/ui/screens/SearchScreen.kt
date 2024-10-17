package com.example.a2024_10_cdan.ui.screens

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
            SearchScreen(modifier = Modifier.padding(innerPadding),
            mainViewModel = MainViewModel()
            )
        }
    }
}

@Composable
fun SearchScreen(modifier: Modifier = Modifier, mainViewModel: MainViewModel) {
    Column (modifier= modifier) {
        repeat(mainViewModel.dataList.size) {
            PictureRowItem(
                mainViewModel.dataList[it].title, Color.Blue,
                modifier = Modifier
                    .background(Color.Red)
                    .padding(1.dp)
                    .background(Color.Yellow)


            )
        }
    }
}

@Composable
fun PictureRowItem(text:String, color:Color, modifier:Modifier = Modifier ){
    Text( modifier = modifier , text = text,fontSize = 20.sp, color = color)
}