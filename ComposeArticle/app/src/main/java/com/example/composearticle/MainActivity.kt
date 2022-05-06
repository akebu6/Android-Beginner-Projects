package com.example.composearticle

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composearticle.ui.theme.ComposeArticleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeArticleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ArticleImage(
                        articleTitle = stringResource(R.string.article_title),
                        articleIntro = stringResource(R.string.article_introduction),
                        articleBody = """In this tutorial, you build a simple UI component with declarative functions. You call Compose functions to say what elements you want and the Compose compiler does the rest. Compose is built around Composable functions. These functions let you define your app\'s UI programmatically because they let you describe how it should look and provide data dependencies, rather than focus on the process of the UI\'s construction, such as initializing an element and then attaching it to a parent. To create a Composable function, you add the @Composable annotation to the function name."""
                    )
                }
            }
        }
    }
}
// creating a compose to handle the article's image header
@Composable
fun ArticleImage(articleTitle: String, articleIntro: String, articleBody: String) {
    val image = painterResource(id = R.drawable.compose_header)
    Column {
        Image(
            painter = image,
            contentDescription = stringResource(R.string.article_title_text),
            modifier = Modifier.fillMaxWidth()
        )
        ArticleTitle(articleTitle = articleTitle)
        ArticleContent(articleIntro = articleIntro, articleBody = articleBody)
    }
}

// creating a compose to handle the article's title
@Composable
fun ArticleTitle(articleTitle: String) {
        Text(
            text = articleTitle,
            style = MaterialTheme.typography.h5,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(align = Alignment.Start)
                .padding(start = 16.dp, top = 16.dp, end = 16.dp, bottom = 16.dp)
        )
}

@Composable
fun ArticleContent(articleIntro: String, articleBody: String) {
    Column {
        Text(
            text = articleIntro,
            style = MaterialTheme.typography.body1,
            fontSize = 16.sp,
            textAlign = TextAlign.Justify,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(align = Alignment.Start)
                .padding(start = 16.dp, top = 16.dp, end = 16.dp, bottom = 16.dp)
        )
        Text(
            text = articleBody,
            style = MaterialTheme.typography.body1,
            fontSize = 16.sp,
            textAlign = TextAlign.Justify,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(align = Alignment.Start)
                .padding(start = 16.dp, top = 16.dp, end = 16.dp, bottom = 16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeArticleTheme {
        ArticleImage(
            articleTitle = stringResource(R.string.article_title),
            articleIntro = stringResource(R.string.article_introduction),
            articleBody = """In this tutorial, you build a simple UI component with declarative functions. You call Compose functions to say what elements you want and the Compose compiler does the rest. Compose is built around Composable functions. These functions let you define your app\'s UI programmatically because they let you describe how it should look and provide data dependencies, rather than focus on the process of the UI\'s construction, such as initializing an element and then attaching it to a parent. To create a Composable function, you add the @Composable annotation to the function name."""
        )
    }
}