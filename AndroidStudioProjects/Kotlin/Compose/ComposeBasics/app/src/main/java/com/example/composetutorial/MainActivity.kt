package com.example.composetutorial

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
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
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
//                    Conversation(SampleData.conversationSample)
                }
            }
        }
    }


    //Create object Message
    data class Message(val author: String, val body: String)

    object SampleData {
        // Sample conversation data
        val conversationSample = listOf(
            Message(
                "Colleague",
                "Test...Test...Test..."
            ),
            Message(
                "Colleague",
                "List of Android versions:\n" +
                        "Android KitKat (API 19)\n" +
                        "Android Lollipop (API 21)\n" +
                        "Android Marshmallow (API 23)\n" +
                        "Android Nougat (API 24)\n" +
                        "Android Oreo (API 26)\n" +
                        "Android Pie (API 28)\n" +
                        "Android 10 (API 29)\n" +
                        "Android 11 (API 30)\n" +
                        "Android 12 (API 31)\n"
            ),
            Message(
                "Colleague",
                "I think Kotlin is my favorite programming language.\n" +
                        "It's so much fun!"
            ),
            Message(
                "Colleague",
                "Searching for alternatives to XML layouts..."
            ),
            Message(
                "Colleague",
                "Hey, take a look at Jetpack Compose, it's great!\n" +
                        "It's the Android's modern toolkit for building native UI." +
                        "It simplifies and accelerates UI development on Android." +
                        "Less code, powerful tools, and intuitive Kotlin APIs :)"
            ),
            Message(
                "Colleague",
                "It's available from API 21+ :)"
            ),
            Message(
                "Colleague",
                "Writing Kotlin for UI seems so natural, Compose where have you been all my life?"
            ),
            Message(
                "Colleague",
                "Android Studio next version's name is Arctic Fox"
            ),
            Message(
                "Colleague",
                "Android Studio Arctic Fox tooling for Compose is top notch ^_^"
            ),
            Message(
                "Colleague",
                "I didn't know you can now run the emulator directly from Android Studio"
            ),
            Message(
                "Colleague",
                "Compose Previews are great to check quickly how a composable layout looks like"
            ),
            Message(
                "Colleague",
                "Previews are also interactive after enabling the experimental setting"
            ),
            Message(
                "Colleague",
                "Have you tried writing build.gradle with KTS?"
            ),
        )
    }

    @Composable
    fun Conversation(message: List<Message>) {
        LazyColumn {
            items(message) { message ->
                MessageCard(message)
            }
        }
    }

    @Preview
    @Composable
    fun PreviewConversation() {
        ComposeTutorialTheme {
            Conversation(SampleData.conversationSample)
        }
    }

    @Composable
    fun MessageCard(msg: Message) {
        //Add a padding around message
        Row(Modifier.padding(all = 8.dp)) {
            Image(
                painter = painterResource(id = R.drawable.screen_shot_2022_01_22_at_21_41_35),
                contentDescription = "Contact profile picture",
                modifier = Modifier
                    //Set image to 40 dp
                    .size(40.dp)
                    //Clip image to be shaped as a circle
                    .clip(CircleShape)
                    .border(1.5.dp, MaterialTheme.colors.onSurface, CircleShape)
            )
            //Add a horizontal space between image and the column
            Spacer(modifier = Modifier.width(8.dp))
            var isExpanded by remember {
                mutableStateOf(false)
            }
// We toggle the isExpanded variable when we click on this Column
            Column(modifier = Modifier.clickable {
                isExpanded = !isExpanded
            }) {
                Text(
                    text = msg.author,
                    color = MaterialTheme.colors.primary,
                    style = MaterialTheme.typography.h1

                )
                //Add a vertical shape between author and message
                Spacer(modifier = Modifier.height(4.dp))
                // We keep track if the message is expanded or not in this
                // variable
                Surface(shape = MaterialTheme.shapes.medium, elevation = 1.dp) {
                    Text(
                        text = msg.body,
                        color = Color.Gray,
                        style = MaterialTheme.typography.body2,
                        modifier = Modifier.padding(all = 4.dp),
                        // If the message is expanded, we display all its content
                        // otherwise we only display the first line
                        maxLines = if (isExpanded) Int.MAX_VALUE else 1
                    )
                }
            }
        }
    }

    @Preview(
        uiMode = Configuration.UI_MODE_NIGHT_YES,
        showBackground = true,
        name = "NightMode"
    )


    @Preview(showBackground = true, name = "DefaultPreivew")
    @Composable
    fun DefaultPreview() {
        ComposeTutorialTheme {
            Conversation(
                SampleData.conversationSample
            )
        }
    }
}


