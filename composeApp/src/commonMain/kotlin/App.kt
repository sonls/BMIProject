import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import bmiproject.composeapp.generated.resources.Res
import bmiproject.composeapp.generated.resources.compose_multiplatform
import kotlin.math.pow

@Composable
@Preview
fun App() {
    MaterialTheme {
        var inputWeight by remember { mutableStateOf("") }
        var inputHeight by remember { mutableStateOf("") }
        var result by remember { mutableStateOf("") }
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                modifier = Modifier.padding(top = 49.dp),
                text = "BMI Calculator",
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp
            )
            Row(
                modifier = Modifier.fillMaxWidth().padding(top = 20.dp).padding(
                    horizontal = 16.dp
                ), verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Your weight",
                    color = Color.Black,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp
                )
                TextField(
                    modifier = Modifier.padding(start = 12.dp),
                    value = inputWeight,
                    onValueChange = { inputWeight = it },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth().padding(top = 16.dp).padding(
                    horizontal = 16.dp
                ), verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Your height",
                    color = Color.Black,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp
                )
                TextField(
                    modifier = Modifier.padding(start = 12.dp),
                    value = inputHeight,
                    onValueChange = { inputHeight = it },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
                )
            }
            Button(
                modifier = Modifier.padding(top = 16.dp),
                onClick = {
                    result = calculateBMI(
                        inputWeight.replace(",", ".").toFloat(),
                        inputHeight.replace(",", ".").toFloat()
                    )
                }) {
                Text("Calculate")
            }
            if (result.isNotEmpty()) {
                Text(
                    text = "Your BMI is: $result",
                    modifier = Modifier.padding(top = 20.dp),
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            }

        }
    }
}

fun calculateBMI(weight: Float, height: Float): String {
    val result = weight / height.pow(2);
    if (result <= 18.4) return "Underweight";
    if (result in 18.5..24.9) return "Normal";
    if (result in 25.0..39.9) return "Overweight";
    if (result >= 40.0) return "Obese";
    return "";
}