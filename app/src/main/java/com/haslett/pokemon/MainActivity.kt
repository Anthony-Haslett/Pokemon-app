package com.haslett.pokemon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.PrecomputedTextCompat
import androidx.core.widget.TextViewCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewTreeLifecycleOwner
import com.haslett.pokemon.data.remote.PostsService
import com.haslett.pokemon.data.remote.dto.PostResponse
import com.haslett.pokemon.ui.theme.PokemonTheme
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.glide.GlideImage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MainActivity : ComponentActivity() {
    
    // TODO this should be done via dependency inject via the view model
    private val service = PostsService.create()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        setContent {
            val posts = produceState<List<PostResponse>>(
                initialValue = emptyList(),
                producer = {
                    value = service.getPosts()
                }
            )
            PokemonTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Column {
                        Greeting("Ash")
                        
                        GlideImage(
                            imageModel = "https://upload.wikimedia.org/wikipedia/en/e/e4/Ash_Ketchum_Journeys.png",
                            // Crop, Fit, Inside, FillHeight, FillWidth, None
                            contentScale = ContentScale.Crop,
                            // shows an image with a circular revealed animation.
                            circularReveal = CircularReveal(duration = 250),
                            // shows a placeholder ImageBitmap when loading.
//                                placeHolder = ImageBitmap.imageResource(R.drawable.placeholder),
                            // shows an error ImageBitmap when the request failed.
//                                error = ImageBitmap.imageResource(R.drawable.),
                            modifier = Modifier.height(25.dp).width(25.dp)
                        )
                        
                        
                        LazyColumn {
                            items(posts.value) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp)
                                ) {
                                    Text(text = it.name, fontSize = 20.sp)
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text(text = it.description, fontSize = 14.sp)
                                    
//                                    Text(text = "pokemon name")
//                                    GlideImage(
//                                        imageModel = R.drawable.ashanime,
//                                        modifier = Modifier.size(150.dp)
//                                    )
                                }
                            }
                        }
                    }
                }
                
            }
        }
    }
    

}


@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!, lets go catch some pokemon!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PokemonTheme {
        Greeting("Ash")
    }
}
