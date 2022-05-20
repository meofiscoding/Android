package com.example.composetutorial

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composetutorial.ui.theme.ComposeTutorialTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContent block: xac dinh bo cuc cua Activity, trong do cac ham Compose co the dc goi
        //Ban chi co the goi cac ham Compose tu cac ham Compose khac
        setContent {

            ComposeTutorialTheme {
                MessageCard(msg = Message("Thanh Tra", "Hello World"))
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
//                    Greeting("Android")
                }
            }
        }
    }
}
//Create object Message
data class Message(val author: String, val body: String)


@Composable
fun MessageCard(msg: Message) {
    //Add a padding around message
    Row(Modifier.padding(all = 8. dp)){
        Image(painter = painterResource(
            id = R.drawable.screen_shot_2022_01_22_at_21_41_35),
            contentDescription = "Contact profile picture",
            modifier = Modifier
                //Set image to 40 dp
                .size(40.dp)
                //Clip image to be shaped as a circle
                .clip(CircleShape)
        )
        //Add a horizontal space between image and the column
        Spacer(modifier = Modifier.width(8. dp))

        Column {
            Text(
                text = msg.author,
                color = MaterialTheme.colors.secondary,
                style = MaterialTheme.typography.subtitle2
            )
            //Add a vertical shape between author and message
            Spacer(modifier = Modifier.height(4. dp))
            Text(
                text =msg.body,
                style = MaterialTheme.typography.body2
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeTutorialTheme {
        MessageCard(
            msg = Message("Thanh Tra", "Hello World")
        )
    }
}


